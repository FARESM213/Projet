CREATE TABLE  `Patient` (
`patno` INT NOT NULL PRIMARY KEY ,
`patname` TEXT NOT NULL,
`patlogin` TEXT,
 `patpassword` TEXT

) ENGINE = INNODB;

CREATE TABLE  `Medecin` (
`medno` INT NOT NULL PRIMARY KEY ,
`medname` TEXT NOT NULL,
`medlogin` TEXT,
`medpassword` TEXT,
`medjob` TEXT NOT NULL

) ENGINE = INNODB;

CREATE TABLE  `Rendez_vous` (

`rdvno` INT NOT NULL  PRIMARY KEY ,
`medno` INT NOT NULL   ,
`patno` INT NOT NULL  ,
`rdv_date` TEXT NOT NULL ,
`rdv_motif` TEXT NOT NULL ,
`rdv_duree` INT NOT NULL  ,
`rdv_horaire` INT NOT NULL  ,
`loc` TEXT NOT NULL
) ENGINE = INNODB;

ALTER TABLE Rendez_vous ADD FOREIGN KEY (patno) REFERENCES Patient(patno);
ALTER TABLE Rendez_vous ADD FOREIGN KEY (medno) REFERENCES Medecin(medno);

-- Remplir table DEPT

INSERT INTO `Patient` (`patno` ,`patname`,`patlogin` ,`patpassword`) VALUES
('1',  'Fares','Fafiso','FAFA2511'),
('2',  'Amine','Minamino','Amine225'),
('3',  'Malena','Malenain','MAMA552'),
('4',  'Abel','Bebel','Bll3');

-- Remplir Table EMP

INSERT INTO  `Medecin` (`medno` ,`medname`,medjob,`medlogin` ,`medpassword`) VALUES
('1',  'Dupont','Generaliste','Hugo.D','Gene'),
('2',  'Bourgois','Chirurgie','Matt.B','Chir'),
('3',  'Christian','Dentiste','Chriri','Dent');

-- Remplir table mission

INSERT INTO `Rendez_vous` (`rdvno`,`medno`,`patno`,`loc`,`rdv_date`,`rdv_motif`,`rdv_duree`,`rdv_horaire`) VALUES
('1', '1' ,'2'		, 'Berlin'	, '2001-02-09','Consulation de routine ','1','17'),
('2', '2' ,'3'		, 'Chicago' , '2001-03-04','Operation Foie','1','13'),
('3', '3' ,'1'	, 'Dallas'  , '2001-04-11','Carries','2','15'),
('4', '3' ,'2'		, 'Paris'   , '2001-06-07','Blanchissement ','2','11'),
('5', '1' ,'3'		, 'Chicago' , '2001-02-09','Allergie','2','9');


