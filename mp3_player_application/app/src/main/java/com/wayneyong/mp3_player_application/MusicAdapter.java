package com.wayneyong.mp3_player_application;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    ArrayList<String> list;
    Context mContext;

    public MusicAdapter(ArrayList<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //add to card music design
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_music, parent, false);

        return new MusicViewHolder(view); //pass by below object class defined
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        //printing name of audio file on the screen
        //pass path of audio file
        String filePath = list.get(position);
        Log.e("filepath: ", filePath);

        String title = filePath.substring(filePath.lastIndexOf("/") + 1);
        holder.textViewFileName.setText(title);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MusicActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("filepath", filePath);
                intent.putExtra("position", position);
                intent.putExtra("list", list);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //responsible for defining card music desgin
    public class MusicViewHolder extends RecyclerView.ViewHolder {

        //define matching component in card view design
        private TextView textViewFileName;
        private CardView cardView;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);

            //match component to their id
            textViewFileName = itemView.findViewById(R.id.textViewFileName);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
