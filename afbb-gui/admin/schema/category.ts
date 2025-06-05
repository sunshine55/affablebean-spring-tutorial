import { z } from 'zod';

export const categorySchema = z
  .object({
    id: z.string(),
    name: z.string().min(1, 'Name is required').max(100, 'Name must be less than 100 characters'),
    description: z.string(),
    imageSrc: z.string().url(),
  })
  .partial();
export type CategoryModel = z.infer<typeof categorySchema>;

export const categoriesSchema = z.array(categorySchema);
export type CategoriesModel = z.infer<typeof categoriesSchema>;
