'use client';

import { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';
import Link from 'next/link';

import { itemsSchema, ItemsModel } from '@/schema';
import { Spinner } from '@/components';

export default function ItemListPage() {
  const { categoryId } = useParams();
  const baseLink = `/categories/${categoryId}`;

  const [items, setItems] = useState<ItemsModel>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    fetch(`${process.env.NEXT_PUBLIC_AFBB_API}/items/category?categoryId=${categoryId}`)
      .then((res) => res.json())
      .then((data) => setItems(itemsSchema.parse(data)))
      .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return <Spinner />;
  }

  return (
    <div>
      <div className="flex justify-between items-center px-6 pt-6">
        <h1 className="text-2xl font-bold">
          <Link href={baseLink} className="hover:underline">
            Category Items
          </Link>
        </h1>
        <Link
          href={`${baseLink}/items/create`}
          className="flex items-center gap-2 px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700 transition"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth={1.5}
            stroke="currentColor"
            className="size-6"
          >
            <path strokeLinecap="round" strokeLinejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
          </svg>
          <p>Create</p>
        </Link>
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-6 p-6">
        {items.map((i) => (
          <div key={i.id} className="bg-white rounded-lg shadow p-6 flex flex-col items-center">
            <h2 className="text-xl font-semibold mb-2">{i.name}</h2>
            <Link
              href={`${baseLink}/items/${i.id}`}
              className="mt-4 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
            >
              Modify
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
}
