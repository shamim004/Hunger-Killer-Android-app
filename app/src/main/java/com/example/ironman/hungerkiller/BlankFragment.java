package com.example.ironman.hungerkiller;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class BlankFragment extends FragmentActivity {


    Context context;

    public BlankFragment(){

    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_blank, container, false);
//    }

    void openFragment(Fragment fragment){
        Toast.makeText(context,"Frag",Toast.LENGTH_LONG).show();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
//
//        FragmentManager transaction = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = transaction.beginTransaction();
//        fragmentTransaction.replace(R.id.container, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
    }
}
