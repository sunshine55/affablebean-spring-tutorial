import {A} from '@solidjs/router';

export const CategoryCard = ({id, name, description, imageSrc}) => {
  return (
    <div className="card bg-base-100 w-96 shadow-sm">
      <figure>
        <img src={imageSrc} alt={name.toUpperCase()} />
      </figure>
      <div className="card-body">
        <h2 className="card-title">{name.toUpperCase()}</h2>
        <p>{description}</p>
        <div className="card-actions justify-end">
          <A className="btn btn-info" href={`/category/${id}`}>
            Browse
          </A>
        </div>
      </div>
    </div>
  );
};
