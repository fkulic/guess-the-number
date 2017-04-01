package com.fkulic.guessthenumber;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UsernameActivity extends Activity implements View.OnClickListener {

    private PreferenceManagement mPrefs;

    EditText etSetUsername;
    Button bSaveUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        setUpUI();
    }

    private void setUpUI() {
        this.mPrefs = new PreferenceManagement();
        this.etSetUsername = (EditText) findViewById(R.id.etSetUsername);
        this.bSaveUsername = (Button) findViewById(R.id.bSaveUsername);

        bSaveUsername.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mPrefs.saveUsername(getApplicationContext(), etSetUsername.getText().toString());
    }
}
