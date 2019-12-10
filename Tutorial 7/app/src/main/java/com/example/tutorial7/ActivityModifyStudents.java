package com.example.tutorial7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityModifyStudents extends Activity implements View.OnClickListener {
    private EditText kelasTxt;
    private EditText namaTxt;
    private Button updateBtn, deleteBtn;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Data");
        setContentView(R.layout.activity_modifydata);
        dbManager = new DBManager(this);
        dbManager.open();

        kelasTxt = (EditText) findViewById(R.id.kelas_edTxt);
        namaTxt = (EditText) findViewById(R.id.nama_edTxt);
        updateBtn = (Button)findViewById(R.id.btnUpdate);
        deleteBtn = (Button)findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String kelas = intent.getStringExtra("kelas");
        String nama = intent.getStringExtra("nama");

        _id = Long.parseLong(id);
        kelasTxt.setText(kelas);
        namaTxt.setText(nama);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnUpdate:
                String kelas = kelasTxt.getText().toString();
                String nama = namaTxt.getText().toString();
                dbManager.update(_id,kelas,nama);
                this.returnHome();
                break;
            case R.id.btnDelete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome(){
        Intent home_intent = new Intent(getApplicationContext(),
                ActivityDataStudents.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
