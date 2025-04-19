import {createSignal} from 'solid-js';
import {mockCategoryItems} from './mock/category';

export const Category = () => {
  const [categoryItems, setCategoryItems] = createSignal(mockCategoryItems);
  return (
    <div class="flex items-center py-16">
      <div class="mx-auto">
        <h1 class="text-center text-red-900">Category List Here! WIP...</h1>
      </div>
    </div>
  );
};
