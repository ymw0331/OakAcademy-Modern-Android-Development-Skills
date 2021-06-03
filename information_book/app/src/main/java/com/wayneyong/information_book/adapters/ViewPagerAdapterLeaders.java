package com.wayneyong.information_book.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.wayneyong.information_book.fragments.FragmentFrance;
import com.wayneyong.information_book.fragments.FragmentItaly;
import com.wayneyong.information_book.fragments.FragmentUnitedKingdom;

public class ViewPagerAdapterLeaders extends FragmentStateAdapter {

    public ViewPagerAdapterLeaders(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;

        //specify order of fragment using position
        switch (position) {
            case 0:
                Log.e("leader0", "UK is showing");
                fragment = FragmentUnitedKingdom.newInstance();
                break;
            case 1:
                Log.e("leader1", "France is showing");
                fragment = FragmentFrance.newInstance();
                break;
            case 2:
                Log.e("leader2", "Italy is showing");
                fragment = FragmentItaly.newInstance();
                break;
            default:
                return null;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
