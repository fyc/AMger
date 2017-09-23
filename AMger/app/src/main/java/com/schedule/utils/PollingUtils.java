package com.schedule.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class PollingUtils {

    /**
     * @param context
     * @param seconds
     * @param cls
     * @param action
     */
    public static void startPollingService(Context context, int seconds, Class<?> cls, String action) {
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long triggerAtTime = SystemClock.elapsedRealtime();
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime,
                seconds * 1000, pendingIntent);
    }

    /**
     * @param context
     * @param cls
     * @param action
     */
    public static void stopPollingService(Context context, Class<?> cls, String action) {
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        manager.cancel(pendingIntent);
    }


    /**
     * 启动闹钟服务，每60秒询问一次服务，若是服务中的线程发生异常，将会进行重启
     * @param context
     * @param cls
     * @param action
     */
    public static void startScheduleService(Context context, Class<?> cls, String action) {
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long triggerAtTime = SystemClock.elapsedRealtime();
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime,
                60* 1000, pendingIntent);
    }

    /**
     * 停止闹钟服务，但是目标服务类并不会关闭
     * @param context
     * @param cls
     * @param action
     */
    public static void stopScheduleService(Context context, Class<?> cls, String action) {
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        manager.cancel(pendingIntent);
        killScheduleService(context,cls,action);
    }

    /**
     * 关闭目标服务类
     * @param context
     * @param cls
     * @param action
     */
    private static void killScheduleService(Context context, Class<?> cls, String action) {
        Intent stopIntent = new Intent(context, cls);
        context.stopService(stopIntent);
    }
}
