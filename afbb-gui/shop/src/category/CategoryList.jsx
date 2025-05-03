import {createResource, For} from 'solid-js';
import {CategoryCard} from './CategoryCard';

const fetchCategories = async () => {
  const response = await fetch(`${import.meta.env.VITE_AFBB_API}/categories`);
  if (!response.ok) {
    throw new Error('Failed to fetch categories');
  }
  return response.json();
};

export const CategoryList = () => {
  const [categories] = createResource(fetchCategories);

  return (
    <div class="flex justify-center flex-wrap p-8">
      <For each={categories()}>
        {category => (
          <div class="grow p-2">
            <CategoryCard {...category} />
          </div>
        )}
      </For>
    </div>
  );
};
