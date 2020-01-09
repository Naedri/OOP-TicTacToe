package partie;

import jeton.Jeton;

public class Joueur {

	private Jeton jeton;
	private int score;
	private static int compteur = 0; // permet de générer altenativement un joueur au JETON_X puis au JETON_O

	/**
	 * constructeur de joueur permet d'associer à un joueur un jeton enuméré
	 */
	public Joueur() {
		this.jeton = Jeton.values()[compteur % 2 + 1]; // x doit commencer et JETON_X est le [1]
		this.score = 0;
		++compteur;
	}

	public Joueur(Jeton jeton) {
		assert (!jeton.estVideJeton());
		this.jeton = jeton;
		this.score = 0;
		++compteur;
	}

	public Jeton getJeton() {
		return jeton;
	}

	public int getScore() {
		return score;
	}

	public void marquerPoint() {
		++this.score;
	}
}
