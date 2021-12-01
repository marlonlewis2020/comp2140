-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2021 at 04:26 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `beaditupja`
--

-- --------------------------------------------------------

--
-- Table structure for table `bracelet`
--

CREATE TABLE `bracelet` (
  `id` int(8) NOT NULL,
  `name` varchar(30) NOT NULL,
  `collection` varchar(30) NOT NULL,
  `cost` double(8,0) NOT NULL,
  `smallBeads` varchar(999) DEFAULT NULL,
  `mediumBeads` varchar(999) DEFAULT NULL,
  `largeBeads` varchar(999) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bracelet`
--

INSERT INTO `bracelet` (`id`, `name`, `collection`, `cost`, `smallBeads`, `mediumBeads`, `largeBeads`) VALUES
(1, 'b1', 'diatta', 3, 'yellow-2;green-4', 'yellow-5;green-8', 'yellow-10;green-16');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(8) NOT NULL,
  `name` varchar(30) NOT NULL,
  `telephone` varchar(15) NOT NULL,
  `pickup_location` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `name`, `telephone`, `pickup_location`) VALUES
(1, 'Mercedes', '8768164681', 'Sedecrem'),
(2, 'Callay', '8769654681', 'Spanish'),
(12, 'Marlon Lewis', '8764385612', 'Half Way Tree'),
(4, 'Kimani', '8769654781', 'Mobay'),
(5, 'Gabriel', '8769654781', 'Mobay');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_number` int(8) NOT NULL,
  `bracelets` varchar(150) NOT NULL,
  `order_quantity` varchar(50) NOT NULL,
  `customer_id` int(8) NOT NULL,
  `pickup_location` varchar(30) NOT NULL,
  `order_date` datetime NOT NULL,
  `total` double(8,0) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_number`, `bracelets`, `order_quantity`, `customer_id`, `pickup_location`, `order_date`, `total`) VALUES
(86, 'b1', '9', 12, 'Half Way Tree', '2021-12-01 00:00:00', 27),
(87, 'b1', '9', 12, 'Half Way Tree', '2021-12-01 00:00:00', 27);

-- --------------------------------------------------------

--
-- Table structure for table `pickup_location`
--

CREATE TABLE `pickup_location` (
  `id` int(8) NOT NULL,
  `parish` varchar(16) NOT NULL,
  `location` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(8) NOT NULL,
  `name` varchar(20) NOT NULL,
  `privilege` varchar(500) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`, `privilege`) VALUES
(1, 'ADMIN', 'create user, create stock, create bracelet, create customer, edit user, edit stock, use stock, edit bracelet, edit customer, view users, view user, view inventory, view stock, bracelets, view bracelet, view customers, view customer'),
(2, 'BUSINESS_OWNER', 'create user, create stock, create bracelet, create customer, edit user, edit stock, use stock, edit bracelet, edit customer, view users, view user, view inventory, view stock, view bracelets, view bracelet, view customers, view customer'),
(6, 'SUPPLIER', ''),
(7, 'PRODUCTION_MANAGER', 'create stock, create bracelet, edit stock, use stock, edit bracelet, view inventory, view stock, view bracelets, view bracelet'),
(5, 'INVENTORY_MANAGER', 'create stock, create bracelet, edit stock, use stock, view inventory, view stock, view bracelets, view bracelet');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `id` int(8) NOT NULL,
  `name` varchar(30) NOT NULL,
  `type` varchar(15) NOT NULL,
  `quantity` int(8) NOT NULL,
  `limit` int(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`id`, `name`, `type`, `quantity`, `limit`) VALUES
(3, 'Gibbits', 'Beads', 17499, 50),
(12, 'iron', 'Beads', 9750, 200),
(11, 'Panther', 'Beads', 795965, 50),
(8, 'irory', 'Beads', 824, 210),
(13, 'white', 'Beads', 9450, 50),
(14, 'black', 'Beads', 9450, 200),
(15, 'brown', 'Beads', 1900, 200),
(16, 'red', 'Beads', 380, 20),
(17, 'green', 'Beads', 570, 200);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(8) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` varchar(16) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`) VALUES
(14, 'Gabriel', '-1012435683', 'BUSINESS_OWNER'),
(13, 'mlewis', '1403730359', 'SUPPLIER'),
(12, 'Calzy', '-1753180783', 'ADMIN'),
(15, 'Marly', '-30188838', 'ADMIN');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bracelet`
--
ALTER TABLE `bracelet`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_number`);

--
-- Indexes for table `pickup_location`
--
ALTER TABLE `pickup_location`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bracelet`
--
ALTER TABLE `bracelet`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_number` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;

--
-- AUTO_INCREMENT for table `pickup_location`
--
ALTER TABLE `pickup_location`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
