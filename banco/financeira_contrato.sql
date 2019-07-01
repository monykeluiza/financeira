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
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valor_cliente` decimal(13,2) NOT NULL,
  `valor_pago` decimal(13,2) DEFAULT NULL,
  `numero` varchar(200) NOT NULL,
  `valor_contrato` decimal(13,2) NOT NULL,
  `qtd_parcelas` int(11) NOT NULL,
  `tipo_operacao_id` int(11) NOT NULL,
  `funcionario_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `data_pgto` date DEFAULT NULL,
  `banco_id` int(11) NOT NULL,
  `data_criacao` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_contrato_tipo_operacao1_idx` (`tipo_operacao_id`),
  KEY `fk_contrato_funcionario1_idx` (`funcionario_id`),
  KEY `fk_contrato_cliente1_idx` (`cliente_id`),
  KEY `fk_contrato_banco1_idx` (`banco_id`),
  CONSTRAINT `fk_contrato_banco1` FOREIGN KEY (`banco_id`) REFERENCES `banco` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_cliente1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_funcionario1` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contrato_tipo_operacao1` FOREIGN KEY (`tipo_operacao_id`) REFERENCES `tipo_operacao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
INSERT INTO `contrato` VALUES (20,9962.17,NULL,'861547300',9962.17,72,1,1,4,NULL,2,'2019-05-06'),(21,3573.23,3573.23,'861546868',3573.23,72,1,1,4,'2019-06-12',2,'2019-05-06'),(22,19090.00,NULL,'10670801',19090.00,72,1,8,5,NULL,1,'2019-06-01');
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-01 11:25:41
