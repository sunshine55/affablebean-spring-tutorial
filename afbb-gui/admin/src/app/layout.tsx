import type {Metadata} from 'next';
import {Header, Footer} from '@/app/components';

import '../styles/globals.css';

export const metadata: Metadata = {
  title: 'Affable Bean Admin',
  description: 'Page to administrate Affable Bean data'
};

export default function RootLayout({
  children
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        <Header />
        {children}
        <Footer />
      </body>
    </html>
  );
}
