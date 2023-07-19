import React, { useState, useEffect }  from 'react'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

import { LoginScreen } from './LoginScreen'

const router = createBrowserRouter([
  {
    path: "/",
    element: <div>Hello world!</div>,
  },
  {
    path: "/login",
    element: <LoginScreen />
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
