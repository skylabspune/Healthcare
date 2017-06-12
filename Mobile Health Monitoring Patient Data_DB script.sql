/*
SQLyog Community v12.16 (32 bit)
MySQL - 5.1.44-community : Database - mobilehealthmonitoringsystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mobilehealthmonitoringsystem` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mobilehealthmonitoringsystem`;

/*Table structure for table `patient` */

DROP TABLE IF EXISTS `patient`;

CREATE TABLE `patient` (
  `pId` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(10) DEFAULT NULL,
  `lastname` varchar(10) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`pId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `patient` */

insert  into `patient`(`pId`,`firstname`,`lastname`,`username`,`password`) values 
(1,'varsha','ajane','varsha','varsha');

/*Table structure for table `patienthealthdetails` */

DROP TABLE IF EXISTS `patienthealthdetails`;

CREATE TABLE `patienthealthdetails` (
  `phId` int(11) NOT NULL AUTO_INCREMENT,
  `pId` int(11) NOT NULL,
  `heartBeat` int(11) DEFAULT '0',
  `temperature` double DEFAULT '0',
  `detailsDate` date DEFAULT NULL,
  `historyFlag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`phId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `patienthealthdetails` */

insert  into `patienthealthdetails`(`phId`,`pId`,`heartBeat`,`temperature`,`detailsDate`,`historyFlag`) values 
(1,1,55,30,'2016-03-09',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
