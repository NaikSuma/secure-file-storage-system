import React from 'react';
import { Outlet } from 'react-router-dom';
import { useSelector } from 'react-redux';
import Navbar from './Navbar';
import './AuthenticationWrapper.css';
import BckgImage from '../assets/img/secure_file_storage.webp';

const AuthenticationWrapper = () => {
  //const isLoading = useSelector((state) => state?.commonState?.loading);

  return (
    <div className="auth-wrapper">
      <Navbar variant="auth" />
      <div className="auth-layout">
        <div className="auth-image-container">
          <img src={BckgImage} className="auth-image" alt="background" />
        </div>
        <div className="auth-outlet-container">
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default AuthenticationWrapper;
