package com.wayneyong.information_book.adapters;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.wayneyong.information_book.ModelClass;
import com.wayneyong.information_book.R;
import com.wayneyong.information_book.activities.CountriesActivity;
import com.wayneyong.information_book.activities.LeadersActivity;
import com.wayneyong.information_book.activities.MuseumsActivity;
import com.wayneyong.information_book.activities.WondersActivity;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.CardViewHolder> {

    private ArrayList<ModelClass> modelList;
    private Context context; //show category

    public AdapterClass(ArrayList<ModelClass> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //as soon as cardViewHolder declared, display in recyclerView

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        //what should be when design is cardview is conn|ected to recycler view, display data in recycler view
        //transfer data in array from modelclass to object

        ModelClass model = modelList.get(position);
        holder.textViewSplash.setText(model.getCategoryName());
        holder.imageViewSplash.setImageResource(context.getResources()
                .getIdentifier(model.getImageName(), "drawable", context.getPackageName()));

        //add cardview
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    Intent intent = new Intent(context, CountriesActivity.class);
                    context.startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(context, LeadersActivity.class);
                    context.startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(context, MuseumsActivity.class);
                    context.startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(context, WondersActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        //specify amount of data
        return modelList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        //declare components in cardView
        private ImageView imageViewSplash;
        private TextView textViewSplash;
        private CardView cardView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewSplash = itemView.findViewById(R.id.imageViewSplash);
            textViewSplash = itemView.findViewById(R.id.textViewSplash);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
