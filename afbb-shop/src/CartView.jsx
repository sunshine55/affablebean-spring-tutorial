import {For, useContext} from 'solid-js';
import {AppContext} from './AppContext';

export const CartView = () => {
  const {appStore} = useContext(AppContext);

  return (
    <div className="grid grid-cols-4 gap-4 p-8">
      <div className="col-start-2">
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
      <div className="col-start-3">
        <div className="flex items-center justify-center h-32 bg-gray-200">
          Item 2
        </div>
      </div>
    </div>
  );
};
