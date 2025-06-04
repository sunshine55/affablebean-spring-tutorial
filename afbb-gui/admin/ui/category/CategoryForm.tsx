import Link from 'next/link';

import { CategoryModel } from '@/schema/category';

export const CategoryForm = ({ id, name, description, imageSrc }: CategoryModel) => {
  return (
    <form className="max-w-md mx-auto p-6 bg-white rounded shadow">
      <input type="hidden" name="id" value={id} />
      <div className="mb-4">
        <label className="block text-gray-700 font-bold mb-2" htmlFor="name">
          Name
        </label>
        <input
          id="name"
          type="text"
          className="w-full px-3 py-2 border rounded focus:outline-none focus:ring"
          value={name || ''}
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
          value={description || ''}
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
          value={imageSrc || ''}
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
};
