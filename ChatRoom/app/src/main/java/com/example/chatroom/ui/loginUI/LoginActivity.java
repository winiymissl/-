package com.example.chatroom.ui.loginUI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chatroom.R;
import com.example.chatroom.ui.chooseAvatorUI.ChooseAvatarActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private CheckBox cb;
    private EditText et_interface;
    private EditText et_ip;
    private EditText et_nickname;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private SharedPreferences s;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_connect = findViewById(R.id.btn_connect);
        btn_connect.setOnClickListener(this);
        findViewById(R.id.btn_quit).setOnClickListener(this);
        cb = findViewById(R.id.cb_remember);
        cb.setOnCheckedChangeListener(this);
        et_interface = findViewById(R.id.et_interface);
        et_ip = findViewById(R.id.et_IP);
        et_nickname = findViewById(R.id.et_nickname);
        init();
    }

    private void init() {
        s = getSharedPreferences("info", Context.MODE_PRIVATE);
        boolean is = s.getBoolean("isChecked", true);
        if (is) {
            String name = s.getString("nickname", null);
            if (name != null) {
                et_nickname.setText(name);
            }
            String ip = s.getString("IP", null);
            if (ip != null) {
                et_ip.setText(ip);
            }
            int anInterface = s.getInt("anInterface", 0);
            if (anInterface != 0) {
                et_interface.setText(String.valueOf(anInterface));
            }
            cb.setChecked(true);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_connect) {
            try {
                Intent in = new Intent(this, ChooseAvatarActivity.class);
                Bundle b = new Bundle();
                b.putString("nickname", et_nickname.getText().toString());
                b.putString("IP", et_ip.getText().toString());
                b.putString("interface", et_interface.getText().toString());
                in.putExtras(b);
                startActivity(in);
            } catch (Exception e) {
                Log.d("wang", "onClick有问题");
                Toast.makeText(this, "onClick有问题", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btn_quit) {
            AlertDialog.Builder quit = new AlertDialog.Builder(this);
            quit.setTitle("尊敬的用户");
            quit.setMessage("你要退出吗?");
            quit.setPositiveButton("确定，我要退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (isEmpty()) {
                        finish();
                    } else {
                        isSaveInfo(cb.isChecked());
                        finish();
                    }
                }
            });
            quit.setNegativeButton("取消，点错了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog dialog = quit.create();
            dialog.show();
        }
    }

    private boolean isEmpty() {
        if (et_ip.getText().toString().equals("") || et_nickname.getText().toString().equals("") || et_interface.getText().toString().equals("")) {
            Toast.makeText(this, "请输入信息", Toast.LENGTH_SHORT).show();
            cb.setChecked(false);
            return true;
        }
        return false;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            isSaveInfo(true);
        }
    }

    private void isSaveInfo(boolean isChecked) {
        preferences = getSharedPreferences("info", Context.MODE_PRIVATE);
        editor = preferences.edit();
        if (isChecked) {
            try {
//                Log.d("wang", "获取数据没有问题");
                editor.putString("nickname", et_nickname.getText().toString());
                String s1 = et_ip.getText().toString();
                editor.putString("IP", et_ip.getText().toString());
                String s = et_interface.getText().toString();

                int i = Integer.parseInt(s);
                //转化为基本数据类型，而不是一个对象
                editor.putInt("anInterface", i);
                editor.putBoolean("isChecked", true);
                editor.apply();
            } catch (NumberFormatException e) {
                e.printStackTrace();
//                Toast.makeText(this, "产生报错", Toast.LENGTH_SHORT).show();
                cb.setChecked(false);
            }
        } else {
            editor.putString("nickname", "");
            editor.putString("IP", "");
            //转化为基本数据类型，而不是一个对象
            editor.putInt("anInterface", 0);
            editor.putBoolean("isChecked", false);
            editor.apply();
        }
    }
}