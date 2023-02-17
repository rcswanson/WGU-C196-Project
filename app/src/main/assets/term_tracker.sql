DROP TABLE IF EXISTS term;

CREATE TABLE term (
  term_id INTEGER PRIMARY KEY AUTOINCREMENT,
  term_name TEXT NOT NULL,
  start_date TEXT NOT NULL,
  end_date TEXT NOT NULL
);

DROP TABLE IF EXISTS course;

CREATE TABLE course (
  course_id INTEGER PRIMARY KEY,
  course_name TEXT,
  start_date TEXT,
  end_date TEXT,
  term_id INTEGER,
  FOREIGN KEY (term_id) REFERENCES term (term_id)
);

DROP TABLE IF EXISTS assessment;

CREATE TABLE assessment (
  assessment_id INTEGER PRIMARY KEY,
  assessment_name TEXT,
  assessment_type TEXT,
  due_date TEXT,
  course_id INTEGER,
  FOREIGN KEY (course_id) REFERENCES course (course_id)
);
