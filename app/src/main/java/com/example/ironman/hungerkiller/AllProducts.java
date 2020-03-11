package com.example.ironman.hungerkiller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class AllProducts extends AppCompatActivity {
    String key;
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
        setContentView(R.layout.all_products);
        if(getIntent().getStringExtra("key")==null){
            key="";
        }else {
            key =getIntent().getStringExtra("key");
        }


        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();

        restaurantsDatabaseRef =firebaseDatabase.getReference().child("allProducts");
        restaurantsRyc =findViewById(R.id.ALL_PRODUCT);

        restaurantsValueHolderArrayList =new ArrayList<>();
        restaurantsAdapter =new RestaurantsAdapter(this,
                restaurantsValueHolderArrayList,
                "Cart");
        horizontalLayoutManger =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        restaurantsRyc.setLayoutManager(horizontalLayoutManger);
        restaurantsRyc.setAdapter(restaurantsAdapter);
//
//        if(getIntent().getStringExtra("key").isEmpty()){
//            key="";
//            getSuggestionData();
//        }else {
//            key =getIntent().getStringExtra("key");
//            getAll("");
//        }
////        getSuggestionData();
        getAll(key);
    }

    void getSuggestionData(){
        restaurantsDatabaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                restaurantsValueHolderArrayList.add(new RestaurantsValueHolder(
                        dataSnapshot.getKey(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getName(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getInfo(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getUrl(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getPrice(),
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
    void getAll(String restaurant){
        restaurantsDatabaseRef.orderByChild("res").startAt(restaurant).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                restaurantsValueHolderArrayList.add(new RestaurantsValueHolder(
                        dataSnapshot.getKey(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getName(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getInfo(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getUrl(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getPrice(),
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
}
