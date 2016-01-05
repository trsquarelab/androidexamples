package com.example.recycleviewexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Random;

/**
 * A simple item decorator which will,
 * 1. Fill the item view with a Random color from ColorList
 * 2. Draw a dark Gray coloured boarder
 */
public class SimpleItemDecorator extends RecyclerView.ItemDecoration {
    private Paint mPaintFill;    // This Paint object is used for filling the view
    private Paint mPaintStroke;  // This Paint object is used for drawing the boarder

    private Random mRandom = new Random();

    // Item view will be filled with one of the below given colour
    // Colour is selected randomly
    private static final int[] ColorList = {
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.CYAN,
            Color.MAGENTA,
            Color.GRAY};

    public SimpleItemDecorator(Context context) {

        // Create the fill paint
        mPaintFill = new Paint();
        mPaintFill.setStyle(Paint.Style.FILL);

        // Create the stroke paint
        mPaintStroke = new Paint();
        mPaintStroke.setStyle(Paint.Style.STROKE);
        mPaintStroke.setStrokeWidth(5.0f);
        mPaintStroke.setColor(Color.DKGRAY);

        // Set seed for random number generator
        mRandom.setSeed(System.currentTimeMillis());
    }

    // This method is called before the item view is rendered
    // This method will be used for filling the item view
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int childCount = parent.getChildCount();

        // For each child view we would need to calculate the
        // bounds and fill it by drawing a rectangle
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            // Get the bounds
            RectF rect = getBounds(child);

            // Set a random color
            mPaintFill.setColor(ColorList[mRandom.nextInt(ColorList.length)]);

            // Fill the view by drawing a rectangle with rounded corner
            c.drawRoundRect (rect, 20, 20, mPaintFill);
        }
    }

    // This method is called after the item view is rendered
    // This method will be used for drawing the boarder
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int childCount = parent.getChildCount();

        // For each child view we would need to calculate the
        // bounds and fill it by drawing a rectangle
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            // Get the bounds
            RectF rect = getBounds(child);

            // Fill the view by drawing a rectangle with rounded corner
            c.drawRoundRect (rect, 10, 10, mPaintStroke);
        }
    }

    // Calculate the bounds for a child
    private RectF getBounds(View child) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

        int left = child.getPaddingLeft();
        int right = child.getWidth() - child.getPaddingRight();

        int top = child.getTop() - params.topMargin;
        int bottom = child.getBottom() + params.bottomMargin;

        return new RectF(left, top, right, bottom);
    }
}
