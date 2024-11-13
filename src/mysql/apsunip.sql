-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: apsunip
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
-- Table structure for table `campeonato`
--

DROP TABLE IF EXISTS `campeonato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campeonato` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `ano` year NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campeonato`
--

LOCK TABLES `campeonato` WRITE;
/*!40000 ALTER TABLE `campeonato` DISABLE KEYS */;
INSERT INTO `campeonato` VALUES (1,'Série A',2024),(2,'Série B',2024),(50,'Série C',2024);
/*!40000 ALTER TABLE `campeonato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campeonato_time`
--

DROP TABLE IF EXISTS `campeonato_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campeonato_time` (
  `id_campeonato` int NOT NULL,
  `id_time` int NOT NULL,
  PRIMARY KEY (`id_campeonato`,`id_time`),
  KEY `id_time` (`id_time`),
  CONSTRAINT `campeonato_time_ibfk_1` FOREIGN KEY (`id_campeonato`) REFERENCES `campeonato` (`id`) ON DELETE CASCADE,
  CONSTRAINT `campeonato_time_ibfk_2` FOREIGN KEY (`id_time`) REFERENCES `time` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campeonato_time`
--

LOCK TABLES `campeonato_time` WRITE;
/*!40000 ALTER TABLE `campeonato_time` DISABLE KEYS */;
INSERT INTO `campeonato_time` VALUES (1,1),(1,2),(1,3),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31),(2,32),(2,33),(2,34),(2,35),(2,36),(2,37),(2,38),(2,39),(2,40),(2,41),(2,42);
/*!40000 ALTER TABLE `campeonato_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jogo`
--

DROP TABLE IF EXISTS `jogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jogo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `campeonato` int NOT NULL,
  `time_mandante` int NOT NULL,
  `time_visitante` int NOT NULL,
  `data_hora` datetime NOT NULL,
  `estadio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `jogo_ibfk_2` (`time_mandante`),
  KEY `jogo_ibfk_3` (`time_visitante`),
  KEY `jogo_ibfk_1` (`campeonato`),
  CONSTRAINT `jogo_ibfk_1` FOREIGN KEY (`campeonato`) REFERENCES `campeonato` (`id`) ON DELETE CASCADE,
  CONSTRAINT `jogo_ibfk_2` FOREIGN KEY (`time_mandante`) REFERENCES `time` (`id`) ON DELETE CASCADE,
  CONSTRAINT `jogo_ibfk_3` FOREIGN KEY (`time_visitante`) REFERENCES `time` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jogo`
--

LOCK TABLES `jogo` WRITE;
/*!40000 ALTER TABLE `jogo` DISABLE KEYS */;
INSERT INTO `jogo` VALUES (4,1,1,3,'2024-11-05 14:00:00','Maracanã'),(5,1,9,7,'2024-11-13 20:00:00','Arena Fonte Nova'),(6,1,8,22,'2024-11-13 22:00:00','Arena da Baixada'),(10,2,23,29,'2024-11-15 16:00:00','Vila Belmiro');
/*!40000 ALTER TABLE `jogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time`
--

DROP TABLE IF EXISTS `time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time`
--

LOCK TABLES `time` WRITE;
/*!40000 ALTER TABLE `time` DISABLE KEYS */;
INSERT INTO `time` VALUES (34,'Amazonas'),(29,'América-MG'),(8,'Athletico'),(22,'Atlético-GO'),(7,'Atlético-MG'),(33,'Avaí'),(9,'Bahia'),(10,'Botafogo'),(37,'Botafogo-SP'),(41,'Brusque'),(27,'Ceará'),(35,'Chapecoense'),(2,'Corinthians'),(31,'Coritiba'),(39,'CRB'),(11,'Criciúma'),(12,'Cruzeiro'),(13,'Cuiabá'),(3,'Flamengo'),(14,'Fluminense'),(15,'Fortaleza'),(30,'Goiás'),(17,'Grêmio'),(42,'Guarani'),(18,'Internacional'),(40,'Ituano'),(20,'Juventude'),(26,'Mirassol'),(24,'Novorizontino'),(32,'Operário-PR'),(6,'Palmeiras'),(36,'Paysandu'),(38,'Ponte Preta'),(19,'RB Bragantino'),(23,'Santos'),(21,'São Paulo'),(25,'Sport'),(53,'Tabajara'),(61,'Tabajara EC'),(60,'Tabajara FC'),(1,'Vasco'),(28,'Vila Nova'),(16,'Vitória');
/*!40000 ALTER TABLE `time` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-13 17:25:12
