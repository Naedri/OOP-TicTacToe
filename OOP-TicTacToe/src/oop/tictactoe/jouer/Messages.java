package oop.tictactoe.jouer;

import oop.tictactoe.jouer.Match;
import oop.tictactoe.jouer.Joueur;
import oop.tictactoe.grille.*;

import java.util.Scanner;

public class Messages {
	
	/**
	 * saisieCellule input de l utilisateur sous format de table à 1 colonne 2 lignes
	 * messageResultat victoire ou non (càd "Continuez la partie!" ou "Le Joueur X a gagné la partie")
	 * messageTour indique qui doit jouer
	 * messagePermutation quel cellule de la grille ont été permutées
	 */
	
//	PROF VERSION		
//	public static void analyseSaisie(String s) {
//		if (s.matches("\\s*\\d+\\s*-\\s*\\d+\\s*")) {
//			Scanner sc = new Scanner(s);
//			sc.useDelimiter("\\s*-\\s*");
//			int ligne = sc.nextInt();
//			int colonne = sc.nextInt();
//			System.out.println("ligne = " + ligne + ", colonne = " + colonne);
//			sc.close();
//		}
//		else
//			System.out.println("Coup invalide");				
//	}
	
	/**
	 * saisieCellule sous forme de int[]
	 * saisieCellule[0] : ligne
	 * saisieCellule[1] : colonne
	 * definie la cellule saisie par l'utilisateur
	 * securise le choix de la cellule par l utilisateur l entrée de l utilisateur
	 * ne verifie pas que la cellule est pleine ou non
	 */
	public static int[] saisirCellule(Grille grille) {
		boolean saisieCorrecte = false ;
		int[] cellule  = new int[2];

		while ( !saisieCorrecte) {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Veuillez choisir une case,\nex: pour la case de la ligne 1 a la colonne 2, tapez :\n1-2\npuis appuyez sur \'Entree\'.\n");
			String saisie = sc.nextLine();
			System.out.println("Vous avez tape : " + saisie +".\n");
			
			try {
				if (estValideSaisie(saisie)) {
					cellule = extraitCelluleSaisie(saisie);
					//il faut tout de meme REverifier que la dimension est de 2
					if (cellule.length == 2) {
						//decrementation pour s adapter à l affichage
						--cellule[0];
						--cellule[1];
						if (cellule[0]>=0 && cellule[0]<grille.getLignes()
								&& cellule[1]>=0 && cellule[1]<grille.getColonnes()) {
							//sortie de la boucle
							saisieCorrecte = true ;
							}
						else
							System.out.println("Il faut choisir une ligne et une colonne de la grille, recommencez.\n");
					}
				}
				else
					System.out.println("La saisie est incorrecte, recommencez.\n");
			}
			
			catch(java.lang.NumberFormatException e1) {
				System.out.println("Le format est invalide, recommencez.\n");
			}
		}
		return cellule;
	}	
	
	public static boolean estValideSaisie(String saisie) {
		// format : entier[espaces]-[espaces]entier (ligne puis colonne)
		// \s 	Matches the whitespace. Equivalent to [\t\n\r\f].
		// \d 	Matches the digits. Equivalent to [0-9].
		// re* 	Matches 0 or more occurrences of the preceding expression (e)
		// re+  Matches 1 or more of the previous thing (e).
		return saisie.matches("\\s*\\d+\\s*-\\s*\\d+\\s*");
	}
	
	
	public static int[] extraitCelluleSaisie (String saisie) {
		assert (estValideSaisie(saisie));
		
		String[] split = saisie.split("-"); //https://stackoverflow.com/a/9857266
		
		int[] coordonnees = new int[split.length];
		for (int i = 0; i < coordonnees.length;++i)
			coordonnees[i] = Integer.parseInt(split[i]) ;
		return coordonnees;
	}
	
	
	/**
	 * messageCellule sous forme string indiquant ce que le joueur a selectionne comme cellule
	 * doit etre appele à la suite de saisirCellule()
	 * @return L’utilisateur X (resp O) a saisie la cellule ligne [i], colonne[j].
	 */
	public static String afficherMessageCellule(Joueur j, int[] celluleCoord) {
		assert(j != null);
		assert(celluleCoord != null) ; //on s assure que setSaisieCellule a ete appelee
		int saisieLigne = celluleCoord[0]+1;
		int saisieColonne = celluleCoord[1]+1;		
		return "L’utilisateur " + j.getJeton().getSymbole()+ " a choisi la cellule ligne ["+ saisieLigne +"], colonne ["+ saisieColonne  +"].\n" ;
	}

	/**
	 * messageResultat
	 * indiquant victoire ou non
	 * il faut qu elle soit appelee après un placerJeton
	 * (càd "C est au joueur suivant" ou "Le Joueur X a gagné la partie")
	 * @param m match en cours
	 * @param j joueur qui vient de jouer
	 * @return
	 */
	public static String afficherMessageResultat(Match m, Joueur j) {
		assert(j != null && m != null);
		String messageResultat;
		if (m.estVictoire()) {
			messageResultat = "C est un match victorieux pour le joueur "+j.getJeton().getSymbole()+".\n";
		}
		else {
			if (m.estMatchNul())
				messageResultat = "C est un match nul.\n" ;
			else
				messageResultat = "Le joueur "+j.getJeton().getSymbole() + " a termine son tour.\n";
		}
		return messageResultat;
	}
	
	/**
	 * messageTour
	 * @param j joueur dont le tour commence
	 * @return
	 */
	public static String afficherMessageTour(Joueur j) {
		assert(j != null);
		return "C'est a l'utilisateur " + j.getJeton().getSymbole() + " de jouer.\n";
	}
	
	/**
	 * MessagePermutation
	 * @param j joueur ayant realiser la permutation
	 * @param celluleCoord1 premier cellule
	 * @param celluleCoord2 seconde cellule
	 * @return "L'utilisateur a choisi de permuter la cellule ligne ... avec la cellule ... ."
	 */
	public static String afficherMessagePermutation(Joueur j, int[] celluleCoord1,  int[] celluleCoord2 ) {
		assert(j != null);
		assert(celluleCoord1 != null && celluleCoord2 != null) ; //on s assure que setSaisieCellule a ete appelee
		int saisieLigne1 = celluleCoord1[0]+1;
		int saisieColonne1 = celluleCoord1[1]+1;
		int saisieLigne2 = celluleCoord2[0]+1;
		int saisieColonne2 = celluleCoord2[1]+1;	
		return "L’utilisateur " + j.getJeton().getSymbole()+ " a choisi de permuter la cellule ligne ["+ saisieLigne1 +"] colonne ["+ saisieColonne1  +"], avec la cellule ligne ["+ saisieLigne2 +"] colonne ["+ saisieColonne2  +"].\n" ;
	}
}
