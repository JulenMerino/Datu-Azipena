CREATE DATABASE  IF NOT EXISTS `fitxategikonbinatuak` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fitxategikonbinatuak`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: fitxategikonbinatuak
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `enpresa`
--

DROP TABLE IF EXISTS `enpresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enpresa` (
  `idEnpresa` varchar(45) NOT NULL,
  `Izena` varchar(45) NOT NULL,
  `Sektorea` varchar(45) NOT NULL,
  `PertsonaKop` int NOT NULL,
  `Telefonoa` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Helbidea` varchar(45) NOT NULL,
  `Hiria` varchar(45) NOT NULL,
  PRIMARY KEY (`idEnpresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enpresa`
--

LOCK TABLES `enpresa` WRITE;
/*!40000 ALTER TABLE `enpresa` DISABLE KEYS */;
INSERT INTO `enpresa` VALUES ('1','TecnoSoluciones S.A.','Tecnología',120,'+52 55 1234 5678','contacto@tecnosoluciones.com','Av. Insurgentes Sur 1234, Piso 4','Ciudad de México'),('2','VerdeVida Agroindustrial','Agricultura',80,'+57 1 9876 5432','info@verdevida.co','Calle 45 No. 23-56','Bogotá'),('3','SaludVital Clínicas','Salud',50,'+34 910 234 567','contacto@saludvital.es','Calle Mayor 89, Planta 2','Madrid'),('4','Logística Global Express','Transporte y Logística',150,'+1 212 345 6789','support@globalexpress.com','456 7th Avenue','Nueva York');
/*!40000 ALTER TABLE `enpresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `langilea`
--

DROP TABLE IF EXISTS `langilea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `langilea` (
  `idLangilea` int NOT NULL AUTO_INCREMENT,
  `Abizena` varchar(45) NOT NULL,
  `Izena` varchar(45) NOT NULL,
  `Kargua` varchar(45) NOT NULL,
  `Tratua` varchar(45) NOT NULL,
  `JaiotzeData` date NOT NULL,
  `KontratazioData` date NOT NULL,
  `Helbidea` varchar(45) NOT NULL,
  `Hiria` varchar(45) NOT NULL,
  `Lanean` tinyint(1) NOT NULL,
  `Enpresa_idEnpresa` varchar(45) NOT NULL,
  PRIMARY KEY (`idLangilea`),
  KEY `fk_Langilea_Enpresa_idx` (`Enpresa_idEnpresa`),
  CONSTRAINT `fk_Langilea_Enpresa` FOREIGN KEY (`Enpresa_idEnpresa`) REFERENCES `enpresa` (`idEnpresa`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `langilea`
--

LOCK TABLES `langilea` WRITE;
/*!40000 ALTER TABLE `langilea` DISABLE KEYS */;
INSERT INTO `langilea` VALUES (28,'Davolio','Nancy','Representante de ventas','Stra.','1968-12-08','1992-05-01','507-20th Ave.E.','Seattle',1,'4'),(29,'Fuller','Andrew','Vicepresidente comercial','Dr.','1952-02-19','1992-08-14','908 W. Capital Way','Tacoma',1,'3'),(30,'Leverling','Janet','Representante de ventas','Stra.','1963-08-30','1992-04-01','722 Moss Bay Blvd.','Kirkland',1,'3'),(31,'Peacock','Margaret','Representante de ventas','Sra.','1958-09-19','1993-05-03','4110 Old Redmond Rd.','Redmond',0,'3'),(32,'Buchanan','Steven','Gerente de ventas','Sr.','1955-03-04','1993-10-17','14 Garrett Hill','Londres',1,'2'),(33,'Suyama','Michel','Representante de ventas','Sr.','1963-07-02','1993-10-17','Coventry House','Londres',1,'1'),(34,'King','Robert','Representante de ventas','Sr.','1960-05-29','1994-01-02','Edgeham Hollow','Londres',0,'1'),(35,'Callahan','Laura','Coordinador ventas internacionales','Stra.','1958-01-09','1994-03-05','4726-11th Ave.N.E.','Seattle',1,'4'),(36,'Dodsworth','Anne','Representante de ventas','Stra.','1969-07-02','1994-11-02','7 Houndstooth Rd.','Londres',1,'2');
/*!40000 ALTER TABLE `langilea` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-14 11:58:32
