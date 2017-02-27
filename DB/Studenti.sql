-- MySQL dump 10.16  Distrib 10.1.21-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: localhost
-- ------------------------------------------------------
-- Server version	10.1.21-MariaDB

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
-- Table structure for table `classe`
--

DROP TABLE IF EXISTS `classe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classe` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `scuola` int(11) NOT NULL,
  `nome` varchar(4) NOT NULL,
  `indirizzo` varchar(20) NOT NULL,
  `opzione` varchar(20) NOT NULL,
  PRIMARY KEY (`cod`,`scuola`),
  KEY `scuola` (`scuola`),
  CONSTRAINT `classe_ibfk_1` FOREIGN KEY (`scuola`) REFERENCES `scuola` (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classe`
--

LOCK TABLES `classe` WRITE;
/*!40000 ALTER TABLE `classe` DISABLE KEYS */;
/*!40000 ALTER TABLE `classe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scuola`
--

DROP TABLE IF EXISTS `scuola`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scuola` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  `provincia` varchar(20) NOT NULL,
  `regione` varchar(20) NOT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scuola`
--

LOCK TABLES `scuola` WRITE;
/*!40000 ALTER TABLE `scuola` DISABLE KEYS */;
/*!40000 ALTER TABLE `scuola` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storico`
--

DROP TABLE IF EXISTS `storico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storico` (
  `studente` int(11) NOT NULL,
  `scuola` int(11) NOT NULL,
  `classe` int(11) NOT NULL,
  `anno` int(11) NOT NULL,
  PRIMARY KEY (`studente`,`scuola`,`classe`,`anno`),
  KEY `classe` (`classe`,`scuola`),
  CONSTRAINT `storico_ibfk_1` FOREIGN KEY (`studente`) REFERENCES `studenti` (`cod`),
  CONSTRAINT `storico_ibfk_2` FOREIGN KEY (`classe`, `scuola`) REFERENCES `classe` (`cod`, `scuola`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storico`
--

LOCK TABLES `storico` WRITE;
/*!40000 ALTER TABLE `storico` DISABLE KEYS */;
/*!40000 ALTER TABLE `storico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studenti`
--

DROP TABLE IF EXISTS `studenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studenti` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `cognome` varchar(20) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `datanascita` date DEFAULT NULL,
  `luogonascita` varchar(20) NOT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studenti`
--

LOCK TABLES `studenti` WRITE;
/*!40000 ALTER TABLE `studenti` DISABLE KEYS */;
/*!40000 ALTER TABLE `studenti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-27 10:23:40
