-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: bevoyager
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `feedbacklocation`
--

DROP TABLE IF EXISTS `feedbacklocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedbacklocation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `senderID` bigint(20) NOT NULL,
  `recipientID` bigint(20) NOT NULL,
  `message` varchar(500) NOT NULL,
  `sendDate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `senderID` (`senderID`),
  KEY `recipientID` (`recipientID`),
  CONSTRAINT `feedbacklocation_ibfk_1` FOREIGN KEY (`senderID`) REFERENCES `registereduser` (`id`) ON DELETE CASCADE,
  CONSTRAINT `feedbacklocation_ibfk_2` FOREIGN KEY (`recipientID`) REFERENCES `location` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedbacklocation`
--

LOCK TABLES `feedbacklocation` WRITE;
/*!40000 ALTER TABLE `feedbacklocation` DISABLE KEYS */;
INSERT INTO `feedbacklocation` VALUES (1,1,2,'Milano � bellissima','2017-01-08'),(2,1,2,'Oh mia bella madunina','2017-01-08'),(3,4,10,'Londraaaa','2017-01-09'),(4,1,2,'','2017-01-11'),(5,1,2,'','2017-01-11'),(6,1,2,'Ciao ciao','2017-01-11');
/*!40000 ALTER TABLE `feedbacklocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedbackroute`
--

DROP TABLE IF EXISTS `feedbackroute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedbackroute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `senderID` bigint(20) NOT NULL,
  `recipientID` bigint(20) NOT NULL,
  `message` varchar(500) NOT NULL,
  `sendDate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `senderID` (`senderID`),
  KEY `recipientID` (`recipientID`),
  CONSTRAINT `feedbackroute_ibfk_1` FOREIGN KEY (`senderID`) REFERENCES `registereduser` (`id`) ON DELETE CASCADE,
  CONSTRAINT `feedbackroute_ibfk_2` FOREIGN KEY (`recipientID`) REFERENCES `route` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedbackroute`
--

