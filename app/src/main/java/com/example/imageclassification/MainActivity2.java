package com.example.imageclassification;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity2 extends AppCompatActivity  {

    RecyclerView recyclerView;
    GalleryAdapter galleryAdapter;
    List<String> images;
    TextView gallery_number;

    private static final int MY_READ_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gallery_number = findViewById(R.id.gallery_number);
        recyclerView = findViewById(R.id.recyclerview_gallery_images);

        //izin kontrolü yapıyor
        if (ContextCompat.checkSelfPermission(MainActivity2.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity2.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_READ_PERMISSION_CODE);
        } else {
            loadImages(); //izin varsa buraya gider
        }


    }

    private void loadImages(){
        recyclerView.setHasFixedSize(true); //resmin boyutu sabittir.
        recyclerView.setLayoutManager(new GridLayoutManager(this,4)); //izgara görünümü
        images = ImagesGallery.lisOfImages(this); //images'a ImagesGallery.lisOfImages atmak?
        galleryAdapter = new GalleryAdapter(this, images, new GalleryAdapter.PhotoListener() {
            @Override
            public void onPhotoClick(String path) {
                Toast.makeText(MainActivity2.this,""+path,Toast.LENGTH_SHORT).show();

            }
        });

        recyclerView.setAdapter(galleryAdapter);
        gallery_number.setText("Fotolar("+images.size()+")");
    }






    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==MY_READ_PERMISSION_CODE){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"abc",Toast.LENGTH_SHORT).show();
                loadImages();
            }else{
                Toast.makeText(this,"dkm",Toast.LENGTH_SHORT).show();
            }
        }

    }
}