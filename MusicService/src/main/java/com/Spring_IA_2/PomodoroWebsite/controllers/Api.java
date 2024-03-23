package com.Spring_IA_2.PomodoroWebsite.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.Spring_IA_2.PomodoroWebsite.classes.Music.Music;
import com.Spring_IA_2.PomodoroWebsite.classes.Music.MusicForm;
import com.Spring_IA_2.PomodoroWebsite.services.SongUploadService;
import com.Spring_IA_2.PomodoroWebsite.services.SongsCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api")
public class Api {
    SongUploadService songUploadService;
    SongsCRUDService songsCRUDService;

    @Autowired
    public Api(SongUploadService songUploadService, SongsCRUDService songsCRUDService){
            this.songUploadService = songUploadService;
            this.songsCRUDService = songsCRUDService;
    }

    @RequestMapping(path ="/uploadSongs",method =RequestMethod.POST )
    public ResponseEntity<String> uploadSongs(@ModelAttribute("MusicForm")MusicForm file) throws IOException, ExecutionException, InterruptedException {
        if(file.getFile().isEmpty()){
            return new ResponseEntity<>("Empty Files not allowed :'(",HttpStatus.valueOf(401));
        }
        System.out.println(file.getFile().getContentType());
        if(!file.getFile().getContentType().equals("audio/mpeg")){
            return new ResponseEntity<>("Only mp3 files are allowed :'(",HttpStatus.valueOf(402));
        }

        String FileName = songUploadService.saveTest(file.getFile());
        System.out.println(FileName);
        file.setUrl(FileName);
        songsCRUDService.CreateSongs(new Music(file.getTitle(),file.getArtist(),file.getCredits(),file.getUrl()));
        return new ResponseEntity<>("Uploaded Successfully",HttpStatus.valueOf(200));
    }

    @RequestMapping(path = "/getSongsList",method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Music>> getSongs() throws ExecutionException, InterruptedException {
       ArrayList<Music> music = songsCRUDService.getSongs();
       return new ResponseEntity<>(music, HttpStatus.OK);
    }
    @RequestMapping(path="/downloadSong",method = RequestMethod.GET)

    public String downloadSong() throws IOException {

        String songUrl = "https://firebasestorage.googleapis.com/v0/b/pomodorowebsite-5a017.appspot.com/o/Songs%2FGhostrifterOfficial%2FHope-Emotional-Soundtrack(chosic.com).mp3?alt=media&token=142b98b6-dae3-490b-8a67-db564f747c57";

        try {
            URL url = new URL(songUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set up the connection
            connection.setRequestMethod("GET");

            // Check if the response code indicates success
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Get the input stream containing the binary data
                try (InputStream inputStream = connection.getInputStream()) {
                    // Save the binary data to a file (you might need to handle file naming appropriately)
                    try (FileOutputStream fileOutputStream = new FileOutputStream("downloaded_song.mp3")) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;

                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, bytesRead);
                        }

                        System.out.println("Song downloaded successfully.");
                    }
                }
            } else {
                System.out.println("Failed to download song. HTTP response code: " + connection.getResponseCode());
            }

            // Close the connection
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Hi";
    }
}


