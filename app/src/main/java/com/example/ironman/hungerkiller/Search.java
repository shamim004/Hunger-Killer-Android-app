package com.example.ironman.hungerkiller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    RecyclerView restaurantsRyc;
    RecyclerView.LayoutManager horizontalLayoutManger;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference restaurantsDatabaseRef;
    ArrayList<RestaurantsValueHolder> restaurantsValueHolderArrayList;
    RestaurantsAdapter restaurantsAdapter;


    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchEditText=findViewById(R.id.search);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();

        restaurantsDatabaseRef =firebaseDatabase.getReference().child("allProducts/deep/deepper");
        restaurantsRyc =findViewById(R.id.SEARCH);

        restaurantsValueHolderArrayList =new ArrayList<>();
        restaurantsAdapter =new RestaurantsAdapter(this,
                restaurantsValueHolderArrayList,
                "Search");
        horizontalLayoutManger =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        restaurantsRyc.setLayoutManager(horizontalLayoutManger);

        restaurantsRyc.setAdapter(restaurantsAdapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String mainText=searchEditText.getText().toString();
                search(mainText);
//                if(mainText.length()<2){
//                    search(mainText.toUpperCase());
//                }else {
//                    String cap=mainText.substring(0, 1).toUpperCase() + mainText.substring(1);
//                    search(cap);
//                }
            }
        });
//        getSuggestionData();
    }

    void search(String queryText){
        restaurantsValueHolderArrayList.clear();

        DatabaseReference myRef;
        FirebaseDatabase database;
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference();
        myRef=myRef.child("allProducts/");

        //Firebese search
        Query name=myRef.orderByChild("name").limitToFirst(10).startAt(queryText).endAt(queryText+"\uf8ff");

        name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child:dataSnapshot.getChildren()){
                    restaurantsValueHolderArrayList.add(new RestaurantsValueHolder(
                            child.getKey(),
                            child.getValue(DummyRestaurentsValueHolder.class).getName(),
                            child.getValue(DummyRestaurentsValueHolder.class).getInfo(),
                            child.getValue(DummyRestaurentsValueHolder.class).getUrl(),
                            child.getValue(DummyRestaurentsValueHolder.class).getPrice(),
                            dataSnapshot.getValue(DummyRestaurentsValueHolder.class).getQuantity()
                    ));
//                    restaurantsAdapter.notifyDataSetChanged();
                }
                restaurantsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error!!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
