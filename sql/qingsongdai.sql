-- MySQL dump 10.17  Distrib 10.3.15-MariaDB, for Linux (x86_64)

--

-- Host: localhost    Database: qingsongdai

-- ------------------------------------------------------

-- Server version	10.3.15-MariaDB



/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;

/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;

/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;

/*!40101 SET NAMES utf8mb4 */;

/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;

/*!40103 SET TIME_ZONE='+00:00' */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;



--

-- Table structure for table `borrow_money_flow`

--



DROP TABLE IF EXISTS `borrow_money_flow`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `borrow_money_flow` (

  `serial_number` int(10) NOT NULL AUTO_INCREMENT,

  `bill_id` int(10) NOT NULL,

  `in_bound_account` varchar(30) NOT NULL COMMENT '借入方资金账户',

  `out_bound_account` varchar(30) NOT NULL COMMENT '借出方资金账户',

  `money` double(15,3) NOT NULL,

  `exact_date` datetime NOT NULL,

  PRIMARY KEY (`serial_number`),

  KEY `bill_id` (`bill_id`),

  KEY `in_bound_account` (`in_bound_account`),

  KEY `out_bound_account` (`out_bound_account`),

  CONSTRAINT `borrow_money_flow_ibfk_1` FOREIGN KEY (`bill_id`) REFERENCES `intend_borrow` (`bill_id`) ON DELETE CASCADE,

  CONSTRAINT `borrow_money_flow_ibfk_2` FOREIGN KEY (`in_bound_account`) REFERENCES `borrower_account` (`funds_account`) ON DELETE CASCADE,

  CONSTRAINT `borrow_money_flow_ibfk_3` FOREIGN KEY (`out_bound_account`) REFERENCES `lender_account` (`funds_account`) ON DELETE CASCADE

) ENGINE=InnoDB AUTO_INCREMENT=100000000 DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;



--

-- Dumping data for table `borrow_money_flow`

--



LOCK TABLES `borrow_money_flow` WRITE;

/*!40000 ALTER TABLE `borrow_money_flow` DISABLE KEYS */;

/*!40000 ALTER TABLE `borrow_money_flow` ENABLE KEYS */;

UNLOCK TABLES;



--

-- Table structure for table `borrower_account`

--



