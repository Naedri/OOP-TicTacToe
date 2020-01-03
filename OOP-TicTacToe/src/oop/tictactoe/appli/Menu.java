package oop.tictactoe.appli;

import java.util.Scanner;

import oop.tictactoe.grille.Forme;
import oop.tictactoe.grille.Grille;

public class Menu {
	
//	private PartieTicTacToe partie ;
//	private PartieMorpion appliMorpion ;
//	private PartieXAlignements appliXAlignement ;
//	private PartieForme appliForme ;
//	private PartiePermutation appliPermutation ;
	
	/**
	 * Securise la selection du choix de la partie
	 * ou
	 * Securise la selection de la taille de la grille 
	 * @param borneMin choixMinimale autorsé
	 * @param borneMax choixMaximum autorsé
	 */
	public int setChoix(int borneMin, int borneMax) {
		boolean saisieCorrecte = false ;
		int nombreChoisie = borneMin;
		while ( !saisieCorrecte) {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Veuillez choisir un nombre entre " + borneMin + " et " + borneMax + ".\nPuis appuyez sur \'Entree\'.\n");
			String saisie = sc.nextLine();
			System.out.println("Vous avez tape : " + saisie +".\n");
			
			try {
				if (saisie.matches("\\s*\\d+\\s*")) {
					nombreChoisie = Integer.parseInt(saisie);
					if (nombreChoisie >= borneMin && nombreChoisie <= borneMax) {
						//sortie de la boucle
						saisieCorrecte = true ;
						}
					else
						System.out.println("La saisie est incorrecte, recommencez.\n");
				}
				else
					System.out.println("La saisie est incorrecte, recommencez.\n");
			}
			
			catch(java.lang.NumberFormatException e1) {
				System.out.println("Le format est invalide, recommencez.\n");
			}
		}
		return nombreChoisie ;
}

	public void affichageMenuPrincipal() {
		int choix ;
		
		System.out.println("Bienvenue dans le jeu TicTacToe et ses variantes.\n\n");
		System.out.println("<<< MENU PRINCIPAL >>>\n");
		System.out.println("Veuillez tapez :");
		System.out.println("< 1 > Pour afficher les règles.");
		System.out.println("< 2 > Pour afficher des informations sur ce projet.");
		System.out.println("< 3 > Pour jouer.");
		
		choix = setChoix(1, 3);
		
		switch (choix) {
		case 1 :
			System.out.println("<<< REGLES >>>\n");
			System.out.println("Le Tic-Tac-Toe, aussi appelé Morpion et OxO en Belgique, est un jeu de réflexion se\n" + 
					"pratiquant à deux joueurs au tour par tour dont le but est de créer le premier un alignement. Nous\n" + 
					"commençons par le jeu dans sa forme la plus simple.\n" + 
					"Le jeu se joue sur une grille de 3 × 3. Deux joueurs s’affrontent. Ils doivent remplir chacun à\n" + 
					"leur tour une case de la grille avec le symbole qui leur est attribué : O ou X. Le gagnant est celui qui\n" + 
					"arrive le premier à aligner trois symboles identiques, horizontalement, verticalement ou en diagonale.\n" + 
					"La partie est nulle si toutes les cases sont occupées et qu’aucun joueur n’a réalisé un alignement. Il\n" + 
					"est coutume de laisser le joueur jouant X effectuer le premier coup de la partie.");
			break;
		case 2 :
			System.out.println("<<< INFORMATIONS >>>\n");
			System.out.println("Auteur : Adrien JALLAIS - adrien.jallais@protonmail.com\n");
			System.out.println("Etablissement : IUT Paris Descartes.\n");
			System.out.println("Diplome prepare : DUT Annee Speciale.\n");
			System.out.println("Sujet propose par : POITRENAUD Denis.\n");
			System.out.println("Lien GitHub : < https://github.com/Naedri/OOP-TicTacToe.git > \n");
			System.out.println("Version 1.0.\n");

			break;
		case 3 :
			System.out.println("<<< JOUEZ ! >>>\n");
			affichageMenuJeu();
			break;
		default:
			System.out.println("Choix de menu invalide.\n");
			break;
		}
		System.out.println("Retour au Menu Principal.\n");
		affichageMenuPrincipal();
	}
	
