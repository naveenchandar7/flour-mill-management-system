CREATE DATABASE IF NOT EXISTS flour_mill;

USE flour_mill;

CREATE TABLE app_user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(100) NOT NULL,
    mobile VARCHAR(15),
    village VARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE service (
    service_id INT PRIMARY KEY AUTO_INCREMENT,
    service_name VARCHAR(100) NOT NULL,
    rate DECIMAL(10,2) NOT NULL,
    status BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE bill (
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    bill_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10,2) NOT NULL,
    payment_status VARCHAR(20) DEFAULT 'PAID',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_bill_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id)
);

CREATE TABLE bill_item (
    bill_item_id INT PRIMARY KEY AUTO_INCREMENT,
    bill_id INT NOT NULL,
    service_id INT NOT NULL,
    quantity DECIMAL(10,2) NOT NULL,
    rate DECIMAL(10,2) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_billitem_bill
        FOREIGN KEY (bill_id)
        REFERENCES bill(bill_id),

    CONSTRAINT fk_billitem_service
        FOREIGN KEY (service_id)
        REFERENCES service(service_id)
);

CREATE TABLE electricity_usage (
    usage_id INT PRIMARY KEY AUTO_INCREMENT,
    usage_date DATE NOT NULL UNIQUE,
    start_time TIME NOT NULL,
    start_unit DECIMAL(10,2) NOT NULL,
    end_time TIME,
    end_unit DECIMAL(10,2),
    units_used DECIMAL(10,2),
    rate_per_unit DECIMAL(10,2) NOT NULL,
    electricity_cost DECIMAL(10,2),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);