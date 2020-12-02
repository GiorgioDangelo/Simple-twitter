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
-- Struttura della tabella `notifica`
--

CREATE TABLE `notifica` (
  `utente_notificato` varchar(20) NOT NULL,
  `utente_eliminato` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `notifica`
--

INSERT INTO `notifica` (`utente_notificato`, `utente_eliminato`) VALUES
('giorgio', ''),
('mario', 'qqq'),
('giorgio', 'qqqq'),
('mario', 'qqqq'),
('mario', 'qqqq'),
('giorgio', 'giovanni'),
('nicola', 'giovanni'),
('nicola', 'laura'),
('giorgio', 'mario'),
('giovanni', 'mario'),
('nicola', 'mario'),
('giorgio', 'mario'),
('giorgio', 'gabriele'),
('giorgio', 'gio');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
