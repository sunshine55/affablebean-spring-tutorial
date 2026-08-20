'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';

import { itemSchema, itemsSchema, ItemModel } from '@/schema';
import { TextField, TextFieldProps } from '@/components';
import { authenticatedFetch } from '@/lib/auth';

const itemFormFields: TextFieldProps[] = [
  { label: 'Name', name: 'name', value: '' },
  {
    label: 'Description',
    name: 'description',
    type: 'textarea',
    rows: 10,
    value: '',
  },
  { label: 'Image URL', name: 'imageSrc', value: '' },
  { label: 'Price', name: 'price', type: 'number', value: 0 },
];

export const ItemForm = (props: ItemModel) => {
  const { id, name, description, imageSrc, price, categoryId } = props;

  const router = useRouter();

  const [formState, setFormState] = useState<ItemModel>({
    name: name || '',
    description: description || '',
    imageSrc: imageSrc || '',
    price: price || 0,
    categoryId: categoryId || '',
  });

  useEffect(() => setFormState(props), [props]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormState({ ...formState, [name]: name === 'price' ? parseFloat(value) : value });
  };

  const handleSave = () => {
    const parseResult = itemSchema.safeParse(formState);
    if (!parseResult.success) {
      const msg = parseResult.error.issues.map((e) => `${e.path.join('.')}: ${e.message}`).join('\n');
      alert(`Validation error:\n${msg}`);
      return;
    }
    authenticatedFetch(`${process.env.NEXT_PUBLIC_AFBB_API}/items`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify([formState]),
    })
      .then((response) => (response.ok ? response.json() : Promise.reject(response)))
      .then((responseData) => {
        const data = itemsSchema.parse(responseData);
        if (data.length === 0) {
          alert('No items were saved. Please check your input.');
          return;
        }
        router.push(`/categories/${categoryId}/items`);
      })
      .catch((error) => {
        alert(`Error saving item: ${error}`);
      });
  };

  const handleDelete = () => {
    if (!id) {
      alert('Cannot delete unidentified item.');
      return;
    }
    if (!confirm('Are you sure you want to delete this item?')) {
      return;
    }
    authenticatedFetch(`${process.env.NEXT_PUBLIC_AFBB_API}/items?id=${id}`, {
      method: 'DELETE',
    })
      .then((response) => {
        if (!response.ok) {
          return Promise.reject(response.statusText);
        }
        router.push(`/categories/${categoryId}/items`);
      })
      .catch((error) => {
        alert(`Error deleting item: ${error}`);
      });
  };

  return (
    <div className="max-w-md mx-auto p-6 bg-white rounded shadow">
      <input type="hidden" name="id" value={id || ''} />
      {itemFormFields.map((field) => (
        <TextField
          key={field.name}
          {...field}
          value={formState[field.name as keyof ItemModel] ?? ''}
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
          href={`/categories/${categoryId}/items`}
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
