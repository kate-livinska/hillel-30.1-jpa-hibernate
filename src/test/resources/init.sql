--
--
--
-- schema
--
--
--
CREATE table t_students (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            first_name VARCHAR(100),
                            last_name VARCHAR(100),
                            email VARCHAR(256)
);

CREATE table t_homeworks (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             description VARCHAR(300),
                             deadline DATE,
                             mark INT,
                             student_id INT,
                             FOREIGN KEY (student_id) REFERENCES t_students(id)
);

INSERT INTO t_students (first_name, last_name, email)
VALUES
    ('John', 'Doe', 'john.doe@example.com'),
    ('Jane', 'Smith', 'jane.smith@example.com'),
    ('Alice', 'Johnson', 'alice.johnson@example.com'),
    ('Bob', 'Brown', 'bob.brown@example.com'),
    ('Charlie', 'Davis', 'charlie.davis@example.com');

INSERT INTO t_homeworks (description, deadline, mark, student_id)
VALUES
    ('Math homework on algebra', '2025-02-10', 85, 1),
    ('History assignment on Ancient Rome', '2025-02-15', 90, 1),
    ('Science project on Chemistry', '2025-03-01', 88, 1),
    ('Essay on Shakespeare', '2025-02-20', 92, 2),
    ('Physics homework', '2025-02-25', 89, 2),
    ('Programming assignment in Java', '2025-02-28', 95, 3),
    ('Essay on Data Structures', '2025-03-05', 87, 3),
    ('English grammar practice', '2025-02-18', 83, 4),
    ('Geography quiz on countries', '2025-02-22', 88, 4),
    ('Research paper on AI', '2025-02-25', 91, 5),
    ('Math exam preparation', '2025-03-03', 90, 5);