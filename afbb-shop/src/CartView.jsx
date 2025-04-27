import {For, useContext} from 'solid-js';
import {AppContext} from './AppContext';

export const CartView = () => {
  const {appStore} = useContext(AppContext);
  const cartGroups = () => {
    return Object.entries(
      appStore.cart.reduce((groups, item) => {
        if (!groups[item.name]) {
          groups[item.name] = {quantity: 0, subtotal: 0};
        }
        groups[item.name].quantity += 1;
        groups[item.name].subtotal += item.price;
        return groups;
      }, {})
    ).map(([name, {quantity, subtotal}]) => ({name, quantity, subtotal}));
  };

  return (
    <div className="grid grid-cols-4 gap-4 p-8">
      <div className="col-start-2">
        <For each={cartGroups()}>
          {({name, quantity, subtotal}) => (
            <div className="collapse collapse-plus bg-base-100 border border-base-300">
              <input type="radio" name="my-accordion-3" defaultChecked />
              <div className="collapse-title font-semibold">
                {name.toUpperCase()}
              </div>
              <div className="collapse-content text-sm">
                <p>Quanity: {quantity}</p>
                <p>
                  Subtotal:{' '}
                  {subtotal.toLocaleString('en-US', {
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
