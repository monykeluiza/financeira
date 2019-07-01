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
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `login` varchar(100) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `id_tabela` int(11) NOT NULL,
  `acao` varchar(45) NOT NULL,
  `nome_tabela` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1,'2019-06-06 11:34:37',2,'moniqueluiza','moniqueluiza',1,'INSERT','Funcionario'),(2,'2019-06-06 13:27:31',2,'moniqueluiza','moniqueluiza',3,'UPDATE','USUARIO'),(3,'2019-06-06 14:28:09',3,'004.654.895-59','004.654.895-59',1,'INSERT','Cliente'),(4,'2019-06-10 10:50:50',2,'moniqueluiza','moniqueluiza',1,'INSERT','Meta'),(5,'2019-06-10 11:20:57',2,'moniqueluiza','moniqueluiza',2,'INSERT','Meta'),(6,'2019-06-11 10:03:30',2,'moniqueluiza','moniqueluiza',1,'INSERT','TipoLembrete'),(7,'2019-06-11 10:03:39',2,'moniqueluiza','moniqueluiza',1,'INSERT','Lembrete'),(8,'2019-06-11 11:03:29',2,'moniqueluiza','moniqueluiza',2,'INSERT','Lembrete'),(9,'2019-06-11 11:09:10',2,'moniqueluiza','moniqueluiza',3,'INSERT','Lembrete'),(10,'2019-06-11 11:18:18',2,'moniqueluiza','moniqueluiza',3,'UPDATE','Lembrete'),(11,'2019-06-11 11:24:36',2,'moniqueluiza','moniqueluiza',1,'UPDATE','Lembrete'),(12,'2019-06-11 14:24:08',2,'moniqueluiza','moniqueluiza',1,'INSERT','Banco'),(13,'2019-06-11 14:26:39',2,'moniqueluiza','moniqueluiza',1,'INSERT','tipo_operacao'),(14,'2019-06-11 14:32:13',2,'moniqueluiza','moniqueluiza',1,'INSERT','Contrato'),(15,'2019-06-11 14:45:22',2,'moniqueluiza','moniqueluiza',1,'UPDATE','Contrato'),(16,'2019-06-11 15:55:47',2,'moniqueluiza','moniqueluiza',2,'INSERT','Contrato'),(17,'2019-06-11 15:56:15',2,'moniqueluiza','moniqueluiza',2,'UPDATE','Contrato'),(18,'2019-06-11 15:58:14',2,'moniqueluiza','moniqueluiza',3,'INSERT','Contrato'),(19,'2019-06-11 15:59:58',2,'moniqueluiza','moniqueluiza',3,'UPDATE','Contrato'),(22,'2019-06-12 10:13:35',2,'moniqueluiza','moniqueluiza',10,'INSERT','Contrato'),(23,'2019-06-12 10:13:35',2,'moniqueluiza','moniqueluiza',10,'UPDATE','Contrato'),(24,'2019-06-12 10:14:14',2,'moniqueluiza','moniqueluiza',10,'UPDATE','Contrato'),(25,'2019-06-12 10:16:25',2,'moniqueluiza','moniqueluiza',10,'UPDATE','Contrato'),(26,'2019-06-12 10:18:25',2,'moniqueluiza','moniqueluiza',10,'UPDATE','Contrato'),(27,'2019-06-12 10:39:29',2,'moniqueluiza','moniqueluiza',11,'INSERT','Contrato'),(28,'2019-06-12 10:39:40',2,'moniqueluiza','moniqueluiza',11,'UPDATE','Contrato'),(29,'2019-06-12 10:41:24',2,'moniqueluiza','moniqueluiza',11,'UPDATE','Contrato'),(30,'2019-06-12 10:50:33',2,'moniqueluiza','moniqueluiza',12,'INSERT','Contrato'),(31,'2019-06-12 10:50:34',2,'moniqueluiza','moniqueluiza',12,'UPDATE','Contrato'),(32,'2019-06-12 10:50:50',2,'moniqueluiza','moniqueluiza',12,'UPDATE','Contrato'),(33,'2019-06-12 12:32:42',3,'004.654.895-59','004.654.895-59',1,'INSERT','Contato'),(34,'2019-06-12 13:08:08',3,'004.654.895-59','004.654.895-59',13,'INSERT','Contrato'),(35,'2019-06-12 13:08:08',3,'004.654.895-59','004.654.895-59',13,'UPDATE','Contrato'),(36,'2019-06-12 13:08:44',3,'004.654.895-59','004.654.895-59',13,'UPDATE','Contrato'),(37,'2019-06-12 13:26:14',3,'004.654.895-59','004.654.895-59',14,'INSERT','Contrato'),(38,'2019-06-12 13:26:14',3,'004.654.895-59','004.654.895-59',14,'UPDATE','Contrato'),(39,'2019-06-12 13:32:58',3,'004.654.895-59','004.654.895-59',2,'INSERT','Contato'),(40,'2019-06-12 13:43:16',3,'004.654.895-59','004.654.895-59',2,'INSERT','Cliente'),(41,'2019-06-12 13:43:28',3,'004.654.895-59','004.654.895-59',2,'UPDATE','Cliente'),(42,'2019-06-12 14:00:24',3,'004.654.895-59','004.654.895-59',3,'INSERT','Contato'),(43,'2019-06-12 14:01:16',3,'004.654.895-59','004.654.895-59',4,'INSERT','Contato'),(44,'2019-06-12 14:11:33',3,'004.654.895-59','004.654.895-59',5,'INSERT','Contato'),(45,'2019-06-13 11:38:14',3,'004.654.895-59','004.654.895-59',2,'UPDATE','Lembrete'),(46,'2019-06-13 11:38:15',3,'004.654.895-59','004.654.895-59',3,'UPDATE','Lembrete'),(47,'2019-06-13 12:59:22',3,'004.654.895-59','004.654.895-59',3,'UPDATE','Lembrete'),(48,'2019-06-13 13:00:00',3,'004.654.895-59','004.654.895-59',2,'UPDATE','Lembrete'),(49,'2019-06-13 13:01:28',3,'004.654.895-59','004.654.895-59',1,'UPDATE','Lembrete'),(50,'2019-06-13 13:03:53',3,'004.654.895-59','004.654.895-59',3,'UPDATE','Lembrete'),(51,'2019-06-13 13:05:34',3,'004.654.895-59','004.654.895-59',2,'UPDATE','Lembrete'),(52,'2019-06-13 13:05:45',3,'004.654.895-59','004.654.895-59',1,'UPDATE','Lembrete'),(53,'2019-06-13 13:06:00',3,'004.654.895-59','004.654.895-59',3,'UPDATE','Lembrete'),(54,'2019-06-13 13:06:01',3,'004.654.895-59','004.654.895-59',2,'UPDATE','Lembrete'),(55,'2019-06-13 13:06:02',3,'004.654.895-59','004.654.895-59',1,'UPDATE','Lembrete'),(56,'2019-06-17 09:04:24',3,'004.654.895-59','004.654.895-59',15,'INSERT','Contrato'),(57,'2019-06-17 09:04:24',3,'004.654.895-59','004.654.895-59',15,'UPDATE','Contrato'),(58,'2019-06-17 09:07:31',3,'004.654.895-59','004.654.895-59',5,'INSERT','Lembrete'),(59,'2019-06-17 09:07:50',3,'004.654.895-59','004.654.895-59',6,'INSERT','Lembrete'),(60,'2019-06-17 09:17:12',3,'004.654.895-59','004.654.895-59',6,'UPDATE','Lembrete'),(61,'2019-06-17 10:04:59',3,'004.654.895-59','004.654.895-59',3,'INSERT','Cliente'),(62,'2019-06-17 13:38:43',3,'004.654.895-59','004.654.895-59',14,'UPDATE','Contrato'),(63,'2019-06-17 14:29:31',2,'moniqueluiza','moniqueluiza',3,'INSERT','Meta'),(64,'2019-06-17 14:46:57',3,'004.654.895-59','004.654.895-59',2,'INSERT','Funcionario'),(65,'2019-06-17 14:47:43',3,'004.654.895-59','004.654.895-59',3,'INSERT','Funcionario'),(66,'2019-06-17 14:48:03',3,'004.654.895-59','004.654.895-59',4,'INSERT','Funcionario'),(67,'2019-06-17 14:48:34',3,'004.654.895-59','004.654.895-59',5,'INSERT','Funcionario'),(68,'2019-06-17 14:48:59',3,'004.654.895-59','004.654.895-59',6,'INSERT','Funcionario'),(69,'2019-06-17 14:49:32',3,'004.654.895-59','004.654.895-59',2,'INSERT','Banco'),(70,'2019-06-17 14:49:47',3,'004.654.895-59','004.654.895-59',3,'INSERT','Banco'),(71,'2019-06-17 14:50:02',3,'004.654.895-59','004.654.895-59',4,'INSERT','Banco'),(72,'2019-06-17 14:50:09',3,'004.654.895-59','004.654.895-59',5,'INSERT','Banco'),(73,'2019-06-17 14:50:20',3,'004.654.895-59','004.654.895-59',6,'INSERT','Banco'),(74,'2019-06-17 14:52:17',3,'004.654.895-59','004.654.895-59',2,'INSERT','tipo_operacao'),(75,'2019-06-17 14:52:26',3,'004.654.895-59','004.654.895-59',3,'INSERT','tipo_operacao'),(76,'2019-06-17 14:52:33',3,'004.654.895-59','004.654.895-59',4,'INSERT','tipo_operacao'),(77,'2019-06-17 14:52:46',3,'004.654.895-59','004.654.895-59',5,'INSERT','tipo_operacao'),(78,'2019-06-17 14:53:55',3,'004.654.895-59','004.654.895-59',4,'INSERT','Cliente'),(79,'2019-06-17 14:58:49',3,'004.654.895-59','004.654.895-59',16,'INSERT','Contrato'),(80,'2019-06-17 14:58:49',3,'004.654.895-59','004.654.895-59',16,'UPDATE','Contrato'),(81,'2019-06-17 15:00:32',3,'004.654.895-59','004.654.895-59',17,'INSERT','Contrato'),(82,'2019-06-17 15:00:33',3,'004.654.895-59','004.654.895-59',17,'UPDATE','Contrato'),(83,'2019-06-17 15:02:56',3,'004.654.895-59','004.654.895-59',6,'INSERT','Contato'),(84,'2019-06-17 15:38:50',3,'004.654.895-59','004.654.895-59',17,'UPDATE','Contrato'),(85,'2019-06-17 15:47:16',3,'004.654.895-59','004.654.895-59',18,'INSERT','Contrato'),(86,'2019-06-17 15:47:16',3,'004.654.895-59','004.654.895-59',18,'UPDATE','Contrato'),(87,'2019-06-17 15:47:52',3,'004.654.895-59','004.654.895-59',19,'INSERT','Contrato'),(88,'2019-06-17 15:47:52',3,'004.654.895-59','004.654.895-59',19,'UPDATE','Contrato'),(89,'2019-06-17 15:48:49',3,'004.654.895-59','004.654.895-59',19,'UPDATE','Contrato'),(90,'2019-06-18 11:23:37',3,'004.654.895-59','004.654.895-59',20,'INSERT','Contrato'),(91,'2019-06-18 11:23:37',3,'004.654.895-59','004.654.895-59',20,'UPDATE','Contrato'),(92,'2019-06-18 11:24:12',3,'004.654.895-59','004.654.895-59',21,'INSERT','Contrato'),(93,'2019-06-18 11:24:12',3,'004.654.895-59','004.654.895-59',21,'UPDATE','Contrato'),(94,'2019-06-18 11:25:17',3,'004.654.895-59','004.654.895-59',21,'UPDATE','Contrato'),(95,'2019-06-18 11:30:14',3,'004.654.895-59','004.654.895-59',3,'UPDATE','Cliente'),(96,'2019-06-18 14:00:06',3,'004.654.895-59','004.654.895-59',3,'UPDATE','USUARIO'),(97,'2019-06-18 14:07:43',3,'004.654.895-59','004.654.895-59',3,'UPDATE','USUARIO'),(98,'2019-06-26 11:11:03',3,'004.654.895-59','004.654.895-59',3,'UPDATE','USUARIO'),(99,'2019-06-26 11:14:29',3,'004.654.895-59','004.654.895-59',7,'INSERT','Funcionario'),(100,'2019-06-26 11:15:41',9,'000.000.000-00','000.000.000-00',9,'UPDATE','USUARIO'),(101,'2019-06-26 13:14:02',3,'004.654.895-59','004.654.895-59',23,'INSERT','USUARIO'),(102,'2019-06-26 13:14:02',3,'004.654.895-59','004.654.895-59',8,'INSERT','Funcionario'),(103,'2019-06-26 13:17:56',3,'004.654.895-59','004.654.895-59',24,'INSERT','USUARIO'),(104,'2019-06-26 13:17:56',3,'004.654.895-59','004.654.895-59',9,'INSERT','Funcionario'),(106,'2019-06-27 11:47:41',3,'004.654.895-59','004.654.895-59',4,'UPDATE','Cliente'),(107,'2019-06-27 11:48:16',3,'004.654.895-59','004.654.895-59',4,'UPDATE','Cliente'),(108,'2019-06-27 13:14:18',23,'111.111.111-11','111.111.111-11',5,'INSERT','Cliente'),(109,'2019-06-27 13:15:07',23,'111.111.111-11','111.111.111-11',22,'INSERT','Contrato'),(110,'2019-06-27 13:15:07',23,'111.111.111-11','111.111.111-11',22,'UPDATE','Contrato'),(111,'2019-06-28 10:04:19',3,'004.654.895-59','004.654.895-59',5,'UPDATE','Cliente');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-01 11:25:44
