-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 17, 2025 at 01:11 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `players_cards`
--

CREATE TABLE `players_cards` (
  `PlayerID` int(11) NOT NULL,
  `CardID` varchar(11) NOT NULL,
  `CardQuantity` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `players_cards`
--
ALTER TABLE `players_cards`
  ADD PRIMARY KEY (`PlayerID`,`CardID`),
  ADD KEY `CardID` (`CardID`);

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
