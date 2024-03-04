package com.Spring_IA_2.PomodoroWebsite.services;

import java.util.concurrent.ExecutionException;

import com.Spring_IA_2.PomodoroWebsite.classes.UsersLogin.UserLogin;
import com.Spring_IA_2.PomodoroWebsite.classes.createUsers.createUsers;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import org.springframework.stereotype.Service;

import com.Spring_IA_2.PomodoroWebsite.classes.Users.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class UsersService {

    public  Users createUsers(createUsers createUsers) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        Users user = getUser(new UserLogin(createUsers.getName(),createUsers.getEmail()));
        if(user!=null){
            return null;
        }
        ApiFuture<WriteResult> apiFuture = db.collection("login").document(createUsers.getEmail()).set(createUsers);
        return user;
    }

    public  Users getUser(UserLogin user) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference documentReference = db.collection("login").document(user.getEmail());
        ApiFuture<DocumentSnapshot>future = documentReference.get();
        DocumentSnapshot snapshot = future.get();
        Users users;
        if(snapshot.exists()){
            users = snapshot.toObject(Users.class);
            return users;
        }

        return null;

    }
    
}
