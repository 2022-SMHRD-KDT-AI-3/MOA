package com.example.moa_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class contacts extends AppCompatActivity {

    TextView tv_spinner;
    Spinner spinner;
    String[] items = {"(선택안함)", "가족", "공공기관", "의료기관"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // 스파이너
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                items
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_spinner = (TextView) view;
                tv_spinner.setText(items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tv_spinner.setText("선택: ");
            }
        });


    }
}