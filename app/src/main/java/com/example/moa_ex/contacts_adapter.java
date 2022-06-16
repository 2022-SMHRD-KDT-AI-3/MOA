package com.example.moa_ex;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class contacts_adapter extends BaseAdapter {

    Context context;  // MainActivity 정보를 저장
    int layout;  // list_item.xml 를 저장
    ArrayList<contacts_data> list; // 연락처 정보를 저장
    LayoutInflater inflater;   // xml -> view 객체로 변환★

    public contacts_adapter(Context context, int layout, ArrayList<contacts_data> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;

        //★ 고정 나중에 쓸 때 갖다쓰셈
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // inflater를 view객체로 변환할 수 있는 기능 ★
    }

    @Override
    public int getCount() { // 데이터의 개수 반환
        // --> ListView.setAdapter에서 리스트 정보들을 넣어줘야 반환가능 그래서 setAdapter에서 호출
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

    // getView()의 역할: list_item.xml에 배치된 뷰객체를 초기화하고 데이터와 바인딩하는 역할
    // i: 인덱스
    // view: list_item.xml의 정보를 가진 객체
    // viewGroup: listView 객체
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(layout,viewGroup,false);

        ImageView imgProfile = view.findViewById(R.id.imgProfile);
        TextView name = view.findViewById(R.id.tv_dept_tell);
        TextView phone = view.findViewById(R.id.tv_dept_name);
        Button btn_call = view.findViewById(R.id.btn_search);
        Button btn_sms = view.findViewById(R.id.btn_alert);

        Log.d("Adapter", list.get(i)+"");

        contacts_data vo = list.get(i);
        imgProfile.setImageResource(vo.getImgId());
        name.setText(vo.getName());
        phone.setText(vo.getPhone());

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast.makeText(context, "선택한 연락처: "+vo.getPhone(), Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(context, 이동할 객체);

            }
        });

        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // view객체를 반환해야 리스트뷰에 정보를 출력할 수 있음 ★★★
        return view;
    }
}

