-- Postgresql DATABASE CREATION
-- CREATE DATABASE 
-- When a database is created without "", the name is always in lower case
CREATE DATABASE "VTInstitute";

-- CREATE TABLE 
-- with 3 different fields, one of them is a serial key
CREATE TABLE subjects (
    code SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    year INTEGER NOT NULL
);