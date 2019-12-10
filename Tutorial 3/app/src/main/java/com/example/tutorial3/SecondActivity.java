package com.example.tutorial3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends Activity {
    private TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        header = (TextView)findViewById(R.id.txtHeader);
    }
    public void returnReply(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
