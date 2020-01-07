package oop.tictactoe.appli;

import oop.tictactoe.grille.Jeton;
import oop.tictactoe.jouer.In_Interaction;
import oop.tictactoe.jouer.In_MessagesPlacement;
import oop.tictactoe.jouer.Joueur;

public class PartieMorpion extends CA_Grille_Partie_alignement_fermeture {

	private int nbrAlign ;
	private int[] saisieCellule ;
	
	public PartieMorpion(int nbrLignes, int nbrColonnes, int nbrAlign) {
		super(nbrLignes, nbrColonnes,  0, (nbrLignes*nbrColonnes));
		this.saisieCellule = new int[2];
		this.nbrAlign = nbrAlign;
		int choixNbrAlignMax = (nbrColonnes >= nbrLignes) ? nbrLignes : nbrColonnes;
		assert (nbrAlign <= choixNbrAlignMax); //ce nombre ne doit pas être plus grand que le nombre de colonnes ou de lignes de votre grille
	}
	
	public PartieMorpion(int nbrLignes, int nbrColonnes) {
		super(nbrLignes, nbrColonnes,  0, (nbrLignes*nbrColonnes));
		this.saisieCellule = new int[2];
		this.nbrAlign = 3;
		int choixNbrAlignMax = (nbrColonnes >= nbrLignes) ? nbrLignes : nbrColonnes;
		assert (nbrAlign <= choixNbrAlignMax); //ce nombre ne doit pas être plus grand que le nombre de colonnes ou de lignes de votre grille
	}
	
	public PartieMorpion() {
		super(5, 6,  0, (5*6));
		this.saisieCellule = new int[2];
		this.nbrAlign = 3;
	}

	@Override
	protected void jouerCoup(Joueur joueurActuel) {
		boolean saisieCorrecte = false;
	
		while (!saisieCorrecte) {
			saisieCellule = In_Interaction.saisirCellule( getGrille());
			System.out.println(In_Interaction.afficherMessageCellule(joueurActuel, saisieCellule));
			if ( estVideCellule(saisieCellule[0], saisieCellule[1])) {
				
				if ( estVideGrille()) {
					saisieCorrecte = true ;
					}
				else {
					if (existeAdjacent(saisieCellule[0], saisieCellule[1])) {
						saisieCorrecte = true ;
					}
					else
						System.out.println("La case selectionnee ne comporte pas de jeton adjacent. Veuillez recommencer.\n");
				}
			}
			else
				System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
			}
		 placerJeton(joueurActuel.getJeton(), saisieCellule[0], saisieCellule[1]);
		System.out.println(In_MessagesPlacement.afficherMessageCoupJoue(joueurActuel, saisieCellule));
	}
	
	@Override
	protected void evaluerCoup(Joueur joueur1, Joueur joueur2) {
		assert(saisieCellule != null);//on oblige le joueur a avoir jouer un coup
		if (nbrDirectAvecAlign(saisieCellule[0], saisieCellule[1], nbrAlign) >=1 ) {
			
			Jeton jetonEvalue = getCellule(saisieCellule[0],  saisieCellule[1]);
			if (jetonEvalue.estEgal(joueur1.getJeton())){
				joueur1.marquerPoint();
				System.out.println(In_Interaction.afficherMessageCoupMarquant(joueur1));
			}
			if (jetonEvalue.estEgal(joueur2.getJeton())){
				joueur2.marquerPoint();
				System.out.println(In_Interaction.afficherMessageCoupMarquant(joueur1));
			}
			
			fermeAlignementXD(saisieCellule[0], saisieCellule[1], nbrAlign);
			afficherGrille();
		}
		
	}
	
}
