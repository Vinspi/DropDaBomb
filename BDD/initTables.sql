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
        niveauJoueur                    Int ,
        nom_guilde                      Varchar (25) ,
        activer                         Bool ,
        id_SkinMap                      Int NOT NULL ,
        activer_possederSkinCartonCarte Bool ,
        id_SkinCartonCarte              Int ,
        activer_possederIconeJoueur     Bool ,
        id_IconeJoueur                  Int ,
        id_Division                     Int ,
        PRIMARY KEY (Pseudo )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Carte
#------------------------------------------------------------

CREATE TABLE Carte(
        id_Carte         Int NOT NULL ,
        nomCarte         Varchar (40) ,
        rareteCarte      Varchar (25) ,
        typeCarte        Varchar (25) ,
        DescriptionCarte Varchar (200) ,
        imageCarte       Varchar (25) ,
        coutCarte        Int ,
        PRIMARY KEY (id_Carte )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ModeDeJeu
#------------------------------------------------------------

CREATE TABLE ModeDeJeu(
        id_mdj         Int NOT NULL ,
        nomModeDeJeu   Varchar (25) ,
        DescriptionMdJ Varchar (200) ,
        PRIMARY KEY (id_mdj )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Map
#------------------------------------------------------------

CREATE TABLE Map(
        id_SkinMap Int NOT NULL ,
        imageFond  Varchar (100) ,
        imageTable Varchar (100) ,
        PRIMARY KEY (id_SkinMap )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Guilde
#------------------------------------------------------------

CREATE TABLE Guilde(
        nom_guilde   Varchar (25) NOT NULL ,
        niveauGuilde Int ,
        nbPlacesMax  Int ,
        PRIMARY KEY (nom_guilde )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Match
#------------------------------------------------------------

CREATE TABLE Match(
        id_match   int (11) Auto_increment  NOT NULL ,
        dateMatch  Date ,
        dureeMatch Time ,
        id_mdj     Int ,
        PRIMARY KEY (id_match )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Deck
#------------------------------------------------------------

CREATE TABLE Deck(
        id_deck int (11) Auto_increment  NOT NULL ,
        PRIMARY KEY (id_deck )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SkinCartonCarte
#------------------------------------------------------------

CREATE TABLE SkinCartonCarte(
        id_SkinCartonCarte Int NOT NULL ,
        imageVerso         Varchar (100) ,
        imageContour       Varchar (100) ,
        PRIMARY KEY (id_SkinCartonCarte )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: IconeJoueur
#------------------------------------------------------------

CREATE TABLE IconeJoueur(
        id_IconeJoueur Int NOT NULL ,
        imageIcone     Varchar (100) ,
        PRIMARY KEY (id_IconeJoueur )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Boost
#------------------------------------------------------------

CREATE TABLE Boost(
        id_Boost  Int NOT NULL ,
        typeBoost Varchar (100) ,
        PRIMARY KEY (id_Boost )
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
        Pseudo              Varchar (20) NOT NULL ,
        Pseudo_CompteJoueur Varchar (20) NOT NULL ,
        PRIMARY KEY (Pseudo ,Pseudo_CompteJoueur )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: JoueurDansMatch
#------------------------------------------------------------

CREATE TABLE JoueurDansMatch(
        Pseudo   Varchar (20) NOT NULL ,
        id_match Int NOT NULL ,
        PRIMARY KEY (Pseudo ,id_match )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: JoueurCarteDeck
#------------------------------------------------------------

CREATE TABLE JoueurCarteDeck(
        qteCarte Int ,
        id_deck  Int NOT NULL ,
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
        id_match  Int NOT NULL ,
        PRIMARY KEY (Pseudo ,id_match )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: activer
#------------------------------------------------------------

CREATE TABLE activer(
        dateFin  Datetime ,
        id_Boost Int NOT NULL ,
        Pseudo   Varchar (20) NOT NULL ,
        PRIMARY KEY (id_Boost ,Pseudo )
)ENGINE=InnoDB;

ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_nom_guilde FOREIGN KEY (nom_guilde) REFERENCES Guilde(nom_guilde);
ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_id_SkinMap FOREIGN KEY (id_SkinMap) REFERENCES Map(id_SkinMap);
ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_id_SkinCartonCarte FOREIGN KEY (id_SkinCartonCarte) REFERENCES SkinCartonCarte(id_SkinCartonCarte);
ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_id_IconeJoueur FOREIGN KEY (id_IconeJoueur) REFERENCES IconeJoueur(id_IconeJoueur);
ALTER TABLE CompteJoueur ADD CONSTRAINT FK_CompteJoueur_id_Division FOREIGN KEY (id_Division) REFERENCES Division(id_Division);
ALTER TABLE Match ADD CONSTRAINT FK_Match_id_mdj FOREIGN KEY (id_mdj) REFERENCES ModeDeJeu(id_mdj);
ALTER TABLE Amis ADD CONSTRAINT FK_Amis_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo);
ALTER TABLE Amis ADD CONSTRAINT FK_Amis_Pseudo_CompteJoueur FOREIGN KEY (Pseudo_CompteJoueur) REFERENCES CompteJoueur(Pseudo);
ALTER TABLE JoueurDansMatch ADD CONSTRAINT FK_JoueurDansMatch_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo);
ALTER TABLE JoueurDansMatch ADD CONSTRAINT FK_JoueurDansMatch_id_match FOREIGN KEY (id_match) REFERENCES Match(id_match);
ALTER TABLE JoueurCarteDeck ADD CONSTRAINT FK_JoueurCarteDeck_id_deck FOREIGN KEY (id_deck) REFERENCES Deck(id_deck);
ALTER TABLE JoueurCarteDeck ADD CONSTRAINT FK_JoueurCarteDeck_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo);
ALTER TABLE JoueurCarteDeck ADD CONSTRAINT FK_JoueurCarteDeck_id_Carte FOREIGN KEY (id_Carte) REFERENCES Carte(id_Carte);
ALTER TABLE Historique ADD CONSTRAINT FK_Historique_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo);
ALTER TABLE Historique ADD CONSTRAINT FK_Historique_id_match FOREIGN KEY (id_match) REFERENCES Match(id_match);
ALTER TABLE activer ADD CONSTRAINT FK_activer_id_Boost FOREIGN KEY (id_Boost) REFERENCES Boost(id_Boost);
ALTER TABLE activer ADD CONSTRAINT FK_activer_Pseudo FOREIGN KEY (Pseudo) REFERENCES CompteJoueur(Pseudo);

