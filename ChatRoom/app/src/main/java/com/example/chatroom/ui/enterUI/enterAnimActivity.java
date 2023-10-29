package com.example.chatroom.ui.enterUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chatroom.R;
import com.example.chatroom.ui.loginUI.LoginActivity;

public class enterAnimActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_anim);
        Button btn = findViewById(R.id.btn_enter);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent in = new Intent(this, LoginActivity.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(in);
    }
}