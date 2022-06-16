package com.example.moa_ex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class login_senior extends AppCompatActivity {

    Button btn_start, btn_join;
    EditText et_id;
    RequestQueue queue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_senior);

        et_id = findViewById(R.id.et_Sname);
        btn_start = findViewById(R.id.btn_start);

        btn_join = findViewById(R.id.btn_join);
        queue = Volley.newRequestQueue(login_senior.this);

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login_senior.this, join_senior_name.class);
                startActivity(i);

            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_id.getText().toString().equals("")){
                    Toast.makeText(login_senior.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    user_login();
                }
            }
        });

    };
    public void user_login(){
        int method = Request.Method.POST;
        String server_url = "http://172.30.1.41:3000/home/user_login";

        request = new StringRequest(
                method,
                server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("login_senior","받은데이터 >> "+response);
                        if (response.toString().equals("로그인성공")) {
                            Intent i = new Intent(login_senior.this, userMainActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "사용자 로그인에 실패하였습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();

                String s_id = et_id.getText().toString();

                param.put("S_ID",s_id);

                return param;
            }
        };
        queue.add(request);
    }


};