import { createSlice } from "@reduxjs/toolkit";
import jwtDecode from "jwt-decode";
import { userLogin } from "./auth-actions";

export interface AuthState {
  loading: boolean;
  userInfo: {
    email: string;
  } | null;
  userToken: string | null;
  error: string | null;
}

// initialize userToken from local storage
const userToken = localStorage.getItem('token')
  ? localStorage.getItem('token')
  : null

const initialState: AuthState = {
  loading: false,
  userInfo: null,
  userToken,
  error: null,
};

// https://www.typescriptlang.org/docs/handbook/2/narrowing.html
const hasSub = (value: unknown): value is { sub: string } => {
  return typeof value === "object" && value !== null && "sub" in value;
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(userLogin.pending, (state) => {
      state.loading = true;
      state.error = null;
    });
    builder.addCase(userLogin.fulfilled, (state, action) => {
      const decoded = jwtDecode(action.payload.token);
      state.loading = true;
      state.loading = false;
      state.userInfo = hasSub(decoded)
        ? {
            email: decoded.sub,
          }
        : null;
      state.userToken = action.payload.token;
    });
    builder.addCase(userLogin.rejected, (state) => {
      state.loading = false;
      state.error = 'fail-to-fetch-token';
    });
  },
});

export default authSlice.reducer;
