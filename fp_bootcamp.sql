-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 07, 2021 at 04:22 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fp_bootcamp`
--

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `game_id` int(10) NOT NULL,
  `nama_game` varchar(255) NOT NULL,
  `pembuat_game` varchar(255) NOT NULL,
  `genre_game` varchar(255) NOT NULL,
  `harga_game` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`game_id`, `nama_game`, `pembuat_game`, `genre_game`, `harga_game`) VALUES
(1, 'Dota 2', 'Valve', 'Action', 1000),
(2, 'Battlefield V', 'Dice', 'Action', 84900),
(3, 'Death Stranding', 'Kojima Production', 'Adventure', 331600),
(4, 'Grand Theft Auto V', 'Rockstar North', 'Adventure', 300750),
(5, 'Phasmophobia', 'Kinetic Game', 'Indie', 90000);

-- --------------------------------------------------------

--
-- Table structure for table `struk`
--

CREATE TABLE `struk` (
  `struk_id` int(10) NOT NULL,
  `game_id` int(10) NOT NULL,
  `nama_game` varchar(255) NOT NULL,
  `pembuat_game` varchar(255) NOT NULL,
  `genre_game` varchar(255) NOT NULL,
  `harga_game` int(10) NOT NULL,
  `kuantitas_game` int(10) NOT NULL,
  `uang_pembayaran` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `struk`
--

INSERT INTO `struk` (`struk_id`, `game_id`, `nama_game`, `pembuat_game`, `genre_game`, `harga_game`, `kuantitas_game`, `uang_pembayaran`) VALUES
(4, 4, 'Grand Theft Auto V', 'Rockstar North', 'Adventure', 300750, 1, 400000),
(5, 1, 'Dota 2', 'Valve', 'Action', 1000, 100, 100000),
(6, 3, 'Death Stranding', 'Kojima Production', 'Adventure', 331600, 1, 331600),
(7, 12, 'Mobile Legend', 'Moonton', 'Moba', 12000, 1, 15000),
(8, 5, 'Phasmophobia', 'Kinetic Game', 'Indie', 90000, 2, 200000),
(9, 13, 'PUBG', 'Tencent', 'Battle Royale', 100, 21, 12000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`game_id`);

--
-- Indexes for table `struk`
--
ALTER TABLE `struk`
  ADD PRIMARY KEY (`struk_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `game_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `struk`
--
ALTER TABLE `struk`
  MODIFY `struk_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
