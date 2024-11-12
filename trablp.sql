-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: trablp
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `estatisticas`
--

DROP TABLE IF EXISTS `estatisticas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estatisticas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome_abertura` varchar(50) NOT NULL,
  `total_games` varchar(50) NOT NULL DEFAULT '0',
  `vitorias_percentual` varchar(50) NOT NULL,
  `derrotas_percentual` varchar(50) NOT NULL,
  `empates_percentual` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estatisticas`
--

LOCK TABLES `estatisticas` WRITE;
/*!40000 ALTER TABLE `estatisticas` DISABLE KEYS */;
INSERT INTO `estatisticas` VALUES (1,'defesa_siciliana','795.089','38%','29%','33%'),(2,'ruy_lopez','181.130','37%','37%','26%'),(3,'gambito_da_rainha','247.589','39%','39%','22%'),(4,'sistema_london','13.555','33%','27%','38%'),(5,'reti','391.110','39%','35%','25%');
/*!40000 ALTER TABLE `estatisticas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jogada`
--

DROP TABLE IF EXISTS `jogada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jogada` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_jogo` int NOT NULL,
  `peca` varchar(25) DEFAULT NULL,
  `pos_inicial` varchar(10) DEFAULT NULL,
  `pos_final` varchar(10) DEFAULT NULL,
  `numero_jogada` int NOT NULL,
  `jogador` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1_idx` (`id_jogo`),
  CONSTRAINT `FK1` FOREIGN KEY (`id_jogo`) REFERENCES `jogo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jogada`
--

LOCK TABLES `jogada` WRITE;
/*!40000 ALTER TABLE `jogada` DISABLE KEYS */;
INSERT INTO `jogada` VALUES (4,1,'peao','41','43',1,'brancas'),(5,1,'peao','26','24',1,'pretas'),(6,1,'cavalo','60','52',2,'brancas'),(7,1,'peao','36','35',2,'pretas'),(8,1,'peao','31','33',3,'brancas'),(9,1,'peao','24','33',3,'pretas'),(10,1,'cavalo','52','33',4,'brancas'),(11,2,'peao','41','43',1,'brancas'),(12,2,'peao','26','24',1,'pretas'),(13,2,'cavalo','60','52',2,'brancas'),(14,2,'peao','36','35',2,'pretas'),(15,2,'peao','31','33',3,'brancas'),(16,2,'peao','24','33',3,'pretas'),(17,2,'cavalo','67','55',4,'brancas'),(18,2,'cavalo','10','22',4,'pretas'),(19,2,'peao','66','65',5,'brancas'),(20,3,'peao','41','43',1,'brancas'),(21,3,'peao','26','24',1,'pretas'),(22,3,'cavalo','60','52',2,'brancas'),(23,3,'peao','36','35',2,'pretas'),(24,3,'peao','31','33',3,'brancas'),(25,3,'peao','24','33',3,'pretas'),(26,3,'cavalo','52','33',4,'brancas'),(27,3,'cavalo','67','55',4,'pretas'),(28,3,'cavalo','10','22',5,'brancas'),(29,3,'peao','06','05',5,'pretas'),(30,5,'peao','41','43',1,'brancas'),(31,5,'peao','46','44',1,'pretas'),(32,5,'cavalo','60','52',2,'brancas'),(33,5,'cavalo','17','25',2,'pretas'),(34,5,'bispo','50','14',3,'brancas'),(35,5,'peao','06','05',3,'pretas'),(36,5,'bispo','14','03',4,'brancas'),(37,5,'cavalo','67','55',4,'pretas'),(38,5,'roque','14','17',5,'brancas'),(39,6,'peao','41','43',1,'brancas'),(40,6,'peao','46','44',1,'pretas'),(41,6,'cavalo','60','52',2,'brancas'),(42,6,'cavalo','17','25',2,'pretas'),(43,6,'bispo','50','14',3,'brancas'),(44,6,'peao','06','05',3,'pretas'),(45,6,'bispo','14','03',4,'brancas'),(46,6,'cavalo','67','55',4,'pretas'),(47,6,'roque','47','66',5,'brancas'),(48,6,'bispo','57','46',5,'pretas'),(49,7,'peao','41','43',1,'brancas'),(50,7,'peao','46','44',1,'pretas'),(51,7,'cavalo','60','52',2,'brancas'),(52,7,'cavalo','17','25',2,'pretas'),(53,7,'bispo','50','14',3,'brancas'),(54,7,'peao','06','05',3,'pretas'),(55,7,'bispo','14','03',4,'brancas'),(56,7,'cavalo','67','55',4,'pretas'),(57,7,'roque','47','66',5,'brancas'),(58,7,'cavalo','55','43',5,'pretas'),(59,8,'peao','41','43',1,'brancas'),(60,8,'peao','46','44',1,'pretas'),(61,8,'cavalo','60','52',2,'brancas'),(62,8,'cavalo','17','25',2,'pretas'),(63,8,'bispo','50','14',3,'brancas'),(64,8,'peao','06','05',3,'pretas'),(65,8,'bispo','14','25',4,'brancas'),(66,8,'peao','36','25',4,'pretas'),(67,9,'peao','31','33',1,'brancas'),(68,9,'peao','36','34',1,'pretas'),(69,9,'peao','21','23',2,'brancas'),(70,9,'peao','46','45',2,'pretas'),(71,10,'peao','31','33',1,'brancas'),(72,10,'peao','36','34',1,'pretas'),(73,10,'peao','21','23',2,'brancas'),(74,10,'peao','34','23',2,'pretas'),(75,11,'peao','31','33',1,'brancas'),(76,11,'peao','36','34',1,'pretas'),(77,11,'bispo','20','53',2,'brancas'),(78,11,'cavalo','67','55',2,'pretas'),(79,11,'peao','41','42',3,'brancas'),(80,11,'peao','26','24',3,'pretas'),(81,11,'peao','21','22',4,'brancas'),(82,11,'cavalo','17','25',4,'pretas'),(83,11,'cavalo','10','31',5,'brancas'),(84,11,'peao','46','45',5,'pretas'),(85,11,'cavalo','60','52',6,'brancas'),(86,11,'bispo','57','35',6,'pretas'),(87,11,'bispo','53','62',7,'brancas'),(88,11,'roque','11','31',7,'pretas'),(89,11,'bispo','50','32',8,'brancas'),(90,11,'peao','16','15',8,'pretas'),(91,11,'rainha','30','41',9,'brancas'),(92,11,'bispo','27','16',9,'pretas'),(93,12,'peao','31','33',1,'brancas'),(94,12,'cavalo','67','55',1,'pretas'),(95,12,'bispo','20','53',2,'brancas'),(96,12,'peao','66','65',2,'pretas'),(97,12,'cavalo','60','52',3,'brancas'),(98,12,'bispo','57','66',3,'pretas'),(99,12,'peao','41','42',4,'brancas'),(100,12,'peao','36','35',3,'pretas'),(101,13,'peao','31','33',1,'brancas'),(102,13,'cavalo','67','55',1,'pretas'),(103,13,'cavalo','10','22',2,'brancas'),(104,13,'peao','36','34',2,'pretas'),(105,13,'bispo','20','53',2,'brancas'),(106,14,'cavalo','60','52',1,'brancas'),(107,14,'peao','36','34',1,'pretas'),(108,14,'peao','61','62',2,'brancas'),(109,14,'cavalo','67','55',2,'pretas'),(110,14,'bispo','50','61',3,'brancas'),(111,14,'peao','26','25',3,'pretas'),(112,14,'roque','50','61',3,'brancas'),(113,15,'cavalo','60','52',1,'brancas'),(114,15,'peao','36','34',1,'pretas'),(115,15,'peao','21','23',2,'brancas'),(116,15,'peao','34','33',2,'pretas'),(117,15,'peao','61','62',3,'brancas'),(118,15,'peao','26','24',3,'pretas');
/*!40000 ALTER TABLE `jogada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jogo`
--

DROP TABLE IF EXISTS `jogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jogo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome_abertura` varchar(255) DEFAULT NULL,
  `variacao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jogo`
--

LOCK TABLES `jogo` WRITE;
/*!40000 ALTER TABLE `jogo` DISABLE KEYS */;
INSERT INTO `jogo` VALUES (1,'defesa_siciliana','ds-open-variation'),(2,'defesa_siciliana','ds-dragon-variation'),(3,'defesa_siciliana','ds-najdorf-variation'),(5,'ruy_lopez','rl-main-variation'),(6,'ruy_lopez','rl-closed-variation'),(7,'ruy_lopez','rl-open-variation'),(8,'ruy_lopez','rl-exchange-variation'),(9,'gambito_da_rainha','gr-gambito-rejeitado'),(10,'gambito_da_rainha','gr-gambito-aceito'),(11,'sistema_london','sl-main-variation'),(12,'sistema_london','sl-indian-setup'),(13,'sistema_london','sl-jobava-london'),(14,'reti','r-kings-indian-attack'),(15,'reti','r-reti-gambit');
/*!40000 ALTER TABLE `jogo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-11 23:30:25
