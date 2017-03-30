package com.fkulic.guessthenumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    Button bPlayGame;
    Button bViewScores;
    Button bShowRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();
    }

    private void setUpUI() {
        bPlayGame = (Button) findViewById(R.id.bPlayGame);
        bViewScores = (Button) findViewById(R.id.bViewScores);
        bShowRules = (Button) findViewById(R.id.bShowRules);

        bPlayGame.setOnClickListener(this);
        bViewScores.setOnClickListener(this);
        bShowRules.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.bPlayGame:
                intent = new Intent(getApplicationContext(), GameActivity.class);
                break;
            case R.id.bViewScores:
                intent = new Intent(getApplicationContext(), ScoreboardActivity.class);
                break;
            case R.id.bShowRules:
                intent = new Intent(getApplicationContext(), RulesActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
