-- MySQL dump 10.11
--
-- to install this database, from a terminal, type:
-- mysql -u USERNAME -p -h SERVERNAME beaditupja < beaditupja.sql
--
-- Host: localhost    Database: beaditupja
-- ------------------------------------------------------
-- Server version   5.0.45-log

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

CREATE DATABASE IF not EXISTS beaditupja;
USE beaditupja;

--
-- Table structure for table `inventory_items`
--

CREATE TABLE IF not EXISTS `inventory_items` (
  `id` int(3) NOT NULL auto_increment,
  `name` char(35) NOT NULL default '',
  `type` char(35) NOT NULL default '',
  `qty` int(10) NOT NULL,
  `limit` int(10) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=440 DEFAULT CHARSET=utf8mb4;

--
-- creating data for table `inventory_items`
--

LOCK TABLES `inventory_items` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `inventory_items` VALUES (1,'white', 'bead',100,25),
(2,'gold bead', 'bead',5000,750),
(3,'black bead','bead',10000,1000),
(4,'red bead','bead',1000,250),
(5,'blue bead','bead',800,250),
(6,'transparent','spacer',500,50),
(7,'white','spacer',500,50),
(8,'black pouch','pouch',100,25);
UNLOCK TABLES;


--
-- Table structure for table `<<name>>`
--

CREATE TABLE IF not EXISTS `products` (
  `id` int(3) NOT NULL auto_increment,
  `collection` char(35) NOT NULL default '',
  `name` char(35) NOT NULL default '',
  `description` char(150) NOT NULL default '',
  `bead1` char(35) NOT NULL,
  `bead1_qty` int(2) NOT NULL,
  `bead2` char(35) NOT NULL,
  `bead2_qty` int(2) NOT NULL,
  `spacer` char(35) NOT NULL,
  `spacer_qty` int(2) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=440 DEFAULT CHARSET=utf8mb4;

--
-- creating data for table `cities`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `inventory_items` VALUES (1,"city of gold","golden calf","golden calf bracelet in the city of gold collection",'gold bead',9,'white bead',5,'spacers',3),
(2,"emerald city","green tortoise","green tortoise bracelet in the emerald city collection",'green bead',7,'white bead',4,'black bead',3),
(3,"city of gold","golden lion","golden lion bracelet in the city of gold collection",'gold bead',7,'white bead',4,'black bead',3),
UNLOCK TABLES;

--
-- Table structure for table `<<name>>`
--

CREATE TABLE IF not EXISTS `<<name>>` (
  `id` int(3) NOT NULL auto_increment,
  `name` char(35) NOT NULL default '',
  `qty` int(10) NOT NULL,
  `limit` int(10) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=440 DEFAULT CHARSET=utf8mb4;

--
-- creating data for table `cities`
--

LOCK TABLES `inventory_items` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `inventory_items` VALUES (1,'white bead',100,25),
(2,'gold bead',5000,750),
(3,'black bead',10000,1000),
(4,'red bead',1000,250),
(5,'spacers',500,50),
(6,'blue bead',800,250),
(7,'pouches',100,25);
UNLOCK TABLES;

--
-- Table structure for table `<<name>>`
--

CREATE TABLE IF not EXISTS `<<name>>` (
  `id` int(3) NOT NULL auto_increment,
  `name` char(35) NOT NULL default '',
  `qty` int(10) NOT NULL,
  `limit` int(10) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=440 DEFAULT CHARSET=utf8mb4;

--
-- creating data for table `cities`
--

LOCK TABLES `inventory_items` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `inventory_items` VALUES (1,'white bead',100,25),
(2,'gold bead',5000,750),
(3,'black bead',10000,1000),
(4,'red bead',1000,250),
(5,'spacers',500,50),
(6,'blue bead',800,250),
(7,'pouches',100,25);
UNLOCK TABLES;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-06-06 19:04:10
