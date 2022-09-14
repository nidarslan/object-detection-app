package com.example.imageclassification;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;


public class ImagesGallery {

    public static ArrayList<String> lisOfImages(Context context) {
        Uri image_uri;
        Cursor cursor; //sorgu sonuclari gibi
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage; //görüntü yolu?
        image_uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;//birincil uri yi oluşturduğumuz uriye attık

        String[] projection = {MediaStore.MediaColumns.DATA, //Diskteki medya öğesinin mutlak dosya sistemi yolu.?
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME}; //Bu medya öğesinin birincil paket görünen adı.

        String orderBy = MediaStore.Video.Media.DATE_TAKEN;//TARİH?
        cursor = context.getContentResolver().query(image_uri, projection, null,
                null, orderBy +" DESC"); //azalan sırada liste görünümü için veritabanından kaydı okuma yöntemi

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//Verilen sütun adı için sıfır tabanlı dizini döndürür
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            listOfAllImages.add(absolutePathOfImage);

        }
        return  listOfAllImages;
    }

}
