'use client';

import { FormEvent, Suspense, useState } from 'react';
import { useSearchParams } from 'next/navigation';

import { useAuth } from '@/context/AuthContext';
import { Spinner, TextField, TextFieldProps } from '@/components';

const loginFields: TextFieldProps[] = [
  { label: 'Username', name: 'username', value: '' },
  { label: 'Password', name: 'password', value: '', type: 'password' },
];

export default function LoginPage() {
  return (
    <Suspense fallback={<Spinner />}>
      <LoginForm />
    </Suspense>
  );
}

function LoginForm() {
  const { login } = useAuth();
  const searchParams = useSearchParams();
  const [formState, setFormState] = useState<Record<string, string>>({ username: '', password: '' });
  const [error, setError] = useState('');
  const [submitting, setSubmitting] = useState(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormState((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setError('');
    setSubmitting(true);
    try {
      await login(formState.username, formState.password, searchParams.get('returnTo'));
    } catch {
      setError('Invalid username or password');
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center">
      <form onSubmit={handleSubmit} className="w-full max-w-sm p-6 bg-white rounded shadow">
        <h2 className="text-2xl font-bold mb-6 text-center">Admin Login</h2>
        {loginFields.map((field) => (
          <TextField key={field.name} {...field} value={formState[field.name] || ''} onChange={handleChange} />
        ))}
        {error && <p className="text-red-600 text-sm mb-4">{error}</p>}
        <button
          type="submit"
          disabled={submitting}
          className="w-full px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition disabled:opacity-50"
        >
          {submitting ? 'Signing in...' : 'Sign in'}
        </button>
      </form>
    </div>
  );
}
