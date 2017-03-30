#____________________________________
#        Script MySQL.
#____________________________________


#____________________________________
# Table: CompteJoueur
#____________________________________

CREATE TABLE CompteJoueur(
  Pseudo                          Varchar (20) NOT NULL ,
  mailCompte                      Varchar (50) UNIQUE ,
  mdpCompte                       Varchar (25) ,
  niveauJoueur                    Int DEFAULT 1,
  expJoueur                       Int DEFAULT 0,
  monnaieIG                       Int DEFAULT 0,
  monnaieIRL                      Int DEFAULT 0,
  nomGuilde                       Varchar (25) DEFAULT NULL,
  id_SkinMap                      Int NOT NULL DEFAULT 0,
  id_SkinCartonCarte              Int NOT NULL DEFAULT 0,
  id_IconeJoueur                  Int NOT NULL DEFAULT 0,
  id_Division                     Int ,
  estAdmin                        Int DEFAULT 0 ,
  PRIMARY KEY (Pseudo )
)ENGINE=InnoDB;


#___________________________________
# Table: Carte
#___________________________________

CREATE TABLE Carte(
  id_Carte           Int NOT NULL ,
  nomCarte           Varchar (40) ,
  rareteCarte        Varchar (25) ,
  typeCarte          Varchar (25) ,
  descriptionCarte   Varchar (200) ,
  imageCarte         Varchar (25) ,
  dropRateCarte      FLOAT DEFAULT 0,
  coutCarte          Int ,
  estAmeliorable     Int DEFAULT 0,
  PRIMARY KEY (id_Carte )
)ENGINE=InnoDB;


#___________________________________
# Table: ModeDeJeu
#___________________________________

CREATE TABLE ModeDeJeu(
  id_ModeDeJeu         Int NOT NULL ,
  nomModeDeJeu         Varchar (25) ,
  descriptionModeDeJeu Varchar (200) ,
  PRIMARY KEY (id_ModeDeJeu )
)ENGINE=InnoDB;


#___________________________________
# Table: Map
#___________________________________

CREATE TABLE SkinMap(
  id_SkinMap Int NOT NULL ,
  nomMap     VARCHAR (50) ,
  imageFond  Varchar (100) ,
  imageTable Varchar (100) ,
  imageMiniatureMap VARCHAR (100) ,
  descriptionMap VARCHAR (200) ,
  PRIMARY KEY (id_SkinMap)
)ENGINE=InnoDB;


#___________________________________
# Table: Guilde
#___________________________________

CREATE TABLE Guilde(
  nomGuilde   Varchar (25) NOT NULL ,
  niveauGuilde Int DEFAULT 1,
  nbPlacesMax  Int DEFAULT 50,
  PRIMARY KEY (nomGuilde )
)ENGINE=InnoDB;


#___________________________________
# Table: Match
#___________________________________

CREATE TABLE Matchs(
  id_Match   int (11) Auto_increment  NOT NULL ,
  debMatch   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
  finMatch   TIMESTAMP NULL,
  id_ModeDeJeu     Int NOT NULL DEFAULT 0,
  Pseudo1    VARCHAR(20) NOT NULL,
  Pseudo2    VARCHAR(20) NOT NULL,
  PRIMARY KEY (id_Match )
)ENGINE=InnoDB;


#___________________________________
# Table: Deck
#___________________________________

CREATE TABLE Deck(
  id_Deck varchar(21)  NOT NULL ,
  estDeckActif TINYINT NOT NULL,
  PRIMARY KEY (id_Deck )
)ENGINE=InnoDB;


#___________________________________
# Table: SkinCartonCarte
#___________________________________

CREATE TABLE SkinCartonCarte(
  id_SkinCartonCarte       Int NOT NULL ,
  nomCarton                VARCHAR (50) ,
  imageVerso               Varchar (100) ,
  imageContour             Varchar (100) ,
  imageMiniatureCarton     VARCHAR (100) ,
  descriptionCarton      VARCHAR (200) ,
  PRIMARY KEY (id_SkinCartonCarte )
)ENGINE=InnoDB;


