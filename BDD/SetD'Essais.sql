INSERT INTO SkinMap (id_SkinMap,imageMiniatureMap,descriptionMap) VALUES (0,'/map1.png','Map0');
INSERT INTO SkinMap (id_SkinMap,imageMiniatureMap,descriptionMap) VALUES (1,'/map1.png','Map1');

INSERT INTO SkinCartonCarte (id_SkinCartonCarte, descriptionCarton, imageMiniatureCarton) VALUES (0, 'Carton6','/carton1.png');

INSERT INTO IconeJoueur (id_IconeJoueur,imageIcone) VALUES (0,'/icone0.png');


INSERT INTO ModeDeJeu (id_ModeDeJeu,nomModeDeJeu,descriptionModeDeJeu) VALUES (0,'Classique','C''est un mode de jeu trop bien, essayez-le (et payez)');

#INSERT INTO Matchs (id_ModeDeJeu,finMatch,Pseudo1,Pseudo2) VALUES (0,NULL,'mouloud','morice');
#INSERT INTO Matchs (id_ModeDeJeu,finMatch,Pseudo1,Pseudo2) VALUES (0,NULL,'mouloud','morice');
#INSERT INTO Matchs (id_ModeDeJeu,finMatch,Pseudo1,Pseudo2) VALUES (0,NULL,'moktar','morice');
#INSERT INTO Matchs (id_ModeDeJeu,finMatch,Pseudo1,Pseudo2) VALUES (0,NULL,'morray','moktar');



INSERT INTO Carte (id_Carte,nomCarte,typeCarte,descriptionCarte,coutCarte) VALUES (10,'WhereIsMyTeam?','Sort','Vous n\'avez pas de team ? Pas de problème, on vous fournit ça',20);
INSERT INTO Carte (id_Carte,nomCarte,typeCarte,descriptionCarte,coutCarte) VALUES (11,'JféDéRouAriairDanLeQDeTaMer','Sort','VroumVroum',5);
INSERT INTO Carte (id_Carte,nomCarte,typeCarte,descriptionCarte,coutCarte) VALUES (12,'Victoire Dou Blazil','Sort','Vous invoquez Leonardo l\'interdit, qui invoque le carnaval Dou Blazil.',100);



INSERT INTO Pack (id_Pack,nomPack,descriptionPack,imageMiniaturePack) VALUES (0,'Pack 0','Le premier pack','/pack1.png');
INSERT INTO Pack (id_Pack,nomPack,descriptionPack,imageMiniaturePack) VALUES (1,'Pack 1','Le deuxième pack','/pack2.png');
INSERT INTO Pack (id_Pack,nomPack,descriptionPack,imageMiniaturePack) VALUES (2,'Pack 2','Le troisième pack','/ipack3.png');

INSERT INTO Boost (id_Boost,typeBoost,imageMiniatureBoost,descriptionBoost) VALUES (0,'Boost0','Boost0','/iboost1.png');
INSERT INTO Boost (id_Boost,typeBoost,imageMiniatureBoost,descriptionBoost) VALUES (1,'Boost1','Boost1','/boost2.png');



INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,id_Pack,typeOffre) VALUES (0,1,2,0,'Pack');
INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,id_Pack,typeOffre) VALUES (1,2,3,1,'Pack');
INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,id_Pack,typeOffre) VALUES (2,3,4,2,'Pack');

INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,typeOffre) VALUES (3,10,1,'Boost');
INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,typeOffre) VALUES (4,11,2,'Boost');
INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,typeOffre) VALUES (5,12,3,'Map');
INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,typeOffre) VALUES (6,13,4,'Carton');
INSERT INTO Offre (id_Offre,prixMonnaieIG,prixMonnaieIRL,typeOffre) VALUES (7,1,1,'Icone');

INSERT INTO OffreBoost (id_Offre,id_Boost) VALUES (3,0);
INSERT INTO OffreBoost (id_Offre,id_Boost) VALUES (4,1);
INSERT INTO OffreMap (id_Offre,id_SkinMap) VALUES (5,0);
INSERT INTO OffreMap (id_Offre,id_SkinMap) VALUES (6,1);
INSERT INTO OffreCartonCarte (id_Offre,id_SkinCartonCarte) VALUES (6,0);
INSERT INTO OffreIcone (id_Offre,id_IconeJoueur) VALUES (7,0);


INSERT INTO LootPackPack (id_LootPack,id_Pack,qteCartePack,nomLootPack) VALUES (0,0,9,'LootPack0');
INSERT INTO LootPackPack (id_LootPack,id_Pack,qteCartePack,nomLootPack) VALUES (1,0,1,'LootPack1');

INSERT INTO EnsembleCarte(id_Ensemble,id_Carte) VALUES (0,5);
INSERT INTO EnsembleCarte(id_Ensemble,id_Carte) VALUES (0,6);
INSERT INTO EnsembleCarte(id_Ensemble,id_Carte) VALUES (0,7);

INSERT INTO EnsembleCarte(id_Ensemble,id_Carte) VALUES (1,0);
INSERT INTO EnsembleCarte(id_Ensemble,id_Carte) VALUES (1,8);

INSERT INTO EnsembleCarte(id_Ensemble,id_Carte) VALUES (2,2);
INSERT INTO EnsembleCarte(id_Ensemble,id_Carte) VALUES (2,12);

INSERT INTO EnsembleCarte(id_Ensemble,id_Carte) VALUES (3,1);
INSERT INTO EnsembleCarte(id_Ensemble,id_Carte) VALUES (3,10);
INSERT INTO EnsembleCarte(id_Ensemble,id_Carte) VALUES (3,11);

INSERT INTO LootPackEnsemble (id_LootPack, id_Ensemble,dropRatePack,nomEnsemble) VALUES (0,0,40,'Ensemble0');
INSERT INTO LootPackEnsemble (id_LootPack, id_Ensemble,dropRatePack,nomEnsemble) VALUES (0,1,60,'Ensemble1');
INSERT INTO LootPackEnsemble (id_LootPack, id_Ensemble,dropRatePack,nomEnsemble) VALUES (1,2,10,'Ensemble2');
INSERT INTO LootPackEnsemble (id_LootPack, id_Ensemble,dropRatePack,nomEnsemble) VALUES (1,3,90,'Ensemble3');
