import axios, { AxiosResponse } from "axios";
import { createAsyncThunk } from "@reduxjs/toolkit";

const backendURL = "http://localhost:8080";

export const userLogin = createAsyncThunk(
  "auth/login",
  async ({ email, password }: { email: string, password: string }, { rejectWithValue }) => {
    try {      
      const config = {
        headers: {
          "Content-Type": "application/json",
        },
      };
      const response: AxiosResponse<{token: string}> = await axios.post(
        `${backendURL}/auth/login`,
        { email, password },
        config
      );
      // store user's token in local storage
      
      localStorage.setItem('token', response.data.token)
      return response.data;
    } catch (error: any) {
      // return custom error message from API if any
      if (error.response && error.response.data.message) {
        return rejectWithValue(error.response.data.message);
      } else {
        return rejectWithValue(error.message);
      }
    }
  }
);
