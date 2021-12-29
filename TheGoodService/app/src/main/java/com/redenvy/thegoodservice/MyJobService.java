package com.redenvy.thegoodservice;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

public class MyJobService extends JobService {
    private boolean isCanceled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        startTheMusic(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public void startTheMusic(JobParameters params){
        new Thread(new Runnable() {
            @Override
            public void run() {
                startService(new Intent(getApplicationContext(),MusicService.class));
                jobFinished(params, true);
            }
        }).start();
    }
}

