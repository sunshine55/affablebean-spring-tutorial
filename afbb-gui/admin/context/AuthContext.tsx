'use client';

import { createContext, useCallback, useContext, useEffect, useState } from 'react';
import { usePathname, useRouter } from 'next/navigation';

import { clearTokens, getAccessToken, getRefreshToken, isLocalPath, setTokens } from '@/lib/auth';

export type User = {
  id: string;
  name: string;
  email: string;
  username: string;
  roles: string[];
};

type AuthContextType = {
  user: User | null;
  isAuthenticated: boolean;
  loading: boolean;
  login: (username: string, password: string, returnTo?: string | null) => Promise<void>;
  logout: () => Promise<void>;
};

const AuthContext = createContext<AuthContextType | null>(null);

const API = process.env.NEXT_PUBLIC_AFBB_API;

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const router = useRouter();
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState(true);

  const fetchUser = useCallback(async (): Promise<User | null> => {
    const token = getAccessToken();
    if (!token) return null;
    try {
      const res = await fetch(`${API}/auth/me`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      if (!res.ok) return null;
      return res.json();
    } catch {
      return null;
    }
  }, []);

  useEffect(() => {
    fetchUser().then((u) => {
      setUser(u);
      setLoading(false);
    });
  }, [fetchUser]);

  const login = async (username: string, password: string, returnTo?: string | null) => {
    const res = await fetch(`${API}/auth/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password }),
    });
    if (!res.ok) {
      throw new Error('Invalid credentials');
    }
    const data = await res.json();
    setTokens(data.access_token, data.refresh_token);
    const me = await fetchUser();
    setUser(me);
    router.push(isLocalPath(returnTo) ? returnTo : '/');
  };

  const logout = async () => {
    const refreshToken = getRefreshToken();
    if (refreshToken) {
      await fetch(`${API}/auth/logout`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ refreshToken }),
      }).catch(() => {});
    }
    clearTokens();
    setUser(null);
    router.push('/login');
  };

  return (
    <AuthContext.Provider value={{ user, isAuthenticated: !!user, loading, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth(): AuthContextType {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth must be used within AuthProvider');
  return ctx;
}

export function useRequireAuth() {
  const { isAuthenticated, loading } = useAuth();
  const pathname = usePathname();
  const router = useRouter();

  useEffect(() => {
    if (!loading && !isAuthenticated && pathname !== '/login') {
      router.replace(`/login?returnTo=${encodeURIComponent(pathname)}`);
    }
  }, [isAuthenticated, loading, pathname, router]);

  return { isAuthenticated, loading };
}
