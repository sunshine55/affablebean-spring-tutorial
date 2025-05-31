import type { Metadata } from 'next';
import { Geist } from 'next/font/google';
import './globals.css';
import Header from './components/Header';
import Footer from './components/Footer';
import Sidebar from './components/Sidebar';

const geist = Geist({
  subsets: ['latin'],
  variable: '--font-geist-sans',
});

export const metadata: Metadata = {
  title: 'Affable Bean Admin',
  description: 'Admin dashboard for Affable Bean store',
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={geist.variable}>
        <div className="layout">
          <Header />
          <Sidebar />
          <main className="main">{children}</main>
          <Footer />
        </div>
      </body>
    </html>
  );
}
