import {Footer} from './Footer';
import {Header} from './Header';

export const App = props => {
  return (
    <>
      <Header />
      {props.children}
      <Footer />
    </>
  );
};
