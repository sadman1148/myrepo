package com.redenvy.thegoodservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MusicService extends Service {

    private MediaPlayer vlc;
    private boolean canPause,canResume;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        canPause = intent.getBooleanExtra("Pause",false);
        canResume = intent.getBooleanExtra("Resume", false);
        if(vlc!=null && !canResume && !canPause){
            vlc.start();
        }
        if(vlc!=null && canPause && vlc.isPlaying()){
            vlc.pause();
        }
        if (vlc!=null && canResume){
            vlc.start();
        }
        Log.e("Sadman searches", "Music started");
        return startId;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        if(vlc==null){
            vlc = vlc.create(this,R.raw.clip);
        }
        vlc.setLooping(false);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        vlc.stop();
        vlc.release();
        vlc = null;
    }
}
