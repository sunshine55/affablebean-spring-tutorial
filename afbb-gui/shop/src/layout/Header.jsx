import {CartBadge} from '../cart';

export const Header = () => {
  return (
    <div className="navbar bg-base-100 shadow-sm">
      <div className="flex-1">
        <a className="btn btn-ghost text-xl" href="/">
          Affable Spring Bean
        </a>
      </div>
      <div className="flex-none">
        <CartBadge />
      </div>
    </div>
  );
};
