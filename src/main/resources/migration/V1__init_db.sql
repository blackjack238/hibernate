-- Створення таблиці Client (клієнт)
CREATE TABLE Client (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 200)
);

-- Створення таблиці Planet (планета)
CREATE TABLE Planet (
    id VARCHAR(3) PRIMARY KEY CHECK (REGEXP_LIKE(id, '^[A-Z0-9]+$')),
    name VARCHAR(500) NOT NULL CHECK (LENGTH(name) >= 1 AND LENGTH(name) <= 500)
);

-- Створення таблиці Ticket (квиток)
CREATE TABLE Ticket (
    id INT PRIMARY KEY AUTO_INCREMENT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    client_id INT NOT NULL,
    from_planet_id VARCHAR(3) NOT NULL,
    to_planet_id VARCHAR(3) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (from_planet_id) REFERENCES Planet(id),
    FOREIGN KEY (to_planet_id) REFERENCES Planet(id)
);
