package com.example.moa_ex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class userSearch extends AppCompatActivity {

    RequestQueue queue;
    StringRequest stringRequest_search;


    ListView userList;
    search_adapter adapter;
    ArrayList<user_data> list;

    Button btn_search;
    Button btn_useradd;
    EditText et_Sname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersearch);

        et_Sname = findViewById(R.id.et_Sname);

        btn_search = findViewById(R.id.btn_search);
        btn_useradd = findViewById(R.id.btn_add);

        queue = Volley.newRequestQueue(userSearch.this);

        userList = findViewById(R.id.userList);

        list = new ArrayList<>();

        adapter = new search_adapter("add",userSearch.this, R.layout.search_layout2, list);

        userList.setAdapter(adapter);


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_sRequestPost();
            }

            public void search_sRequestPost() {

                int method = Request.Method.POST;
                String server_url = "http://172.30.1.42:3000/home/userSearch";

                stringRequest_search = new StringRequest(
                        method,
                        server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("NodeConnActivity", "응답받은 데이터: " + response);

                                try {
                                    JSONObject obj = new JSONObject(response);
                                    JSONArray array = obj.getJSONArray(response);

                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject dept = array.getJSONObject(i);

                                        JSONArray id = dept.getJSONArray("s_id");
                                        Log.d(String.valueOf(id), "onResponse: ");


                                        String S_name = dept.getString("s_name");
                                        String S_birth = dept.getString("s_birth");
                                        String S_phone = dept.getString("s_phone");

                                        list.add(new user_data(S_name, S_birth, S_phone));

                                    }

                                    //adapter = new search_adapter("add",userSearch.this, R.layout.search_layout2,list);

                                    userList.setAdapter(adapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                adapter.notifyDataSetChanged();
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

                        String search_name = et_Sname.getText().toString();

                        param.put("search_name", search_name);

                        return param;
                    }
                };

                queue.add(stringRequest_search);
            }

        });


    }

    public void btnMypage(View view) {
        Intent i = new Intent(userSearch.this, admin_mypage.class);
        startActivity(i);
        finish();
    }

}