// src/api/constant.js

import { getToken } from "../utils/jwt-helper";

export const API_BASE_URL = 'http://localhost:8082'; // Update if using a different port

export const API_URLS = {
  REGISTER: '/api/auth/register',
  LOGIN: '/api/auth/login',
  VERIFY: '/api/auth/verify',

  UPLOAD_FILE: '/api/files/upload',
  LIST_FILES: '/api/files/list',
  DOWNLOAD_FILE: (filename) => `/api/files/download/${filename}`,
};

export const getHeaders = (includeJson = true) => {
  const headers = {
    Authorization: `Bearer ${getToken()}`
  };

  if (includeJson) {
    headers['Content-Type'] = 'application/json';
  }

  return headers;
};
