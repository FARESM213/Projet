DROP TABLE IF EXISTS `medecin`;
CREATE TABLE IF NOT EXISTS `medecin` (
  `medno` int(11) NOT NULL,
  `medname` text NOT NULL,
  `medlogin` text,
  `medpassword` text,
  `medjob` text NOT NULL,
  PRIMARY KEY (`medno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `medecin`
--

INSERT INTO `medecin` (`medno`, `medname`, `medlogin`, `medpassword`, `medjob`) VALUES
(1, 'Dupont', 'Hugo.D', 'Gene', 'Generaliste'),
(2, 'Bourgois', 'Matt.B', 'Chir', 'Chirurgie'),
(3, 'Christian', 'Chriri', 'Dent', 'Dentiste');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `patno` int(11) NOT NULL,
  `patname` text NOT NULL,
  `patlogin` text,
  `patpassword` text,
  PRIMARY KEY (`patno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`patno`, `patname`, `patlogin`, `patpassword`) VALUES
(1, 'Fares', 'Fafiso', 'faf'),
(2, 'Amine', 'Minamino', 'Amine225'),
(3, 'Malena', 'Malenain', 'MAMA552'),
(4, 'Abel', 'Bebel', 'Bll3'),
(222, 'FAFOI', 'null', 'jiji');

-- --------------------------------------------------------

--
-- Structure de la table `rendez_vous`
--

DROP TABLE IF EXISTS `rendez_vous`;
CREATE TABLE IF NOT EXISTS `rendez_vous` (
  `rdvno` int(11) NOT NULL,
  `medno` int(11) NOT NULL,
  `patno` int(11) NOT NULL,
  `rdv_date` text NOT NULL,
  `rdv_motif` text NOT NULL,
  `rdv_duree` int(11) NOT NULL,
  `rdv_horaire` int(11) NOT NULL,
  `loc` text NOT NULL,
  `etat` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`rdvno`),
  KEY `patno` (`patno`),
  KEY `medno` (`medno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `rendez_vous`
--

INSERT INTO `rendez_vous` (`rdvno`, `medno`, `patno`, `rdv_date`, `rdv_motif`, `rdv_duree`, `rdv_horaire`, `loc`, `etat`) VALUES
(1, 1, 2, '2001-02-09', 'Consulation de routine ', 1, 17, 'Berlin', 1),
(2, 2, 3, '2001-03-04', 'Operation Foie', 1, 13, 'Chicago', 1),
(3, 3, 1, '2001-04-11', 'Carries', 2, 15, 'Dallas', 1),
(4, 3, 2, '2001-06-07', 'Blanchissement ', 2, 11, 'Paris', 1),
(5, 1, 3, '2001-02-09', 'Allergie', 2, 9, 'Chicago', 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `rendez_vous`
--
ALTER TABLE `rendez_vous`
  ADD CONSTRAINT `rendez_vous_ibfk_1` FOREIGN KEY (`patno`) REFERENCES `patient` (`patno`),
  ADD CONSTRAINT `rendez_vous_ibfk_2` FOREIGN KEY (`medno`) REFERENCES `medecin` (`medno`);
COMMIT;
