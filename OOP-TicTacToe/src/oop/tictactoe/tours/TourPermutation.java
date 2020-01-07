package oop.tictactoe.tours;

import oop.tictactoe.appli.CA_PartieGrille;
import oop.tictactoe.appli.PartiePermutation;
import oop.tictactoe.grille.*;
import oop.tictactoe.jouer.In_Interaction;
import oop.tictactoe.jouer.In_MessagesPermutation;
import oop.tictactoe.jouer.Joueur;

public class TourPermutation extends TourMorpion implements In_Tour, In_MessagesPermutation {
	
	protected int[] saisieCellule2 ; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
	protected Joueur joueurAutre; //necessaire car une même permutation peut conduire à augmenter le score des deux joueurs.

	public TourPermutation(PartiePermutation partie, Joueur joueurActuel, Joueur joueurAutre) {
		super(partie,joueurActuel);
		assert(joueurActuel != joueurAutre);
		assert(! joueurActuel.getJeton().estEgal(joueurAutre.getJeton()));
		this.saisieCellule2 = new int[2]; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
		this.joueurAutre = joueurAutre ; //necessaire car une même permutation peut conduire à augmenter le score des deux joueurs.
	}
	
	public TourPermutation(PartiePermutation partie, Joueur joueurActuel, Joueur joueurAutre, int nbrAlign) {
		super(partie,joueurActuel,nbrAlign);
		assert(joueurActuel != joueurAutre);
		assert(! joueurActuel.getJeton().estEgal(joueurAutre.getJeton()));
		this.saisieCellule2 = new int[2]; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
		this.joueurAutre = joueurAutre ; //necessaire car une même permutation peut conduire à augmenter le score des deux joueurs.
	}

	@Override
	public void jouerCoup() {
		boolean saisieCorrectejouerCoup = false;

		while (!saisieCorrectejouerCoup) {
			System.out.println("Vous allez choisir les deux cases pour permutation.\n");
			saisieCellule = In_Interaction.saisirCellule(partie.getGrille());
			System.out.println(In_Interaction.afficherMessageCellule(joueur, saisieCellule));
			saisieCellule2 = In_Interaction.saisirCellule(partie.getGrille());
			System.out.println(In_Interaction.afficherMessageCellule(joueur, saisieCellule2));
			
			//les jetons doivent etre de cases differentes
			if( partie.sontDifferentes(saisieCellule[0], saisieCellule[1],saisieCellule2[0], saisieCellule2[1]) ) {
				// les jetons doivent etre adjacents
				if (sontAdjacents(saisieCellule[0], saisieCellule[1],saisieCellule2[0], saisieCellule2[1]) ) {
					saisieCorrectejouerCoup = true ;
				}
				else
					System.out.println("La case selectionnee n est pas adjacente a la premiere case selectionnee. Veuillez recommencer la saisie des deux cellules.\n");
			}
			else
				System.out.println("La case selectionnee est identique a la premiere case selectionnee. Veuillez recommencer la saisie des deux cellules.\n");
			
		}
		.permutationJeton(saisieCellule[0], saisieCellule[1],saisieCellule2[0], saisieCellule2[1]);
		System.out.println(In_MessagesPermutation.afficherMessageCoupJoue(joueur, saisieCellule, saisieCellule2));
	}
	
	/**
	 * on ne peut pas ganger d alignements avec des jetons qui sont deja fermes
	 * comme on peut modifier les jetons fermes il faut limiter les jetons evalues au jeton ouverts
	 */
	@Override
	public void evaluerCoup() {
		assert(saisieCellule != null);//on oblige le joueur a avoir jouer un coup
		assert(saisieCellule2 != null);//on oblige le joueur a avoir jouer un coup

		if (partie.estOuvert(saisieCellule[0], saisieCellule[1])) {
			Joueur joueurMarquant = null ;
			if (nbrDirectAvecAlign(saisieCellule[0], saisieCellule[1], nbrAlign) >=1 ) {
				if (partie.getCellule(saisieCellule[0], saisieCellule[1]).estEgal(joueur.getJeton() ) ) {
					joueurMarquant = joueur;
				}
				else {
					joueurMarquant = joueurAutre;
				}
				fermeAlignementXD(saisieCellule[0], saisieCellule[1], nbrAlign);
				System.out.println(In_Interaction.afficherMessageCoupMarquant(joueurMarquant));
				partie.afficherGrille();
				joueurMarquant.marquerPoint();
			}
		}
		if (partie.estOuvert(saisieCellule2[0], saisieCellule2[1])) {
			Joueur joueurMarquant = null ;
			if (nbrDirectAvecAlign(saisieCellule2[0], saisieCellule2[1], nbrAlign) >=1 ) {
				if (partie.getCellule(saisieCellule2[0], saisieCellule2[1]).estEgal(joueur.getJeton() ) ) {
					joueurMarquant = joueur;
				}
				else {
					joueurMarquant = joueurAutre;
				}
				fermeAlignementXD(saisieCellule2[0], saisieCellule2[1], nbrAlign);
				System.out.println(In_Interaction.afficherMessageCoupMarquant(joueurMarquant));
				partie.afficherGrille();
				joueurMarquant.marquerPoint();
			}
		}

	}
	
	// ******* METHODE GRILLE PERMUTATION *******
	/**
	 * permute deux jetons de la grille
	 * verifie que les deux jetons electionnes sont dans la grille
	 * verifie que les deux jetons sont adjacents
	 * verifie que les les cellules sont rempli de JETON
	 * Il n est PAS verifie que les deux JETONS a permuter soient ouverts 	 
	 * @param ligne1
	 * @param colonne1
	 * @param colonne1
	 * @param ligne2
	 */
	public void permutationJeton(int ligne1, int colonne1, int ligne2, int colonne2) {
		assert(partie.sontDifferentes(ligne1, colonne1, ligne2, colonne2)); //les jetons doivent etre differents
		assert (ligne1 < partie.getLignes() && ligne1 >= 0); //la cellule doit être dans la grille
		assert (colonne1 < partie.getColonnes() && colonne1 >= 0); //la cellule doit être dans la grille
		assert (ligne2 <  partie.getLignes() && ligne2 >= 0); //la cellule doit être dans la grille
		assert (colonne2 <  partie.getColonnes() && colonne2 >= 0); //la cellule doit être dans la grille

		assert (! partie.estVideCellule(ligne1, colonne1)); // la cellule ne doit pas etre vide		
		assert (! partie.estVideCellule(ligne2, colonne2)); // la cellule ne doit pas etre vide

		assert(sontAdjacents(ligne1, colonne1, ligne2, colonne2)); //les jetons doivent etre adjacents

		//si les jetons sont differents
		if (!  partie.getCellule(ligne1,colonne1).estEgal( partie.getCellule(ligne2,colonne2)) ) {
			Jeton jtemp =  partie.getCellule(ligne1,colonne1);
			 partie.placerJeton( partie.getCellule(ligne2,colonne2), ligne1, colonne1);
			 partie.placerJeton(jtemp, ligne2, colonne2);
		}
	}

	
}
