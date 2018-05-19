package com.example.hjh.lab52;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView, textView2;
    Button button;
    int result = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        textView =findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new calculateFacto().execute();
            }
        });
    }
    private class calculateFacto extends AsyncTask<Void, Integer, Void>{
        @Override
        protected void onPreExecute() {
            result = 1;
            textView.setText(" ");
        }

        @Override
        protected Void doInBackground(Void... params) {
            int num = Integer.parseInt(editText.getText().toString());

            for(int i = num; i > 0; --i){
                try{
                    Thread.sleep(500);
                    publishProgress(i);
                }catch (Exception e){}
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            textView.append(" "+Integer.toString(values[0].intValue()));
            textView2.setText(" = ?");
            result *= values[0].intValue();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            textView2.setText(" = " + result);
        }
    }

}
