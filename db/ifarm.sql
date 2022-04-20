-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 20, 2022 at 02:14 AM
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
  `unitTyoe` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pesticides`
--

INSERT INTO `pesticides` (`_id`, `name`, `unitTyoe`) VALUES
('1', 'Pesticide B', 'mass'),
('10', 'Pesticide C', 'mass'),
('100', 'Chlorophen', 'mass'),
('11', 'Stake', 'mass'),
('12', 'Silvanol', 'mass'),
('13', 'Velsicol 1068', 'mass'),
('14', 'Gold', 'mass'),
('15', 'Bisoflex', 'mass'),
('16', 'Bronco', 'mass'),
('17', 'Lozo', 'mass'),
('18', 'Nudor Extra', 'mass'),
('19', 'Eviplast', 'mass'),
('2', 'HCB', 'mass'),
('20', 'Sydane', 'mass'),
('21', 'Gammex', 'mass'),
('22', 'Phenatox', 'mass'),
('23', 'Corodane', 'mass'),
('24', 'Priltox', 'mass'),
('25', 'Agronexit', 'mass'),
('26', 'DAP', 'mass'),
('27', 'Chlorotox', 'mass'),
('28', 'Lariat', 'mass'),
('29', 'Phenatox', 'mass'),
('3', 'Gamma Hexachlorocyclohexan', 'mass'),
('30', 'DOP', 'mass'),
('31', 'Penta', 'mass'),
('32', 'Lintox', 'mass'),
('33', 'Penwar', 'mass'),
('34', 'Novigam', 'mass'),
('35', 'Synklor', 'mass'),
('36', 'Benzene Hexachloride', 'mass'),
('37', 'Camphoclor', 'mass'),
('38', 'Forchlor', 'mass'),
('39', 'Lasso', 'mass'),
('4', 'Vitavax', 'mass'),
('40', 'HCH', 'mass'),
('41', 'Gamallin', 'mass'),
('42', 'Polychloro camphene', 'mass'),
('43', 'Sinituho', 'mass'),
('44', 'Anticarie', 'mass'),
('45', 'Shroud', 'mass'),
('46', 'Glazd penta and Block penta,', 'mass'),
('47', 'Alazine', 'mass'),
('48', 'Penta Preservative', 'mass'),
('49', 'Dow Pentachlorophenol', 'mass'),
('5', 'Attac', 'mass'),
('50', 'BEHP', 'mass'),
('51', 'Isotox', 'mass'),
('52', 'Santophen', 'mass'),
('53', 'Penta', 'mass'),
('54', 'Chlorohepton', 'mass'),
('55', 'Perchlorobenzene', 'mass'),
('56', 'Termided,', 'mass'),
('57', 'Dowicide 7', 'mass'),
('58', 'Novigam,', 'mass'),
('59', 'Ortho', 'mass'),
('6', 'Nexit', 'mass'),
('60', 'Lindagam', 'mass'),
('61', 'Alanex', 'mass'),
('62', 'Toxon 63', 'mass'),
('63', 'Topiclor 20', 'mass'),
('64', 'EC 30', 'mass'),
('65', 'Forlin', 'mass'),
('66', 'Agrox 3???Way,', 'mass'),
('67', 'DPP', 'mass'),
('68', 'Latimol', 'mass'),
('69', 'Kypchlo', 'mass'),
('7', 'Niran,', 'mass'),
('70', 'Chlor Kil', 'mass'),
('71', 'Sicol', 'mass'),
('72', 'Phenacide', 'mass'),
('73', 'Plasticizer', 'mass'),
('74', 'Antimicrobial', 'mass'),
('75', 'Octachlor', 'mass'),
('76', 'Pesticide E', 'mass'),
('77', 'DCP', 'mass'),
('78', 'Dowicide EC', 'mass'),
('79', 'Lacco Hi Lin', 'mass'),
('8', 'Chlon,', 'mass'),
('80', 'Aspon Chlordate', 'mass'),
('81', 'DMP', 'mass'),
('82', 'Termide', 'mass'),
('83', 'Pillarzo', 'mass'),
('84', 'Belt', 'mass'),
('85', 'Octoil', 'mass'),
('86', 'Alatox', 'mass'),
('87', 'Marksman', 'mass'),
('88', 'Motox', 'mass'),
('89', 'DEP', 'mass'),
('9', 'DEHP', 'mass'),
('90', 'Penta Plus 40', 'mass'),
('91', 'BHC', 'mass'),
('92', 'Gamaphex', 'mass'),
('93', 'Perchlorobenzene', 'mass'),
('94', 'Lindafor', 'mass'),
('95', 'Pentacon', 'mass'),
('96', 'Freedom', 'mass'),
('97', 'Landafor', 'mass'),
('98', 'Gamatin', 'mass'),
('99', 'Lacco Lin', 'mass');

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
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `farms` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`_id`, `name`, `email`, `password`, `phoneNumber`, `farms`) VALUES
('1', 'Juana Jenkins IV', 'juanajenkinsiv1@gmail.com', 'vIAe1eQr', '014-2348692', '4,5,7,10'),
('10', 'Taylor Kertzmann', 'taylorkertzmann10@gmail.com', 'qNNtNQOk', '017-3132200', '5,6,7,9,10'),
('100', 'Brannon O\'Conner V', 'brannono\'connerv100@gmail.com', 'UDTpUPAV', '016-4896248', '1,3,4'),
('11', 'Dr. Lilyan D\'Amore II', 'dr.lilyand\'amoreii11@gmail.com', '8KVOaQUY', '014-6386979', '2,3,7,9'),
('12', 'Miss Sophia Grant II', 'misssophiagrantii12@gmail.com', 'hDkPGBOq', '013-1125108', '2,4,5,10'),
('13', 'Veronica Konopelski', 'veronicakonopelski13@gmail.com', 'oDCH5iD3', '017-8114019', '3,10'),
('14', 'June Paucek', 'junepaucek14@gmail.com', '8Hou8pMt', '015-1170178', '9'),
('15', 'Trevor Thompson', 'trevorthompson15@gmail.com', 'CXOOHXWE', '018-1620598', '2,4,5,10'),
('16', 'Guy Carroll', 'guycarroll16@gmail.com', '2ab33BCs', '010-3278885', '2,8'),
('17', 'Beau Turner', 'beauturner17@gmail.com', 'tMnmOFg8', '013-9154259', '9'),
('18', 'Lawrence Goyette IV', 'lawrencegoyetteiv18@gmail.com', '00TbHeUy', '017-4779852', '3,4,7,8'),
('19', 'Vella Feeney II', 'vellafeeneyii19@gmail.com', '1EXyPluU', '017-9310982', '9'),
('2', 'Augustine Kessler', 'augustinekessler2@gmail.com', 'b0rmJTzb', '013-5797555', '1,2,4'),
('20', 'Kory Robel', 'koryrobel20@gmail.com', 'kvQBcBxe', '018-8262712', '3,10'),
('21', 'Lavina Osinski', 'lavinaosinski21@gmail.com', 'Z39htOf9', '012-7114649', '2,7,8,9'),
('22', 'Griffin Pollich', 'griffinpollich22@gmail.com', '8T917cGL', '015-3623002', '5,7,8,9'),
('23', 'Dr. Linnea Hickle MD', 'dr.linneahicklemd23@gmail.com', 'xU0apEDK', '014-7568875', '2,9'),
('24', 'Prof. Roxanne Emmerich', 'prof.roxanneemmerich24@gmail.com', '0SiXdYGT', '013-7294257', '5,8,9,10'),
('25', 'Mr. Urban Ratke I', 'mr.urbanratkei25@gmail.com', 'Aftt5iFz', '019-7923843', '4,8,9,10'),
('26', 'Adela Jacobi MD', 'adelajacobimd26@gmail.com', 'lxOtAjgG', '011-8862264', '1,3,7,10'),
('27', 'Evelyn Crist III', 'evelyncristiii27@gmail.com', '8tz9IzYc', '010-8681028', '3,4,5,8'),
('28', 'Edwin Rippin', 'edwinrippin28@gmail.com', '2t4cjuG4', '011-1744628', '2,8'),
('29', 'Richmond Blanda PhD', 'richmondblandaphd29@gmail.com', 'rEPbKc7u', '015-9278293', '2,3,8,9'),
('3', 'Prudence Tromp', 'prudencetromp3@gmail.com', 'fCe7fHJC', '010-2104940', '3,4,6,7,9'),
('30', 'Aliyah Lesch V', 'aliyahleschv30@gmail.com', 'z0TvO9Hc', '018-3810374', '5,8,9'),
('31', 'Chet Runolfsdottir IV', 'chetrunolfsdottiriv31@gmail.com', 'y5DG35pJ', '014-5106411', '1,5,7'),
('32', 'Tod Roob', 'todroob32@gmail.com', 'MaNn6Q5S', '013-3814423', '1,4,6,8,9'),
('33', 'Russel Wuckert', 'russelwuckert33@gmail.com', 'GXzm0RjS', '013-5763746', '1,2,4,6,8'),
('34', 'Christiana Koepp', 'christianakoepp34@gmail.com', 'mdeXbAIg', '019-2453179', '2,4,5,10'),
('35', 'Emmy Friesen', 'emmyfriesen35@gmail.com', 'IR5PN1Da', '013-1577642', '1,5'),
('36', 'Jamel Klein PhD', 'jamelkleinphd36@gmail.com', 'oaAE3jUH', '010-3021287', '2,3,6,8'),
('37', 'Hans Greenfelder', 'hansgreenfelder37@gmail.com', '66DUaqTf', '017-3891318', '1,4,6,7'),
('38', 'Kenton Stiedemann', 'kentonstiedemann38@gmail.com', 'revadIqj', '011-4783764', '3,8,9'),
('39', 'Ellie Hessel', 'elliehessel39@gmail.com', 'vDfhsINg', '013-4783153', '6,7,9,10'),
('4', 'Milo Kris', 'milokris4@gmail.com', 'Dh5c95C3', '017-4521707', '1,5,6,8,10'),
('40', 'Dan O\'Reilly', 'dano\'reilly40@gmail.com', 'gHuQGWjY', '013-3460580', '1,2,6,7,10'),
('41', 'Prof. Willis Conroy', 'prof.willisconroy41@gmail.com', 'rMjYvI0S', '012-8848857', '4'),
('42', 'Reggie Kshlerin', 'reggiekshlerin42@gmail.com', 'hHB7O8j6', '012-9563491', '2'),
('43', 'Dr. Leora Schiller', 'dr.leoraschiller43@gmail.com', 'BhJgAduY', '018-4556877', '1,4,6,8,10'),
('44', 'Nikita Reichert', 'nikitareichert44@gmail.com', 'hrvj4xtp', '017-7447021', '1,4,9,10'),
('45', 'Mr. Maximus Bailey II', 'mr.maximusbaileyii45@gmail.com', '2W2Fc4u9', '013-9428634', '1,10'),
('46', 'Mrs. Heloise Jacobson', 'mrs.heloisejacobson46@gmail.com', 'JT10z1xB', '011-1356387', '4,7,8,10'),
('47', 'Ruth Becker', 'ruthbecker47@gmail.com', 'O5ZXTWcq', '017-9401758', '1,3,6,8,9'),
('48', 'Alf Lesch', 'alflesch48@gmail.com', 'JUeoT28J', '017-5592294', '4,5,7,10'),
('49', 'Garnett D\'Amore', 'garnettd\'amore49@gmail.com', 'c02d1Psz', '013-4933294', '4,6,8,10'),
('5', 'Evie Pagac MD', 'eviepagacmd5@gmail.com', 'Ymol1aQF', '019-1905354', '2,5,6,8,9'),
('50', 'Miss Marie Frami', 'missmarieframi50@gmail.com', 'SNeHmofL', '010-6887111', '2,4'),
('51', 'Hailey King', 'haileyking51@gmail.com', 'TRrUGTnb', '015-5400390', '1,3,5,6,7'),
('52', 'Dr. Stephany Mayer IV', 'dr.stephanymayeriv52@gmail.com', 'JzCjTC6d', '017-5972502', '2,8,9,10'),
('53', 'Caroline Douglas', 'carolinedouglas53@gmail.com', 'PgTgfDbT', '012-7435888', '8'),
('54', 'Faye Boehm', 'fayeboehm54@gmail.com', '1omDz7yi', '014-1279992', '1,4,6,8,10'),
('55', 'Miss Beverly Schaden', 'missbeverlyschaden55@gmail.com', 'TpmdtbfY', '013-3830300', '5,9'),
('56', 'Renee Mertz', 'reneemertz56@gmail.com', 'E1HLIyac', '018-1017331', '1,2,5,6,8'),
('57', 'Miss Macy Olson', 'missmacyolson57@gmail.com', 'vaUoYVem', '015-7549070', '1,8,10'),
('58', 'Helene Little V', 'helenelittlev58@gmail.com', 'AxZKWD2C', '013-5226553', '2,5,8'),
('59', 'Brittany Olson I', 'brittanyolsoni59@gmail.com', 'Pr6tndo2', '017-1252473', '1,6,8,9'),
('6', 'Prof. Furman Shields II', 'prof.furmanshieldsii6@gmail.com', '76yBu2iv', '018-1900408', '2,8'),
('60', 'Ryan Heller', 'ryanheller60@gmail.com', 'nkHDMGfl', '017-9039377', '2,5'),
('61', 'Kamille Corwin', 'kamillecorwin61@gmail.com', 'dRT7DzIW', '017-9716378', '4,9'),
('62', 'Angelita Skiles', 'angelitaskiles62@gmail.com', 'utijf3EX', '018-3893387', '3,6,7,8,9'),
('63', 'Zion Schmidt', 'zionschmidt63@gmail.com', '8pQ7yIRA', '017-4667458', '2,5,7,8'),
('64', 'Chaim Crona', 'chaimcrona64@gmail.com', '8ZITCgLS', '011-7602856', '2'),
('65', 'Destini Gutmann Jr.', 'destinigutmannjr.65@gmail.com', 't2UGr8mH', '014-3105199', '3,4,6,7,9'),
('66', 'Prof. Brisa Pfeffer PhD', 'prof.brisapfefferphd66@gmail.com', 'PI1x5XBx', '017-8501137', '5,7,9,10'),
('67', 'Llewellyn Crist', 'llewellyncrist67@gmail.com', 'IIplZEKj', '018-6357833', '2,3'),
('68', 'Joey Rippin', 'joeyrippin68@gmail.com', 'tStU7N2e', '017-7908362', '3,9'),
('69', 'Nicole Murphy', 'nicolemurphy69@gmail.com', 'BJrSXHsf', '015-1394721', '2,3,4,10'),
('7', 'Dr. Reymundo Fritsch', 'dr.reymundofritsch7@gmail.com', 'i9XgIK4R', '012-8318360', '1,3,7,8,9'),
('70', 'Janick Bashirian', 'janickbashirian70@gmail.com', 'Zz88giTy', '011-1614403', '2,3,5,7,8'),
('71', 'Dr. Priscilla Lueilwitz V', 'dr.priscillalueilwitzv71@gmail.com', 'PmvbLt61', '014-7445083', '1,3,4,5,7'),
('72', 'Viviane Larson', 'vivianelarson72@gmail.com', 'QPJRmv6e', '016-4181352', '1,2,3,7,10'),
('73', 'Sonia Simonis', 'soniasimonis73@gmail.com', 'I8AfR8Hz', '018-3169480', '5,9,10'),
('74', 'Dorris Shields I', 'dorrisshieldsi74@gmail.com', 'x5DxQLyb', '011-2149469', '3,4,9'),
('75', 'Roselyn Bednar V', 'roselynbednarv75@gmail.com', '1cIipYWF', '010-1822591', '1,3,4,8,9'),
('76', 'Prince Lowe DDS', 'princelowedds76@gmail.com', 'vI6Bhm0M', '016-7089066', '3,9'),
('77', 'Dr. Erwin Keeling PhD', 'dr.erwinkeelingphd77@gmail.com', 'SVzkLiEB', '017-7352241', '1,5,10'),
('78', 'Prof. Carmen Purdy PhD', 'prof.carmenpurdyphd78@gmail.com', 'GVQarR6Q', '011-7339743', '1,2,4,9'),
('79', 'Yazmin Walsh', 'yazminwalsh79@gmail.com', 'kS2QH8oN', '012-1553823', '2,9'),
('8', 'Carmela Jones', 'carmelajones8@gmail.com', '1f72eEnH', '019-1679810', '9'),
('80', 'Daisha Sporer DDS', 'daishasporerdds80@gmail.com', 'HeQag4o9', '012-4646279', '5'),
('81', 'Kailyn Botsford', 'kailynbotsford81@gmail.com', '9E8lrh0E', '010-9532498', '5,7,9'),
('82', 'Norbert Torphy PhD', 'norberttorphyphd82@gmail.com', 'Jk3XEg41', '016-7421385', '1,3,5,7'),
('83', 'Vernie Spinka', 'verniespinka83@gmail.com', 'Q2Xyhm3r', '013-8358065', '2,6,9,10'),
('84', 'Jerrold Kuhn', 'jerroldkuhn84@gmail.com', 't9FlzCp7', '013-4317913', '2'),
('85', 'Chet McDermott DVM', 'chetmcdermottdvm85@gmail.com', '3NekA0xG', '016-3156838', '1,3,5,7'),
('86', 'Ms. Jude Gutmann PhD', 'ms.judegutmannphd86@gmail.com', 'OJlHY30I', '015-4269525', '2,8,10'),
('87', 'Sharon Beier DVM', 'sharonbeierdvm87@gmail.com', '5JrokGP3', '012-2727166', '3,4,5,10'),
('88', 'Vergie Dach', 'vergiedach88@gmail.com', 'jCjQYdLq', '016-6055250', '2,5,7,9'),
('89', 'Ava Effertz', 'avaeffertz89@gmail.com', '27dLFdKK', '014-7348174', '3,4,5,6,7'),
('9', 'Prof. Destini McCullough II', 'prof.destinimcculloughii9@gmail.com', 'nSnQ0bpl', '011-3601589', '6,8'),
('90', 'Mr. Jabari Farrell IV', 'mr.jabarifarrelliv90@gmail.com', 'NqmWS8Y1', '015-9316661', '7,8'),
('91', 'Armando Mante', 'armandomante91@gmail.com', 'XM09MsdJ', '010-6620467', '1,3,4,6,9'),
('92', 'Leslie Greenfelder', 'lesliegreenfelder92@gmail.com', 'gDW2C6T8', '018-7011205', '1,4,5,7,8'),
('93', 'Adolf Bruen', 'adolfbruen93@gmail.com', 'lZz3fbP5', '012-7032571', '1,3,4,5,10'),
('94', 'Filomena Heaney', 'filomenaheaney94@gmail.com', 'j0KR0Y5y', '011-9431330', '5'),
('95', 'Nathanael Beatty DDS', 'nathanaelbeattydds95@gmail.com', 'ByzsV6KK', '010-3027595', '3'),
('96', 'Cali Dach I', 'calidachi96@gmail.com', 'JLY0qayo', '013-1437585', '3,5'),
('97', 'Laurence Emard', 'laurenceemard97@gmail.com', '28HrERer', '013-2721733', '1,3,7,9'),
('98', 'Ms. Aniya Mante', 'ms.aniyamante98@gmail.com', 'CD5Xh5dQ', '015-5702634', '1,3,5,6,7'),
('99', 'Yasmin Champlin', 'yasminchamplin99@gmail.com', '73puI3ay', '013-7889768', '1,2,5,9');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `farms`
--
ALTER TABLE `farms`
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
