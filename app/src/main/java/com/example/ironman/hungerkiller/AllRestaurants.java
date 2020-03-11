package com.example.ironman.hungerkiller;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.ironman.hungerkiller.utills.RestaurantsAdapter;
import com.example.ironman.hungerkiller.utills.RestaurantsValueHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AllRestaurants extends AppCompatActivity {

    RecyclerView restaurantsRyc;
    RecyclerView.LayoutManager horizontalLayoutManger;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference restaurantsDatabaseRef;
    ArrayList<RestaurantsValueHolder> restaurantsValueHolderArrayList;
    RestaurantsAdapter restaurantsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();

        restaurantsDatabaseRef =firebaseDatabase.getReference().child("restaurants");
        restaurantsRyc =findViewById(R.id.RESTAURANTS_RYC);

        restaurantsValueHolderArrayList =new ArrayList<>();
        restaurantsAdapter =new RestaurantsAdapter(this,
                restaurantsValueHolderArrayList,
                "Category");
        horizontalLayoutManger =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        restaurantsRyc.setLayoutManager(horizontalLayoutManger);

        restaurantsRyc.setAdapter(restaurantsAdapter);
        getSuggestionData();


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!checkInternet()){
            Toast.makeText(this,"Internet is not connected",Toast.LENGTH_LONG).show();
        }
    }

    void getSuggestionData(){
        restaurantsDatabaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                restaurantsValueHolderArrayList.add(dataSnapshot.getValue(RestaurantsValueHolder.class));

                restaurantsValueHolderArrayList.add(new RestaurantsValueHolder(
                        dataSnapshot.getKey(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getName(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getInfo(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getUrl(),
                        0,
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getQuantity()
                ));
                restaurantsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    boolean checkInternet(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
        return connected;
    }

    public void gotoSearch(View view) {
        startActivity(new Intent(this,Search.class));
    }
}
