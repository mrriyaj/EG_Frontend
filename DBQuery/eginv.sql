-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 03:41 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eginv`
--

-- --------------------------------------------------------

--
-- Table structure for table `billing`
--

CREATE TABLE `billing` (
  `billID` int(11) NOT NULL,
  `billing_id` varchar(10) NOT NULL,
  `mc_id` varchar(30) NOT NULL,
  `user_id` varchar(4) NOT NULL,
  `usege` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `status` varchar(30) NOT NULL,
  `created` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `billing`
--

INSERT INTO `billing` (`billID`, `billing_id`, `mc_id`, `user_id`, `usege`, `total`, `status`, `created`, `updated`) VALUES
(1, 'Bi001', 'Mc001', 'Ur00', 80, 9000, 'Pending', '2022-05-14 19:57:37', '2022-05-14 19:57:37'),
(2, 'Bi001', 'Mc001', 'Ur00', 80, 9000, 'Pending', '2022-05-14 19:57:37', '2022-05-14 19:57:37'),
(3, 'Bi001', 'Mc001', 'Ur00', 80, 9000, 'Pending', '2022-05-14 19:57:37', '2022-05-14 19:57:37');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `invID` int(11) NOT NULL,
  `invCode` varchar(10) NOT NULL,
  `invName` varchar(30) NOT NULL,
  `manufact` varchar(4) NOT NULL,
  `stockQty` int(11) NOT NULL,
  `repair` varchar(12) NOT NULL,
  `handledBy` varchar(30) NOT NULL,
  `created` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`invID`, `invCode`, `invName`, `manufact`, `stockQty`, `repair`, `handledBy`, `created`, `updated`) VALUES
(1, 'INV345', 'Electric+Hammer', '2018', 100, '2022-04-16', 'EGM214', '2022-05-15 01:27:37', '2022-05-15 01:27:37'),
(2, 'INV348', 'Integrated Circuits', '2014', 500, '2022-05-03', 'EGM245', '2022-05-15 01:29:27', '2022-05-15 01:29:27');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `billing`
--
ALTER TABLE `billing`
  ADD PRIMARY KEY (`billID`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`invID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `billing`
--
ALTER TABLE `billing`
  MODIFY `billID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `invID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
