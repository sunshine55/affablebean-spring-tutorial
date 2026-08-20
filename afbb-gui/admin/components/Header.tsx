'use client';

import { useAuth } from '@/context/AuthContext';

export function Header() {
  const { user, logout } = useAuth();

  return (
    <header className="header flex items-center justify-between">
      <h1>Admin Dashboard</h1>
      {user && (
        <div className="flex items-center gap-4">
          <span className="text-sm text-gray-600">Hello, {user.name}</span>
          <button
            type="button"
            onClick={() => logout()}
            className="px-3 py-1 text-sm bg-gray-200 text-gray-700 rounded hover:bg-gray-300 transition"
          >
            Logout
          </button>
        </div>
      )}
    </header>
  );
}
