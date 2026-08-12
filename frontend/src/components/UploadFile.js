import React, { useState } from 'react';
import API from '../api/api';
import './Auth.css'; // Reuse the same styling


const Upload = () => {
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState('');
  const username = localStorage.getItem('username');

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!file || !username) return;

    const formData = new FormData();
    formData.append('file', file);
    formData.append('username', username);

    try {
      await API.post('/api/files/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      setMessage('File uploaded and encrypted successfully!');
    } catch (err) {
      setMessage('Upload failed');
    }
  };

  return (
    <div className="auth-container">
      <form onSubmit={handleSubmit} className="auth-form">
        <h3>Upload File</h3>
        <input
          type="file"
          onChange={(e) => setFile(e.target.files[0])}
          required
        />
        <button className="btn btn-primary w-100" type="submit">Upload</button>
        {message && <p className="status-msg">{message}</p>}
      </form>
    </div>
  );
};

export default Upload;
