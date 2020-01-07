package oop.tictactoe.tours;

import java.util.EnumSet;

import oop.tictactoe.appli.CA_PartieGrille;
import oop.tictactoe.appli.PartieTicTacToe;
import oop.tictactoe.grille.Direction;
import oop.tictactoe.grille.Jeton;
import oop.tictactoe.jouer.*;

public class TourTicTacToe implements In_Tour, In_MessagesPlacement {
	
	protected CA_PartieGrille partie;
	protected Joueur joueur;
	protected int[] saisieCellule ;
	protected int nbrAlign ;
	
	public TourTicTacToe(CA_PartieGrille partie, Joueur joueurActuel) {
		this.partie = (PartieTicTacToe) partie ;
		this.joueur = joueurActuel;
		this.saisieCellule = new int[2];
		this.nbrAlign = 3;	
	}

	public TourTicTacToe(CA_PartieGrille partie, Joueur joueurActuel, int nbrAlign) {
		this.partie = (PartieTicTacToe) partie ;
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
		assert (ligne < partie.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < partie.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (!partie.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);
		
		// jetonEvalue dont on evalue l implication dans un alignement avec d'autres
		Jeton jetonEvalue = partie.getCellule(ligne, colonne);
		
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
			if (ligneCible < partie.getLignes() && colonneCible < partie.getColonnes() 
					&& ligneCible >= 0 && colonneCible >= 0) {
				Jeton jetonCible = partie.getCellule(ligneCible, colonneCible);
				aligneCible += jetonCible.getSymbole();
			}
			++coeffProfondeur;
		} while (coeffProfondeur < profondeur 
				&& ligneCible < partie.getLignes() && colonneCible < partie.getColonnes()
				&& ligneCible >= 0 && colonneCible >= 0);

		// direction oppposee
		direction = direction.inverser();
		coeffProfondeur = 1; // on ne souhaite pas rajouter le jeton central
		do {
			// jetonCible jeton que l on ajoute à cibleLigne
			colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
			ligneCible = coeffProfondeur * direction.getDligne() + ligne;
			if (ligneCible < partie.getLignes() && colonneCible < partie.getColonnes() 
					&& ligneCible >= 0 && colonneCible >= 0) {
				Jeton jetonCible = partie.getCellule(ligneCible, colonneCible);
				aligneCible = jetonCible.getSymbole() + aligneCible;
			}
			++coeffProfondeur;
		} while (coeffProfondeur < profondeur 
				&& ligneCible < partie.getLignes() && colonneCible < partie.getColonnes()
				&& ligneCible >= 0 && colonneCible >= 0);

		// comparaison des chaines
		if (aligneCible.contains(aligneEvalue))
			return true;
		else
			return false;
	}
	
	/**
	 * il faut qu avant l appel de cette fonction il ai ete verifie qu il y avait bel et bien un alignement
	 * renvoie la PREMIERE direction trouvée pour laquel un alignement a ete trouve
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @return PREMIERE direction trouvée pour laquel un alignement a ete trouve
	 */
	public Direction directionAlignementXD(int ligne, int colonne, int profondeur) {
		assert (ligne < partie.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < partie.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (!partie.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);
		assert (nbrDirectAvecAlign(ligne, colonne, profondeur) >=1); //il faut qu avant l appel de cette fonction il ai ete verifie qu il y avait bel et bien un alignement
		
		Direction oneDirection = null ;
		
		for (Direction direction : EnumSet.range(Direction.NORD, Direction.SUD_EST))
			if (isAlignement1D1DI(ligne, colonne, profondeur, direction)) {
				oneDirection = direction ;
				return oneDirection ;
			}
		
		return oneDirection ;
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
		assert (ligne < partie.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < partie.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (!partie.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
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

	@Override
	public void jouerCoup() {
		boolean saisieCorrecte = false;

		while (!saisieCorrecte) {
			saisieCellule = In_Interaction.saisirCellule(partie.getGrille());
			System.out.println(In_Interaction.afficherMessageCellule(joueur, saisieCellule));
			if (partie.estVideCellule(saisieCellule[0], saisieCellule[1]))
				saisieCorrecte = true ;
			else
				System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
			}
		partie.placerJeton(joueur.getJeton(), saisieCellule[0], saisieCellule[1]);
		System.out.println(In_MessagesPlacement.afficherMessageCoupJoue(joueur, saisieCellule));
	}
	

	@Override
	public void evaluerCoup() {
		assert(saisieCellule != null);//on oblige le joueur a avoir jouer un coup
		if (nbrDirectAvecAlign(saisieCellule[0], saisieCellule[1], nbrAlign) >=1 ) {
			joueur.marquerPoint();
		}
	}
	


}
