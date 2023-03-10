-- DATABASE SCRIPT FOR LIBRARY PROJECT
CREATE DATABASE IF NOT EXISTS myDB; 

-- create the tables 

USE myDB; 

DROP TABLE IF EXISTS Ratings; 
DROP TABLE IF EXISTS Reviews; 
DROP TABLE IF EXISTS BOOKLISTS;
DROP TABLE IF EXISTS COMMENTS; 
DROP TABLE IF EXISTS USERTABLE; 
DROP TABLE IF EXISTS LIBRARY;

-- Creates the book library table where all the books are stored 
CREATE TABLE IF NOT EXISTS LIBRARY ( 
LIB_ID INT NOT NULL auto_increment PRIMARY KEY, 
LIB_TITLE varchar(255) NOT NULL, 
LIB_AUTHOR varchar(255) NOT NULL, 
LIB_YEAR integer NOT NULL, 
LIB_ISBN varchar(255) NOT NULL, 
UNIQUE KEY ( LIB_TITLE, LIB_ISBN )
);

-- creates the table for user information 
CREATE TABLE IF NOT EXISTS USERTABLE(
user_id INT auto_increment unique PRIMARY KEY, 
USERNAME varchar(255) NOT NULL,
PASSWORD varchar(255) NOT NULL, 
EMAIL varchar(255) NOT NULL UNIQUE, 
UNIQUE KEY ( USERNAME )
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

CREATE TABLE Ratings(
	id INT NOT NULL auto_increment PRIMARY KEY,
	book VARCHAR(255),
	user VARCHAR(255),
	rating INT NOT NULL,
    FOREIGN KEY(book) REFERENCES LIBRARY(LIB_TITLE), 
    FOREIGN KEY(user) REFERENCES USERTABLE(USERNAME)
); 

CREATE TABLE Reviews(
	id INT NOT NULL,
	book VARCHAR(255),
	user VARCHAR(255),
	message TEXT,
    likes INT NOT NULL, 
    FOREIGN KEY(id) REFERENCES Ratings(id)
); 

-- Populate the tabeles 

INSERT INTO USERTABLE (USERNAME, PASSWORD, EMAIL) VALUES 
('Polywertz' , '123456', 'Polywertz@gmail.com' ),
('JohnDoe', 'him123', 'john_doe@gmail.com') ,
('janewest', 'her5676' , 'jane@gmail.com') ;

INSERT INTO LIBRARY ( LIB_TITLE, LIB_AUTHOR, LIB_ISBN, LIB_YEAR) VALUES 
('To Kill a Mockingbird',  'Harper Lee' , '0446310789', '1960' ), 
('1984LIBRARY',  'George Orwell' , '0446310789', '1949' ),
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
('Polywertz', 1, 6, 'The Great Gatsby',  '9780743273565')

;








