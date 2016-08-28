package com.liu.work01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "vivi";
    @Bind(R.id.btnStartSecond)
    Button   mBtnStartSecond;
    @Bind(R.id.etName)
    EditText mEtName;
    @Bind(R.id.iv)
    ImageView mIv;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

       initView();

        Log.d(TAG, "onCreate: MainActivity ");
    }

    private void initView() {

        Glide.with(this);
    }

    @OnClick({R.id.btnStartSecond})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnStartSecond:
                goSecond();
                break;
        }
    }

    private void goSecond() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: MainActivity ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: MainActivity ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: MainActivity ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(TAG, "onRestoreInstanceState: MainActivity");

        String test = savedInstanceState.getString("test");
        Log.d(TAG, "onRestoreInstanceState: MainActivity    " +test);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: MainActivity ");
        outState.putString("test","aaaaaaaaaaa");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: MainActivity ");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: MainActivity ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        Log.d(TAG, "onDestroy: MainActivity ");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Log.d(TAG, "dispatchTouchEvent: Activity    "+TouchEventUtil.getTouchAction(ev.getAction()));

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: Activity    "+TouchEventUtil.getTouchAction(event.getAction()));
        return super.onTouchEvent(event);
    }
}
