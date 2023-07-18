-- V2__populate_db.sql

-- Додаємо 5 планет
INSERT INTO Planet (id, name) VALUES
    ('MARS', 'Марс'),
    ('VEN', 'Венера'),
    ('JUP', 'Юпітер'),
    ('SAT', 'Сатурн'),
    ('URAN', 'Уран');

-- Додаємо 10 клієнтів
INSERT INTO Client (name) VALUES
    ('Іванов Іван'),
    ('Петров Петро'),
    ('Сидорова Олена'),
    ('Коваль Микола'),
    ('Мельник Людмила'),
    ('Семенко Олександр'),
    ('Соловйова Юлія'),
    ('Коваленко Марія'),
    ('Тимошенко Анна'),
    ('Грищенко Олег');

-- Додаємо 10 квитків
INSERT INTO Ticket (client_id, from_planet_id, to_planet_id) VALUES
    (1, 'MARS', 'VEN'),
    (2, 'VEN', 'MARS'),
    (3, 'JUP', 'URAN'),
    (4, 'SAT', 'MARS'),
    (5, 'URAN', 'VEN'),
    (6, 'MARS', 'SAT'),
    (7, 'VEN', 'JUP'),
    (8, 'JUP', 'MARS'),
    (9, 'SAT', 'VEN'),
    (10, 'URAN', 'JUP');
