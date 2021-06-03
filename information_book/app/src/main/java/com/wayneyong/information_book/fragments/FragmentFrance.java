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


public class FragmentFrance extends Fragment {

    public static FragmentFrance newInstance() {
        return new FragmentFrance();
    }

    private ImageView imageViewFrance;
    private ProgressBar progressBarFrance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fargment_france, container, false);

        imageViewFrance = view.findViewById(R.id.imageViewFrance);
        progressBarFrance = view.findViewById(R.id.progressBarFrance);

        //get country flag from wikipedia and show in imageView
        Picasso.get()
                .load("https://upload.wikimedia.org/wikipedia/en/c/c3/Flag_of_France.svg")
                .into(imageViewFrance, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBarFrance.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        progressBarFrance.setVisibility(View.INVISIBLE);
                    }
                });

        return view;
    }
}
