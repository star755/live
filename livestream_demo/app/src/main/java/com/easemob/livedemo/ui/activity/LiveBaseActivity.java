package com.easemob.livedemo.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.bumptech.glide.Glide;
import com.easemob.livedemo.DemoApplication;
import com.easemob.livedemo.DemoConstants;
import com.easemob.livedemo.R;
import com.easemob.livedemo.ThreadPoolManager;
import com.easemob.livedemo.custom.M;
import com.easemob.livedemo.data.TestAvatarRepository;
import com.easemob.livedemo.data.model.LiveRoom;
import com.easemob.livedemo.data.model.User;
import com.easemob.livedemo.data.restapi.LiveException;
import com.easemob.livedemo.data.restapi.LiveManager;
import com.easemob.livedemo.net.Api;
import com.easemob.livedemo.net.bean.UserModule;
import com.easemob.livedemo.net.response.BaseResponse;
import com.easemob.livedemo.net.service.DemoUser;
import com.easemob.livedemo.ui.widget.PeriscopeLayout;
import com.easemob.livedemo.ui.widget.RoomMessagesView;
import com.hyphenate.EMChatRoomChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.easemob.livedemo.custom.M.TO_GROUP;

/**
 * Created by wei on 2016/6/12.
 */
public abstract class LiveBaseActivity extends BaseActivity {
    protected static final String TAG = "LiveActivity";

    //@BindView(R.id.left_gift_view1) LiveLeftGiftView leftGiftView;
    //@BindView(R.id.left_gift_view2) LiveLeftGiftView leftGiftView2;
    @BindView(R.id.message_view)
    RoomMessagesView messageView;
    @BindView(R.id.periscope_layout)
    PeriscopeLayout periscopeLayout;
    @BindView(R.id.bottom_bar)
    View bottomBar;

    //@BindView(R.id.barrage_layout) BarrageLayout barrageLayout;
    @BindView(R.id.horizontal_recycle_view)
    RecyclerView horizontalRecyclerView;
    @BindView(R.id.audience_num)
    TextView audienceNumView;
    //@BindView(R.id.new_messages_warn) ImageView newMsgNotifyImage;

    @BindView(R.id.user_manager_image)
    ImageView userManagerView;
    @BindView(R.id.switch_camera_image)
    ImageView switchCameraView;
    @BindView(R.id.like_image)
    ImageView likeImageView;
    @BindView(R.id.txt_live_id)
    TextView liveIdView;
    @BindView(R.id.tv_username)
    TextView usernameView;

    protected String anchorId;

    protected LiveRoom liveRoom;

    protected int watchedCount;
    protected int membersCount;

    protected String chatroomId;
    /**
     * ucloud直播id
     */
    protected String liveId = "";
    protected boolean isMessageListInited;
    protected EMChatRoomChangeListener chatRoomChangeListener;

    //volatile boolean isGiftShowing = false;
    //volatile boolean isGift2Showing = false;
    //List<String> toShowList = Collections.synchronizedList(new LinkedList<String>());

