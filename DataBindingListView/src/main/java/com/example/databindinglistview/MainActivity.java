package com.example.databindinglistview;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.databindinglistview.databinding.MainBinding;

public class MainActivity extends AppCompatActivity {
    private AndroidInfoList infos;
    int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainBinding binding = DataBindingUtil.setContentView(this, R.layout.main);

        // Set the infos heading
        binding.setHeading(new ListHeading("List Heading"));

        // Set list items
        infos = new AndroidInfoList();
        for (; i<11; ++i) {
            infos.add(new AndroidInfo(android.R.drawable.sym_def_app_icon, "icon_" + (i)));
        }
        binding.setInfos(infos);

        findViewById(R.id.addItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infos.list.add(new AndroidInfo(android.R.drawable.sym_def_app_icon, "icon_" + i++));
            }
        });

        findViewById(R.id.removeItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!infos.list.isEmpty()) {
                    infos.list.remove(0);
                }
            }
        });

    }
}
