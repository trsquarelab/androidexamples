package com.example.helloretrolambda;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int[] mColors = {
        Color.BLACK,
        Color.BLUE,
        Color.CYAN,
        Color.DKGRAY,
        Color.GRAY,
        Color.GREEN,
        Color.LTGRAY,
        Color.MAGENTA,
        Color.RED,
        Color.TRANSPARENT,
        Color.WHITE,
        Color.YELLOW
    };

    private Random mRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRandom = new Random(System.currentTimeMillis());

        findViewById(R.id.changeColorButton).setOnClickListener((v) -> v.getRootView().setBackgroundColor(mColors[mRandom.nextInt(mColors.length)]))
        ;

    }
}
