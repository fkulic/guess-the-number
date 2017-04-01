package com.fkulic.guessthenumber;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Filip on 1.4.2017..
 */

public class PreferenceManagement {

    public static final String PREFS_FILE = "user_data";
    public static final String PREFS_KEY_USERNAME = "username_key";

    public void saveUsername(Context context, String username) {
        SharedPreferences sp = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PREFS_KEY_USERNAME, username);
        editor.apply();
    }

    public String retrieveUsername(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        return sp.getString(PREFS_KEY_USERNAME, "unknown");
    }
}
