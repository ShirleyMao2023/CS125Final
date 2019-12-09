package com.example.cs125final;

import android.app.Application;
import android.content.Intent;

public class Variables extends Application {
    private String name;
    private int score;
    private Intent musicService;
    public String getUserName() {
        return name;
    }

    public void setUserName(String setName) {
        this.name = setName;
    }
    public void setMusicService(Intent setMusicService) {
        this.musicService = setMusicService;
    }
    public Intent getMusicService() {
        return musicService;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int setScore) {
        this.score = score + setScore;
    }

    public void resetScore() {
        this.score = 0;
    }
}
