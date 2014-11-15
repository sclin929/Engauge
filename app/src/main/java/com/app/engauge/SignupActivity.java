package com.app.engauge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class SignupActivity extends Activity {
    // All views from Login
    EditText firstNameView;
    EditText lastNameView;
    EditText emailView;
    EditText passwordView;
    EditText reenterPasswordView;
    Button signUpButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Connect views to values in xml file using their id's
        firstNameView = (EditText) findViewById(R.id.sign_up_first_name_input);
        lastNameView = (EditText) findViewById(R.id.sign_up_last_name_input);
        emailView = (EditText) findViewById(R.id.sign_up_email_input);
        passwordView = (EditText) findViewById(R.id.sign_up_password_input);
        reenterPasswordView = (EditText) findViewById(R.id.sign_up_reenter_password_input);
        signUpButtonView = (Button) findViewById(R.id.sign_up_button);

        // Data validation for sign up fields.
        addInlineSignUpValidation();

        // Sign up Button Click Listener
        signUpButtonView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                String name = firstNameView.getText().toString() + ' '
                        + lastNameView.getText().toString();
                String email = emailView.getText().toString().toLowerCase();
                String password = passwordView.getText().toString();

                // Validate fields before creating the new user.
                if (emailValidation() && reenterPasswordViewValidation()) {
                    Firebase ref = new Firebase(FirebaseApp.FIREBASE_URL);
                    ref.createUser(email, password, new Firebase.ResultHandler() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(getApplicationContext(),
                                    "Successfully signed up. Please login.",
                                    Toast.LENGTH_LONG)
                                    .show();
                            Intent loginIntent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            Log.i("Signup error", firebaseError.getMessage());
                            Toast.makeText(getApplicationContext(),
                                    "Error signing up. Please try again.",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean emailValidation() {
        String email = (emailView.getText().toString()).toLowerCase();

        if (email.length() == 0) {
            emailView.setError("Contact is a required field.");
            return false;
        } else {
            emailView.setError(null);
        }

        return true;
    }

    public boolean reenterPasswordViewValidation() {
        String reenterPassword = reenterPasswordView.getText().toString();
        String password = passwordView.getText().toString();

        if (reenterPassword.length() == 0) {
            reenterPasswordView.setError("Please confirm your password.");
            return false;
        } else if (!reenterPassword.equals(password)){
            reenterPasswordView.setError("Password confirmation does not match password.");
            return false;
        } else {
            reenterPasswordView.setError(null);
        }

        return true;
    }

    public void addInlineSignUpValidation() {
        emailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                // Do nothing.
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                // Do nothing.
            }

            public void afterTextChanged(Editable edt) {
                emailValidation();
            }
        });

        reenterPasswordView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                // Do nothing.
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                // Do nothing.
            }

            public void afterTextChanged(Editable edt) {
                reenterPasswordViewValidation();
            }
        });
    }
}
