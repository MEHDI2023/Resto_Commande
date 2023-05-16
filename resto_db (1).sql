-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 29 avr. 2023 à 00:38
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
-- Base de données : `resto_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `tb_aliment`
--

CREATE TABLE `tb_aliment` (
  `code_aliment` varchar(50) NOT NULL,
  `nom_aliment` varchar(100) NOT NULL,
  `prix_aliment` decimal(9,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `tb_aliment`
--

INSERT INTO `tb_aliment` (`code_aliment`, `nom_aliment`, `prix_aliment`) VALUES
('CARPE_RIZ', 'Soupe de carpe au riz', '2000.00'),
('EAU_GB', 'Eau minérale 1.5 cl', '500.00'),
('EAU_PB', 'Eau minérale 50cl', '250.00'),
('FRUIT_JUS', 'Jus de fruit', '500.00'),
('njkbd', 'aece', '354.00'),
('POULET_B', 'Poulet braisé', '3000.00'),
('SHANDWICH_O', 'Shandwich omellette', '500.00'),
('SHANDWICH_V', 'Shandwich de viande hachée', '500.00'),
('SUCRERIE_G', 'Sucrerie grande bouteille', '1250.00'),
('SUCRERIE_P', 'sucrerie petite bouteille', '300.00');

-- --------------------------------------------------------

--
-- Structure de la table `tb_commande`
--

CREATE TABLE `tb_commande` (
  `id_commande` int(11) NOT NULL,
  `aliment` varchar(50) NOT NULL,
  `qte_aliment` smallint(6) NOT NULL,
  `tab_num` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `tb_commande`
--

INSERT INTO `tb_commande` (`id_commande`, `aliment`, `qte_aliment`, `tab_num`) VALUES
(14, 'POULET_B', 1, 1),
(18, 'CARPE_RIZ', 2, 1),
(25, 'CARPE_RIZ', 33, 5);

-- --------------------------------------------------------

--
-- Structure de la table `tb_historique`
--

CREATE TABLE `tb_historique` (
  `id_commande` int(11) NOT NULL,
  `aliment` varchar(50) NOT NULL,
  `qte_aliment` smallint(6) NOT NULL,
  `tab_num` smallint(6) NOT NULL,
  `date_commande` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `tb_historique`
--

INSERT INTO `tb_historique` (`id_commande`, `aliment`, `qte_aliment`, `tab_num`, `date_commande`) VALUES
(6, 'EAU_PB', 1, 2, '2021-04-15 21:06:25'),
(7, 'SHANDWICH_V', 2, 1, '2021-04-15 21:06:54'),
(8, 'EAU_GB', 1, 1, '2021-04-15 21:07:13'),
(14, 'POULET_B', 1, 1, '2021-04-15 21:57:57'),
(15, 'CARPE_RIZ', 2, 3, '2023-04-25 23:43:40'),
(16, 'EAU_GB', 12, 3, '2023-04-26 00:11:09'),
(17, 'njkbd', 31, 2, '2023-04-26 01:05:03'),
(18, 'CARPE_RIZ', 2, 1, '2023-04-26 01:14:14'),
(19, 'CARPE_RIZ', 2, 3, '2023-04-26 01:20:21'),
(20, 'CARPE_RIZ', 12, 10, '2023-04-26 01:20:51'),
(21, 'EAU_GB', 1, 2, '2023-04-26 01:23:35'),
(22, 'CARPE_RIZ', 22, 2, '2023-04-27 04:22:06'),
(23, 'CARPE_RIZ', 33, 5, '2023-04-28 21:37:53');

-- --------------------------------------------------------

--
-- Structure de la table `tb_table`
--

CREATE TABLE `tb_table` (
  `num_tab` smallint(6) NOT NULL,
  `nb_place` smallint(6) NOT NULL,
  `etat` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `tb_table`
--

INSERT INTO `tb_table` (`num_tab`, `nb_place`, `etat`) VALUES
(1, 1, 'occupee'),
(2, 1, 'libre'),
(3, 2, 'libre'),
(4, 2, 'libre'),
(5, 4, 'occupee'),
(6, 4, 'libre'),
(7, 6, 'libre'),
(8, 6, 'libre'),
(9, 4, 'libre'),
(10, 1, 'libre');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `name`, `password`) VALUES
(12, 'mehdi', 'mehdi'),
(1122, 'iheb', 'iheb');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `tb_aliment`
--
ALTER TABLE `tb_aliment`
  ADD PRIMARY KEY (`code_aliment`);

--
-- Index pour la table `tb_commande`
--
ALTER TABLE `tb_commande`
  ADD PRIMARY KEY (`id_commande`),
  ADD KEY `fk` (`aliment`),
  ADD KEY `fk2` (`tab_num`);

--
-- Index pour la table `tb_historique`
--
ALTER TABLE `tb_historique`
  ADD PRIMARY KEY (`id_commande`);

--
-- Index pour la table `tb_table`
--
ALTER TABLE `tb_table`
  ADD PRIMARY KEY (`num_tab`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `tb_commande`
--
ALTER TABLE `tb_commande`
  MODIFY `id_commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pour la table `tb_historique`
--
ALTER TABLE `tb_historique`
  MODIFY `id_commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `tb_commande`
--
ALTER TABLE `tb_commande`
  ADD CONSTRAINT `fk` FOREIGN KEY (`aliment`) REFERENCES `tb_aliment` (`code_aliment`),
  ADD CONSTRAINT `fk2` FOREIGN KEY (`tab_num`) REFERENCES `tb_table` (`num_tab`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
