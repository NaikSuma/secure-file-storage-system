// src/components/Register.js

import React, { useState } from 'react';
import API from '../api/api';
import { Link, useNavigate } from 'react-router-dom';
import './Auth.css';
import Verify from './Verify';

const Register = () => {
  const [values, setValues] = useState({
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    phoneNumber: ''
  });
  const [error, setError] = useState('');
  const [enableVerify, setEnableVerify] = useState(false);
  const navigate = useNavigate();

  const handleOnChange = (e) => {
    setValues({ ...values, [e.target.name]: e.target.value });
    setError('');
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      const { data } = await API.post('/api/auth/register', values);

      if (data.code === 200) {
        setEnableVerify(true);
        localStorage.setItem('username', values.email);
      } else {
        setError(data.message || 'Registration failed');
      }
    } catch (err) {
      setError(err.message || 'Registration error');
    }
  };

  return (
    <div className="auth-container">
      {!enableVerify ? (
        <form onSubmit={onSubmit} className="auth-form">
          <h3>Register</h3>

          <input
            type="email"
            name="email"
            value={values.email}
            onChange={handleOnChange}
            placeholder="Email address"
            className="form-control"
            required
          />
          <input
            type="text"
            name="firstName"
            value={values.firstName}
            onChange={handleOnChange}
            placeholder="First Name"
            className="form-control"
            required
          />
          <input
            type="text"
            name="lastName"
            value={values.lastName}
            onChange={handleOnChange}
            placeholder="Last Name"
            className="form-control"
            required
          />
          <input
            type="tel"
            name="phoneNumber"
            value={values.phoneNumber}
            onChange={handleOnChange}
            placeholder="Phone Number"
            className="form-control"
            required
          />
          <input
            type="password"
            name="password"
            value={values.password}
            onChange={handleOnChange}
            placeholder="Password"
            className="form-control"
            required
            autoComplete="new-password"
          />
          <button className="btn btn-primary w-100" type="submit">
            Sign Up
          </button>

          {error && <p className="auth-error">{error}</p>}
          <p style={{ marginTop: '10px' }}>
            Already have an account? <Link to="/login">Login here</Link>
          </p>
        </form>
      ) : (
        <Verify email={values.email} />
      )}
    </div>
  );
};

export default Register;
