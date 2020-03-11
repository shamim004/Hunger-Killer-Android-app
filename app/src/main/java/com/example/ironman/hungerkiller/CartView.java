package com.example.ironman.hungerkiller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class CartView extends AppCompatActivity {

    String info,name,url;
    int price;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    EditText quantity;
    EditText location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_view);

        info =getIntent().getStringExtra("info");
        price =getIntent().getIntExtra("price",0);
        name=getIntent().getStringExtra("name");
        url=getIntent().getStringExtra("url");

        quantity=findViewById(R.id.input_quantity);
        location=findViewById(R.id.input_location);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference();
        user=firebaseAuth.getCurrentUser();

    }

    public void addToCart(View view) {
        buyThisItem();
    }

    void buyThisItem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Add to cart?");

        builder.setPositiveButton("Add to cart", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                buyItem();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    void buyItem(){
        int i=1;
        HashMap orderInfo=new HashMap();
        String demoString=quantity.getText().toString();
        if(!demoString.isEmpty()){
            i= Integer.parseInt(demoString);
        }
        orderInfo.put("info",info);
        orderInfo.put("price",price);
        orderInfo.put("quantity",i);
        orderInfo.put("name",name);
        orderInfo.put("url",url);
        orderInfo.put("location",location.getText().toString());


        databaseReference.child("users").child(user.getUid()).child("cart").push().setValue(orderInfo);
        startActivity(new Intent(this,AllCartItem.class));
    }

}
