package com.example.moa_ex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.moa_ex.user_data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



//어댑터의 역할 : 데이터와 레이아웃을 바인딩해서 listView에 출력될 수 있도록 변환해주는 역할
public class search_adapter extends BaseAdapter {

    RequestQueue queue;
    StringRequest request;


    Context context;
    int layout;
    ArrayList<user_data> list;
    LayoutInflater inflater;
    search_viewholder holder; //list_item.xml에 배치된 View객체들을 초기화하는 역할
    String page;


    public search_adapter(String page, Context context, int layout, ArrayList<user_data> list) {
        this.page = page;
        this.context = context;
        this.layout = layout;
        this.list = list;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public search_adapter(userSearch userSearch, int search_layout2, ArrayList<user_data> list) {
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        if(view == null) {
            view = inflater.inflate(layout, viewGroup, false);
            holder = new search_viewholder(view,page);
            view.setTag(holder); // 해당 뷰 객체 상태정보 저장 용도

        }else{
            holder = (search_viewholder) view.getTag();
        }

        user_data vo = list.get(i);

//        holder.getImg().setImageResource(vo.getImgId());
        holder.getTv_sname().setText(vo.getS_name());
        holder.getTv_sbirth().setText(String.valueOf(vo.getS_birth()));
        holder.getTv_sphone().setText(String.valueOf(vo.getS_phone()));

        //화면구분
        if(page.equals("add")){

            holder.getBtn_add().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int method = Request.Method.POST;
                    String server_url = "http://172.30.1.2:3000/home/matching";
                    request = new StringRequest(
                            method,
                            server_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("NodeConnActivity", "응답받은 데이터" + response);
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

                            String S_id = vo.getS_id();
                            param.put("S_id", S_id);
                            return param;
                        }
                    };
                }
            });

        }else{

//              holder.getBtn_calender().setOnClickListener();
//              holder.getBtn_alert().setOnClickListener();

        }

        return view;
    }
}