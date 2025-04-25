CREATE TABLE Author (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE Genre (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id BIGINT,
    genre_id BIGINT,
    price DECIMAL(10, 2),

    CONSTRAINT fk_book_author FOREIGN KEY (author_id) REFERENCES Author(id),
    CONSTRAINT fk_book_genre FOREIGN KEY (genre_id) REFERENCES Genre(id)
);

CREATE TABLE Customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE Orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT,

    CONSTRAINT fk_orders_customer FOREIGN KEY (customer_id) REFERENCES Customer(id)
);

CREATE TABLE Order_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    book_id BIGINT,
    quantity INT,

    CONSTRAINT fk_order_item_order FOREIGN KEY (order_id) REFERENCES Orders(id),
    CONSTRAINT fk_order_item_book FOREIGN KEY (book_id) REFERENCES Book(id)
);
