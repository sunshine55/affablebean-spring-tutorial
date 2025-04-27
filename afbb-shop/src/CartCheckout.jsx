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

      <div className="divider">THANK YOU</div>

      <button className="btn btn-success w-full">Checkout</button>
    </div>
  );
};
