package com.app.data;

import com.app.engauge.FirebaseApp;
import com.firebase.client.Firebase;

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
    private String questions;

    public Group(String name, String description, String ownerId) {
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.questions = "";
    }

    public String createGroupId() {
        /* Return a unique identifier based on the name:
         *   i.e. "CS Colloquium 4" --> "cs-colloquium-4"
         * This is shown to the user at the end of the creation of a group so they can
         * easily share their group with others.
         **/
        return "";
    }

    public String saveToFirebase() {
        Firebase firebaseRef = new Firebase(FirebaseApp.FIREBASE_URL);
        Firebase groupsRef = firebaseRef.child("groups");
        Firebase newGroupRef = groupsRef.push();

        newGroupRef.setValue(this);
        return newGroupRef.getKey();
    }
}
