package com.easemob.livedemo.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.easemob.livedemo.DemoApplication;
import com.easemob.livedemo.R;
import com.easemob.livedemo.ThreadPoolManager;
import com.easemob.livedemo.custom.MESSAGE;
import com.easemob.livedemo.data.model.LiveRoom;
import com.easemob.livedemo.data.restapi.LiveManager;
import com.easemob.livedemo.net.Api;
import com.easemob.livedemo.net.bean.ChangeRoomModule;
import com.easemob.livedemo.net.bean.UserModule;
import com.easemob.livedemo.net.response.BaseResponse;
import com.easemob.livedemo.net.response.LoginModule;
import com.easemob.livedemo.net.service.DemoUser;
import com.easemob.livedemo.net.service.DemoUserList;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.cloud.EMCloudOperationCallback;
import com.hyphenate.cloud.HttpFileManager;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.exceptions.HyphenateException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserActivity extends BaseActivity implements EMMessageListener {

    public static final String CHAT_ROOM = "61095445331969";


    private MyContactListener listener = new MyContactListener();
    private String userToadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        String user = DemoApplication.getInstance().getCurrentUserName();
        Button butn = (Button) findViewById(R.id.btn_click);
        if (user.startsWith("A")) {
            butn.setText("请求直播");
        } else if (user.startsWith("B")) {
            butn.setText("上线");
        } else if (user.startsWith("C")) {
            butn.setText("上线");
        } else if (user.startsWith("E")) {
            butn.setText("上线");
        } else {
            butn.setText("上线");
        }
        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = DemoApplication.getInstance().getCurrentUserName();
                if (user.startsWith("A")) {
                    startLive();
                } else {
                    onlive();
                }
            }
        });
    }

    private void onlive() {
        DemoUser user = Api.create(DemoUser.class);
        UserModule currentUser = DemoApplication.getInstance().getmUser();
        currentUser.status = UserModule.STATUS_ONLINE;
        user.update(currentUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse<UserModule>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(BaseResponse<UserModule> userModuleBaseResponse) {
                        if (userModuleBaseResponse == null && userModuleBaseResponse.Code != 0) {
                            showMSG(userModuleBaseResponse.Message);
                            return;
                        }
                        DemoApplication.getInstance().setmUser(userModuleBaseResponse.Data);
                        startActivity(new Intent(UserActivity.this, LiveAudienceActivity.class));
                    }
                });
    }

    private void startLive() {
        showProgressDialog("正在请求直播...");
        //1.获取在线的B的列表,自动选取一个B
        final DemoUserList user = Api.create(DemoUserList.class);
        user.get(UserModule.B, UserModule.STATUS_ONLINE)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse<List<UserModule>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dismissProgressDialog();
                    }

                    @Override
                    public void onNext(BaseResponse<List<UserModule>> userModuleBaseResponse) {
                        if (userModuleBaseResponse == null || userModuleBaseResponse.Data == null || userModuleBaseResponse.Data.size() == 0) {
                            dismissProgressDialog();
                            showNotActive();
                            return;
                        }
                        final UserModule b = userModuleBaseResponse.Data.get(0);
                        userToadd = b.name;
                        //-------设置为默认的聊天室-------------------
                        DemoApplication.getInstance().setOther(b);
                        Intent intent = new Intent(UserActivity.this, LiveAnchorActivity.class);
                        intent.putExtra("liveroom", DemoApplication.getInstance().getRoom());
                        startActivity(intent);
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
//                                    //从唤醒 取得是小写的
//                                    if (!usernames.contains(b.name.toLowerCase())) {
//                                        EMClient.getInstance().contactManager().addContact(b.name, "live");
//                                    }else{
//                                        createRoom();
//                                    }
//                                } catch (HyphenateException e) {
//                                    dismissProgressDialog();
//                                    DemoApplication.getInstance().setOther(null);
//                                    e.printStackTrace();
//                                    return;
//                                }
//                            }
//                        }).start();

                    }
                });
        //2.创建一个群组
        //3.设置设置房主为选取的B
        //4.开始直播
        //5.直播将流地址发给B
        //6.B播放流
    }

    private void createRoom() {
        executeTask(new ThreadPoolManager.Task<LiveRoom>() {
            @Override
            public LiveRoom onRequest() throws HyphenateException {
                return LiveManager.getInstance().createLiveRoom(DemoApplication.getInstance().getCurrentUserName(), "", "");
            }

            @Override
            public void onSuccess(LiveRoom liveRoom) {
                DemoApplication.getInstance().setRoom(liveRoom);
                changeUserRoom(Integer.parseInt(liveRoom.getId()));
            }

            @Override
            public void onError(HyphenateException exception) {
                exception.printStackTrace();
                showToast("发起直播失败: " + exception.getMessage());
                dismissProgressDialog();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(this);
        EMClient.getInstance().contactManager().setContactListener(listener);
    }


    @Override
    protected void onPause() {
        super.onPause();
        EMClient.getInstance().chatManager().removeMessageListener(this);
        EMClient.getInstance().contactManager().removeContactListener(listener);
    }

    private void changeUserRoom(int id) {
        DemoUser user = Api.create(DemoUser.class);
        user.changeRoom(new ChangeRoomModule(DemoApplication.getInstance().getmUser().id, id))
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse<UserModule>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dismissProgressDialog();
                    }

                    @Override
                    public void onNext(BaseResponse<UserModule> userModuleBaseResponse) {
                        DemoApplication.getInstance().setmUser(userModuleBaseResponse.Data);
                        inviteToLiveChat(String.valueOf(userModuleBaseResponse.Data.roomid));
                        //等待B返回消息给我
                    }
                });
    }

    private void inviteToLiveChat(String roomId) {
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);

        //支持单聊和群聊，默认单聊，如果是群聊添加下面这行