    protected EMChatRoom chatroom;
    private static final int MAX_SIZE = 10;
    LinkedList<String> memberList = new LinkedList<>();
    public UserModule user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(false);
        }
        user= DemoApplication.getInstance().getmUser();
        liveRoom = (LiveRoom) getIntent().getSerializableExtra("liveroom");
        onActivityCreate(savedInstanceState);
        initRoom(liveRoom, savedInstanceState);
    }

    private void initRoom(LiveRoom liveRoom, Bundle savedInstanceState) {
        if (liveRoom == null) {
            return;
        }
        liveId = liveRoom.getId();
        chatroomId = liveRoom.getChatroomId();
        anchorId = liveRoom.getAnchorId();
        usernameView.setText(anchorId);
        liveIdView.setText(liveId);
        audienceNumView.setText(String.valueOf(liveRoom.getAudienceNum()));
        watchedCount = liveRoom.getAudienceNum();
    }

    protected Handler handler = new Handler();

    protected abstract void onActivityCreate(@Nullable Bundle savedInstanceState);

    protected abstract void onVideoOK();

    protected void showPraise(final int count) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    if (!isFinishing())
                        periscopeLayout.addHeart();
                }
            }
        });

    }


    protected void addChatRoomChangeListener() {
        chatRoomChangeListener = new EMChatRoomChangeListener() {

            @Override
            public void onChatRoomDestroyed(String roomId, String roomName) {
                if (roomId.equals(chatroomId)) {
                    finish();
                }
            }

            @Override
            public void onMemberJoined(String roomId, String participant) {
                onRoomMemberAdded(participant);
            }

            @Override
            public void onMemberExited(String roomId, String roomName, String participant) {
                //                showChatroomToast("member : " + participant + " leave the room : " + roomId + " room name : " + roomName);
                onRoomMemberExited(participant);
            }

            @Override
            public void onRemovedFromChatRoom(String roomId, String roomName, String participant) {
                if (roomId.equals(chatroomId)) {
                    String curUser = EMClient.getInstance().getCurrentUser();
                    if (curUser.equals(participant)) {
                        EMClient.getInstance().chatroomManager().leaveChatRoom(roomId);
                        //postUserChangeEvent(StatisticsType.LEAVE, curUser);
                        showToast("你已被移除出此房间");
                        finish();
                    } else {
                        //                        showChatroomToast("member : " + participant + " was kicked from the room : " + roomId + " room name : " + roomName);
                        onRoomMemberExited(participant);
                    }
                }
            }

            @Override
            public void onMuteListAdded(String chatRoomId, List<String> mutes, long expireTime) {
                for (String name : mutes) {
                    showMemberChangeEvent(name, "被禁言");
                }
            }

            @Override
            public void onMuteListRemoved(String chatRoomId, List<String> mutes) {
                for (String name : mutes) {
                    showMemberChangeEvent(name, "被解除禁言");
                }
            }

            @Override
            public void onAdminAdded(String chatRoomId, String admin) {
                if (admin.equals(EMClient.getInstance().getCurrentUser())) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            userManagerView.setVisibility(View.VISIBLE);
                        }
                    });
                }
                showMemberChangeEvent(admin, "被提升为房管");
            }

            @Override
            public void onAdminRemoved(String chatRoomId, String admin) {
                if (admin.equals(EMClient.getInstance().getCurrentUser())) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            userManagerView.setVisibility(View.INVISIBLE);
                        }
                    });
                }
                showMemberChangeEvent(admin, "被解除房管");
            }

            @Override
            public void onOwnerChanged(String chatRoomId, String newOwner, String oldOwner) {

            }
        };

        EMClient.getInstance().chatroomManager().addChatRoomChangeListener(chatRoomChangeListener);
    }

    private void showMemberChangeEvent(String username, String event) {
        EMMessage message = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
        message.setTo(chatroomId);
        message.setFrom(username);
        EMTextMessageBody textMessageBody = new EMTextMessageBody(event);
        message.addBody(textMessageBody);
        message.setChatType(EMMessage.ChatType.ChatRoom);
        EMClient.getInstance().chatManager().saveMessage(message);
        messageView.refreshSelectLast();
    }


    private EMChatRoom room;


    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {

            for (EMMessage message : messages) {
                String username = null;
                // 群组消息
                if (message.getChatType() == EMMessage.ChatType.GroupChat
                        || message.getChatType() == EMMessage.ChatType.ChatRoom) {
                    username = message.getTo();
                } else {
                    // 单聊消息
                    username = message.getFrom();
                }
                // 如果是当前会话的消息，刷新聊天页面
                if (username.equals(chatroomId)) {
                    //if (message.getBooleanAttribute(DemoConstants.EXTRA_IS_BARRAGE_MSG, false)) {
                    //    barrageLayout.addBarrage(
                    //            ((EMTextMessageBody) message.getBody()).getMessage(),
                    //            message.getFrom());
                    //}
                    messageView.refreshSelectLast();
                } else {
                    //if(message.getChatType() == EMMessage.ChatType.Chat && message.getTo().equals(EMClient.getInstance().getCurrentUser())){
                    //  runOnUiThread(new Runnable() {
                    //    @Override public void run() {
                    //      newMsgNotifyImage.setVisibility(View.VISIBLE);
                    //    }
                    //  });
                    //}
                    //// 如果消息不是和当前聊天ID的消息
                    //EaseUI.getInstance().getNotifier().onNewMsg(message);
                }
            }
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            for (EMMessage message : messages) {
                String action = ((EMCmdMessageBody) message.getBody()).action();
                Log.v("asdf", "recieve message" + action);
                if (DemoConstants.CMD_GIFT.equals(action)) {
                    //showLeftGiftView(message.getFrom());
                } else if (DemoConstants.CMD_PRAISE.equals(action)) {
                    showPraise(message.getIntAttribute(DemoConstants.EXTRA_PRAISE_COUNT, 1));
                } else if (action.startsWith(M.JION_ROOM)) {
                    M.JoinRoom room = M.parseInviteUser(action);
                    if (!room.toUser.equalsIgnoreCase(DemoApplication.getInstance().getCurrentUserName())) {
                        return;
                    }
                    chatroomId = room.roomId;
                    String fromUser = message.getFrom();
                    DemoApplication.getInstance().setOtherUserName(fromUser);
                    bisBusy(true);
                    sendokMsg();
                    startChat();
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
            if (isMessageListInited) {
                messageView.refresh();
            }
        }
    };

    abstract void startChat();

    protected void onMessageListInit() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageView.init(LiveBaseActivity.this, chatroomId);
                messageView.setVisibility(View.VISIBLE);
                //bottomBar.setVisibility(View.VISIBLE);
                if (!chatroom.getAdminList().contains(EMClient.getInstance().getCurrentUser())
                        && !chatroom.getOwner().equals(EMClient.getInstance().getCurrentUser())) {
                    userManagerView.setVisibility(View.INVISIBLE);
                }
                isMessageListInited = true;
                updateUnreadMsgView();
                showMemberList();
            }
        });
    }

    protected void updateUnreadMsgView() {
        //if(isMessageListInited) {
        //  for (EMConversation conversation : EMClient.getInstance()
        //      .chatManager()
        //      .getAllConversations()
        //      .values()) {
        //    if (conversation.getType() == EMConversation.EMConversationType.Chat
        //        && conversation.getUnreadMsgCount() > 0) {
        //      newMsgNotifyImage.setVisibility(View.VISIBLE);
        //      return;
        //    }
        //  }
        //  newMsgNotifyImage.setVisibility(View.INVISIBLE);
        //}
    }

    private void showUserDetailsDialog(String username) {
        RoomUserDetailsDialog dialog = RoomUserDetailsDialog.newInstance(username, liveRoom);
        dialog.setManageEventListener(new RoomUserDetailsDialog.RoomManageEventListener() {
            @Override
            public void onKickMember(String username) {
                onRoomMemberExited(username);
            }

            @Override
            public void onAddBlacklist(String username) {
                onRoomMemberExited(username);
            }
        });
        dialog.show(getSupportFragmentManager(), "RoomUserDetailsDialog");
    }

    private void showInputView() {
        //bottomBar.setVisibility(View.INVISIBLE);
    }


    private void sendokMsg() {
        Log.v("asdf", "send to a confirm");
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);
        cmdMsg.setChatType(EMMessage.ChatType.GroupChat);
        String toUsername = DemoApplication.getInstance().getOtherUserName();
        EMCmdMessageBody cmdBody = new EMCmdMessageBody(M.replayInviteUser(toUsername));
        cmdMsg.setTo(M.TO_GROUP);
        cmdMsg.addBody(cmdBody);
        EMClient.getInstance().chatManager().sendMessage(cmdMsg);
        //startPlay();
    }

    private void startPlay() {
        initRoom(liveRoom, null);
    }

    protected void bisBusy(boolean value) {
        UserModule b = DemoApplication.getInstance().getmUser();
        if (value) {
            b.Flag = UserModule.STATUS_BUSY;
            b.roomid = chatroomId;
        } else {
            b.Flag = UserModule.STATUS_ONLINE;
            b.roomid = "";
        }
        final DemoUser user = Api.create(DemoUser.class);
        user.update(b)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
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
                        DemoApplication.getInstance().setmUser((UserModule) userModuleBaseResponse.Data);
                    }
                });
    }


    private LinearLayoutManager layoutManager;

    void showMemberList() {
        layoutManager = new LinearLayoutManager(LiveBaseActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(layoutManager);
        horizontalRecyclerView.setAdapter(new AvatarAdapter(LiveBaseActivity.this, memberList));
        executeTask(new ThreadPoolManager.Task<Void>() {
            @Override
            public Void onRequest() throws HyphenateException {
                try {
                    chatroom = EMClient.getInstance()
                            .chatroomManager()
                            .fetchChatRoomFromServer(chatroomId, true);
                    memberList.clear();
                    List<String> tempList = new ArrayList<>();
                    tempList.addAll(chatroom.getAdminList());
                    tempList.addAll(chatroom.getMemberList());
                    if (tempList.contains(chatroom.getOwner())) {
                        tempList.remove(chatroom.getOwner());
                    }
                    if (tempList.size() > MAX_SIZE) {
                        for (int i = 0; i < MAX_SIZE; i++) {
                            memberList.add(i, tempList.get(i));
                        }
                    }
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void onSuccess(Void aVoid) {
                int size = chatroom.getMemberCount();
                audienceNumView.setText(String.valueOf(size));
                membersCount = size;
                //观看人数不包含主播
                watchedCount = membersCount - 1;
                notifyDataSetChanged();
            }

            @Override
            public void onError(HyphenateException exception) {

            }
        });
    }

    private synchronized void onRoomMemberAdded(String name) {
        watchedCount++;
        if (!memberList.contains(name)) {
            membersCount++;
            if (memberList.size() >= MAX_SIZE)
                memberList.removeLast();
            memberList.addFirst(name);
            showMemberChangeEvent(name, "来了");
            EMLog.d(TAG, name + "added");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    audienceNumView.setText(String.valueOf(membersCount));
                    notifyDataSetChanged();
                }
            });
        }

    }

    private void notifyDataSetChanged() {
        if (memberList.size() > 4) {
            layoutManager.setStackFromEnd(false);
        } else {
            layoutManager.setStackFromEnd(true);
        }
        horizontalRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private synchronized void onRoomMemberExited(final String name) {
        memberList.remove(name);
        membersCount--;
        EMLog.e(TAG, name + "exited");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                audienceNumView.setText(String.valueOf(membersCount));
                horizontalRecyclerView.getAdapter().notifyDataSetChanged();
                if (name.equals(anchorId)) {
                    showLongToast("主播已结束直播");
                }
            }
        });
    }


    //@OnClick(R.id.root_layout) void onRootLayoutClick() {
    //    periscopeLayout.addHeart();
    //}

    @OnClick(R.id.comment_image)
    void onCommentImageClick() {
        showInputView();
    }

    @OnClick(R.id.user_manager_image)
    void showUserList() {
        RoomUserManagementDialog managementDialog = new RoomUserManagementDialog(chatroomId);
        managementDialog.show(getSupportFragmentManager(), "RoomUserManagementDialog");
    }

    //@OnClick(R.id.present_image) void onPresentImageClick() {
    //  EMMessage message = EMMessage.createSendMessage(EMMessage.Type.CMD);
    //  message.setTo(chatroomId);
    //  EMCmdMessageBody cmdMessageBody = new EMCmdMessageBody(DemoConstants.CMD_GIFT);
    //  message.addBody(cmdMessageBody);
    //  message.setChatType(EMMessage.ChatType.ChatRoom);
    //  EMClient.getInstance().chatManager().sendMessage(message);
    //  showLeftGiftView(EMClient.getInstance().getCurrentUser());
    //}

    //@OnClick(R.id.chat_image) void onChatImageClick() {
    //  ConversationListFragment fragment = ConversationListFragment.newInstance(anchorId, false);
    //  getSupportFragmentManager().beginTransaction()
    //      .replace(R.id.message_container, fragment)
    //      .commit();
    //
    //}

    @Override
    protected void onResume() {
        super.onResume();
    }

    private class AvatarAdapter extends RecyclerView.Adapter<AvatarViewHolder> {
        List<String> namelist;
        Context context;
        TestAvatarRepository avatarRepository;

        public AvatarAdapter(Context context, List<String> namelist) {
            this.namelist = namelist;
            this.context = context;
            avatarRepository = new TestAvatarRepository();
        }

        @Override
        public AvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new AvatarViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.avatar_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(AvatarViewHolder holder, final int position) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showUserDetailsDialog(namelist.get(position));
                }
            });
            //暂时使用测试数据
            Glide.with(context)
                    .load(avatarRepository.getAvatar())
                    .placeholder(R.drawable.ease_default_avatar)
                    .into(holder.Avatar);
        }

        @Override
        public int getItemCount() {
            return namelist.size();
        }
    }

    static class AvatarViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        ImageView Avatar;

        public AvatarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
