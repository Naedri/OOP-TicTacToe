// plan.md

# OOP-TicTacToe

## Première Application - Tic Tac toe

| Classe | But | Definition |
| :--- | :--- |:--- |
|Grille | Contenir les cases vides ou choix Joueurs du plateau | Tableau de Caractère de taille 3*3*|
| Jeton | Indiquer choix Joueur | Caractère "X" ou "O" |
| Interface | Affichage <br> - de la grille <br> - de la saisie de clavier | Grille <br> Message |
| Jeu | Verification : <br> - des Victoires <br> - de la mise en place des jetons | |

### Classes

#### Grille et Jeton
+ grile 
	+ taile 3*3
	+ diponible case
	+ case  occupé
+ jeton 
	+ X ou O
	+ compteur de nombre de jeton deposé ? bof plutit compter le nmbre de case de la grille directement

#### Interface 

+ Affichage Grille

+ Affichage Mesages
	+ cas1
		+ Tour du joueur
		+ Evaluation du message saisie (validation ou rejet)
	+ cas2 
		+ Match resultat (nulle ou victoire)

#### Jeu

+ Verification
	+ 	des victoires  (premier alignement)
		+ 	nmbre de jeton aligner
	+ de la mise en place des jetons


## Seconde Application - Tic Tac Toe + Morpion

| Classe | But | Definition |
| :--- | :--- |:--- |
|Grille | Contenir les cases vides ou choix Joueurs du plateau | Tableau dynamique de Caractère |
| Jeton | Indiquer choix Joueur | Caractère "x" ou "o" puis "X" ou "O" |
| Interface | Affichage <br> - de la grille <br> - de la saisie de clavier | Grille <br> Message |
| Jeu | Verification : <br> - des Victoires <br> - de la mise en place des jetons | |
| Menu | Choix du Morpion ou TIC TAC TOE |choix utilisateur en entrant 1 chiffre |

### Classes

#### grille
taille dynamique pas forcement carré

#### jeton
booleant pour indiquer si appartient à un alignement
Jeton : Caractère "x" ou "o" puis "X" ou "O" si alignement

#### interface
dynamiquement changer l apparence de jeton x voire X ou o voire O

#### Jeu

+ Verification
	+ de la mise en place du jeton
		+ premier = libre
		+ suivant = adjacent (peu importe le symbole adjacent)
+ Denombrement des points
	+ au fur et à mesure ou en fin de partie ?
		+ au fur et à mesure car les jetons doivent changer de forme
		+ au fur et a mesure car si taille de grielle grande peut prendre du temps à la fin 
	+ si plusieurs aligements impliques lequel choisir ?
		+ un seul
		+ les deux 

#### Menu

1. Accueil
2. Regles
3. Jouer
	1. Retour
	2. MORPION
	3. TIC TAC TOE 
		1. Determiner taille grille
			+ largeur 
			+ longueur

## Troisieme Application - Tic Tac Toe + Morpion + Bazar

| Classe | But | Definition |
| :--- | :--- |:--- |
|Grille | Contenir les cases vides ou choix Joueurs du plateau <br> remplissage aléatoire possible | Tableau dynamique de Caractère |
| Jeton | Indiquer choix Joueur | Caractère "x" ou "o" puis "X" ou "O" |
| Interface | Affichage <br> - de la grille <br> - de la saisie de clavier | Grille <br> Message |
| Jeu | Verification : <br> - des Victoires <br> - de la mise en place des jetons <br> permutations | |
| Menu | Choix du Morpion ou TIC TAC TOE ou Bazar |choix utilisateur en entrant 1 chiffre |
| Forme de victoire | sauvegarder tampon  coller decoller sur la grille pour verification de victoire |coordonnées ou tampon ? |
| Joueur | sauvegarder nom et statistiques |binair file|


#### grille

taille dynamique pas forcement carré

#### joueur

pour permettre l extenion de notre appli
la sauvegarde de victoire et de pattern de forme de victoire

#### Menu

1. Accueil
2. Regles
3. Jouer
	1. Retour
	2. MORPION
		+ Determiner taille grille
			+ largeur 
			+ longueur
		+ Determiner nombre de jeton à aligner

	3. TIC TAC TOE 
		+ Determiner taille grille
			+ largeur 
			+ longueur
		+ Determiner nombre de jeton à aligner
		+ Joueur nom saisie
	4. Bazar
		+ Determiner taille grille
			+ largeur 
			+ longueur
		+ Joueur nom saisie  
		+ choix forme
			+ joueur1.forme choix + phrase memoi
			+ commune ?
				+ si oui -> joueur1.forme = joueur2.forme
				+ si non -> joueur2.forme choix + phrase memo
		+ choix jeu
			1. normale
			2. permutation


#### forme de victoire

+ sauvegarder tampon / pattern de victoire dans fichier binaire ?
+ taille a determiner par l utilisateur

+ taille doit être commune
	+ non pas forcement
+  forme ne doit pas etre la meme

+ coller decoller sur la grille pour verification de victoire 
+ coordonnées ou tampon ?


#### grille


#### jeu
+ permutation des jetons possibles
	+ une permutation peut elle avoir lieu avec un jeton pour lequel une victoire a été comptabilise ?
+ remplissage aléatoire
	+ verification que la forme n est pas deja faite oour un jeton donnée si c est le cas changement du symbole pour un autre
	+ 
