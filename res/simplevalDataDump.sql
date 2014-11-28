# ************************************************************
# Sequel Pro SQL dump
# Version 3408
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.5.38)
# Database: simpleval
# Generation Time: 2014-11-28 17:03:46 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table daten
# ------------------------------------------------------------

DROP TABLE IF EXISTS `daten`;

CREATE TABLE `daten` (
  `daten_id` int(11) NOT NULL,
  `mat_id` int(11) NOT NULL,
  `u` double NOT NULL,
  `i` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `daten` WRITE;
/*!40000 ALTER TABLE `daten` DISABLE KEYS */;

INSERT INTO `daten` (`daten_id`, `mat_id`, `u`, `i`)
VALUES
	(1,1,10,70.3),
	(2,1,20,87.5),
	(3,1,30,100),
	(4,1,40,113.4),
	(5,1,50,124.8),
	(6,1,60,136.3),
	(7,1,70,146.2),
	(8,1,80,155.4),
	(9,1,90,164.7),
	(10,1,100,173.7),
	(11,1,110,187.5),
	(12,1,120,195.7),
	(13,1,130,204),
	(14,1,140,213),
	(15,1,150,220.1),
	(16,1,160,226.2),
	(17,1,170,232),
	(18,1,180,240),
	(19,1,190,246.1),
	(20,1,200,254.1),
	(21,1,210,259),
	(22,1,220,264.9),
	(31,2,10,7.1),
	(32,2,20,14.3),
	(33,2,30,21.9),
	(34,2,40,30.9),
	(35,2,50,39.9),
	(36,2,60,48.5),
	(37,2,70,56.7),
	(38,2,80,66.6),
	(39,2,90,75.8),
	(40,2,100,84.9),
	(41,2,110,101.3),
	(42,2,120,110.9),
	(43,2,130,123),
	(44,2,140,132.7),
	(45,2,150,143.8),
	(46,2,160,154.4),
	(47,2,170,167.2),
	(48,2,180,178.7),
	(49,2,190,189.8),
	(50,2,200,202.1),
	(51,2,210,217),
	(52,2,220,228.9),
	(61,3,0.59,0.5),
	(62,3,0.62,1),
	(63,3,0.66,2),
	(64,3,0.7,5),
	(65,3,0.72,7),
	(66,3,0.74,10),
	(67,3,0.78,20),
	(68,3,0.81,30),
	(69,3,0.82,40),
	(70,3,0.84,50),
	(71,3,0.86,60),
	(72,3,0.87,70),
	(73,3,0.88,80),
	(74,3,0.9,100),
	(75,3,0.94,150),
	(76,3,0.97,200),
	(80,4,0,0),
	(81,4,0.56,0.5),
	(82,4,1.38,3),
	(83,4,1.82,5),
	(84,4,1.9,6.5),
	(85,4,1.94,8),
	(86,4,1.99,10),
	(87,4,2.01,12),
	(88,4,2.03,14),
	(89,4,2.06,16),
	(90,4,2.08,18),
	(91,4,2.09,19.5),
	(92,4,2.11,21),
	(93,4,2.13,23),
	(94,4,2.14,24.5),
	(95,4,2.16,25.5),
	(96,4,2.17,27.5),
	(97,4,2.19,29),
	(98,4,2.2,30.5),
	(99,4,2.21,32),
	(100,4,2.23,34),
	(101,4,2.24,35.5),
	(102,4,2.26,37),
	(103,4,2.27,39),
	(104,4,2.29,40.5),
	(105,4,2.3,42),
	(106,4,2.31,43.5),
	(107,4,2.33,45),
	(108,4,2.34,47),
	(109,4,2.35,48.5),
	(110,4,2.37,50),
	(111,4,2.38,51),
	(112,4,2.39,52),
	(113,4,2.39,53.5),
	(114,4,2.41,54.5),
	(115,4,2.42,56),
	(116,4,2.43,57),
	(117,4,2.43,57),
	(118,4,2.44,58),
	(119,4,2.44,58.5),
	(120,4,2.45,59.5),
	(121,4,2.45,60),
	(122,4,2.46,60.5),
	(123,4,2.46,60.5),
	(124,4,2.46,61),
	(125,4,2.47,61);

/*!40000 ALTER TABLE `daten` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table material
# ------------------------------------------------------------

DROP TABLE IF EXISTS `material`;

CREATE TABLE `material` (
  `material_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;

INSERT INTO `material` (`material_id`, `name`)
VALUES
	(1,'Gluehlampe'),
	(2,'Kohlefadenlampe'),
	(3,'Diode 1N4148'),
	(4,'LED-Gruen');

/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
