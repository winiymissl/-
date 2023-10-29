package com.example.chatroom.ui.chooseAvatorUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chatroom.R;
import com.example.chatroom.ui.chatUI.TalkActivity;

public class ChooseAvatarActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv;
    private String name;
    private String ip;
    private String anInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_avator);
        ImageButton ib = findViewById(R.id.ib);
        ib.setOnClickListener(this);
        Bundle b = getIntent().getExtras();
        name = b.getString("nickname");
        ip = b.getString("IP");
        anInterface = b.getString("interface");
        tv = findViewById(R.id.tv_title);
        tv.setText(name + "用户,请您选择您的头像：");
        findViewById(R.id.iv_img4).setOnClickListener(this);
        findViewById(R.id.iv_img5).setOnClickListener(this);
        findViewById(R.id.iv_img6).setOnClickListener(this);
        findViewById(R.id.iv_img7).setOnClickListener(this);
        findViewById(R.id.iv_img8).setOnClickListener(this);
        findViewById(R.id.iv_img9).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib) {
            finish();
        } else if (v.getId() == R.id.iv_img4) {
            Intent in = new Intent(this, TalkActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("nickname", name);
            bundle.putString("IP", ip);
            bundle.putString("interface", anInterface);
            bundle.putInt("image", R.drawable.img_4);
            in.putExtras(bundle);
            startActivity(in);
        } else if (v.getId() == R.id.iv_img5) {
            Intent in = new Intent(this, TalkActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("nickname", name);
            bundle.putString("IP", ip);
            bundle.putString("interface", anInterface);
            bundle.putInt("image", R.drawable.img_5);
            in.putExtras(bundle);
            startActivity(in);
        } else if (v.getId() == R.id.iv_img6) {
            Intent in = new Intent(this, TalkActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("nickname", name);
            bundle.putString("IP", ip);
            bundle.putString("interface", anInterface);
            bundle.putInt("image", R.drawable.img_6);
            in.putExtras(bundle);
            startActivity(in);
        } else if (v.getId() == R.id.iv_img7) {
            Intent in = new Intent(this, TalkActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("nickname", name);
            bundle.putString("IP", ip);
            bundle.putString("interface", anInterface);
            bundle.putInt("image", R.drawable.img_7);
            in.putExtras(bundle);
            startActivity(in);
        } else if (v.getId() == R.id.iv_img8) {
            Intent in = new Intent(this, TalkActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("nickname", name);
            bundle.putString("IP", ip);
            bundle.putString("interface", anInterface);
            bundle.putInt("image", R.drawable.img_8);
            in.putExtras(bundle);
            startActivity(in);
        } else if (v.getId() == R.id.iv_img9) {
            Intent in = new Intent(this, TalkActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("nickname", name);
            bundle.putString("IP", ip);
            bundle.putString("interface", anInterface);
            bundle.putInt("image", R.drawable.img_9);
            in.putExtras(bundle);
            startActivity(in);
        }
    }
}