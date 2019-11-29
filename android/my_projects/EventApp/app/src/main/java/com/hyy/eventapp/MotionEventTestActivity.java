package com.hyy.eventapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 测试MotionEvent的界面
 */
public class MotionEventTestActivity extends Activity {

    private MyImageView iv_main;
    private LinearLayout parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        //得到MYimageView
        iv_main = (MyImageView) findViewById(R.id.iv_touch_test);
        parentView = (LinearLayout) iv_main.getParent();

        //设置touch监听
        iv_main.setOnTouchListener((view, event) -> {
            Log.d("TAG", "MYimageView Listener onTouch() " + event.getAction());

            /*
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                return true;
            }
            */
            move(event);
            return true; //所有的motionEvent都交给imageView处理
        });

    }

    private int lastX;
    private int lastY;
    private int maxRight;
    private int maxBottom;

    private void move(MotionEvent event) {
        //得到事件的坐标
        int eventX = (int) event.getRawX();
        int eventY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //得到父视图的right/bottom
                if (maxRight == 0) { //保证只赋一次值
                    maxRight = parentView.getRight();
                    maxBottom = parentView.getBottom();
                }

                //第一次记录lastX/lastY
                lastX = eventX;
                lastY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算事件的偏移
                int dx = eventX - lastX;
                int dy = eventY - lastY;
                //根据事件的偏移来移动imageView
                int left = iv_main.getLeft() + dx;
                int top = iv_main.getTop() + dy;
                int right = iv_main.getRight() + dx;
                int bottom = iv_main.getBottom() + dy;

                //限制left  >=0
                if (left < 0) {
                    right += -left;
                    left = 0;
                }
                //限制top
                if (top < 0) {
                    bottom += -top;
                    top = 0;
                }
                //限制right <=maxRight
                if (right > maxRight) {
                    left -= right - maxRight;
                    right = maxRight;
                }
                //限制bottom <=maxBottom
                if (bottom > maxBottom) {
                    top -= bottom - maxBottom;
                    bottom = maxBottom;
                }

                iv_main.layout(left, top, right, bottom);
                //再次记录lastX/lastY
                lastX = eventX;
                lastY = eventY;
                break;

            default:
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("TAG", "-------------event begin " + ev.getAction() + "-------------");
        Log.i("TAG", "Activity dispatchTouchEvent() " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("TAG", "Activity onTouchEvent() " + event.getAction());
        Log.i("TAG", "-------------event end " + event.getAction() + "-------------");
        return super.onTouchEvent(event);
    }
}
