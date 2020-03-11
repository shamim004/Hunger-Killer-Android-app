package com.example.ironman.hungerkiller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.example.ironman.hungerkiller.utills.RestaurantsAdapter;
import com.example.ironman.hungerkiller.utills.RestaurantsValueHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AllCartItem extends AppCompatActivity {
    String userUid;
    RecyclerView restaurantsRyc;
    RecyclerView.LayoutManager horizontalLayoutManger;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference restaurantsDatabaseRef;
    ArrayList<RestaurantsValueHolder> restaurantsValueHolderArrayList;
    RestaurantsAdapter restaurantsAdapter;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cart_item);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        userUid =user.getUid();

        restaurantsDatabaseRef =firebaseDatabase.getReference().child("users/"+ userUid+"/cart/");
        restaurantsRyc =findViewById(R.id.ALL_CART_ITEM);

        restaurantsValueHolderArrayList =new ArrayList<>();
        restaurantsAdapter =new RestaurantsAdapter(this,
                restaurantsValueHolderArrayList,
                "AllCart");
        horizontalLayoutManger =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        restaurantsRyc.setLayoutManager(horizontalLayoutManger);
        restaurantsRyc.setAdapter(restaurantsAdapter);
        restaurantsRyc.setNestedScrollingEnabled(false);
        total=findViewById(R.id.total);

        getSuggestionData();
    }

    void getSuggestionData(){
        restaurantsDatabaseRef.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                restaurantsValueHolderArrayList.add(new RestaurantsValueHolder(
                        dataSnapshot.getKey(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getName(),
                        "",
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getUrl(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getPrice(),
                        dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getQuantity()
                ));
                getTotalCount(restaurantsValueHolderArrayList);
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

    void getTotalCount( ArrayList<RestaurantsValueHolder> cartArray){
        int price=0;
        for(RestaurantsValueHolder productValueHolder:cartArray){
            for(int i=0;i<productValueHolder.getQuantity();i++){
                price=price+productValueHolder.getPrice();
            }
        }

        DecimalFormat df = new DecimalFormat("0.00");
        String display_txt="Total cost: "+df.format(price)+" TK";
        total.setText(display_txt);
    }
}
