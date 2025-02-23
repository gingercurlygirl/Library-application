CREATE DATABASE library;
USE library;

CREATE TABLE books (
	id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100) NOT NULL,
    available BOOLEAN
    );
    
CREATE TABLE loans (
	id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    book_id INT,
    FOREIGN KEY (book_id) REFERENCES books(id),
    loan_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    return_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    
INSERT INTO books (title, author, available) VALUES 
("The Lord of the Rings: The Fellowship of the Ring", "J. R. R. Tolkien", true),
("The Lord of the Rings: The Two Towers", "J. R. R. Tolkien", true),
("The Lord of the Rings: The Return of the King", "J. R. R. Tolkien", true),
("The Witcher: The Last Wish", "Andrzej Sapkowski", true),
("The Witcher: Sword of Destiny", "Andrzej Sapkowski", true),
("The Witcher: Blood of Elves", "Andrzej Sapkowski", true),
("Harry Potter: Philosopher's Stone", "J. K. Rowling", true),
("Harry Potter: Chamber of Secrets", "J. K. Rowling", true),
("Harry Potter: Prisoner of Azkaban", "J. K. Rowling", true),
("A Song of Ice and Fire: A Game of Thrones", "George R.R. Martin", true),
("Pippi Longstocking", "Astrid Lindgren", true),
("The Six Bullerby Children", "Astrid Lindgren", true);    
    