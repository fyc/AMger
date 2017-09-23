package com.schedule.utils;

import android.app.Service;
import android.content.Intent;

import java.util.concurrent.TimeUnit;

/**
 * @desc
 * @auth 方毅超
 * @time 2017/7/30 23:42
 */

public abstract class ScheduleService extends Service {
    String className = this.getClass().getName();
    public long delay = 0;
    public long period = 10;

    public abstract void create();

    public abstract void startCommand(Intent intent, int flags, int startId);

    /**
     * 初始化延时时间，周期时间，单位为秒
     */
    public abstract void initTime();

    /**
     * 需要周期执行的内容
     */
    public abstract void scheduleRun();

    ScheduleUtil.SRunnable sr = new ScheduleUtil.SRunnable() {
        @Override
        public void run() {
            System.out.println("ScheduleService服务执行中...");
            scheduleRun();
        }

        @Override
        public String getName() {
            return className;
        }
    };

    @Override
    public void onCreate() {
        System.out.println("ScheduleService服务启动onCreate");
        initTime();
        create();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (ScheduleUtil.isAlive(sr)) {
            System.out.println("ScheduleService服务线程存活着...");
        } else {
            System.out.println("ScheduleService服务线程死了，正在重新启动...");
            ScheduleUtil.stop(sr);
            ScheduleUtil.stard(sr, delay, period, TimeUnit.SECONDS);
        }
        startCommand(intent, flags, startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("ScheduleService服务停止");
        ScheduleUtil.stop(sr);
        super.onDestroy();
    }
}
