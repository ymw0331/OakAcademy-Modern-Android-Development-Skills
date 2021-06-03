package com.wayneyong.information_book.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.wayneyong.information_book.adapters.AdapterClass;
import com.wayneyong.information_book.ModelClass;
import com.wayneyong.information_book.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ModelClass> arrayList;
    private AdapterClass adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));//vertical or horizontal ans no of coloum

        arrayList = new ArrayList<>();

        //create object in model class, in array
        ModelClass modelClass1 = new ModelClass("countries", "The Countries");
        ModelClass modelClass2 = new ModelClass("leaders", "The Leaders");
        ModelClass modelClass3 = new ModelClass("museums", "The Museums");
        ModelClass modelClass4 = new ModelClass("wonders", "Seven Wonders of the World");

        //transfer all of the objects into an arrayList
        arrayList.add(modelClass1);
        arrayList.add(modelClass2);
        arrayList.add(modelClass3);
        arrayList.add(modelClass4);


        adapter = new AdapterClass(arrayList, this);
        recyclerView.setAdapter(adapter);


    }
}