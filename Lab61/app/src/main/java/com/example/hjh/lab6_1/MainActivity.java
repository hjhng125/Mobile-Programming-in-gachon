package com.example.hjh.lab6_1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 익스터널 스토리지
 * */
public class MainActivity extends AppCompatActivity {
    Button writeBtn, readBtn, clearBtn, finishBtn;
    EditText txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeBtn = findViewById(R.id.writefile);
        readBtn = findViewById(R.id.readfile);
        clearBtn = findViewById(R.id.clearscreen);
        finishBtn = findViewById(R.id.finishapp);
        txtData = findViewById(R.id.txtData);
        File sdCard = Environment.getExternalStorageDirectory();
        final File directory = new File(sdCard.getAbsolutePath() + "/Lab");
        directory.mkdirs();

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(new File(directory,"mysdfile.txt")));
                    outputStreamWriter.append(txtData.getText());
                    outputStreamWriter.close();
                    Toast.makeText(getApplicationContext(),"Done writing SD 'mysdfile.txt'" ,Toast.LENGTH_SHORT).show();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BufferedReader fIn = new BufferedReader(new InputStreamReader(new FileInputStream(new File(directory,"mysdfile.txt"))));
                    String aBuffer = "";
                    String data = "";
                    while((data = fIn.readLine()) != null){
                        aBuffer += data + "\n";
                    }
                    Log.d("buffer",aBuffer);
                    txtData.setText(aBuffer);
                    fIn.close();
                    Toast.makeText(getApplicationContext(),"Done reading SD 'mysdfile.txt'" ,Toast.LENGTH_SHORT).show();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });


        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtData.setText(null);
            }
        });
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
