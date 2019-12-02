package com.example.cs125final;

import android.app.Application;
import android.content.Intent;

public class Controller extends Application {
    private static Controller mInstance;

    public static synchronized Controller getInstance() {
        return mInstance;
    }

    public void stopService() {
        stopService(new Intent(getBaseContext(), MusicService.class));
    }
}
