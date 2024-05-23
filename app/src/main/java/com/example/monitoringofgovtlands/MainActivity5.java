package com.example.monitoringofgovtlands;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity5 extends AppCompatActivity {
    Button Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Back = findViewById(R.id.backbutton);
        ImageView imageView = findViewById(R.id.imageView);
        //Retrieve the jpeg image link from the Intent extra
        String imageLink = getIntent().getStringExtra("imageLink");

//Create a Bitmap object from the jpeg image
        Bitmap bitmap = BitmapFactory.decodeFile(imageLink);

//Display the Bitmap object in an ImageView widget

        imageView.setImageBitmap(bitmap);


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity5.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }
}