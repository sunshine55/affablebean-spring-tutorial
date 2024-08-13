import {Category} from './Category';

export const App = () => {
  return (
    <>
      <div class="flex items-center">
        <section class="mx-auto bg-white dark:bg-gray-900">
          <div class="mx-auto max-w-screen-xl text-center lg:py-16">
            <h1 class="mb-4 text-4xl font-extrabold tracking-tight leading-none text-gray-900 md:text-5xl lg:text-6xl dark:text-white">
              Affable Spring Bean
            </h1>
            <p class="mb-8 text-lg font-normal text-gray-500 lg:text-xl sm:px-16 lg:px-48 dark:text-gray-400">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit.
            </p>
          </div>
        </section>
      </div>

      <Category />
      
      <div class="flex items-center h-16 bg-gray-300">
        <div class="mx-auto">
          <p class="text-center text-amber-600">
            &copy; Developed by <a href="https://github.com/sunshine55?tab=repositories">sunshine55</a>
          </p>
        </div>
      </div>
    </>
  );
}
