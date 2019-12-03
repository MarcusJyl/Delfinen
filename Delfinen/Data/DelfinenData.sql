-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: delfinen
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `hold`
--

DROP TABLE IF EXISTS `hold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hold` (
  `hold_id` int(11) NOT NULL,
  `medlems_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hold`
--

LOCK TABLES `hold` WRITE;
/*!40000 ALTER TABLE `hold` DISABLE KEYS */;
/*!40000 ALTER TABLE `hold` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medlemmer`
--

DROP TABLE IF EXISTS `medlemmer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medlemmer` (
  `medlems_id` int(11) NOT NULL AUTO_INCREMENT,
  `medlems_navn` varchar(255) NOT NULL,
  `medlems_fødselsdato` date NOT NULL,
  `medlems_holdtype` varchar(255) NOT NULL,
  `medlems_status` varchar(255) NOT NULL,
  `medlems_kontingent` double NOT NULL,
  `medlems_kontingent_status` tinyint(1) NOT NULL,
  PRIMARY KEY (`medlems_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medlemmer`
--

LOCK TABLES `medlemmer` WRITE;
/*!40000 ALTER TABLE `medlemmer` DISABLE KEYS */;
INSERT INTO `medlemmer` VALUES (5,'Morten Andersen','1997-02-10','Konkurrence','Aktiv',1600,1),(6,'Søren Petersen','1995-09-28','Konkurrence','Aktiv',1600,1),(7,'Kristian Jensen','1997-02-28','Konkurrence','Aktiv',1600,1),(8,'Victor Bjerre','1999-07-07','Konkurrence','Aktiv',1600,1),(9,'Marc Ekström','1997-02-10','Konkurrence','Aktiv',1600,1),(10,'Marius Mortensen','1998-07-03','Konkurrence','Aktiv',1600,1),(11,'Jens Jørgensen','1997-06-12','Konkurrence','Aktiv',1600,1),(12,'Lars Løkke','1996-04-12','Konkurrence','Aktiv',1600,1),(13,'Søren Jespersen','1980-02-10','Motion','Aktiv',1600,1),(14,'Preben Andersen','1979-09-12','Motion','Aktiv',1600,1),(15,'Victor Akselsen','1960-12-09','Motion','Aktiv',1600,1),(16,'Jørgen Andersen','1967-12-12','Motion','Aktiv',1600,1),(17,'Daniel Poulsen','1969-12-12','Motion','Aktiv',1600,1),(18,'Jannick Prebensen','1960-02-09','Motion','Aktiv',1600,1),(19,'Victor Jespersen','2004-02-10','Motion','Aktiv',1000,1),(20,'Christoffer Jensen','2003-02-10','Motion','Aktiv',1000,1),(21,'William Petersen','2004-02-10','Motion','Aktiv',1000,1),(22,'Phillip Christiansen','2003-02-10','Motion','Aktiv',1000,1),(23,'Jonas Jensen','2004-02-10','Motion','Aktiv',1000,1),(24,'Hans Didriksen','2003-02-10','Motion','Aktiv',1000,1),(25,'Jonas Larsen','2003-02-10','Konkurrence','Aktiv',1000,1),(26,'Lars Larsen','2003-12-15','Konkurrence','Aktiv',1000,1),(27,'Søren Sørensen','2003-12-16','Konkurrence','Aktiv',1000,1),(28,'William Mikkelsen','2003-02-17','Konkurrence','Aktiv',1000,1),(29,'Frederik Jørgsen','2003-02-19','Konkurrence','Aktiv',1000,1),(30,'Malene Degn','2003-02-20','Konkurrence','Aktiv',1000,1);
/*!40000 ALTER TABLE `medlemmer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medlemmers_dicipliner`
--

DROP TABLE IF EXISTS `medlemmers_dicipliner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medlemmers_dicipliner` (
  `medlems_id` int(11) NOT NULL,
  `diciplin` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medlemmers_dicipliner`
--

LOCK TABLES `medlemmers_dicipliner` WRITE;
/*!40000 ALTER TABLE `medlemmers_dicipliner` DISABLE KEYS */;
INSERT INTO `medlemmers_dicipliner` VALUES (6,'crawl'),(7,'crawl'),(8,'brystsvømning'),(9,'brystsvømning'),(10,'brystsvømning'),(11,'brystsvømning'),(12,'brystsvømning'),(25,'butterfly'),(26,'butterfly'),(27,'crawl'),(28,'crawl'),(29,'crawl'),(30,'crawl'),(5,'crawl'),(5,'butterfly'),(6,'brystsvømning'),(6,'brystsvømning'),(28,'brystsvømning'),(28,'brystsvømning'),(29,'brystsvømning'),(10,'butterfly');
/*!40000 ALTER TABLE `medlemmers_dicipliner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resultater`
--

DROP TABLE IF EXISTS `resultater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resultater` (
  `trænings_id` int(11) NOT NULL,
  `medlems_id` int(11) NOT NULL,
  `tid` varchar(255) NOT NULL,
  `diciplin` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resultater`
--

LOCK TABLES `resultater` WRITE;
/*!40000 ALTER TABLE `resultater` DISABLE KEYS */;
/*!40000 ALTER TABLE `resultater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stævne`
--

DROP TABLE IF EXISTS `stævne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stævne` (
  `stævne_id` int(11) NOT NULL AUTO_INCREMENT,
  `stævne_navn` varchar(255) NOT NULL,
  `dato` varchar(255) NOT NULL,
  `placering` int(11) NOT NULL,
  PRIMARY KEY (`stævne_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stævne`
--

LOCK TABLES `stævne` WRITE;
/*!40000 ALTER TABLE `stævne` DISABLE KEYS */;
/*!40000 ALTER TABLE `stævne` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `træning`
--

DROP TABLE IF EXISTS `træning`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `træning` (
  `trænings_id` int(11) NOT NULL AUTO_INCREMENT,
  `træning_dato` varchar(255) NOT NULL,
  `senior_træning` tinyint(1) NOT NULL,
  `konkurrence_svømmer` tinyint(1) NOT NULL,
  PRIMARY KEY (`trænings_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `træning`
--

LOCK TABLES `træning` WRITE;
/*!40000 ALTER TABLE `træning` DISABLE KEYS */;
/*!40000 ALTER TABLE `træning` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-03 14:56:31
