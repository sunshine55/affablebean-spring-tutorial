'use client';

import { useParams } from 'next/navigation';
import { ItemModel } from '@/schema';
import { ItemForm } from '@/ui';

export default function ItemCreatePage() {
  const { categoryId } = useParams();
  const item: ItemModel = {
    id: '',
    name: '',
    description: '',
    imageSrc: '',
    price: 0,
    categoryId: categoryId as string,
  };
  return <ItemForm {...item} />;
}
