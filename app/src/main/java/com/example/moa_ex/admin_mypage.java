package com.example.moa_ex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class admin_mypage extends AppCompatActivity {

    RequestQueue queue;
    StringRequest stringRequest;

    ListView userList;
    search_adapter adapter;
    ArrayList<user_data> list;

    Button btn_previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mypage);

        btn_previous = findViewById(R.id.btn_previous);

        queue = Volley.newRequestQueue(admin_mypage.this);

        userList = findViewById(R.id.userList);

        list = new ArrayList<>();

        adapter = new search_adapter("",admin_mypage.this, R.layout.search_layout_item, list);

        userList.setAdapter(adapter);
//        matching_sRequestPost();
    }

//    public void matching_sRequestPost() {
//
//        int method = Request.Method.POST;
//        String server_url = "http://172.30.1.42:3000/home/userSearch";
//
//        stringRequest = new StringRequest(
//                method,
//                server_url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("NodeConnActivity", "응답받은 데이터: " + response);
//
//                        list.add(new user_data(R.drawable.member, "박준형", "950518-1552020", "010"));
//                        list.add(new user_data(R.drawable.member, "박준형", "950518-1552020", "010"));
//                        list.add(new user_data(R.drawable.member, "박준형", "950518-1552020", "010"));
//                        list.add(new user_data(R.drawable.member, "박준형", "950518-1552020", "010"));
//                        list.add(new user_data(R.drawable.member, "박준형", "950518-1552020", "010"));
//
//                        adapter.notifyDataSetChanged();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("Volley Error", "오류발생>>" + error.toString());
//                    }
//                }
//        ) {
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> param = new HashMap<>();
//
//                String search_name = et_Sname.getText().toString();
//                Intent i = new Intent(admin_mypage.this, popup.class);
//                i.putExtra("search_name", search_name);
//                param.put("search_name", search_name);
//
//                return param;
//            }
//        };
//
//        queue.add(stringRequest);
//    }
}