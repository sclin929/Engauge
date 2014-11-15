package com.app.engauge;

import android.app.Application;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by jiexicao on 11/14/14.
 */
public class FirebaseApp extends Application {
    public static String FIREBASE_URL = "https://engauge.firebaseio.com/";

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase(FIREBASE_URL);

        /*
        // Example:
        myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");
        myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
            }

            @Override public void onCancelled(FirebaseError error) { }

        });
        */
    }
}
