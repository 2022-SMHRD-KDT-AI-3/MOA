package com.example.moa_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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

public class contacts_ListActivity extends AppCompatActivity {

    TextView tv_spinner;
    Spinner spinner;
    String[] items = { "의료기관", "공공기관", "가족"};

    ListView listView;
    contacts_deptAdapter deptAdapter;
    ArrayList<contacts_dept_VO> list;

    RequestQueue queue;
    StringRequest request;
    StringRequest request2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list_view);

        listView = findViewById(R.id.listview);
        list = new ArrayList<>();

        queue = Volley.newRequestQueue(contacts_ListActivity.this);

        int method = Request.Method.GET;
//        String server_url = "http://172.30.1.2:3000/home/dept";
//        String server_url2 = "http://172.30.1.2:3000/home/hospital";

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                items
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // 스피너 아이템 클릭시 값을 받아와줌
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_spinner = (TextView) view;
                tv_spinner.setText(items[position]);
                String text = spinner.getSelectedItem().toString();

                if(text == "공공기관"){
                    Log.d(text, "onItemSelected: 공공기관 선택");
                    String server_url = "http://172.30.1.2:3000/home/list?name=dept";
                    request = new StringRequest(
                            method,
                            server_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("기관정보", response);

                                    try {
                                        JSONObject obj = new JSONObject(response);
                                        JSONArray array = obj.getJSONArray("dept_list");

                                        for(int i=0; i<array.length(); i++){
                                            JSONObject dept = array.getJSONObject(i);

                                            String dept_name = dept.getString("dept_name");
                                            String dept_tel = dept.getString ("dept_tel");

                                            list.add(new contacts_dept_VO(dept_name,dept_tel));

                                        }

                                        deptAdapter = new contacts_deptAdapter(
                                                contacts_ListActivity.this,
                                                R.layout.contacts_listitem,
                                                list);

                                        listView.setAdapter(deptAdapter);
                                        // 포스터 어댑터

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("오류발생", error.toString());
                                }
                            }

                    );
                }else if(text == "의료기관"){
                    Log.d(text, "onItemSelected: 공공기관 선택");
                    String server_url = "http://172.30.1.2:3000/home/list?name=hospital";
                    request = new StringRequest(
                            method,
                            server_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("기관정보", response);

                                    try {
                                        JSONObject obj = new JSONObject(response);
                                        JSONArray array = obj.getJSONArray("dept_list");

                                        for(int i=0; i<array.length(); i++){
                                            JSONObject dept = array.getJSONObject(i);

                                            String dept_name = dept.getString("dept_name");
                                            String dept_tel = dept.getString ("dept_tel");

                                            list.add(new contacts_dept_VO(dept_name,dept_tel));

                                        }

                                        deptAdapter = new contacts_deptAdapter(
                                                contacts_ListActivity.this,
                                                R.layout.contacts_listitem,
                                                list);

                                        listView.setAdapter(deptAdapter);
                                        // 포스터 어댑터

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("오류발생", error.toString());
                                }
                            }

                    );
                }




                queue.add(request);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tv_spinner.setText("선택: ");
            }
        });


    }
}