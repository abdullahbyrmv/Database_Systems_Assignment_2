CREATE TABLE book
(
    book_id SERIAL PRIMARY KEY,
    title   VARCHAR(255),
    genre   VARCHAR(100),
    stock   INTEGER
);

CREATE TABLE author
(
    author_id      SERIAL PRIMARY KEY,
    author_name    VARCHAR(100),
    author_surname VARCHAR(100)
);

CREATE TABLE book_detail
(
    book_id   INTEGER REFERENCES book (book_id),
    author_id INTEGER REFERENCES author (author_id),
    PRIMARY KEY (book_id, author_id)
);
CREATE TABLE customer
(
    customer_id      SERIAL PRIMARY KEY,
    customer_name    VARCHAR(100),
    customer_surname VARCHAR(100),
    address          VARCHAR(255),
    email            VARCHAR(255)
);

CREATE TABLE orders
(
    order_id    SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customer (customer_id),
    order_date  DATE
);

CREATE TABLE order_detail
(
    order_id                INTEGER REFERENCES orders (order_id),
    book_id                 INTEGER REFERENCES book (book_id),
    number_of_ordered_books INTEGER,
    PRIMARY KEY (order_id, book_id)
);
