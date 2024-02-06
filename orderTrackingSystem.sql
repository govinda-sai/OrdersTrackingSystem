create table customers 
(
    cust_id int identity CONSTRAINT customers_pk primary key, 
    name varchar(30) CONSTRAINT customers_name_nn not null, 
    email varchar(30) CONSTRAINT customers_email_unq UNIQUE, 
    mobile varchar(13) 
)

create table products 
(
    prodid int IDENTITY(100, 1) CONSTRAINT products_pk primary key, 
    prodname varchar(20) CONSTRAINT products_prodname_nn not null, 
    description varchar(50), 
    price money constraint products_price_chk check(price >= 0)
)

create table customer_orders 
(
    order_id int IDENTITY(200, 1) CONSTRAINT orders_pk primary key, 
    order_date datetime, 
    cust_id int CONSTRAINT customer_orders_cust_id REFERENCES customers(cust_id) on delete CASCADE, 
    status varchar(1) CONSTRAINT orders_status_chk check(status in ('n', 'd', 'c')), 
    expected_delivery_date DATETIME
)

create table order_items 
(
    order_id int foreign key REFERENCES customer_orders(order_id) on delete cascade, 
    prodid int foreign key references products(prodid) on delete cascade, 
    qty int CONSTRAINT order_items_qty_chk check(qty > 0),
    price money CONSTRAINT order_items_price_chk check (price >= 0),
    primary key(order_id, prodid)
)

select * from customers 
select * from products 
select * from customer_orders
select * from order_items

-- Inserting 10 rows into the customers table with unique 10-digit mobile numbers starting with 9, 8, 7, or 6
INSERT INTO customers (name, email, mobile) VALUES ('Alice Johnson', 'alice@example.com', '9111222333');
INSERT INTO customers (name, email, mobile) VALUES ('Bob Smith', 'bob@example.com', '8222333444');
INSERT INTO customers (name, email, mobile) VALUES ('Charlie Brown', 'charlie@example.com', '7333444555');
INSERT INTO customers (name, email, mobile) VALUES ('David Lee', 'david@example.com', '6444555666');
INSERT INTO customers (name, email, mobile) VALUES ('Eva Martinez', 'eva@example.com', '5556667777');
INSERT INTO customers (name, email, mobile) VALUES ('Frank Wilson', 'frank@example.com', '6667778888');
INSERT INTO customers (name, email, mobile) VALUES ('Grace Davis', 'grace@example.com', '7778889999');
INSERT INTO customers (name, email, mobile) VALUES ('Henry Young', 'henry@example.com', '8889990000');
INSERT INTO customers (name, email, mobile) VALUES ('Ivy Adams', 'ivy@example.com', '9990001111');
INSERT INTO customers (name, email, mobile) VALUES ('Jack Brown', 'jack@example.com', '6000111222');

-- Inserting data into the products table
INSERT INTO products (prodname, description, price) VALUES ('Laptop', 'High-performance laptop with SSD', 799.99);
INSERT INTO products (prodname, description, price) VALUES ('Smartphone', 'Latest smartphone with high-res camera', 499.99);
INSERT INTO products (prodname, description, price) VALUES ('Tablet', 'Large screen tablet with pen support', 349.99);
INSERT INTO products (prodname, description, price) VALUES ('Headphones', 'Wireless noise-canceling headphones', 149.99);
INSERT INTO products (prodname, description, price) VALUES ('Printer', 'Color inkjet printer with Wi-Fi', 129.99);
INSERT INTO products (prodname, description, price) VALUES ('Smartwatch', 'Fitness tracker with heart rate monitor', 199.99);
INSERT INTO products (prodname, description, price) VALUES ('Gaming Console', 'Latest gaming console with 4K support', 299.99);
INSERT INTO products (prodname, description, price) VALUES ('Camera', 'Mirrorless camera with interchangeable lens', 699.99);
INSERT INTO products (prodname, description, price) VALUES ('External Hard Drive', '1TB portable external hard drive', 79.99);
INSERT INTO products (prodname, description, price) VALUES ('Wireless Router', 'High-speed wireless router with multiple antennas', 89.99);

-- Syntax for changing column name in MS SQL Server
EXEC sp_rename 'products.decription', 'description', 'COLUMN';

-- Inserting 10 rows into the orders table without the time component in the dates and allowing duplicate cust_id for cancelled orders
INSERT INTO customer_orders (order_date, cust_id, status, expected_delivery_date) VALUES 
('2023-10-31', 1, 'n', '2023-11-07'),
('2023-10-31', 2, 'd', '2023-11-05'),
('2023-10-31', 3, 'c', NULL),
('2023-10-31', 4, 'n', '2023-11-06'),
('2023-10-31', 5, 'd', '2023-11-04'),
('2023-10-31', 1, 'c', NULL), -- Allowing duplicate cust_id for cancelled orders
('2023-10-31', 7, 'c', NULL),
('2023-10-31', 8, 'n', '2023-11-09'),
('2023-10-31', 2, 'c', NULL), -- Allowing duplicate cust_id for cancelled orders
('2023-10-31', 10, 'n', '2023-11-01');

insert into customer_orders values ('2023-11-03', 1011, 'n', '2023-11-30')

-- drop table customers
-- drop table products
-- drop table orders
-- drop table order_items

UPDATE order_items 
SET price = (
    SELECT price 
    FROM products p
    WHERE p.prodid = order_items.prodid
) * qty;

INSERT INTO order_items (order_id, prodid, qty, price) VALUES (200, 103, 3, 99.99); -- Mouse
INSERT INTO order_items (order_id, prodid, qty, price) VALUES (201, 105, 2, 249.99); -- Monitor
INSERT INTO order_items (order_id, prodid, qty, price) VALUES (201, 100, 1, 799.99); -- Laptop
INSERT INTO order_items (order_id, prodid, qty, price) VALUES (203, 102, 2, 349.99); -- Tablet
INSERT INTO order_items (order_id, prodid, qty, price) VALUES (204, 104, 3, 79.99); -- Keyboard
INSERT INTO order_items (order_id, prodid, qty, price) VALUES (204, 106, 1, 199.99); -- Gaming Console
INSERT INTO order_items (order_id, prodid, qty, price) VALUES (203, 105, 4, 249.99); -- Monitor
INSERT INTO order_items (order_id, prodid, qty, price) VALUES (207, 107, 1, 699.99); -- Camera
INSERT INTO order_items (order_id, prodid, qty, price) VALUES (209, 106, 2, 299.99); -- Gaming Console
INSERT INTO order_items (order_id, prodid, qty, price) VALUES (209, 108, 3, 79.99); -- External Hard Drive

exec sp_rename 'orders', 'customer_orders'

select * from customer_orders

-- truncate table order_items

insert into customers(name) values ('sample')

-- delete from customer_orders
-- where order_id = 1223 

-- select * from customer_orders