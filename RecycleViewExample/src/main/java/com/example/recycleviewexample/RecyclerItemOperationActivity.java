package com.example.recycleviewexample;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class RecyclerItemOperationActivity extends AppCompatActivity implements FruitItemClickedListener {
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    protected ArrayList<FruitData> mExtraFruits = new ArrayList<FruitData>();
    protected ClickableFruitAdapter mAdapter;
    protected int mMode = R.id.opAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main2);

        mRecyclerView  = (RecyclerView)findViewById(R.id.alphaList);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ClickableFruitAdapter(FruitData.getList1(), this);
        mRecyclerView.setAdapter(mAdapter);

        for (FruitData d: FruitData.getList2()) {
            mExtraFruits.add(d);
        }

        RadioGroup opGroup = (RadioGroup)findViewById(R.id.opGroup);
        opGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                mMode = i;
            }
        });

    }

    @Override
    public void onClicked(int pos) {
        if (mMode == R.id.opAdd) {
            Log.d(Common.Tag, "onClicked : " + pos + " Operation : Add");
            if (mExtraFruits.size() > 0) {
                mAdapter.addFruit(pos, mExtraFruits.get(0));
                mExtraFruits.remove(0);
            }
        } else if (mMode == R.id.opRemove) {
            mExtraFruits.add(mAdapter.removeFruit(pos));
            Log.d(Common.Tag, "onClicked : " + pos + " Operation : Remove");
        } else if (mMode == R.id.opChange) {
            Log.d(Common.Tag, "onClicked : " + pos + " Operation : Change");
            if (mExtraFruits.size() > 0) {
                FruitData d = mExtraFruits.get(0);
                mExtraFruits.add(mAdapter.changeFruit(pos, d));
                mExtraFruits.remove(0);
                mExtraFruits.add(d);
            }
        }
    }
}
