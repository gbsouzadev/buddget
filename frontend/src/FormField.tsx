import React from 'react'
import { UseFormRegister } from 'react-hook-form'


export function FormField(props: {
  autoComplete: string
  children: React.ReactNode
  id: string
  name: string
  register: UseFormRegister<any>
  required: boolean
  showForgotPassword?: boolean
  type: string
  registerOptions?: any
}) {
    return (
      <div>
        <div className="flex items-center justify-between">
          <label
            htmlFor="email"
            className="block text-sm font-medium leading-6 text-gray-900"
          >
            {props.children}
          </label>
          {props.showForgotPassword ? (
            <div className="text-sm">
              <a
                href="#"
                className="font-semibold text-indigo-600 hover:text-indigo-500"
              >
                Forgot password?
              </a>
            </div>
          ) : null}
        </div>
        <div className="mt-2">
          <input
            id={props.id}
            {...props.register(props.name, props.registerOptions)}
            type={props.type}
            autoComplete={props.autoComplete}
            required={props.required}
            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
        </div>
      </div>
    );
  }