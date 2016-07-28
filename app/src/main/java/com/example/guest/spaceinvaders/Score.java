package com.example.guest.spaceinvaders;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.parceler.Parcel;

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcel
public class Score {
    String mScore;
    String mUser;

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

    public void setUser(String user){this.mUser = mUser;}
    public void setScore(String score){this.mScore = mScore;}
}
