db = db.getSiblingDB('afbb');

db.category.drop();
db.item.drop();
db.system_user.drop();
db.refresh_token.drop();

db.category.insertMany([
  {
    _id: ObjectId("68142ad4b3498d12af5275b1"),
    name: "dairy",
    description: "Aliquam faucibus purus in massa tempor nec feugiat nisl. Eget nunc scelerisque viverra mauris in aliquam sem fringilla ut. Nunc sed velit dignissim sodales ut eu sem integer vitae justo.",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    _id: ObjectId("68142ad4b3498d12af5275b2"),
    name: "meat",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    _id: ObjectId("68142ad4b3498d12af5275b3"),
    name: "bakery",
    description: "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Donec velit neque, auctor sit amet aliquam vel, ullamcorper sit amet ligula.",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    _id: ObjectId("68142ad4b3498d12af5275b4"),
    name: "vegetables",
    description: "Curabitur aliquet quam id dui posuere blandit. Nulla porttitor accumsan tincidunt. Proin eget tortor risus.",
    imageSrc: "https://picsum.photos/300/200"
  }
]);

db.item.insertMany([
  {
    name: "milk",
    description: "Semi skimmed milk (1L)",
    price: 1.7,
    categoryId: "68142ad4b3498d12af5275b1",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "cheese",
    description: "Mild cheddar cheese (330g)",
    price: 2.39,
    categoryId: "68142ad4b3498d12af5275b1",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "butter",
    description: "Unsalted butter (250g)",
    price: 1.09,
    categoryId: "68142ad4b3498d12af5275b1",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "eggs",
    description: "Medium sized package (6 eggs)",
    price: 1.76,
    categoryId: "68142ad4b3498d12af5275b1",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "patties",
    description: "Rolled in fresh herbs<br>2 patties (250g)",
    price: 2.29,
    categoryId: "68142ad4b3498d12af5275b2",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "ham",
    description: "Matured and organic ham (70g)",
    price: 3.49,
    categoryId: "68142ad4b3498d12af5275b2",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "chicken",
    description: "Free range chicken whole meat (250g)",
    price: 5.59,
    categoryId: "68142ad4b3498d12af5275b2",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "sausages",
    description: "Reduced fat, pork, pack of three sausages (350g)",
    price: 4.55,
    categoryId: "68142ad4b3498d12af5275b2",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "loaf",
    description: "Plain white bread (250g)",
    price: 1.89,
    categoryId: "68142ad4b3498d12af5275b3",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "bagel",
    description: "Whole grain bread (70g)",
    price: 7.5,
    categoryId: "68142ad4b3498d12af5275b3",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "bun",
    description: "Over baked bread (250g)",
    price: 8,
    categoryId: "68142ad4b3498d12af5275b3",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "cookies",
    description: "Mint chocolate cookies (12)",
    price: 10.11,
    categoryId: "68142ad4b3498d12af5275b3",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "watermelon",
    description: "Seedless juicy watermelon (250g)",
    price: 1.2,
    categoryId: "68142ad4b3498d12af5275b4",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "currants",
    description: "Multi purposes black currants (70g)",
    price: 7.22,
    categoryId: "68142ad4b3498d12af5275b4",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "broccoli",
    description: "Organic green broccoli (250g)",
    price: 3.01,
    categoryId: "68142ad4b3498d12af5275b4",
    imageSrc: "https://picsum.photos/300/200"
  },
  {
    name: "corn",
    description: "Mixed white and yellow whole kernel corns (12)",
    price: 6.77,
    categoryId: "68142ad4b3498d12af5275b4",
    imageSrc: "https://picsum.photos/300/200"
  }
]);

db.system_user.insertMany([
  {
    _id: ObjectId("68142ad4b3498d12af5275c1"),
    name: "Root User",
    email: "root@afbb.com",
    username: "root",
    password: "$2b$12$afs3q4JXdJsloiMZz5jltusSGueThI27BJaG1H.x2CXNrb6yXQaY2",
    active: true,
    roles: ["admin"],
    createdAt: ISODate("2026-08-19T00:00:00Z"),
    updatedAt: ISODate("2026-08-19T00:00:00Z"),
    lastLoginAt: null
  },
  {
    _id: ObjectId("68142ad4b3498d12af5275c2"),
    name: "Admin User",
    email: "admin@afbb.com",
    username: "admin",
    password: "$2b$12$/ZIkLQ.GZayN17Hy3fH68.sXzYmmhnBBNES1z1.Mmc2A3PCCrULa2",
    active: true,
    roles: ["user"],
    createdAt: ISODate("2026-08-19T00:00:00Z"),
    updatedAt: ISODate("2026-08-19T00:00:00Z"),
    lastLoginAt: null
  }
]);
