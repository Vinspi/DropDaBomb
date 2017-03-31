# DDB_NODE

Nouvelle version du site web (entièrement HTML/CSS/JS/NODEJS).
 
+ Ajout des modules Express.js et Jade. 

## Important : 

L'architecture à été changée et comprend 3 dossiers : 
- [web](web) : contient tous les fichiers html/js/css/img/fonts ( destiné a disparaitre, présent le temps du portage vers full js)
- [node](node) : contient le fichier serveur (serv.js) et le dossier des modules npm node
- [views](views) : contient l'ensemble des fichiers Jade (les fichiers "racines", les fichiers "feuilles" importés par la racine selon les différents cas, et un dossier "templates" qui contient des template à importer au besoin dans les fichiers racines/feuilles

Tous les fichiers dans views sont commentés afin d'expliciter leurs rôles

[localhost:8080/Account]("") : URL Source pour le moment, permet d'acceder a l'accueil qui va afficher/rediriger là ou la situation l'indique (voir views/Account.jade) 

[localhost:8080/clear]("") : URL utile pendant les phases de test, pour "re-initialiser le cookie" et simuler une "nouvelle" connexion 
