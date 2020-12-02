-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Apr 03, 2020 alle 19:13
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
-- Struttura della tabella `messaggi`
--

CREATE TABLE `messaggi` (
  `Messaggio` varchar(140) NOT NULL,
  `utente_seguito_fk` varchar(20) NOT NULL,
  `hashtag` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `messaggi`
--

INSERT INTO `messaggi` (`Messaggio`, `utente_seguito_fk`, `hashtag`) VALUES
('andr? tutto bene', 'giorgio', '#coronavirus'),
('speriamo vada bene', 'giorgio', '#coronavirus'),
('Forza italia', 'giorgio', '#coronavirus'),
('Forza e coraggio a tutti', 'giorgio', ''),
('Viva l\'italia', 'giorgio', ''),
('Viva l\'italia', 'giorgio', '#Italia'),
('ci siamo tutti??', 'giorgio', ''),
('Ciaooo', 'mario', '#corona'),
('ci sonoo', 'mario', ''),
('aaa', 'angelo', ''),
('Ciao a tutti,come State?', 'angelo', ''),
('State studiando?', 'angelo', '#scuola');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `messaggi`
--
ALTER TABLE `messaggi`
  ADD KEY `utente_seguito_fk` (`utente_seguito_fk`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
