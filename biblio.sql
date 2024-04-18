-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 18 avr. 2024 à 03:52
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `biblio`
--

-- --------------------------------------------------------

--
-- Structure de la table `abonne`
--

CREATE TABLE `abonne` (
  `idab` int(11) NOT NULL,
  `nomab` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `abonne`
--

INSERT INTO `abonne` (`idab`, `nomab`) VALUES
(1, 'DIOP'),
(2, 'SENGHOR'),
(3, 'Fall'),
(4, 'ZENABA YOUNOUS'),
(5, 'FATIME DAGO'),
(6, 'JEANNINE ROUTOUANG'),
(7, 'Mangane'),
(8, 'RAISSA BETELh'),
(9, 'kaltam'),
(10, 'terap');

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `idlivre` int(11) NOT NULL,
  `titre` varchar(90) NOT NULL,
  `auteur` varchar(50) DEFAULT NULL,
  `disponible` varchar(10) NOT NULL,
  `idabonne` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`idlivre`, `titre`, `auteur`, `disponible`, `idabonne`) VALUES
(1, 'Tragedies', 'Roi christophe', 'OUI', NULL),
(11, 'Les mains sales', 'Jean Paule sartre', 'OUI', NULL),
(14, 'debedouin a president', 'kaka', 'NON', 10),
(15, 'mamaman', 'issay', 'OUI', NULL),
(20, 'hassanage', 'hassan', 'OUI', NULL),
(24, 'jacksonn', 'jaque', 'OUI', NULL),
(1000, 'bedouine', 'kaka', 'OUI', NULL);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `pret`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `pret` (
`idlivre` int(11)
,`titre` varchar(90)
,`nomab` varchar(100)
);

-- --------------------------------------------------------

--
-- Structure de la vue `pret`
--
DROP TABLE IF EXISTS `pret`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pret`  AS SELECT `livre`.`idlivre` AS `idlivre`, `livre`.`titre` AS `titre`, `abonne`.`nomab` AS `nomab` FROM (`livre` join `abonne` on(`livre`.`idabonne` = `abonne`.`idab`)) WHERE `livre`.`disponible` = 'NON''NON'  ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `abonne`
--
ALTER TABLE `abonne`
  ADD PRIMARY KEY (`idab`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`idlivre`),
  ADD KEY `fk1` (`idabonne`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `livre`
--
ALTER TABLE `livre`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`idabonne`) REFERENCES `abonne` (`idab`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
