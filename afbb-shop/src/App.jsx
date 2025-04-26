import {createStore} from 'solid-js/store';
import {AppContext, INITIAL_APP_STORE} from './AppContext';
import {Footer} from './Footer';
import {Header} from './Header';

export const App = props => {
  const [appStore, setAppStore] = createStore(INITIAL_APP_STORE);

  const appContext = {
    appStore,
    addToCart: item => setAppStore('cart', cart => [...cart, item])
  };

  return (
    <AppContext.Provider value={appContext}>
      <Header />
      {props.children}
      <Footer />
    </AppContext.Provider>
  );
};
