import {For} from 'solid-js';
import {CategoryCard} from './CategoryCard';
import {mockCategoryItems} from './mock/category';

export const CategoryList = () => {
  const categories = () => mockCategoryItems;

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
