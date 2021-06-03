package com.wayneyong.information_book.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.wayneyong.information_book.fragments.FragmentTajMahal;

public class ViewPagerAdapterWonders extends FragmentStateAdapter {

    public ViewPagerAdapterWonders(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;

        //specify order of fragment using position
        switch (position) {
            case 0:
                Log.e("frag0", "Taj Mahal is showing");
                fragment = FragmentTajMahal.newInstance();
                break;

            default:
                return null;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
