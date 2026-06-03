CREATE TABLE library_items (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    available BOOLEAN
);

CREATE TABLE books (
    author VARCHAR(255) NOT NULL
) INHERITS (library_items);

CREATE TABLE dvds (
    duration REAL NOT NULL
) INHERITS (library_items);

CREATE TABLE customers(
    id BIGSERIAL PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL
);

INSERT INTO books(title, author, genre, available) VALUES
('Assassin''s Apprentice', 'Robin Hobb', 'High Fantasy', TRUE),
('Royal Assassin', 'Robin Hobb', 'High Fantasy', TRUE),
('Blood Over Bright Haven', 'M.L. Wang', 'Dark Academia', TRUE);

INSERT INTO dvds(title, duration, genre, available) VALUES
('Project Hail Mary', 156.0, 'Science Fiction', TRUE),
('Alien', 116.0, 'Horror', TRUE);

INSERT INTO customers(firstName, lastName) VALUES
('Max', 'Price'),
('James', 'Price'),
('John', 'Smith');

CREATE TABLE checkouts(
    id BIGSERIAL PRIMARY KEY,
    bookId BIGINT REFERENCES library_items(id),
    customerId BIGINT REFERENCES customers(id),
    checkoutDate DATE NOT NULL,
    dueDate DATE NOT NULL
);