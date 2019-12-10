package com.example.tutorial7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAddStudents extends Activity implements View.OnClickListener {
    private Button addTodoBtn;
    private EditText kelasEditTxt;
    private EditText namaEditTxt;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tambah Data");
        setContentView(R.layout.activity_addstudents);

        kelasEditTxt = (EditText) findViewById(R.id.kelas_edTxt);
        namaEditTxt = (EditText) findViewById(R.id.nama_edTxt);
        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add_record:
                final String kelas = kelasEditTxt.getText().toString();
                final String nama = namaEditTxt.getText().toString();

                dbManager.insert(kelas,nama);
                Intent main = new Intent(ActivityAddStudents.this,
                        ActivityAddStudents.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
    }
}
