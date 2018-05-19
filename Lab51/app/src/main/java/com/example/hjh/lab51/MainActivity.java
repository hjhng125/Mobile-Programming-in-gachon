package com.example.hjh.lab51;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1;
    ImageView imageView2;
    EditText editText;
    Button button;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BearThread thread1 = new BearThread(0);
                thread1.start();
                BearThread thread2 = new BearThread(1);
                thread2.start();
            }
        });
    }

    class BearThread extends Thread {
        int stateIndex, dogIndex;

        ArrayList<Integer> images = new ArrayList<Integer>();

        public BearThread(int index) {
            dogIndex = index;
            images.add(R.drawable.action1);
            images.add(R.drawable.action2);
            images.add(R.drawable.action3);
        }

        public void run() {
            stateIndex = 0;
            for (int i = 0; i < 9; ++i) {
                final String msg = "bear #" + dogIndex + " state: " + stateIndex;
                handler.post(new Runnable() {
                    public void run() {
                        editText.append(msg + "\n");
                        if (dogIndex == 0) {
                            imageView1.setImageResource(images.get(stateIndex));
                        } else if (dogIndex == 1) {
                            imageView2.setImageResource(images.get(stateIndex));
                        }
                    }});
                try{
                    int sleepTime = getRandomTime(500,3000);
                    Thread.sleep(sleepTime);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                stateIndex++;
                if(stateIndex >= images.size())
                    stateIndex = 0;
            }
        }
    }
    public int getRandomTime(int min, int max){
        return min+(int)(Math.random() * (max-min));
    }
}
