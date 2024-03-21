package com.Spring_IA_2.PomodoroWebsite.services;

import com.Spring_IA_2.PomodoroWebsite.classes.Music.Music;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

@Service
public class SongsCRUDService {

    public Music CreateSongs(Music music) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult>apiFuture = db.collection("Songs").document(music.getUrl()).set(music);

        return music;
    }

    public ArrayList<Music> getSongs() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ArrayList<Music>SongsList = new ArrayList<>();
        CollectionReference reference = db.collection("Songs");

        Iterable<DocumentReference> iterable = reference.listDocuments();

        for (DocumentReference documentReference : iterable) {
            ApiFuture<DocumentSnapshot> snapshot = documentReference.get();
            DocumentSnapshot documentSnapshot = snapshot.get();
            if (documentSnapshot.exists()) {
                SongsList.add(documentSnapshot.toObject(Music.class));
            }
        }

       return SongsList;
    }
}
