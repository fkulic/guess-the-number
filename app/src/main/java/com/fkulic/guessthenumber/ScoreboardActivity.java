package com.fkulic.guessthenumber;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ScoreboardActivity extends Activity {

    RecyclerView rvScoreboard;
    ScoreAdapter mScoreAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.ItemDecoration mItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        setUpUI();
    }

    private void setUpUI() {
        this.rvScoreboard = (RecyclerView) findViewById(R.id.rvScoreboard);
        this.mScoreAdapter = new ScoreAdapter(loadScores());
        this.mLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.mItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);

        this.rvScoreboard.addItemDecoration(this.mItemDecoration);
        this.rvScoreboard.setLayoutManager(this.mLayoutManager);
        this.rvScoreboard.setAdapter(this.mScoreAdapter);
    }

    private ArrayList<User> loadScores() {
        return ScoreDBHelper.getInstance(getApplicationContext()).getScores();
    }
}
