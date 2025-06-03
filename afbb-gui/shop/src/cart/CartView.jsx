import {For, useContext} from 'solid-js';
import {AppContext} from '../context';
import {CartCheckout} from './CartCheckout';

export const CartView = () => {
  const {appStore} = useContext(AppContext);
  const total = () => {
    const result = appStore.cart.reduce((acc, item) => acc + item.subtotal, 0);
    return result.toLocaleString('en-US', {
      style: 'currency',
      currency: 'USD'
    });
  };

  return (
    <div className="grid grid-cols-3 gap-4 p-8">
      <div className="col-start-1">
        <div className="card bg-base-300 rounded-box grid h-20 place-items-center">
          <p>Total: {total()}</p>
        </div>
        <div className="divider"></div>
        <For each={appStore.cart}>
          {item => (
            <div className="collapse collapse-plus bg-base-100 border border-base-300">
              <input type="radio" name="my-accordion-3" defaultChecked />
              <div className="collapse-title font-semibold">
                {item.name.toUpperCase()}
              </div>
              <div className="collapse-content text-sm">
                <p>Quanity: {item.quantity}</p>
                <p>
                  Subtotal:{' '}
                  {item.subtotal.toLocaleString('en-US', {
                    style: 'currency',
                    currency: 'USD'
                  })}
                </p>
              </div>
            </div>
          )}
        </For>
      </div>
      <div className="col-start-2 col-span-2">
        <CartCheckout />
      </div>
    </div>
  );
};
