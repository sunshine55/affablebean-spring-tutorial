import {createStore, produce} from 'solid-js/store';
import {AppContext, INITIAL_APP_STORE} from './context';
import {Footer, Header} from './layout';

export const App = props => {
  const [appStore, setAppStore] = createStore(INITIAL_APP_STORE);

  const appContext = {
    appStore,
    addToCart: item =>
      setAppStore(
        'cart',
        produce(cart => {
          let existing = cart.find(existingItem => existingItem.id === item.id);
          if (existing) {
            existing.quantity += 1;
            existing.subtotal += item.price;
          } else {
            cart.push({
              id: item.id,
              name: item.name,
              quantity: 1,
              subtotal: item.price
            });
          }
        })
      ),
    removeFromCart: item =>
      setAppStore(
        'cart',
        produce(cart => {
          let existing = cart.find(existingItem => existingItem.id === item.id);
          if (existing) {
            existing.quantity -= 1;
            existing.subtotal -= item.price;
            if (existing.quantity <= 0) {
              cart.splice(cart.indexOf(existing), 1);
            }
          }
        })
      )
  };

  return (
    <AppContext.Provider value={appContext}>
      <Header />
      {props.children}
      <Footer />
    </AppContext.Provider>
  );
};
