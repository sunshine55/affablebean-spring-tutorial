import { ChangeEvent } from 'react';

export interface TextFieldProps {
  label: string;
  name: string;
  value: string;
  type?: 'text' | 'textarea';
  rows?: number;
  onChange?: (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => void;
}

export const TextField = ({ label, name, value, type = 'text', rows, onChange }: TextFieldProps) => (
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
      <input
        name={name}
        type={type}
        className="w-full px-3 py-2 border rounded focus:outline-none focus:ring"
        value={value}
        onChange={onChange}
      />
    )}
  </div>
);
