'use client';

import { useEffect, useState } from 'react';
import Link from 'next/link';

import { categoriesSchema, CategoriesModel } from '@/schema';
import { Spinner } from '@/components';

export default function CategoryListPage() {
  const [categories, setCategories] = useState<CategoriesModel>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    fetch(`${process.env.NEXT_PUBLIC_AFBB_API}/categories`)
      .then((res) => res.json())
      .then((data) => setCategories(categoriesSchema.parse(data)))
      .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return <Spinner />;
  }

  return (
    <div>
      <div className="flex justify-between items-center px-6 pt-6">
        <h1 className="text-2xl font-bold">Categories</h1>
        <Link
          href="/categories/create"
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
        {categories.map((c) => (
          <div key={c.id} className="bg-white rounded-lg shadow p-6 flex flex-col items-center">
            <h2 className="text-xl font-semibold mb-2">{c.name}</h2>
            <Link
              href={`/categories/${c.id}`}
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
