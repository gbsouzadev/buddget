import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import { useSelector } from "react-redux";

import { useAppDispatch } from "./store";
import type { AuthState } from "./auth-slice";
import { userLogin } from "./auth-actions";

import { FormField } from "./FormField";

type FormValues = {
  email: string;
  password: string;
};

export function LoginScreen() {
  const { loading, userInfo } = useSelector(
    (state: { auth: AuthState }) => state.auth
  );
  const navigate = useNavigate();

  const dispatch = useAppDispatch();
  const { register, handleSubmit } = useForm<FormValues>();

  // redirect authenticated user to profile screen
  useEffect(() => {
    if (userInfo) {
      navigate("/profile");
    }
  }, [navigate, userInfo]);

  const submitForm = (data: { email: string; password: string }) => {
    dispatch(userLogin(data));
  };

  return (
    <>
      <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
        <div className="sm:mx-auto sm:w-full sm:max-w-sm">
          <img
            className="mx-auto h-10 w-auto"
            src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600"
            alt="Your Company"
          />
          <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
            Sign in to your account
          </h2>
        </div>
        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <form
            className="space-y-6"
            action="POST"
            onSubmit={handleSubmit(submitForm)}
          >
            <FormField
              id="email"
              name="email"
              type="email"
              autoComplete="email"
              required
              register={register}
            >
              Email address
            </FormField>
            <FormField
              id="password"
              type="password"
              name="password"
              autoComplete="password"
              showForgotPassword
              required
              register={register}
            >
              Password
            </FormField>
            <div>
              <button
                type="submit"
                className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                disabled={loading}
              >
                Sign in
              </button>
            </div>
          </form>

          <p className="mt-10 text-center text-sm text-gray-500">
            Not a member?{" "}
            <a
              href="/sign-up"
              className="font-semibold leading-6 text-indigo-600 hover:text-indigo-500"
            >
              Sign up for free
            </a>
          </p>
        </div>
      </div>
    </>
  );
}
