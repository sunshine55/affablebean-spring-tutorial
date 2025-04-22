import {createContext} from 'solid-js';

const defaultCategoryContext = {
  categories: [],
  selectedCategory: null
};

export const CategoryContext = createContext(defaultCategoryContext);
