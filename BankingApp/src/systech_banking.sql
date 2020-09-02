-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.15-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for systech_banking
DROP DATABASE IF EXISTS `systech_banking`;
CREATE DATABASE IF NOT EXISTS `systech_banking` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `systech_banking`;

-- Dumping structure for table systech_banking.accounttypes
DROP TABLE IF EXISTS `accounttypes`;
CREATE TABLE IF NOT EXISTS `accounttypes` (
  `accid` int(11) NOT NULL AUTO_INCREMENT,
  `acctype` varchar(255) NOT NULL,
  PRIMARY KEY (`accid`),
  UNIQUE KEY `acctype` (`acctype`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table systech_banking.accounttypes: ~5 rows (approximately)
DELETE FROM `accounttypes`;
/*!40000 ALTER TABLE `accounttypes` DISABLE KEYS */;
INSERT INTO `accounttypes` (`accid`, `acctype`) VALUES
	(3, 'Children Saving Account'),
	(1, 'Fixed Account'),
	(5, 'Joint Account'),
	(4, 'Personal Account'),
	(2, 'Savings Account');
/*!40000 ALTER TABLE `accounttypes` ENABLE KEYS */;

-- Dumping structure for table systech_banking.countries
DROP TABLE IF EXISTS `countries`;
CREATE TABLE IF NOT EXISTS `countries` (
  `ctry_id` int(11) NOT NULL AUTO_INCREMENT,
  `ctry_name` varchar(255) NOT NULL,
  PRIMARY KEY (`ctry_id`),
  UNIQUE KEY `ctry_name` (`ctry_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Dumping data for table systech_banking.countries: ~8 rows (approximately)
DELETE FROM `countries`;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` (`ctry_id`, `ctry_name`) VALUES
	(5, 'Burundi'),
	(8, 'Ethiopia'),
	(1, 'Kenya'),
	(4, 'Rwanda'),
	(7, 'S.Sudan'),
	(6, 'Somali'),
	(9, 'Sudan'),
	(3, 'Tanzania'),
	(2, 'Uganda'),
	(10, 'Zambia');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;

-- Dumping structure for table systech_banking.customers
DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `ct_id` int(11) NOT NULL AUTO_INCREMENT,
  `ct_fname` varchar(255) NOT NULL,
  `ct_lname` varchar(255) NOT NULL,
  `ct_email` varchar(255) NOT NULL,
  `ct_phone` varchar(255) NOT NULL,
  `ct_nextkin` varchar(255) DEFAULT NULL,
  `ct_address` varchar(255) NOT NULL DEFAULT '100, 80100',
  `ct_city` varchar(255) NOT NULL DEFAULT 'Nairobi',
  `ct_country` varchar(255) NOT NULL DEFAULT 'Kenya',
  `ct_gender` varchar(255) DEFAULT NULL,
  `ct_accounttype` varchar(255) DEFAULT NULL,
  `ct_accountnumber` varchar(255) NOT NULL,
  `ct_accbalance` bigint(20) NOT NULL DEFAULT 0,
  `ct_accesscode` varchar(255) NOT NULL,
  `ct_pic` varchar(255) DEFAULT NULL,
  `ct_date` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`ct_id`),
  UNIQUE KEY `ct_email` (`ct_email`),
  UNIQUE KEY `ct_accountnumber` (`ct_accountnumber`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table systech_banking.customers: ~0 rows (approximately)
DELETE FROM `customers`;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` (`ct_id`, `ct_fname`, `ct_lname`, `ct_email`, `ct_phone`, `ct_nextkin`, `ct_address`, `ct_city`, `ct_country`, `ct_gender`, `ct_accounttype`, `ct_accountnumber`, `ct_accbalance`, `ct_accesscode`, `ct_pic`, `ct_date`) VALUES
	(1, 'Yonathaniel', 'Aviator', 'av@gmail.com', ' 254701953920', NULL, '80100, Nairobi', 'Nairobi', 'Kenya', 'M', 'Fixed Account', '15979594318459446', 0, 'EfAkei3R', NULL, '2020-08-21 00:37:11');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;

-- Dumping structure for table systech_banking.transactions
DROP TABLE IF EXISTS `transactions`;
CREATE TABLE IF NOT EXISTS `transactions` (
  `tr_id` int(11) NOT NULL AUTO_INCREMENT,
  `tr_accountnumber` varchar(255) NOT NULL,
  `tr_type` varchar(255) NOT NULL,
  `tr_amount` bigint(20) NOT NULL DEFAULT 0,
  `tr_charge` double NOT NULL DEFAULT 0,
  `tr_date` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`tr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table systech_banking.transactions: ~0 rows (approximately)
DELETE FROM `transactions`;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;

-- Dumping structure for table systech_banking.transactiontypes
DROP TABLE IF EXISTS `transactiontypes`;
CREATE TABLE IF NOT EXISTS `transactiontypes` (
  `tp_id` int(11) NOT NULL AUTO_INCREMENT,
  `tp_type` varchar(50) NOT NULL,
  `tp_charge` double DEFAULT 0,
  PRIMARY KEY (`tp_id`),
  UNIQUE KEY `tp_type` (`tp_type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table systech_banking.transactiontypes: ~4 rows (approximately)
DELETE FROM `transactiontypes`;
/*!40000 ALTER TABLE `transactiontypes` DISABLE KEYS */;
INSERT INTO `transactiontypes` (`tp_id`, `tp_type`, `tp_charge`) VALUES
	(1, 'Transfer', 220.2),
	(2, 'Deposit', 10),
	(3, 'Withdraw', 50.5),
	(4, 'Balance', 0);
/*!40000 ALTER TABLE `transactiontypes` ENABLE KEYS */;

-- Dumping structure for table systech_banking.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `usr_id` int(11) NOT NULL AUTO_INCREMENT,
  `usr_fname` varchar(255) NOT NULL,
  `usr_lname` varchar(255) NOT NULL,
  `usr_email` varchar(255) NOT NULL,
  `usr_username` varchar(255) NOT NULL,
  `usr_pwd` varchar(255) NOT NULL,
  `usr_phone` varchar(255) NOT NULL,
  `usr_role` varchar(255) NOT NULL,
  `usr_status` tinyint(4) NOT NULL DEFAULT 0,
  `usr_image` varchar(255) DEFAULT NULL,
  `usr_date` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`usr_id`),
  UNIQUE KEY `usr_email` (`usr_email`),
  UNIQUE KEY `usr_username` (`usr_username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table systech_banking.users: ~0 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`usr_id`, `usr_fname`, `usr_lname`, `usr_email`, `usr_username`, `usr_pwd`, `usr_phone`, `usr_role`, `usr_status`, `usr_image`, `usr_date`) VALUES
	(1, 'Aviator', 'K', 'admin@concorde.com', 'Aviator', '123456', '+254711268164', 'Admin', 1, NULL, '2020-08-18 16:20:25');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
