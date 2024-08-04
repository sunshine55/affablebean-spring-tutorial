import {render} from 'solid-js/web';

const App = () => {
  return (
    <h1 class="text-3xl font-bold underline">
      Hello SolidJS!!!
    </h1>
  );
};

const root = document.getElementById('root');
render(() => <App />, root);
