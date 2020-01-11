//****************************************************************
//CA_Grille_Partie.java
//****************************************************************
package partie;

import interaction.Messages_Saisie;

public abstract class CA_Grille_Partie extends CA_Grille implements In_Partie {

	private Joueur joueur1;
	private Joueur joueur2;
	private int tour;

	public CA_Grille_Partie(int nbrLignes, int nbrColonnes) {
		super(nbrLignes, nbrColonnes);
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		tour = 0;
	}

	public void lancerPartie() {
		afficherGrille();
		// on fait des tours
		while (!(estFinie())) {
			++tour;
			Joueur joueurActuel = (tour % 2 == 0) ? joueur2 : joueur1;

			System.out.println(Messages_Saisie.afficherMessageDebutTour(joueurActuel));
			jouerCoup(joueurActuel);
			afficherGrille();
			evaluerCoup();

			System.out.println(Messages_Saisie.afficherMessageFinTour(joueurActuel));
		}
		// on compte les points
		System.out.println(Messages_Saisie.afficherMessageResultat(joueur1, joueur2));

	}

	public abstract boolean estFinie();

	public abstract void jouerCoup(Joueur joueurActuel);

	public abstract void evaluerCoup();

	// ************ getteurs **********
	public int getTour() {
		return tour;
	}
	public Joueur getJ1() {
		return joueur1;
	}
	public Joueur getJ2() {
		return joueur2;
	}

}