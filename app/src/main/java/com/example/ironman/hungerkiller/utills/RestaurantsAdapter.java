package com.example.ironman.hungerkiller.utills;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ironman.hungerkiller.AllProducts;
import com.example.ironman.hungerkiller.CartView;
import com.example.ironman.hungerkiller.CategoryItem;
import com.example.ironman.hungerkiller.R;
import com.example.ironman.hungerkiller.Splash;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestaurantsAdapter extends RecyclerView.Adapter<ResViewHolder> {
    private ArrayList<RestaurantsValueHolder> restaurantsValueHolderArrayList;
    private Context context;
    private String VIEW_TYPE;
    private StorageReference storageReference;

    public RestaurantsAdapter(Context context,
                              ArrayList<RestaurantsValueHolder> suggestionsValueModelArrayList,
                              String VIEW_TYPE) {
        this.restaurantsValueHolderArrayList = suggestionsValueModelArrayList;
        this.context = context;
        this.VIEW_TYPE =VIEW_TYPE;
        storageReference=FirebaseStorage.getInstance().getReference().child("image");
    }


    @NonNull
    @Override
    public ResViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if(VIEW_TYPE.equals("Cart")){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.list_item_with_cart_button,viewGroup,false);
        }else {
            view= LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.list_item,viewGroup,false);
        }
        return new ResViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ResViewHolder resView, int i) {

        final String name= restaurantsValueHolderArrayList.get(i).getName();
        final String key= restaurantsValueHolderArrayList.get(i).getKey();
        final String info= restaurantsValueHolderArrayList.get(i).getInfo();
        final int price= restaurantsValueHolderArrayList.get(i).getPrice();
        final int q= restaurantsValueHolderArrayList.get(i).getQuantity();

        final String image_url=restaurantsValueHolderArrayList.get(i).getUrl();


        if(!image_url.isEmpty()){
            storageReference.child(image_url).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    // Got the download URL for 'users/me/profile.png'
                    // Pass it to Picasso to download, show in ImageView and caching
                    Picasso.get().load(uri.toString()).into(resView.imageView);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });

        }
        resView.name.setText(name);
        resView.location.setText(info);
        if(VIEW_TYPE.equals("AllCart")){
            resView.location.setText(name+"("+price+")"+" X "+q);
            resView.location.setTextSize(25);
        }

        //click activity for AllRestaurants
        if(VIEW_TYPE.equals("Category")){
            resView.resparents.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,CategoryItem.class)
                            .putExtra("key",key));
                }
            });
        }
        else if(VIEW_TYPE.equals("AllProducts")){
            resView.resparents.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,AllProducts.class)
                            .putExtra("key",key)
                    .putExtra("info",info));
                }
            });
        }
        else if (VIEW_TYPE.equals("Cart")){
            resView.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,CartView.class)
                            .putExtra("info",info)
                    .putExtra("price",price)
                    .putExtra("name",name)
                    .putExtra("url",image_url));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return restaurantsValueHolderArrayList.size();
    }
}
