package com.example.databindinglistview;

import android.databinding.ObservableArrayList;

public class AndroidInfoList {
    public ObservableArrayList<AndroidInfo> list = new ObservableArrayList<>();

    public AndroidInfoList() {
    }

    public void add(AndroidInfo info) {
        list.add(info);
    }

}
