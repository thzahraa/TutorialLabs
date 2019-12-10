package com.example.tutorial3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityInput extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        final EditText firstNum = (EditText)findViewById(R.id.angkaSatu);
        final EditText secNum = (EditText)findViewById(R.id.angkaDua);
        Button btnAdd = (Button)findViewById(R.id.btnTambah);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Memberi nilai ke dua buah variabel yakni num1 dan num2
            // Nilai diambil dari nilai yang dimasukkan pengguna di TextField
                int num1 = Integer.parseInt(firstNum.getText().toString());
                int num2 = Integer.parseInt(secNum.getText().toString());
            // Membuat objek Intent
                Intent intent = new Intent(ActivityInput.this,ActivityOutput.class);
            /* Mendeklarasikan atribut Intent yakni jumlah dengan nilai hasil penjumlahan num1 dan num2 */
                intent.putExtra("jumlah",num1+" + "+num2+" = "+(num1+num2));
            // Menjalankan activity dengan parameter Intent
                startActivity(intent);
            }
        });
    }
}
