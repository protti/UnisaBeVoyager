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
  `locationID` bigint(20) NOT NULL,
  `message` varchar(500) NOT NULL,
  `sendDate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `senderID` (`senderID`),
  KEY `locationID` (`locationID`),
  CONSTRAINT `feedbacklocation_ibfk_1` FOREIGN KEY (`senderID`) REFERENCES `registereduser` (`id`) ON DELETE CASCADE,
  CONSTRAINT `feedbacklocation_ibfk_2` FOREIGN KEY (`locationID`) REFERENCES `registereduser` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedbacklocation`
--

LOCK TABLES `feedbacklocation` WRITE;
/*!40000 ALTER TABLE `feedbacklocation` DISABLE KEYS */;
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
  `routeID` bigint(20) NOT NULL,
  `message` varchar(500) NOT NULL,
  `sendDate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `senderID` (`senderID`),
  KEY `routeID` (`routeID`),
  CONSTRAINT `feedbackroute_ibfk_1` FOREIGN KEY (`senderID`) REFERENCES `registereduser` (`id`) ON DELETE CASCADE,
  CONSTRAINT `feedbackroute_ibfk_2` FOREIGN KEY (`routeID`) REFERENCES `registereduser` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedbackroute`
--

LOCK TABLES `feedbackroute` WRITE;
/*!40000 ALTER TABLE `feedbackroute` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedbackuser`
--

LOCK TABLES `feedbackuser` WRITE;
/*!40000 ALTER TABLE `feedbackuser` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
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
  `locationName` char(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `positive` int(11) NOT NULL DEFAULT '0',
  `negative` int(11) NOT NULL DEFAULT '0',
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `idTravel` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idTravel` (`idTravel`),
  CONSTRAINT `poll_ibfk_1` FOREIGN KEY (`idTravel`) REFERENCES `travel` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poll`
--

LOCK TABLES `poll` WRITE;
/*!40000 ALTER TABLE `poll` DISABLE KEYS */;
/*!40000 ALTER TABLE `poll` ENABLE KEYS */;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registereduser`
--

LOCK TABLES `registereduser` WRITE;
/*!40000 ALTER TABLE `registereduser` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
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
  `destination` varchar(100) DEFAULT NULL,
  `creatorID` bigint(20) NOT NULL,
  `type` tinyint(1) NOT NULL,
  `name` char(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `routeID` (`routeID`),
  KEY `creatorID` (`creatorID`),
  CONSTRAINT `travel_ibfk_1` FOREIGN KEY (`routeID`) REFERENCES `route` (`id`),
  CONSTRAINT `travel_ibfk_2` FOREIGN KEY (`creatorID`) REFERENCES `registereduser` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel`
--

LOCK TABLES `travel` WRITE;
/*!40000 ALTER TABLE `travel` DISABLE KEYS */;
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

-- Dump completed on 2016-12-28 17:11:50
