package com.example.sanyogghosh.coursify;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Introduction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);


        Typeface a = Typeface.createFromAsset(getAssets(),"fonts/robotoslabbold.ttf");

        Typeface b = Typeface.createFromAsset(getAssets(),"fonts/robotoslablight.ttf");

        TextView heading = (TextView)findViewById(R.id.coursify);

        TextView heading_line = (TextView)findViewById(R.id.line);

        heading.setTypeface(a);

        heading_line.setTypeface(b);

        FrameLayout frm = (FrameLayout)findViewById(R.id.main);

        frm.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));

        new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                Intent i = new Intent(getApplicationContext(),First2.class);
                startActivity(i);
            }
        }.start();

    }
}
