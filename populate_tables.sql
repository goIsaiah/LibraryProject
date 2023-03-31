
USE sql9609229; 

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
