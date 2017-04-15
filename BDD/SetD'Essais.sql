INSERT INTO SkinMap (id_SkinMap,imageMiniatureMap,descriptionMap) VALUES (0,'map1.png','Map0');
INSERT INTO SkinMap (id_SkinMap,imageMiniatureMap,descriptionMap) VALUES (1,'map1.png','Map1');

INSERT INTO SkinCartonCarte (id_SkinCartonCarte, descriptionCarton, imageMiniatureCarton) VALUES (0, 'Carton6','carton1.png');
INSERT INTO SkinCartonCarte (id_SkinCartonCarte, descriptionCarton, imageMiniatureCarton) VALUES (1, 'Carton6','carton1.png');


INSERT INTO IconeJoueur (id_IconeJoueur,imageIcone) VALUES (0,'icone0.png');
INSERT INTO IconeJoueur (id_IconeJoueur,imageIcone) VALUES (1,'superIcone.png');


INSERT INTO ModeDeJeu (id_ModeDeJeu,nomModeDeJeu,descriptionModeDeJeu) VALUES (0,'Classique','C''est un mode de jeu vraiment bien, essayez-le (et payez)');


INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (0,'BOMB 25','normal','Degat','inflige 25 pts de degats à votre adversaire','BOMB_25.png',3,0);

INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (1,'BOMB 50','normal','Degat','inflige 50 pts de degats à votre adversaire','BOMB_50.png',3,0);

INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (2,'BOMB 50 retardement','normal','Degat','inflige 50 pts de degats à votre adversaire après deux tours','BOMB_50_2TOURS.png',3,0);

INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (3,'BOMB 100 retardement','normal','Degat','inflige 100 pts de degats à votre adversaire après deux tours','BOMB_25.png',3,0);

INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (4,'BOUCLIER 15','normal','Bouclier','absorbe 15 pts de degat','BOUCLIER_15.png',3,0);

INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (5,'BOUCLIER 45','rare','Bouclier','absorbe 45 pts de degat','BOUCLIER_45.png',3,0);

INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (6,'BOUCLIER GENERATEUR DE MANA','epique','Sort','convertit 20% des degat subit en poudre','BOUCLIER_GEN_MANA.png',3,0);

INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (7,'Echange forcé','rare','Sort','echange une carte de votre main avec une de celle de votre adversaire','EXCHANGE_CARD.png',3,0);

INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (8,'Desenvoutement','rare','Sort','désenvoute votre adversaire','SPELL_SUPPR.png',3,0);

INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (9,'Melange','rare','Sort','mélange votre deck avec votre main','SWAP_CARDS_DECK.png',3,0);

INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (10,'Canon','rare','batiment','pose un batiment canon qui inflige des dégat a chaque fin de tour, peut etre amélioré','SPELL_CANON_LVL_1.jpg',5,0);

INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (11,'Upgrade','normal','sort','ameliore un de vos batiment','SPELL_UPGRADE.jpg',2,0);

INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (12,'Extracteur','rare','batiment','pose un batiment extracteur qui genere de la poudre a chaque fin de tour, peut etre amélioré','EXTRACTEUR_LVL_1.jpg',5,0);

INSERT INTO `Carte`(`id_Carte`, `nomCarte`, `rareteCarte`, `typeCarte`, `descriptionCarte`, `imageCarte`,`coutCarte`, `estAmeliorable`) VALUES (13,'Canon','rare','batiment','lance trois bombes, le materiel etant ce qu\'il est il est possible qu\'elle n\'explose pas toutes ...','SPELL_CANON_LVL_1.jpg',5,0);






INSERT INTO Pack (id_Pack,nomPack,descriptionPack) VALUES (1,'Pack 0','Le premier pack');
INSERT INTO Pack (id_Pack,nomPack,descriptionPack) VALUES (2,'Pack 1','Le deuxième pack');
INSERT INTO Pack (id_Pack,nomPack,descriptionPack) VALUES (3,'Pack 2','Le troisième pack');

