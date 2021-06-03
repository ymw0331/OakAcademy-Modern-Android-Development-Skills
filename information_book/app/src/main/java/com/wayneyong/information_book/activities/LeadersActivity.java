package com.wayneyong.information_book.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wayneyong.information_book.R;
import com.wayneyong.information_book.adapters.ViewPagerAdapterLeaders;

public class LeadersActivity extends AppCompatActivity {

    private TabLayout tabLayoutLeaders;
    private ViewPager2 viewPagerLeaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaders);

        tabLayoutLeaders = findViewById(R.id.tabLayoutLeaders);
        viewPagerLeaders = findViewById(R.id.viewPagerLeaders);

        ViewPagerAdapterLeaders adapter = new ViewPagerAdapterLeaders(getSupportFragmentManager(), getLifecycle());

        viewPagerLeaders.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayoutLeaders, viewPagerLeaders,
                true, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position) {
                    case 0:
                        tab.setText("Julius Caesar");
                        break;
                    case 1:
                        tab.setText("Alexander The Great");
                        break;
                    case 2:
                        tab.setText("Napoleon");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();
    }
}