package oop.tictactoe.mouvements;

import oop.tictactoe.grille.Jeton;
import oop.tictactoe.jouer.In_MessagesPlacement;
import oop.tictactoe.jouer.Joueur;
import oop.tictactoe.grille.Grille;

import java.util.LinkedList;

import oop.tictactoe.grille.Direction;
import oop.tictactoe.grille.Forme;

public class TourForme extends TourTicTacToe implements In_Tour, In_MessagesPlacement   {

	private Grille grille;
	private Joueur joueur;
	int[] saisieCellule ; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
	private Forme forme;
	
	public TourForme(Grille grille, Joueur joueurActuel, Forme forme) {
		super(grille, joueurActuel);
		this.forme = forme ;
	}
	
	/**
	 * Pour les elements donnes la cellule image est elle comprise dans la grille
	 * Pas d indication de la nature du jeton
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
	private boolean existeNextCellule(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		
		boolean existe = false;
		
		int ligneCible = profondeur * direction.getDcolonne() + colonne ;
		int colonneCible =  profondeur * direction.getDcolonne() + colonne ;
		
		if(ligneCible < grille.getLignes() && ligneCible >= 0 
				&& colonneCible < grille.getColonnes() && colonneCible >= 0) {
			existe = true;
		}
		
		return existe;
	}
	
	/**
	 * permet de savoir quelles sont les coordonnes (ligne,colonne) du jeton image
	 * càd le jeton contenu dans la cellule 
	 * projetee depuis la cellule de la grille a ligne,colonne
	 * vers la direction donnee a la profondeur/distance donnee
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	private int[] coordNextJeton(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert(existeNextCellule(ligne, colonne, profondeur, direction));
		
		int[] coord = new int[2];
		int ligneCible = profondeur * direction.getDcolonne() + colonne ;
		int colonneCible =  profondeur * direction.getDcolonne() + colonne ;
		
		coord[0]=ligneCible;
		coord[1]=colonneCible;
		return coord ;

	}
	
	/**
	 * permet d obtenir le jeton image 
	 * càd le jeton contenu dans la cellule 
	 * projetee depuis la cellule de la grille a ligne,colonne
	 * vers la direction donnee a la profondeur/distance donnee
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	private Jeton getNextJeton (int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert(existeNextCellule(ligne, colonne, profondeur, direction));
		
		int[] coord = coordNextJeton(ligne, colonne, profondeur, direction);

		return grille.getCellule(coord[0], coord[1]);
	}

	public boolean existeCoordForme (int ligne, int colonne, Forme forme) {
		boolean existe = false ;
		
		int ligneOrigine = ligne ;
		int colonneOrigine = colonne ;
				
		for(int i = 0; i< forme.getNbrPoint(); ++i) {
			
			Direction directionCible =  Direction.values()[ forme.getOrientation()[i] ];
			int profondeurCible = forme.getDistance()[i] ;

			//Direction.values()[d]
			if (!existeNextCellule(ligneOrigine, colonneOrigine,profondeurCible, directionCible)) {
				return false;
			}
			else {
				int[] coordCible = coordNextJeton(ligneOrigine, colonneOrigine,profondeurCible, directionCible );
				ligneOrigine = coordCible[0] ;
				colonneOrigine = coordCible[1] ;
				
			}
		}
		
		return existe ;
	}
	
	/**
	 * donne un tableau de coordonne (ligne colonne)
	 * permettant d identifier les cellules impliquees dans la realisation de la forme donnee
	 * mais ne donne la forme que pour le point donne grille[ligne,colonne]
	 * etant un point le point en haut a gauche de la forme
	 * @param ligne
	 * @param colonne
	 * @param forme
	 * @return
	 */
	public int[][] getCoordForme (int ligne, int colonne, Forme forme) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (existeCoordForme (ligne, colonne, forme) );
		
		int[][] coord ;

		for(int i = 0; i< forme.getNbrPoint(); ++i) {
			
				coord[i][0]= ;
				coord[i][1]= ;
		}
		return coord ;
	}
		
		
		
//		
//		
//		
//		
//		// jetonEvalue dont on evalue l implication dans un alignement avec d'autres
//		// jetons : jetonCible
//		Jeton jetonEvalue = grille.getCellule(ligne, colonne);
//		
//		/// aligneEvalue ligne de jeton que le joueur souhaiterait avoir à partir de
//		/// jetonEvalue
//		String aligneEvalue = "";
//		for (int i = 1; i <= profondeur; ++i) {
//			// aligneEvalue ligne de jeton que le joueur souhaiterait avoir à partir de
//			// jetonEvalue
//			aligneEvalue += jetonEvalue.getSymbole();
//		}
//
//		// aligneCible ligne de jeton observé dans la direction donnée
//		String aligneCible = "";
//		int colonneCible = 0;
//		int ligneCible = 0;
//
//		// direction donnee
//		int coeffProfondeur = 0;
//		do {
//			// jetonCible jeton que l on ajoute à cibleLigne
//			colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
//			ligneCible = coeffProfondeur * direction.getDligne() + ligne;
//			if (ligneCible < grille.getLignes() && colonneCible < grille.getColonnes() 
//					&& ligneCible >= 0 && colonneCible >= 0) {
//				Jeton jetonCible = grille.getCellule(ligneCible, colonneCible);
//				aligneCible += jetonCible.getSymbole();
//			}
//			++coeffProfondeur;
//		} while (coeffProfondeur < profondeur 
//				&& ligneCible < grille.getLignes() && colonneCible < grille.getColonnes()
//				&& ligneCible >= 0 && colonneCible >= 0);
//
//		// direction oppposee
//		direction = direction.inverser();
//		coeffProfondeur = 1; // on ne souhaite pas rajouter le jeton central
//		do {
//			// jetonCible jeton que l on ajoute à cibleLigne
//			colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
//			ligneCible = coeffProfondeur * direction.getDligne() + ligne;
//			if (ligneCible < grille.getLignes() && colonneCible < grille.getColonnes() 
//					&& ligneCible >= 0 && colonneCible >= 0) {
//				Jeton jetonCible = grille.getCellule(ligneCible, colonneCible);
//				aligneCible = jetonCible.getSymbole() + aligneCible;
//			}
//			++coeffProfondeur;
//		} while (coeffProfondeur < profondeur 
//				&& ligneCible < grille.getLignes() && colonneCible < grille.getColonnes()
//				&& ligneCible >= 0 && colonneCible >= 0);
//
//		// comparaison des chaines
//		if (aligneCible.contains(aligneEvalue))
//			return true;
//		else
//			return false;
//	}
	
	@Override
	public void evaluerCoup() {
		// TODO Auto-generated method stub
		
	}

	

}
