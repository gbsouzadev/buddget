import { FormField } from "./FormField";

export function SignUpScreen() {
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
            Sign up
          </h2>
        </div>
        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <form className="space-y-6" action="#" method="POST">
            <FormField
              id="firstName"
              name="firstName"
              type="firstName"
              autoComplete="firstName"
              required
            >
              First name
            </FormField>
            <FormField
              id="lastName"
              name="lastName"
              type="lastName"
              autoComplete="lastName"
              required
            >
              Last name
            </FormField>
            <FormField
              id="email"
              name="email"
              type="email"
              autoComplete="email"
              required
            >
              Email address
            </FormField>
            <FormField
              id="password"
              name="password"
              type="password"
              autoComplete="password"
              required
            >
              Password
            </FormField>
            <FormField
              id="repeatPassword"
              name="repeatPassword"
              type="repeatPassword"
              autoComplete="repeatPassword"
              required
            >
              Repeat your password
            </FormField>
            <div>
              <button
                type="submit"
                className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
              >
                Sign up
              </button>
            </div>
          </form>

          <p className="mt-10 text-center text-sm text-gray-500">
            Already registered?{" "}
            <a
              href="/login"
              className="font-semibold leading-6 text-indigo-600 hover:text-indigo-500"
            >
              Sign in
            </a>
          </p>
        </div>
      </div>
    </>
  );
}
