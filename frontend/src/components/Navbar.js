import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Navbar.css';

const Navbar = () => {
  const nav = useNavigate();
  const username = localStorage.getItem('username');

  const handleLogout = () => {
    localStorage.clear();
    nav('/login');
  };

  return (
    <nav className="navbar-container">
      <div className="navbar-title">Secure File Storage</div>
      <div className="navbar-links">
        {username && <span className="welcome-text">Welcome, {username}</span>}
        <Link to="/upload" className="nav-btn">Upload</Link>
        <Link to="/files" className="nav-btn">My Files</Link>
        <button onClick={handleLogout} className="logout-btn">Logout</button>
      </div>
    </nav>
  );
};

export default Navbar;
