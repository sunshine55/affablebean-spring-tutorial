import {defineConfig} from 'vite';
import solidPlugin from 'vite-plugin-solid';
import devtools from 'solid-devtools/vite';

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
    solidPlugin()
  ],
  server: {
    port: 5147,
    host: true
  },
  build: {
    target: 'esnext'
  }
});
