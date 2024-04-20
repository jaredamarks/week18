DROP TABLE IF EXISTS customer_item;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS loaf_store;

CREATE TABLE loaf_store (
	loaf_store_id int NOT NULL AUTO_INCREMENT,
	loaf_store_name varchar(256) NOT NULL,
	loaf_store_info text NOT NULL,
	PRIMARY KEY (loaf_store_id)
);

CREATE TABLE customer (
	customer_id int NOT NULL AUTO_INCREMENT,
	loaf_store_id int NOT NULL,
	customer_first_name varchar(60) NOT NULL,
	customer_last_name varchar(60) NOT NULL,
	customer_info text,
	PRIMARY KEY(customer_id),
	FOREIGN KEY (loaf_store_id) REFERENCES loaf_store (loaf_store_id)
);

CREATE TABLE item (
	item_id int NOT NULL AUTO_INCREMENT,
	item_info varchar(128),
	PRIMARY KEY(item_id)
);

CREATE TABLE customer_item (
	customer_id int NOT NULL,
	item_id int NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
	FOREIGN KEY (item_id) REFERENCES item (item_id)
);