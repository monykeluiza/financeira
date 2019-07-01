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
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `rg` varchar(45) DEFAULT NULL,
  `ctps` varchar(100) DEFAULT NULL,
  `pispasep` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(45) NOT NULL,
  `whatsapp` varchar(45) DEFAULT NULL,
  `endereco` varchar(500) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `data_inicio` date NOT NULL,
  `data_saida` date DEFAULT NULL,
  `usuario_id` int(11) NOT NULL,
  `chefia_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_funcionario_usuario1_idx` (`usuario_id`),
  KEY `fk_funcionario_funcionario1_idx` (`chefia_id`),
  CONSTRAINT `fk_funcionario_funcionario1` FOREIGN KEY (`chefia_id`) REFERENCES `funcionario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcionario_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,'Cleyton Sodré dos Santos','004.654.895-59','0967427630','','','monykeluiza@gmail.com','(71) 9 9992-4531','(71) 9 9992-4531','','1982-05-27','2019-06-01',NULL,3,NULL),(2,'Milena','025.995.015-71','','','','teste@teste.com.br','(54) 5 4564-5645','','','2019-06-12','2019-06-01',NULL,4,NULL),(3,'Silmar','012.345.678-98','','','','teste@teste.com.br','(56) 4 5656-4564','','','2019-06-24','2019-06-01',NULL,5,NULL),(4,'Cleison','546.545.645-64','','','','4564','(56) 4 6456-4564','','','1986-12-12','2019-06-01',NULL,6,NULL),(5,'Rodrigo','544.897.874-89','','','','teste@teste,cin,br','(45) 6 4321-3213','','','1986-06-01','2019-06-01',NULL,7,NULL),(6,'Raiana','054.898.451-56','','','','teste@teste.com.br','(46) 5 4454-5645','','','2018-06-01','2019-06-01',NULL,8,NULL),(7,'Teste','000.000.000-00','','','','monykeluiza@gmail.com','(71) 9 9999-9999','','','1986-12-28','2019-06-01',NULL,9,NULL),(8,'Teste 2','111.111.111-11','','','','monykeluiza@gmail.com','(99) 9 9999-9999','','','1986-12-28','2018-09-01',NULL,23,NULL),(9,'Teste 3','222.222.222-22','','','','monykeluiza@gmail.com','(99) 9 9999-9999','(99) 9 9999-9999','','1986-12-28','2019-06-01',NULL,24,NULL);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-01 11:25:40
