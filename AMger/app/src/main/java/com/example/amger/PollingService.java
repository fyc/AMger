package com.example.amger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Polling service
 *
 * @Author Ryan
 * @Create 2013-7-13 上午10:18:44
 */
public class PollingService extends Service {

    public static final String ACTION = "com.ryantang.service.PollingService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        System.out.println("Polling...onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        //*1
//        ScheduledExecutorService service= Executors.newScheduledThreadPool(2);
////*2
//        Runnable task1=new Runnable()
//        {
//            public void run()
//            {
//                System.out.println("Taskrepeating.");
//            }
//        };
//        //*3
//        final ScheduledFuture future1=service.scheduleAtFixedRate(task1,0,1, TimeUnit.SECONDS);
//
////*4
//        ScheduledFuture future2=service.schedule(new Callable()
//        {
//            public String call()
//            {
//                future1.cancel(true);
//                return "taskcancelled!";
//            }
//        },10,TimeUnit.SECONDS);
//        try {
//            System.out.println(future2.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//*5
//        service.shutdown();

        if (pt != null && pt.isAlive()) {
            System.out.println("Polling...onStartCommand");
        } else {
            pt = new PollingThread();
            pt.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    PollingThread pt;
    /**
     * Polling thread
     *
     * @Author Ryan
     * @Create 2013-7-13 上午10:18:34
     */
    int count = 0;

    class PollingThread extends Thread {
        @Override
        public void run() {
            System.out.println("Polling...");

            while (true) {
                count++;
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("New message!");
//                Toast.makeText(getApplicationContext(), "默认Toast样式",
//                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service:onDestroy");
    }

}
