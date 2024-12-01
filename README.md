# ⏰ Pomodoro Productivity Platform

**A productivity-boosting study platform built on the Pomodoro Technique to help users stay focused, organized, and motivated during study sessions. It integrates background music for a more immersive experience.**

---

## ✨ What is this website about?

The Pomodoro Productivity Platform leverages the Pomodoro Technique to boost productivity and enhance focus. The Pomodoro Technique involves breaking work into 25-minute intervals, followed by short breaks, to maintain concentration and efficiency. Our platform takes it a step further by integrating background music, customizable timers, and a cozy, animated UI to create a fun and engaging study environment.

https://github.com/user-attachments/assets/3f735b26-0546-4145-a557-a24ac79d23a3

## ☑️ Features

- **Customizable Study and Break Timers**: Set your own durations for study sessions and breaks with customizable timers, accompanied by alarm sounds to indicate the start and end of each session.
- **Background Music**: Enjoy a selection of calming music during your study sessions to improve focus.
- **User Stats**: Track your study progress and statistics to monitor your performance over time.
- **Admin Role**: Admins can upload, delete, and edit songs, while users can listen to music during study sessions.
- **Cozy UI**: The platform features an animated cat in the background and a cozy, user-friendly interface for an engaging experience.

## 🧩 Microservices Implemented

The application is structured around several microservices to ensure scalability, maintainability, and separation of concerns:
### 1. **Eureka Service**  
  - Provides the Eureka Server for service discovery and monitoring.  

### 2. **Gateway**  
  - The platform uses an asynchronous API Gateway powered by the Netty web server to manage incoming HTTP requests and route them to the appropriate services.

### 3. **Auth Service**
  - User registration and login.
  - Role-based access control (Admin/User).
  - JWT token generation and validation for secure access to protected routes.

### 4. **MusicService**
  - Admins can upload, edit, and delete songs.
  - Users can select and listen to songs during their study sessions.
  - Uses Firebase Storage to store media files and Firestore to manage song metadata.

### 5. **TrackUser Service**
  - Tracks the total time a user spends studying.
  - Stores and updates user session data in the PostgreSQL database.
  - Displays user stats for tracking productivity over time.

##  🛠️ Technologies Used

### **Frontend**:
- **React.js**: Used for building the dynamic user interface.
- **Ant Design**: Utilized for UI components and styling to ensure an intuitive and aesthetically pleasing design.

### **Backend**:
- **Spring Boot**: Used to create the RESTful APIs for handling the backend logic and business rules.
- **PostgreSQL (via Supabase)**: Used for storing user data and application state.
- **Firebase**: Manages the storage of media files (songs) and handles user authentication.

### **Other Tools**:
- **Postman**: Used for testing APIs and troubleshooting backend functionality.


