CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    product_ids VARCHAR(1000),
    total_amount DOUBLE,
    status VARCHAR(50),
    created_at TIMESTAMP
);
