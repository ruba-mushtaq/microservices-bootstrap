CREATE TABLE IF NOT EXISTS products (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(500),
    price DOUBLE,
    stock INT
);