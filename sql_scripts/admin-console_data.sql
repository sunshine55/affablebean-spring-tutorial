--
-- Sample data for table `customer`
--
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('Charlie Pace', 'c.pace@mailinator.com', '844-134-3777', '45 Autofcase Ouvonstaut', 'PG', '342392325479721');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('MC Hammer', 'hammer@mailinator.com', '833-045-6368', 'Ruskacvotak 11', 'PG', '378168419808042');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('Karel Gott', 'gott@mailinator.com', '855-837-2290', '83/12/45 Kosteln', 'PG', '370126117019757');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('Helena Vondrackova', 'h.vondrackova@mailinator.cz', '899-102-1174', '18 Letohradskaovack', 'PG', '371248067514600');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('Sawyer Ford', 'sawyer.ford@mailinator.com', '822-803-1623', '1855 Dual Rauls, Anfitioie', 'PG', '341197495395040');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('Dalibor Janda', 'dalibor@mailinator.cz', '833-517-7188', '56/18 Krononika', 'BN', '378050877811223');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('Richard Genzer', 'r.genzer@mailinator.cz', '833-517-2188', 'PlzeAzA 1317', 'BN', '4485823813367068');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('Iveta Bartosova', 'i.bartosova@mailinator.cz', '811-617-2519', 'Prokopskafiav 602', 'BN', '4556259753482704');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('Jin-Soo Kwon', 'jin.kwon@mailinator.kr', '844-136-8504', '1888 Ven. Staubert de Litkeinstein', 'BN', '4556076486388705');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('Benjamin Linus', 'b.linus@mailinator.com', '899-118-3975', '77 Drupal von Daustablish', 'BN', '4539115340503505');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('Leo Mares', 'mares@mailinator.it', '811-899-7746', 'Paladovski, St.Uferstein', 'HS', '4716444626246463');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('John Locke', 'maninblack@mailinator.com', '844-647-1676', '777/11 De la Valeria', 'HS', '5509303732993327');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('Lucie Bacbara', 'lucie@mailinator.cz', '846-026-5206', 'Nav 33 Kov, St.Aufeint', 'HS', '5462453572139864');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('Sayid Jarrah', 'sayid@mailinator.com', '822-026-5206', '778 Kodakaown', 'HS', '5581415713843213');
INSERT INTO customer (`name`, email, phone, address, city_region, cc_number) VALUES ('Hugo Reyes', 'hurley@mailinator.com', '844-026-5206', 'Andreina­nova 6455', 'HS', '5363537132894677');


--
-- Sample data for table `customer_order`
--
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (16.50, '2010-05-14 18:00:11.0', 15, 285434339);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (16.11, '2010-05-14 17:56:23.0', 14, 428278565);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (26.00, '2010-05-14 17:51:37.0', 13, 503113888);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (17.63, '2010-05-14 17:47:46.0', 12, 916407556);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (17.24, '2010-05-14 17:45:21.0', 11, 189191209);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (15.57, '2010-05-14 17:43:12.0', 10, 274027361);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (4.49, '2010-05-14 18:04:09.0', 9, 250764732);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (19.70, '2010-05-14 18:10:09.0', 8, 766244032);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (37.49, '2010-05-14 18:23:08.0', 7, 53395157);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (18.90, '2010-05-14 18:25:56.0', 6, 818358116);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (18.92, '2010-05-14 18:32:03.0', 5, 244956320);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (17.66, '2010-05-14 18:35:07.0', 4, 868642371);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (10.22, '2010-05-14 18:40:38.0', 3, 344549009);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (12.16, '2010-05-14 18:51:58.0', 2, 475644436);
INSERT INTO customer_order (amount, date_created, customer_id, confirmation_number) VALUES (10.75, '2010-05-14 18:56:13.0', 1, 247455344);


--
-- Sample data for table `ordered_product`
--
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (15, 1, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (15, 15, '3');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (15, 3, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (14, 5, '4');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (13, 13, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (13, 4, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (13, 10, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (13, 16, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (12, 1, '3');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (12, 12, '4');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (11, 13, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (11, 2, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (11, 9, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (11, 14, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (11, 16, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (11, 10, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (10, 13, '10');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (9, 8, '5');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (9, 7, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (9, 6, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (9, 5, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (8, 8, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (8, 15, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (8, 11, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (8, 9, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (8, 14, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (8, 16, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (7, 16, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (6, 15, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (6, 9, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (6, 4, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (6, 6, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (6, 3, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (5, 15, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (5, 7, '5');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (4, 8, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (4, 1, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (4, 11, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (4, 14, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (4, 4, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (4, 16, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (3, 1, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (3, 8, '6');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (2, 13, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (2, 5, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (2, 15, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (2, 2, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (2, 11, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (2, 16, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (2, 4, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (1, 12, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (1, 2, '2');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (1, 13, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (1, 10, '1');
INSERT INTO ordered_product (customer_order_id, product_id, quantity) VALUES (1, 8, '1');


--
-- Sample data for table `role`
--
INSERT INTO role (name) VALUES ('ROLE_ADMIN'),('ROLE_USER');


--
-- Sample data for table `member`
--
INSERT INTO member (name, username, password, status, role_id) VALUES ('Danton Hamphire', 'admin', '21232f297a57a5a743894a0e4a801fc3', 1, 1); -- admin/admin
INSERT INTO member (name, username, password, status, role_id) VALUES ('Serkov Anistoin', 'user', 'ee11cbb19052e40b07aac0ca060c23ee', 1, 2); -- user/user
