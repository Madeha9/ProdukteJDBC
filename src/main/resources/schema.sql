-- SQL schema for the product table
--Table struktur , created_at ist Automatisch gesezt in db, braucht kein zugriff
--CREATE TABLE product(
    id BIGINT PRIMARY KEY;
    name VARCHAR(255) NOT NULL
    price DECIMAL(10,2) NOT NULL,
    active BOOLEAN NOT NULL,
	category  VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
SELECT * FROM product;
commit ;