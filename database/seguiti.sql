-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Apr 03, 2020 alle 19:14
-- Versione del server: 10.4.11-MariaDB
-- Versione PHP: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `seguiti`
--

CREATE TABLE `seguiti` (
  `user_seguito` varchar(20) NOT NULL,
  `user_fk1` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `seguiti`
--

INSERT INTO `seguiti` (`user_seguito`, `user_fk1`) VALUES
('nicola', 'giorgio'),
('giovanni', 'giorgio'),
('giovanni', 'nicola'),
('giorgio', 'giovanni'),
('nicola', 'giovanni'),
('ee', 'giorgio'),
('fabrizio', 'giorgio'),
('fabrizio', 'giorgio'),
('nicola', 'giorgio'),
('dai', 'giorgio'),
('da', 'giorgio'),
('dai', 'giorgio'),
('da', 'giorgio'),
('dai', 'giorgio'),
('daii', 'giorgio'),
('b', 'giorgio'),
('bb', 'giorgio'),
('bbb', 'giorgio'),
('bbbb', 'giorgio'),
('b', 'giorgio'),
('bb', 'giorgio'),
('bbb', 'giorgio'),
('bbbb', 'giorgio'),
('z', 'giorgio'),
('zz', 'giorgio'),
('zzz', 'giorgio'),
('zzzz', 'giorgio'),
('q', 'giorgio'),
('qq', 'giorgio'),
('qqq', 'giorgio'),
('qqqq', 'giorgio'),
('qqq', 'mario'),
('qqqq', 'mario'),
('qqqq', 'mario'),
('angelo', 'giorgio'),
('giorgio', 'nicola');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
