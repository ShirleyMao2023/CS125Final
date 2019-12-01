package com.example.cs125final;


import android.app.Application;

public class Variables extends Application {
    private String name;
    private int score;

    public String getUserName() {
        return name;
    }

    public void setUserName(String setName) {
        this.name = setName;
        System.out.println("name " + name);
    }
    public int getScore() {
        return score;
    }
    public void setScore(int setScore) {
        this.score = setScore;
        System.out.println("Score " + score);
    }

}
