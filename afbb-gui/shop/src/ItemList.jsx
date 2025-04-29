import {useParams} from '@solidjs/router';
import {ItemCard} from './ItemCard';
import {mockCategoryItems} from './mock/category';

export const ItemList = () => {
  const categoryId = useParams().id;
  const items = () =>
    mockCategoryItems.find(category => category.id === categoryId)?.items || [];
  return (
    <div class="flex justify-center flex-wrap p-8">
      <For each={items()}>
        {item => (
          <div class="grow p-2">
            <ItemCard {...item} />
          </div>
        )}
      </For>
    </div>
  );
};
