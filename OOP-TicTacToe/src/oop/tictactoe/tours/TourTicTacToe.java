package oop.tictactoe.tours;

import java.util.EnumSet;
import oop.tictactoe.grille.*;
import oop.tictactoe.jouer.*;

public class TourTicTacToe implements In_Tour, In_MessagesPlacement {
	
	protected Grille grille;
	protected Joueur joueur;
	protected int[] saisieCellule ; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
	protected int nbrAlign ;
	
	public TourTicTacToe(Grille grille, Joueur joueurActuel) {
		this.grille = grille;
		this.joueur = joueurActuel;
		this.saisieCellule = new int[2];
		this.nbrAlign = 3;
	}

	public TourTicTacToe(Grille grille, Joueur joueurActuel, int nbrAlign) {
		this.grille = grille;
		this.joueur = joueurActuel;
		this.saisieCellule = new int[2];
		this.nbrAlign = nbrAlign;
	}

	/**
	 * alignement pour UNE Direction donnee ET son Inversee
	 * 
	 * @param ligne      de la cellule observée
	 * @param colonne    de la cellule observée
	 * @param profondeur est le nombre de cellule observées au max qui sont alignées
	 *                   dans grille 
	 *                   doit etre >=2
	 * @param direction  et direction opposée vers laquelle observer un alignement
	 * @return si un alignement a été trouvé
	 */
	public boolean isAlignement1D1DI(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);
		
		// jetonEvalue dont on evalue l implication dans un alignement avec d'autres
		Jeton jetonEvalue = grille.getCellule(ligne, colonne);
		
		/// aligneEvalue ligne de jeton que le joueur souhaiterait avoir à partir de
		/// jetonEvalue
		String aligneEvalue = "";
		for (int i = 1; i <= profondeur; ++i) {
			// aligneEvalue ligne de jeton que le joueur souhaiterait avoir à partir de
			// jetonEvalue
			aligneEvalue += jetonEvalue.getSymbole();
		}

		// aligneCible ligne de jeton observé dans la direction donnée
		String aligneCible = "";
		int colonneCible = 0;
		int ligneCible = 0;

		// direction donnee
		int coeffProfondeur = 0;
		do {
			// jetonCible jeton que l on ajoute à cibleLigne
			colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
			ligneCible = coeffProfondeur * direction.getDligne() + ligne;
			if (ligneCible < grille.getLignes() && colonneCible < grille.getColonnes() 
					&& ligneCible >= 0 && colonneCible >= 0) {
				Jeton jetonCible = grille.getCellule(ligneCible, colonneCible);
				aligneCible += jetonCible.getSymbole();
			}
			++coeffProfondeur;
		} while (coeffProfondeur < profondeur 
				&& ligneCible < grille.getLignes() && colonneCible < grille.getColonnes()
				&& ligneCible >= 0 && colonneCible >= 0);

		// direction oppposee
		direction = direction.inverser();
		coeffProfondeur = 1; // on ne souhaite pas rajouter le jeton central
		do {
			// jetonCible jeton que l on ajoute à cibleLigne
			colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
			ligneCible = coeffProfondeur * direction.getDligne() + ligne;
			if (ligneCible < grille.getLignes() && colonneCible < grille.getColonnes() 
					&& ligneCible >= 0 && colonneCible >= 0) {
				Jeton jetonCible = grille.getCellule(ligneCible, colonneCible);
				aligneCible = jetonCible.getSymbole() + aligneCible;
			}
			++coeffProfondeur;
		} while (coeffProfondeur < profondeur 
				&& ligneCible < grille.getLignes() && colonneCible < grille.getColonnes()
				&& ligneCible >= 0 && colonneCible >= 0);

		// comparaison des chaines
		if (aligneCible.contains(aligneEvalue))
			return true;
		else
			return false;
	}

	/**
	 * alignement pour TOUTES les Directions disponibles
	 * le nombre de direction pour laquelle un alignement a ete trouvé
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @return le nombre de direction/orientation qui ont été trouvés avec alignementCellule
	 *         dans toutes les directions
	 */
	public int nbrDirectAvecAlign(int ligne, int colonne, int profondeur) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);
		
		int alignement = 0;

		for (Direction oneDirection : EnumSet.range(Direction.NORD, Direction.SUD_EST)) {
			// pas besoin de (Direction dd : Direction.values())
			if (isAlignement1D1DI(ligne, colonne, profondeur, oneDirection)) {
				++alignement;
			}
		}

		return alignement;
	}
	

	public void jouerCoup() {
		boolean saisieCorrecte = false;

		while (!saisieCorrecte) {
			saisieCellule = In_Interaction.saisirCellule(grille);
			System.out.println(In_Interaction.afficherMessageCellule(joueur, saisieCellule));
			if (grille.estVideCellule(saisieCellule[0], saisieCellule[1]))
				saisieCorrecte = true ;
			else
				System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
			}
		grille.placerJeton(joueur.getJeton(), saisieCellule[0], saisieCellule[1]);
		System.out.println(In_MessagesPlacement.afficherMessageCoupJoue(joueur, saisieCellule));
	}
	

	public void evaluerCoup() {
		assert(saisieCellule != null);//on oblige le joueur a avoir jouer un coup
		if (nbrDirectAvecAlign(saisieCellule[0], saisieCellule[1], nbrAlign) >=1 ) {
			joueur.marquerPoint();
		}
	}
	
	// ******* METHODE GRILLE *******
	// ******* METHODE GRILLE GET_NEXT_CELLULE *******

	/**
	 * Pour les elements donnes, existeNextCellule permet de savoir si la cellule image est comprise dans la grille
	 * Pas d indication de la nature du jeton
	 * Pas de limite de profondeur
	 * cellule image cad :
	 * càd le jeton contenu dans la cellule 
	 * projetee depuis la cellule de la grille a ligne,colonne
	 * vers la direction donnee a la profondeur/distance donnee
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	public boolean existeNextCellule(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		
		boolean existe = false;
		
		int ligneCible = profondeur * direction.getDligne() + ligne ;
		int colonneCible =  profondeur * direction.getDcolonne() + colonne ;
		
		if(ligneCible < getLignes() && ligneCible >= 0 
				&& colonneCible < getColonnes() && colonneCible >= 0) {
			existe = true;
		}
		return existe;
	}

	
	/**
	 * coordNextJeton permet de savoir quelles sont les coordonnes (ligne,colonne) du jeton image
	 * càd le jeton contenu dans la cellule 
	 * projetee depuis la cellule de la grille a ligne,colonne
	 * vers la direction donnee a la profondeur/distance donnee
	 * Le jeton peut etre vide
 	 * Pas de limite de profondeur
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	public int[] coordNextJeton(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert(existeNextCellule(ligne, colonne, profondeur, direction));
		
		int[] coord = new int[2];
		int ligneCible = profondeur * direction.getDligne() + ligne ;
		int colonneCible =  profondeur * direction.getDcolonne() + colonne ;
		
		coord[0]=ligneCible;
		coord[1]=colonneCible;
		return coord ;
	}

	/**
	 * getNextJeton permet d obtenir le jeton image 
	 * càd le jeton contenu dans la cellule 
	 * projetee depuis la cellule de la grille a ligne,colonne
	 * vers la direction donnee a la profondeur/distance donnee
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	public Jeton getNextJeton (int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert(existeNextCellule(ligne, colonne, profondeur, direction));
		
		int[] coord = coordNextJeton(ligne, colonne, profondeur, direction);
	
		return getCellule(coord[0], coord[1]);
	}

}
