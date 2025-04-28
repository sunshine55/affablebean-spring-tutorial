export const CartCheckout = () => {
  return (
    <div className="flex flex-col justify-center gap-4">
      <fieldset className="fieldset w-full">
        <legend className="fieldset-legend">What is your full name?</legend>
        <input
          type="text"
          className="input w-full"
          pattern="[A-Za-z]{1,} [A-Za-z]{1,} [A-Za-z]{1,}"
          placeholder="First Name, Middle Initials, Last Name"
        />
      </fieldset>

      <fieldset className="fieldset w-full">
        <legend className="fieldset-legend">What is your email contact?</legend>
        <input
          type="email"
          className="input validator w-full"
          placeholder="mail@site.com"
        />
      </fieldset>

      <fieldset className="fieldset w-full">
        <legend className="fieldset-legend">
          Where is your delivery address?
        </legend>
        <input type="text" className="input w-full" />
      </fieldset>

      <fieldset className="fieldset w-full">
        <legend className="fieldset-legend">
          What is your daytime contact number?
        </legend>
        <input
          type="tel"
          className="input validator tabular-nums w-full"
          placeholder="0123456789"
          pattern="[0-9]*"
        />
      </fieldset>

      <div className="divider">PAYMENT</div>

      <div className="flex gap-2 justify-center">
        <button
          type="button"
          class="text-gray-900 bg-[#F7BE38] hover:bg-[#F7BE38]/90 focus:ring-4 focus:ring-[#F7BE38]/50 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center dark:focus:ring-[#F7BE38]/50 mr-2 mb-2"
        >
          <svg
            class="mr-2 -ml-1 w-4 h-4"
            aria-hidden="true"
            focusable="false"
            data-prefix="fab"
            data-icon="paypal"
            role="img"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 384 512"
          >
            <path
              fill="currentColor"
              d="M111.4 295.9c-3.5 19.2-17.4 108.7-21.5 134-.3 1.8-1 2.5-3 2.5H12.3c-7.6 0-13.1-6.6-12.1-13.9L58.8 46.6c1.5-9.6 10.1-16.9 20-16.9 152.3 0 165.1-3.7 204 11.4 60.1 23.3 65.6 79.5 44 140.3-21.5 62.6-72.5 89.5-140.1 90.3-43.4 .7-69.5-7-75.3 24.2zM357.1 152c-1.8-1.3-2.5-1.8-3 1.3-2 11.4-5.1 22.5-8.8 33.6-39.9 113.8-150.5 103.9-204.5 103.9-6.1 0-10.1 3.3-10.9 9.4-22.6 140.4-27.1 169.7-27.1 169.7-1 7.1 3.5 12.9 10.6 12.9h63.5c8.6 0 15.7-6.3 17.4-14.9 .7-5.4-1.1 6.1 14.4-91.3 4.6-22 14.3-19.7 29.3-19.7 71 0 126.4-28.8 142.9-112.3 6.5-34.8 4.6-71.4-23.8-92.6z"
            ></path>
          </svg>
          Check out with PayPal
        </button>
        <button
          type="button"
          class="text-white bg-[#050708] hover:bg-[#050708]/80 focus:ring-4 focus:ring-[#050708]/50 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center mr-2 mb-2"
        >
          Check out with Apple Pay
          <svg
            class="ml-2 -mr-1 w-5 h-5"
            aria-hidden="true"
            focusable="false"
            data-prefix="fab"
            data-icon="apple"
            role="img"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 384 512"
          >
            <path
              fill="currentColor"
              d="M318.7 268.7c-.2-36.7 16.4-64.4 50-84.8-18.8-26.9-47.2-41.7-84.7-44.6-35.5-2.8-74.3 20.7-88.5 20.7-15 0-49.4-19.7-76.4-19.7C63.3 141.2 4 184.8 4 273.5q0 39.3 14.4 81.2c12.8 36.7 59 126.7 107.2 125.2 25.2-.6 43-17.9 75.8-17.9 31.8 0 48.3 17.9 76.4 17.9 48.6-.7 90.4-82.5 102.6-119.3-65.2-30.7-61.7-90-61.7-91.9zm-56.6-164.2c27.3-32.4 24.8-61.9 24-72.5-24.1 1.4-52 16.4-67.9 34.9-17.5 19.8-27.8 44.3-25.6 71.9 26.1 2 49.9-11.4 69.5-34.3z"
            ></path>
          </svg>
        </button>
      </div>

      <div className="divider"></div>

      <button className="btn btn-success">Place Order</button>
    </div>
  );
};
