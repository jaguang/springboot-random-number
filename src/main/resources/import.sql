INSERT INTO unit (id, code, description, created_date) VALUES (1, 'g', 'Kilogram', '2021-01-14');
INSERT INTO unit (id, code, description, created_date) VALUES (2, 'u', 'Unit', '2021-01-14');

INSERT INTO item (id, name, price, unit_id, created_date) VALUES (1, 'Mobil', 3000, 2, '2021-01-14');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (2, 'Motor',1200, 2, '2021-01-14');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (3, 'Emas',100, 1, '2021-01-14');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (4, 'laptop',700, 2, '2021-01-14');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (5, 'TV LED',200, 2, '2021-01-14');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (6, 'Hanphone',300, 2, '2021-01-14');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (7, 'kamera',500, 2, '2021-01-14');
INSERT INTO item (id, name, price, unit_id, created_date) VALUES (8, 'Jam Tanga',80, 2, '2021-01-14');

INSERT INTO stock (item_id, quantity, created_date, total_price) VALUES (1, 1, '2021-01-14', ((SELECT (item.price * 1) FROM item WHERE item.id = 1)));
INSERT INTO stock (item_id, quantity, created_date, total_price) VALUES (2, 5, '2021-01-14', ((SELECT (item.price * 5) FROM item WHERE item.id = 2)));
INSERT INTO stock (item_id, quantity, created_date, total_price) VALUES (3, 10, '2021-01-14', ((SELECT (item.price * 10) FROM item WHERE item.id = 3)));
INSERT INTO stock (item_id, quantity, created_date, total_price) VALUES (4, 10, '2021-01-14', ((SELECT (item.price * 10) FROM item WHERE item.id = 4)));
INSERT INTO stock (item_id, quantity, created_date, total_price) VALUES (5, 50, '2021-01-14', ((SELECT (item.price * 50) FROM item WHERE item.id = 5)));
INSERT INTO stock (item_id, quantity, created_date, total_price) VALUES (6, 20, '2021-01-14', ((SELECT (item.price * 20) FROM item WHERE item.id = 6)));
INSERT INTO stock (item_id, quantity, created_date, total_price) VALUES (7, 15, '2021-01-14', ((SELECT (item.price * 15) FROM item WHERE item.id = 7)));
INSERT INTO stock (item_id, quantity, created_date, total_price) VALUES (8, 100, '2021-01-14', ((SELECT (item.price * 100) FROM item WHERE item.id = 8)));


INSERT INTO customer (id, name, address) VALUES (1, 'gemoy', 'ragunan');
INSERT INTO customer (id, name, address) VALUES (2, 'arif', 'jakarta');
INSERT INTO customer (id, name, address) VALUES (3, 'rifki', 'bekasi');
INSERT INTO customer (id, name, address) VALUES (4, 'naufal', 'jakarta selatan');
INSERT INTO customer (id, name, address) VALUES (5, 'dio', 'mampang');
INSERT INTO customer (id, name, address) VALUES (6, 'dika', 'jakarta barat');
INSERT INTO customer (id, name, address) VALUES (7, 'salaman', 'ragunan');
INSERT INTO customer (id, name, address) VALUES (8, 'irfa', 'jakarta selatan');
INSERT INTO customer (id, name, address) VALUES (9, 'wisa', 'bekasi');
INSERT INTO customer (id, name, address) VALUES (10, 'ari', 'bsd');
INSERT INTO customer (id, name, address) VALUES (11, 'mario', 'bsd');
INSERT INTO customer (id, name, address) VALUES (12, 'susi', 'banten');

