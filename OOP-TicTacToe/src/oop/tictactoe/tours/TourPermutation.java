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
		assert(joueurActuel != joueurAutre);
		assert(! joueurActuel.getJeton().estEgal(joueurAutre.getJeton()));
		this.saisieCellule2 = new int[2]; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
		this.joueurAutre = joueurAutre ; //necessaire car une même permutation peut conduire à augmenter le score des deux joueurs.
	}
	
	public TourPermutation(Grille grille, Joueur joueurActuel, Joueur joueurAutre, int nbrAlign) {
		super(grille,joueurActuel,nbrAlign);
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
			saisieCellule = In_Interaction.saisirCellule(grille);
			System.out.println(In_Interaction.afficherMessageCellule(joueur, saisieCellule));
			saisieCellule2 = In_Interaction.saisirCellule(grille);
			System.out.println(In_Interaction.afficherMessageCellule(joueur, saisieCellule2));
			
			//les jetons doivent etre de cases differentes
			if( grille.sontDifferentes(saisieCellule[0], saisieCellule[1],saisieCellule2[0], saisieCellule2[1]) ) {
				// les jetons doivent etre adjacents
				if (grille.sontAdjacents(saisieCellule[0], saisieCellule[1],saisieCellule2[0], saisieCellule2[1]) ) {
					saisieCorrectejouerCoup = true ;
				}
				else
					System.out.println("La case selectionnee n est pas adjacente a la premiere case selectionnee. Veuillez recommencer la saisie des deux cellules.\n");
			}
			else
				System.out.println("La case selectionnee est identique a la premiere case selectionnee. Veuillez recommencer la saisie des deux cellules.\n");
			
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
			if (nbrDirectAvecAlign(saisieCellule[0], saisieCellule[1], nbrAlign) >=1 ) {
				if (grille.getCellule(saisieCellule[0], saisieCellule[1]).estEgal(joueur.getJeton() ) ) {
					joueurMarquant = joueur;
				}
				else {
					joueurMarquant = joueurAutre;
				}
				fermeAlignementXD(saisieCellule[0], saisieCellule[1], nbrAlign);
				System.out.println(In_Interaction.afficherMessageCoupMarquant(joueurMarquant));
				grille.afficherGrille();
				joueurMarquant.marquerPoint();
			}
		}
		
		if (grille.getCellule(saisieCellule2[0], saisieCellule2[1]).estOuvert()) {
			Joueur joueurMarquant = null ;
			if (nbrDirectAvecAlign(saisieCellule2[0], saisieCellule2[1], nbrAlign) >=1 ) {
				if (grille.getCellule(saisieCellule2[0], saisieCellule2[1]).estEgal(joueur.getJeton() ) ) {
					joueurMarquant = joueur;
				}
				else {
					joueurMarquant = joueurAutre;
				}
				fermeAlignementXD(saisieCellule2[0], saisieCellule2[1], nbrAlign);
				System.out.println(In_Interaction.afficherMessageCoupMarquant(joueurMarquant));
				grille.afficherGrille();
				joueurMarquant.marquerPoint();
			}
		}

	}

	
}
