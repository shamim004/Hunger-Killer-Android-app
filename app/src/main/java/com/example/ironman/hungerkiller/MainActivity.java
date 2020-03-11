package com.example.ironman.hungerkiller;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ironman.hungerkiller.frag.fragtw;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        bottomNavigationView=findViewById(R.id.nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.container, new BlankFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.home){
//            openFragment(new BlankFragment());
            Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_LONG).show();
            return true;
        }
        else if(menuItem.getItemId()==R.id.account){
            openFragment(new fragtw());
            Toast.makeText(MainActivity.this,"Account",Toast.LENGTH_LONG).show();
            return true;
        }


        return false;
    }

    void openFragment(Fragment fragment){
        Toast.makeText(MainActivity.this,"Frag",Toast.LENGTH_LONG).show();

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
