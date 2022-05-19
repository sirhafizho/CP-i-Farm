-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 20, 2022 at 01:58 AM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ifarm`
--

-- --------------------------------------------------------

--
-- Table structure for table `activities`
--

CREATE TABLE `activities` (
  `_id` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `action` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `unit` varchar(50) NOT NULL,
  `quantity` int(50) NOT NULL,
  `field` int(50) NOT NULL,
  `row` int(50) NOT NULL,
  `farmId` varchar(100) NOT NULL,
  `userId` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `activities`
--

INSERT INTO `activities` (`_id`, `date`, `action`, `type`, `unit`, `quantity`, `field`, `row`, `farmId`, `userId`) VALUES
('1', '2008-04-11', 'fertilizer', 'Other Potassic Fertilizers', 'pack (500g)', 2, 8, 8, '5', '1'),
('10', '2006-11-19', 'pesticide', 'Perchlorobenzene', 'l', 2, 7, 3, '5', '1'),
('11', '2007-10-14', 'sales', 'Orache', 'kg', 1, 3, 2, '5', '1'),
('12', '2005-02-13', 'pesticide', 'Lindafor', 'ml', 489, 10, 2, '5', '1'),
('13', '2007-05-07', 'fertilizer', 'NPK 10 10 10 + TE', 'pack (1000g)', 1, 8, 5, '7', '1'),
('14', '2020-06-02', 'fertilizer', 'Borax', 'pack (1000g)', 1, 9, 9, '7', '1'),
('15', '2021-11-05', 'pesticide', 'Pesticide C', 'l', 14, 10, 4, '7', '1'),
('16', '2014-05-27', 'sowing', 'Komatsuna', 'kg', 7, 1, 4, '7', '1'),
('17', '2001-04-04', 'sales', 'Turnip', 'g', 110, 9, 6, '7', '1'),
('18', '2014-11-17', 'harvest', 'Lamb’s quarters', 'g', 707, 7, 7, '9', '1'),
('19', '2009-08-16', 'sowing', 'Horseradish', 'g', 522, 4, 3, '9', '1'),
('2', '2000-03-05', 'sowing', 'Brussels sprouts', 'kg', 8, 3, 8, '5', '1'),
('20', '2012-04-22', 'sowing', 'Daikon', 'g', 101, 3, 8, '9', '1'),
('21', '2016-04-08', 'fertilizer', 'NPK 25 5 5', 'pack (1000g)', 1, 1, 3, '9', '1'),
('22', '2000-09-12', 'sowing', 'Fluted pumpkin', 'g', 548, 5, 5, '9', '1'),
('23', '2000-01-01', 'harvest', 'Chaya', 'kg', 10, 10, 2, '9', '1'),
('24', '2016-09-02', 'fertilizer', 'NPK 17 12 11 +3MgO + TE', 'pack (500g)', 2, 8, 1, '9', '1'),
('25', '2007-01-09', 'sowing', 'Horseradish', 'kg', 2, 10, 2, '9', '1'),
('26', '2014-01-16', 'sales', 'Kuka', 'g', 738, 3, 5, '10', '1'),
('27', '2003-01-17', 'pesticide', 'Dowicide EC', 'ml', 154, 4, 1, '10', '1'),
('28', '2004-02-10', 'harvest', 'Melokhia', 'g', 533, 3, 2, '10', '1'),
('29', '2015-10-28', 'fertilizer', 'NP 28 28 0', 'pack (500g)', 9, 1, 2, '10', '1'),
('3', '2018-04-23', 'harvest', 'Radish', 'g', 488, 8, 10, '5', '1'),
('30', '2003-02-07', 'sales', 'Horseradish', 'g', 257, 10, 5, '10', '1'),
('31', '2004-10-02', 'harvest', 'Galangal', 'g', 490, 9, 6, '10', '1'),
('32', '2012-05-08', 'pesticide', 'Eviplast', 'l', 13, 3, 5, '10', '1'),
('33', '2017-09-12', 'pesticide', 'Dowicide EC', 'l', 15, 9, 2, '10', '1'),
('34', '2009-12-08', 'fertilizer', 'NP 20 20 0', 'pack (500g)', 1, 8, 10, '2', '2'),
('35', '2013-07-07', 'sowing', 'Sea kale', 'kg', 4, 2, 5, '2', '2'),
('36', '2000-02-04', 'fertilizer', 'NPK 10 10 10 + TE', 'pack (500g)', 5, 8, 7, '2', '2'),
('37', '2020-03-10', 'sales', 'Turmeric', 'g', 684, 8, 6, '2', '2'),
('38', '2020-11-08', 'harvest', 'Earthnut pea', 'kg', 7, 8, 2, '2', '2'),
('39', '2012-07-08', 'sowing', 'Pea', 'g', 571, 9, 7, '2', '2'),
('4', '2014-03-02', 'sales', 'Soko', 'g', 699, 10, 1, '5', '1'),
('40', '2021-09-15', 'fertilizer', 'NP 20 20 0', 'pack (500g)', 7, 9, 5, '2', '2'),
('41', '2019-04-09', 'sales', 'Sea kale', 'g', 467, 3, 9, '2', '2'),
('42', '2008-04-12', 'sowing', 'Sea kale', 'g', 700, 8, 8, '2', '2'),
('43', '2008-02-01', 'sales', 'Brussels sprouts', 'kg', 5, 3, 3, '5', '2'),
('44', '2017-09-16', 'pesticide', 'Perchlorobenzene', 'l', 7, 8, 1, '5', '2'),
('45', '2014-05-07', 'sowing', 'Water caltrop', 'kg', 2, 6, 5, '5', '2'),
('46', '2013-06-09', 'pesticide', 'Gamaphex', 'ml', 570, 6, 9, '5', '2'),
('47', '2014-09-20', 'sowing', 'Camas', 'kg', 1, 1, 4, '5', '2'),
('48', '2012-01-13', 'pesticide', 'Gamaphex', 'ml', 1063, 2, 1, '5', '2'),
('49', '2021-04-13', 'fertilizer', 'Other Potassic Fertilizers', 'pack (1000g)', 4, 5, 3, '5', '2'),
('5', '2012-09-17', 'sales', 'Komatsuna', 'kg', 7, 9, 8, '5', '1'),
('6', '2010-04-11', 'fertilizer', 'NPK 17 12 11 +3MgO + TE', 'pack (1000g)', 4, 7, 8, '5', '1'),
('7', '2003-12-26', 'harvest', 'Orache', 'g', 178, 1, 6, '5', '1'),
('8', '2007-04-14', 'harvest', 'Radish', 'g', 155, 9, 10, '5', '1'),
('9', '2014-06-25', 'sowing', 'Brussels sprouts', 'g', 179, 4, 3, '5', '1');

-- --------------------------------------------------------

--
-- Table structure for table `farms`
--

CREATE TABLE `farms` (
  `_id` varchar(255) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `plants` varchar(100) DEFAULT NULL,
  `fertilizers` varchar(100) DEFAULT NULL,
  `pesticides` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `farms`
--

INSERT INTO `farms` (`_id`, `name`, `address`, `plants`, `fertilizers`, `pesticides`) VALUES
('1', 'Blue Star Estate', '235, Nelm Street', '1,2,3,4,5,6,7,8,12,30,60', '1,3,5', '1,8,17'),
('10', 'Black Bear Lands', '4899, Roguski Road', '10,21,37,45,51,61,71,81,91,97', '20,23,25,42', '19,78,79'),
('11', 'Big Oak Meadow', '4992, Froe Street', '11,12,33,44,55,56,65,77,88', '4,14,24', '46,56'),
('12', 'Rosebush Fields', '1646, Apple Lane', '12,24,36,48,52,72,84,88,93,96', '9,18,27,35,67', '20,37,53'),
('13', 'Gold Trees Lands', '351, Gregory Lane', '13,23,38,42,53,58,72,79', '56,63,74,88', '38,60,84'),
('14', 'Grassy Knoll Farmstead', '149, Charla Lane', '14,24,34,44,54,64,74,84,94', '3,45,46,79', '3,9,55,82'),
('15', 'Canyon Farms', '1839, Essex Court', '56,59,72,76,84,87,93,95', '8,92,93', '15,33,73'),
('16', 'Blackmeadow Gardens', '583, Adams Avenue', '23,32,47,48,49,68,78,80', '17,27,36,91,94', '59,67,71,86'),
('17', 'Happy Trails Lands', '4410, Daffodil Lane', '8,40,56,77,90,91,93', '20,40', '72,73,90'),
('18', 'Honey Farms', '849, Passaic Street', '6,8,10,28,33,49,53,66,67,88', '10,54,63,72,93', '4,44,48,94'),
('19', 'Riverrock Farms', '2422, Friendship Lane', '2,5,40,56,77,82,90,91', '57,67,78,97', '18,63,72'),
('2', 'Jelly Farms', '1260, East Avenue', '10,20,30,40,50,60,70,80,90', '23,56,76,80', '6,8,80'),
('20', 'Hollow Hill Pastures', '4932, Linda Street', '1,3,15,67,68,73,80', '33,43,53', '9,18,19,30'),
('3', 'Sunshine Woods Fields', '2320, Wilkinson Street', '25,28,30,52,60,75,76,77', '34,56,73,95', '22,33'),
('4', 'White Oak Farms', '4785, Emily Drive', '2,6,17,22,23,40,55,66,73,75,89', '2,9,13,67,82', '34,56,83'),
('5', 'Mayday Farms', '1534, Carriage Court', '9,11,50,60,66,75,77,83,90,95', '6,34,63,77', '92,93,94'),
('6', 'Shady Oaks Farms', '424, Fraggle Drive', '30,44,47,52,57,60,73,74,80', '15,26,72,73,75', '2,4,37'),
('7', 'Day Break Meadow', '3940, Renwick Drive', '50,53,59,91,92,93,94,95,96,97', '21,22,23,24,25', '10,36,47'),
('8', 'Green Canyon Farms', '1823, School House Road', '8,16,24,32,40,48,56,64,72,80,88', '5,6,7,65,90', '5,73,77,85'),
('9', 'MappleLand Farms', '4911, Joy Lane', '9,18,27,36,45,54,63,70', '33,34,68', '17,65,66');

-- --------------------------------------------------------

--
-- Table structure for table `fertilizers`
--

CREATE TABLE `fertilizers` (
  `_id` varchar(255) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `unitType` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fertilizers`
