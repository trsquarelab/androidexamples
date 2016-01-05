package com.example.recycleviewexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ClickableFruitAdapter extends RecyclerView.Adapter<ClickableFruitAdapter.ViewHolder> {
    private FruitItemClickedListener mListener;

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

    public ClickableFruitAdapter(FruitData[] desc, FruitItemClickedListener l) {
        for (FruitData d: desc) {
            mFruits.add(d);
        }
        mListener = l;
    }

    public void addFruit(int pos, FruitData data) {
        mFruits.add(pos, data);
        notifyItemInserted(pos);
    }

    public FruitData removeFruit(int pos) {
        FruitData d = mFruits.get(pos);
        mFruits.remove(pos);
        notifyItemRemoved(pos);
        return d;
    }

    public FruitData changeFruit(int pos, FruitData data) {
        FruitData d = mFruits.get(pos);
        mFruits.set(pos, data);
        notifyItemChanged(pos);
        return d;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit, parent, false);
        return new ViewHolder(v);
    }

    public static class NameClickListener implements View.OnClickListener {

        private ViewHolder mHolder;
        private FruitItemClickedListener mListener;

        public NameClickListener(ViewHolder holder, FruitItemClickedListener listener) {
            mHolder = holder;
            mListener = listener;
        }

        @Override
        public void onClick(View view) {
            if (mHolder.getLayoutPosition() >= 0) {
                mListener.onClicked(mHolder.getLayoutPosition());
            }
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FruitData fruit = mFruits.get(position);
        holder.mNameText.setText(fruit.mName);
        holder.mVitaminCText.setText(fruit.mVitaminC);

        holder.itemView.setOnClickListener(new NameClickListener(holder, mListener));
    }

    @Override
    public int getItemCount() {
        return mFruits.size();
    }

}
