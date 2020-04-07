package com.example.covid19_navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class graph extends AppCompatActivity {
    LineChart lineChart;
    //ArrayList<Entry> x;
    ArrayList<String> y;
    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList<Entry> lineEntries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineChart = findViewById(R.id.chart);
        lineEntries = new ArrayList<>();
        getEntries();

        y = new ArrayList<>();

        //lineDataSet.setValueTextSize(18f);


    }
    public void getEntries() {


        Call<String> call = retrofit_client.getInstance().getapi().region();
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                try {
                    assert response.body() != null;
                    JSONObject object = new JSONObject(response.body());
                    JSONArray array = object.getJSONArray("cases_time_series");

                    for (int i = 0; i < array.length(); i++) {

                        case_time_series data = new case_time_series();
                        JSONObject dataobj = array.getJSONObject(i);

                        data.setTotal_confirmed(dataobj.getString("totalconfirmed"));

                        float data_int = Float.parseFloat(String.valueOf(data.getTotal_confirmed()));


                        lineEntries.add(new Entry(Float.parseFloat(String.valueOf(i)),data_int));










                    }
                    //XAxis xAxis = new XAxis();
                    YAxis yAxis = lineChart.getAxisLeft();
                    XAxis xAxis = lineChart.getXAxis();

                    //yAxis.setLineWidth(4f);
                    yAxis.setAxisLineColor(Color.BLACK);
                    yAxis.setAxisLineWidth(1.2f);
                    yAxis.setGridColor(Color.WHITE);
                    yAxis.setTextColor(Color.BLACK);
                    yAxis.setTextSize(12f);
                    xAxis.setAxisLineColor(Color.BLACK);
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setAxisLineWidth(1.2f);
                    xAxis.setGridColor(Color.WHITE);
                    xAxis.setTextColor(Color.BLACK);
                    xAxis.setTextSize(12f);
// .. and more styling options


                    lineDataSet = new LineDataSet(lineEntries, "Total Cases");
                    lineDataSet.setLabel("Total Cases");
                    lineData = new LineData(lineDataSet);
                    lineChart.setData(lineData);

                    lineChart.setEnabled(true);


                    //lineDataSet.setColors();
                   lineChart.setGridBackgroundColor(Color.WHITE);
                    lineChart.setTouchEnabled(true);

                    lineChart.setDrawGridBackground(false);
                    int color = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
                    lineChart.setPinchZoom(true);
                    lineDataSet.setColor(color);
                    lineDataSet.setLineWidth(1.2f);

                    lineDataSet.setCircleColor(color);
                    lineDataSet.setLineWidth(2.1f);
                    lineDataSet.setCircleHoleColor(color);
                    lineChart.setDrawBorders(false);
                    lineChart.setBackgroundColor(Color.WHITE);
                    lineChart.setHighlightPerTapEnabled(true);
                    lineDataSet.setValueTextColor(Color.BLACK);
                    lineChart.animateXY(3000,3000);

                    //legend
                    Legend l = lineChart.getLegend();
                    l.setFormSize(10f); // set the size of the legend forms/shapes
                    l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
                   // l.(LegendPosition.BELOW_CHART_LEFT);
                    //l.setTypeface(R.font.font);
                    l.setTextSize(12f);
                    l.setTextColor(Color.BLACK);
                    l.setXEntrySpace(5f); // space between the legend entries on the x-axis
                    l.setYEntrySpace(5f); // space between the legend entries on the y-axis
                    // set custom labels and colors




                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("error : " + e.getMessage());
                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("error : "+ t.getMessage());

            }
        });

    }
}
