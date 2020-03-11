package com.example.ironman.hungerkiller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ironman.hungerkiller.frag.fragtw;
import com.example.ironman.hungerkiller.user_managements.Login;
import com.google.firebase.auth.FirebaseAuth;


public class Init extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;

//
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.init, container, false);
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init);


        bottomNavigationView=findViewById(R.id.navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);




    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            finish();
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
        }
    }

    public void gotoSearch(View view) {
        startActivity(new Intent(this,Search.class));
    }

    public void gotoTopRes(View view) {
        startActivity(new Intent(this,AllRestaurants.class));
    }

    public void gotoTopFood(View view) {
        startActivity(new Intent(this,AllProducts.class));
    }

    public void gotoTopContact(View view) {
        startActivity(new Intent(this,ContactUs.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.food_list){
//            openFragment(new BlankFragment());
//            Toast.makeText(Init.this,"Home",Toast.LENGTH_LONG).show();
            startActivity(new Intent(Init.this,AllProducts.class));
            return true;
        }
        else if(menuItem.getItemId()==R.id.account){
            startActivity(new Intent(Init.this,Account.class));
            return true;
        }
        else if(menuItem.getItemId()==R.id.cart_history){
            startActivity(new Intent(Init.this,AllCartItem.class));
            return true;
        }
        return false;
    }
}
