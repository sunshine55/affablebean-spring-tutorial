'use client';

import { usePathname } from 'next/navigation';

import { AuthProvider } from '@/context/AuthContext';
import { Footer, Header, Sidebar } from '@/components';

export function Layout({ children }: { children: React.ReactNode }) {
  const pathname = usePathname();
  const isLoginPage = pathname === '/login';

  return (
    <AuthProvider>
      <div className={isLoginPage ? '' : 'layout'}>
        {!isLoginPage && <Header />}
        {!isLoginPage && <Sidebar />}
        <main className={isLoginPage ? '' : 'main'}>{children}</main>
        {!isLoginPage && <Footer />}
      </div>
    </AuthProvider>
  );
}
