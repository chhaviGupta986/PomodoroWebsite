package com.Spring_IA_2.PomodoroWebsite.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import com.Spring_IA_2.PomodoroWebsite.classes.Music.Music;
import com.Spring_IA_2.PomodoroWebsite.classes.Music.MusicForm;
import com.Spring_IA_2.PomodoroWebsite.classes.UsersLogin.UserLogin;
import com.Spring_IA_2.PomodoroWebsite.classes.createUsers.createUsers;
import com.Spring_IA_2.PomodoroWebsite.services.SongUploadService;
import com.Spring_IA_2.PomodoroWebsite.services.SongsCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Spring_IA_2.PomodoroWebsite.classes.Users.Users;
import com.Spring_IA_2.PomodoroWebsite.services.UsersService;

@RestController
@RequestMapping(path = "/auth")
public class PomodoroWebsiteController {
    UsersService usersService;
    SongUploadService songUploadService;
    SongsCRUDService songsCRUDService;

    @Autowired
    public PomodoroWebsiteController(UsersService usersService,SongUploadService songUploadService,SongsCRUDService songsCRUDService){
            this.usersService = usersService;
            this.songUploadService = songUploadService;
            this.songsCRUDService = songsCRUDService;
    }

    @RequestMapping(path = "/createUser",method = RequestMethod.POST)
    public ResponseEntity<String> createUsers(@RequestBody createUsers user) throws ExecutionException, InterruptedException {
        Users res = usersService.createUsers(user);
        if(res == null){
            return new ResponseEntity<>("Error user already exists :'D ", HttpStatus.valueOf(400));
        }
        return new ResponseEntity<>("User created successfully :D", HttpStatus.valueOf(200));
    }
    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public ResponseEntity<Users> getUsers(@RequestBody UserLogin user) throws ExecutionException, InterruptedException {

         Users user_db =  usersService.getUser(user);
         if (user_db != null){
             return new ResponseEntity<>(user_db, HttpStatus.valueOf(200));
         }
        return new ResponseEntity<>(null, HttpStatus.valueOf(400));
    }

//    @RequestMapping(path ="/uploadSongs",method =RequestMethod.POST )
//    public ResponseEntity<String> uploadSongs(@RequestBody MusicForm file) throws IOException, ExecutionException, InterruptedException {
//        String FileName = songUploadService.saveTest(file.getFile());
//        file.setUrl(FileName);
//        songsCRUDService.CreateSongs(new Music(file.getTitle(),file.getArtist(),file.getUrl(),file.getCredits()));
//        return new ResponseEntity<>("Uploaded Successfully",HttpStatus.valueOf(200));
//    }

    @RequestMapping(path="/getSongs",method = RequestMethod.GET)
    public String getSongs() throws IOException {

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


