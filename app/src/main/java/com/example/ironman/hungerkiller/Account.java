package com.example.ironman.hungerkiller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ironman.hungerkiller.user_managements.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Account extends AppCompatActivity {
    TextView email;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_view);
        email=findViewById(R.id.email);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        email.setText(user.getEmail());

    }

    public void logoutFromApps(View view) {
        auth.signOut();
//        int pid = android.os.Process.myPid();
//        android.os.Process.killProcess(pid);
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
        finish();
    }
}
