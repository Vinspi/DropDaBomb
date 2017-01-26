# Accès attribut (ex: mdp) du joueur $var

SELECT mdpCompte
FROM CompteJoueur
WHERE Pseudo = $pseudo;

# Accès division du joueur $var
SELECT Division.*
FROM CompteJoueur
  JOIN Division USING (id_Division)
WHERE Pseudo = $pseudo;


# Accès matchs (en cours) du joueur $var
SELECT *
FROM Matchs
WHERE Pseudo1 = $pseudo; #&& en_cours = true;


# Accès à l'historique du joueur $var
SELECT *
FROM Historiqueur
WHERE Pseudo = $joueur;


#Accès au mode de jeu d'un match
SELECT *
FROM ModeDeJeu
  JOIN Matchs USING (id_mdj)
WHERE id_match = $match;


#Accès à l'historique des achats d'un joueur
SELECT *
FROM HistoriqueAchat
WHERE Pseudo = $pseudo;

#Accès aux attributs d'une carte (ex : depuis le shop)
SELECT *
FROM Carte
WHERE id_Carte = $carte;


#Accès aux cartes possédées pour pouvoir les afficher dans l'inventaire.
SELECT Carte.*
FROM JoueurCarteDeck
  JOIN Carte USING (id_Carte)
WHERE Pseudo = $pseudo && id_deck = 0;  #deck 0 => deck null, ensemble de toutes les cartes possédées par le joueur.


#___________________________________________________________
#Création d'un nouveau compte
#___________________________________________________________

#Partie 1 : Vérification de l'unicité du pseudo dans la base
#___________________________________________________________
SELECT Pseudo
FROM CompteJoueur
WHERE Pseudo = $pseudo;

#Partie 2 : Insertion dans la DB en fonction du formulaire
#___________________________________________________________

#2.1 : CompteJoueur
INSERT INTO CompteJoueur (Pseudo, mailCompte, mdpCompte, niveauJoueur, nom_guilde, id_Division) VALUES ($pseudo, $mailCompte, $mdpCompte, 1, 0, NULL, NULL/*|| id_divisionDeBase*/);

#2.1.1 : Deck
INSERT INTO Deck VALUES (CONCAT($pseudo,0));
INSERT INTO Deck VALUES (CONCAT($pseudo,1));
INSERT INTO Deck VALUES (CONCAT($pseudo,2));
INSERT INTO Deck VALUES (CONCAT($pseudo,3));

#2.2 : Icones fournies à la création
INSERT INTO posséderIconeJoueur (Pseudo, id_IconeJoueur, activer) VALUES ($pseudo, id_IconeDeBase, 1);
INSERT INTO posséderIconeJoueur (Pseudo, id_IconeJoueur, activer) VALUES ($pseudo, id_IconeDeBase1, 0);
INSERT INTO posséderIconeJoueur (Pseudo, id_IconeJoueur, activer) VALUES ($pseudo, id_IconeDeBase2, 0);
INSERT INTO posséderIconeJoueur (Pseudo, id_IconeJoueur, activer) VALUES ($pseudo, id_IconeDeBase3, 0);
INSERT INTO posséderIconeJoueur (Pseudo, id_IconeJoueur, activer) VALUES ($pseudo, id_IconeDeBase4, 0);
#...

#2.3 : Maps fournies à la création
INSERT INTO posséderSkinMap (Pseudo, id_SkinMap, activer) VALUES ($pseudo, id_SkinMapDeBase, 1);
INSERT INTO posséderSkinMap (Pseudo, id_SkinMap, activer) VALUES ($pseudo, id_SkinMapDeBase1, 0);
INSERT INTO posséderSkinMap (Pseudo, id_SkinMap, activer) VALUES ($pseudo, id_SkinMapDeBase2, 0);
INSERT INTO posséderSkinMap (Pseudo, id_SkinMap, activer) VALUES ($pseudo, id_SkinMapDeBase3, 0);
#...

#2.4 : Carton de Cartes fournies à la création
INSERT INTO posséderSkinCartonCarte (Pseudo, id_SkinCartonCarte, activer) VALUES ($pseudo, id_SkinCartonCarteDeBase, 1);
INSERT INTO posséderSkinCartonCarte (Pseudo, id_SkinCartonCarte, activer) VALUES ($pseudo, id_SkinCartonCarteDeBase1, 0);
INSERT INTO posséderSkinCartonCarte (Pseudo, id_SkinCartonCarte, activer) VALUES ($pseudo, id_SkinCartonCarteDeBase2, 0);
#...

#2.5 : Cartes fournies à la création, -> soit on fournit 0 unité de chaque carte, et 1 de chaque unité des cartes de base -> soit on ne fournit que les cartes de bases.
# ->
INSERT INTO JoueurCarteDeck (qteCarte, id_deck, Pseudo, id_Carte) VALUES (1, 0, $pseudo, id_CarteDeBase0);
INSERT INTO JoueurCarteDeck (qteCarte, id_deck, Pseudo, id_Carte) VALUES (1, 0, $pseudo, id_CarteDeBase1);
INSERT INTO JoueurCarteDeck (qteCarte, id_deck, Pseudo, id_Carte) VALUES (1, 0, $pseudo, id_CarteDeBase2);
INSERT INTO JoueurCarteDeck (qteCarte, id_deck, Pseudo, id_Carte) VALUES (1, 0, $pseudo, id_CarteDeBase3);
INSERT INTO JoueurCarteDeck (qteCarte, id_deck, Pseudo, id_Carte) VALUES (1, 0, $pseudo, id_CarteDeBase4);
INSERT INTO JoueurCarteDeck (qteCarte, id_deck, Pseudo, id_Carte) VALUES (1, 0, $pseudo, id_CarteDeBase5);
INSERT INTO JoueurCarteDeck (qteCarte, id_deck, Pseudo, id_Carte) VALUES (1, 0, $pseudo, id_CarteDeBase6);
INSERT INTO JoueurCarteDeck (qteCarte, id_deck, Pseudo, id_Carte) VALUES (1, 0, $pseudo, id_CarteDeBase7);
#...


# -> Soit faire ça, soit le faire directement côté serveur en bouclant des INSERT INTO (probablement mieux/plus simple)
DELIMITER /
CREATE PROCEDURE init_carte()
  BEGIN

    DECLARE id_carte INT;
    DECLARE testC CURSOR FOR SELECT id_Carte FROM Carte;
    DECLARE done INT DEFAULT 0;
    OPEN testC;
    REPEAT
      FETCH testC INTO id_carte;
      IF NOT done THEN
        INSERT INTO JoueurCarteDeck (qteCarte, id_deck, Pseudo, id_Carte) VALUES  (0, 0, $pseudo, @id_carte);
      END IF;
    UNTIL done END REPEAT;

    CLOSE testC;
  END
    DELIMITER ;

CALL init_carte();


#___________________________________________________________
# A
#___________________________________________________________