--

INSERT INTO `fertilizers` (`_id`, `name`, `unitType`) VALUES
('1', 'NP', 'pack'),
('10', 'NPK 19 9 19', 'pack'),
('100', 'NPK 4 17 15', 'pack'),
('11', 'NPK 11 11 17 + 2MGO + TE', 'pack'),
('12', 'NPK 22 6 12 + 3S + 1MgO + 2CaO + TE', 'pack'),
('13', 'NPK 25 5 5 +3.95S+ 2.6MgO', 'pack'),
('14', 'NPK 7 14 7', 'pack'),
('15', 'NP 10 30 0', 'pack'),
('16', 'SSP', 'pack'),
('17', 'NPK 12 12 17 + 2MgO', 'pack'),
('18', 'NP 12 37 0 + 3S + 7CaO', 'pack'),
('19', 'NPK 3 2 13', 'pack'),
('2', 'NPK 6 24 20', 'pack'),
('20', 'NPK 15 7 25 ', 'pack'),
('21', 'NPK 23 10 5 +3S + Zn', 'pack'),
('22', 'Borax', 'pack'),
('23', 'NPK 10 10 10 + TE', 'pack'),
('24', 'NPK 35 5 7 + 2MgO', 'pack'),
('25', 'NPK 19 4 19 + 2S + 3MgO + TE', 'pack'),
('26', 'MAP', 'pack'),
('27', 'Calcium Nitrate', 'pack'),
('28', 'NPK 14 13 9 +14S', 'pack'),
('29', 'NP 10 5 0', 'pack'),
('3', 'NPK 20 10 5', 'pack'),
('30', 'NPK 31 8 8 ', 'pack'),
('31', 'NPK 25 5 5 + 5S + 3MgO', 'pack'),
('32', 'NPS 10 20 0 + 5S  + 1.5MgO + 20 CaO', 'pack'),
('33', 'NPK 14 28 18 + TE', 'pack'),
('34', 'NPK 17 12 11 +3MgO + TE', 'pack'),
('35', 'NPK 5.9  18 30 ', 'pack'),
('36', 'NK 6 0 20 + TE', 'pack'),
('37', 'NPK 10 20 40 + TE', 'pack'),
('38', 'NPK 12 30 7 + S+ MgO + CaO + TE', 'pack'),
('39', 'NPK 15 6 12 + 2MgO + TE', 'pack'),
('4', 'NPK 12 24 12', 'pack'),
('40', 'Dolimite', 'pack'),
('41', 'NPK 12 7 24 + 2Mg0 + 7CaO + TE', 'pack'),
('42', 'NP 28 28 0', 'pack'),
('43', 'NPK 10 18 20 + 6S + 0.8MgO', 'pack'),
('44', 'NPK 13 7 32 + 3MgO', 'pack'),
('45', 'NPK 23 5 12 + 2MgO', 'pack'),
('46', 'NPK 5 7.5 5 + 5S+5CaO', 'pack'),
('47', 'NPK 16 21 10 + 4MgO', 'pack'),
('48', 'NPK 23 23 11 + TE', 'pack'),
('49', 'NPK 23 5 5 + TE', 'pack'),
('5', 'MRP', 'pack'),
('50', 'SOP', 'pack'),
('51', 'PK 0 42 28', 'pack'),
('52', 'NPK 27 6 6 + 2S', 'pack'),
('53', 'Kieserite', 'pack'),
('54', 'NPK 15 22 10 + 4MgO', 'pack'),
('55', 'NP 23 23 0', 'pack'),
('56', 'NP 20 20 0', 'pack'),
('57', 'Magnesium Sulphate', 'pack'),
('58', 'NPK 4 25 15', 'pack'),
('59', 'NPK 10 50 20 + 2MgO', 'pack'),
('6', 'Other Potassic Fertilizers', 'pack'),
('60', 'NPK 12 22 22 + 0.6 S', 'pack'),
('61', 'NPK 17 17 14', 'pack'),
('62', 'NPK 20 20 20', 'pack'),
('63', 'NPK 16 18 12', 'pack'),
('64', 'NPK 23 10 5', 'pack'),
('65', 'TSP', 'pack'),
('66', 'NP 19 38 0 + 7S', 'pack'),
('67', 'Calcium Cyanamides', 'pack'),
('68', 'NPK 25 5 5', 'pack'),
('69', 'NPK 16 6 12 + 2MgO + TE', 'pack'),
('7', 'NPK 26 5 5 ', 'pack'),
('70', 'NPK 18 5 18 + 2MgO', 'pack'),
('71', 'NPK 12 10 8 + TE', 'pack'),
('72', 'Urea', 'pack'),
('73', 'NPK 28 14 14 + TE', 'pack'),
('74', 'UAN', 'pack'),
('75', 'NPK 10 52 10 + 1MgO + TE', 'pack'),
('76', 'NPK 10 10 7.5', 'pack'),
('77', 'Ammonium sulphate', 'pack'),
('78', 'NPK  15 9 20 + 9.5 S', 'pack'),
('79', 'NPK (Known Grades)', 'pack'),
('8', 'NPK 14 26 9 + 3S + 0.5Zn + 0.2B', 'pack'),
('80', 'PK Compounds', 'pack'),
('81', 'PK 0 23 15 + 4S +1MgO + 10CaO + Z', 'pack'),
('82', 'NP 25 5 0 + 5S (MOP BASED)', 'pack'),
('83', 'NPK 26 12 12 + 2MgO', 'pack'),
('84', 'NPK 20 6 18 + 2MgO', 'pack'),
('85', 'NPK 15 9 9 + 3MgO', 'pack'),
('86', 'Other Nitrogenous Fertilizers', 'pack'),
('87', 'NPK 25 5 5 + 3MgO (SOP BASED)', 'pack'),
('88', 'NPK 15 10 18', 'pack'),
('89', 'NPK 20 20 8', 'pack'),
('9', 'NPK 10 10 30', 'pack'),
('90', 'NPK 20 10 10', 'pack'),
('91', 'NPK 20 20 20 + 1MgO + TE', 'pack'),
('92', 'NPK 21 7 21 + TE', 'pack'),
('93', 'NK 16 0 24', 'pack'),
('94', 'NPK 6 13 3 + 3.3S + 2MgO + 2.9Mn + 2Zn', 'pack'),
('95', 'NPK 22 21 17 + TE', 'pack'),
('96', 'NPK 17 7 17', 'pack'),
('97', 'NPK 8 21 7', 'pack'),
('98', 'NPK 27 13 13', 'pack'),
('99', 'NPK 13 11 11 + TE', 'pack');

