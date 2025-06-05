'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';

import { categoriesSchema, CategoryModel } from '@/schema/category';

export const CategoryForm = (props: CategoryModel) => {
  const { id, name, description, imageSrc } = props;

  const router = useRouter();

  const [formState, setFormState] = useState<CategoryModel>({
    name: name || '',
    description: description || '',
    imageSrc: imageSrc || '',
  });

  useEffect(() => setFormState(props), [props]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormState((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = () => {
    fetch(`${process.env.NEXT_PUBLIC_AFBB_API}/categories`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify([formState]),
    })
      .then((response) => (response.ok ? response.json() : Promise.reject(response)))
      .then((responseData) => {
        const data = categoriesSchema.parse(responseData);
        if (data.length === 0) {
          alert('No categories were saved. Please check your input.');
          return;
        }
        router.push('/categories');
      })
      .catch((error) => {
        alert(`Error saving category: ${error}`);
      });
  };

  return (
    <div className="max-w-md mx-auto p-6 bg-white rounded shadow">
      <input type="hidden" name="id" value={id || ''} />
      <div className="mb-4">
        <label className="block text-gray-700 font-bold mb-2" htmlFor="name">
          Name
        </label>
        <input
          name="name"
          type="text"
          className="w-full px-3 py-2 border rounded focus:outline-none focus:ring"
          value={formState.name || ''}
          onChange={handleChange}
        />
      </div>
      <div className="mb-4">
        <label className="block text-gray-700 font-bold mb-2" htmlFor="description">
          Description
        </label>
        <textarea
          name="description"
          rows={10}
          className="w-full px-3 py-2 border rounded focus:outline-none focus:ring"
          value={formState.description || ''}
          onChange={handleChange}
        />
      </div>
      <div className="mb-4">
        <label className="block text-gray-700 font-bold mb-2" htmlFor="imageSrc">
          Image URL
        </label>
        <input
          name="imageSrc"
          type="text"
          className="w-full px-3 py-2 border rounded focus:outline-none focus:ring"
          value={formState.imageSrc || ''}
          onChange={handleChange}
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
          onClick={handleSubmit}
        >
          Save
        </button>
      </div>
    </div>
  );
};
