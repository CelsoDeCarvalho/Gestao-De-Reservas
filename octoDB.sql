-- MySQL dump 10.13  Distrib 8.0.23, for Linux (x86_64)
--
-- Host: localhost    Database: OctoDB
-- ------------------------------------------------------
-- Server version	8.0.23-0ubuntu0.20.04.1

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
-- Table structure for table `Prato`
--

DROP TABLE IF EXISTS `Prato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Prato` (
  `idPrato` int NOT NULL AUTO_INCREMENT,
  `caminhoFoto` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `preco` double NOT NULL,
  `tipo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idRestauracao` int DEFAULT NULL,
  PRIMARY KEY (`idPrato`),
  KEY `FKoviobjar49gw3bytj7rx3h6c2` (`idRestauracao`),
  CONSTRAINT `FKoviobjar49gw3bytj7rx3h6c2` FOREIGN KEY (`idRestauracao`) REFERENCES `restauracao` (`idEntidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prato`
--

LOCK TABLES `Prato` WRITE;
/*!40000 ALTER TABLE `Prato` DISABLE KEYS */;
/*!40000 ALTER TABLE `Prato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `idFuncionario` int NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  CONSTRAINT `FKffxh7a9o5w1pgo4h8w40qjai5` FOREIGN KEY (`idFuncionario`) REFERENCES `funcionario` (`idFuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alojamento`
--

DROP TABLE IF EXISTS `alojamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alojamento` (
  `idEntidade` int NOT NULL,
  PRIMARY KEY (`idEntidade`),
  CONSTRAINT `FKib6jq5vfg23m4ecfamwhupm8g` FOREIGN KEY (`idEntidade`) REFERENCES `entidade` (`idEntidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alojamento`
--

LOCK TABLES `alojamento` WRITE;
/*!40000 ALTER TABLE `alojamento` DISABLE KEYS */;
INSERT INTO `alojamento` VALUES (2);
/*!40000 ALTER TABLE `alojamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `central`
--

DROP TABLE IF EXISTS `central`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `central` (
  `idEntidade` int NOT NULL,
  PRIMARY KEY (`idEntidade`),
  CONSTRAINT `FK97vofdveilithvb9gep1ox1gp` FOREIGN KEY (`idEntidade`) REFERENCES `entidade` (`idEntidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `central`
--

LOCK TABLES `central` WRITE;
/*!40000 ALTER TABLE `central` DISABLE KEYS */;
INSERT INTO `central` VALUES (1);
/*!40000 ALTER TABLE `central` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `dataEntrada` date DEFAULT NULL,
  `dataSaida` date DEFAULT NULL,
  `nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `numeroQuarto` int NOT NULL,
  `valorPagar` double NOT NULL,
  `idAlojamento` int DEFAULT NULL,
  PRIMARY KEY (`idCliente`),
  KEY `FKldg4x0vsmrwcdhrryw5ihe9yw` (`idAlojamento`),
  CONSTRAINT `FKldg4x0vsmrwcdhrryw5ihe9yw` FOREIGN KEY (`idAlojamento`) REFERENCES `alojamento` (`idEntidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contato`
--

DROP TABLE IF EXISTS `contato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contato` (
  `idContato` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telefone` int NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idEntidade` int DEFAULT NULL,
  PRIMARY KEY (`idContato`),
  KEY `FKhsl3yfhc3sx3hhdw7qnwht1ha` (`idEntidade`),
  CONSTRAINT `FKhsl3yfhc3sx3hhdw7qnwht1ha` FOREIGN KEY (`idEntidade`) REFERENCES `entidade` (`idEntidade`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contato`
--

LOCK TABLES `contato` WRITE;
/*!40000 ALTER TABLE `contato` DISABLE KEYS */;
INSERT INTO `contato` VALUES (1,'celso@gmail.com',844950492,'www.muhipiti.com',1),(2,'executivo@gmail.com',845032567,'www.hexecutivo.com',2),(3,'nelas@gmail.com',874534567,'www.nelasbar.com',3);
/*!40000 ALTER TABLE `contato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidade`
--

DROP TABLE IF EXISTS `entidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entidade` (
  `idEntidade` int NOT NULL AUTO_INCREMENT,
  `classificacao` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enderecoFisico` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tipo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idEntidade`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidade`
--

LOCK TABLES `entidade` WRITE;
/*!40000 ALTER TABLE `entidade` DISABLE KEYS */;
INSERT INTO `entidade` VALUES (1,NULL,'Av 3 De Fevereiro','Central Muhipiti','celso1999','','celso momade'),(2,'5 Estrelas','Av Eduardo Mondlane','Hotel Executivo','catia1999','Hotel','catia'),(3,NULL,'Av Muahivire Expansao','Nela\'s Bar','neeno1999','Bar','neeno');
/*!40000 ALTER TABLE `entidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faxineiro`
--

DROP TABLE IF EXISTS `faxineiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faxineiro` (
  `idFuncionario` int NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  CONSTRAINT `FKdlmlyta7euxfv4oevu2qxi918` FOREIGN KEY (`idFuncionario`) REFERENCES `funcionario` (`idFuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faxineiro`
--

LOCK TABLES `faxineiro` WRITE;
/*!40000 ALTER TABLE `faxineiro` DISABLE KEYS */;
/*!40000 ALTER TABLE `faxineiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario` (
  `idFuncionario` int NOT NULL AUTO_INCREMENT,
  `apelido` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sexo` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telefone` int NOT NULL,
  `tipo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idEntidade` int DEFAULT NULL,
  PRIMARY KEY (`idFuncionario`),
  KEY `FKo9m9hcvc1rvgto4dgmurvj84n` (`idEntidade`),
  CONSTRAINT `FKo9m9hcvc1rvgto4dgmurvj84n` FOREIGN KEY (`idEntidade`) REFERENCES `entidade` (`idEntidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quarto`
--

DROP TABLE IF EXISTS `quarto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quarto` (
  `idQuarto` int NOT NULL AUTO_INCREMENT,
  `numero` int NOT NULL,
  `ocupado` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `quantidadeCamas` int NOT NULL,
  `idAlojamento` int DEFAULT NULL,
  PRIMARY KEY (`idQuarto`),
  KEY `FKqw43hhwkh5mtt09ugjix3hqj6` (`idAlojamento`),
  CONSTRAINT `FKqw43hhwkh5mtt09ugjix3hqj6` FOREIGN KEY (`idAlojamento`) REFERENCES `alojamento` (`idEntidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quarto`
--

LOCK TABLES `quarto` WRITE;
/*!40000 ALTER TABLE `quarto` DISABLE KEYS */;
/*!40000 ALTER TABLE `quarto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relatorio`
--

DROP TABLE IF EXISTS `relatorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `relatorio` (
  `idRelatorio` int NOT NULL AUTO_INCREMENT,
  `dataEntrada` date DEFAULT NULL,
  `dataSaida` date DEFAULT NULL,
  `nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `numeroQuarto` int NOT NULL,
  `valorPagar` double NOT NULL,
  `idEntidade` int DEFAULT NULL,
  PRIMARY KEY (`idRelatorio`),
  KEY `FK1v2y1ebpcoksfkidk4j797eb2` (`idEntidade`),
  CONSTRAINT `FK1v2y1ebpcoksfkidk4j797eb2` FOREIGN KEY (`idEntidade`) REFERENCES `entidade` (`idEntidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relatorio`
--

LOCK TABLES `relatorio` WRITE;
/*!40000 ALTER TABLE `relatorio` DISABLE KEYS */;
/*!40000 ALTER TABLE `relatorio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restauracao`
--

DROP TABLE IF EXISTS `restauracao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restauracao` (
  `totalCadeiras` int NOT NULL,
  `totalMesas` int NOT NULL,
  `idEntidade` int NOT NULL,
  PRIMARY KEY (`idEntidade`),
  CONSTRAINT `FKgrqrd11qkni5vvj6hh674qcac` FOREIGN KEY (`idEntidade`) REFERENCES `entidade` (`idEntidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restauracao`
--

LOCK TABLES `restauracao` WRITE;
/*!40000 ALTER TABLE `restauracao` DISABLE KEYS */;
INSERT INTO `restauracao` VALUES (0,0,3);
/*!40000 ALTER TABLE `restauracao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idFuncionario` int NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  CONSTRAINT `FKkcbdm8ju2q1b5e56sgsosmdgq` FOREIGN KEY (`idFuncionario`) REFERENCES `funcionario` (`idFuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'OctoDB'
--

--
-- Dumping routines for database 'OctoDB'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-02 14:23:57
