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

import java.util.HashMap;
import java.util.Map;

public class login_admin extends AppCompatActivity {

    RequestQueue queue;
    StringRequest stringRequest_login;

    EditText et_id, et_pw;
    Button btn_start, btn_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        queue = Volley.newRequestQueue(login_admin.this);

        et_id = findViewById(R.id.et_Sname);
        et_pw = findViewById(R.id.et_pw);
        btn_start = findViewById(R.id.btn_start);
        btn_join = findViewById(R.id.btn_join);

        // 회원가입 버튼 클릭시
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login_admin.this, join_admin.class);
                startActivity(i);

            }
        });

        // 시작하기 버튼 클릭시
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 메소드 호출
                login_adminRequestPost();
            }
        });


    }

    public void login_adminRequestPost(){
        int method = Request.Method.POST;
        String login_id = et_id.getText().toString();
        String login_pw = et_pw.getText().toString();
        String server_url = "http://172.30.1.41:3000/home/admin_login";

        stringRequest_login = new StringRequest(
                method,
                server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("login_senior","응답받은 데이터 >>"+response);
                        if (response.toString().equals("성공")){
                            Intent i =  new Intent(login_admin.this, userSearch.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); // 액티비티 스택에 쌓이지 않음
                            startActivity( i );
                        }else{
                            Toast.makeText(login_admin.this,"관리자 로그인에 실패했습니다",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley Error","오류발생 >> "+error.toString());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("a_id",login_id);
                param.put("a_pw",login_pw);

                return param;
            }
        };

        queue.add(stringRequest_login);

    }

//    public void onResponse(String response) {
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            boolean success = jsonObject.getBoolean("success");
//
//            if (success) {//로그인 성공시
//
//                String admin_id = jsonObject.getString("admin_id");
//                String pw = jsonObject.getString("pw");
//
//                Toast.makeText(getApplicationContext(), String.format("%s님 환영합니다.", admin_id), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(login_admin.this, search.class);
//                startActivity(intent);
//
//                intent.putExtra("UserId", admin_id);
//                intent.putExtra("UserPw", pw);
//
//                startActivity(intent);
//
//            } else {//로그인 실패시
//                Toast.makeText(getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

//    public void onButtonLogin(View v){
//        String admin_id = et_id.getText().toString();
//        String pw = et_pw.getText().toString();
//
//        Myapplication.isLogin = true;
//        Myapplication.admin_id = admin_id;
//        Myapplication.pw = pw;
//
//        setContentView(R.layout.activity_user_search);
//
//    }



//    public void onClickJoin(View v) {
//        //다른 화면(액티비티)로 이동
//        //자신 액티비티 객체, 이동할 액티비티 클래스
//        Intent i = new Intent(this, join_admin.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); // 액티비티 스택에 쌓이지 않음
//        startActivity( i );
//    }

}