package com.easemob.livedemo.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easemob.livedemo.R;
import com.easemob.livedemo.net.bean.UserModule;
import com.easemob.livedemo.ui.activity.UserActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.easemob.livedemo.net.bean.UserModule.STATUS_BUSY;
import static com.easemob.livedemo.net.bean.UserModule.STATUS_OFFLINE;
import static com.easemob.livedemo.net.bean.UserModule.STATUS_ONLINE;
import static com.easemob.livedemo.net.bean.UserModule.STATUS_VIEW;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private List<UserModule> users;
    private Context context;

    public UserAdapter(List<UserModule> list, Context context) {
        users = list;
        this.context = context;
    }


    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserHolder(LayoutInflater.from(context).inflate(R.layout.adapter_users, parent, false));
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        String user = users.get(position).name;
        String status = "";
        if (users.get(position).status.equals(STATUS_BUSY)) {
            status = "(忙碌)";
        } else if (users.get(position).status.equals(STATUS_ONLINE)) {
            status = "(在线)";
        } else if (users.get(position).status.equals(STATUS_OFFLINE)) {
            status = "(离线)";
        } else if (users.get(position).status.equals(STATUS_VIEW)) {
            status = "(观察)";
        }
        holder.tvName.setText(user + status);
        holder.tvInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_invite)
        TextView tvInvite;
        @BindView(R.id.tv_name)
        TextView tvName;

        public UserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
