package appli;

import grille.Jeton;
import interaction.MessagePlacement;
import interaction.Messages_Saisie;
import utilitaires.Utils_Grille_Evaluation_Alignement;

public class PartieTicTacToe extends CA_Grille_Partie {

	private int nbrAlign; // nombre de jetons a aligner
	private int[] saisieCellule;

	public PartieTicTacToe() {
		// taille de 3*3 et pointMax =1 et nbrTourMax=9
		super(3, 3);
		nbrAlign = 3;
	}

	public PartieTicTacToe(int choixGrilleLigne, int choixGrilleColonne) {
		super(choixGrilleLigne, choixGrilleColonne);
		nbrAlign = 3;
	}

	public PartieTicTacToe(int choixGrilleLigne, int choixGrilleColonne, int choixNbrAlignements) {
		super(choixGrilleLigne, choixGrilleColonne);
		nbrAlign = choixNbrAlignements;
	}

	@Override
	public void jouerCoup(Joueur joueurActuel) {
		boolean saisieCorrecte = false;

		while (!saisieCorrecte) {
			saisieCellule = Messages_Saisie.saisirCellule(getGrille());
			System.out.println(Messages_Saisie.afficherMessageCellule(joueurActuel, saisieCellule));
			if (estVideCellule(saisieCellule[0], saisieCellule[1]))
				saisieCorrecte = true;
			else
				System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
		}
		placerJeton(joueurActuel.getJeton(), saisieCellule[0], saisieCellule[1]);
		System.out.println(MessagePlacement.afficherMessageCoupJoue(joueurActuel, saisieCellule));

	}

	@Override
	public void evaluerCoup(Joueur joueur1, Joueur joueur2) {
		assert (saisieCellule != null);// on oblige le joueur a avoir jouer un coup
		if (Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(saisieCellule[0], saisieCellule[1], nbrAlign,
				this) >= 1) {
			
			Jeton jetonEvalue = getCellule(saisieCellule[0], saisieCellule[1]);
			if (jetonEvalue.estEgal(joueur1.getJeton())) {
				joueur1.marquerPoint();
				System.out.println(Messages_Saisie.afficherMessageCoupMarquant(joueur1));
			}
			if (jetonEvalue.estEgal(joueur2.getJeton())) {
				joueur2.marquerPoint();
				System.out.println(Messages_Saisie.afficherMessageCoupMarquant(joueur1));
			}
		}
	}

	@Override
	public boolean estFinie() {
		return (getScoreJ1() >= 1 || getScoreJ2() >= 1 || estPleineGrille());
	}

}
