package com.liu.work01.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.liu.work01.TouchEventUtil;

/**
 * Created by 刘楠 on 2016/8/28 0028.16:43
 */
public class MyImageView extends View {
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("vivi ", " MyImageView dispatchTouchEvent:   "+ TouchEventUtil.getTouchAction(event.getAction()));
        return super.dispatchTouchEvent(event);
      // return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("vivi ", "  MyImageView     onTouchEvent:   "+TouchEventUtil.getTouchAction(event.getAction()));
        //return super.onTouchEvent(event);
        return false;
    }
}