#___________________________________
# Table: IconeJoueur
#___________________________________

CREATE TABLE IconeJoueur(
  id_IconeJoueur Int NOT NULL ,
  nomIcone       VARCHAR (50) ,
  imageIcone     Varchar (100) ,
  imageMiniatureIcone VARCHAR (100) ,
  descriptionIcone    VARCHAR (200) ,
  PRIMARY KEY (id_IconeJoueur )
)ENGINE=InnoDB;


#___________________________________
# Table: Boost
#___________________________________

CREATE TABLE Boost(
  id_Boost  Int NOT NULL ,
  nomBoost  VARCHAR (50) ,
  typeBoost Varchar (100) ,
  imageMiniatureBoost VARCHAR (100) ,
  descriptionBoost    VARCHAR (200) ,
  PRIMARY KEY (id_Boost )
)ENGINE=InnoDB;


#___________________________________
# Table: Division
#___________________________________

CREATE TABLE Division(
  id_Division   Int NOT NULL ,
  iconeDivision Varchar (25) ,
  nomDivision   Varchar (100) ,
  PRIMARY KEY (id_Division )
)ENGINE=InnoDB;


#___________________________________
# Table: Amis
#___________________________________

CREATE TABLE Amis(
  Pseudo      Varchar (20) NOT NULL ,
  Amis        Varchar (20) NOT NULL ,
  PRIMARY KEY (Pseudo, Amis )
)ENGINE=InnoDB;



#___________________________________
# Table: JoueurCarteDeck
#___________________________________

CREATE TABLE JoueurCarteDeck(
  qteCarteDeck Int DEFAULT 0,
  id_Deck  Varchar (21) NOT NULL ,
  Pseudo   Varchar (20) NOT NULL ,
  id_Carte Int NOT NULL ,
  PRIMARY KEY (id_deck ,Pseudo ,id_Carte )
)ENGINE=InnoDB;


#___________________________________
# Table: Historique
#___________________________________

CREATE TABLE Historique(
  vainqueur Varchar (50) ,
  Pseudo    Varchar (20) NOT NULL ,
  id_Match  Int NOT NULL ,
  PRIMARY KEY (Pseudo ,id_match )
)ENGINE=InnoDB;


#___________________________________
# Table: activer
#___________________________________

CREATE TABLE activer(
  HeuresFin  TIME ,
  nbMatchFin int ,
  id_Boost Int NOT NULL ,
  Pseudo   Varchar (20) NOT NULL ,
  PRIMARY KEY (id_Boost ,Pseudo )
)ENGINE=InnoDB;


#____________________________________________________________
# Table: Offres
#____________________________________________________________

CREATE TABLE Offre(
  id_Offre int NOT NULL,
  nomPack  VARCHAR (50) ,
  prixMonnaieIG int,
  prixMonnaieIRL int,
  typeOffre VARCHAR (50) ,
  misEnVente int DEFAULT 0 NOT NULL,
  imageOffre  VARCHAR (100),
  id_Pack int,
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
  descriptionPack varchar(200),
  PRIMARY KEY (id_Pack)
)ENGINE=InnoDB;


#_____________________________________________________________
# Table: LootPackPack
#_____________________________________________________________

CREATE TABLE LootPackPack (
  id_LootPack int NOT NULL,
  id_Pack int NOT NULL,
  qteCartePack int DEFAULT 0,
  PRIMARY KEY (id_LootPack,id_Pack)
)ENGINE=InnoDB;



#_____________________________________________________________
# Table: LootPackEnsemble
#_____________________________________________________________
CREATE TABLE LootPack (
  id_LootPack int NOT NULL ,
  id_Ensemble int NOT NULL ,
  nomLootPack VARCHAR (50) ,
  dropRatePack FLOAT DEFAULT 1,
  PRIMARY KEY (id_LootPack,id_Ensemble)
)ENGINE=InnoDB;




