'use client';

import { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';
import Link from 'next/link';

import { categorySchema, CategoryModel } from '@/schema';
import { Spinner } from '@/components';
import { CategoryForm } from '@/ui';

export default function CategoryEditPage() {
  const { categoryId } = useParams();

  const [category, setCategory] = useState<CategoryModel>({} as CategoryModel);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    if (!categoryId) return;
    fetch(`${process.env.NEXT_PUBLIC_AFBB_API}/categories?id=${categoryId}`)
      .then((res) => res.json())
      .then((data) => setCategory(categorySchema.parse(data[0] || {})))
      .finally(() => setLoading(false));
  }, [categoryId]);

  if (loading) {
    return <Spinner />;
  }

  return (
    <div>
      <div className="flex justify-between items-center px-6 pt-6">
        <h1 className="text-2xl font-bold">Category Modification</h1>
        <Link
          href={`/categories/${categoryId}/items`}
          className="flex items-center gap-2 px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700 transition"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            className="size-6"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10"
            />
          </svg>

          <p>Modify Items</p>
        </Link>
      </div>
      <CategoryForm {...category} />
    </div>
  );
}
