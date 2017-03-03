#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: CompteJoueur
#------------------------------------------------------------

CREATE TABLE CompteJoueur(
  Pseudo                          Varchar (20) NOT NULL ,
  mailCompte                      Varchar (50) UNIQUE ,
  mdpCompte                       Varchar (25) ,
  niveauJoueur                    Int DEFAULT 1,
  expJoueur                       Int DEFAULT 0,
  ptsDivision                     Int DEFAULT 0,
  monnaieIG                       Int DEFAULT 0,
  monnaieIRL                      Int DEFAULT 0,
  nomGuilde                      Varchar (25) DEFAULT NULL,
  id_SkinMap                      Int NOT NULL DEFAULT 0,
  id_SkinCartonCarte              Int NOT NULL DEFAULT 0,
  id_IconeJoueur                  Int NOT NULL DEFAULT 0,
  id_Division                     Int ,
  PRIMARY KEY (Pseudo )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Carte
#------------------------------------------------------------

CREATE TABLE Carte(
  id_Carte           Int NOT NULL ,
  nomCarte           Varchar (40) ,
  rareteCarte        Varchar (25) ,
  typeCarte          Varchar (25) ,
  descriptionCarte   Varchar (200) ,
  imageCarte         Varchar (25) ,
  dropRateCarte      int DEFAULT 0,
  coutCarte          Int ,
  estAmeliorable     boolean DEFAULT 0,
  PRIMARY KEY (id_Carte )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ModeDeJeu
#------------------------------------------------------------

CREATE TABLE ModeDeJeu(
  id_ModeDeJeu         Int NOT NULL ,
  nomModeDeJeu         Varchar (25) ,
  descriptionModeDeJeu Varchar (200) ,
  PRIMARY KEY (id_ModeDeJeu )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SkinMap
#------------------------------------------------------------

CREATE TABLE SkinMap(
  id_SkinMap Int NOT NULL ,
  nomMap        varchar (50) ,
  imageFondMap  Varchar (100) ,
  imageTableMap Varchar (100) ,
  imageMiniatureMap VARCHAR (100) ,
  descriptionMap VARCHAR (200) ,
  PRIMARY KEY (id_SkinMap )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Guilde
#------------------------------------------------------------

CREATE TABLE Guilde(
  nomGuilde   Varchar (25) NOT NULL ,
  niveauGuilde Int DEFAULT 1,
  expGuilde    Int DEFAULT 0,
  nbPlacesMax  Int DEFAULT 50,
  PRIMARY KEY (nomGuilde )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Match
#------------------------------------------------------------

CREATE TABLE Matchs(
  id_Match   int (11) Auto_increment  NOT NULL ,
  debMatch   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
  finMatch   TIMESTAMP NULL,
  id_ModeDeJeu     Int NOT NULL DEFAULT 0,
  Pseudo1    VARCHAR(20) NOT NULL,
  Pseudo2    VARCHAR(20) NOT NULL,
  PRIMARY KEY (id_Match )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Deck
#------------------------------------------------------------

CREATE TABLE Deck(
  id_Deck varchar(21)  NOT NULL ,
  PRIMARY KEY (id_Deck )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SkinCartonCarte
#------------------------------------------------------------

CREATE TABLE SkinCartonCarte(
  id_SkinCartonCarte       Int NOT NULL ,
  nomCarton           varchar (50) ,
  imageVersoCarte          Varchar (100) ,
  imageContourCarte        Varchar (100) ,
  imageMiniatureCarton     VARCHAR (100) ,
  descritiptionCarton      VARCHAR (200) ,
  PRIMARY KEY (id_SkinCartonCarte )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: IconeJoueur
#------------------------------------------------------------

CREATE TABLE IconeJoueur(
  id_IconeJoueur Int NOT NULL ,
  nomIcone       varchar (50) ,
  imageIcone     Varchar (100) ,
  imageMiniatureIcone VARCHAR (100) ,
  descriptionIcone    VARCHAR (200) ,
  PRIMARY KEY (id_IconeJoueur )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Boost
#------------------------------------------------------------

CREATE TABLE Boost(
  id_Boost  Int NOT NULL ,
  nomBoost  Varchar (50),
  typeBoost Varchar (100) ,
  imageMiniatureBoost VARCHAR (100) ,
  descriptionBoost    VARCHAR (200) ,
  PRIMARY KEY (id_Boost )
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: activer
#------------------------------------------------------------

CREATE TABLE activer(
  HeuresFin  time ,
  nbMatchsFin int,
  id_Boost Int NOT NULL ,
  Pseudo   Varchar (20) NOT NULL ,
  PRIMARY KEY (id_Boost ,Pseudo )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Division
#------------------------------------------------------------

CREATE TABLE Division(
  id_Division   Int NOT NULL ,
  iconeDivision Varchar (25) ,
  nomDivision   Varchar (100) ,
  PRIMARY KEY (id_Division )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Amis
#------------------------------------------------------------

CREATE TABLE Amis(
  Pseudo      Varchar (20) NOT NULL ,
  Amis        Varchar (20) NOT NULL ,
  PRIMARY KEY (Pseudo, Amis )
)ENGINE=InnoDB;



#------------------------------------------------------------
# Table: JoueurCarteDeck
#------------------------------------------------------------

CREATE TABLE JoueurCarteDeck(
  qteCarte Int DEFAULT 0,
  id_Deck  Varchar (21) NOT NULL ,
  Pseudo   Varchar (20) NOT NULL ,
  id_Carte Int NOT NULL ,
  PRIMARY KEY (id_deck ,Pseudo ,id_Carte )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Historique
#------------------------------------------------------------

CREATE TABLE Historique(
  vainqueur Varchar (50) ,
  Pseudo    Varchar (20) NOT NULL ,
  id_Match  Int NOT NULL ,
  PRIMARY KEY (Pseudo ,id_match )
)ENGINE=InnoDB;




#____________________________________________________________
# Table: Offres
#____________________________________________________________

CREATE TABLE Offre(
  id_Offre int NOT NULL,
  prixMonnaieIG int,
  prixMonnaieIRL int,
  id_Pack int,
  typeOffre varchar(50),
  nomOffre  varchar(50),
  imageOffre varchar(50),
  PRIMARY KEY (id_Offre)
)ENGINE=InnoDB;

#____________________________________________________________
# Table: OffreMap
#____________________________________________________________
CREATE TABLE OffreMap (
    id_Offre int NOT NULL,
    id_SkinMap int NOT NULL,
    PRIMARY KEY (id_Offre,id_SkinMap)
)ENGINE=InnoDB;


#____________________________________________________________
# Table: OffreCartonCarte
#____________________________________________________________
CREATE TABLE OffreCartonCarte (
    id_Offre int NOT NULL,
    id_SkinCartonCarte int NOT NULL,
    PRIMARY KEY (id_Offre,id_SkinCartonCarte)
)ENGINE=InnoDB;


#____________________________________________________________
# Table: OffreBoost
#____________________________________________________________
CREATE TABLE OffreBoost (
    id_Offre int NOT NULL,
    id_Boost int NOT NULL,
    PRIMARY KEY (id_Offre,id_Boost)
)ENGINE=InnoDB;

#____________________________________________________________
# Table: OffreIcone
#____________________________________________________________
CREATE TABLE OffreIcone (
    id_Offre int NOT NULL,
    id_IconeJoueur int NOT NULL,
    PRIMARY KEY (id_Offre,id_IconeJoueur)
)ENGINE=InnoDB;


#_____________________________________________________________
# Table: Pack
#_____________________________________________________________

CREATE TABLE Pack(
  id_Pack int NOT NULL,
  nomPack varchar(20),
  imageMiniaturePack VARCHAR (100),
  descriptionPack varchar(200),
  PRIMARY KEY (id_Pack)
)ENGINE=InnoDB;


#_____________________________________________________________
# Table: LootPack  #_____________________________________________________________

CREATE TABLE LootPack (
  id_LootPack int NOT NULL,
  id_Pack int NOT NULL,
  qteCarte int DEFAULT 1,
  PRIMARY KEY (id_LootPack)
)ENGINE=InnoDB;

#_____________________________________________________________
# Table: LootPackEnsemble
#_____________________________________________________________
CREATE TABLE LootPackEnsemble (
  id_LootPack int NOT NULL,
  id_Ensemble int NOT NULL,
  PRIMARY KEY (id_LootPack,id_Ensemble)
)ENGINE=InnoDB;


#_____________________________________________________________
# Table: Ensemble
#_____________________________________________________________

CREATE TABLE Ensemble (
  id_Ensemble int NOT NULL,
  dropRatePack int DEFAULT 1,
  PRIMARY KEY (id_Ensemble)
)ENGINE=InnoDB;

#_____________________________________________________________
# Table: EnsembleCarte
#_____________________________________________________________

CREATE TABLE EnsembleCarte (
  id_Ensemble int NOT NULL,
  id_Carte int NOT NULL,
  PRIMARY KEY (id_Ensemble,id_Carte)
)ENGINE=InnoDB;

#_____________________________________________________________
# Table: AchatMonnaieIRL
#_____________________________________________________________

CREATE TABLE AchatMonnaieIRL(
  id_AchatIRL int NOT NULL Auto_increment,
  prixEuros int,
  gainMonnaie int,
  PRIMARY KEY (id_AchatIRL)
)ENGINE=InnoDB;

#_____________________________________________________________
# Table: (Historique)AchatIRL
#_____________________________________________________________
CREATE TABLE HistoriqueAchatIRL(
  dateAchatIRL  date ,
  id_AchatIRL int NOT NULL ,
  Pseudo varchar(50) ,
  PRIMARY KEY (id_AchatIRL,Pseudo)
)ENGINE=InnoDB;


#______________________________________________________________
# Table: posséderSkinMap
#______________________________________________________________
CREATE TABLE posséderSkinMap (
  Pseudo varchar(20) NOT NULL ,
  id_SkinMap int NOT NULL ,
  PRIMARY KEY (Pseudo,id_SkinMap)
)ENGINE=InnoDB;

#______________________________________________________________
# Table: posséderSkinCartonCarte
#______________________________________________________________
CREATE TABLE posséderSkinCartonCarte (
  Pseudo VARCHAR(20) NOT NULL ,
  id_SkinCartonCarte INT NOT NULL ,
  PRIMARY KEY (Pseudo,id_SkinCartonCarte)
)ENGINE=InnoDB;


#______________________________________________________________
# Table: posséderIconeJoueur
#______________________________________________________________
CREATE TABLE posséderIconeJoueur(
  Pseudo VARCHAR(20) NOT NULL ,
  id_IconeJoueur INT NOT NULL ,
  PRIMARY KEY (Pseudo,id_IconeJoueur)
)ENGINE=InnoDB;


ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_nom_guilde FOREIGN KEY (nomGuilde) REFERENCES Guilde(nomGuilde);
ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_id_SkinMap FOREIGN KEY (id_SkinMap) REFERENCES SkinMap(id_SkinMap);
ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_id_SkinCartonCarte FOREIGN KEY (id_SkinCartonCarte) REFERENCES SkinCartonCarte(id_SkinCartonCarte);
ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_id_IconeJoueur FOREIGN KEY (id_IconeJoueur) REFERENCES IconeJoueur(id_IconeJoueur);
ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_id_Division FOREIGN KEY (id_Division) REFERENCES Division(id_Division);

ALTER TABLE Matchs ADD CONSTRAINT FK_Matchs_id_mdj FOREIGN KEY (id_ModeDeJeu) REFERENCES ModeDeJeu(id_ModeDeJeu);
ALTER TABLE Matchs ADD CONSTRAINT FK_Matchs_id_Pseudo1 FOREIGN KEY  (Pseudo1) REFERENCES CompteJoueur(Pseudo);
ALTER TABLE Matchs ADD CONSTRAINT FK_Matchs_id_Pseudo2 FOREIGN KEY  (Pseudo2) REFERENCES CompteJoueur(Pseudo);


ALTER TABLE Amis ADD CONSTRAINT FK_Amis_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo);
ALTER TABLE Amis ADD CONSTRAINT FK_Amis_Pseudo_Amis FOREIGN KEY (Amis) REFERENCES CompteJoueur(Pseudo);

ALTER TABLE JoueurCarteDeck ADD CONSTRAINT FK_JoueurCarteDeck_id_deck FOREIGN KEY (id_Deck) REFERENCES Deck(id_Deck);
ALTER TABLE JoueurCarteDeck ADD CONSTRAINT FK_JoueurCarteDeck_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo);
ALTER TABLE JoueurCarteDeck ADD CONSTRAINT FK_JoueurCarteDeck_id_Carte FOREIGN KEY (id_Carte) REFERENCES Carte(id_Carte);

ALTER TABLE Historique ADD CONSTRAINT FK_Historique_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo);
ALTER TABLE Historique ADD CONSTRAINT FK_Historique_id_match FOREIGN KEY (id_Match) REFERENCES Matchs(id_Match);

ALTER TABLE activer ADD CONSTRAINT FK_activer_id_Boost FOREIGN KEY (id_Boost) REFERENCES Boost(id_Boost);
ALTER TABLE activer ADD CONSTRAINT FK_activer_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo);

ALTER TABLE Offre ADD CONSTRAINT FK_Offres_id_pack FOREIGN KEY (id_Pack) REFERENCES Pack(id_Pack);

ALTER TABLE HistoriqueAchatIRL ADD CONSTRAINT FK_HistoriqueAchat_id_achat FOREIGN KEY (id_AchatIRL) REFERENCES AchatMonnaieIRL(id_AchatIRL);
ALTER TABLE HistoriqueAchatIRL ADD CONSTRAINT FK_HistoriqueAchat_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo);

ALTER TABLE posséderIconeJoueur ADD CONSTRAINT FK_posséderIconeJoueur_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo);
ALTER TABLE posséderIconeJoueur ADD CONSTRAINT FK_posséderIconeJoueur_id_IconeJoueur FOREIGN KEY (id_IconeJoueur) REFERENCES IconeJoueur(id_IconeJoueur);

ALTER TABLE posséderSkinCartonCarte ADD CONSTRAINT FK_posséderSkinCartonCarte_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo);
ALTER TABLE posséderSkinCartonCarte ADD CONSTRAINT FK_posséderSkinCartonCarte_id_SkinCartonCarte FOREIGN KEY (id_SkinCartonCarte) REFERENCES SkinCartonCarte(id_SkinCartonCarte);

ALTER TABLE posséderSkinMap ADD CONSTRAINT FK_posséderSkinMap_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo);
ALTER TABLE posséderSkinMap ADD CONSTRAINT FK_posséderSkinMap_id_SkinMap FOREIGN KEY (id_SkinMap) REFERENCES SkinMap(id_SkinMap);

ALTER TABLE OffreMap ADD CONSTRAINT FK_OffreMap_id_Offre FOREIGN KEY (id_Offre) REFERENCES Offre(id_Offre);
ALTER TABLE OffreMap ADD CONSTRAINT FK_OffreMap_id_SkinMap FOREIGN KEY (id_SkinMap) REFERENCES SkinMap(id_SkinMap);

ALTER TABLE OffreCartonCarte ADD CONSTRAINT FK_OffreCartonCarte_id_Offre FOREIGN KEY (id_Offre) REFERENCES Offre(id_Offre);
ALTER TABLE OffreCartonCarte ADD CONSTRAINT FK_OffreCartonCarte_id_SkinCartonCarte FOREIGN KEY (id_SkinCartonCarte) REFERENCES SkinCartonCarte(id_SkinCartonCarte);

ALTER TABLE OffreBoost ADD CONSTRAINT FK_OffreBoost_id_Offre FOREIGN KEY (id_Offre) REFERENCES Offre(id_Offre);
ALTER TABLE OffreBoost ADD CONSTRAINT FK_OffreBoost_id_Boost FOREIGN KEY (id_Boost) REFERENCES Boost(id_Boost);

ALTER TABLE OffreIcone ADD CONSTRAINT FK_OffreIcone_id_Offre FOREIGN KEY (id_Offre) REFERENCES Offre(id_Offre);
ALTER TABLE OffreIcone ADD CONSTRAINT FK_OffreIcone_id_IconeJoueur FOREIGN KEY (id_IconeJoueur) REFERENCES IconeJoueur(id_IconeJoueur);

ALTER TABLE LootPack ADD CONSTRAINT FK_LootPack_id_Pack FOREIGN KEY (id_Pack) REFERENCES Pack(id_Pack);

ALTER TABLE LootPackEnsemble ADD CONSTRAINT FK_LootPackEnsemble_id_Ensemble FOREIGN KEY (id_Ensemble) REFERENCES Ensemble(id_Ensemble);
ALTER TABLE LootPackEnsemble ADD CONSTRAINT FK_LootPackEnsemble_id_LootPack FOREIGN KEY (id_LootPack) REFERENCES LootPack(id_LootPack);

ALTER TABLE EnsembleCarte ADD CONSTRAINT FK_EnsembleCarte_id_Carte FOREIGN KEY (id_Carte) REFERENCES Carte(id_Carte);
ALTER TABLE EnsembleCarte ADD CONSTRAINT FK_EnsembleCarte_id_Ensemble FOREIGN KEY (id_Ensemble) REFERENCES Ensemble(id_Ensemble);
