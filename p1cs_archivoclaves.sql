-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: p1cs
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `archivoclaves`
--

DROP TABLE IF EXISTS `archivoclaves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `archivoclaves` (
  `nombre` varchar(256) NOT NULL,
  `clave` varchar(512) NOT NULL,
  `usuario` varchar(256) DEFAULT NULL,
  `tipo` int DEFAULT NULL,
  KEY `usuario_idx` (`usuario`),
  KEY `FK_archivoclaves_tipo_idx` (`tipo`),
  CONSTRAINT `FK_archivoclaves_tipo` FOREIGN KEY (`tipo`) REFERENCES `tiposarchivo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usuario` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archivoclaves`
--

LOCK TABLES `archivoclaves` WRITE;
/*!40000 ALTER TABLE `archivoclaves` DISABLE KEYS */;
INSERT INTO `archivoclaves` VALUES ('ciudad.jpg','kWT0RrmeITfpJA4xcDuWDBLtHXpD96j4LgIhtxETlgc8LJzCX7HQAJLUlLhx0bl86cS8Db0WwDN8Mw0ae20A9gP4C8dihUiJmaAE1V2sNvvnO44OicaojcakkI9Q7QeFS7CAIuUQh11eTUeYVdYtTe4UmvvpZkKilWmV/8OIs+KapW9HEiHSLd9pmgDYvah0PwttSvlhAXryKejVQFtQR7OHQpzrOAwYr8ERmUTQy7QB5WKRw53duN/ysVROLD2eij5cWgp54mjAd0jRh9sK+zN1NaGQZpiRjmcnTO1kf8t3Hh9r9VcJgncfPwf8tdMyX6/RJoy2UBeRkl1tIwwStw==','admin',3),('archivo1','clave1','admin',3),('archivo2','clave2','admin',3);
/*!40000 ALTER TABLE `archivoclaves` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-29 12:35:34
