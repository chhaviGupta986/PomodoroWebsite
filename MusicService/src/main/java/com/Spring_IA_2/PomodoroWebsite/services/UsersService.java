package com.Spring_IA_2.PomodoroWebsite.services;

import java.util.concurrent.ExecutionException;

import com.Spring_IA_2.PomodoroWebsite.classes.Users.userResponse;
import com.Spring_IA_2.PomodoroWebsite.classes.UsersLogin.UserLogin;
import com.Spring_IA_2.PomodoroWebsite.classes.createUsers.createUsers;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Spring_IA_2.PomodoroWebsite.classes.Users.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class UsersService {

    public userResponse createUsers(createUsers createUsers) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        userResponse user = getUser(new UserLogin(createUsers.getPassword(),createUsers.getEmail()));
        if(user!=null){
            return null;
        }
        ApiFuture<WriteResult> apiFuture = db.collection("login").document(createUsers.getEmail()).set(createUsers);
        return new userResponse(createUsers.getUsername(),createUsers.getEmail(),createUsers.getRole());
    }

    public ResponseEntity<Users>getUsersByEmail(String Email) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentSnapshot>apiFuture = db.collection("login").document().get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        if (documentSnapshot.exists()){
            Users users = documentSnapshot.toObject(Users.class);
            return new ResponseEntity<>(users,HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.valueOf(400));
    }

    public  userResponse getUser(UserLogin user) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference documentReference = db.collection("login").document(user.getEmail());
        ApiFuture<DocumentSnapshot>future = documentReference.get();
        DocumentSnapshot snapshot = future.get();
        Users users;
        if(snapshot.exists()){
            users = snapshot.toObject(Users.class);
            //check password here
            return new userResponse(users.getUsername(),users.getEmail(),users.getRole());
        }

        return null;

    }
    
}
