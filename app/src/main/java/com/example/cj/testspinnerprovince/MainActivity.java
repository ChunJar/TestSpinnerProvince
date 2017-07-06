package com.example.cj.testspinnerprovince;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Spinner provinceSpinner;
    private Spinner citySpinner;
    private Spinner countySpinner;
    private TextView show;

    private String[] provinces = new String[]{"1", "2", "3"};
    private String[][] cities = new String[][]{{"11", "12", "13"}, {"21", "22"}, {"31"}};
    private String[][][] counties = new String[][][]{{{"111", "112"}, {"121"}, {"131", "132",
            "133"}}, {{"211", "212"}, {"221", "222"}}, {{"311"}}};

    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> cityAdapter;
    private ArrayAdapter<String> countyAdapter;

    private int provincePosition;
    private int cityPosition;
    private int countyPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        provinceSpinner = (Spinner) findViewById(R.id.provinceSpinner);
        citySpinner = (Spinner) findViewById(R.id.citySpinner);
        countySpinner = (Spinner) findViewById(R.id.countySpinner);
        show = (TextView) findViewById(R.id.show);
        //初始化省级下拉列表
        provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                provinces);
        provinceSpinner.setAdapter(provinceAdapter);
        //对省级下拉列表进行监听
        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //需要在item选中省级的时候动态地改变市对应地值
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout
                        .simple_spinner_item, cities[position]);
                citySpinner.setAdapter(cityAdapter);
                //记录省索引
                provincePosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //对地级下拉列表进行监听
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countyAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout
                        .simple_spinner_item, counties[provincePosition][position]);
                countySpinner.setAdapter(countyAdapter);
                //记录市索引
                cityPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //对区级下拉列表进行监听
        countySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countyPosition = position;
                show.setText("选中的城市为：" + provinces[provincePosition] +
                        cities[provincePosition][cityPosition] +
                        counties[provincePosition][cityPosition][countyPosition]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
