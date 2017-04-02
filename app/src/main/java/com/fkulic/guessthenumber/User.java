package com.fkulic.guessthenumber;

import android.support.annotation.NonNull;

/**
 * Created by Filip on 1.4.2017..
 */

public class User implements Comparable<User> {
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

    @Override
    public int compareTo(@NonNull User o) {
        if (this.getScore() > o.getScore()) {
            return 1;
        } else if (this.getScore() < o.getScore()) {
            return -1;
        }
        return 0;
    }
}
