package com.app.data;

import com.app.engauge.FirebaseApp;
import com.firebase.client.Firebase;

/**
 * Created by jiexicao on 11/15/14.
 */
public class Question {
    private String question;
    private String poster;
    private int upvotes;
    private int downvotes;

    public Question() {
        // Default constructor needed to convert Firebase results to Question.
    }

    public Question(String question, String poster, int upvotes, int downvotes) {
        this.question = question;
        this.poster = poster;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getPoster() {
        return this.poster;
    }

    public int getUpvotes() {
        return this.upvotes;
    }

    public int getDownvotes() {
        return this.downvotes;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public void setDownvotes(int downvotes) {
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
