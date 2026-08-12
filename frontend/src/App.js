import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import PrivateRoute from './routes/PrivateRoute';

import UploadFile from './components/UploadFile';
import FileList from './components/FileList';



import AuthenticationWrapper from './components/AuthenticationWrapper'; // Adjust path if needed
import Login from './components/Login';
import Verify from './components/Verify';
import Register from './components/Register';



function App() {
  return (
    <Router>
      <Routes>
        {/* Auth Routes Grouped under AuthenticationWrapper */}
        <Route element={<AuthenticationWrapper />}>
          <Route path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/verify" element={<Verify />} />
        </Route>

        {/* Protected Routes */}
        <Route path="/upload" element={<PrivateRoute><UploadFile /></PrivateRoute>} />
        <Route path="/files" element={<PrivateRoute><FileList /></PrivateRoute>} />
      </Routes>
    </Router>
  );
}

export default App;
