package com.easemob.livedemo.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.livedemo.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.domain.EaseEmojicon;
import com.hyphenate.easeui.widget.EaseChatInputMenu;
import com.hyphenate.easeui.widget.EaseChatMessageList;
import com.hyphenate.easeui.widget.EaseVoiceRecorderView;

/**
 * Created by wei on 2016/6/3.
 */
public class RoomMessagesView extends RelativeLayout {

    public boolean isBarrageShow = false;
    private EaseChatMessageList messageList;
    private EaseChatInputMenu inputMenu;
    private EaseVoiceRecorderView voiceRecorderView;


    public RoomMessagesView(Context context) {
        super(context);
        init(context, null);
    }

    public RoomMessagesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoomMessagesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.widget_room_messages, this);
        messageList = (EaseChatMessageList) view.findViewById(R.id.message_list);
        inputMenu = (EaseChatInputMenu) view.findViewById(R.id.input_menu);
        voiceRecorderView = (EaseVoiceRecorderView) view.findViewById(R.id.voice_recorder);
    }

    public EditText getInputView() {
        return inputMenu.getPrimaryMenu().getEditText();
    }

    public void init(String chatroomId) {
        messageList.init(chatroomId, EMConversation.EMConversationType.GroupChat.ordinal(),null);
        messageList.setItemClickListener(new EaseChatMessageList.MessageListItemClickListener() {
            @Override
            public void onResendClick(EMMessage message) {

            }

            @Override
            public boolean onBubbleClick(EMMessage message) {
                if(messageList!=null) {
                    messageViewListener.onItemClickListener(message);
                }
                return true;
            }

            @Override
            public void onBubbleLongClick(EMMessage message) {

            }

            @Override
            public void onUserAvatarClick(String username) {

            }

            @Override
            public void onUserAvatarLongClick(String username) {

            }
        });
        inputMenu.init();
        //设置相关事件监听
        inputMenu.setChatInputMenuListener(new EaseChatInputMenu.ChatInputMenuListener() {

            @Override
            public void onSendMessage(String content) {
                // 发送文本消息
                if(messageViewListener!=null) {
                    messageViewListener.onMessageSend(content);
                }
            }

            @Override
            public boolean onPressToSpeakBtnTouch(View v, MotionEvent event) {
                ////把touch事件传入到EaseVoiceRecorderView 里进行录音
                return voiceRecorderView.onPressToSpeakBtnTouch(v, event, new EaseVoiceRecorderView.EaseVoiceRecorderCallback() {
                    @Override
                    public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
                        // 发送语音消息
                        if(messageViewListener!=null) {
                            messageViewListener.onVoiceMessageSend(voiceFilePath,voiceTimeLength);
                        }
                    }
                });
            }


            @Override
            public void onBigExpressionClicked(EaseEmojicon emojicon) {

            }

        });

    }

    private MessageViewListener messageViewListener;

    public interface MessageViewListener {
        void onMessageSend(String content);

        void onVoiceMessageSend(String filePath,int length);
        void onItemClickListener(EMMessage message);

        void onHiderBottomBar();
    }

    public void setMessageViewListener(MessageViewListener messageViewListener) {
        this.messageViewListener = messageViewListener;
    }

    public void refresh() {
        messageList.refresh();
    }

    public void refreshSelectLast() {
        messageList.refreshSelectLast();
    }


}
