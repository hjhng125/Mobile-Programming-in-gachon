package com.example.hjh.lab6_2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,stNum;
    Button load, store, reset;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        stNum = findViewById(R.id.sn);
        load = findViewById(R.id.load);
        store = findViewById(R.id.store);
        reset = findViewById(R.id.reset);
        sharedPreferences = getSharedPreferences("myPref",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText(sharedPreferences.getString("name",null));
                stNum.setText(sharedPreferences.getString("stNum",null));
                Toast.makeText(getApplicationContext(),"불러오기!",Toast.LENGTH_SHORT).show();
            }
        });
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("name",name.getText().toString());
                editor.putString("stNum",stNum.getText().toString());
                editor.commit();
                Toast.makeText(getApplicationContext(),"저장!",Toast.LENGTH_SHORT).show();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText(null);
                stNum.setText(null);
                editor.clear();
                Toast.makeText(getApplicationContext(),"초기화!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
