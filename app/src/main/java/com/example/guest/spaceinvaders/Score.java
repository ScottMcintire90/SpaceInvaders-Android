package com.example.guest.spaceinvaders;

public class Score {
    private String mScore;
    private String mUser;

    public Score() {}

    public Score(String score, String user) {
        this.mScore = score;
        this.mUser = user;
    }

    public String getUser() {
        return mUser;
    }

    public String getScore() {
        return mScore;
    }

}
