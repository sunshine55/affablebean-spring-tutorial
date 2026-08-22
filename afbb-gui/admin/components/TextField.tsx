'use client';

import { ChangeEvent, useState } from 'react';

export type TextFieldProps = {
  label: string;
  name: string;
  value?: string | number;
  type?: 'text' | 'textarea' | 'number' | 'password';
  rows?: number;
  onChange?: (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => void;
};

export const TextField = ({ label, name, value, type = 'text', rows, onChange }: TextFieldProps) => {
  const [visible, setVisible] = useState(false);
  const isPassword = type === 'password';
  const inputType = isPassword ? (visible ? 'text' : 'password') : type;

  return (
    <div className="mb-4">
      <label className="block text-gray-700 font-bold mb-2" htmlFor={name}>
        {label}
      </label>
      {type === 'textarea' ? (
        <textarea
          name={name}
          rows={rows}
          className="w-full px-3 py-2 border rounded focus:outline-none focus:ring"
          value={value}
          onChange={onChange}
        />
      ) : (
        <div className="relative">
          <input
            name={name}
            type={inputType}
            className={`w-full px-3 py-2 border rounded focus:outline-none focus:ring${isPassword ? ' pr-10' : ''}`}
            value={value}
            onChange={onChange}
          />
          {isPassword && (
            <button
              type="button"
              aria-label={visible ? 'Hide password' : 'Show password'}
              aria-pressed={visible}
              onClick={() => setVisible((v) => !v)}
              className="absolute inset-y-0 right-0 flex items-center px-3 text-gray-500 hover:text-gray-700"
            >
              {visible ? (
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-5 h-5">
                  <path strokeLinecap="round" strokeLinejoin="round" d="M3.98 8.223A10.477 10.477 0 001.934 12C3.226 16.338 7.244 19.5 12 19.5c.993 0 1.953-.138 2.863-.395M6.228 6.228A10.45 10.45 0 0112 4.5c4.756 0 8.773 3.162 10.065 7.498a10.523 10.523 0 01-4.293 5.774M6.228 6.228L3 3m3.228 3.228l3.65 3.65m7.894 7.894L21 21m-3.228-3.228l-3.65-3.65m0 0a3 3 0 10-4.243-4.243m4.242 4.242L9.88 9.88" />
                </svg>
              ) : (
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-5 h-5">
                  <path strokeLinecap="round" strokeLinejoin="round" d="M2.036 12.322a1.012 1.012 0 010-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178z" />
                  <path strokeLinecap="round" strokeLinejoin="round" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                </svg>
              )}
            </button>
          )}
        </div>
      )}
    </div>
  );
};
