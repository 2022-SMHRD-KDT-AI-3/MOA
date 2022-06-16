package com.example.moa_ex;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONActivity extends AppCompatActivity {
    TextView tvJSONResult;

    // 서버 통신을 위한 객체 생성
    RequestQueue queue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonactivity);

        tvJSONResult = findViewById(R.id.tvJSONResult);

        // context : this
        queue = Volley.newRequestQueue(JSONActivity.this);

        int method = Request.Method.GET;

        String url = "http://172.30.1.2:3000/home/list";

        request = new StringRequest(
                method,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvJSONResult.setText(response);

                        try {
                            JSONObject object = new JSONObject(response);
                            JSONObject result = object.getJSONObject("boxOfficeResult");
                            JSONArray dept_list = result.getJSONArray("dept_list");

//                          대량의 텍스트 데이터를 저장할 때 사용하는 객체
                            StringBuffer sb = new StringBuffer();


                            for(int i =0; i<dept_list.length(); i++){
                                JSONObject movie = dept_list.getJSONObject(i);

                                // 순위(rank), 영화명(movieNm), 개봉일(openDt) 가져오기
                                // 기관명(dept_name), 연락처 (dept_tell)
                                String dept_name = movie.getString("dept_name");
                                String dept_tel = movie.getString("dept_tel");

                                sb.append(" {"+dept_name+" / "+dept_tel+" / } \n ");

                                Log.v("dept_list","기관정보 >> {"+dept_name+" / "+dept_tel+" / }");
                            }

                            tvJSONResult.setText(sb.toString());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error","요청실패>>" +error.toString());
                    }
                }
        );

        queue.add(request);




    }
}
