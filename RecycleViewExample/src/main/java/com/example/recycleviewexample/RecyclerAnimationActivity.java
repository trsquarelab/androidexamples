package com.example.recycleviewexample;

import android.os.Bundle;

public class RecyclerAnimationActivity extends RecyclerItemOperationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mRecyclerView.setItemAnimator(new ScaleInItemAnimator());
    }
}
