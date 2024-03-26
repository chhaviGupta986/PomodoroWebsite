import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './assets/components/Login/Login';
import Register from './assets/components/Register/Register';
import Home from './assets/components/Home/Home'; // Import the Home component
import AuthenticatedRoute from './assets/components/AuthenticatedRoutes/AuthenticatedRoute';

function App() {
  const [user, setUser] = useState({
    token: null,
    iat: null,
    expiration: null,
    userName: null,
    mobile_number: null,
    email: null,
    role: null
  });

  return (
    <Router>
      <Routes>
        
          <>
            
            <Route element={<Login />} path="/Login" />
            <Route element={<Register />} path="/Register" />

            <Route  path="/"
             element={<AuthenticatedRoute loggedIn={user}>
                      <Home/>
                      </AuthenticatedRoute>
                    }
            />

            <Route  path="/Profile"
              element={<AuthenticatedRoute loggedIn={user}>
                        <Home/>
                        </AuthenticatedRoute>
                      }
            />

          </>
        
      </Routes>
    </Router>
  );
}

export default App;
