package com.audio.record.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.audio.record.R;

import java.util.Timer;
import java.util.TimerTask;

public class RecordView extends LinearLayout {
    private static final String TAG = "RecordView-1";

    private EventUpCallBack  mCallback;
    Timer timer = null;
    float  startX = 0;
    float  startY= 0;


    public void setmCallback(EventUpCallBack cb){
        mCallback =cb;
    }

    public RecordView(Context context) {
        super(context);
    }

    public RecordView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecordView(Context context, AttributeSet attrs,
                      int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();

                Log.d(TAG, "ACTION_DOWN: ");
                setBackgroundResource(R.drawable.record_select);
                timer = new Timer();

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(mCallback!=null){
                            mCallback.enentLongCB();
                        }
                    }
                }, 300); // 按下时长设置


                return true;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "ACTION_MOVE: ");
                double deltaX = Math.sqrt((event.getX() - startX) * (event.getX() - startX) + (event.getY() - startY) * (event.getY() - startY));
                if (deltaX > 40 && timer != null) { // 移动大于20像素
                    timer.cancel();
                    timer = null;
                }

                setBackgroundResource(R.drawable.record_select);

                return true;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "ACTION_UP: ");
                setBackgroundResource(R.drawable.record_unselect);
                if(mCallback!=null){
                    mCallback.eventUpCB();
                }
                return false;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "ACTION_CANCEL");

                return true;
        }


        return super.onTouchEvent(event);
    }



    public interface EventUpCallBack{
        void enentLongCB();
        void eventUpCB();
    }


}
