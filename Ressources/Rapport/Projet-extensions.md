1. Il y a trois classes abstraites qui permettent à de futures extensions d'y prendre racines. Celles ci pourront ré-utiliser la possibilité de fermer les jetons (à partir de *CA_Grille_Partie_Fermeture*) ou non (*CA_Grille_Partie*).

2. Les extensions pourront être lancer à partir de l'*Appli* du moment qu'elles implementent l'interface *In_Partie* qui comprend uniquement la méthode *lancerPartie()*.

3. La réutilisation des méthodes d'évaluation (package *utilitaires*) et celles du choix securisé de cellule (package *interaction*) de la grille est possible dans d'autres extensions si elles sont issues de *CA_Grille*.

4. Si l'on souhaite jouer avec un 3ème joueur il faudrait :

+ dans la *CA_Grille_Partie* :
	+ utiliser la méthode `public Joueur aQuiLeTour() {return ((tour % 2 == 0) ? joueur2 : joueur1);}` pour la définition `Joueur joueurActuel = aQuiLeTour();`
+ dans *Partie3J.java* :
	+ surcharger la méthode *aQuiLeTour()*
	+ rajouter dans les attribut de : `private Joueur joueur3`
+ dans l'enumération *Jeton* :
	+ rajouter un symbole d'un autre caractère 

5. Comme la *CA_Grille_Partie*, comporte la méthode `jouerCoup(Joueur joueurActuel)`, si l'on souhaite faire une extension de chacune des partes avec une *IA*, il faudrait :

+ qu'elles etendent leur partie 2 joueurs physiques respectives
+ qu'elles surcharge la méthode *jouerCoup(Joueur joueurActuel)* en choisissant si c'est le joueur physique a le jeton 'X' ou 'O' et auqel cas utiliser la méthode `super.jouerCoup(joueurActuel);` ou celle de l'*IA*.
