package com.fkulic.guessthenumber;

/**
 * Created by Filip on 1.4.2017..
 */

public class User {
    private String username;
    private int score;

    public User(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }
}
