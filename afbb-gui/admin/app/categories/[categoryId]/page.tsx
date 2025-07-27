'use client';

import { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';

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

  return <CategoryForm {...category} />;
}
