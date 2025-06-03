import { z } from 'zod';

export const categorySchema = z.object({
  id: z.string(),
  name: z.string().min(1, 'Name is required').max(100, 'Name must be less than 100 characters'),
  description: z.string().optional(),
  imageSrc: z.string().url().optional(),
});
export type CategoryType = z.infer<typeof categorySchema>;

export const categoriesSchema = z.array(categorySchema);
export type CategoriesType = z.infer<typeof categoriesSchema>;
