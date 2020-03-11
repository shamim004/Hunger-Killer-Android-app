package com.example.ironman.hungerkiller.utills;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ironman.hungerkiller.R;

import pl.droidsonroids.gif.GifImageView;

public class ResViewHolder extends RecyclerView.ViewHolder {
    TextView name,button,location;
    RelativeLayout resparents;
//    ImageView imageView;
    GifImageView imageView;
    public ResViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.res_name);
        resparents=itemView.findViewById(R.id.res_parents);
        button=itemView.findViewById(R.id.cart_button);
        imageView=itemView.findViewById(R.id.image_view);
        location=itemView.findViewById(R.id.location);
    }
}
