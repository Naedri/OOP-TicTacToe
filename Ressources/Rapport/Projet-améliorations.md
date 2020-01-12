1. Pour le point 6. de la partie Difficultés, ce qui aurait du être fait c'est : 
	1. soit
+ incorporer ces méthodes au sein de la classe TTT,
+ faire du M une classe fille du TTT,
+ et obtenir les caractères des jetons non pas avec la méthode *getCellule(i,j).getJeton()* mais avec une méthode *getSymbole(i,j)* (renvoyant X ou O dans TTT) qui aurait ensuite été surchargée (renvoyant X,x,O ou O dans M).
	2. ou plutot
+ definir une méthode *public int[][][] getCoordAlignJeton(ligne,colonne,profondeur)* qui pour la cellule donnée (ligne,colonne) renvoie *[q][p][k]* pour tous les *q* alignements de taille *p* (constitués de *p*) cellulles aux coordonnées *k*.
+ utiliser cette méthode dans l'appli M mais evaluer pour chacun des alignements si il comprend un jeton ferme, afin de determiner quel est le premier alignement sans jetons fermés.

2. La forme n est pas une classe de type enum, mais elle en a tout l'air : le constructeur contraint l'utilisateur a definir un nombre dans un intervalle pour définir une instanciation de cette classe (carré,losange,croix). Il faudrait donc repenser la méthode *transForme(n)* pour qu'elle fonctionne avec les classes d'énumération.

3. L'application *Forme* ne se termine pas quand un joueur a completé une forme donnée (*i.e* marque un point), alors qu'au vue des méthodes de TTT dont elle hérite (ex. *estFinie()*) ce devrait être le cas puisque celles-ci fonctionnent pour le TTT. De plus les méthodes qui reconnaissent si un jeton complete une forme donnée sont opérationnelles comme le montre les tests. 
Après une observation attentive lorsque *evaluerCoup()* evalue un point de forme complète, elle augmente le score d'un joueur, mais celui-ci ne semble pas être celui de *CA_Grille_Partie* car le score de ce dernier reste à 0. 

4. La partie TF dépend de TTT, ainsi dès qu'une forme est réalisée elle s'arrête.
Si l'on souhaite faire une partie de M avec des formes il faudrait que TF hérite de M, et réecrire les méthodes pour qu'elles prennent en compte l'état d'ouverture des jetons.
Il a été réalise une externalisation de ces méthodes dans le but de limiter l'héritage de méthode non utilisée.
Récrire du code est contraire à la POO, ainsi l'externalisation de l'évalutation de la grille ne semble pas une bonne idée.


5. Avec la méthode de déplacement relatif toutes les formes ne peuvent être réalisée. En effet il y a des angles morts : par exemple d'une case en 3-3 ne peut pas rejoindre une case en 1-2 directement.
