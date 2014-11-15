package com.app.engauge;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickLogin(View view) {
        // Extract username and password.
        EditText usernameView = (EditText) view.findViewById(R.id.login_username);
        EditText passwordView = (EditText) view.findViewById(R.id.login_password);
        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();

        // Authenticate user with Firebase.
        Firebase ref = new Firebase("https://engauge.firebaseio.com");
        ref.authWithPassword(username, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                // If user exists and is authenticated, send user to the home page.
                Intent intent = new Intent(
                        LoginActivity.this,
                        HomepageActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Successfully logged in.",
                        Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
                if (firebaseError.getCode() == FirebaseError.INVALID_AUTH_ARGUMENTS) {
                    Toast.makeText(getApplicationContext(),
                            "Invalid email/password combination.",
                            Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Login failed. Please try again.",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        Intent loginIntent = new Intent(LoginActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }

    public void onClickSignUp(View view) {
        Intent signUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(signUpIntent);
    }
}
