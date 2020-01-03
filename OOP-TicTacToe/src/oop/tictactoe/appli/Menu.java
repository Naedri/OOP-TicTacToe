package oop.tictactoe.appli;

import java.util.Scanner;

import oop.tictactoe.grille.Grille;

public class Menu {
	
	private int choixAccueil ;
	private int choixJouer ;
	
	private PartieTicTacToe partie ;
//	private PartieMorpion appliMorpion ;
//	private PartieXAlignements appliXAlignement ;
//	private PartieForme appliForme ;
//	private PartiePermutation appliPermutation ;
	
	public int getChoixAccueil() {
		return choixAccueil;
	}

	public int getChoixJouer() {
		return choixJouer;
	}

	/*
	 * Securise la selection du choix dans le Menu
	 */
	/**
	 * Securise la selection du choix dans le Menu
	 * @param choixAccueil
	 */
	private void setChoixAccueil(int choixAccueil) {
		this.choixAccueil = choixAccueil;
	}
	
	/**
	 * Securise la selection du choix de la partie
	 * @param borneMin choixMinimale autorsé
	 * @param borneMax choixMaximum autorsé
	 */
	public int setChoix(int borneMin, int borneMax) {
		boolean saisieCorrecte = false ;
		int nombreChoisie = borneMin;
		while ( !saisieCorrecte) {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Veuillez choisir un choix entre " + borneMin + " et " + borneMax + ".\nPuis appuyez sur \'Entree\'.\n");
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

	
	/*
	 * Composition du Menu
	 *  1. Afficher Regles
	 *  2. Afficher Auteurs
	 *  3. Jouer  
	 *  	1. Retour  
	 *  	2. MORPION   
	 *  		+ Determiner taille grille    
	 *  			+ largeur     
	 *  			+ longueur
	 *  	3. TIC TAC TOE    
	 *  		+ Determiner taille grille    
	 *  			+ largeur     
	 *  			+ longueur   
	 *  		+ Joueur nom saisie  
	 *  	4. Extension1 : XAlignements
	 *  		+ Determiner taille grille    
	 *  			+ largeur     
	 *  			+ longueur   
	 *  		+ Determiner nombre de jeton à aligner   
	 *  	5. Extension2 :  Forme  
	 *  		+ Determiner taille grille    
	 *  			+ largeur     
	 *  			+ longueur   
	 *  		+ Joueur nom saisie     
	 *  		+ choix forme    
	 *  			+ joueur1.forme choix 
	 *  			+ phrase memoi    
	 *  			+ commune ?     + 
	 *  				+ si oui -> joueur1.forme = joueur2.forme     
	 *  				+ si non -> joueur2.forme choix + phrase memo    
	 *  	6. Extension3 : permutation
	 */
	public void affichageMenu() {
		choixAccueil = 0;
		choixJouer = 0 ;
		
		if (choixAccueil == 3)
			
			if (choixJouer == 1)
				System.out.println("Retour au menu !\n");
			else {
//				PartieTicTacToe partie ; 
				
				if (choixJouer == 2) {
					System.out.println("La partie de TicTacToe va commencer, preparez-vous !\n");
					partie = new PartieTicTacToe();
				}
				if (choixJouer == 3) {
					System.out.println("La partie de Morpion va commencer, preparez-vous !\n");
					partie = new PartieMorpion();
				}
				if (choixJouer == 4) {
					System.out.println("La partie de TicTacToe XAlignements va commencer, preparez-vous !\n");
					partie = new PartieXAlignements();
				}
				if (choixJouer == 5)
					System.out.println("La partie de TicTacToe Forme va commencer, preparez-vous !\n");
					partie = new PartieForme();
				if (choixJouer == 6) {
					System.out.println("La partie de TicTacToe Permutation va commencer, preparez-vous !\n");
					partie = new PartiePermutation();
				}
				
				partie.lancerPartie() ;
			}

			affichageMenu();
	}
}
