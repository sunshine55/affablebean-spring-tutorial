'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';

import { categorySchema, categoriesSchema, CategoryModel } from '@/schema';
import { TextField, TextFieldProps } from '@/components';

const categoryFormFields: TextFieldProps[] = [
  { label: 'Name', name: 'name', value: '' },
  {
    label: 'Description',
    name: 'description',
    type: 'textarea',
    rows: 10,
    value: '',
  },
  { label: 'Image URL', name: 'imageSrc', value: '' },
];

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
    setFormState({ ...formState, [name]: value });
  };

  const handleSave = () => {
    const parseResult = categorySchema.safeParse(formState);
    if (!parseResult.success) {
      alert(
        'Validation error:\n' + parseResult.error.errors.map((e) => `${e.path.join('.')}: ${e.message}`).join('\n'),
      );
      return;
    }
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

  const handleDelete = () => {
    if (!id) {
      alert('Cannot delete unidentified category.');
      return;
    }
    if (!confirm('Are you sure you want to delete this category?')) {
      return;
    }
    fetch(`${process.env.NEXT_PUBLIC_AFBB_API}/categories?id=${id}`, {
      method: 'DELETE',
    })
      .then((response) => {
        if (!response.ok) {
          return Promise.reject(response.statusText);
        }
        router.push('/categories');
      })
      .catch((error) => {
        alert(`Error deleting category: ${error}`);
      });
  };

  return (
    <div className="max-w-md mx-auto p-6 bg-white rounded shadow">
      <input type="hidden" name="id" value={id || ''} />
      {categoryFormFields.map((field) => (
        <TextField
          key={field.name}
          {...field}
          value={formState[field.name as keyof CategoryModel] || ''}
          onChange={handleChange}
        />
      ))}
      <div className="flex justify-between">
        <button
          hidden={!id}
          type="button"
          className="px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700 transition"
          onClick={handleDelete}
        >
          Delete
        </button>
        <Link
          href="/categories"
          className="px-4 py-2 mr-2 bg-gray-300 text-gray-700 rounded hover:bg-gray-400 transition"
        >
          Cancel
        </Link>
        <button
          type="button"
          className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
          onClick={handleSave}
        >
          Save
        </button>
      </div>
    </div>
  );
};
