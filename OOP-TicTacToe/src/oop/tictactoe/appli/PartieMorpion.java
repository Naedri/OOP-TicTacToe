package oop.tictactoe.appli;

import oop.tictactoe.tours.TourMorpion;
import oop.tictactoe.grille.Direction;
import oop.tictactoe.jouer.*;

public class PartieMorpion extends PartieTicTacToe implements In_Partie {
	
	public PartieMorpion() {
		super();
		match = new Match(0,grille.getNbrCellules()); //nombre de point max = infini ; nombre de coup max = nombre taille grille
	}
	
	public PartieMorpion(int grilleNrbLignes, int grilleNbrColonnes) {
		super(grilleNrbLignes, grilleNrbLignes);
		match = new Match(0,grille.getNbrCellules()); //nombre de point max = infini ; nombre de coup max = nombre taille grille
	}
	
	public PartieMorpion(int grilleNrbLignes, int grilleNbrColonnes, int choixNbrAlignements) {
		super(grilleNrbLignes, grilleNbrColonnes,choixNbrAlignements);
		match = new Match(0,grille.getNbrCellules()); //nombre de point max = infini ; nombre de coup max = nombre taille grille
	}

	@Override
	public void lancerPartie() {
		grille.afficherGrille();
		//on fait des tours
		while(!(match.estTourMax() || match.getVictoire())) {
			match.tourDebut();
			
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;

			System.out.println(In_Interaction.afficherMessageDebutTour(joueurActuel));
			TourMorpion tour = new TourMorpion(grille, joueurActuel, nbrAlign);

			tour.jouerCoup();
			grille.afficherGrille();
			tour.evaluerCoup();
			
			match.evalVictoireParPointMax (joueurActuel);

			System.out.println(In_Interaction.afficherMessageFinTour(joueurActuel));
		}
		//on compte les points
		System.out.println(In_Interaction.afficherMessageResultat(match, joueur1, joueur2));

	}
	
	// ******* METHODE GRILLE *******
	// ******* METHODE GRILLE ADJACENT JETON *******
	
	/**
	 * existe il dans les cellules voisines de la cellule donnee [ligne,colonne]
	 * des jetons non vides
	 * la cellule peut etre vide mais doit etre dans la grille
	 * @param ligne
	 * @param colonne
	 * @return
	 */
	public boolean existeAdjacent(int ligne, int colonne) {
		assert (ligne < this.grille.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grille[0].length && colonne >= 0); //la cellule doit être dans la grille
		
		for (Direction oneDirection : Direction.values())
			if (existeNextCellule(ligne, colonne, 1, oneDirection)) {
				if (! getNextJeton(ligne, colonne, 1, oneDirection).estVideJeton()) {
					return true;
				}
			}
		return false;
		
	}
	

	/**
	 * les cellules donnees sont elles adjacents
	 * doivent etre des ellules differentes
	 * Il n est pas verifie que les cellules comprennent des jetons non vides
	 * @param ligne1
	 * @param colonne1
	 * @param colonne2
	 * @param ligne2
	 * @return
	 */
	public boolean sontAdjacents(int ligne1, int colonne1, int ligne2, int colonne2) {
		assert(sontDifferentes(ligne1, colonne1, ligne2, colonne2)); //les jetons doivent etre differents

		assert (ligne1 < this.grille.length && ligne1 >= 0); //la cellule doit être dans la grille
		assert (colonne1 < this.grille[0].length && colonne1 >= 0); //la cellule doit être dans la grille
		assert (ligne2 < this.grille.length && ligne2 >= 0); //la cellule doit être dans la grille
		assert (colonne2 < this.grille[0].length && colonne2 >= 0); //la cellule doit être dans la grille
		
		boolean adjacent = false ;
		
		if ( (Math.abs(ligne1-ligne2) <= 1) && (Math.abs(colonne1-colonne2) <= 1) ) {
			adjacent = true ;
		}
		
		return adjacent;
	}

}