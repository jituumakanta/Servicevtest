package com.ipvworld.servicevtest;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        ExecutorService executor = Executors.newFixedThreadPool(10);//creating a pool of 5 threads

        Runnable worker = new news1();
        executor.execute(worker);

        Runnable worker1 = new news2();
        executor.execute(worker1);

        executor.shutdown();
        while (!executor.isTerminated()) {}
        System.out.println("Finished all threads");

    }


}
