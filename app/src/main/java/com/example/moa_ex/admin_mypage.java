package com.example.moa_ex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    ///////////////////////////////////////////////////////
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    ///////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mypage);

        ////////////////////////////////
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String A_ID = sharedPreferences.getString(KEY_NAME,null);
        if(A_ID != null){
            // textView.setText(ID);
            Log.d(A_ID, "onCreate: ");
        }
        /////////////////////////////////


        btn_previous = findViewById(R.id.btn_previous);

        queue = Volley.newRequestQueue(admin_mypage.this);

        userList = findViewById(R.id.userList);

        list = new ArrayList<>();

        adapter = new search_adapter("",admin_mypage.this, R.layout.search_layout_item, list);

        userList.setAdapter(adapter);

        matching_sRequestPost();

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(admin_mypage.this, userSearch.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void matching_sRequestPost() {

        int method = Request.Method.POST;
        String server_url = "http://172.30.1.42:3000/home/userList";

        stringRequest = new StringRequest(
                method,
                server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NodeConnActivity", "응답받은 데이터(list): " + response);

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject info = array.getJSONObject(i);

                                String S_id = info.getString("s_id");
                                Log.d(S_id, "onResponse: 아이디 검사중");


                                String S_name = info.getString("s_name");
                                String S_birth = info.getString("s_birth");
                                String S_phone = info.getString("s_phone");

                                list.add(new user_data(S_id,S_name, S_birth, S_phone));

                            }

                            adapter = new search_adapter("",admin_mypage.this, R.layout.search_layout_item,list);

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



                return param;
            }
        };

        queue.add(stringRequest);
    }
}