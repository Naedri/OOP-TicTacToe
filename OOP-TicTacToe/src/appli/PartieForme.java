package appli;

import grille.Forme;
import grille.Jeton;
import interaction.MessagePlacement;
import interaction.Messages_Saisie;
import utilitaires.Utils_Grille_Evaluation_Forme;

public class PartieForme extends CA_Grille_Partie_FermetureJeton {

	private Forme forme;
	private int[] saisieCellule;

	public PartieForme() {
		super(7, 7);
		this.forme = new Forme(1);
		this.saisieCellule = new int[2];
	}

	public PartieForme(int choixForme) {
		super(7, 7);
		this.forme = new Forme(choixForme);
		this.saisieCellule = new int[2];
	}
	
	public PartieForme(int lignes, int colonnes) {
		super(lignes, colonnes);
		assert(lignes >= 3 && colonnes >= 3);
		this.forme = new Forme(1);
		this.saisieCellule = new int[2];
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
		if (Utils_Grille_Evaluation_Forme.estCompleteForme(saisieCellule[0], saisieCellule[1], this, forme)) {
			// jetonEvalue dont on evalue l implication dans un alignement avec d'autres
			Jeton jetonEvalue = getCellule(saisieCellule[0], saisieCellule[1]);
			if (jetonEvalue.estEgal(joueur1.getJeton())) {
				joueur1.marquerPoint();
				System.out.println(Messages_Saisie.afficherMessageCoupMarquant(joueur1));
			}
			if (jetonEvalue.estEgal(joueur2.getJeton())) {
				joueur2.marquerPoint();
				System.out.println(Messages_Saisie.afficherMessageCoupMarquant(joueur2));
			}
//			int[][] coordAFermer = Utils_Grille_Evaluation_Forme.getCoordFormeComplete(saisieCellule[0], saisieCellule[1], this, forme);
//			ouvertsToFermesJetons(coordAFermer);
			afficherGrille();
		}
	}

	@Override
	public boolean estFinie() {
//		return (estPleineGrille());
		return (getScoreJ1() >= 1 || getScoreJ2() >= 1 || estPleineGrille());
	}

}
