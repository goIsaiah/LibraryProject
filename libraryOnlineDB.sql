-- DATABASE SCRIPT FOR LIBRARY PROJECT
CREATE DATABASE IF NOT EXISTS sql9609229; 

-- create the tables 

USE sql9609229; 


SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS BOOKLISTS;
DROP TABLE IF EXISTS COMMENTS; 
DROP TABLE IF EXISTS CRITICS;
DROP TABLE IF EXISTS USERTABLE; 
DROP TABLE IF EXISTS LIBRARY;
DROP TABLE IF EXISTS STATUSTABLE; 
DROP TABLE IF EXISTS BOOKLISTSWGenre; 



-- Creates the book library table where all the books are stored 
CREATE TABLE IF NOT EXISTS LIBRARY ( 
LIB_ID INT NOT NULL auto_increment PRIMARY KEY, 
LIB_TITLE varchar(255) NOT NULL, 
LIB_AUTHOR varchar(255) NOT NULL, 
LIB_YEAR integer NOT NULL, 
LIB_ISBN varchar(255) NOT NULL, 
GENRE varchar(255) UNIQUE KEY,
UNIQUE KEY ( LIB_TITLE, LIB_ISBN)
);

-- creates the table for user information 
CREATE TABLE IF NOT EXISTS USERTABLE(
user_id INT auto_increment unique PRIMARY KEY, 
USERNAME varchar(255) NOT NULL,
PASSWORD varchar(255) NOT NULL, 
EMAIL varchar(255) NOT NULL UNIQUE, 
UNIQUE KEY ( USERNAME )
);

CREATE TABLE BOOKLISTSWGenre(
	usrname VARCHAR(255), 
    usr_id INT ,
	book_id INT , 
	book_title VARCHAR(255) NOT NULL, 
    book_isbn VARCHAR(255) NOT NULL, 
    book_genre VARCHAR(255),
    FOREIGN KEY(usr_id) REFERENCES USERTABLE(user_id),
    FOREIGN KEY(usrname) REFERENCES USERTABLE(USERNAME),
	FOREIGN KEY(book_id) REFERENCES LIBRARY(LIB_ID),
    FOREIGN KEY(book_title, book_isbn) REFERENCES LIBRARY(LIB_TITLE, LIB_ISBN), 
	FOREIGN KEY (book_genre) REFERENCES LIBRARY(GENRE)
);

CREATE TABLE IF NOT EXISTS USERINFO (
  info_id INT auto_increment unique PRIMARY KEY,
  user_id INT NOT NULL,
  URL varchar(255),
  TEXTFIELD varchar(255),
  FIRSTNAME varchar(255),
  LASTNAME varchar(255),
  FOREIGN KEY (user_id) REFERENCES USERTABLE(user_id)
);

CREATE TABLE COMMENTS(
	usrname VARCHAR(255), 
    usr_id INT,
	book_id INT, 
	book_title VARCHAR(255) NOT NULL, 
	comment TEXT ,
    FOREIGN KEY(usr_id) REFERENCES USERTABLE(user_id),          --  usr_id == USERTABLE.user_id
    FOREIGN KEY(usrname) REFERENCES USERTABLE(USERNAME),        --  usrname == USERTABLE.USERNAME
    FOREIGN KEY(book_title) REFERENCES LIBRARY(LIB_TITLE),      --  book_title == LIBRARY.LIB_TITLE
	FOREIGN KEY (book_id) REFERENCES LIBRARY(LIB_ID)            -- book_id == LIBRARY.LIB_ID
);

CREATE TABLE BOOKLISTS(
	usrname VARCHAR(255), 
    usr_id INT,
	book_id INT, 
	book_title VARCHAR(255) NOT NULL, 
    book_isbn VARCHAR(255) NOT NULL, 
    FOREIGN KEY(usr_id) REFERENCES USERTABLE(user_id),
    FOREIGN KEY(usrname) REFERENCES USERTABLE(USERNAME),
    FOREIGN KEY(book_title, book_isbn) REFERENCES LIBRARY(LIB_TITLE, LIB_ISBN), -- book_title == LIBRARY.LIB_TITLE &&  book_isbn == LIBRARY.LIB_ISBN
	FOREIGN KEY (book_id) REFERENCES LIBRARY(LIB_ID)
);


CREATE TABLE CRITICS(
	usrname VARCHAR(255),
    user_id INT NOT NULL auto_increment PRIMARY KEY,
    book_id INT NOT NULL,
    book_title VARCHAR(255) NOT NULL,
    message TEXT,
    rating INT NOT NULL,
    FOREIGN KEY(user_id) REFERENCES USERTABLE(user_id),          --  usr_id == USERTABLE.user_id
    FOREIGN KEY(usrname) REFERENCES USERTABLE(USERNAME),        --  usrname == USERTABLE.USERNAME
    FOREIGN KEY(book_title) REFERENCES LIBRARY(LIB_TITLE) , 
    FOREIGN KEY (book_id) REFERENCES LIBRARY(LIB_ID)--  book_title == LIBRARY.LIB_TITLE
); 

CREATE TABLE STATUSTABLE(
	TITLE VARCHAR(255),
    USER VARCHAR(255),
    MONTH INT NOT NULL,
    DAY INT NOT NULL,
    YEAR INT NOT NULL
);



SET FOREIGN_KEY_CHECKS=ON; 

-- Populate the tabeles 

INSERT INTO USERTABLE (USERNAME, PASSWORD, EMAIL) VALUES 
('Polywertz' , '123456', 'Polywertz@gmail.com' ),
('JohnDoe', 'him123', 'john_doe@gmail.com') ,
('janewest', 'her5676' , 'jane@gmail.com') ;

INSERT INTO LIBRARY ( LIB_TITLE, LIB_AUTHOR, LIB_ISBN, LIB_YEAR) VALUES 
('To Kill a Mockingbird',  'Harper Lee' , '0446310789', '1960' ), 
('1984',  'George Orwell' , '0446310789', '1949' ),
('Pride and Prejudice',  'Pride and Prejudice' , '0446310789', '1813' ), 
('Harry Potter and the Sorcerer s Stone',  'J. K. Rowling' , '1338596705', '2020' ), 
('Diary of a Wimpy Kid',  'Jeff Kinney' , '0141324902', '2007' ), 
('The Great Gatsby' ,  'F. Scott Fitzgerald', '9780743273565', '1925'), 
('Fahrenheit 451', 'Ray Bradbury' ,'9781451673319', '2011') , 
('Murder on the Orient Express', 'Agatha Christie', '9780007119318', '2007')
;

INSERT INTO BOOKLISTS(usrname, usr_id, book_id, book_title, book_isbn) VALUES 

('Polywertz', 1, 2, '1984',  '0446310789'), 
('Polywertz', 1, 3, 'Pride and Prejudice',  '0446310789'),
('Polywertz', 1, 6, 'The Great Gatsby',  '9780743273565');











