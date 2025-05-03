import {createContext} from 'solid-js';

export const INITIAL_APP_STORE = {cart: []};

export const AppContext = createContext(INITIAL_APP_STORE);
