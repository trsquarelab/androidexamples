package com.example.databindingrecyclerview;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ListView;

public class ListBinder {

    @BindingAdapter("bind:imageRes")
    public  static void bindImage(ImageView view, int r) {
        view.setImageResource(r);
    }

    @BindingAdapter("bind:items")
    public  static void bindList(RecyclerView view, ObservableArrayList<AndroidInfo> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(new AndroidInfoAdapter(list));
    }
}
