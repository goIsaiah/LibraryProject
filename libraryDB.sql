
CREATE DATABASE IF NOT EXISTS myDB; 
use myDB; 
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `BOOKLISTS`
--

DROP TABLE IF EXISTS `BOOKLISTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOOKLISTS` (
  `usrname` varchar(255) DEFAULT NULL,
  `usr_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `book_title` varchar(255) NOT NULL,
  `book_isbn` varchar(255) NOT NULL,
  KEY `usr_id` (`usr_id`),
  KEY `usrname` (`usrname`),
  KEY `book_title` (`book_title`,`book_isbn`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `BOOKLISTS_ibfk_1` FOREIGN KEY (`usr_id`) REFERENCES `USERTABLE` (`user_id`),
  CONSTRAINT `BOOKLISTS_ibfk_2` FOREIGN KEY (`usrname`) REFERENCES `USERTABLE` (`USERNAME`),
  CONSTRAINT `BOOKLISTS_ibfk_3` FOREIGN KEY (`book_title`, `book_isbn`) REFERENCES `LIBRARY` (`LIB_TITLE`, `LIB_ISBN`),
  CONSTRAINT `BOOKLISTS_ibfk_4` FOREIGN KEY (`book_id`) REFERENCES `LIBRARY` (`LIB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOOKLISTS`
--

LOCK TABLES `BOOKLISTS` WRITE;
/*!40000 ALTER TABLE `BOOKLISTS` DISABLE KEYS */;
INSERT INTO `BOOKLISTS` VALUES ('Polywertz',1,2,'1984','0446310789'),('Polywertz',1,3,'Pride and Prejudice','0446310789'),('Polywertz',1,6,'The Great Gatsby','9780743273565');
/*!40000 ALTER TABLE `BOOKLISTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOOKLISTSWGenre`
--

DROP TABLE IF EXISTS `BOOKLISTSWGenre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOOKLISTSWGenre` (
  `usrname` varchar(255) DEFAULT NULL,
  `usr_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `book_title` varchar(255) NOT NULL,
  `book_isbn` varchar(255) NOT NULL,
  `book_genre` varchar(255) DEFAULT NULL,
  KEY `usr_id` (`usr_id`),
  KEY `usrname` (`usrname`),
  KEY `book_id` (`book_id`),
  KEY `book_title` (`book_title`,`book_isbn`),
  KEY `book_genre` (`book_genre`),
  CONSTRAINT `BOOKLISTSWGenre_ibfk_1` FOREIGN KEY (`usr_id`) REFERENCES `USERTABLE` (`user_id`),
  CONSTRAINT `BOOKLISTSWGenre_ibfk_2` FOREIGN KEY (`usrname`) REFERENCES `USERTABLE` (`USERNAME`),
  CONSTRAINT `BOOKLISTSWGenre_ibfk_3` FOREIGN KEY (`book_id`) REFERENCES `LIBRARY` (`LIB_ID`),
  CONSTRAINT `BOOKLISTSWGenre_ibfk_4` FOREIGN KEY (`book_title`, `book_isbn`) REFERENCES `LIBRARY` (`LIB_TITLE`, `LIB_ISBN`),
  CONSTRAINT `BOOKLISTSWGenre_ibfk_5` FOREIGN KEY (`book_genre`) REFERENCES `LIBRARY` (`GENRE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOOKLISTSWGenre`
--

LOCK TABLES `BOOKLISTSWGenre` WRITE;
/*!40000 ALTER TABLE `BOOKLISTSWGenre` DISABLE KEYS */;
/*!40000 ALTER TABLE `BOOKLISTSWGenre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMMENTS`
--

DROP TABLE IF EXISTS `COMMENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `COMMENTS` (
  `usrname` varchar(255) DEFAULT NULL,
  `usr_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `book_title` varchar(255) NOT NULL,
  `comment` text,
  KEY `usr_id` (`usr_id`),
  KEY `usrname` (`usrname`),
  KEY `book_title` (`book_title`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `COMMENTS_ibfk_1` FOREIGN KEY (`usr_id`) REFERENCES `USERTABLE` (`user_id`),
  CONSTRAINT `COMMENTS_ibfk_2` FOREIGN KEY (`usrname`) REFERENCES `USERTABLE` (`USERNAME`),
  CONSTRAINT `COMMENTS_ibfk_3` FOREIGN KEY (`book_title`) REFERENCES `LIBRARY` (`LIB_TITLE`),
  CONSTRAINT `COMMENTS_ibfk_4` FOREIGN KEY (`book_id`) REFERENCES `LIBRARY` (`LIB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMMENTS`
--

LOCK TABLES `COMMENTS` WRITE;
/*!40000 ALTER TABLE `COMMENTS` DISABLE KEYS */;
INSERT INTO `COMMENTS` VALUES ('JohnDoe',NULL,1,'To Kill a Mockingbird','This is a new comment'),('JohnDoe',NULL,5,'Diary of a Wimpy Kid','The kid is wimpy'),('Polywertz',NULL,1,'To Kill a Mockingbird','This is another new comment for this book.'),('Polywertz',NULL,1,'To Kill a Mockingbird','This is a book'),('Polywertz',NULL,1,'To Kill a Mockingbird','Hi this is a commment'),('abbey22',NULL,4,'Harry Potter and the Sorcerer s Stone','this book sucks');
/*!40000 ALTER TABLE `COMMENTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CRITICS`
--

DROP TABLE IF EXISTS `CRITICS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CRITICS` (
  `usrname` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `book_id` int(11) NOT NULL,
  `book_title` varchar(255) NOT NULL,
  `message` longtext,
  `rating` int(11) NOT NULL,
  KEY `usrname` (`usrname`),
  KEY `book_title` (`book_title`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `CRITICS_ibfk_2` FOREIGN KEY (`usrname`) REFERENCES `USERTABLE` (`USERNAME`),
  CONSTRAINT `CRITICS_ibfk_3` FOREIGN KEY (`book_title`) REFERENCES `LIBRARY` (`LIB_TITLE`),
  CONSTRAINT `CRITICS_ibfk_4` FOREIGN KEY (`book_id`) REFERENCES `LIBRARY` (`LIB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CRITICS`
--

LOCK TABLES `CRITICS` WRITE;
/*!40000 ALTER TABLE `CRITICS` DISABLE KEYS */;
INSERT INTO `CRITICS` VALUES ('Polywertz',0,4,'Harry Potter and the Sorcerer s Stone','Bad',1),('abbey22',NULL,5,'Diary of a Wimpy Kid','insane book',5),('Polywertz',NULL,5,'Diary of a Wimpy Kid','horrible',2),('Polywertz',NULL,7,'Fahrenheit 451','Decent book!',3),('Polywertz',NULL,1,'To Kill a Mockingbird','Hi this book is great!',5),('abbey22',NULL,4,'Harry Potter and the Sorcerer s Stone','amazing book wouild read again',5),('Polywertz',NULL,26,'Animal Farm','Amazing book!',5),('JohnDoe',NULL,2,'1984','another critic',4),('abbey22',NULL,3,'Pride and Prejudice','decent',2);
/*!40000 ALTER TABLE `CRITICS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FOLLOWERS`
--

DROP TABLE IF EXISTS `FOLLOWERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FOLLOWERS` (
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  PRIMARY KEY (`friend_id`),
  UNIQUE KEY `uc_user_friend` (`user_id`,`friend_id`),
  UNIQUE KEY `friend_id_UNIQUE` (`friend_id`),
  CONSTRAINT `FOLLOWERS_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `USERTABLE` (`user_id`),
  CONSTRAINT `FOLLOWERS_ibfk_2` FOREIGN KEY (`friend_id`) REFERENCES `USERTABLE` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FOLLOWERS`
--

LOCK TABLES `FOLLOWERS` WRITE;
/*!40000 ALTER TABLE `FOLLOWERS` DISABLE KEYS */;
INSERT INTO `FOLLOWERS` VALUES (1,2),(2,17),(2,19),(2,20);
/*!40000 ALTER TABLE `FOLLOWERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LIBRARY`
--

DROP TABLE IF EXISTS `LIBRARY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LIBRARY` (
  `LIB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LIB_TITLE` varchar(255) NOT NULL,
  `LIB_AUTHOR` varchar(255) NOT NULL,
  `LIB_YEAR` int(11) NOT NULL,
  `LIB_ISBN` varchar(255) NOT NULL,
  `GENRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LIB_ID`),
  UNIQUE KEY `LIB_TITLE` (`LIB_TITLE`,`LIB_ISBN`),
  UNIQUE KEY `GENRE` (`GENRE`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LIBRARY`
--

LOCK TABLES `LIBRARY` WRITE;
/*!40000 ALTER TABLE `LIBRARY` DISABLE KEYS */;
INSERT INTO `LIBRARY` VALUES (1,'To Kill a Mockingbird','Harper Lee',1960,'0446310789',NULL),(2,'1984','George Orwell',1949,'0446310789',NULL),(3,'Pride and Prejudice','Pride and Prejudice',1813,'0446310789',NULL),(4,'Harry Potter and the Sorcerer s Stone','J. K. Rowling',2020,'1338596705',NULL),(5,'Diary of a Wimpy Kid','Jeff Kinney',2007,'0141324902',NULL),(6,'The Great Gatsby','F. Scott Fitzgerald',1925,'9780743273565',NULL),(7,'Fahrenheit 451','Ray Bradbury',2011,'9781451673319',NULL),(8,'Murder on the Orient Express','Agatha Christie',2007,'9780007119318',NULL),(9,'The Jack Ryan Agenda','William Terdoslavich',2006,'9781466806498',NULL),(10,'Because the Sun','Sarah Burgoyne',2021,'9781770566705',NULL),(11,'The Lord of the Rings','John Ronald Reuel Tolkien',1983,'9780048232298',NULL),(12,'Mockingbird','Kathryn Erskine',2018,'9781409541677',NULL),(13,'Hell on Earth','Dafydd ab Hugh',2016,'9781416525127',NULL),(14,'Jackson\'s Magic Wishing Well','Gail Peeler',2008,'9781435729506',NULL),(15,'Steal Like an Artist','Austin Kleon',2012,'9780761171256',NULL),(16,'Hello Hello','Brendan Wenzel',2018,'9781452174181',NULL),(17,'Coding Roblox Games Made Easy','Zander Brumbaugh',2021,'9781800566361',NULL),(18,'The Official DVSA Theory Test for Car Drivers','Driver And Vehicle Standards Agency (Dvsa)',2015,'9780115534195',NULL),(19,'The Joker Vol. 1','James Tynion IV',2021,'9781779516190',NULL),(20,'On Holiday','Orvar Löfgren',2002,'9780520234642',NULL),(21,'JoJo\'s Guide to the Sweet Life','JoJo Siwa',2017,'9781683352020',NULL),(22,'Guide to Minecraft Dungeons','Mojang Ab',2020,'9781984818720',NULL),(23,'Boys','Rachel Giese',2018,'9781580058759',NULL),(24,'Medical Medium Celery Juice','Anthony William',2019,'9781401957667',NULL),(25,'Girl','Edna O\'Brien',2019,'9780374721381',NULL),(26,'Animal Farm','George Orwell',2021,'9780571355914',NULL),(27,'You (Export)','Caroline Kepnes',2020,'9781982151447',NULL);
/*!40000 ALTER TABLE `LIBRARY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STATUSTABLE`
--

DROP TABLE IF EXISTS `STATUSTABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `STATUSTABLE` (
  `title` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `year` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `day` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STATUSTABLE`
--

LOCK TABLES `STATUSTABLE` WRITE;
/*!40000 ALTER TABLE `STATUSTABLE` DISABLE KEYS */;
INSERT INTO `STATUSTABLE` VALUES ('Harry Potter and the Sorcerer s Stone',NULL,0,0,0),('To Kill a Mockingbird','abbey22',2023,3,40),('Diary of a Wimpy Kid',NULL,0,0,0),('The Lord of the Rings','JohnDoe',2023,3,13),('Steal Like an Artist',NULL,0,0,0),('Pride and Prejudice','Polywertz',2023,3,59),('Coding Roblox Games Made Easy',NULL,0,0,0),('The Great Gatsby',NULL,0,0,0),('Fahrenheit 451',NULL,0,0,0),('1984','janewest',2023,3,36),('Guide to Minecraft Dungeons',NULL,0,0,0),('Medical Medium Celery Juice','JohnDoe',2023,3,18),('Animal Farm',NULL,0,0,0),('You (Export)','Polywertz',2023,3,21);
/*!40000 ALTER TABLE `STATUSTABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERINFO`
--

DROP TABLE IF EXISTS `USERINFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USERINFO` (
  `info_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `TEXTFIELD` longtext,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`info_id`),
  UNIQUE KEY `info_id` (`info_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `USERINFO_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `USERTABLE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERINFO`
--

LOCK TABLES `USERINFO` WRITE;
/*!40000 ALTER TABLE `USERINFO` DISABLE KEYS */;
INSERT INTO `USERINFO` VALUES (27,1,'https://static-cdn.jtvnw.net/jtv_user_pictures/1975b18f-fa7d-443f-b191-fba08f92f3a2-profile_image-50x50.jpeg','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eget nulla vel nisl hendrerit aliquet eu blandit nibh. Cras ornare egestas hendrerit. ','Sathira','Williams'),(28,2,'https://static-cdn.jtvnw.net/jtv_user_pictures/a28b0d37-81d0-415a-8ed8-1a1f998ea26d-profile_image-50x50.png','Sed non dictum nunc. Phasellus sed aliquam ex. Pellentesque finibus, nibh ut elementum congue, arcu ex gravida tellus, quis scelerisque sapien sem eu nisl. ','John','Doe'),(29,3,'https://static-cdn.jtvnw.net/jtv_user_pictures/cf25be21-3b39-4997-9122-3b58065bbe50-profile_image-50x50.png','Mauris ut leo libero. Donec tellus velit, dapibus porta venenatis sed, viverra eu ex. Nam vel pellentesque purus. Aliquam at lectus porta, dignissim libero non, auctor tellus. Fusce vitae lacus volutpat, vestibulum arcu et, dignissim turpis. Duis sit amet libero efficitur, hendrerit odio ac, blandit lectus. Aenean luctus facilisis metus, non eleifend est imperdiet sed. Aenean interdum, lectus et maximus tincidunt, ipsum nisi consectetur quam, et blandit lacus neque at nisl. ','Jane','West'),(30,17,'https://static-cdn.jtvnw.net/jtv_user_pictures/bobross-profile_image-0b9dd167a9bb16b5-50x50.jpeg','Curabitur condimentum elementum tempor. Pellentesque venenatis interdum mi eget vulputate. Sed porttitor felis vitae tellus luctus ornare. Fusce nulla justo.','Isaiah','Lastname'),(31,17,'https://i.pinimg.com/originals/6e/cb/78/6ecb786a4a2e1845274ee79adef0c44c.jpg','Curabitur condimentum elementum tempor. Pellentesque venenatis interdum mi eget vulputate. Sed porttitor felis vitae tellus luctus ornare. Fusce nulla justo.','Isaiah','Gocool'),(32,17,'https://i.pinimg.com/originals/6e/cb/78/6ecb786a4a2e1845274ee79adef0c44c.jpg','Curabitur condimentum elementum tempor. Pellentesque venenatis interdum mi eget vulputate. Sed porttitor felis vitae tellus luctus ornare. Fusce nulla justo.','Isaiah','Gocool'),(33,20,'https://i.pinimg.com/originals/ae/24/87/ae24874dd301843548c034a3d2973658.png','','Abbey','McMillan'),(34,19,'https://howtoapps.com/wp-content/uploads/2020/01/a416edf8-funny-profile-pic-14-563x400.jpg','','Sathira','W'),(35,17,'https://i.redd.it/b2akkyfhp8s41.jpg','Curabitur condimentum elementum tempor. Pellentesque venenatis interdum mi eget vulputate. Sed porttitor felis vitae tellus luctus ornare. Fusce nulla justo.','Isaiah','Gocool'),(36,1,'https://i.pinimg.com/736x/54/26/a5/5426a51fe15b4bb1dca378b3f6963d30.jpg','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eget nulla vel nisl hendrerit aliquet eu blandit nibh. Cras ornare egestas hendrerit. ','Sathira','Williams'),(37,20,'https://i.pinimg.com/originals/ae/24/87/ae24874dd301843548c034a3d2973658.png','i love books','Abbey','McMillan'),(38,1,'https://i.pinimg.com/736x/54/26/a5/5426a51fe15b4bb1dca378b3f6963d30.jpg','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eget nulla vel nisl hendrerit aliquet eu blandit nibh. Cras ornare egestas hendrerit. hi i made this changge','Sathiras','Williamss'),(39,20,'https://i.pinimg.com/originals/ae/24/87/ae24874dd301843548c034a3d2973658.png','u whre','Abbey','McMillan'),(40,20,'https://i.pinimg.com/originals/ae/24/87/ae24874dd301843548c034a3d2973658.png','i love cats','Abbey','McMillan'),(41,1,'https://i.pinimg.com/736x/54/26/a5/5426a51fe15b4bb1dca378b3f6963d30.jpg','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eget nulla vel nisl hendrerit aliquet eu blandit nibh. Cras ornare egestas hendrerit.','Sathira','Williams');
/*!40000 ALTER TABLE `USERINFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERTABLE`
--

DROP TABLE IF EXISTS `USERTABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USERTABLE` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERTABLE`
--

LOCK TABLES `USERTABLE` WRITE;
/*!40000 ALTER TABLE `USERTABLE` DISABLE KEYS */;
INSERT INTO `USERTABLE` VALUES (1,'Polywertz','123456','Polywertz@gmail.com'),(2,'JohnDoe','him123','john_doe@gmail.com'),(3,'janewest','her5676','jane@gmail.com'),(17,'GoIsaiah','isaiahpw','goisaiah@yorku.ca'),(19,'SathiraW','123456','sathira@gmail.com'),(20,'abbey22','1224','a.jarmillan@gmail.com');
/*!40000 ALTER TABLE `USERTABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userFavGenre`
--

DROP TABLE IF EXISTS `userFavGenre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userFavGenre` (
  `username` varchar(255) DEFAULT NULL,
  `genre` varchar(500) NOT NULL,
  KEY `username` (`username`),
  CONSTRAINT `userFavGenre_ibfk_1` FOREIGN KEY (`username`) REFERENCES `USERTABLE` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userFavGenre`
--

LOCK TABLES `userFavGenre` WRITE;
/*!40000 ALTER TABLE `userFavGenre` DISABLE KEYS */;
/*!40000 ALTER TABLE `userFavGenre` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


