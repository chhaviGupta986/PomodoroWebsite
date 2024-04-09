import React, { useState, useEffect } from 'react';
import './StudyRoom.css'
const StudyRoom = () => {
  const [studyDuration, setStudyDuration] = useState(0.1); // Study duration in minutes
  const [breakDuration, setBreakDuration] = useState(0.05); // Break duration in minutes
  const [timeLeft, setTimeLeft] = useState(studyDuration * 60); // Initial time left in seconds
  const [timerRunning, setTimerRunning] = useState(false);
  const [isStudySession, setIsStudySession] = useState(true); // Initial session is study session

  useEffect(() => {
    const intervalId = setInterval(() => {
      if (timerRunning) {
        // setTimeLeft(prevTimeLeft => {
        //   if (prevTimeLeft <= 0) {
        //     // Switch session and set time left accordingly
        //     setIsStudySession(isStudySession => !isStudySession);
        //     playBeep();
        //     return isStudySession ? breakDuration * 60 : studyDuration * 60;
        //   }
        //   return prevTimeLeft - 1;
        // });
        setTimeLeft(prevTimeLeft => {
          if (prevTimeLeft <= 0) {
            // Toggle session
            const nextIsStudySession = !isStudySession;
            setIsStudySession(nextIsStudySession);
        
            // Calculate time left based on the session
            const nextTimeLeft = nextIsStudySession ? studyDuration * 60 : breakDuration * 60;
        
            // Trigger beep sound
            playBeep();
        
            return nextTimeLeft;
          }
          return prevTimeLeft - 1;
        });
        




      }
    }, 1000);

    return () => clearInterval(intervalId);
  }, [timerRunning, breakDuration, studyDuration, isStudySession]);

  const startTimer = () => {
    setTimerRunning(true);
  };

  const stopTimer = () => {
    setTimerRunning(false);
  };
  const playBeep = () => {
    const audio = new Audio('https://firebasestorage.googleapis.com/v0/b/pomodorowebsite-cab09.appspot.com/o/ring1.mp3?alt=media&token=490078a4-174f-4385-be97-57378360cf1b');
    audio.play();
  };
  const formatTime = (seconds) => {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return `${minutes.toString().padStart(2, '0')}:${remainingSeconds.toString().padStart(2, '0')}`;
  };

  return (
    <div className="study-room-container">
      <div className="study-room">
        <h1 className="title">Pomodoro Timer</h1>
        <div className="timer">{formatTime(timeLeft)}</div>
        <div className="buttons">
          {timerRunning ? (
            <button onClick={stopTimer}>Pause Timer</button>
          ) : (
            <button onClick={startTimer}>Start Timer</button>
          )}
        </div>
      </div>
    </div>
  );
};

export default StudyRoom;
