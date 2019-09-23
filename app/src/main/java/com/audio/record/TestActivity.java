package com.audio.record;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.audio.record.views.RecordView;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity-1";
    RecordView llLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        llLayout = findViewById(R.id.ll_long);
        llLayout.setmCallback(new RecordView.EventUpCallBack() {
            @Override
            public void enentLongCB() {
                Log.d(TAG, "手指长按");
            }

            @Override
            public void eventUpCB() {
                Log.d(TAG, "手指放开了");
            }
        });

        
        llLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.d(TAG, "onLongClick: ");
                return true;
            }
        });
    }
}
