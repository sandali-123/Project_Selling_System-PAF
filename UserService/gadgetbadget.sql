-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 07:42 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gadgetbadget`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `U_id` int(30) NOT NULL,
  `fname` varchar(250) NOT NULL,
  `lname` varchar(250) NOT NULL,
  `nic` varchar(250) NOT NULL,
  `address` varchar(250) NOT NULL,
  `phone` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `type` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`U_id`, `fname`, `lname`, `nic`, `address`, `phone`, `email`, `username`, `password`, `type`) VALUES
(11, 'Sandali', 'Wijekoon', '990234780V', 'No 26,Aluvihare rd,Matale', '0778996678', 'san1999@gmail.com', 'sandali', 'c2FuZGFsaTk5', 'Seller'),
(15, 'Sam', 'Perera', '900233790V', 'No 23,Peradeniya rd,Kandy', '0716789118', 'samp@gmail.com', 'admin', 'YWRtaW4xMjM=', 'Admin'),
(16, 'Yohani', 'Silva', '932343790V', 'No 43,Katugastota rd,Kurunegala', '0756345608', 'yohasil@gmail.com', 'yohani', 'eW9oYW5pMTY=', 'Researcher'),
(19, 'Anne', 'Peries', '962135460V', 'No 51,Kandy rd,Colombo', '0711196608', 'aneper96@gmail.com', 'anne', 'YW5uZXA5Ng==', 'Buyer'),
(32, 'Rashini', 'Weerakoon', '984825467V', 'No 21,Kandy rd,Kegalle', '0714534528', 'rashweer@gmail.com', 'rashini', 'cmFzaGk5OA==', 'Seller');

--
-- Indexes for dumped tables
--


--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`U_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `U_id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
