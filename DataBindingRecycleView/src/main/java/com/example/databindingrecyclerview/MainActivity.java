package com.example.databindingrecyclerview;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.databindingrecyclerview.databinding.MainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainBinding binding = DataBindingUtil.setContentView(this, R.layout.main);

        // Set the infos heading
        binding.setHeading(new ListHeading("List Heading"));

        // Set list items
        AndroidInfoList infos = new AndroidInfoList();
        binding.setInfos(infos);
    }
}
