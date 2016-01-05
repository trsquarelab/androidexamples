package com.example.databindingrecyclerview;

import android.databinding.ObservableArrayList;
import android.view.View;

public class AndroidInfoList {
    public ObservableArrayList<AndroidInfo> list = new ObservableArrayList<>();
    private int mTotalCount;

    public AndroidInfoList() {
        for (mTotalCount =1; mTotalCount <11; ++mTotalCount) {
            add(new AndroidInfo(android.R.drawable.sym_def_app_icon, "icon_" + (mTotalCount)));
        }
    }

    public void add(View v) {
        list.add(new AndroidInfo(android.R.drawable.sym_def_app_icon, "icon_" + mTotalCount++));
    }

    public void remove(View v) {
        if (!list.isEmpty()) {
            list.remove(0);
        }
    }

    private void add(AndroidInfo info) {
        list.add(info);
    }

}
