package com.wayneyong.photo_album_application_roompersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class UpdateImageActivity extends AppCompatActivity {

    private ImageView imageViewUpdateImage;
    private EditText editTextUpdateTitle, editTextUpdateDescription;
    private Button buttonUpdate;

    private String title, description;
    private byte[] image;
    private int id;

    private Bitmap selectedImage;
    private Bitmap scaledImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_image);

        imageViewUpdateImage = findViewById(R.id.imageViewUpdateImage);
        editTextUpdateTitle = findViewById(R.id.editTextUpdateTitle);
        editTextUpdateDescription = findViewById(R.id.editTextUpdateDescription);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        //get data from mainact
        id = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        image = getIntent().getByteArrayExtra("image");

        editTextUpdateTitle.setText(title);
        editTextUpdateDescription.setText(description);
        imageViewUpdateImage.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));

        //send updated to mainact

        imageViewUpdateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imageIntent, 5);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

    }

    public void updateData() {

        //id check
        if (id == -1) {
            Toast.makeText(UpdateImageActivity.this, "There is a problem", Toast.LENGTH_SHORT).show();
        } else {

            String updatedTitle = editTextUpdateTitle.getText().toString();
            String updatedDescription = editTextUpdateDescription.getText().toString();

            Intent intent = new Intent();
            intent.putExtra("id", id);
            intent.putExtra("updatedTitle", updatedTitle);
            intent.putExtra("updatedDescription", updatedDescription);

            if (selectedImage == null) {
                intent.putExtra("image", image);
            } else {
                //convert image
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                scaledImage = makeSmall(selectedImage, 300);
                scaledImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
                byte[] image = outputStream.toByteArray();

                //send the data in variable to mainactivity using intent
                intent.putExtra("image", image);
            }
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    //method to scale image
    public Bitmap makeSmall(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float ratio = (float) width / (float) height;

        if (ratio > 1) {
            width = maxSize;
            height = (int) (width / ratio);
        } else {
            height = maxSize;
            width = (int) (height * ratio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}