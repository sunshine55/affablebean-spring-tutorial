/* @refresh reload */
import 'solid-devtools';
import {render} from 'solid-js/web';
import {Router, Route} from '@solidjs/router';

import './index.css';
import {App} from './App';
import {CartView} from './cart';
import {CategoryList} from './category';
import {ItemList} from './item';

const root = document.getElementById('root');

if (import.meta.env.DEV && !(root instanceof HTMLElement)) {
  throw new Error(
    'Root element not found. Did you forget to add it to your index.html? Or maybe the id attribute got misspelled?'
  );
}

render(
  () => (
    <Router root={App}>
      <Route path="/" component={CategoryList} />
      <Route path="/category/:id" component={ItemList} />
      <Route path="/cart" component={CartView} />
    </Router>
  ),
  root
);
