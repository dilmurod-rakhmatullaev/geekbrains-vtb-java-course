SQL shell (psql)
Queries:
1. Students who have passed subject with mark > 3;
2. Average mark by subject;
3. Average mark by student.

Server [localhost]:
Database [postgres]:
Port [5432]:
Username [postgres]:
Password for user postgres:

psql (18.3)
WARNING: Console code page (437) differs from Windows code page (1252)
         8-bit characters might not work correctly. See psql reference
         page "Notes for Windows users" for details.
Type "help" for help.

postgres=# \dt
            List of tables
 Schema |   Name   | Type  |  Owner
--------+----------+-------+----------
 public | demo     | table | postgres
 public | isodemo  | table | postgres
 public | modes    | table | postgres
 public | progress | table | postgres
(4 rows)


postgres=# CREATE TABLE students (id SERIAL PRIMARY KEY, name VARCHAR(255), passport_series VARCHAR(255), passport_number VARCHAR(255), UNIQUE(passport_series, passport_number));
CREATE TABLE
    postgres=# \d students
                                        Table "public.students"
     Column      |          Type          | Collation | Nullable |               Default
-----------------+------------------------+-----------+----------+--------------------------------------
 id              | integer                |           | not null | nextval('students_id_seq'::regclass)
 name            | character varying(255) |           |          |
 passport_series | character varying(255) |           |          |
 passport_number | character varying(255) |           |          |
Indexes:
    "students_pkey" PRIMARY KEY, btree (id)
    "students_passport_series_passport_number_key" UNIQUE CONSTRAINT, btree (passport_series, passport_number)


postgres=# CREATE TABLE subjects (id SERIAL PRIMARY KEY, subject VARCHAR(255));
CREATE TABLE
    postgres=# CREATE TABLE progress (id SERIAL PRIMARY KEY, student_id INTEGER REFERENCES students(id) ON DELETE CASCADE, subject_id INTEGER REFERENCES subject(id), mark INTEGER CHECK (mark >= 2 AND mark <= 5));
ERROR:  relation "progress" already exists
postgres=# DROP TABLE progress;
DROP TABLE
    postgres=# CREATE TABLE progress (id SERIAL PRIMARY KEY, student_id INTEGER REFERENCES students(id) ON DELETE CASCADE, subject_id INTEGER REFERENCES subject(id), mark INTEGER CHECK (mark >= 2 AND mark <= 5));
ERROR:  relation "subject" does not exist
postgres=# CREATE TABLE progress (id SERIAL PRIMARY KEY, student_id INTEGER REFERENCES students(id) ON DELETE CASCADE, subject_id INTEGER REFERENCES subjects(id), mark INTEGER CHECK (mark >= 2 AND mark <= 5));
CREATE TABLE
    postgres=# INSERT INTO students (name, passport_series, passport_number) VALUES ('Alex', '1234', '56789'), ('Bob', '2345', '67890'), ('John', '3456', '78901');
INSERT 0 3
postgres=# INSERT INTO subjects (subject) VALUES ('Math', 'Physics', 'Chemistry');
ERROR:  INSERT has more expressions than target columns
LINE 1: INSERT INTO subjects (subject) VALUES ('Math', 'Physics', 'C...
                                                       ^
postgres=# INSERT INTO subjects (subject) VALUES ('Math'), ('Physics'), ('Chemistry');
INSERT 0 3
postgres=# INSERT INTO progress (student_id, subject_id, mark) VALUES (1, 1, 5), (1, 2, 4), (1, 3, 3), (2, 1, 4), (2, 2, 3), (2, 2, 5), (3, 1, 3), (3, 2, 3), (3, 3, 3);
INSERT 0 9
postgres=# SELECT s.name, sub.subject, p.mark
postgres-# FROM progress p
postgres-# JOIN students s ON s.id = p.student_id
postgres-# JOIN subjects sub ON sub.id = p.subject_id
postgres-# WHERE sub.subject = 'Math' AND p.mark > 3;
 name | subject | mark
------+---------+------
 Alex | Math    |    5
 Bob  | Math    |    4
(2 rows)


postgres=# SELECT AVG(mark)
postgres-# FROM progress
postgres-# WHERE subject_id = (SELECT id FROM subjects WHERE name = 'Physics');
ERROR:  column "name" does not exist
LINE 3: WHERE subject_id = (SELECT id FROM subjects WHERE name = 'Ph...
                                                          ^
postgres=# SELECT AVG(mark)
postgres-# FROM progress
postgres-# WHERE subject_id = (SELECT id FROM subjects WHERE subject = 'Physics');
        avg
--------------------
 3.7500000000000000
(1 row)


postgres=# SELECT AVG(mark)
postgres-# FROM progress
postgres-# WHERE student_id = 2;
        avg
--------------------
 4.0000000000000000
(1 row)


postgres=#