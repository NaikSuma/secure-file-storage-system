// src/components/Login.js

import React, { useState } from 'react';
import API from '../api/api';
import { useNavigate } from 'react-router-dom';
import './Auth.css';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const nav = useNavigate();

  const handle = async (e) => {
    e.preventDefault();
    try {
      // ✅ Backend expects userName, not username/email
      const { data } = await API.post('/api/auth/login', {
        userName: email,
        password: password
      });

      localStorage.setItem('token', data.token);
      localStorage.setItem('username', email); // saving for display or requests
      nav('/upload');
    } catch (err) {
      console.error(err);
      alert('Login failed');
    }
  };

  return (
    <div className="auth-container">
      <form onSubmit={handle} className="auth-form">
        <h3>Login</h3>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder="Email address"
          required
          className="form-control"
        />
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Password"
          required
          className="form-control"
        />
        <button className="btn btn-primary w-100">Login</button>
        <p style={{ marginTop: '10px' }}>
          Don't have an account? <a href="/register">Register here</a>
        </p>
      </form>
    </div>
  );
}
