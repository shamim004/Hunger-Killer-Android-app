package com.example.ironman.hungerkiller.user_managements;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ironman.hungerkiller.Init;
import com.example.ironman.hungerkiller.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText pass;
    EditText email;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        email=findViewById(R.id.l_email);
        pass=findViewById(R.id.l_pass);

        progressDialog=new ProgressDialog(Login.this);
        progressDialog.setTitle("Loading, Please wait");
        progressDialog.setCancelable(false);
        mAuth =FirebaseAuth.getInstance();
    }

    public void goToSignUp(View view) {
        startActivity(new Intent(this,SignUp.class));
    }

    public void log_in(View view) {

        String email=this.email.getText().toString()
                ,pass=this.pass.getText().toString();
        //
        if(!email.isEmpty() && !pass.isEmpty()){
            progressDialog.show();
            loginToServer(email,pass);
        }
        //
        else {
            Toast.makeText(this,"Enter all the field",Toast.LENGTH_LONG).show();
        }
    }

    void loginToServer(String email,String pass){
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            startActivity(new Intent(Login.this,Init.class));
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
