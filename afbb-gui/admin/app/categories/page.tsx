'use client';

import { useEffect, useState } from 'react';
import Link from 'next/link';

import { categoriesSchema, CategoriesType } from '@/schema/category';

const apiUrl = `${process.env.NEXT_PUBLIC_AFBB_API}/categories`;

export default function CategoryListPage() {
  const [categories, setCategories] = useState<CategoriesType>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    fetch(apiUrl)
      .then((res) => res.json())
      .then((data) => setCategories(categoriesSchema.parse(data)))
      .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return <div className="flex justify-center items-center h-64">Loading...</div>;
  }

  return (
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
  );
}
