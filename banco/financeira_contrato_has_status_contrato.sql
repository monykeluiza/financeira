CREATE DATABASE  IF NOT EXISTS `financeira` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `financeira`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: financeira
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `contrato_has_status_contrato`
--

DROP TABLE IF EXISTS `contrato_has_status_contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato_has_status_contrato` (
  `contrato_id` int(11) NOT NULL,
  `status_contrato_id` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`contrato_id`,`status_contrato_id`),
  KEY `fk_contrato_has_status_contrato_status_contrato1_idx` (`status_contrato_id`),
  KEY `fk_contrato_has_status_contrato_contrato1_idx` (`contrato_id`),
  KEY `fk_contrato_has_status_contrato_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_contrato_has_status_contrato_contrato1` FOREIGN KEY (`contrato_id`) REFERENCES `contrato` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_has_status_contrato_status_contrato1` FOREIGN KEY (`status_contrato_id`) REFERENCES `status_contrato` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_has_status_contrato_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato_has_status_contrato`
--

LOCK TABLES `contrato_has_status_contrato` WRITE;
/*!40000 ALTER TABLE `contrato_has_status_contrato` DISABLE KEYS */;
INSERT INTO `contrato_has_status_contrato` VALUES (20,1,'2019-06-18 11:23:37',3),(21,1,'2019-06-18 11:24:12',3),(21,2,'2019-06-18 11:25:17',3),(22,1,'2019-06-27 13:15:07',23);
/*!40000 ALTER TABLE `contrato_has_status_contrato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-01 11:25:49
