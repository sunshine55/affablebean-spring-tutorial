import {render} from 'solid-js/web';

const App = () => {
  return <h1>Hello SolidJS!!!</h1>
};

const root = document.getElementById('root');
render(() => (<App/>), root);
