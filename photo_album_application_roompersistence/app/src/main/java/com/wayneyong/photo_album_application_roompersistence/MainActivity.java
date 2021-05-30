package com.wayneyong.photo_album_application_roompersistence;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyImagesViewModel myImagesViewModel;

    private RecyclerView rv;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);


        rv.setLayoutManager(new LinearLayoutManager(this));

        MyImagesAdapter adapter = new MyImagesAdapter();
        rv.setAdapter(adapter);

        myImagesViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(MyImagesViewModel.class);
        myImagesViewModel.getAllImages().observe(MainActivity.this, new Observer<List<MyImages>>() {
            @Override
            public void onChanged(List<MyImages> myImages) {
                adapter.setImagesList(myImages);


            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddImageActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, 3);
                //need req code from here to display data to get db to the string
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                //drag and drop
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                //determine which data user want to delete
                myImagesViewModel.delete(adapter.getPosition(viewHolder.getAdapterPosition()));
                //if use viewHolder.getAdapterPosi only will get integer, but we need to get myImage object instead
            }
        }).attachToRecyclerView(rv);

        adapter.setListener(new MyImagesAdapter.onImageClickListener() {
            @Override
            public void onImageClick(MyImages myImages) {
                Intent intent = new Intent(MainActivity.this, UpdateImageActivity.class);
                //make changes over the existing id
                //send data to the compontent to updateImageActivity
                intent.putExtra("id", myImages.getImage_id());
                intent.putExtra("title", myImages.getImage_title());
                intent.putExtra("description", myImages.getImage_description());
                intent.putExtra("image", myImages.getImage());
                startActivityForResult(intent, 4);
            }
        });
    }

    //receive data from save button clicked
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3 && resultCode == RESULT_OK && data != null) {

            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            byte[] image = data.getByteArrayExtra("image");

            MyImages myImages = new MyImages(title, description, image);
            myImagesViewModel.insert(myImages);
        }

        if (requestCode == 4 && resultCode == RESULT_OK && data != null) {

            String title = data.getStringExtra("updatedTitle");
            String description = data.getStringExtra("updatedDescription");
            byte[] image = data.getByteArrayExtra("image");
            int id = data.getIntExtra("id", -1);

            MyImages myImages = new MyImages(title, description, image);
            myImages.setImage_id(id);
            myImagesViewModel.update(myImages);
        }
    }
}