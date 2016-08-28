package com.liu.work01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "vivi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, "onCreate: SecondActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: SecondActivity ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: SecondActivity ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: SecondActivity ");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: SecondActivity ");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: SecondActivity ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        Log.d(TAG, "onDestroy: SecondActivity ");
    }
}
