DROP TABLE IF EXISTS customer_item;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS store;

CREATE TABLE store (
	store_id int NOT NULL AUTO INCREMENT,
	store_name varchar(256) NOT NULL,
	store_info varchar(512) NOT NULL,
	PRIMARY KEY (store_id)
);

CREATE TABLE customer (
	customer_id int NOT NULL AUTO_INCREMENT,
	store_id int NULL,
	customer_first_name varchar(60) NOT NULL,
	customer_last_name varchar(60) NOT NULL,
	customer_info varchar(512),
	PRIMARY KEY(customer_id),
	FOREIGN KEY (store_id) REFERENCES store (store_id)
);

CREATE TABLE item (
	item_id int NOT NULL AUTO_INCREMENT,
	item_info varchar(128),
	PRIMARY KEY(item_id)
);

CREATE TABLE customer_item (
	customer_id int NOT NULL,
	item_id int NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE,
	FOREIGN KEY (item_id) REFERENCES item (item_id) ON DELETE CASCADE
);