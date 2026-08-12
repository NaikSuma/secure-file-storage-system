import React, { useEffect, useState } from 'react';
import API from '../api/api';

export default function FileList() {
  const [files, setFiles] = useState([]);
  const username = localStorage.getItem('username');

  useEffect(() => {
    API.get(`/api/files/list`).then(res => setFiles(res.data));
  }, [username]);

  const download = filename =>
    API.get(`/api/files/download/${filename}`, { responseType: 'blob' })
      .then(r => {
        const url = URL.createObjectURL(r.data);
        const a = document.createElement('a');
        a.href = url;
        a.download = filename;
        a.click();
      });

  return (
    <div>
      <h3>Your Files</h3>
      <ul className="list-group">
        {files.map(file => (
          <li key={file.id} className="list-group-item d-flex justify-content-between align-items-center">
            {file.filename}
            <button className="btn btn-outline-primary btn-sm" onClick={() => download(file.filename)}>Download</button>
          </li>
        ))}
      </ul>
    </div>
  );
}
