import {defineConfig} from 'vite';
import solidPlugin from 'vite-plugin-solid';
import devtools from 'solid-devtools/vite';
import tailwindcss from '@tailwindcss/vite';

export default defineConfig({
  plugins: [
    devtools({
      autoname: true,
      locator: {
        targetIDE: 'vscode',
        key: 'Ctrl',
        jsxLocation: true,
        componentLocation: true
      }
    }),
    solidPlugin(),
    tailwindcss()
  ],
  server: {
    port: 3000,
    host: true
  },
  preview: {
    port: 3000,
    host: true
  },
  build: {
    target: 'esnext'
  }
});
