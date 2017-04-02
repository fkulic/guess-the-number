package com.fkulic.guessthenumber;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final int KEY_REQ_USERNAME = 5;
    public static final String KEY_USERNAME = "username";

    Button bPlayGame;
    Button bViewScores;
    Button bShowRules;
    Button bOptions;

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
        bOptions = (Button) findViewById(R.id.bOptions);

        bPlayGame.setOnClickListener(this);
        bViewScores.setOnClickListener(this);
        bShowRules.setOnClickListener(this);
        bOptions.setOnClickListener(this);
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
                //intent = new Intent(getApplicationContext(), RulesActivity.class);
                // testing database and shared prefs
                ArrayList<User> arr = ScoreDBHelper.getInstance(getApplicationContext()).getScores();
                for (User user : arr) {
                    Log.d("BAZA", user.getUsername() + ": " + user.getScore());
                }

                break;
            case R.id.bOptions:
                intent = new Intent(getApplicationContext(), UsernameActivity.class);
                this.startActivityForResult(intent, KEY_REQ_USERNAME);
                intent = null;
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == KEY_REQ_USERNAME) {
            if (resultCode == RESULT_OK) {
                PreferenceManagement pf = new PreferenceManagement();
                Context context = getApplicationContext();
                String username = data.getStringExtra(KEY_USERNAME);
                pf.saveUsername(context, username);
                Toast.makeText(context, getString(R.string.toastChangeUsername) + username, Toast.LENGTH_SHORT).show();
            }
        }
    }

}

