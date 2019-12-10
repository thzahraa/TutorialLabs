package com.example.tutorial4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityLogin extends Activity {
    private EditText mViewUser, mViewPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewUser = findViewById(R.id.et_emailSignin);
        mViewPass = findViewById(R.id.et_passSignin);

        mViewUser.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL){
                    razia();
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.btn_signinSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                razia();
            }
        });

        findViewById(R.id.btn_signupSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),ActivityRegist.class));
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        }
    }

    private void razia(){
        mViewUser.setError(null);
        mViewPass.setError(null);
        View fokus = null;
        boolean cancel = false;

        String user = mViewUser.getText().toString();
        String pass = mViewPass.getText().toString();

        if (TextUtils.isEmpty(user)){
            mViewUser.setError("This field is required");
            fokus = mViewUser;
            cancel = true;
        }else if (!cekUser(user)){
            mViewUser.setError("This username is not found");
            fokus = mViewUser;
            cancel = true;
        }

        if (TextUtils.isEmpty(pass)){
            mViewPass.setError("This field is required");
            fokus = mViewPass;
            cancel = true;
        }else if (!cekPass(pass)){
            mViewPass.setError("This password is incorrect");
            fokus = mViewPass;
            cancel = true;
        }

        if (cancel) fokus.requestFocus();
        else masuk();
    }

    private void masuk(){
        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(),MainActivity.class));
        finish();
    }

    private boolean cekPass(String pass){
        return pass.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}
