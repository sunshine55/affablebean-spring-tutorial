--
-- Sample data for table `category`
--
INSERT INTO `category` (`name`) VALUES ('dairy'),('meats'),('bakery'),('fruit & veg');



--
-- Sample data for table `product`
--
INSERT INTO `product` (`name`, price, description, category_id) VALUES ('milk', 1.70, 'semi skimmed (1L)', 1);
INSERT INTO `product` (`name`, price, description, category_id) VALUES ('cheese', 2.39, 'mild cheddar (330g)', 1);
INSERT INTO `product` (`name`, price, description, category_id) VALUES ('butter', 1.09, 'unsalted (250g)', 1);
INSERT INTO `product` (`name`, price, description, category_id) VALUES ('free range eggs', 1.76, 'medium-sized (6 eggs)', 1);

INSERT INTO `product` (`name`, price, description, category_id) VALUES ('organic meat patties', 2.29, 'rolled in fresh herbs<br>2 patties (250g)', 2);
INSERT INTO `product` (`name`, price, description, category_id) VALUES ('parma ham', 3.49, 'matured, organic (70g)', 2);
INSERT INTO `product` (`name`, price, description, category_id) VALUES ('chicken leg', 2.59, 'free range (250g)', 2);
INSERT INTO `product` (`name`, price, description, category_id) VALUES ('sausages', 3.55, 'reduced fat, pork<br>3 sausages (350g)', 2);

INSERT INTO `product` (`name`, price, description, category_id) VALUES ('sunflower seed loaf', 1.89, '600g', 3);
INSERT INTO `product` (`name`, price, description, category_id) VALUES ('sesame seed bagel', 1.19, '4 bagels', 3);
INSERT INTO `product` (`name`, price, description, category_id) VALUES ('pumpkin seed bun', 1.15, '4 buns', 3);
INSERT INTO `product` (`name`, price, description, category_id) VALUES ('chocolate cookies', 2.39, 'contain peanuts<br>(3 cookies)', 3);

INSERT INTO `product` (`name`, price, description, category_id) VALUES ('corn on the cob', 1.59, '2 pieces', 4);
INSERT INTO `product` (`name`, price, description, category_id) VALUES ('red currants', 2.49, '150g', 4);
INSERT INTO `product` (`name`, price, description, category_id) VALUES ('broccoli', 1.29, '500g', 4);
INSERT INTO `product` (`name`, price, description, category_id) VALUES ('seedless watermelon', 1.49, '250g', 4);