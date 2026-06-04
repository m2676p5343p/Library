CREATE TABLE library_items (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    available BOOLEAN
);

CREATE TABLE books (
    id BIGINT PRIMARY KEY REFERENCES library_items(id),
    author VARCHAR(255) NOT NULL
);

CREATE TABLE dvds (
    id BIGINT PRIMARY KEY REFERENCES library_items(id),
    duration FLOAT NOT NULL
);

CREATE TABLE customers(
    id BIGSERIAL PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL
);

INSERT INTO library_items(title, genre, available) VALUES
('Assassin''s Apprentice', 'High Fantasy', TRUE),
('Royal Assassin', 'High Fantasy', TRUE),
('Blood Over Bright Haven', 'Dark Academia', TRUE),
('Project Hail Mary', 'Science Fiction', TRUE),
('Alien', 'Horror', TRUE);

INSERT INTO books(id, author) VALUES
(1, 'Robin Hobb'),
(2, 'Robin Hobb'),
(3, 'M.L. Wang');

INSERT INTO dvds(id, duration) VALUES
(4, 156),
(5, 116);
 
INSERT INTO customers(firstName, lastName) VALUES
('Max', 'Price'),
('James', 'Price'),
('John', 'Smith');

CREATE TABLE checkouts(
    id BIGSERIAL PRIMARY KEY,
    itemId BIGINT REFERENCES library_items(id),
    customerId BIGINT REFERENCES customers(id),
    checkoutDate DATE NOT NULL,
    dueDate DATE NOT NULL
);