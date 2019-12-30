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
	 * Pour les elements donnes, existeNextCellule permet de savoir si la cellule image est comprise dans la grille
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
	 * coordNextJeton permet de savoir quelles sont les coordonnes (ligne,colonne) du jeton image
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
	private Jeton getNextJeton (int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert(existeNextCellule(ligne, colonne, profondeur, direction));
		
		int[] coord = coordNextJeton(ligne, colonne, profondeur, direction);

		return grille.getCellule(coord[0], coord[1]);
	}

	/**
	 * existeCoordForme permet de savoir 
	 * si les cellules de toute une forme 
	 * à partir d un point donné 
	 * sont inclus dans la grille
	 * @param ligneOrigine
	 * @param colonneOrigine
	 * @param forme
	 * @return
	 */
	public boolean existeForme (int ligne, int colonne, Forme forme) {
		boolean existe = true ;
		//le premier point de la forme est evalue en coordonne [ligne,colonne]
				
		for(int i = 0; i< forme.getNbrPoint(); ++i) {
			//on obtient les parametres de la projection (directionCible et profondeurCible) pour parvenir à la cellule suivante
			Direction directionCible =  Direction.values()[ forme.getOrientation()[i] ];
			int profondeurCible = forme.getDistance()[i] ;
			
			//on verifie que la cellule cible existe
			if (!existeNextCellule(ligne, colonne,profondeurCible, directionCible)) {
				return false; // si il y a au moins un point de la forme qui n est pas dans la grille on renvoie false
			}
			else {
				//si elle existe on extrait ses coordonnees pout les reutiliser dans la boucle
				int[] coordCible = coordNextJeton(ligne, colonne,profondeurCible, directionCible );
				ligne = coordCible[0] ;
				colonne= coordCible[1] ;	
			}
		}
		return existe ;
	}
	
	/**
	 * getCoordForme
	 * donne un tableau de coordonne (ligne colonne)
	 * permettant d identifier les cellules impliquees dans la realisation de la forme donnee
	 * mais ne donne la forme que pour le point donne grille[ligne,colonne]
	 * etant un point le point en haut a gauche de la forme
	 * @param ligneOrigine
	 * @param colonneOrigine
	 * @param forme
	 * @return
	 */
	public int[][] getCoordForme (int ligne, int colonne, Forme forme) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (existeForme (ligne, colonne, forme) );
		
		int[][] coord = new int[forme.getNbrPoint()][2];
		
		//le premier point de la forme est evalue en coordonne [ligne,colonne]
		coord[0][0] = ligne;
		coord[0][1] = colonne;
		
		for(int i = 1; i < forme.getNbrPoint(); ++i) {
			//on obtient les parametres de la projection (directionCible et profondeurCible) pour parvenir à la cellule suivante
			Direction directionCible =  Direction.values()[ forme.getOrientation()[i-1] ];
			int profondeurCible = forme.getDistance()[i-1] ;
			coord[i] = coordNextJeton(ligne,colonne,profondeurCible, directionCible);
			ligne = coord[i][0] ;
			colonne= coord[i][1] ;
		}
		return coord ;
	}
	
	
	public String getJetonForme (int ligne, int colonne, Forme forme) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (existeForme (ligne, colonne, forme) );
		
		int[][] coordDesJetons = getCoordForme(ligne,colonne,forme) ;
		
		String sJeton = "";

		for (int i=0 ; i < coordDesJetons.length; ++i) {
			sJeton += grille.getCellule(coordDesJetons[i][0],coordDesJetons[i][1]).getSymbole() ;
		}
		
		return sJeton;
	}
	
	@Override
	public void evaluerCoup() {
		// TODO Auto-generated method stub
		
	}

	

}
