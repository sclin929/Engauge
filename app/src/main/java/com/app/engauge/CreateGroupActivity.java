package com.app.engauge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.app.data.Group;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by Maury on 11/15/14.
 */
public class CreateGroupActivity extends Activity {
    public static final String TAG = "CreateGroupActivity";
    EditText groupName;
    EditText groupShortDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        groupName = (EditText) findViewById(R.id.group_name);
        groupShortDescription = (EditText) findViewById(R.id.group_short_description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_create_group_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_save_group:
                saveGroup();
                break;
            case R.id.action_settings:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        Intent createGroup = new Intent(CreateGroupActivity.this, HomepageActivity.class);
        startActivity(createGroup);

        return true;
    }

    public void saveGroup() {
        Firebase ref = ((FirebaseApp) getApplication()).FIREBASE_REF;
        Group newGroup = new Group(groupName.getText().toString(),
                groupShortDescription.getText().toString(),
                "123");
        newGroup.saveToFirebase(new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    Log.e(TAG, "Data could not be saved. " + firebaseError.getMessage());

                    Toast toast = Toast.makeText(getApplicationContext(),
                            getString(R.string.toast_unsuccessful_create_group),
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            getString(R.string.toast_successful_create_group),
                            Toast.LENGTH_SHORT);
                    toast.show();

                    Intent createGroup = new Intent(CreateGroupActivity.this, HomepageActivity.class);
                    startActivity(createGroup);
                }
            }
        });
    }
}
