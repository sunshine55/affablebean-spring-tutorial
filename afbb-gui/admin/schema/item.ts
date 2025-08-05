import { z } from 'zod';

export const itemSchema = z.object({
  id: z.string().optional(),
  name: z.string().min(1, 'Name is required').max(100, 'Name must be less than 100 characters'),
  description: z.string().optional(),
  price: z.number().min(0, 'Price must be a positive number'),
  imageSrc: z.url(),
  categoryId: z.string(),
});
export type ItemModel = z.infer<typeof itemSchema>;

export const itemsSchema = z.array(itemSchema);
export type ItemsModel = z.infer<typeof itemsSchema>;
