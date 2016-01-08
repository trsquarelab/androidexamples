package com.example.recycleviewexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BasicFruitAdapter extends RecyclerView.Adapter<BasicFruitAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameText;
        public TextView mVitaminCText;

        public ViewHolder(View v) {
            super(v);
            mNameText = (TextView) v.findViewById(R.id.fruitNameText);
            mVitaminCText = (TextView) v.findViewById(R.id.vitaminCText);
        }
    }

    private ArrayList<FruitData> mFruits = new ArrayList<FruitData>();

    public BasicFruitAdapter(FruitData[] desc) {
        for (FruitData d : desc) {
            mFruits.add(d);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FruitData fruit = mFruits.get(position);
        holder.mNameText.setText(fruit.mName);
        holder.mVitaminCText.setText(fruit.mVitaminC);
    }

    @Override
    public int getItemCount() {
        return mFruits.size();
    }

}
