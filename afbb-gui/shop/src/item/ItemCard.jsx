import {useContext} from 'solid-js';
import {AppContext} from '../context';

export const ItemCard = ({id, name, description, imageSrc, price}) => {
  const {appStore, addToCart, removeFromCart} = useContext(AppContext);
  const quantity = () => appStore.cart.find(item => item.id === id)?.quantity;

  return (
    <div className="card bg-base-100 w-96 shadow-sm">
      <figure>
        <img src={imageSrc} alt={name.toUpperCase()} />
      </figure>
      <div className="card-body">
        <h2 className="card-title">{name.toUpperCase()}</h2>
        <p>{description}</p>
        <p>
          {price.toLocaleString('en-US', {style: 'currency', currency: 'USD'})}
        </p>
        <div className="card-actions">
          <button
            className="btn btn-sm btn-warning"
            onClick={() => removeFromCart({id, name, price})}
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              fill="currentColor"
              className="size-6"
            >
              <path
                fillRule="evenodd"
                d="M4.25 12a.75.75 0 0 1 .75-.75h14a.75.75 0 0 1 0 1.5H5a.75.75 0 0 1-.75-.75Z"
                clipRule="evenodd"
              />
            </svg>
          </button>
          <input
            type="text"
            value={quantity() ?? 0}
            className="input input-sm input-bordered text-center w-12"
          />
          <button
            className="btn btn-sm btn-accent"
            onClick={() => addToCart({id, name, price})}
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth={1.5}
              stroke="currentColor"
              className="w-5 h-5"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M12 4.5v15m7.5-7.5h-15"
              />
            </svg>
          </button>
        </div>
      </div>
    </div>
  );
};
