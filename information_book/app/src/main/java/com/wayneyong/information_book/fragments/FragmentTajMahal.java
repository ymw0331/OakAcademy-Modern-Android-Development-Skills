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


public class FragmentTajMahal extends Fragment {

    public static FragmentTajMahal newInstance() {
        return new FragmentTajMahal();
    }

    private ImageView imageViewTajMahal;
    private ProgressBar progressBarTajMahal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fargment_taj_mahal, container, false);

        imageViewTajMahal = view.findViewById(R.id.imageViewTajMahal);
        progressBarTajMahal = view.findViewById(R.id.progressBarTajMahal);

        //get country flag from wikipedia and show in imageView
        Picasso.get()
                .load("https://upload.wikimedia.org/wikipedia/commons/6/67/Taj_Mahal_in_India_-_Kristian_Bertel.jpg")
                .into(imageViewTajMahal, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBarTajMahal.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        progressBarTajMahal.setVisibility(View.INVISIBLE);
                    }
                });

        return view;
    }
}
