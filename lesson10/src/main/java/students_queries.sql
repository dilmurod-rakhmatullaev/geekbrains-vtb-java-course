-- PostgreSQL practice: students table

-- Create table
CREATE TABLE students (id serial, name text, score int);

-- Insert data
INSERT INTO students (name, score) VALUES ('Bob', 100);
INSERT INTO students (name, score) VALUES ('John', 90) RETURNING *;
INSERT INTO students (name, score) VALUES ('Tom', 80), ('Alex', 85), ('Lucy', 95) RETURNING *;

-- Basic queries
SELECT * FROM students;
SELECT * FROM students WHERE id > 1;
SELECT name FROM students WHERE id > 2;
SELECT * FROM students WHERE id > 1 ORDER BY score DESC;

-- Update and Delete
UPDATE students SET score = 60 WHERE name = 'Tom' RETURNING *;
DELETE FROM students WHERE score = 100 RETURNING *;

-- More data
INSERT INTO students (name, score) VALUES ('Sam', 75);
SELECT * FROM students;

-- LIKE patterns
SELECT * FROM students WHERE name LIKE 'L%';   -- starts with L
SELECT * FROM students WHERE name LIKE '%m';   -- ends with m
SELECT * FROM students WHERE name LIKE '_o_';  -- 3 letters, second is 'o'
SELECT * FROM students WHERE name LIKE '_o%';  -- second letter is 'o'

-- BETWEEN
SELECT * FROM students WHERE score BETWEEN 50 and 85;

-- Computed columns
SELECT name, score, score / 5 another_score FROM students;

-- DISTINCT
SELECT DISTINCT score FROM students;

-- Pagination
SELECT * FROM students ORDER BY score DESC LIMIT 3;
SELECT * FROM students ORDER BY score DESC LIMIT 3 OFFSET 3;

-- Batch update
UPDATE students SET score = 50 RETURNING *;
UPDATE students SET score = 40 WHERE id = 4 RETURNING *;

-- CASE statement
SELECT name, score,
       CASE
           WHEN score = 50 THEN 'A'
           WHEN score = 40 THEN 'B'
           ELSE 'C'
           END AS mark
FROM students;