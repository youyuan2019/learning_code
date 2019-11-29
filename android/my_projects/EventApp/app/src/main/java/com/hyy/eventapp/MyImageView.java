package com.hyy.eventapp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 自定义ImageView
 *
 */
public class MyImageView extends AppCompatImageView {

	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.d("TAG", "MyImageView()");
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.d("TAG", "MyImageView dispatchTouchEvent() "+event.getAction());
		return super.dispatchTouchEvent(event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("TAG", "MyImageView onTouchEvent() "+event.getAction());
		return false;
		//return true;
	}
}
