package com.example.amger;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.schedule.utils.ScheduleService;

/**
 * @desc
 * @auth 方毅超
 * @time 2017/7/31 10:06
 */

public class PollingService2 extends ScheduleService {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("Taskrepeating.onBind");
        return null;
    }

    @Override
    public void create() {
        System.out.println("Taskrepeating.create");
    }

    @Override
    public void startCommand(Intent intent, int flags, int startId) {
        System.out.println("Taskrepeating.startCommand");
    }

    @Override
    public void initTime() {
        this.delay = 1;
        this.period = 3;
    }

    @Override
    public void scheduleRun() {
        System.out.println("Taskrepeating.scheduleRun...ing");
    }

}
