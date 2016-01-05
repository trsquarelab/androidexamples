package com.example.databindingrecyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import com.example.databindingrecyclerview.databinding.ListItemBinding;

public class AndroidInfoAdapter extends RecyclerView.Adapter<AndroidInfoAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ListItemBinding binder;

        public ViewHolder(View v) {
            super(v);
            binder = DataBindingUtil.bind(v);
        }
    }

    private ObservableArrayList<AndroidInfo> list;

    public AndroidInfoAdapter(ObservableArrayList<AndroidInfo> l) {
        list = l;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final AndroidInfo info = list.get(position);
        holder.binder.setInfo(info);
        holder.binder.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
