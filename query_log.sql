-- phpMyAdmin SQL Dump
-- version 4.1.9
-- http://www.phpmyadmin.net
--
-- Host: localhost:8889
-- Generation Time: Sep 18, 2015 at 06:08 PM
-- Server version: 5.5.34
-- PHP Version: 5.5.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `fits`
--

-- --------------------------------------------------------

--
-- Table structure for table `query_log`
--

CREATE TABLE `query_log` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `from_postcode` varchar(10) NOT NULL,
  `from_address` varchar(1024) NOT NULL,
  `to_postcode` varchar(10) NOT NULL,
  `to_address` varchar(1024) NOT NULL,
  `age_group` varchar(20) NOT NULL,
  `mobility_status` varchar(20) NOT NULL,
  `purpose` varchar(20) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_return` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `query_log`
--

INSERT INTO `query_log` (`id`, `from_postcode`, `from_address`, `to_postcode`, `to_address`, `age_group`, `mobility_status`, `purpose`, `timestamp`, `is_return`) VALUES
(7, '', '+uk', '', 'uk', '60 and above', 'Able bodied', 'Health Appointment', '2015-09-18 14:08:38', 0),
(8, '', '+uk', '', 'uk', '60 and above', 'Able bodied', 'Health Appointment', '2015-09-18 14:08:38', 0),
(9, '', '+uk', '', 'uk', '60 and above', 'Able bodied', 'Health Appointment', '2015-09-18 14:08:43', 0),
(10, 'AB24 4AL', '1 adsda AB24 4AL Aberdeen uk', 'AB24 5UA', '222 xyz AB24 5UA Aberdeen uk', '60 and above', 'Able bodied', 'Health Appointment', '2015-09-18 14:31:15', 0),
(11, 'AB24 5UA', '222 xyz AB24 5UA Aberdeen uk', 'AB24 4AL', '1 adsda AB24 4AL Aberdeen uk', '60 and above', 'Able bodied', 'Health Appointment', '2015-09-18 14:31:17', 1),
(12, 'AB24 4AJ', '2 a AB24 4AJ Aberdeen uk', 'AB24 4AL', 'a a AB24 4AL Aberdeen uk', '60 and above', 'Able bodied', 'Health Appointment', '2015-09-18 15:12:45', 0),
(13, 'AB24 5UA', 'a 2 AB24 5UA Aberdeen uk', 'AB24 4AL', '2 a AB24 4AL Aberdeen uk', '60 and above', 'Able bodied', 'Health Appointment', '2015-09-30 10:00:00', 0),
(14, 'AB24 5UA', 'a 2 AB24 5UA Aberdeen uk', 'AB24 4AL', '2 a AB24 4AL Aberdeen uk', '60 and above', 'Able bodied', 'Health Appointment', '2015-09-30 10:00:00', 0),
(15, 'AB24 4AL', '2 a AB24 4AL Aberdeen uk', 'AB24 5UA', 'a 2 AB24 5UA Aberdeen uk', '60 and above', 'Able bodied', 'Health Appointment', '2015-10-02 11:00:00', 1),
(16, 'AB24 5UA', 'a 2 AB24 5UA Aberdeen uk', 'AB24 4AL', '2 a AB24 4AL Aberdeen uk', '60 and above', 'Able bodied', 'Health Appointment', '2015-09-30 09:00:00', 0),
(17, 'AB24 4AL', '2 a AB24 4AL Aberdeen uk', 'AB24 5UA', 'a 2 AB24 5UA Aberdeen uk', '60 and above', 'Able bodied', 'Health Appointment', '2015-10-02 10:00:00', 1);
