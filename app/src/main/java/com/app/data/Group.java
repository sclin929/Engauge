package com.app.data;

import com.app.engauge.FirebaseApp;
import com.firebase.client.Firebase;

import java.util.HashMap;

/**
 * Created by jiexicao on 11/14/14.
 *
 * Group hierarcy:
 *
 * groups: {
 *     id1: {
 *        name: "",
 *        description: "",
 *        ownerId: "",
 *        questions: {
 *            q1: {...}
 *            q1: {...}
 *        }
 *     },
 *     id2: {...}
 * }
 */
public class Group {
    private String name;
    private String description;
    private String ownerId;
    private HashMap<String, Question> questions;

    public Group() {
        // Default constructor needed to convert Firebase results to Group.
    }

    public Group(String name, String description, String ownerId) {
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.questions = null;
    }

    public String createGroupId() {
        /* Return a unique identifier based on the name:
         *   i.e. "CS Colloquium 4" --> "cs-colloquium-4"
         * This is shown to the user at the end of the creation of a group so they can
         * easily share their group with others.
         **/
        return "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setQuestions(HashMap<String, Question> questions) {
        this.questions = questions;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public HashMap<String, Question> getQuestions() {
        return this.questions;
    }


    public String saveToFirebase(Firebase.CompletionListener listener) {
        Firebase firebaseRef = new Firebase(FirebaseApp.FIREBASE_URL);
        Firebase groupsRef = firebaseRef.child("groups");
        Firebase newGroupRef = groupsRef.push();

        newGroupRef.setValue(this, listener);
        return newGroupRef.getKey();
    }
}