#_____________________________________________________________
# Table: Ensemble
#_____________________________________________________________

CREATE TABLE Ensemble (
  id_Ensemble int NOT NULL,
  id_Carte int NOT NULL,
  nomEnsemble VARCHAR (50),
  PRIMARY KEY (id_Ensemble,id_Carte)
)ENGINE=InnoDB;

#_____________________________________________________________
# Table: AchatMonnaieIRL
#_____________________________________________________________

CREATE TABLE AchatMonnaieIRL(
  id_AchatIRL int NOT NULL,
  prixEuros int,
  gainMonnaie int,
  PRIMARY KEY (id_AchatIRL)
)ENGINE=InnoDB;

#_____________________________________________________________
# Table: (Historique)Achat
#_____________________________________________________________
CREATE TABLE HistoriqueAchat(

  id_AchatIRL int NOT NULL ,
  Pseudo varchar(50) ,
  dateAchatIRL TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
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


ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_nom_guilde FOREIGN KEY (nomGuilde) REFERENCES Guilde(nomGuilde) ON DELETE SET NULL;
ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_id_SkinMap FOREIGN KEY (id_SkinMap) REFERENCES SkinMap(id_SkinMap) ON DELETE SET NULL;
ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_id_SkinCartonCarte FOREIGN KEY (id_SkinCartonCarte) REFERENCES SkinCartonCarte(id_SkinCartonCarte);
ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_id_IconeJoueur FOREIGN KEY (id_IconeJoueur) REFERENCES IconeJoueur(id_IconeJoueur);
ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_id_Division FOREIGN KEY (id_Division) REFERENCES Division(id_Division);

ALTER TABLE Matchs ADD CONSTRAINT FK_Matchs_id_mdj FOREIGN KEY (id_ModeDeJeu) REFERENCES ModeDeJeu(id_ModeDeJeu);
ALTER TABLE Matchs ADD CONSTRAINT FK_Matchs_id_Pseudo1 FOREIGN KEY  (Pseudo1) REFERENCES CompteJoueur(Pseudo);
ALTER TABLE Matchs ADD CONSTRAINT FK_Matchs_id_Pseudo2 FOREIGN KEY  (Pseudo2) REFERENCES CompteJoueur(Pseudo);


ALTER TABLE Amis ADD CONSTRAINT FK_Amis_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo) ON DELETE CASCADE;
ALTER TABLE Amis ADD CONSTRAINT FK_Amis_Pseudo_Amis FOREIGN KEY (Amis) REFERENCES CompteJoueur(Pseudo);

ALTER TABLE JoueurCarteDeck ADD CONSTRAINT FK_JoueurCarteDeck_id_deck FOREIGN KEY (id_Deck) REFERENCES Deck(id_Deck);
ALTER TABLE JoueurCarteDeck ADD CONSTRAINT FK_JoueurCarteDeck_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo) ON DELETE CASCADE;
ALTER TABLE JoueurCarteDeck ADD CONSTRAINT FK_JoueurCarteDeck_id_Carte FOREIGN KEY (id_Carte) REFERENCES Carte(id_Carte);

ALTER TABLE Historique ADD CONSTRAINT FK_Historique_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo) ON DELETE CASCADE;
ALTER TABLE Historique ADD CONSTRAINT FK_Historique_id_match FOREIGN KEY (id_Match) REFERENCES Matchs(id_Match);

ALTER TABLE activer ADD CONSTRAINT FK_activer_id_Boost FOREIGN KEY (id_Boost) REFERENCES Boost(id_Boost);
ALTER TABLE activer ADD CONSTRAINT FK_activer_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo) ON DELETE CASCADE;

ALTER TABLE Offre ADD CONSTRAINT FK_Offres_id_pack FOREIGN KEY (id_Pack) REFERENCES Pack(id_Pack);

ALTER TABLE HistoriqueAchat ADD CONSTRAINT FK_HistoriqueAchat_id_achat FOREIGN KEY (id_AchatIRL) REFERENCES AchatMonnaieIRL(id_AchatIRL);
ALTER TABLE HistoriqueAchat ADD CONSTRAINT FK_HistoriqueAchat_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo) ON DELETE CASCADE;

