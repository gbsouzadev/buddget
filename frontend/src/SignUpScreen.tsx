import { FormField } from "./FormField";
import { useForm, RegisterOptions } from "react-hook-form";

type FormValues = {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  repeatPassword: string;
};

export function SignUpScreen() {

  const { register, handleSubmit, formState } = useForm<FormValues>();
  
  const submitForm = (data: FormValues) => {

    if (formState.errors.repeatPassword?.type === 'validate') {
      console.log("error: ", formState.errors.repeatPassword.message)
    } else {
      console.log(data);
    }
  };

  const repeatPasswordValidation: RegisterOptions<FormValues> = {
    validate: {
    equalToPassword: (_, values) => 
      values.password === values.repeatPassword
    }
  }
  
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
          <form 
            className="space-y-6" 
            onSubmit={handleSubmit(submitForm)}
            >
            <FormField
              id="firstName"
              name="firstName"
              type="text"
              autoComplete="firstName"
              required
              register={register}
            >
              First name
            </FormField>
            <FormField
              id="lastName"
              name="lastName"
              type="text"
              autoComplete="lastName"
              register={register}
              required
            >
              Last name
            </FormField>
            <FormField
              id="email"
              name="email"
              type="email"
              autoComplete="email"
              register={register}
              required
            >
              Email address
            </FormField>
            <FormField
              id="password"
              name="password"
              type="password"
              autoComplete="password"
              register={register}
              required
            >
              Password
            </FormField>
            <FormField
              id="repeatPassword"
              name="repeatPassword"
              type="password"
              autoComplete="repeatPassword"
              register={register}
              required
              registerOptions={repeatPasswordValidation}
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
