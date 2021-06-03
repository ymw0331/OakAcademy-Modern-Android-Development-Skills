package com.wayneyong.information_book.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wayneyong.information_book.R;


public class FragmentUnitedKingdom extends Fragment {

    public static FragmentUnitedKingdom newInstance() {
        return new FragmentUnitedKingdom();
    }

    private ImageView imageViewUnitedKingdom;
    private ProgressBar progressBarUnitedKingdom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fargment_united_kingdom, container, false);

        imageViewUnitedKingdom = view.findViewById(R.id.imageViewUnitedKingdom);
        progressBarUnitedKingdom = view.findViewById(R.id.progressBarUnitedKingdom);

        //get country flag from wikipedia and show in imageView
        Picasso.get()
                .load("https://upload.wikimedia.org/wikipedia/en/a/ae/Flag_of_the_United_Kingdom.svg")
                .into(imageViewUnitedKingdom, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBarUnitedKingdom.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        progressBarUnitedKingdom.setVisibility(View.INVISIBLE);
                    }
                });

        return view;
    }
}
