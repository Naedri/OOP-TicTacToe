1. Grille :
	Dois je avoir une classe grille ? 
Jusqu'au début du développement de la MP, il y avait une classe *Grille*. En effet le placement de symbole etait securise par la verification que la cellule etait vide. Mais dans la partie de MP, cette verification n etait pas possible, j'ai trouve que la grille devait etre tellement des-encapsulé, que j'ai préfére la mettre dans la *CA_Grille*, au sommet de ma hierarchie.

2. Tours :
	Dois je avoir une fabrique de tour qui composerait ma partie de tours, permettant ainsi de si l on veut de faire une partie avec plusieurs types de tour possible ? 
Ce travail a été initié par la création d'une interface afin d'obliger les classes des tours d'implementer les méthodes *jouerCoup()* et *evaluerCoup()*. Cependant comme celles-c étaient pas dans un package differents (tours) et que les informations nécessaires n'étaient pas les mêmes cela impliquait d'avoir des méthodes dont les prototypes différaient. D'autant plus que l'externalisation des tours impliquait des cast de redondant, il a été décidé d'incorporer les méthodes des tours dans les classes des parties directement.

3. Forme : 
	1. Enumeration non possible avec la methode *transForme(n)*, qui decale le chemin deplacement relatif de *getJeton()*, afin d observer si un point vient completer une forme par n importe quel point.
	2. les déplacements relatifs utilisent les valeurs énumérées de la classe Direction n'ont pas pas leur nom mais leur indice, ce qui nous permet de constituer un tableau d'entier.

4. Jetons : 
	Pour eviter que la grille de M hérite de données qu'elle ne doit pas utiliser, il a fallu limiter les jetons a deux valeurs (X,O) et non 4 (X,x,O,O). Ainsi il a été mise en place une table de boolean pour definir l'état d'ouverture des jetons.

5. Utilitaires : 
	J'ai souhaité limiter l'héritage de méthodes non utilisées par les applications. Ainsi si je definissais une méthode d'évaluation d alignement de jetons dans le TTT et le M je ne souhaitais pas qu'elle soit heritee dans le TF. J'ai donc definit des classes vides dans le package *Utilitaires*.

6. Factorisation de code :
	Pour le M, qui comprenait des jetons à ouvertures variables, il a fallu recopier les méthodes d'alignement élaborer par pour l'appli TTT mais en remplacant *getCellule(i,j).getJeton()* par *getCellule(i,j).getJetonOouF()*. En effet il a fallu remplacer le *getJeton*(X,O) par *getJetonOouF* (X,x,O,O). Comme le M utilise la classe Jeton, mais n'hérite pas de la classe Jeton une surcharge de la méthode n'est à ma connaissance pas utilisable.

7. Permutation : 
Trouver une manière efficace de mélanger la grille du jeu PM. J'ai donc mis les jetons sous une forme de LinkedList (car les accès aux élements terminaux sont constants) et utiliser *Collections.shuffle*.

8. Tests : 
J'ai souhaité definir des méthodes privés pour qu elles ne soient heritées mais je souhaite tout de même les tester ? Le lien suivant m'a montré une solution mais elle ne semble triviale : <https://stackoverflow.com/questions/34571/how-do-i-test-a-private-function-or-a-class-that-has-private-methods-fields-or>
Ainsi il a été décide de modifier leur attribut pour la réalisation des test. Ce n'est pas la bonne méthode car tous les test devraient être opérationnels en tout temps *t*.

9. Comment obliger que la méthode évaluer coup ne soit pas appeler plusieurs fois après le même placement de jeton, afin d'éviter qu'un joueur marque plusieurs points pour un même coup ? Peut-être faudrait il mettre à nulle la saisie cellule apres l avoir evalue ?

10. En C, nous avons pris l'habitude de mettre les données dont nous avions besoin en paramètre des nos fonctions. Ainsi au début du projet, lors de l'écriture de la classe abstraire *CA_Grille_Partie* il y avait la méthode suivante *evaluerCoup(Joueur j1, Joueur j2)*, or cela contraignant la visibilité de *j1* et *j2* à protected. Ainsi finalement il a été décidé le prototype suivant : **evaluerCoup()* et de mettre en place des getteurs.
