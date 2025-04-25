
INSERT INTO Author (name) VALUES ('J.K. Rowling');
INSERT INTO Author  (name) VALUES ('George R.R. Martin');
INSERT INTO Author  (name) VALUES ('J.R.R. Tolkien');
INSERT INTO Author  (name) VALUES ('Agatha Christie');

INSERT INTO Genre (name) VALUES ('Fantasy');
INSERT INTO Genre (name) VALUES ('Mystery');
INSERT INTO Genre (name) VALUES ('Thriller');
INSERT INTO Genre (name) VALUES ('Science Fiction');

INSERT INTO Book (title, author_id, genre_id, price) VALUES ('Harry Potter', 1, 1, 39.99);
INSERT INTO Book (title, author_id, genre_id, price) VALUES ('A Game of Thrones', 2, 1, 49.99);
INSERT INTO Book (title, author_id, genre_id, price) VALUES ('The Hobbit', 3, 1, 29.99);
INSERT INTO Book (title, author_id, genre_id, price) VALUES ('Murder on the Orient Express', 4, 2, 19.99);
INSERT INTO Book (title, author_id, genre_id, price) VALUES ('The Catcher in the Rye', 4, 3, 14.99);
INSERT INTO Book (title, author_id, genre_id, price) VALUES ('The Martian', 3, 4, 24.99);

INSERT INTO Customer (email) VALUES ('john.doe@example.com');
INSERT INTO Customer (email) VALUES ('jane.smith@example.com');

INSERT INTO Orders (customer_id) VALUES (1);
INSERT INTO Orders (customer_id) VALUES (2);

INSERT INTO Order_item (order_id, book_id, quantity) VALUES (1, 1, 2);  
INSERT INTO Order_item (order_id, book_id, quantity) VALUES (2, 3, 1);