ALTER TABLE posséderIconeJoueur ADD CONSTRAINT FK_posséderIconeJoueur_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo) ON DELETE CASCADE;
ALTER TABLE posséderIconeJoueur ADD CONSTRAINT FK_posséderIconeJoueur_id_IconeJoueur FOREIGN KEY (id_IconeJoueur) REFERENCES IconeJoueur(id_IconeJoueur) ON DELETE SET NULL;

ALTER TABLE posséderSkinCartonCarte ADD CONSTRAINT FK_posséderSkinCartonCarte_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo) ON DELETE CASCADE ;
ALTER TABLE posséderSkinCartonCarte ADD CONSTRAINT FK_posséderSkinCartonCarte_id_SkinCartonCarte FOREIGN KEY (id_SkinCartonCarte) REFERENCES SkinCartonCarte(id_SkinCartonCarte);

ALTER TABLE posséderSkinMap ADD CONSTRAINT FK_posséderSkinMap_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo) ON DELETE CASCADE;
ALTER TABLE posséderSkinMap ADD CONSTRAINT FK_posséderSkinMap_id_SkinMap FOREIGN KEY (id_SkinMap) REFERENCES SkinMap(id_SkinMap);

ALTER TABLE OffreMap ADD CONSTRAINT FK_OffreMap_id_Offre FOREIGN KEY (id_Offre) REFERENCES Offre(id_Offre) ON DELETE CASCADE ;
ALTER TABLE OffreMap ADD CONSTRAINT FK_OffreMap_id_SkinMap FOREIGN KEY (id_SkinMap) REFERENCES SkinMap(id_SkinMap);

ALTER TABLE OffreCartonCarte ADD CONSTRAINT FK_OffreCartonCarte_id_Offre FOREIGN KEY (id_Offre) REFERENCES Offre(id_Offre) ON DELETE CASCADE;
ALTER TABLE OffreCartonCarte ADD CONSTRAINT FK_OffreCartonCarte_id_SkinCartonCarte FOREIGN KEY (id_SkinCartonCarte) REFERENCES SkinCartonCarte(id_SkinCartonCarte);

ALTER TABLE OffreBoost ADD CONSTRAINT FK_OffreBoost_id_Offre FOREIGN KEY (id_Offre) REFERENCES Offre(id_Offre) ON DELETE CASCADE ;
ALTER TABLE OffreBoost ADD CONSTRAINT FK_OffreBoost_id_Boost FOREIGN KEY (id_Boost) REFERENCES Boost(id_Boost);

ALTER TABLE OffreIcone ADD CONSTRAINT FK_OffreIcone_id_Offre FOREIGN KEY (id_Offre) REFERENCES Offre(id_Offre) ON DELETE CASCADE ;
ALTER TABLE OffreIcone ADD CONSTRAINT FK_OffreIcone_id_IconeJoueur FOREIGN KEY (id_IconeJoueur) REFERENCES IconeJoueur(id_IconeJoueur);

ALTER TABLE LootPackPack ADD CONSTRAINT FK_LootPack_id_Pack FOREIGN KEY (id_Pack) REFERENCES Pack(id_Pack) ON DELETE CASCADE;
ALTER TABLE LootPackPack ADD CONSTRAINT FK_LootPackPack_id_LootPack FOREIGN KEY (id_LootPack) REFERENCES LootPack(id_LootPack) ON DELETE CASCADE;

ALTER TABLE LootPack ADD CONSTRAINT FK_LootPack_id_Ensemble FOREIGN KEY (id_Ensemble) REFERENCES Ensemble(id_Ensemble) ON DELETE CASCADE;

ALTER TABLE Ensemble ADD CONSTRAINT FK_EnsembleCarte_id_Carte FOREIGN KEY (id_Carte) REFERENCES Carte(id_Carte) ON DELETE CASCADE;
