package com.example.tutorial3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityIntentFilter extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intentfilter);
        Button btnSend = (Button)findViewById(R.id.sndEmail);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent si = new Intent(Intent.ACTION_SEND);

                si.setType("message/rfc822");
                si.putExtra(Intent.EXTRA_EMAIL, new String[]{"fitrianichaerunisaun@gmail.com"});
                si.putExtra(Intent.EXTRA_SUBJECT, "Welcome to 3SC6-AeU Class");
                si.putExtra(Intent.EXTRA_TEXT, "Hello, Welcome to My Class");

                startActivity(Intent.createChooser(si, "Choose Mail App"));
            }
        });
    }
}