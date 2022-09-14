package com.example.imageclassification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Context context;
    private List<String> images;
    protected PhotoListener photoListener;

    public GalleryAdapter(Context context, List<String> images, PhotoListener photoListener){
        this.context =context;
        this.images=images;
        this.photoListener=photoListener;
    }

    //Bu method adaptör oluşturulduğunda oluşturduğumuz ViewHolder'ın başlatılması için çağrılır.
    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.gallery_item,parent,false)
        );
    }

    //nCreateViewHolder’dan dönen verileri bağlama işlemini gerçekleştirildiği metotdur.
    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder holder, int position) {
        String image = images.get(position);

        Glide.with(context).load(image).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoListener.onPhotoClick(image); //yolu yazdırmak
            }
        });
    }
    //getItemCount() : Listemizin eleman sayısını döndüren metottur.
    @Override
    public int getItemCount() {
        return images.size();
    }
    //ViewHolder(): Her bir satırımızın içinde bulunan bileşenleri tanımlama işleminin yapıldığı metottur.
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);

        }
    }
    public interface PhotoListener{
        void onPhotoClick(String path);
    }
}

//https://gelecegiyazanlar.turkcell.com.tr/konu/egitim/android-201/recyclerview-kullanimi

