import React  from 'react'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

import { Provider } from 'react-redux'
import store from './store'

import { LoginScreen } from './LoginScreen'
import { SignUpScreen } from './SignUpScreen'
import { ProtectedRoute } from './ProtectedRoute';

const routesForPublic = [
  {
    path: "/about-us",
    element: <div>About Us</div>,
  },
];

const routesForNotAuthenticatedOnly = [
  {
    path: "/",
    element: <div>Home Page</div>,
  },
  {
    path: "/login",
    element: <LoginScreen />,
  },
  {
    path: "/sign-up",
    element: <SignUpScreen />
  },
];

const routesForAuthenticatedOnly = [
  {
    path: "/",
    element: <ProtectedRoute />,
    children: [
      {
        path: "/",
        element: <div>User Home Page</div>,
      },
      {
        path: "/profile",
        element: <div>User Profile</div>,
      },      
    ],
  },
];

const router = createBrowserRouter([
  ...routesForPublic,
  ...(!store.getState().auth.userToken ? routesForNotAuthenticatedOnly : []),
  ...routesForAuthenticatedOnly,
]);

function App() {
  return (
    <React.StrictMode>
      <Provider store={store}>
        <RouterProvider router={router} />
      </Provider>
    </React.StrictMode>
  )
}

export default App
