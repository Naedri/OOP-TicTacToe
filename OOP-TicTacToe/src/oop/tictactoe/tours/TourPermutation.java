package oop.tictactoe.tours;

import oop.tictactoe.grille.*;
import oop.tictactoe.jouer.In_Interaction;
import oop.tictactoe.jouer.In_MessagesPermutation;
import oop.tictactoe.jouer.Joueur;

public class TourPermutation extends TourMorpion implements In_Tour, In_MessagesPermutation {
	
	protected int[] saisieCellule2 ; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
	protected Joueur joueurAutre; //necessaire car une même permutation peut conduire à augmenter le score des deux joueurs.

	public TourPermutation(Grille grille, Joueur joueurActuel, Joueur joueurAutre) {
		super(grille,joueurActuel);
		this.saisieCellule2 = new int[2]; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
		this.joueurAutre = joueurAutre ; //necessaire car une même permutation peut conduire à augmenter le score des deux joueurs.
	}
	
	@Override
	public void jouerCoup() {
		boolean saisieCorrecte = false;

		while (!saisieCorrecte) {
			saisieCellule = In_Interaction.saisirCellule(grille);
			System.out.println(In_Interaction.afficherMessageCellule(joueur, saisieCellule));
			saisieCellule2 = In_Interaction.saisirCellule(grille);
			System.out.println(In_Interaction.afficherMessageCellule(joueur, saisieCellule2));

			if (grille.sontAdjacents(saisieCellule[0], saisieCellule[1],saisieCellule2[0], saisieCellule2[1])) {
				saisieCorrecte = true ;
			}
			else
				System.out.println("La case selectionnee n est pas adjacent a la premiere case selectionnee. Veuillez recommencer.\n");
			
		}
		grille.permutationJeton(saisieCellule[0], saisieCellule[1],saisieCellule2[0], saisieCellule2[1]);
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

		if (grille.getCellule(saisieCellule[0], saisieCellule[1]).estOuvert()) {
			Joueur joueurMarquant = null ;
			if (nbrDirectAvecAlign(saisieCellule[0], saisieCellule[1], 3) >=1 ) {
				fermeAlignementXD(saisieCellule[0], saisieCellule[1], 3);
				if (grille.getCellule(saisieCellule[0], saisieCellule[1]).estEgal(joueur.getJeton() ) ) {
					joueurMarquant = joueur;
				}
				else {
					joueurMarquant = joueurAutre;
				}
				System.out.println(In_Interaction.afficherMessageCoupMarquant(joueurMarquant));
				grille.afficherGrille();
				joueurMarquant.marquerPoint();
			}
		}
		
		if (grille.getCellule(saisieCellule2[0], saisieCellule2[1]).estOuvert()) {
			Joueur joueurMarquant = null ;
			if (nbrDirectAvecAlign(saisieCellule2[0], saisieCellule2[1], 3) >=1 ) {
				fermeAlignementXD(saisieCellule2[0], saisieCellule2[1], 3);
				if (grille.getCellule(saisieCellule2[0], saisieCellule2[1]).estEgal(joueur.getJeton() ) ) {
					joueurMarquant = joueur;
				}
				else {
					joueurMarquant = joueurAutre;
				}
				System.out.println(In_Interaction.afficherMessageCoupMarquant(joueurMarquant));
				grille.afficherGrille();
				joueurMarquant.marquerPoint();
			}
		}

	}

	
}
