package com.example.ironman.hungerkiller.utills;

import com.google.firebase.database.FirebaseDatabase;

public class OfflineCap extends android.app.Application  {
    @Override
    public void onCreate() {
        super.onCreate();
        /* Enable disk persistence  */
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
