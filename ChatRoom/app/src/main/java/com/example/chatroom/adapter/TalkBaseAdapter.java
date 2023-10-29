package com.example.chatroom.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.chatroom.R;
import com.example.chatroom.util.mMessage;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TalkBaseAdapter extends BaseAdapter {
    private Context context;
    private List l;
    public static final int MESSAGE_MY = 1;
    public static final int MESSAGE_OTHER = 0;

    public TalkBaseAdapter(Context context, List<mMessage> list) {
        this.context = context;
        this.l = list;
    }

    @Override
    public int getCount() {
        return l.size();
    }

    @Override
    public Object getItem(int position) {
        return l.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mMessage m = (mMessage) l.get(position);
        View view = null;
        ImageView iv_avatar = null;
        TextView tv_nickname = null;
        TextView tv_mess = null;
        TextView tv_time = null;

        if (m.getType() == MESSAGE_MY) {
            view = LayoutInflater.from(context).inflate(R.layout.item_message_mine, null);
            iv_avatar = view.findViewById(R.id.iv_avatar);
            tv_nickname = view.findViewById(R.id.tv_nickname);
            tv_mess = view.findViewById(R.id.tv_mess);
            tv_time = view.findViewById(R.id.tv_time);
        } else if (m.getType() == MESSAGE_OTHER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_message_others, null);
            iv_avatar = view.findViewById(R.id.iv_avatar);
            tv_nickname = view.findViewById(R.id.tv_nickname);
            tv_mess = view.findViewById(R.id.tv_mess);
            tv_time = view.findViewById(R.id.tv_time);
        }
        //设置细节，一个条目的细节
        iv_avatar.setImageResource(m.getImage());
        tv_nickname.setText(m.getNickname());
        tv_mess.setText(m.getMessage());
        tv_time.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        return view;
    }
}

