'use client';

import { usePathname } from 'next/navigation';

import { AuthProvider, useRequireAuth } from '@/context/AuthContext';
import { Footer, Header, Sidebar, Spinner } from '@/components';

export function Layout({ children }: { children: React.ReactNode }) {
  return (
    <AuthProvider>
      <Shell>{children}</Shell>
    </AuthProvider>
  );
}

function Shell({ children }: { children: React.ReactNode }) {
  const pathname = usePathname();
  const isLoginPage = pathname === '/login';
  const { isAuthenticated, loading } = useRequireAuth();

  if (!isLoginPage && (!isAuthenticated || loading)) {
    return <Spinner />;
  }

  return (
    <div className={isLoginPage ? '' : 'layout'}>
      {!isLoginPage && <Header />}
      {!isLoginPage && <Sidebar />}
      <main className={isLoginPage ? '' : 'main'}>{children}</main>
      {!isLoginPage && <Footer />}
    </div>
  );
}
