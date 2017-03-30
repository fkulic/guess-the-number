package com.fkulic.guessthenumber;

import java.security.SecureRandom;

/**
 * Created by Filip on 30.3.2017..
 */

public class RandomNumber {
    private static final RandomNumber singleton = new RandomNumber();
    private final SecureRandom mRandom = new SecureRandom();

    private RandomNumber() {}

    public static synchronized RandomNumber getInstance() {
        return singleton;
    }

    public final synchronized int nextInt() {
        return mRandom.nextInt();
    }

    public final synchronized int nextInt(int n) {
        return mRandom.nextInt(n);
    }


}
