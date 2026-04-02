-- =====================================================
-- PostgreSQL Practice - Course Lesson 10
-- Topics: DDL, DML, Constraints, Indexes, Views, MV, Transactions, Isolation Levels
-- =====================================================

-- 1. CREATE TABLE with constraints
CREATE TABLE students (
                          id SERIAL PRIMARY KEY,
                          name TEXT,
                          score INTEGER
);

CREATE TABLE progress (
                          id SERIAL,
                          subject TEXT,
                          mark INTEGER CHECK (mark >= 2 AND mark <= 5),
                          student_id INTEGER REFERENCES students(id)
);

-- 2. CRUD Operations
INSERT INTO students (name, score) VALUES ('John', 90), ('Lucy', 95), ('Tom', 60);
INSERT INTO progress (subject, mark, student_id) VALUES ('chemistry', 4, 1);

SELECT * FROM students WHERE score > 75;
SELECT * FROM students ORDER BY score DESC LIMIT 3 OFFSET 1;

UPDATE students SET score = 80 WHERE name = 'John' RETURNING *;
DELETE FROM students WHERE score < 50 RETURNING *;

-- 3. Constraints
ALTER TABLE students ADD PRIMARY KEY (id);
ALTER TABLE progress ADD FOREIGN KEY (student_id) REFERENCES students(id);
ALTER TABLE progress ADD COLUMN description TEXT;

-- 4. Indexes
CREATE INDEX idx_students_name ON students (name);
CREATE UNIQUE INDEX idx_students_desc ON students (description);
DROP INDEX idx_students_name;

-- 5. Views & Materialized Views
CREATE VIEW students_view AS SELECT name, score FROM students WHERE score > 50;
CREATE MATERIALIZED VIEW students_mv AS SELECT name, score FROM students WHERE score > 50;
REFRESH MATERIALIZED VIEW students_mv;

-- 6. Transactions & Isolation Levels
-- READ COMMITTED (default)
BEGIN TRANSACTION ISOLATION LEVEL READ COMMITTED;
UPDATE students SET score = 100 WHERE id = 1;
COMMIT;

-- REPEATABLE READ
BEGIN TRANSACTION ISOLATION LEVEL REPEATABLE READ;
SELECT * FROM students WHERE id = 1;
UPDATE students SET score = 90 WHERE id = 1;
COMMIT;

-- SERIALIZABLE
BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;
UPDATE students SET score = 95 WHERE id = 1;
COMMIT;

-- 7. Row-level locking
BEGIN;
SELECT * FROM students WHERE id = 1 FOR UPDATE;
UPDATE students SET score = 85 WHERE id = 1;
COMMIT;

-- 8. JOIN
SELECT s.name, p.subject, p.mark
FROM progress p
         JOIN students s ON s.id = p.student_id;

-- 9. LIKE / Pattern Matching
SELECT * FROM students WHERE name LIKE 'J%';
SELECT * FROM students WHERE name LIKE '%n';

-- 10. CASE expression
SELECT name, score,
       CASE
           WHEN score >= 90 THEN 'A'
           WHEN score >= 75 THEN 'B'
           WHEN score >= 60 THEN 'C'
           ELSE 'D'
           END AS grade
FROM students;

-- 11. Show transaction isolation level
SHOW default_transaction_isolation;