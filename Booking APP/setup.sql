-- Create the database
CREATE DATABASE IF NOT EXISTS iBarberShop;
USE iBarberShop;

-- Create accounts table
CREATE TABLE IF NOT EXISTS accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, 
    phone_number VARCHAR(20) NOT NULL,
    uuid VARCHAR(36) NOT NULL UNIQUE
);

-- Create appointments table
CREATE TABLE IF NOT EXISTS appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    top_length VARCHAR(50),
    buzz_cut VARCHAR(50),
    thin_out VARCHAR(50),
    fade_type VARCHAR(50),
    taper_type VARCHAR(50),
    side_type VARCHAR(50),
    design VARCHAR(50),
    beard VARCHAR(50),
    line_up VARCHAR(50),
    appointment_date DATE,
    time VARCHAR(50), 
    cost VARCHAR(50), 
    unique_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (unique_id) REFERENCES accounts(uuid)
);

INSERT INTO accounts (first_name, last_name, email, password, phone_number, uuid)
VALUES
('admin', 'test', 'admin@example.com', 'asdasdasd', '6912345678', '123e4567-e89b-12d3-a456-426614174000');

INSERT INTO appointments (top_length, buzz_cut, thin_out, fade_type, taper_type, side_type, design, beard, line_up, appointment_date, time, cost, unique_id)
VALUES
('4', 'Yes', 'No', 'High', 'Medium', 'Short', 'None', 'Trimmed', 'Yes', '2024-08-03', '400 min - 650 min', '20.00', '123e4567-e89b-12d3-a456-426614174000');
