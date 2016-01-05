package com.example.recycleviewexample;

import android.os.Bundle;

public class RecyclerDecoratorActivity extends RecyclerAnimationActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView.addItemDecoration(new SimpleItemDecorator(this));
    }
}
