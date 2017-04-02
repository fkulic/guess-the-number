package com.fkulic.guessthenumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.fkulic.guessthenumber.MainActivity.KEY_USERNAME;

public class UsernameActivity extends Activity implements View.OnClickListener {

    EditText etSetUsername;
    Button bSaveUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        setUpUI();
    }

    private void setUpUI() {
        this.etSetUsername = (EditText) findViewById(R.id.etSetUsername);
        this.bSaveUsername = (Button) findViewById(R.id.bSaveUsername);

        bSaveUsername.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String username = this.etSetUsername.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(KEY_USERNAME, username);
        this.setResult(RESULT_OK, intent);
        this.finish();
    }
}
