package com.easemob.livedemo.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseEmojicon;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.EaseChatInputMenu;
import com.hyphenate.easeui.widget.EaseChatMessageList;
import com.hyphenate.easeui.widget.EaseVoiceRecorderView;

import static com.hyphenate.easeui.EaseConstant.CHATTYPE_CHATROOM;
import static com.hyphenate.easeui.EaseConstant.CHATTYPE_GROUP;
import static com.hyphenate.easeui.EaseConstant.EXTRA_CHAT_TYPE;

/**
 * Created by wei on 2016/6/3.
 */
public class RoomMessagesView extends RelativeLayout {

    private EaseChatFragment chatFragment;

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
    }


    public void init(FragmentActivity activity,String chatroomId) {
        chatFragment = new EaseChatFragment();
        Bundle data = new Bundle();
        data.putString(EaseConstant.EXTRA_USER_ID,chatroomId);
        data.putInt(EXTRA_CHAT_TYPE,CHATTYPE_CHATROOM);
        chatFragment.setArguments(data);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.message_list_container, chatFragment).commit();
    }

    public void refresh() {
        chatFragment.refresh();
    }

    public void refreshSelectLast() {
        chatFragment.refreshLast();
    }
}
