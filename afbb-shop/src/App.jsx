import {AppBodySidebar} from './AppBodySidebar';
import {AppBodyContent} from './AppBodyContent';
import {CategoryList} from './CategoryList';

export const App = () => {
  return (
    <div class="grid">
      {/* Header */}
      <div class="flex items-center justify-center bg-base-100 py-8">
        <div class="text-center">
          <h1 class="text-4xl font-bold text-green-500">Affable Spring Bean</h1>
          <p class="text-lg text-base-content">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
          </p>
        </div>
      </div>

      {/* Body */}
      <div class="flex flex-grow">
        <AppBodySidebar />
        <AppBodyContent>
          <CategoryList />
        </AppBodyContent>
      </div>

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
  );
};
