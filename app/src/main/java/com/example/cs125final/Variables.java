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
        System.out.println("Score " + score);
        return score;
    }
    public void addScore(int setScore) {
        this.score = score + setScore;
        System.out.println("Score " + score);
    }
    public void resetScore() {
        this.score = 0;
        System.out.println("setScore " + score);
    }


}
