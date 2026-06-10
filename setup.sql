CREATE TABLE library_items (
    id BIGSERIAL PRIMARY KEY,
    category VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    available BOOLEAN,
    author VARCHAR(255),
    duration FLOAT,
    numPages INT
);

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
    itemId BIGINT REFERENCES library_items(id),
    customerId BIGINT REFERENCES customers(id),
    checkoutDate DATE NOT NULL,
    dueDate DATE NOT NULL
);