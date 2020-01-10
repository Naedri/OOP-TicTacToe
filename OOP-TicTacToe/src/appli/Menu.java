//****************************************************************
//Menu.java
//****************************************************************
package appli;

import java.util.Scanner;

import partie.CA_Grille_Partie;
import partie.Forme;
import partie.PartieForme;
import partie.PartieMorpion;
import partie.PartiePermutation;
import partie.PartieTicTacToe;

public class Menu {

	/**
	 * Securise la selection du choix de la partie ou Securise la selection de la
	 * taille de la grille
	 * 
	 * @param borneMin choixMinimale autorsé
	 * @param borneMax choixMaximum autorsé
	 */
	public static int setChoix(int borneMin, int borneMax) {
		boolean saisieCorrecte = false;
		int nombreChoisie = borneMin;
		while (!saisieCorrecte) {

			Scanner sc = new Scanner(System.in);
//			System.out.println("Veuillez choisir un nombre entre " + borneMin + " et " + borneMax + " compris.\nPuis appuyez sur \'Entree\'.\n");
			String saisie = sc.nextLine();
			System.out.println("Vous avez tape : " + saisie + ".\n");

			try {
				if (saisie.matches("\\s*\\d+\\s*")) {
					nombreChoisie = Integer.parseInt(saisie);
					if (nombreChoisie >= borneMin && nombreChoisie <= borneMax) {
						// sortie de la boucle
						saisieCorrecte = true;
					} else
						System.out.println("La saisie est incorrecte, recommencez.\n");
				} else
					System.out.println("La saisie est incorrecte, recommencez.\n");
			}

			catch (java.lang.NumberFormatException e1) {
				System.out.println("Le format est invalide, recommencez.\n");
			}
		}
		return nombreChoisie;
	}

	public static void affichageMenuPrincipal() {
		System.out.println("Bienvenue dans le jeu TicTacToe et ses variantes.\n\n");
		System.out.println("<<< MENU PRINCIPAL >>>\n");
		System.out.println("Veuillez tapez :");
		System.out.println("< 1 > Pour afficher les regles.");
		System.out.println("< 2 > Pour afficher des informations sur ce projet.");
		System.out.println("< 3 > Pour jouer.");
		System.out.println("< 4 > Pour quitter.");

		int choix;
		choix = setChoix(1, 4);

		switch (choix) {
		case 1:
			System.out.println("<<< REGLES >>>\n");
			affichageMenuRegles();
			break;
		case 2:
			System.out.println("<<< INFORMATIONS >>>\n");
			affichageMenuInfo();
			break;
		case 3:
			System.out.println("<<< JOUEZ ! >>>\n");
			affichageMenuJeu();
			break;
		case 4:
			System.out.println("Au revoir.\n");
			break;
		default:
			System.out.println("Choix de menu invalide.\n");
			break;
		}
		if (choix != 4) {
			System.out.println("Retour au Menu Principal.\n");
			affichageMenuPrincipal();
		}
	}

	private static void affichageMenuRegles() {
		System.out.println("\nLe Tic-Tac-Toe.");
		System.out.println("Le Tic-Tac-Toe, aussi appelé Morpion et OxO en Belgique, est un jeu de réflexion se\n"
				+ "pratiquant à deux joueurs au tour par tour dont le but est de créer le premier un alignement. Nous\n"
				+ "commençons par le jeu dans sa forme la plus simple.\n"
				+ "Le jeu se joue sur une grille de 3 × 3. Deux joueurs s’affrontent. Ils doivent remplir chacun à\n"
				+ "leur tour une case de la grille avec le symbole qui leur est attribué : O ou X. Le gagnant est celui qui\n"
				+ "arrive le premier à aligner trois symboles identiques, horizontalement, verticalement ou en diagonale.\n"
				+ "La partie est nulle si toutes les cases sont occupées et qu’aucun joueur n’a réalisé un alignement. Il\n"
				+ "est coutume de laisser le joueur jouant X effectuer le premier coup de la partie.");
		System.out.println("\nLe Morpion.");
		System.out.println(
				"ans le jeu de Morpion, les grilles ont une taille quelconque. Les règles du jeu sont modifiées\n"
						+ "comme suit.\n"
						+ "• La partie ne se termine plus au premier alignement mais continue en alternant les coups des\n"
						+ "deux joueurs jusqu’à ce que toutes les cases soient occupées.\n"
						+ "• Un joueur ne peut poser un symbole que sur une case étant adjacente (horizontalement, verti-\n"
						+ "calement ou en diagonale) à une case déjà occupée. Au premier coup, le placement est libre.\n"
						+ "• Un même symbole ne peut compter que pour un alignement. Dès qu’un alignement est formé,\n"
						+ "les symboles qui le composent ne peuvent plus concourir à la réalisation d’un autre alignement.\n"
						+ "Ces symboles sont dits être fermés. Les symboles non encore fermés sont dits être ouverts.\n"
						+ "• En fin de partie, le joueur ayant fait le plus d’alignements gagne. La partie est nulle en cas\n"
						+ "d’égalité.");
		System.out.println("\nLe Tic-Tac-Toe extension forme.");
		System.out
				.println("Ce n’est plus des alignements qu’il faut faire mais des formes particulières (une croix par\n"
						+ "exemple).");
		System.out.println("\nLe Tic-Tac-Toe extension permutation.");
		System.out.println(
				"La grille est initialement remplie aléatoirement d’autant de X que de O (plus un X ou un O choisi\n"
						+ "lui aussi aléatoirement si le nombre de cases est impair). Un coup ne consiste plus à déposer\n"
						+ "un symbole mais à permuter un X avec un O. Les symboles permutés peuvent être ouverts ou\n"
						+ "fermés. Un point est remporté si cette permutation conduit à réaliser une forme particulière\n"
						+ "de symboles ouverts (un alignement de 3 symboles, par exemple). Le joueur qui remporte le\n"
						+ "point ne dépend pas de qui joue le coup mais du symbole composant la forme. Si c’est un X\n"
						+ "(resp. O), le point est remporté par le joueur X (resp. O). Ainsi, une même permutation peut\n"
						+ "conduire à augmenter le score des deux joueurs.");
	}

