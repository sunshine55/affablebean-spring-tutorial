import {Category} from './Category';

export const App = () => {
  return (
    <div class="grid grid-cols-6 min-h-screen">
      {/* Left Blank */}
      <div class="col-span-1 bg-gray-100"></div>

      {/* Middle Container */}
      <div class="col-span-4 flex flex-col">
        {/* Header */}
        <div class="flex items-center justify-center bg-white py-8">
          <div class="text-center">
            <h1 class="text-4xl font-bold text-gray-900">Affable Spring Bean</h1>
            <p class="text-lg text-gray-500">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
          </div>
        </div>

        {/* Body */}
        <div class="flex flex-grow">
          {/* Sidebar Navigation */}
          <aside class="w-1/4 bg-gray-100 p-4">
            <nav>
              <ul class="space-y-4">
                <li>
                  <a href="#" class="text-gray-700 hover:underline">
                    Home
                  </a>
                </li>
                <li>
                  <a href="#" class="text-gray-700 hover:underline">
                    About
                  </a>
                </li>
                <li>
                  <a href="#" class="text-gray-700 hover:underline">
                    Services
                  </a>
                </li>
                <li>
                  <a href="#" class="text-gray-700 hover:underline">
                    Contact
                  </a>
                </li>
              </ul>
            </nav>
          </aside>

          {/* Body Content */}
          <main class="w-3/4 p-6">
            <h2 class="text-2xl font-bold mb-4">Welcome to the Body Content</h2>
            <p class="text-gray-600">
              This is the main content area. You can add your content here.
            </p>
          </main>
        </div>

        {/* Footer */}
        <div class="h-16 bg-gray-300 flex items-center justify-center">
          <p class="text-center text-amber-600">
            &copy; Developed by{' '}
            <a href="https://github.com/sunshine55?tab=repositories">
              sunshine55
            </a>
          </p>
        </div>
      </div>
      
      {/* Right Blank */}
      <div class="col-span-1 bg-gray-100"></div>
    </div>
  );
};
