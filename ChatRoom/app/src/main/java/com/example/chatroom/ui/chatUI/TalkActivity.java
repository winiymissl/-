package com.example.chatroom.ui.chatUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chatroom.R;
import com.example.chatroom.adapter.TalkBaseAdapter;
import com.example.chatroom.util.mMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class TalkActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lv;
    private Button btn_send;
    private ObjectOutputStream oos;
    private OutputStream os;
    private TalkBaseAdapter mAdapter;
    private int image;
    private String anInterface;
    private String ip;
    private String name;
    private EditText et_message;
    private Socket s;
    private int port;
    private List<mMessage> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        lv = findViewById(R.id.lv_chat);
        et_message = findViewById(R.id.et_message);
        //设置适配器
        list = mMessage.getListDefault();
        mAdapter = new TalkBaseAdapter(this, list);
        lv.setAdapter(mAdapter);
        //获取自身信息
        Bundle b = getIntent().getExtras();
        name = b.getString("nickname");
        ip = b.getString("IP");
        anInterface = b.getString("interface");
        image = b.getInt("image");
        port = Integer.valueOf(anInterface);
        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
        //配置网络信息
        new Thread(new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    InetAddress in = InetAddress.getByName(ip);
                    s = new Socket(in, port);
                    os = s.getOutputStream();
                    oos = new ObjectOutputStream(os);
                    //先接收信息
                    ClientReceive cr = new ClientReceive();
                    cr.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static final int UPDATE_TEXT = 1;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == UPDATE_TEXT) {
                //用于更新ui
                mAdapter.notifyDataSetChanged();
                lv.smoothScrollToPosition(list.size() - 1);
            }
        }
    };


    class ClientReceive extends Thread {
        @Override
        public void run() {
            super.run();
            try {

                while (true) {
                    //由于每次从服务端发送过来的信息需要更新，那边的输出流会更新，所以，这个流也需要更新
                    InputStream is = s.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(is);
                    List l = (List) ois.readObject();
                    mMessage m = (mMessage) l.get(0);
//                    Iterator it = l.iterator();
//                    mMessage m = (mMessage) it.next();
                    m.type = 0;
                    list.add(m);
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
                    Message mess = new Message();
                    mess.what = UPDATE_TEXT;
                    handler.sendMessage(mess);
//                        }
//                    }).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_send) {
            //往listView里面添加信息
            list.add(new mMessage(name, image, et_message.getText().toString(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), 1));
            List sendTo = new ArrayList();
            sendTo.add(new mMessage(name, image, et_message.getText().toString(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), 1));
            //给服务器发送消息
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        oos.writeObject(sendTo);
                        oos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            Log.d("wang", "给服务器发送消息");
            mAdapter.notifyDataSetChanged();
            lv.smoothScrollToPosition(list.size()-1);
            et_message.setText("");
        }
    }

}