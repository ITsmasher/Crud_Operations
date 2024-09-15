# CRUD Operations using JDBC

This project demonstrates a basic implementation of CRUD (Create, Read, Update, Delete) operations in Java using JDBC (Java Database Connectivity). The program allows users to perform operations on an employee database.

## Features

- **Insert**: Add a new employee record to the database.
- **Update**: Modify an existing employee's name using their employee ID.
- **Find**: Retrieve an employee's details using their employee ID.
- **Delete**: Remove an employee record from the database using their employee ID.
- **Fetch**: Retrieve and display all employee records.

## Technologies Used

- **Java**: Core programming language
- **JDBC**: Java Database Connectivity for database operations
- **MySQL**: Database to store employee records

## Prerequisites

To run this project, you need:

- **Java Development Kit (JDK)** (version 8 or higher)
- **MySQL Server** (with a demo database created)
- **MySQL JDBC Driver** (Add `mysql-connector-java` to your project)

## Database Setup

1. Create a MySQL database named `demo`.
2. Create an `emp` table with the following structure:

   ```sql
   CREATE TABLE emp (
       eid INT PRIMARY KEY,
       ename VARCHAR(50),
       sal DOUBLE,
       dept INT,
       address VARCHAR(100)
   );
