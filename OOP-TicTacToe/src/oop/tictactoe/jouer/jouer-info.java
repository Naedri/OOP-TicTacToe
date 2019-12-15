package oop.tictactoe.jouer;

/*
 * package contenant les élements 
 * 	+ necessaires, 
 * 	+ communs, 
 * pour permettre aux applis de lancer une partie
 * 
 * il y a : 
 * 	+ Interface.java : 
 * 		+ permet de selectionner de manizre securisee une cellule
 * 		+ contient les messages necessaires à l'interface dans les differentes appli
 * 			+ messageTour s affiche a chaque debut de tour indiquant quel joueur doit placer le prochain jeton
 * 			+ messageResultat s affiche si la partie est terminee (cad si mvictoire ou match nul)
 * 			+ messageCellule s'affiche à chaque jeton place indiquant quelle cellule a été choisie
 * 	+ Joueur.java : POTENTIELLEMENT A ENLEVER CAR marquerPoint est une forme de Setteur public alors qu il faudrait qu il ne soit appelle que quand il marque un point
 * 		+ genere des joueurs qui peuvent jouer un type de jeton est un seul
 * 		+ enregistre leur score
 * 	+ Match.java :  POTENTIELLEMENT A ENLEVER car on fait pas un match mais un match de morpion ou un match de tictactoe etc. ET on doit mettre un setteur pour la variable victoire
 * 		+ enregistre le nombre de tour
 * 		+ determine si le nombre de tour max est atteint pour indiquer un match nul
 * 	+ UNE INTERFACE SERA CREE POUR TOUT NOUVEAU COUP / POUR CHAQUE TOUR
 * 
 */
