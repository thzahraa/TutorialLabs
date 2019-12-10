package com.example.tutorial4;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityRegist extends Activity {
    private EditText mViewUser, mViewPass, mViewRepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        mViewUser = (EditText)findViewById(R.id.et_emailSignup);
        mViewPass = (EditText)findViewById(R.id.et_passSignup);
        mViewRepass = (EditText)findViewById(R.id.et_passSignup2);

        mViewRepass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL){
                    razia();
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.btn_signupSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                razia();
            }
        });
    }

    private void razia(){
        mViewUser.setError(null);
        mViewPass.setError(null);
        mViewRepass.setError(null);

        View fokus = null;
        boolean cancel = false;

        String user = mViewUser.getText().toString();
        String pass = mViewPass.getText().toString();
        String repass = mViewRepass.getText().toString();

        if (TextUtils.isEmpty(user)){
            mViewUser.setError("This field is required");
            fokus = mViewUser;
            cancel = true;
        }else if(cekUser(user)){
            mViewUser.setError("This Username is already exist");
            fokus = mViewUser;
            cancel = true;
        }

        if (TextUtils.isEmpty(pass)){
            mViewPass.setError("This field is required");
            fokus = mViewPass;
            cancel = true;
        }else if (!cekPass(pass,repass)){
            mViewRepass.setError("This password is incorrect");
            fokus = mViewRepass;
            cancel = true;
        }

        if (cancel){
            fokus.requestFocus();
        }else {
            Preferences.setRegisteredUser(getBaseContext(),user);
            Preferences.setRegisteredPass(getBaseContext(),pass);
            finish();
        }
    }

    private boolean cekPass(String pass, String repass){
        return pass.equals(repass);
    }

    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}