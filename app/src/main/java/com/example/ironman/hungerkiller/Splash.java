package com.example.ironman.hungerkiller;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ironman.hungerkiller.user_managements.Login;
import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                initActivity();
                finish();
            }
        };
        handler.postDelayed(runnable,2000);
    }


    boolean checkIfSigndIn(){
        FirebaseAuth auth=FirebaseAuth.getInstance();
        return auth.getCurrentUser() != null;
    }

    void initActivity(){
        if(checkIfSigndIn()){
            startActivity(new Intent(this, Init.class));
            finish();
        }else {
            startActivity(new Intent(this, Login.class));
            finish();
        }
    }
}