	private static void affichageMenuInfo() {
		System.out.println("Auteur : Adrien JALLAIS - adrien.jallais@protonmail.com\n");
		System.out.println("Etablissement : IUT Paris Descartes.\n");
		System.out.println("Diplome prepare : DUT Annee Speciale.\n");
		System.out.println("Sujet propose par : POITRENAUD Denis.\n");
		System.out.println("Lien GitHub : < https://github.com/Naedri/OOP-TicTacToe.git > \n");
		System.out.println("Version 1.0.\n");
	}

	private static void affichageMenuJeu() {
		int choixJeu;

		System.out.println("Veuillez tapez :");
		System.out.println("< 0 > Pour revenir au Menu Principal");
		System.out.println("< 1 > Pour jouer au TicTacToe.");
		System.out.println("< 2 > Pour jouer au Morpion.");
		System.out.println("< 3 > Pour jouer au TicTacToe extension Forme.");
		System.out.println("< 4 > Pour jouer au TicTacToe extension Permutation.");
		choixJeu = setChoix(0, 4);

		if (choixJeu != 0) {
			int choixGrilleLigne;
			int choixGrilleColonne;
			int choixNbrAlignements = 3;
			int choixForme = 1;
			Forme formeChoisie = null;

			if (choixJeu == 3) {
				// taille grille
				choixGrilleLigne = 12;
				choixGrilleColonne = 12;

				System.out.println("La grille sera composee de " + choixGrilleLigne + " lignes et " + choixGrilleColonne
						+ "colonnes.");

				// forme choix
				System.out.println("Determinons la forme  qui permettra de marquer un point.");
				System.out.println(Forme.toStringFormeDispoConsigne());
				System.out.println("Veuillez choisir une forme pas son indice, entre 1 et "
						+ Forme.getListFormesDispo().length + " compris.");
				choixForme = setChoix(1, Forme.getListFormesDispo().length);
				formeChoisie = new Forme(choixForme);
				System.out.println(formeChoisie.toStringFormeChoisie());
			} else {
				// taille grille
				System.out.println("Determinons la taille de la grille surlaquelle vous allez jouer.");
				System.out.println("Combien de lignes souhaitez vous ?");
				System.out.println("Veuillez choisir un nombre entre 3 et 12 compris");
				choixGrilleLigne = setChoix(3, 12);
				System.out.println("Combien de colonnes souhaitez vous ?");
				System.out.println("Veuillez choisir un nombre entre 3 et 12 compris");
				choixGrilleColonne = setChoix(3, 12);

				System.out.println("La grille sera composee de " + choixGrilleLigne + " lignes et " + choixGrilleColonne
						+ "colonnes.");

				// taille alignement
				System.out.println("Determinons la taille de l'alignement qui permettra de marquer un point.");
				System.out.println("Combien de jetons alignes souhaitez vous ?");
				System.out.println("Veuillez choisir un nombre entre 3 et 5 compris.");
				System.out.println(
						"Attention ce nombre ne doit pas être plus grand que le nombre de colonnes ou de lignes de votre grille.");
				int choixNbrAlignMax = (choixGrilleColonne >= choixGrilleLigne) ? choixGrilleLigne : choixGrilleColonne;
				choixNbrAlignMax = (choixNbrAlignMax > 5) ? 5 : choixNbrAlignMax;
				choixNbrAlignements = setChoix(3, choixNbrAlignMax);

				System.out.println("Le nombre d alignement choisi est de " + choixNbrAlignements + " jetons.");

			}

			CA_Grille_Partie partie = null;

			switch (choixJeu) {
			case 1:
				System.out.println("La partie de TicTacToe va commencer, preparez-vous !\n");
				partie = new PartieTicTacToe(choixGrilleLigne, choixGrilleColonne, choixNbrAlignements);
				break;
			case 2:
				System.out.println("La partie de Morpion va commencer, preparez-vous !\n");
				partie = new PartieMorpion(choixGrilleLigne, choixGrilleColonne, choixNbrAlignements);
				break;
			case 3:
				System.out.println("La partie de TicTacToe Forme va commencer, preparez-vous !\n");
				partie = new PartieForme(choixForme);
				break;
			case 4:
				System.out.println("La partie de TicTacToe Permutation va commencer, preparez-vous !\n");
				partie = new PartiePermutation(choixGrilleLigne, choixGrilleColonne, choixNbrAlignements);
				break;
			}

			partie.lancerPartie();
		}
	}
}
