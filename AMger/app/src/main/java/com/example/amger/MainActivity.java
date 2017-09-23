package com.example.amger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.schedule.utils.PollingUtils;
import com.schedule.utils.ScheduleUtil;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {


    Button btn1, btn2, btn3, btn4;
    ScheduleUtil.SRunnable sr = new ScheduleUtil.SRunnable() {
        @Override
        public void run() {
            System.out.println("Taskrepeating.a");
        }

        @Override
        public String getName() {
            return "a";
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("启动周期线程...");
                ScheduleUtil.stard(sr, 1, 2, TimeUnit.SECONDS);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("启动周期线程service...");
                PollingUtils.startScheduleService(MainActivity.this, PollingService2.class, "com.ryantang.service.PollingService2");

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("停止周期线程");
                ScheduleUtil.stop(sr);

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("停止周期线程service");
                PollingUtils.stopScheduleService(MainActivity.this, PollingService2.class, "com.ryantang.service.PollingService2");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
