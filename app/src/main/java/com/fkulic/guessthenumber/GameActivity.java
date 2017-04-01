package com.fkulic.guessthenumber;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements View.OnClickListener {
    public static final String KEY_GUESS_COUNT = "guess_count";
    public static final String KEY_GUESS_STATUS = "guess_status";
    public static final String KEY_GUESS_TRY = "guess_try";

    public static final String GUESS_START = "Guess the number (1-100)";
    public static final String GUESS_CORRECT = "Correct";
    public static final String GUESS_BIGGER = "Bigger";
    public static final String GUESS_SMALLER = "Smaller";

    EditText etGuessNumber;
    Button bGuessNumber;
    TextView tvGuessStatus;
    TextView tvGuessCount;

    private int mRandomNum;
    private int mGuessCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setUpUI();
        reset();
    }

    private void setUpUI() {
        etGuessNumber = (EditText) findViewById(R.id.etGuessNumber);
        bGuessNumber = (Button) findViewById(R.id.bGuessNumber);
        tvGuessStatus = (TextView) findViewById(R.id.tvGuessStatus);
        tvGuessCount = (TextView) findViewById(R.id.tvGuessCount);

        bGuessNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (bGuessNumber.getText().toString().equals(getString(R.string.bGuessText))) {
            int guess;
            try {
                guess = Integer.parseInt(etGuessNumber.getText().toString());
                tvGuessCount.setText(String.valueOf(++this.mGuessCount));
                checkGuess(guess);
                etGuessNumber.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Please enter number as value", Toast.LENGTH_SHORT).show();
            }
        } else {
            reset();
        }

    }

    private void reset() {
        this.mRandomNum = RandomNumber.getInstance().nextInt(100)+1;
        this.mGuessCount = 0;
        setTextLabels(GUESS_START, mGuessCount);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_GUESS_COUNT, this.mGuessCount);
        outState.putString(KEY_GUESS_STATUS, tvGuessStatus.getText().toString());
        outState.putString(KEY_GUESS_TRY, etGuessNumber.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        this.mGuessCount = savedInstanceState.getInt(KEY_GUESS_COUNT);
        String guessStatus = savedInstanceState.getString(KEY_GUESS_STATUS);
        String guessTry = savedInstanceState.getString(KEY_GUESS_TRY);
        etGuessNumber.setText(guessTry);
        setTextLabels(guessStatus, mGuessCount);
    }

    private void checkGuess(int guess) {
        if (guess == mRandomNum) {
            tvGuessStatus.setText(GUESS_CORRECT);
            bGuessNumber.setText(R.string.bTryAgainText);
            saveScore();
        } else if (guess < mRandomNum) {
            tvGuessStatus.setText(GUESS_BIGGER);
        } else {
            tvGuessStatus.setText(GUESS_SMALLER);
        }
    }

    private void saveScore() {
        PreferenceManagement pf = new PreferenceManagement();
        String username = pf.retrieveUsername(getApplicationContext());
        ScoreDBHelper dbHelper = ScoreDBHelper.getInstance(getApplicationContext());
        dbHelper.insertScore(new User(username, mGuessCount));
    }

    private void setTextLabels(String s, int n) {
        tvGuessStatus.setText(s);
        tvGuessCount.setText(String.valueOf(n));
    }
}
