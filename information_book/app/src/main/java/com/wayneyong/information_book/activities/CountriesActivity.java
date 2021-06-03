package com.wayneyong.information_book.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wayneyong.information_book.R;
import com.wayneyong.information_book.adapters.ViewPagerAdapterCountries;

public class CountriesActivity extends AppCompatActivity {

    private TabLayout tabLayoutCountries;
    private ViewPager2 viewPagerCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        tabLayoutCountries = findViewById(R.id.tabLayoutCountries);
        viewPagerCountries = findViewById(R.id.viewPagerCountries);

        ViewPagerAdapterCountries adapter = new ViewPagerAdapterCountries(getSupportFragmentManager(), getLifecycle());

        viewPagerCountries.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayoutCountries, viewPagerCountries
                , true, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position) {
                    case 0:
                        Log.e("tag0", "UK is showing");
                        tab.setText("United Kingdom");
                        break;
                    case 1:
                        Log.e("tag1", "France is showing");
                        tab.setText("France");
                        break;
                    case 2:
                        Log.e("tag2", "Italy is showing");
                        tab.setText("Italy");
                        break;
                }
            }
        });

        tabLayoutMediator.attach();
    }
}