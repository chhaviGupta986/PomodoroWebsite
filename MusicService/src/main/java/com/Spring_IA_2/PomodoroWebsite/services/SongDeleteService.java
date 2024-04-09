package com.Spring_IA_2.PomodoroWebsite.services;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SongDeleteService {
    private Storage storage;
    private Firestore firestore;

    @EventListener
    public void init(ApplicationReadyEvent event) {
        try {
            ClassPathResource serviceAccount = new ClassPathResource("PomodoroWebsite.json");
            storage = StorageOptions.newBuilder().
                    setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream())).
                    setProjectId("pomodorowebsite-5a017").build().getService();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteTest(String filename) throws IOException {
        // DocumentReference docRef = firestore.collection("Songs").document(music.getUrl());
        // Map<String, Object> updates = new HashMap<>();
        // updates.put(filename, FieldValue.delete());
        
        // // Update and delete the "filename" field in the document
        // ApiFuture<WriteResult> writeResult = docRef.update(updates);
        
        // try {
        //     // Wait for the operation to complete
        //     System.out.println("Update time : " + writeResult.get());
        // } catch (Exception ex) {
        //     ex.printStackTrace();
        // }
    
    }
    
}
