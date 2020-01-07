package oop.tictactoe.tours;

import oop.tictactoe.grille.Jeton;
import oop.tictactoe.jouer.In_Interaction;
import oop.tictactoe.jouer.In_MessagesPlacement;
import oop.tictactoe.jouer.Joueur;
import oop.tictactoe.appli.CA_PartieGrille;
import oop.tictactoe.grille.Direction;
import oop.tictactoe.grille.Forme;

public class TourForme extends TourTicTacToe implements In_Tour, In_MessagesPlacement   {

	private Forme forme;
	
	public TourForme(CA_PartieGrille partie, Joueur joueurActuel, Forme forme) {
		super(partie, joueurActuel);
		this.forme = forme ;
	}
	
	/**
	 * existeForme permet de savoir 
	 * si les cellules de toute une forme 
	 * à partir d un point donné (en haut a gauche de la forme)
	 * sont inclus dans la grille
	 * 
	 * mais pas de savoir si elle existe
	 * juste de savoir si elle est comprises dans la grille à partir du point en haut a gauche
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
			if (!partie.existeNextCellule(ligne, colonne,profondeurCible, directionCible)) {
				return false; // si il y a au moins un point de la forme qui n est pas dans la grille on renvoie false
			}
			else {
				//si elle existe on extrait ses coordonnees pout les reutiliser dans la boucle
				int[] coordCible = partie.coordNextJeton(ligne, colonne,profondeurCible, directionCible );
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
	 * @return unt table contenant x coordonnes (donc une table de table a deux dimensions)
	 */
	public int[][] getCoordForme (int ligne, int colonne, Forme forme) {
		assert (ligne < partie.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < partie.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (existeForme (ligne, colonne, forme) );
		
		int[][] coord = new int[forme.getNbrPoint()][2];
		
		//le premier point de la forme est evalue en coordonne [ligne,colonne]
		coord[0][0] = ligne;
		coord[0][1] = colonne;
		
		for(int i = 1; i < forme.getNbrPoint(); ++i) {
			//on obtient les parametres de la projection (directionCible et profondeurCible) pour parvenir à la cellule suivante
			Direction directionCible =  Direction.values()[ forme.getOrientation()[i-1] ];
			int profondeurCible = forme.getDistance()[i-1] ;
			coord[i] = partie.coordNextJeton(ligne,colonne,profondeurCible, directionCible);
			ligne = coord[i][0] ;
			colonne= coord[i][1] ;
		}
		return coord ;
	}
	
	/**
	 * permet de renvoyer le contenue des cellules (cad jetons)
	 * appartenant à une seule forme
	 * à partir d un point donnée
	 * dans une chaine de caractere
	 * @param ligne du point d ancarge de la forme
	 * @param colonne du point d ancrage de la forme
	 * @param forme evaluee
	 * @return
	 */
	public String getJetonForme (int ligne, int colonne, Forme forme) {
		assert (ligne < partie.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < partie.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (existeForme (ligne, colonne, forme) );
		
		int[][] coordDesJetons = getCoordForme(ligne,colonne,forme) ;
		
		String sJeton = "";

		for (int i=0 ; i < coordDesJetons.length; ++i) {
			sJeton += partie.getCellule(coordDesJetons[i][0],coordDesJetons[i][1]).getSymbole() ;
		}
		
		return sJeton;
	}
		
	/**
	 * permet de renvoyer le contenue des cellules (cad jetons) 
	 * (dans une chaine de caractere)
	 * appartenant à plusieurs formes derivees d une seule (forme donnee)
	 * les formes sont derivees à partir de forme.transForme()
	 * qui renvoie une forme dont les indices sont decale de int decalageIndice
	 * permettant ainsi les jetons de toutes les cellules existantes incluses dans une forme
	 * pour lequel le point donnee est considere succesivement comme chaqu'un des points de la forme
	 * @param ligne du point d ancarge de la forme
	 * @param colonne du point d ancrage de la forme
	 * @param forme evaluee d origine
	 * @return
	 */
	public String getJetonFormeAll (int ligne, int colonne, Forme forme) {
		assert (ligne < partie.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < partie.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		
		String sJetonAll = "";
		
		for (int i = 0 ; i < forme.getNbrPoint(); ++i) {
			Forme formeTemp = forme.transForme(i) ;
			if (existeForme(ligne, colonne, formeTemp)) {
				sJetonAll += getJetonForme(ligne, colonne, formeTemp);
			}
			sJetonAll += ",";
		}
		
		return sJetonAll;
	}
	
	/**
	 * permet d evaluer si un point complete une forme
 	 * @param ligne
	 * @param colonne
	 * @param forme
	 * @return
	 */
	public boolean estCompleteForme (int ligne, int colonne) {
		assert (ligne < partie.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < partie.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (! partie.estVideCellule(ligne, colonne)); //la cellule evaluee ne doit pas etre vide
		
		//quelle chaine devrait on avoir pour que la forme soit complete
		Jeton jetonCible = partie.getCellule(ligne, colonne);
		String formeCible= "";
		for (int i = 1; i <= forme.getNbrPoint(); ++i) {
			formeCible += jetonCible.getSymbole();
		}
		
		//quelle sont les formes derivees dans lesquelles sont impliques la cellule[ligne,colonne] observe
		//pou le template donnee (forme)
		String formeEvaluee =  getJetonFormeAll(ligne, colonne, forme);
		
		// comparaison des chaines
		return formeEvaluee.contains(formeCible);
	}
	
	@Override
	public void evaluerCoup() {
		assert(saisieCellule != null);//on oblige le joueur a avoir jouer un coup
		if (estCompleteForme(saisieCellule[0], saisieCellule[1])) {
			System.out.println(In_Interaction.afficherMessageCoupMarquant(joueur));
			partie.afficherGrille();
			joueur.marquerPoint();
		}
	}

}
