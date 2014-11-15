package com.app.data;

import com.app.engauge.FirebaseApp;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by jiexicao on 11/14/14.
 */
public class User {
    public static void createUser(String email, String password) {
        Firebase ref = new Firebase(FirebaseApp.FIREBASE_URL);
        ref.createUser(email, password, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // user was created
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }

    public static void loginUser(String email, String password) {
        Firebase ref = new Firebase(FirebaseApp.FIREBASE_URL);
        ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }
}
