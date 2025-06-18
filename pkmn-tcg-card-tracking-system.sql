-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 17, 2025 at 03:07 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pkmn-tcg-card-tracking-system`
--
DROP DATABASE IF EXISTS `pkmn-tcg-card-tracking-system`;
CREATE DATABASE IF NOT EXISTS `pkmn-tcg-card-tracking-system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `pkmn-tcg-card-tracking-system`;
-- --------------------------------------------------------

--
-- Table structure for table `cards`
--
DROP TABLE IF EXISTS `cards`;
CREATE TABLE IF NOT EXISTS `cards` (
  `CardID` varchar(11) NOT NULL,
  `CardName` varchar(30) NOT NULL,
  `CardFilePath` varchar(255) DEFAULT NULL,
  `CardDescription` varchar(100) NOT NULL,
  `CardType` varchar(20) DEFAULT NULL,
  `Type` varchar(20) DEFAULT NULL,
  `Stage` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cards`
--

INSERT INTO `cards` (`CardID`, `CardName`, `CardFilePath`, `CardDescription`, `CardType`, `Type`, `Stage`) VALUES
('BS001', 'Alakazam', 'images/BS001', '[Base Set 1/102] Alakazam', 'Pokemon', 'Psychic', 'Stage 2'),
('BS002', 'Blastoise', 'images/BS002', '[Base Set 2/102] Blastoise', 'Pokemon', 'Water', 'Stage 2'),
('BS003', 'Chansey', 'images/BS003', '[Base Set 3/102] Chansey', 'Pokemon', 'Colorless', 'Basic'),
('BS004', 'Charizard', 'images/BS004', '[Base Set 4/102] Charizard', 'Pokemon', 'Fire', 'Stage 2'),
('BS005', 'Clefairy', 'images/BS005', '[Base Set 5/102] Clefairy', 'Pokemon', 'Colorless', 'Basic'),
('BS006', 'Gyarados', 'images/BS006', '[Base Set 6/102] Gyarados', 'Pokemon', 'Water', 'Stage 1'),
('BS007', 'Hitmonchan', 'images/BS007', '[Base Set 7/102] Hitmonchan', 'Pokemon', 'Fighting', 'Basic'),
('BS008', 'Machamp', 'images/BS008', '[Base Set 8/102] Machamp', 'Pokemon', 'Fighting', 'Stage 2'),
('BS009', 'Magneton', 'images/BS009', '[Base Set 9/102] Magneton', 'Pokemon', 'Lightning', 'Stage 1'),
('BS010', 'Mewtwo', 'images/BS010', '[Base Set 10/102] Mewtwo', 'Pokemon', 'Psychic', 'Basic'),
('BS011', 'Nidoking', 'images/BS011', '[Base Set 11/102] Nidoking', 'Pokemon', 'Grass', 'Stage 2'),
('BS012', 'Ninetales', 'images/BS012', '[Base Set 12/102] Ninetales', 'Pokemon', 'Fire', 'Stage 1'),
('BS013', 'Poliwrath', 'images/BS013', '[Base Set 13/102] Poliwrath', 'Pokemon', 'Water', 'Stage 2'),
('BS014', 'Raichu', 'images/BS014', '[Base Set 14/102] Raichu', 'Pokemon', 'Lightning', 'Stage 1'),
('BS015', 'Venusaur', 'images/BS015', '[Base Set 15/102] Venusaur', 'Pokemon', 'Grass', 'Stage 2'),
('BS016', 'Zapdos', 'images/BS016', '[Base Set 16/102] Zapdos', 'Pokemon', 'Lightning', 'Basic'),
('BS017', 'Beedrill', 'images/BS017', '[Base Set 17/102] Beedrill', 'Pokemon', 'Grass', 'Stage 2'),
('BS018', 'Dragonair', 'images/BS018', '[Base Set 18/102] Dragonair', 'Pokemon', 'Colorless', 'Stage 1'),
('BS019', 'Dugtrio', 'images/BS019', '[Base Set 19/102] Dugtrio', 'Pokemon', 'Fighting', 'Stage 1'),
('BS020', 'Electabuzz', 'images/BS020', '[Base Set 20/102] Electabuzz', 'Pokemon', 'Lightning', 'Basic'),
('BS021', 'Electrode', 'images/BS021', '[Base Set 21/102] Electrode', 'Pokemon', 'Lightning', 'Stage 1'),
('BS022', 'Pidgeotto', 'images/BS022', '[Base Set 22/102] Pidgeotto', 'Pokemon', 'Colorless', 'Stage 1'),
('BS023', 'Arcanine', 'images/BS023', '[Base Set 23/102] Arcanine', 'Pokemon', 'Fire', 'Stage 1'),
('BS024', 'Charmeleon', 'images/BS024', '[Base Set 24/102] Charmeleon', 'Pokemon', 'Fire', 'Stage 1'),
('BS025', 'Dewgong', 'images/BS025', '[Base Set 25/102] Dewgong', 'Pokemon', 'Water', 'Stage 1'),
('BS026', 'Dratini', 'images/BS026', '[Base Set 26/102] Dratini', 'Pokemon', 'Colorless', 'Basic'),
('BS027', 'Farfetchd', 'images/BS027', '[Base Set 27/102] Farfetch\'d', 'Pokemon', 'Colorless', 'Basic'),
('BS028', 'Growlithe', 'images/BS028', '[Base Set 28/102] Growlithe', 'Pokemon', 'Fire', 'Basic'),
('BS029', 'Haunter', 'images/BS029', '[Base Set 29/102] Haunter', 'Pokemon', 'Psychic', 'Stage 1'),
('BS030', 'Ivysaur', 'images/BS030', '[Base Set 30/102] Ivysaur', 'Pokemon', 'Grass', 'Stage 1'),
('BS031', 'Jynx', 'images/BS031', '[Base Set 31/102] Jynx', 'Pokemon', 'Psychic', 'Stage 1'),
('BS032', 'Kadabra', 'images/BS032', '[Base Set 32/102] Kadabra', 'Pokemon', 'Psychic', 'Stage 1'),
('BS033', 'Kakuna', 'images/BS033', '[Base Set 33/102] Kakuna', 'Pokemon', 'Grass', 'Stage 1'),
('BS034', 'Machoke', 'images/BS034', '[Base Set 34/102] Machoke', 'Pokemon', 'Fighting', 'Stage 1'),
('BS035', 'Magikarp', 'images/BS035', '[Base Set 35/102] Magikarp', 'Pokemon', 'Water', 'Basic'),
('BS036', 'Magmar', 'images/BS036', '[Base Set 36/102] Magmar', 'Pokemon', 'Fire', 'Basic'),
('BS037', 'Nidorino', 'images/BS037', '[Base Set 37/102] Nidorino', 'Pokemon', 'Grass', 'Stage 1'),
('BS038', 'Poliwhirl', 'images/BS038', '[Base Set 38/102] Poliwhirl', 'Pokemon', 'Water', 'Stage 1'),
('BS039', 'Porygon', 'images/BS039', '[Base Set 39/102] Porygon', 'Pokemon', 'Colorless', 'Basic'),
('BS040', 'Raticate', 'images/BS040', '[Base Set 40/102] Raticate', 'Pokemon', 'Colorless', 'Stage 1'),
('BS041', 'Seel', 'images/BS041', '[Base Set 41/102] Seel', 'Pokemon', 'Water', 'Basic'),
('BS042', 'Wartortle', 'images/BS042', '[Base Set 42/102] Wartortle', 'Pokemon', 'Water', 'Stage 1'),
('BS043', 'Abra', 'images/BS043', '[Base Set 43/102] Abra', 'Pokemon', 'Psychic', 'Basic'),
('BS044', 'Bulbasaur', 'images/BS044', '[Base Set 44/102] Bulbasaur', 'Pokemon', 'Grass', 'Basic'),
('BS045', 'Caterpie', 'images/BS045', '[Base Set 45/102] Caterpie', 'Pokemon', 'Grass', 'Basic'),
('BS046', 'Charmander', 'images/BS046', '[Base Set 46/102] Charmander', 'Pokemon', 'Fire', 'Basic'),
('BS047', 'Diglett', 'images/BS047', '[Base Set 47/102] Diglett', 'Pokemon', 'Fighting', 'Basic'),
('BS048', 'Doduo', 'images/BS048', '[Base Set 48/102] Doduo', 'Pokemon', 'Colorless', 'Basic'),
('BS049', 'Drowzee', 'images/BS049', '[Base Set 49/102] Drowzee', 'Pokemon', 'Psychic', 'Basic'),
('BS050', 'Gastly', 'images/BS050', '[Base Set 50/102] Gastly', 'Pokemon', 'Psychic', 'Basic'),
('BS051', 'Koffing', 'images/BS051', '[Base Set 51/102] Koffing', 'Pokemon', 'Grass', 'Basic'),
('BS052', 'Machop', 'images/BS052', '[Base Set 52/102] Machop', 'Pokemon', 'Fighting', 'Basic'),
('BS053', 'Magnemite', 'images/BS053', '[Base Set 53/102] Magnemite', 'Pokemon', 'Lightning', 'Basic'),
('BS054', 'Metapod', 'images/BS054', '[Base Set 54/102] Metapod', 'Pokemon', 'Grass', 'Stage 1'),
('BS055', 'Nidoran M', 'images/BS055', '[Base Set 55/102] Nidoran M', 'Pokemon', 'Grass', 'Basic'),
('BS056', 'Onix', 'images/BS056', '[Base Set 56/102] Onix', 'Pokemon', 'Fighting', 'Basic'),
('BS057', 'Pidgey', 'images/BS057', '[Base Set 57/102] Pidgey', 'Pokemon', 'Colorless', 'Basic'),
('BS058', 'Pikachu', 'images/BS058', '[Base Set 58/102] Pikachu', 'Pokemon', 'Lightning', 'Basic'),
('BS059', 'Poliwag', 'images/BS059', '[Base Set 59/102] Poliwag', 'Pokemon', 'Water', 'Basic'),
('BS060', 'Ponyta', 'images/BS060', '[Base Set 60/102] Ponyta', 'Pokemon', 'Fire', 'Basic'),
('BS061', 'Rattata', 'images/BS061', '[Base Set 61/102] Rattata', 'Pokemon', 'Colorless', 'Basic'),
('BS062', 'Sandshrew', 'images/BS062', '[Base Set 62/102] Sandshrew', 'Pokemon', 'Fighting', 'Basic'),
('BS063', 'Squirtle', 'images/BS063', '[Base Set 63/102] Squirtle', 'Pokemon', 'Water', 'Basic'),
('BS064', 'Starmie', 'images/BS064', '[Base Set 64/102] Starmie', 'Pokemon', 'Water', 'Basic'),
('BS065', 'Staryu', 'images/BS065', '[Base Set 65/102] Staryu', 'Pokemon', 'Water', 'Basic'),
('BS066', 'Tangela', 'images/BS066', '[Base Set 66/102] Tangela', 'Pokemon', 'Grass', 'Basic'),
('BS067', 'Voltorb', 'images/BS067', '[Base Set 67/102] Voltorb', 'Pokemon', 'Lightning', 'Basic'),
('BS068', 'Vulpix', 'images/BS068', '[Base Set 68/102] Vulpix', 'Pokemon', 'Fire', 'Basic'),
('BS069', 'Weedle', 'images/BS069', '[Base Set 69/102] Weedle', 'Pokemon', 'Grass', 'Basic'),
('BS070', 'Clefairy Doll', 'images/BS070', '[Base Set 70/102] Clefairy Doll', 'Trainer', NULL, NULL),
('BS071', 'Computer Search', 'images/BS071', '[Base Set 71/102] Computer Search', 'Trainer', NULL, NULL),
('BS072', 'Devolution Spray', 'images/BS072', '[Base Set 72/102] Devolution Spray', 'Trainer', NULL, NULL),
('BS073', 'Imposter Professor Oak', 'images/BS073', '[Base Set 73/102] Imposter Professor Oak', 'Trainer', NULL, NULL),
('BS074', 'ItemFinder', 'images/BS074', '[Base Set 74/102] Item Finder', 'Trainer', NULL, NULL),
('BS075', 'Lass', 'images/BS075', '[Base Set 75/102] Lass', 'Trainer', NULL, NULL),
('BS076', 'Pokemon Breeder', 'images/BS076', '[Base Set 76/102] Pokemon Breeder', 'Trainer', NULL, NULL),
('BS077', 'Pokemon Trader', 'images/BS077', '[Base Set 77/102] Pokemon Trader', 'Trainer', NULL, NULL),
('BS078', 'ScoopUp', 'images/BS078', '[Base Set 78/102] Scoop Up', 'Trainer', NULL, NULL),
('BS079', 'Super Energy Removal', 'images/BS079', '[Base Set 79/102] Super Energy Removal', 'Trainer', NULL, NULL),
('BS080', 'Defender', 'images/BS080', '[Base Set 80/102] Defender', 'Trainer', NULL, NULL),
('BS081', 'Energy Retrieval', 'images/BS081', '[Base Set 81/102] Energy Retrieval', 'Trainer', NULL, NULL),
('BS082', 'Full Heal', 'images/BS082', '[Base Set 82/102] Full Heal', 'Trainer', NULL, NULL),
('BS083', 'Maintenance', 'images/BS083', '[Base Set 83/102] Maintenance', 'Trainer', NULL, NULL),
('BS084', 'PlusPower', 'images/BS084', '[Base Set 84/102] PlusPower', 'Trainer', NULL, NULL),
('BS085', 'Pokemon Center', 'images/BS085', '[Base Set 85/102] Pokemon Center', 'Trainer', NULL, NULL),
('BS086', 'Pokemon Flute', 'images/BS086', '[Base Set 86/102] Pokemon Flute', 'Trainer', NULL, NULL),
('BS087', 'Pokedex', 'images/BS087', '[Base Set 87/102] Pokedex', 'Trainer', NULL, NULL),
('BS088', 'Professor Oak', 'images/BS088', '[Base Set 88/102] Professor Oak', 'Trainer', NULL, NULL),
('BS089', 'Revive', 'images/BS089', '[Base Set 89/102] Revive', 'Trainer', NULL, NULL),
('BS090', 'Super Potion', 'images/BS090', '[Base Set 90/102] Super Potion', 'Trainer', NULL, NULL),
('BS091', 'Bill', 'images/BS091', '[Base Set 91/102] Bill', 'Trainer', NULL, NULL),
('BS092', 'Energy Removal', 'images/BS092', '[Base Set 92/102] Energy Removal', 'Trainer', NULL, NULL),
('BS093', 'Gust Of Wind', 'images/BS093', '[Base Set 93/102] Gust of Wind', 'Trainer', NULL, NULL),
('BS094', 'Potion', 'images/BS094', '[Base Set 94/102] Potion', 'Trainer', NULL, NULL),
('BS095', 'Switch', 'images/BS095', '[Base Set 95/102] Switch', 'Trainer', NULL, NULL),
('BS096', 'Double Colorless Energy', 'images/BS096', '[Base Set 96/102] Double Colorless Energy', 'Energy', 'Colorless', NULL),
('BS097', 'Fighting Energy', 'images/BS097', '[Base Set 97/102] Fighting Energy', 'Energy', 'Fighting', NULL),
('BS098', 'Fire Energy', 'images/BS098', '[Base Set 98/102] Fire Energy', 'Energy', 'Fire', NULL),
('BS099', 'Grass Energy', 'images/BS099', '[Base Set 99/102] Grass Energy', 'Energy', 'Grass', NULL),
('BS100', 'Lightning Energy', 'images/BS100', '[Base Set 100/102] Lightning Energy', 'Energy', 'Lightning', NULL),
('BS101', 'Psychic Energy', 'images/BS101', '[Base Set 101/102] Psychic Energy', 'Energy', 'Psychic', NULL),
('BS102', 'Water Energy', 'images/BS102', '[Base Set 102/102] Water Energy', 'Energy', 'Water', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
CREATE TABLE IF NOT EXISTS `players` (
  `PlayerID` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `RegistrationDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `players_cards`
--
DROP TABLE IF EXISTS `players_cards`;
CREATE TABLE IF NOT EXISTS `players_cards` (
  `PlayerID` int(11) NOT NULL,
  `CardID` varchar(11) NOT NULL,
  `CardQuantity` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cards`
--
ALTER TABLE `cards`
  ADD PRIMARY KEY (`CardID`);

--
-- Indexes for table `players`
--
ALTER TABLE `players`
  ADD PRIMARY KEY (`PlayerID`);

--
-- Indexes for table `players_cards`
--
ALTER TABLE `players_cards`
  ADD PRIMARY KEY (`PlayerID`,`CardID`),
  ADD KEY `CardID` (`CardID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `players`
--
ALTER TABLE `players`
  MODIFY `PlayerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `players_cards`
--
ALTER TABLE `players_cards`
  ADD CONSTRAINT `players_cards_ibfk_1` FOREIGN KEY (`PlayerID`) REFERENCES `players` (`PlayerID`),
  ADD CONSTRAINT `players_cards_ibfk_2` FOREIGN KEY (`CardID`) REFERENCES `cards` (`CardID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
