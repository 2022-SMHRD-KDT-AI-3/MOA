package com.example.moa_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class userMainActivity extends AppCompatActivity {

    Button btn_addFamily, btn_startTalk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        btn_addFamily = findViewById(R.id.btn_addFamily);
        btn_startTalk = findViewById(R.id.btn_startTalk);
    }

    public void onClickButton1(View v){
        Intent i = new Intent(this, join_senior_family.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); // 액티비티 스택에 쌓이지 않음
        startActivity( i );
    }

    public void onClickButton2(View v){
        Intent i = new Intent(this, talk.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); // 액티비티 스택에 쌓이지 않음
        startActivity( i );
    }
}