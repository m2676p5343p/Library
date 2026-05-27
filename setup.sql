CREATE TABLE books(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    available BOOLEAN
);

INSERT INTO books(title, author, genre, available) VALUES
('Assassin''s Apprentice', 'Robin Hobb', 'High Fantasy', TRUE),
('Royal Assassin', 'Robin Hobb', 'High Fantasy', TRUE),
('Blood Over Bright Haven', 'M.L. Wang', 'Dark Academia', TRUE);

CREATE TABLE customers(
    id BIGSERIAL PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL
);

INSERT INTO customers(firstName, lastName) VALUES
('Max', 'Price'),
('James', 'Price'),
('John', 'Smith');

CREATE TABLE checkouts(
    id BIGSERIAL PRIMARY KEY,
    bookId BIGINT REFERENCES books(id),
    customerId BIGINT REFERENCES customers(id),
    checkoutDate DATE NOT NULL
);