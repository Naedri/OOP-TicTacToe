package oop.tictactoe.jouer;

import oop.tictactoe.jouer.Match;
import oop.tictactoe.jouer.Joueur;
import oop.tictactoe.grille.*;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Interface {
	
	/**
	 * saisieCellule input de l utilisateur sous format de table à 1 colonne 2 lignes
	 * messageResultat victoire ou non (càd "Continuez la partie!" ou "Le Joueur X a gagné la partie")
	 * messageTour quel est le tour et qui doit jouer
	 */
	private int[] saisieCellule; //saisieCellule[0] : ligne, saisieCellule[1] : colonne
	private String messageCellule ;
	private String messageResultat ;
	private String messageTour ;
	
	public Interface() {
		messageCellule = "";
		messageResultat = "";
		messageTour = "";
		saisieCellule = new int[2];
	}
	
	/**
	 * saisieCellule sous forme de int[]
	 * saisieCellule[0] : ligne
	 * saisieCellule[1] : colonne
	 * definie la cellule saisie par l'utilisateur
	 * securise le choix de la cellule par l utilisateur l entrée de l utilisateur
	 */
	public void setSaisieCellule(Grille grille) {
		boolean saisieCorrecte = false ;
		
		Pattern motif = Pattern.compile("\\s*\\d+\\s*-\\s*\\d+\\s*");
		// format : entier[espaces]-[espaces]entier (ligne puis colonne)
		// \s 	Matches the whitespace. Equivalent to [\t\n\r\f].
		// \d 	Matches the digits. Equivalent to [0-9].
		// re* 	Matches 0 or more occurrences of the preceding expression (e)
		// re+  Matches 1 or more of the previous thing (e).

		while ( ! (saisieCorrecte 
				&& saisieCellule[0]>=0 && saisieCellule[0]<grille.getLignes() 
				&& saisieCellule[1]>=0 && saisieCellule[1]<grille.getColonnes())
				) {
			
			System.out.println("Veuillez choisir une case,\nex: pour la case de la ligne 1 à la colonne 2, tapez : 1-2),\npuis appuyez sur \'Entree\'.\n");
			Scanner scanner = new Scanner(System.in);
			String saisie = scanner.nextLine() ;
			scanner.close();
			Matcher match = motif.matcher(saisie);
			
			if (match.find()){
				Scanner sc = new Scanner(saisie);
				sc.useDelimiter("\\s*-\\s*");
				
				saisieCellule[0] = sc.nextInt()-1;
				saisieCellule[1] = sc.nextInt()-1;

				if (saisieCellule[0]>=0 && saisieCellule[0]<grille.getLignes() 
						&& saisieCellule[1]>=0 && saisieCellule[1]<grille.getColonnes()) {
					// ferme le scanner
					sc.close();
					// sortie de la boucle while
					saisieCorrecte = true ;
				}
				else
					System.out.println("Il faut choisir une ligne et une colonne de la grille.\n");
			}
			else
				System.out.println("Il faut realiser une saisie incorrecte.\n");
		}
			
			
//			String[] saisie = sc.nextLine().trim().split("-");
//			
//			try{
//				if(saisie.length==2) {
//						ligneChoisie = Integer.parseInt(saisie[0].trim());
//						colonneChoisie = Integer.parseInt(saisie[1].trim());
//						if (!(ligneChoisie>=1 && ligneChoisie<=grille.getLignes() && colonneChoisie>=1 && colonneChoisie<=grille.getColonnes() ) )
//							System.out.println("Veuillez saisir une cellule dans la grille, recommencer.\n");
//						else
//							saisieCorrecte = true;	
//					}
//				if(saisie.length!=2) {
//					System.out.println("Saisie incorrecte, veuillez recommencer.\n");
//				}
//			}	
//			catch(java.lang.NumberFormatException e1) {
//				System.out.println("Format invalide veuillez recommencer.\n");
//			} 
//			catch(java.lang.ArrayIndexOutOfBoundsException e2) {
//				System.out.println("Format invalide veuillez recommencer.\n"); //format ou valeur depassant la grille ?
//			}
//
	}

	public int[] getSaisieCellule() {
		assert(saisieCellule != null) ; //on s assure que setSaisieCellule a ete appelee
		return saisieCellule ;
	}
	
	public int getSaisieColonne() {
		assert(saisieCellule != null) ; //on s assure que setSaisieCellule a ete appelee
		return saisieCellule[1] ;
	}
	public int getSaisieLigne() {
		assert(saisieCellule != null) ; //on s assure que setSaisieCellule a ete appelee
		return saisieCellule[0] ;
	}
	
	
	/**
	 * messageCellule sous forme string
	 * doit etre appele à la suite de setSaisieCellule()
	 * @return L’utilisateur X (resp O) a saisie la cellule «[i],[j]»
	 */
	public String getMessageCellule(Joueur j) {
		assert(j != null);
		assert(saisieCellule != null) ; //on s assure que setSaisieCellule a ete appelee
		messageCellule = "L’utilisateur" + j.getJeton().getSymbole()+ "a saisie la cellule ligne ["+ saisieCellule[0]+1 +"], colonne ["+ saisieCellule[1]+1  +"]." ;
		return  messageCellule;
	}
	public void afficherMessageCellule(Joueur j) {
		assert(j != null);
		System.out.println(getMessageCellule(j));
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
	public String getMessageResultat(Match m, Joueur j) {
		assert(j != null && m != null);
		if (!m.estTermine()) {
			messageResultat = "C est au joueur suivant.";
		}
		else
			if (m.estMatchNul())
				messageResultat = "C est un match nul." ;
			else
				messageResultat = "C est un match victorieux pour le joueur "+j.getJeton().getSymbole()+".";
		return messageResultat;
	}
	public void afficherMessageResultat(Match m, Joueur j) {
		assert(j != null && m != null);
		System.out.println(getMessageResultat(m,j));
	}
	
	/**
	 * messageTour
	 * @param j joueur dont le tour commence
	 * @return
	 */
	public String getMessageTour(Joueur j) {
		assert(j != null);
		messageTour = "C'est a l'utilisateur " + j.getJeton().getSymbole() + " de jouer.";
		return messageTour ;
	}
	public void afficherMessageTour(Joueur j) {
		assert(j != null);
		System.out.println(getMessageTour(j));
	}
	
}
