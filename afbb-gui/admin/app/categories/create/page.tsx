'use client';

import { CategoryModel } from '@/schema';
import { CategoryForm } from '@/ui';

export default function CategoryCreatePage() {
  const category: CategoryModel = {
    id: '',
    name: '',
    description: '',
    imageSrc: '',
  };
  return <CategoryForm {...category} />;
}
