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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(250) NOT NULL,
  `cpf` varchar(15) DEFAULT NULL,
  `data_nasc` date DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `telefones` varchar(200) DEFAULT NULL,
  `whatsapp` varchar(100) DEFAULT NULL,
  `endereco` varchar(500) DEFAULT NULL,
  `siape` varchar(10) DEFAULT NULL,
  `rg` varchar(45) DEFAULT NULL,
  `orgao` varchar(400) DEFAULT NULL,
  `funcionario_id` int(11) NOT NULL,
  `beneficio` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cliente_funcionario1_idx` (`funcionario_id`),
  CONSTRAINT `fk_cliente_funcionario1` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Monique Luiza Sant Ana Rêgo Dantas','025.995.015-71','1986-12-28','monykeluiza@gmail.com','71999607377','(71) 9 9960-7377','Rua Péricles Cardoso','','151651','',1,NULL),(2,'JOAO DAMASCENDO BARBOSA','137.805.735-04','1954-05-12','','983392077','','','','','',1,1837231300),(3,'Mateus Dantas dos Santos','054.156.045-64','2019-06-18','','56405604560564','','','','','',1,NULL),(4,'ANTONIO RUY BARRETO','169.551.535-87','2019-06-27','','00000000000000000','','','','','',1,92),(5,'JOAO DAMASCENDO BARBOSA','137.805.735-04','1954-06-28','','983392077','','','','','',8,1837231300);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-01 11:25:47
