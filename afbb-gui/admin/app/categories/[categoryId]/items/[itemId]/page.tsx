'use client';

import { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';

import { itemSchema, ItemModel } from '@/schema';
import { Spinner } from '@/components';
import { ItemForm } from '@/ui';

export default function ItemEditPage() {
  const { itemId } = useParams();

  const [item, setItem] = useState<ItemModel>({} as ItemModel);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    if (!itemId) return;
    fetch(`${process.env.NEXT_PUBLIC_AFBB_API}/items?id=${itemId}`)
      .then((res) => res.json())
      .then((data) => setItem(itemSchema.parse(data[0] || {})))
      .finally(() => setLoading(false));
  }, [itemId]);

  if (loading) {
    return <Spinner />;
  }

  return <ItemForm {...item} />;
}
