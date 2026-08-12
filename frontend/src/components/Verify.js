// src/components/Verify.js

import React, { useState } from 'react';
import API from '../api/api';
import { useNavigate } from 'react-router-dom';
import './Auth.css';

const Verify = () => {
  const [code, setCode] = useState('');
  const navigate = useNavigate();
  const username = localStorage.getItem('username');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await API.post('/api/auth/verify', { userName: username, code });
      alert('Verification successful! You can now login.');
      localStorage.removeItem('username');
      navigate('/login');
    } catch {
      alert('Invalid code or verification failed.');
    }
  };

  return (
    <div className="auth-container">
      <h2>Email Verification</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Enter verification code"
          value={code}
          onChange={(e) => setCode(e.target.value)}
          required
        />
        <button type="submit">Verify</button>
      </form>
    </div>
  );
};

export default Verify;
