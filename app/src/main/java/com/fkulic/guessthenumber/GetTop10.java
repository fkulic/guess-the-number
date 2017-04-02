package com.fkulic.guessthenumber;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Filip on 2.4.2017..
 */

public class GetTop10 extends AsyncTask<Void, Void, ArrayList<User>>  {

    private final OnGetTop10 mCallback;
    Context mContext;

    public interface OnGetTop10 {
        void getTop10(ArrayList<User> users);
    }

    public GetTop10( Context context, OnGetTop10 callback) {
        mContext = context;
        mCallback = callback;
    }

    @Override
    protected ArrayList<User> doInBackground(Void... params) {
        ArrayList<User> scores = ScoreDBHelper.getInstance(mContext).getScores();
        ArrayList<User> top10 = null;
        if (scores.size() > 0) {
            Collections.sort(scores);
            top10 = new ArrayList<>();
            for (int i=0; i<10; i++) {
                if (i >= scores.size()) {
                    break;
                }
                top10.add(scores.get(i));
            }
        }
        return top10;
    }

    @Override
    protected void onPostExecute(ArrayList<User> users) {
        if (mCallback != null) {
            mCallback.getTop10(users);
        }
    }


}
