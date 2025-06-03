'use client';

import { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';
import Link from 'next/link';

import { categorySchema, CategoryType } from '@/schema/category';

export default function CategoryEditPage() {
  const { id } = useParams();
  const [category, setCategory] = useState<CategoryType | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    if (!id) return;

    const apiUrl = `${process.env.NEXT_PUBLIC_AFBB_API}/categories/${id}`;
    fetch(apiUrl)
      .then((res) => res.json())
      .then((data) => setCategory(categorySchema.parse(data)))
      .finally(() => setLoading(false));
  }, [id]);

  if (loading) {
    return <div className="flex justify-center items-center h-64">Loading...</div>;
  }

  return (
    <form className="max-w-md mx-auto p-6 bg-white rounded shadow">
      <div className="mb-4">
        <label className="block text-gray-700 font-bold mb-2" htmlFor="name">
          Name
        </label>
        <input
          id="name"
          type="text"
          className="w-full px-3 py-2 border rounded focus:outline-none focus:ring"
          value={category?.name || ''}
          readOnly
        />
      </div>
      <div className="mb-4">
        <label className="block text-gray-700 font-bold mb-2" htmlFor="description">
          Description
        </label>
        <textarea
          id="description"
          rows={10}
          className="w-full px-3 py-2 border rounded focus:outline-none focus:ring"
          value={category?.description || ''}
          readOnly
        />
      </div>
      <div className="mb-4">
        <label className="block text-gray-700 font-bold mb-2" htmlFor="imageSrc">
          Image URL
        </label>
        <input
          id="imageSrc"
          type="text"
          className="w-full px-3 py-2 border rounded focus:outline-none focus:ring"
          value={category?.imageSrc || ''}
          readOnly
        />
      </div>
      <div className="flex justify-end">
        <Link
          href="/categories"
          className="px-4 py-2 mr-2 bg-gray-300 text-gray-700 rounded hover:bg-gray-400 transition"
        >
          Cancel
        </Link>
        <button
          type="button"
          className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
          onClick={() => alert('Edit functionality not implemented yet')}
        >
          Save
        </button>
      </div>
    </form>
  );
}