//        cmdMsg.setChatType(ChatType.GroupChat)
        String action = MESSAGE.JION_ROOM + roomId;//action可以自定义
        EMCmdMessageBody cmdBody = new EMCmdMessageBody(action);
        String toUsername = DemoApplication.getInstance().getOther().name;//发送给某个人
        cmdMsg.setTo(toUsername);
        cmdMsg.addBody(cmdBody);
        EMClient.getInstance().chatManager().sendMessage(cmdMsg);
    }


    private void showNotActive() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("没有在线的B列表,请稍后再试")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        dialog.show();
    }

    @Override
    public void onMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {
        for (EMMessage ms :
                messages) {
            String action = ((EMCmdMessageBody) ms.getBody()).action();
            if (action.startsWith(MESSAGE.JION_ROOM_REPLAY)) {
                //b也加入了聊天室
                //让B成为管理员
                try {
                    EMChatRoom chatRoom = EMClient.getInstance().chatroomManager().addChatRoomAdmin(
                            DemoApplication.getInstance().getRoom().getChatroomId(),
                            DemoApplication.getInstance().getOther().name);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //进入直播节目
                            dismissProgressDialog();
                            Intent intent = new Intent(UserActivity.this, LiveAnchorActivity.class);
                            intent.putExtra("liveroom", DemoApplication.getInstance().getRoom());
                            startActivity(intent);
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> messages) {

    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    /***
     * 好友变化listener
     *
     */
    public class MyContactListener implements EMContactListener {

        @Override
        public void onContactAdded(String username) {
            //showToast(username + " added");
        }

        @Override
        public void onContactDeleted(String username) {
            //showToast(username + " deletet ");

        }

        @Override
        public void onContactInvited(String username, String reason) {
            //showToast(username + " 邀请");

        }

        @Override
        public void onFriendRequestAccepted(String username) {
            String CURREN = userToadd.toUpperCase();
            String ser = username.toUpperCase();
            if (!TextUtils.isEmpty(userToadd) && CURREN.equals(ser)) {
                userToadd = null;
                createRoom();
            }
        }

        @Override
        public void onFriendRequestDeclined(String username) {
            // your request was refused
            showToast(username + " refused to be your friend");
        }
    }

}
