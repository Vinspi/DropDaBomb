INSERT INTO CompteJoueur(Pseudo, mailCompte, mdpCompte) VALUES ('morray','bidon@bidon.com','yolo');
INSERT INTO CompteJoueur(Pseudo, mailCompte, mdpCompte) VALUES ('mouloud','mouloud@bidon.com','yolo');
INSERT INTO CompteJoueur(Pseudo, mailCompte, mdpCompte) VALUES ('moktar','moktar@bidon.com','yolo');
INSERT INTO CompteJoueur(Pseudo, mailCompte, mdpCompte) VALUES ('morice','morice@bidon.com','yolo');


INSERT INTO ModeDeJeu (id_ModeDeJeu,nomModeDeJeu,descriptionModeDeJeu) VALUES (0,'Classique','C\'est un mode de jeu trop bien, essayez-le (et payez)');

INSERT INTO Matchs (id_ModeDeJeu,finMatch,Pseudo1,Pseudo2) VALUES (0,NULL,'mouloud','morice');
INSERT INTO Matchs (id_ModeDeJeu,finMatch,Pseudo1,Pseudo2) VALUES (0,NULL,'mouloud','morice');
INSERT INTO Matchs (id_ModeDeJeu,finMatch,Pseudo1,Pseudo2) VALUES (0,NULL,'moktar','morice');
INSERT INTO Matchs (id_ModeDeJeu,finMatch,Pseudo1,Pseudo2) VALUES (0,NULL,'morray','moktar');


INSERT INTO Carte (id_Carte,nomCarte,typeCarte,descriptionCarte,coutCarte) VALUES (0,'Dick Out For Harambe','Sort','Harambe est toujours dans nos coeurs. +25 mana',0);
INSERT INTO Carte (id_Carte,nomCarte,typeCarte,descriptionCarte,coutCarte) VALUES (1,'YasuoPogChamp','Combattant','2items100%ccNarmol',5);
INSERT INTO Carte (id_Carte,nomCarte,typeCarte,descriptionCarte,coutCarte) VALUES (2,'WhereIsMyTeam?','Sort','Vous n\'avez pas de team ? Pas de problème, on vous fournit ça',20);
INSERT INTO Carte (id_Carte,nomCarte,typeCarte,descriptionCarte,coutCarte) VALUES (3,'JféDéRouAriairDanLeQDeTaMer','Sort','VroumVroum',5);
INSERT INTO Carte (id_Carte,nomCarte,typeCarte,descriptionCarte,coutCarte) VALUES (4,'Victoire Dou Blazil','Sort','Vous invoquez Leonardo l\'interdit, qui invoque le carnaval Dou Blazil.',100);


INSERT INTO Deck () VALUES ('morray0');
INSERT INTO Deck () VALUES ('morray1');
INSERT INTO Deck () VALUES ('morray2');
INSERT INTO Deck () VALUES ('morray3');

INSERT INTO Deck () VALUES ('moktar0');
INSERT INTO Deck () VALUES ('moktar1');
INSERT INTO Deck () VALUES ('moktar2');
INSERT INTO Deck () VALUES ('moktar3');