INSERT INTO Boost (id_Boost,typeBoost,nomBoost,imageMiniatureBoost,descriptionBoost) VALUES (0,'XP 10 Heures','Boost 0','boost0.png','Double votre génération d\'expérience pendant 10 heures !');
INSERT INTO Boost (id_Boost,typeBoost,nomBoost,imageMiniatureBoost,descriptionBoost) VALUES (1,'XP 10 Matchs','Boost 1','boost1.png','Double votre génération d\'expérience pendant 10 matchs !');



INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,id_Pack,typeOffre,misEnVente,imageOffre) VALUES (0,1,2,1,'Pack',1,'pack1.png');
INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,id_Pack,typeOffre,misEnVente,imageOffre) VALUES (1,2,3,2,'Pack',0,'pack2.png');
INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,id_Pack,typeOffre,misEnVente,imageOffre) VALUES (2,3,4,3,'Pack',0,'pack3.png');

INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,typeOffre,misEnVente,imageOffre) VALUES (3,10,1,'Boost',1,'boost1.png');
INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,typeOffre,misEnVente,imageOffre) VALUES (4,11,2,'Boost',1,'boost2.png');
INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,typeOffre,misEnVente,imageOffre) VALUES (5,12,3,'Map',1,'map1.png');
INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,typeOffre,misEnVente,imageOffre) VALUES (6,13,4,'Carton',1,'carton1.png');
INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,typeOffre,misEnVente,imageOffre) VALUES (7,1,1,'Icone',1,'superIcone.png');

INSERT INTO OffreBoost (id_Offre,id_Boost) VALUES (3,0);
INSERT INTO OffreBoost (id_Offre,id_Boost) VALUES (4,1);
INSERT INTO OffreMap (id_Offre,id_SkinMap) VALUES (5,1);
INSERT INTO OffreCartonCarte (id_Offre,id_SkinCartonCarte) VALUES (6,1);
INSERT INTO OffreIcone (id_Offre,id_IconeJoueur) VALUES (7,1);


INSERT INTO Ensemble(id_Ensemble,id_Carte,nomEnsemble) VALUES (0,5,'Ensemble0');
INSERT INTO Ensemble(id_Ensemble,id_Carte,nomEnsemble) VALUES (0,6,'Ensemble0');
INSERT INTO Ensemble(id_Ensemble,id_Carte,nomEnsemble) VALUES (0,7,'Ensemble0');

INSERT INTO Ensemble(id_Ensemble,id_Carte,nomEnsemble) VALUES (1,0,'Ensemble1');
INSERT INTO Ensemble(id_Ensemble,id_Carte,nomEnsemble) VALUES (1,8,'Ensemble1');

INSERT INTO Ensemble(id_Ensemble,id_Carte,nomEnsemble) VALUES (2,2,'Ensemble2');
INSERT INTO Ensemble(id_Ensemble,id_Carte,nomEnsemble) VALUES (2,12,'Ensemble2');

INSERT INTO Ensemble(id_Ensemble,id_Carte,nomEnsemble) VALUES (3,1,'Ensemble3');
INSERT INTO Ensemble(id_Ensemble,id_Carte,nomEnsemble) VALUES (3,10,'Ensemble3');
INSERT INTO Ensemble(id_Ensemble,id_Carte,nomEnsemble) VALUES (3,11,'Ensemble3');


INSERT INTO LootPack (id_LootPack, id_Ensemble,dropRatePack,nomLootPack) VALUES (1,0,40,'LootPack0');
INSERT INTO LootPack (id_LootPack, id_Ensemble,dropRatePack,nomLootPack) VALUES (1,1,60,'LootPack0');
INSERT INTO LootPack (id_LootPack, id_Ensemble,dropRatePack,nomLootPack) VALUES (2,2,10,'LootPack1');
INSERT INTO LootPack (id_LootPack, id_Ensemble,dropRatePack,nomLootPack) VALUES (2,3,90,'LootPack1');




INSERT INTO LootPackPack (id_LootPack,id_Pack,qteCartePack) VALUES (1,1,9);
INSERT INTO LootPackPack (id_LootPack,id_Pack,qteCartePack) VALUES (2,1,1);
