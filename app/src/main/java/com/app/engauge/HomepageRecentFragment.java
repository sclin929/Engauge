package com.app.engauge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.data.Group;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Maury on 11/15/14.
 */
public class HomepageRecentFragment extends Fragment {
    public static final String TAG = "HomepageRecentFragment";

    ListView recentGroupsList;
    GroupArrayAdapter groupArrayAdapter;
    ArrayList<Group> recentGroups;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_homepage_recent, container, false);

        recentGroupsList = (ListView) rootView.findViewById(R.id.recent_groups_list);
        recentGroups = new ArrayList<Group>();

        groupArrayAdapter = new GroupArrayAdapter(getActivity(), R.layout.item_group, recentGroups);
        recentGroupsList.setAdapter(groupArrayAdapter);

        populateRecentGroups();
        return rootView;
    }

    public void populateRecentGroups() {
        groupArrayAdapter = new GroupArrayAdapter(getActivity(), R.layout.item_group, recentGroups);
        recentGroupsList.setAdapter(groupArrayAdapter);

        Firebase firebaseRef = new Firebase(FirebaseApp.FIREBASE_URL + "groups/");
        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                recentGroups.clear();
                for (DataSnapshot child : snapshot.getChildren()) {
                    recentGroups.add(child.getValue(Group.class));
                }
                groupArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v(TAG, "The read failed: " + firebaseError.getMessage());
            }
        });
    }
}