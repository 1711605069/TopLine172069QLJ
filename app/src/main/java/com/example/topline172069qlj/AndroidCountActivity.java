package com.example.topline172069qlj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class AndroidCountActivity extends AppCompatActivity {
private PieChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_count);
   chart=findViewById(R.id.chart);
        ArrayList<PieEntry> entries=new ArrayList<>();
        entries.add(new PieEntry(4,"月薪8-15K"));
        entries.add(new PieEntry(3,"月薪15-20K"));
        entries.add(new PieEntry(4,"月薪20-30K"));
        entries.add(new PieEntry(4,"月薪30K+"));
        PieDataSet dataSet=new PieDataSet(entries,"月薪");
        dataSet.setColors(new int[]{R.color.color_qiu,R.color.color_violet,
        R.color.color_blue,R.color.color_orange},getApplicationContext());
        PieData pieData=new PieData(dataSet);
        chart.setData(pieData);
        chart.setHoleRadius(0f);
        chart.setTransparentCircleRadius(0f);
        dataSet.setDrawValues(false);
        chart.setEntryLabelTextSize(16);
    }
}