LOCK TABLES `feedbackroute` WRITE;
/*!40000 ALTER TABLE `feedbackroute` DISABLE KEYS */;
INSERT INTO `feedbackroute` VALUES (1,1,2,'Milano � bellissima','2017-01-08'),(2,4,9,'Ciao da Londra','2017-01-09'),(3,1,2,'LLLLLL','2017-01-11');
/*!40000 ALTER TABLE `feedbackroute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedbackuser`
--

DROP TABLE IF EXISTS `feedbackuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedbackuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `senderID` bigint(20) NOT NULL,
  `recipientID` bigint(20) NOT NULL,
  `message` varchar(500) NOT NULL,
  `sendDate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `senderID` (`senderID`),
  KEY `recipientID` (`recipientID`),
  CONSTRAINT `feedbackuser_ibfk_1` FOREIGN KEY (`senderID`) REFERENCES `registereduser` (`id`) ON DELETE CASCADE,
  CONSTRAINT `feedbackuser_ibfk_2` FOREIGN KEY (`recipientID`) REFERENCES `registereduser` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedbackuser`
--

LOCK TABLES `feedbackuser` WRITE;
/*!40000 ALTER TABLE `feedbackuser` DISABLE KEYS */;
INSERT INTO `feedbackuser` VALUES (1,1,6,'Ciao Francesco','2017-01-08'),(3,4,1,'Ciao Alessandro','2017-01-10'),(4,1,4,'Ciao Donato','2017-01-10'),(5,1,4,'Ciao','2017-01-11'),(6,1,4,'sdcsd','2017-01-11'),(7,1,4,'SI nu strunz e merd','2017-01-12');
/*!40000 ALTER TABLE `feedbackuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `name` char(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (2,'Bellissimo','Milano'),(10,'Welcome to London','Londra'),(12,'Città bellissima','Barcellona'),(13,'Descrizione luogo 1','Luogo 1'),(14,'Descrizione luogo 2','Luogo 2'),(15,'Descrizione luogo 3','Luogo 3'),(16,'Descrizione luogo 4','Luogo 4');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `senderID` bigint(20) NOT NULL,
  `recipientID` bigint(20) NOT NULL,
  `body` varchar(255) NOT NULL,
  `readThis` tinyint(1) NOT NULL DEFAULT '0',
  `sendDate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `senderID` (`senderID`),
  KEY `recipientID` (`recipientID`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`senderID`) REFERENCES `registereduser` (`id`) ON DELETE CASCADE,
  CONSTRAINT `notification_ibfk_2` FOREIGN KEY (`recipientID`) REFERENCES `registereduser` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poll`
--

DROP TABLE IF EXISTS `poll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poll` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `positive` int(11) NOT NULL DEFAULT '0',
  `negative` int(11) NOT NULL DEFAULT '0',
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `idTravel` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idTravel` (`idTravel`),
  CONSTRAINT `poll_ibfk_1` FOREIGN KEY (`idTravel`) REFERENCES `travel` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poll`
--

LOCK TABLES `poll` WRITE;
/*!40000 ALTER TABLE `poll` DISABLE KEYS */;
INSERT INTO `poll` VALUES (1,'Brighton',0,1,'2017-02-04','2017-02-07',10),(2,'Vediamo il Duomo',1,0,'2017-02-01','2017-02-03',9);
/*!40000 ALTER TABLE `poll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pollusermatch`
--

DROP TABLE IF EXISTS `pollusermatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pollusermatch` (
  `userID` bigint(20) NOT NULL,
  `pollID` bigint(20) NOT NULL,
  `confirmDate` date NOT NULL,
  PRIMARY KEY (`userID`,`pollID`),
  KEY `pollID` (`pollID`),
  CONSTRAINT `pollusermatch_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `registereduser` (`id`) ON DELETE CASCADE,
  CONSTRAINT `pollusermatch_ibfk_2` FOREIGN KEY (`pollID`) REFERENCES `poll` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pollusermatch`
--

LOCK TABLES `pollusermatch` WRITE;
/*!40000 ALTER TABLE `pollusermatch` DISABLE KEYS */;
INSERT INTO `pollusermatch` VALUES (1,1,'2017-01-07'),(1,2,'2017-01-07');
/*!40000 ALTER TABLE `pollusermatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registereduser`
--

DROP TABLE IF EXISTS `registereduser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registereduser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `name` char(30) NOT NULL,
  `lastName` char(30) NOT NULL,
  `email` char(100) NOT NULL,
  `password` varchar(30) NOT NULL,
  `birthDate` date NOT NULL,
  `age` smallint(6) NOT NULL,
  `authorization` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registereduser`
--

LOCK TABLES `registereduser` WRITE;
/*!40000 ALTER TABLE `registereduser` DISABLE KEYS */;
INSERT INTO `registereduser` VALUES (1,'alexlon','Alessandro','Longobardi','alexlon994@gmail.com','ciao','1994-08-29',22,1),(4,'protti','Donato','Tiano','protti@gmail.com','camicia','1995-04-17',21,0),(5,'bestZirpolone','Paolo','Zirpoli','zirpoli.paolo@gmail.com','amoreamaro','1995-05-12',21,0),(6,'theZirpolone','Francesco','Zirpoli','zirpoli.francesco@gmail.com','amaro','1995-11-10',21,0),(7,'mario','mario','zirpoli','prova@libero.it','ciao','2012-02-02',5,0),(9,'mattia95','mattia','qualcosa','mattia@libero.it','ciao','2012-02-02',5,0),(10,'ddd','aaa','bbb','a@gmail.com','ccc','2012-02-02',5,0),(11,'user','utente','utente','utente@prove.com','userpass','1990-01-01',27,0),(12,'jncjdnvj','ajncfakjvcn','','m@m.com','ciao','2012-02-02',5,0),(13,'xvzdfvfdzv','alksjddjj','ads<gafgs','lkmlm@m.com','','2012-02-02',5,0),(14,'antonio','Antonio','Ionio','antoionio@libero.it','ionioionio','1994-08-29',23,0);
/*!40000 ALTER TABLE `registereduser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `name` char(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'Paesi di buona cultura','Gironzolando in Olanda'),(2,'Terun','Giro a milano'),(9,'Go to London','Everybody to Manchester'),(10,'Terun','Giro a milano'),(11,'Meta Catalogna','Todos a Barcellona'),(12,'Mitica paella','Barcellona stiamo arrivando'),(13,'Meta Catalogna','Todos a Barcellona'),(14,'Mitica paella','Barcellona stiamo arrivando'),(19,'Go to Milano','Milano milano'),(20,'Terun','Giro a milano'),(21,'Descrizione route 1','Route 1');
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routelocationmatch`
--

DROP TABLE IF EXISTS `routelocationmatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `routelocationmatch` (
  `locationID` bigint(20) NOT NULL,
  `routeID` bigint(20) NOT NULL,
  `entryDate` date NOT NULL,
  PRIMARY KEY (`locationID`,`routeID`),
  KEY `routeID` (`routeID`),
  CONSTRAINT `routelocationmatch_ibfk_1` FOREIGN KEY (`locationID`) REFERENCES `location` (`id`),
  CONSTRAINT `routelocationmatch_ibfk_2` FOREIGN KEY (`routeID`) REFERENCES `route` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routelocationmatch`
--

LOCK TABLES `routelocationmatch` WRITE;
/*!40000 ALTER TABLE `routelocationmatch` DISABLE KEYS */;
INSERT INTO `routelocationmatch` VALUES (2,2,'2017-01-06'),(2,10,'0000-00-00'),(2,19,'0000-00-00'),(2,20,'0000-00-00'),(10,9,'0000-00-00'),(12,12,'0000-00-00'),(12,14,'0000-00-00'),(16,21,'2017-01-17');
/*!40000 ALTER TABLE `routelocationmatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemadmin`
--

DROP TABLE IF EXISTS `systemadmin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemadmin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` char(100) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` char(30) NOT NULL,
  `lastName` char(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemadmin`
--

LOCK TABLES `systemadmin` WRITE;
/*!40000 ALTER TABLE `systemadmin` DISABLE KEYS */;
/*!40000 ALTER TABLE `systemadmin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travel`
--

DROP TABLE IF EXISTS `travel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `travel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `routeID` bigint(20) DEFAULT NULL,
  `creatorID` bigint(20) NOT NULL,
  `type` tinyint(1) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `routeID` (`routeID`),
  KEY `creatorID` (`creatorID`),
  CONSTRAINT `travel_ibfk_1` FOREIGN KEY (`routeID`) REFERENCES `route` (`id`),
  CONSTRAINT `travel_ibfk_2` FOREIGN KEY (`creatorID`) REFERENCES `registereduser` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel`
--

LOCK TABLES `travel` WRITE;
/*!40000 ALTER TABLE `travel` DISABLE KEYS */;
INSERT INTO `travel` VALUES (9,'2017-02-02','2017-02-08',2,4,0,'Andiamo a Milano'),(10,'2017-02-01','2017-02-13',9,1,0,'Viaggiamo a Londra'),(12,'2017-02-04','2017-02-13',13,1,0,'Destinazione Spagna'),(16,'2017-02-01','2017-02-04',20,1,0,'Milano3');
/*!40000 ALTER TABLE `travel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertravelmatch`
--

DROP TABLE IF EXISTS `usertravelmatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usertravelmatch` (
  `partecipantID` bigint(20) NOT NULL,
  `travelID` bigint(20) NOT NULL,
  `entryDate` date NOT NULL,
  PRIMARY KEY (`partecipantID`,`travelID`),
  KEY `travelID` (`travelID`),
  CONSTRAINT `usertravelmatch_ibfk_1` FOREIGN KEY (`partecipantID`) REFERENCES `registereduser` (`id`),
  CONSTRAINT `usertravelmatch_ibfk_2` FOREIGN KEY (`travelID`) REFERENCES `travel` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertravelmatch`
--

LOCK TABLES `usertravelmatch` WRITE;
/*!40000 ALTER TABLE `usertravelmatch` DISABLE KEYS */;
INSERT INTO `usertravelmatch` VALUES (1,9,'2017-01-06'),(1,16,'2017-01-11'),(4,10,'2017-01-09'),(4,16,'2017-01-13'),(5,1,'2016-12-29'),(6,9,'2017-01-06'),(14,10,'2017-01-13');
/*!40000 ALTER TABLE `usertravelmatch` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-17 18:32:59
