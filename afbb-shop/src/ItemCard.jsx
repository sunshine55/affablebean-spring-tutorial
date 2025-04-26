export const ItemCard = ({id, name, description, price}) => {
  return (
    <div className="card bg-base-100 w-96 shadow-sm">
      <figure>
        <img
          src={`https://raw.githubusercontent.com/sunshine55/affablebean-microservice-tutorial/refs/heads/master/afbb-db/cdn/items/${name}.png`}
          alt={name.toUpperCase()}
        />
      </figure>
      <div className="card-body">
        <h2 className="card-title">{name.toUpperCase()}</h2>
        <p>{description}</p>
        <p>{price}</p>
        <div className="card-actions justify-end">
          <button className="btn btn-primary">Add to Cart</button>
        </div>
      </div>
    </div>
  );
};
