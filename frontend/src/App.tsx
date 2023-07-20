import React, { useState, useEffect }  from 'react'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

import { LoginScreen } from './LoginScreen'
import { SignUpScreen } from './SignUpScreen'

const router = createBrowserRouter([
  {
    path: "/",
    element: <div>Hello World!</div>,
  },
  {
    path: "/login",
    element: <LoginScreen />
  },
  {
    path: "/sign-up",
    element: <SignUpScreen />
  },
]);

function App() {
  return (
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
  )
}

export default App