-- --------------------------------------------------------

--
-- Table structure for table `pesticides`
--

CREATE TABLE `pesticides` (
  `_id` varchar(255) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `unitType` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pesticides`
--

INSERT INTO `pesticides` (`_id`, `name`, `unitType`) VALUES
('1', 'Pesticide B', 'volume'),
('10', 'Pesticide C', 'volume'),
('100', 'Chlorophen', 'volume'),
('11', 'Stake', 'volume'),
('12', 'Silvanol', 'volume'),
('13', 'Velsicol 1068', 'volume'),
('14', 'Gold', 'volume'),
('15', 'Bisoflex', 'volume'),
('16', 'Bronco', 'volume'),
('17', 'Lozo', 'volume'),
('18', 'Nudor Extra', 'volume'),
('19', 'Eviplast', 'volume'),
('2', 'HCB', 'volume'),
('20', 'Sydane', 'volume'),
('21', 'Gammex', 'volume'),
('22', 'Phenatox', 'volume'),
('23', 'Corodane', 'volume'),
('24', 'Priltox', 'volume'),
('25', 'Agronexit', 'volume'),
('26', 'DAP', 'volume'),
('27', 'Chlorotox', 'volume'),
('28', 'Lariat', 'volume'),
('29', 'Phenatox', 'volume'),
('3', 'Gamma Hexachlorocyclohexan', 'volume'),
('30', 'DOP', 'volume'),
('31', 'Penta', 'volume'),
('32', 'Lintox', 'volume'),
('33', 'Penwar', 'volume'),
('34', 'Novigam', 'volume'),
('35', 'Synklor', 'volume'),
('36', 'Benzene Hexachloride', 'volume'),
('37', 'Camphoclor', 'volume'),
('38', 'Forchlor', 'volume'),
('39', 'Lasso', 'volume'),
('4', 'Vitavax', 'volume'),
('40', 'HCH', 'volume'),
('41', 'Gamallin', 'volume'),
('42', 'Polychloro camphene', 'volume'),
('43', 'Sinituho', 'volume'),
('44', 'Anticarie', 'volume'),
('45', 'Shroud', 'volume'),
('46', 'Glazd penta and Block penta,', 'volume'),
('47', 'Alazine', 'volume'),
('48', 'Penta Preservative', 'volume'),
('49', 'Dow Pentachlorophenol', 'volume'),
('5', 'Attac', 'volume'),
('50', 'BEHP', 'volume'),
('51', 'Isotox', 'volume'),
('52', 'Santophen', 'volume'),
('53', 'Penta', 'volume'),
('54', 'Chlorohepton', 'volume'),
('55', 'Perchlorobenzene', 'volume'),
('56', 'Termided,', 'volume'),
('57', 'Dowicide 7', 'volume'),
('58', 'Novigam,', 'volume'),
('59', 'Ortho', 'volume'),
('6', 'Nexit', 'volume'),
('60', 'Lindagam', 'volume'),
('61', 'Alanex', 'volume'),
('62', 'Toxon 63', 'volume'),
('63', 'Topiclor 20', 'volume'),
('64', 'EC 30', 'volume'),
('65', 'Forlin', 'volume'),
('66', 'Agrox 3???Way,', 'volume'),
('67', 'DPP', 'volume'),
('68', 'Latimol', 'volume'),
('69', 'Kypchlo', 'volume'),
('7', 'Niran,', 'volume'),
('70', 'Chlor Kil', 'volume'),
('71', 'Sicol', 'volume'),
('72', 'Phenacide', 'volume'),
('73', 'Plasticizer', 'volume'),
('74', 'Antimicrobial', 'volume'),
('75', 'Octachlor', 'volume'),
('76', 'Pesticide E', 'volume'),
('77', 'DCP', 'volume'),
('78', 'Dowicide EC', 'volume'),
('79', 'Lacco Hi Lin', 'volume'),
('8', 'Chlon,', 'volume'),
('80', 'Aspon Chlordate', 'volume'),
('81', 'DMP', 'volume'),
('82', 'Termide', 'volume'),
('83', 'Pillarzo', 'volume'),
('84', 'Belt', 'volume'),
('85', 'Octoil', 'volume'),
('86', 'Alatox', 'volume'),
('87', 'Marksman', 'volume'),
('88', 'Motox', 'volume'),
('89', 'DEP', 'volume'),
('9', 'DEHP', 'volume'),
('90', 'Penta Plus 40', 'volume'),
('91', 'BHC', 'volume'),
('92', 'Gamaphex', 'volume'),
('93', 'Perchlorobenzene', 'volume'),
('94', 'Lindafor', 'volume'),
('95', 'Pentacon', 'volume'),
('96', 'Freedom', 'volume'),
('97', 'Landafor', 'volume'),
('98', 'Gamatin', 'volume'),
('99', 'Lacco Lin', 'volume');

-- --------------------------------------------------------

--
-- Table structure for table `plants`
--

CREATE TABLE `plants` (
  `_id` varchar(255) NOT NULL COMMENT 'The unique identifier for each row',
  `name` varchar(100) DEFAULT NULL COMMENT 'The name of the plant/',
  `unitType` varchar(20) DEFAULT NULL COMMENT 'The type of unit used to indicate the amount of the \r\nplant'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `plants`
--

INSERT INTO `plants` (`_id`, `name`, `unitType`) VALUES
('1', 'Amaranth', 'mass'),
('10', 'Cabbage', 'mass'),
('100', 'Yarrow', 'mass'),
('11', 'Camas', 'mass'),
('12', 'Canna', 'mass'),
('13', 'Carrot', 'mass'),
('14', 'Cassava', 'mass'),
('15', 'Catsear', 'mass'),
('16', 'Celery', 'mass'),
('17', 'Celtuce', 'mass'),
('18', 'Chaya', 'mass'),
('19', 'Chickweed', 'mass'),
('2', 'Arugula', 'mass'),
('20', 'Chicory', 'mass'),
('21', 'Chinese artichoke', 'mass'),
('22', 'Chinese mallow', 'mass'),
('23', 'Collard greens', 'mass'),
('24', 'Common purslane', 'mass'),
('25', 'Corn salad', 'mass'),
('26', 'Cress', 'mass'),
('27', 'Daikon', 'mass'),
('28', 'Dandelion', 'mass'),
('29', 'Dill', 'mass'),
('3', 'Beet', 'mass'),
('30', 'Earthnut pea', 'mass'),
('31', 'Elephant foot yam', 'mass'),
('32', 'Endive', 'mass'),
('33', 'Ensete', 'mass'),
('34', 'Fat hen', 'mass'),
('35', 'Fiddlehead', 'mass'),
('36', 'Fluted pumpkin', 'mass'),
('37', 'Galangal', 'mass'),
('38', 'Garland Chrysanthemum', 'mass'),
('39', 'Ginger', 'mass'),
('4', 'Bok choy', 'mass'),
('40', 'Golden samphire', 'mass'),
('41', 'Good King Henry', 'mass'),
('42', 'Grapes', 'mass'),
('43', 'Greater plantain', 'mass'),
('44', 'Hamburg parsley', 'mass'),
('45', 'Horseradish', 'mass'),
('46', 'Jerusalem artichoke', 'mass'),
('47', 'Jícama', 'mass'),
('48', 'Kai-lan', 'mass'),
('49', 'Kale', 'mass'),
('5', 'Borage greens', 'mass'),
('50', 'Komatsuna', 'mass'),
('51', 'Kuka', 'mass'),
('52', 'Lagos bologi', 'mass'),
('53', 'Lamb’s lettuce', 'mass'),
('54', 'Lamb’s quarters', 'mass'),
('55', 'Land cress', 'mass'),
('56', 'Lettuce', 'mass'),
('57', 'Lizard’s tail', 'mass'),
('58', 'Malabar spinach', 'mass'),
('59', 'Mallow', 'mass'),
('6', 'Broadleaf arrowhead', 'mass'),
('60', 'Mashua', 'mass'),
('61', 'Melokhia', 'mass'),
('62', 'Mizuna greens', 'mass'),
('63', 'Mustard', 'mass'),
('64', 'Napa cabbage', 'mass'),
('65', 'New Zealand Spinach', 'mass'),
('66', 'Orache', 'mass'),
('67', 'Pak choy', 'mass'),
('68', 'Paracress', 'mass'),
('69', 'Parsnip', 'mass'),
('7', 'Broccoli ', 'mass'),
('70', 'Pea', 'mass'),
('71', 'Pignut', 'mass'),
('72', 'Poke', 'mass'),
('73', 'Potato', 'mass'),
('74', 'Radicchio', 'mass'),
('75', 'Radish', 'mass'),
('76', 'Rapini', 'mass'),
('77', 'Samphire', 'mass'),
('78', 'Sculpit', 'mass'),
('79', 'Sea beet', 'mass'),
('8', 'Brooklime', 'mass'),
('80', 'Sea kale', 'mass'),
('81', 'Shepherd’s purse', 'mass'),
('82', 'Sierra Leone bologi', 'mass'),
('83', 'Soko', 'mass'),
('84', 'Sorrel', 'mass'),
('85', 'Sour cabbage', 'mass'),
('86', 'Spinach', 'mass'),
('87', 'Summer purslane', 'mass'),
('88', 'Swiss chard', 'mass'),
('89', 'Tatsoi', 'mass'),
('9', 'Brussels sprouts', 'mass'),
('90', 'Turmeric', 'mass'),
('91', 'Turnip', 'mass'),
('92', 'Turnip greens', 'mass'),
('93', 'Ulluco', 'mass'),
('94', 'Wasabi', 'mass'),
('95', 'Water caltrop', 'mass'),
('96', 'Water chestnut', 'mass'),
('97', 'Water spinach', 'mass'),
('98', 'Watercress', 'mass'),
('99', 'Wheatgrass', 'mass');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `_id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `farms` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`_id`, `name`, `email`, `password`, `phoneNumber`, `farms`) VALUES
('1', 'Kiera Dietrich', 'kieradietrich1@gmail.com', 'qvmaCrBB', '018-8751682', '[5, 7, 9, 10]'),
('2', 'Gladys Hansen', 'gladyshansen2@gmail.com', 'ptZ8biEj', '018-9762489', '[2, 5]');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activities`
--
ALTER TABLE `activities`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `fertilizers`
--
ALTER TABLE `fertilizers`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `pesticides`
--
ALTER TABLE `pesticides`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `plants`
--
ALTER TABLE `plants`
  ADD PRIMARY KEY (`_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
