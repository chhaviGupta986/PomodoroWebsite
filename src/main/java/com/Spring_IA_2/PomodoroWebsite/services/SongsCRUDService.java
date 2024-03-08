package com.Spring_IA_2.PomodoroWebsite.services;

import com.Spring_IA_2.PomodoroWebsite.classes.Music.Music;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class SongsCRUDService {

    public Music CreateSongs(Music music) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult>apiFuture = db.collection("Songs").document(music.getUrl()).set(music);

        return music;

    }
}
