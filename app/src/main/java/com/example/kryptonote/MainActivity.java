package com.example.kryptonote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public File getFilesDir() {
        return getFilesDir();
    }

    public void onEncrypt(View v) {
        EditText keyView = (EditText) findViewById(R.id.key);
        String key = keyView.getText().toString();
        EditText dataView = (EditText) findViewById(R.id.data);
        String note = dataView.getText().toString();
        Cipher myModel = new Cipher(key);

        String res = myModel.encrypt(note);
        dataView.setText(res);


    }

    public void onDecrypt(View v) {
        EditText keyView = (EditText) findViewById(R.id.key);
        String key = keyView.getText().toString();
        EditText dataView = (EditText) findViewById(R.id.data);
        String note = dataView.getText().toString();
        Cipher myModel = new Cipher(key);

        String res = myModel.decrypt(note);
        dataView.setText(res);

    }

    public void onSave(View v) {
        try {
           // Log.i("Log1", "dir" );
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            //Log.i("Log2", "ok" );
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText) findViewById(R.id.data)).getText().toString());
            fw.close();
            Toast.makeText(this, "Note saved.", Toast.LENGTH_LONG);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
    }

    public void onLoad(View v) {
        try {

            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileReader fr = new FileReader(file);
            String show = "";
            for (int c = fr.read(); c != -1; c = fr.read()) {
                show += (char) c;
            }
            fr.close();
            ((EditText) findViewById(R.id.file)).setText(show);
            Toast.makeText(this, "Note loaded.", Toast.LENGTH_LONG);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }

    }
}
