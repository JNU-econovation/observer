
package com.ecnv2021.observer_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ecnv2021.observer_android.scatterchart.DemoBase;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScatterChartActivity extends DemoBase implements OnSeekBarChangeListener,
        OnChartValueSelectedListener {

    private static ScatterChart chart;
    private SeekBar seekBarX, seekBarY;

    private static Retrofit mRetrofit;
    private static RetrofitAPI mRetrofitAPI;
    private Gson mGson;
    private static StatisticsVO statisticsResult;
    public static final String TAG = "TTestStatisticsResult";
    public static TextView textView1, textView2;

    public static String name;
    TimerTask timerTask;
    TimerTask timerTaskUI;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scatterchart);

        Intent secondIntent = getIntent();
        name = secondIntent.getStringExtra("name");

        setTitle(name);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        seekBarX = findViewById(R.id.seekBar1);
        seekBarX.setOnSeekBarChangeListener(this);

        seekBarY = findViewById(R.id.seekBar2);
        seekBarY.setOnSeekBarChangeListener(this);

        chart = findViewById(R.id.chart1);
        chart.getDescription().setEnabled(false);
        chart.setOnChartValueSelectedListener(this);

        chart.setDrawGridBackground(false);
        chart.setTouchEnabled(true);
        chart.setMaxHighlightDistance(50f);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        chart.setMaxVisibleValueCount(200);
        chart.setPinchZoom(true);

        seekBarX.setProgress(1440);
        seekBarY.setProgress(1000);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setTypeface(tfLight);
        l.setXOffset(5f);

        YAxis yl = chart.getAxisLeft();
        yl.setTypeface(tfLight);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.getAxisRight().setEnabled(false);

        XAxis xl = chart.getXAxis();
        xl.setTypeface(tfLight);
        xl.setDrawGridLines(false);

        //API 서버 연결
        setRetrofitInit();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "StatisticsData Timer starts");
                //scatter차트 데이터 세팅
                callStatisticsList();

            }
        };
        timer.schedule(timerTask, 0, 2000);

        timerTaskUI = new TimerTask() {
            @Override
            public void run() {
                // UI Thread를 사용해서 View 를 처리합니다.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //통계 데이터 UI 세팅
                            Log.d(TAG, "StatisticsUI Timer starts");
                            setStatisticsResult();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        timer.schedule(timerTaskUI, 1000, 2000);
    }

    @Override
    protected void onStop() {
        timer.cancel();
        Log.d(TAG, "Statistics Timer onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        Log.d(TAG, "Statistics Timer onDestroy()");
        super.onDestroy();
    }

    public void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);
    }

    private static void callStatisticsList() {
        final Call<StatisticsVO> call = mRetrofitAPI.getStatisticsList(name);
        call.enqueue(new Callback<StatisticsVO>() {
            @Override
            public void onResponse(Call<StatisticsVO> call, Response<StatisticsVO> response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG, "error code : " + response.code());
                    return;
                }
                StatisticsVO list = response.body();
                statisticsResult = list;
                Log.d(TAG, "StatisticsResult: 성공 + 결과 : " + list.toString().substring(0, 6));
                Log.d(TAG, "StatisticsResult_xValueResult : " + statisticsResult.getSuccessList().get(0).getTransactionStartTime());
            }

            @Override
            public void onFailure(Call<StatisticsVO> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                Log.d(TAG, "Statistics onResponse: 실패");
            }
        });
    }

    public void setStatisticsResult() {
        ArrayList<Entry> values1 = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();

        if (statisticsResult != null) {
            Log.d(TAG, "Statistics data values 추가");
            for (int i = 0; i < statisticsResult.getSuccessList().size(); i++) {
                float x = (float) statisticsResult.getSuccessList().get(i).getTransactionStartTime();
                float y = (float) statisticsResult.getSuccessList().get(i).getTransactionTimeMillis();
                values1.add(new Entry(x, y));
            }

            for (int i = 0; i < statisticsResult.getFailList().size(); i++) {
                float x = (float) statisticsResult.getFailList().get(i).getTransactionStartTime();
                float y = (float) statisticsResult.getFailList().get(i).getTransactionTimeMillis();
                values2.add(new Entry(x, y));
            }
            textView1.setText("Success: " + statisticsResult.getSuccessNum());
            textView2.setText("Fail: " + statisticsResult.getFailNum());
        } else {
            Log.d(TAG, "StatisticsResult 값 null");
        }

        // create a dataset and give it a type
        ScatterDataSet set1 = new ScatterDataSet(values1, "Success");
        set1.setScatterShape((ScatterChart.ScatterShape.CIRCLE));
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setScatterShapeHoleRadius(3f);
        set1.setScatterShapeHoleColor(ColorTemplate.getHoloBlue());
        ScatterDataSet set2 = new ScatterDataSet(values2, "Fail");
        set2.setScatterShape((ScatterChart.ScatterShape.CIRCLE));
        set2.setScatterShapeHoleColor(ColorTemplate.COLORFUL_COLORS[0]);
        set2.setScatterShapeHoleRadius(3f);
        set2.setColor(ColorTemplate.COLORFUL_COLORS[0]);


        set1.setScatterShapeSize(8f);
        set2.setScatterShapeSize(8f);

        ArrayList<IScatterDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the data sets
        dataSets.add(set2);

        // create a data object with the data sets
        ScatterData data = new ScatterData(dataSets);
        data.setValueTypeface(tfLight);

        chart.setData(data);
        chart.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "ScatterChartActivity");
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", xIndex: " + e.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
