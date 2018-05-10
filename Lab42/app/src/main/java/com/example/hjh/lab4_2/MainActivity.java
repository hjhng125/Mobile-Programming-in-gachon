package com.example.hjh.lab4_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout page;
    Button btn;
    Animation translate_left; //variable for move to left
    Animation translage_right; //variable for move to right
    boolean isPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = findViewById(R.id.page);
        btn = findViewById(R.id.btn);
        translate_left = AnimationUtils.loadAnimation(this, R.anim.translate);
        translage_right = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingAnimationListener listener = new SlidingAnimationListener();
        translate_left.setAnimationListener(listener);
        translage_right.setAnimationListener(listener);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPage) { //if true, slide is moved to right
                    page.startAnimation(translage_right);
                } else { //if false, slide is moved to left
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(translate_left);
                }
            }
        });
    }
    // because implements, all method is overrided
    class SlidingAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isPage) {
                page.setVisibility(View.INVISIBLE);
                btn.setText("Open Page");
                isPage = false;
            }
            else{
                btn.setText("Close Page");
                isPage = true;
            }
        }
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
