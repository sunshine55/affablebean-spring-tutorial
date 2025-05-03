import {createResource, For} from 'solid-js';
import {useParams} from '@solidjs/router';
import {ItemCard} from './ItemCard';

const fetchCategoryItems = async categoryId => {
  const response = await fetch(
    `${import.meta.env.VITE_AFBB_API}/items?categoryId=${categoryId}`
  );
  if (!response.ok) {
    throw new Error('Failed to fetch items');
  }
  return response.json();
};

export const ItemList = () => {
  const {id: categoryId} = useParams();
  const [items] = createResource(categoryId, fetchCategoryItems);

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
