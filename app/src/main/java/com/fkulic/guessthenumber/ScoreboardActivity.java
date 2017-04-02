package com.fkulic.guessthenumber;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ScoreboardActivity extends Activity implements GetTop10.OnGetTop10 {

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

    @Override
    protected void onResume() {
        super.onResume();
        GetTop10 top10 = new GetTop10(getApplicationContext(), this);
        top10.execute();
    }

    private void setUpUI() {
        this.rvScoreboard = (RecyclerView) findViewById(R.id.rvScoreboard);
        this.mScoreAdapter = new ScoreAdapter(new ArrayList<User>());
        this.mLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.mItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);

        this.rvScoreboard.addItemDecoration(this.mItemDecoration);
        this.rvScoreboard.setLayoutManager(this.mLayoutManager);
        this.rvScoreboard.setAdapter(this.mScoreAdapter);
    }

    @Override
    public void getTop10(ArrayList<User> users) {
        mScoreAdapter.loadData(users);
    }

}