	public void affichageMenuJeu() {
		int choixJeu;
		
		System.out.println("Veuillez tapez :");
		System.out.println("< 0 > Pour revenir au Menu Principal");
		System.out.println("< 1 > Pour jouer au TicTacToe.");
		System.out.println("< 2 > Pour jouer au Morpion.");
		System.out.println("< 3 > Pour jouer au TicTacToe extension Forme.");
		System.out.println("< 4 > Pour jouer au TicTacToe extension Permutation.");
		
		choixJeu = setChoix(0,4);
		
		if (choixJeu != 0) {
			int choixGrilleLigne;
			int choixGrilleColonne ;
			int choixNbrAlignements;
			int choixForme = 1 ;
			
			if (choixJeu == 3) {
				//taille grille
				choixGrilleLigne = 12;
				choixGrilleColonne = 12 ;
				
				System.out.println("La grille sera composee de "+ choixGrilleLigne + " lignes et " + choixGrilleColonne + "colonnes.");

				//forme choix
				System.out.println("Determinons la forme  qui permettra de marquer un point.");
				System.out.println(Forme.toStringFormeDispoConsigne());
				System.out.println("Veuillez choisir une forme pas son indice, entre 1 et "+ Forme.getListFormesDispo().length + " compris.");
				choixForme = setChoix(1, Forme.getListFormesDispo().length);
				Forme formeChoisie = new Forme(choixForme);
				
				System.out.println(formeChoisie.toStringFormeChoisie());
			}
			else {
				//taille grille
				System.out.println("Determinons la taille de la grille surlaquelle vous allez jouer.");
				System.out.println("Combien de lignes souhaitez vous ?");
				System.out.println("Veuillez choisir un nombre entre 3 et 12 compris");
				choixGrilleLigne = setChoix(3, 12);
				System.out.println("Combien de colonnes souhaitez vous ?");
				System.out.println("Veuillez choisir un nombre entre 3 et 12 compris");
				choixGrilleColonne = setChoix(3, 12);
				
				System.out.println("La grille sera composee de "+ choixGrilleLigne + " lignes et " + choixGrilleColonne + "colonnes.");
				
				//taille alignement
				System.out.println("Determinons la taille de l'alignement qui permettra de marquer un point.");
				System.out.println("Combien de jetons alignes souhaitez vous ?");
				System.out.println("Veuillez choisir un nombre entre 3 et 5 compris.");
				System.out.println("Attention ce nombre ne doit pas être plus grand que le nombre de colonnes ou de lignes de votre grille.");
				int choixNbrAlignMax = (choixGrilleColonne >= choixGrilleLigne) ? choixGrilleLigne : choixGrilleColonne;
				choixNbrAlignements = setChoix(3, choixNbrAlignMax);
				
				System.out.println("Les nombre d alignement choisi est de "+ choixNbrAlignements +".");

			}
			
			PartieTicTacToe partie = null ; 
			
			switch (choixJeu) {
				case 1 :
					System.out.println("La partie de TicTacToe va commencer, preparez-vous !\n");
					partie = new PartieTicTacToe(choixGrilleLigne,choixGrilleColonne);
					break;
				case 2 :
					System.out.println("La partie de Morpion va commencer, preparez-vous !\n");
					partie = new PartieMorpion(choixGrilleLigne,choixGrilleColonne);
					break;
				case 3 :
					System.out.println("La partie de TicTacToe Forme va commencer, preparez-vous !\n");
					partie = new PartieForme(choixForme);
					break;
				case 4 :
					System.out.println("La partie de TicTacToe Permutation va commencer, preparez-vous !\n");
					partie = new PartiePermutation(choixGrilleLigne,choixGrilleColonne);
					break;
			}
			
			partie.lancerPartie() ;
		}
	}
}
