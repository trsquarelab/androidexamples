package com.example.collapsiblemenu;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private static final long AnimDuration = 500;

    private boolean isMenuOpen = false;
    private LinearLayout mLinearLayout;

    private float mPivoteX;
    private float mPivoteY;

    private float mFromX;
    private float mFromY;

    private float mToX;
    private float mToY;

    private View currentView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLinearLayout = (LinearLayout)findViewById(R.id.menuLayout);

        findViewById(R.id.menuItemTopCenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentView = view;

                mPivoteX = 0.5f;
                mPivoteY = 0.0f;

                mFromX = 1.0f;
                mToX = 1.0f;

                mFromY = 0.0f;
                mToY = 1.0f;

                startAnimation(mLinearLayout.getVisibility() != View.VISIBLE);
            }
        });

        findViewById(R.id.menuItemTopLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentView = view;

                mPivoteX = 0.0f;
                mPivoteY = 0.0f;

                mFromX = 0.0f;
                mToX = 1.0f;

                mFromY = 0.0f;
                mToY = 1.0f;

                startAnimation(mLinearLayout.getVisibility() != View.VISIBLE);
            }
        });

        findViewById(R.id.menuItemTopRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentView = view;

                mPivoteX = 1.0f;
                mPivoteY = 0.0f;

                mFromX = 0.0f;
                mToX = 1.0f;

                mFromY = 0.0f;
                mToY = 1.0f;

                startAnimation(mLinearLayout.getVisibility() != View.VISIBLE);
            }
        });

        findViewById(R.id.menuItemLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentView = view;

                mPivoteX = 0.0f;
                mPivoteY = 0.5f;

                mFromX = 0.0f;
                mToX = 1.0f;

                mFromY = 1.0f;
                mToY = 1.0f;

                startAnimation(mLinearLayout.getVisibility() != View.VISIBLE);
            }
        });

        findViewById(R.id.menuItemRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentView = view;

                mPivoteX = 1.0f;
                mPivoteY = 0.5f;

                mFromX = 0.0f;
                mToX = 1.0f;

                mFromY = 1.0f;
                mToY = 1.0f;

                startAnimation(mLinearLayout.getVisibility() != View.VISIBLE);
            }
        });
    }

    private void startAnimation(final boolean expanding) {

        float fx = expanding?mFromX:mToX;
        float fy = expanding?mFromY:mToY;

        float tx = expanding?mToX:mFromX;
        float ty = expanding?mToY:mFromY;

        Animation animation = new ScaleAnimation (fx, tx,
                                                  fy, ty,
                                                  Animation.RELATIVE_TO_SELF, mPivoteX,
                                                  Animation.RELATIVE_TO_SELF, mPivoteY);
        animation.setDuration(AnimDuration);
        animation.setFillAfter(true);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (expanding) {
                    mLinearLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!expanding) {
                    // You can as well set the visibility to View.GONE
                    mLinearLayout.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mLinearLayout.startAnimation(animation);
    }
}
