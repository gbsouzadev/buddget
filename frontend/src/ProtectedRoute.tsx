import { useSelector } from "react-redux";
import { NavLink, Outlet } from "react-router-dom";

import { AuthState } from "./auth-slice";

export function ProtectedRoute() {
  const { userToken } = useSelector((state: { auth: AuthState }) => state.auth);

  // show unauthorized screen if no user is found in redux store
  if (!userToken) {
    return (
      <div className="unauthorized">
        <h1>Unauthorized :(</h1>
        <span>
          <NavLink to="/login">Login</NavLink> to gain access
        </span>
      </div>
    );
  }

  return <Outlet />;
}
