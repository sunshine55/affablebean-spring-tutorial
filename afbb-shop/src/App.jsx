import {CategoryList} from './CategoryList';

export const App = () => {
  return (
    <div class="grid grid-cols-6 min-h-screen">
      {/* Left Blank */}
      <div class="col-span-1 bg-gray-100"></div>

      {/* Middle Container */}
      <div class="col-span-4 flex flex-col">
        {/* Header */}
        <div class="flex items-center justify-center bg-base-100 py-8">
          <div class="text-center">
            <h1 class="text-4xl font-bold text-green-500">
              Affable Spring Bean
            </h1>
            <p class="text-lg text-base-content">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit.
            </p>
          </div>
        </div>

        {/* Body */}
        <CategoryList />

        {/* Footer */}
        <div class="flex items-center justify-center bg-base-200 h-16">
          <p class="text-center text-blue-900">
            &copy; Developed by{' '}
            <a
              href="https://github.com/sunshine55?tab=repositories"
              class="link text-red-500"
            >
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
