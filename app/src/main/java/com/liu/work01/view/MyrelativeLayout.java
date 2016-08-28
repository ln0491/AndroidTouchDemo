package com.liu.work01.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.liu.work01.TouchEventUtil;

/**
 * Created by 刘楠 on 2016/8/28 0028.16:38
 */
public class MyrelativeLayout extends RelativeLayout {
    public MyrelativeLayout(Context context) {
        super(context);
    }

    public MyrelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyrelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("vivi", "MyrelativeLayout  dispatchTouchEvent:   "+ TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
        //return false;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("vivi", " MyrelativeLayout onInterceptTouchEvent  "+TouchEventUtil.getTouchAction(ev.getAction()));
        return super.onInterceptTouchEvent(ev);
       //return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d("vivi", "MyrelativeLayout: onTouchEvent  "+TouchEventUtil.getTouchAction(event.getAction()));
       // return false;
        return super.onTouchEvent(event);
    }
}
