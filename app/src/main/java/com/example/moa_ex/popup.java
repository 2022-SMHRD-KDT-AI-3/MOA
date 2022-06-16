package com.example.moa_ex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class popup extends AppCompatActivity {

    RequestQueue queue;
    StringRequest stringRequest_matching;

    Button btn_add, btn_cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        btn_add = findViewById(R.id.btn_add);
        btn_cancle = findViewById(R.id.btn_cancle);
    }

    public void AddEvent(View view){
        // 추가버튼을 누르면 DB의 관리자별 보호자 테이블에 값을 저장

        matching_sRequestPost();

        queue.add(stringRequest_matching);
        Intent i = new Intent(popup.this, userSearch.class);
        startActivity(i);

    }

    public void CancleEvent(View view){
        finish();
    }

    public void matching_sRequestPost() {

        int method = Request.Method.POST;
        String server_url = "http://172.30.1.42:3000/home/matching";

        stringRequest_matching = new StringRequest(
                method,
                server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NodeConnActivity", "응답받은 데이터: " + response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", "오류발생>>" + error.toString());
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();

                Intent i = getIntent();
                String search_name = i.getStringExtra("search_name");

                param.put("search_name",search_name);

                return param;

            }
        };

        queue.add(stringRequest_matching);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        // 바깥레이어 클릭 시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        // 안드로이드 백버튼 막기
        return;
    }



}