DROP TABLE IF EXISTS `borrower_account`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `borrower_account` (

  `funds_account` varchar(30) NOT NULL,

  `id_card` char(18) NOT NULL,

  `account_balance` double(15,3) NOT NULL,

  `borrowed_money` double(15,3) NOT NULL,

  `credit_score` int(4) NOT NULL,

  `total_limit` double(15,3) NOT NULL COMMENT '总额度',

  `available_limit` double(15,3) NOT NULL COMMENT '可用额度',

  PRIMARY KEY (`funds_account`),

  KEY `id_card` (`id_card`),

  CONSTRAINT `borrower_account_ibfk_1` FOREIGN KEY (`id_card`) REFERENCES `user_required_info` (`id_card`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;



--

-- Dumping data for table `borrower_account`

--



LOCK TABLES `borrower_account` WRITE;

/*!40000 ALTER TABLE `borrower_account` DISABLE KEYS */;

/*!40000 ALTER TABLE `borrower_account` ENABLE KEYS */;

UNLOCK TABLES;



--

-- Table structure for table `configuration`

--



DROP TABLE IF EXISTS `configuration`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `configuration` (

  `parameter_name` varchar(30) NOT NULL,

  `parameter_value` varchar(30) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;



--

-- Dumping data for table `configuration`

--



LOCK TABLES `configuration` WRITE;

/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;

/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;

UNLOCK TABLES;



--

-- Table structure for table `depository`

--



DROP TABLE IF EXISTS `depository`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `depository` (

  `funds_account` varchar(30) NOT NULL,

  `depository_account` varchar(30) NOT NULL,

  PRIMARY KEY (`funds_account`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;



--

-- Dumping data for table `depository`

--



LOCK TABLES `depository` WRITE;

/*!40000 ALTER TABLE `depository` DISABLE KEYS */;

/*!40000 ALTER TABLE `depository` ENABLE KEYS */;

UNLOCK TABLES;



--

-- Table structure for table `intend_borrow`

--



DROP TABLE IF EXISTS `intend_borrow`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `intend_borrow` (

  `bill_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '流水号id',

  `id_card` char(18) NOT NULL,

  `intend_money` double(15,3) NOT NULL,

  `start_date` datetime NOT NULL,

  `pay_rate` float(5,5) NOT NULL,

  `pay_type` int(1) NOT NULL COMMENT '还款方式，按月还款，按季还款',

  `limit_months` int(2) NOT NULL COMMENT '借款期限',

  `state` int(1) NOT NULL COMMENT '-1 represent fail,0 represent start,1 represent successful',

  `raised_money` double(15,3) NOT NULL,

  PRIMARY KEY (`bill_id`),

  KEY `id_card` (`id_card`),

  CONSTRAINT `intend_borrow_ibfk_1` FOREIGN KEY (`id_card`) REFERENCES `user_required_info` (`id_card`) ON DELETE CASCADE

) ENGINE=InnoDB AUTO_INCREMENT=100000000 DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;



--

-- Dumping data for table `intend_borrow`

--



LOCK TABLES `intend_borrow` WRITE;

/*!40000 ALTER TABLE `intend_borrow` DISABLE KEYS */;

/*!40000 ALTER TABLE `intend_borrow` ENABLE KEYS */;

UNLOCK TABLES;



--

-- Table structure for table `intend_lend`

--



DROP TABLE IF EXISTS `intend_lend`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `intend_lend` (

  `bill_id` int(10) NOT NULL COMMENT '账目id',

  `id_card` char(18) NOT NULL,

  `lend_money` double(15,3) NOT NULL,

  `start_date` datetime NOT NULL,

  `state` int(1) NOT NULL,

  PRIMARY KEY (`bill_id`,`id_card`),

  KEY `id_card` (`id_card`),

  CONSTRAINT `intend_lend_ibfk_1` FOREIGN KEY (`id_card`) REFERENCES `user_required_info` (`id_card`) ON DELETE CASCADE,

  CONSTRAINT `intend_lend_ibfk_2` FOREIGN KEY (`bill_id`) REFERENCES `intend_borrow` (`bill_id`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;



--

-- Dumping data for table `intend_lend`

--



LOCK TABLES `intend_lend` WRITE;

/*!40000 ALTER TABLE `intend_lend` DISABLE KEYS */;

/*!40000 ALTER TABLE `intend_lend` ENABLE KEYS */;

UNLOCK TABLES;



--

-- Table structure for table `lender_account`

--



DROP TABLE IF EXISTS `lender_account`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `lender_account` (

  `funds_account` varchar(30) NOT NULL,

  `id_card` char(18) NOT NULL,

  `account_balance` double(15,3) NOT NULL,

  `lent_money` double(15,3) NOT NULL,

  `current_income` double(15,3) NOT NULL,

  `expected_income` double(15,3) NOT NULL,

  PRIMARY KEY (`funds_account`),

  KEY `id_card` (`id_card`),

  CONSTRAINT `lender_account_ibfk_1` FOREIGN KEY (`id_card`) REFERENCES `user_required_info` (`id_card`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;



--

-- Dumping data for table `lender_account`

--



LOCK TABLES `lender_account` WRITE;

/*!40000 ALTER TABLE `lender_account` DISABLE KEYS */;

/*!40000 ALTER TABLE `lender_account` ENABLE KEYS */;

UNLOCK TABLES;



--

-- Table structure for table `recharge_record`

--



DROP TABLE IF EXISTS `recharge_record`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `recharge_record` (

  `serial_number` int(10) NOT NULL AUTO_INCREMENT COMMENT '流水号id',

  `recharge_date` datetime NOT NULL,

  `recharge_money` double(15,3) NOT NULL,

  `bank_account` varchar(30) NOT NULL,

  PRIMARY KEY (`serial_number`),

  KEY `bank_account_index` (`bank_account`(10))

) ENGINE=InnoDB AUTO_INCREMENT=100000000 DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;



--

-- Dumping data for table `recharge_record`

--



LOCK TABLES `recharge_record` WRITE;

/*!40000 ALTER TABLE `recharge_record` DISABLE KEYS */;

/*!40000 ALTER TABLE `recharge_record` ENABLE KEYS */;

UNLOCK TABLES;



--

-- Table structure for table `repay_money_flow`

--



DROP TABLE IF EXISTS `repay_money_flow`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `repay_money_flow` (

  `serial_number` int(10) NOT NULL AUTO_INCREMENT,

  `bill_id` int(10) NOT NULL,

  `in_bound_account` varchar(30) NOT NULL COMMENT '借入方资金账户',

  `out_bound_account` varchar(30) NOT NULL COMMENT '借出方资金账户',

  `exact_date` datetime NOT NULL,

  `money` double(15,3) NOT NULL,

  `interest_money` double(15,3) NOT NULL,

  `liquidated_money` double(15,3) NOT NULL,

  `principal_money` double(15,3) NOT NULL,

  PRIMARY KEY (`serial_number`),

  KEY `bill_id` (`bill_id`),

  KEY `in_bound_account` (`in_bound_account`),

  KEY `out_bound_account` (`out_bound_account`),

  CONSTRAINT `repay_money_flow_ibfk_1` FOREIGN KEY (`bill_id`) REFERENCES `intend_borrow` (`bill_id`) ON DELETE CASCADE,

  CONSTRAINT `repay_money_flow_ibfk_2` FOREIGN KEY (`in_bound_account`) REFERENCES `borrower_account` (`funds_account`) ON DELETE CASCADE,

  CONSTRAINT `repay_money_flow_ibfk_3` FOREIGN KEY (`out_bound_account`) REFERENCES `lender_account` (`funds_account`) ON DELETE CASCADE

) ENGINE=InnoDB AUTO_INCREMENT=100000000 DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;



--

-- Dumping data for table `repay_money_flow`

--



LOCK TABLES `repay_money_flow` WRITE;

/*!40000 ALTER TABLE `repay_money_flow` DISABLE KEYS */;

/*!40000 ALTER TABLE `repay_money_flow` ENABLE KEYS */;

UNLOCK TABLES;



--

-- Table structure for table `trade`

--



DROP TABLE IF EXISTS `trade`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `trade` (

  `serial_number` int(10) NOT NULL AUTO_INCREMENT,

  `bill_id` int(10) NOT NULL,

  `in_bound_account` varchar(30) NOT NULL,

  `out_bound_account` varchar(30) NOT NULL,

  `money` double(15,3) NOT NULL,

  `limit_months` int(2) NOT NULL,

  `pay_type` int(1) NOT NULL,

  `pay_rate` float(5,5) NOT NULL,

  `exact_date` datetime NOT NULL,

  `next_time_pay` double(15,3) NOT NULL,

  `repaid_principal` double(15,3) NOT NULL COMMENT '已还本金',

  `repaid_interest` double(15,3) NOT NULL COMMENT '已还利息',

  `liquidated_money` double(15,3) NOT NULL COMMENT '已还违约金',

  `should_repay_principal` double(15,3) NOT NULL COMMENT '下次应还本金',

  `should_repay_interest` double(15,3) NOT NULL COMMENT '下次应还利息',

  `should_repay_liquidated_money` double(15,3) NOT NULL COMMENT '总共应还违约金',

  `finished_date` datetime NOT NULL COMMENT '账单完成日期',

  PRIMARY KEY (`serial_number`),

  KEY `bill_id` (`bill_id`),

  KEY `in_bound_account` (`in_bound_account`),

  KEY `out_bound_account` (`out_bound_account`),

  CONSTRAINT `trade_ibfk_1` FOREIGN KEY (`bill_id`) REFERENCES `intend_borrow` (`bill_id`) ON DELETE CASCADE,

  CONSTRAINT `trade_ibfk_2` FOREIGN KEY (`in_bound_account`) REFERENCES `borrower_account` (`funds_account`) ON DELETE CASCADE,

  CONSTRAINT `trade_ibfk_3` FOREIGN KEY (`out_bound_account`) REFERENCES `lender_account` (`funds_account`) ON DELETE CASCADE

) ENGINE=InnoDB AUTO_INCREMENT=100000000 DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;



--

-- Dumping data for table `trade`

--



LOCK TABLES `trade` WRITE;

/*!40000 ALTER TABLE `trade` DISABLE KEYS */;

/*!40000 ALTER TABLE `trade` ENABLE KEYS */;

UNLOCK TABLES;



--

-- Table structure for table `user_optional_info`

--



DROP TABLE IF EXISTS `user_optional_info`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `user_optional_info` (

  `id_card` char(18) NOT NULL,

  `sex` char(1) DEFAULT NULL,

  `educational_level` varchar(3) DEFAULT NULL,

  `marriage` char(2) DEFAULT NULL,

  `profession` varchar(10) DEFAULT NULL,

  `address` varchar(30) DEFAULT NULL,

  `avatar` mediumblob DEFAULT NULL,

  `special_identity` char(1) DEFAULT NULL,

  PRIMARY KEY (`id_card`),

  CONSTRAINT `user_optional_info_ibfk_1` FOREIGN KEY (`id_card`) REFERENCES `user_required_info` (`id_card`) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;



--

-- Dumping data for table `user_optional_info`

--



LOCK TABLES `user_optional_info` WRITE;

/*!40000 ALTER TABLE `user_optional_info` DISABLE KEYS */;

/*!40000 ALTER TABLE `user_optional_info` ENABLE KEYS */;

UNLOCK TABLES;



--

-- Table structure for table `user_required_info`

--



DROP TABLE IF EXISTS `user_required_info`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `user_required_info` (

  `phone_number` char(11) DEFAULT NULL,

  `id_card` char(18) NOT NULL,

  `user_name` varchar(30) NOT NULL,

  `bank_account` varchar(19) NOT NULL,

  `user_type` int(1) NOT NULL,

  `password` varchar(30) NOT NULL,

  PRIMARY KEY (`id_card`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;



--

-- Dumping data for table `user_required_info`

--



LOCK TABLES `user_required_info` WRITE;

/*!40000 ALTER TABLE `user_required_info` DISABLE KEYS */;

/*!40000 ALTER TABLE `user_required_info` ENABLE KEYS */;

UNLOCK TABLES;



--

-- Table structure for table `withdraw_record`

--



DROP TABLE IF EXISTS `withdraw_record`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `withdraw_record` (

  `serial_number` int(10) NOT NULL AUTO_INCREMENT COMMENT '流水号id',

  `withdraw_date` datetime NOT NULL,

  `withdraw_money` double(15,3) NOT NULL,

  `bank_account` char(19) NOT NULL,

  PRIMARY KEY (`serial_number`),

  KEY `bank_account_index` (`bank_account`(10))

) ENGINE=InnoDB AUTO_INCREMENT=100000000 DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;



--

-- Dumping data for table `withdraw_record`

--



LOCK TABLES `withdraw_record` WRITE;

/*!40000 ALTER TABLE `withdraw_record` DISABLE KEYS */;

/*!40000 ALTER TABLE `withdraw_record` ENABLE KEYS */;

UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;

/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;

/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;



-- Dump completed on 2019-05-31 16:50:55