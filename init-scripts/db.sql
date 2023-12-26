CREATE DATABASE IF NOT EXISTS meli;

USE meli;

CREATE TABLE users (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    date_of_birth DATE NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    INDEX idx_name (name),
    INDEX idx_cpf (cpf)
);

-- idx-name to improve query by name
-- idx-cpf to improve exists by cpf