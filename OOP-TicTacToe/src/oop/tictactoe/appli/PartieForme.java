package oop.tictactoe.appli;

import Utilitaires.Utils_Grille_Evaluation_Forme;
import oop.tictactoe.grille.Forme;
import oop.tictactoe.grille.Jeton;
import oop.tictactoe.interaction.Messages_Saisie;
import oop.tictactoe.interaction.MessagePlacement;

public class PartieForme extends CA_Grille_Partie {

	private Forme forme ;
	private int[] saisieCellule ;
	
	public PartieForme() {
		super(12, 12);
		this.forme = Forme.CARRE;
		this.saisieCellule = new int[2];
	}
	
	public PartieForme(Forme forme) {
		super(12, 12);
		this.forme = forme;
		this.saisieCellule = new int[2];
	}
	
	@Override
	public void jouerCoup(Joueur joueurActuel) {
		boolean saisieCorrecte = false;
		
		while (!saisieCorrecte) {
			saisieCellule = Messages_Saisie.saisirCellule( getGrille());
			System.out.println(Messages_Saisie.afficherMessageCellule(joueurActuel, saisieCellule));
			if ( estVideCellule(saisieCellule[0], saisieCellule[1]))
				saisieCorrecte = true ;
			else
				System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
			}
		placerJeton(joueurActuel.getJeton(), saisieCellule[0], saisieCellule[1]);
		System.out.println(MessagePlacement.afficherMessageCoupJoue(joueurActuel, saisieCellule));
		
	}

	@Override
	public void evaluerCoup(Joueur joueur1, Joueur joueur2) {
		assert(saisieCellule != null);//on oblige le joueur a avoir jouer un coup
		if (Utils_Grille_Evaluation_Forme.estCompleteForme(saisieCellule[0], saisieCellule[1], this, forme)) {
			// jetonEvalue dont on evalue l implication dans un alignement avec d'autres
			Jeton jetonEvalue = getCellule(saisieCellule[0],  saisieCellule[1]);
			if (jetonEvalue.estEgal(joueur1.getJeton())){
				joueur1.marquerPoint();
			}
			if (jetonEvalue.estEgal(joueur2.getJeton())){
				joueur2.marquerPoint();
			}
				
		}		
	}

	@Override
	public boolean estFinie() {
		return (estPleineGrille());
	}
	
	
}
