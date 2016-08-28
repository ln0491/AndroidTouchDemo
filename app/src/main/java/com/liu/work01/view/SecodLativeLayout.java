package com.liu.work01.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by 刘楠 on 2016/8/28 0028.16:42
 */
public class SecodLativeLayout extends LinearLayout {
    public SecodLativeLayout(Context context) {
        super(context);
    }

    public SecodLativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SecodLativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("vivi", "SecodLativeLayout  dispatchTouchEvent:  "+super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("vivi", " SecodLativeLayout onInterceptTouchEvent ");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d("vivi", "SecodLativeLayout: onTouchEvent "+super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }
}
