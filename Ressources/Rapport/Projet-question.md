question
1. Dois je definir une interface jouerCoup ?
+ non car le prototype ne serait pas constant entre les classes (permuter prend deux jetons alors que TTT prend un jeton)
2. SI je veux etendre le jeux quelles classes abstraites ou interfaces dois je modifier ?

3. on a une classe grille qui possede placer jeton mais cette méthode doit varier en fonction du jeu donc on place la grille dans l appli directement.

4.la classe joueur doit saisir une cellule

5. je souhaite factoriser le code realiser dans ma classe grille
il y a des méthodes générales (getColonnes ou getLignes) et des méthodes spécifique et propres à une appli (permutationJeton)  ou plusieurs appli (placeJeton mais placerJeton n est pas le meme dans les differentes appli : c est le meme dans l appli 1 et 3.1 càd taille indefinie de pions à aligner, et c est different dans appli 2.)
je ne souhaite pas que l appli 3 permutation dispose de la méthode placerJeton

6.jouerCoup doit etre dans l interface Tour car jouerCoup doit etre fonction de la grille qui elle est dans Partie.
Une partie est composée de Tour.

7. Doit il y avoir une interface de forme ?
Oui car notre methode parcoursForme fonctionne et est optimise pour les 3formes données : il est possible de rejoindre tous les points sans passer deux fois par le meme point, mais quand est il d une autre forme (comme le 5 d un dé)

8.Je n ai pas fait d enum pour les forme car on ne couvre pas tous les cas (mon imagination ne peut pas trouver tous les cas), donc il faut une interface.


9. dans Forme.Java la realisation du grilleModel aurait pu etre mieux faite (automatiquement par parcoursForme)

10. le Tour_Forme depend du tictactoe(placer jeton pas forcement adjacent) et du morpion (placer jeton adjacent) car selon moi si on veut faire des formes pour lesquels ils n y a pas que des jetons adjacents on ne pourra pas donc l utiliser.


11. je souhaite definir des méthodes privés pour ne pas qu elles soient heritées mais je souhaite tout de même les tester, comment faire ?
dois modifier leur attribut 
how to test private methods ?
https://stackoverflow.com/questions/34571/how-do-i-test-a-private-function-or-a-class-that-has-private-methods-fields-or
et je  ne souhaite pas definir les autres fonctions comme static pour les mettre dans une interface afin qu elle soit heritable

12. j ai une méthode dans joueur Marquer point qui est public, est ce dangeureux ?


13. pour l appli permutation, j ai voulu faire des clones en faisant new,
aurait il fallu changer directement les jetons en creant une méthode

14. definir les attributs des parties (joueur, grille) comme protected au lieu de private.

15. linked list pour la liste des jetons

16. faudrait il mettre a null la saisie cellule apres l avoir evalue ?

17. dans tourMorpion on doit utiliser une partieMorpion car on a besoin de la table des jetonsFermes

18.doit on avoir une classe joueur, on pourrait avoir une table de score ? mais le si on veut plusieurs joueurs, cela securise d avoir des jetons differents.

19. il ne semble pas pertinent de faire heriter le morpion du tic tac toe car pour definir la classe abstraite jeu grille il faut definir un nombre de tour max et nombre de point max, ils different du tic tac toe et du morpion

20. il y a des angles mort pour nos methodes forme 