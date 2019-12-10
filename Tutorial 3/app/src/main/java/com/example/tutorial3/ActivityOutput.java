package com.example.tutorial3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityOutput extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        TextView result = (TextView)findViewById(R.id.vHasil);
        // Mengambil nilai Intent dari activity sebelumnya, yakni Activity1
        Intent intent = getIntent();
        /* Mengambil nilai atribut yang diangkut Intent dari Activity1 lalu disimpan ke sebuah variabel bertipe string dengan nama addition */
        String addition = (String)intent.getSerializableExtra("jumlah");
        // Mengatur nilai TextField result dengan nilai didalam variabel addition
        result.setText(addition);
    }
}
