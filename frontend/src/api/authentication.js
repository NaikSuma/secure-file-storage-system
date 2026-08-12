import axios from "axios";
import { API_BASE_URL } from "./constant";

const postRequest = async (url, body) => {
  try {
    const response = await axios.post(API_BASE_URL + url, body);
    return response.data;
  } catch (err) {
    throw new Error(err?.response?.data?.message || err.message || 'Something went wrong');
  }
};

export const loginAPI = (body) => postRequest('/api/auth/login', body);
export const registerAPI = (body) => postRequest('/api/auth/register', body);
export const verifyAPI = (body) => postRequest('/api/auth/verify', body);
