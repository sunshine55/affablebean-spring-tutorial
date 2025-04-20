import {createSignal, For} from 'solid-js';
import {mockCategoryItems} from './mock/category';
import {CategoryCard} from './CategoryCard';

export const CategoryList = () => {
  const [categories, setCategories] = createSignal(mockCategoryItems);

  return (
    <div class="flex flex-wrap items-center justify-center">
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
