package com.example.tutorial7;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ActivityExternal extends Activity {
    EditText inputTxt;
    TextView response;
    Button saveBtn, readBtn;

    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    String myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        inputTxt = (EditText) findViewById(R.id.myInputTxt);
        response = (TextView) findViewById(R.id.response);
        saveBtn = (Button) findViewById(R.id.saveExtern);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile);
                    fos.write(inputTxt.getText().toString().getBytes());
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                inputTxt.setText("");
                response.setText("SampleFile.txt saved to External Storage");
            }
        });
        readBtn = (Button) findViewById(R.id.getExtern);
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = new FileInputStream(myExternalFile);
                    DataInputStream dis = new DataInputStream(fis);
                    BufferedReader br = new BufferedReader(new InputStreamReader(dis));
                    String strLine;
                    while ((strLine = br.readLine()) != null){
                        myData = myData + strLine;
                    }
                    dis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                inputTxt.setText(myData);
                response.setText("SampleFile.txt data retrieved from Internal Storage");
            }
        });
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            saveBtn.setEnabled(false);
        }else {
            myExternalFile = new File(getExternalFilesDir(filepath),filename);
        }
    }

    private static boolean isExternalStorageAvailable(){
        String extStrgState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStrgState)){
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageReadOnly(){
        String extStrgState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStrgState)){
            return true;
        }
        return false;
    }
}
