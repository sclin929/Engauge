package com.app.data;

import com.app.engauge.FirebaseApp;
import com.firebase.client.Firebase;

import java.util.Map;

/**
 * Created by jiexicao on 11/15/14.
 */
public class Question {
    private String message;
    private int upvotes;
    private int downvotes;
    // How to store user votes?
    // private Map<String, int> userVotes;

    public Question(String message, int upvotes, int downvotes) {
        this.message = message;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public String saveToFirebase(String groupId) {
        Firebase firebaseRef = new Firebase(FirebaseApp.FIREBASE_URL);
        Firebase groupsRef = firebaseRef.child("groups");
        Firebase specificGroupRef = groupsRef.child(groupId);
        Firebase questionRef = specificGroupRef.child("questions");

        Firebase newQuestionRef = questionRef.push();
        newQuestionRef.setValue(this);
        return newQuestionRef.getKey();
    }
}
