package grille;

public enum Jeton {

	JETON_VIDE(' '), JETON_X('X'), JETON_O('O');

	private char symbole;

	// Jeton enum
	private Jeton(char jeton) {
		this.symbole = jeton;
	}

	public boolean estEgal(Jeton jetonCible) {
		return (this.symbole == jetonCible.symbole);
	}

	// jeton charactere
	public char getSymbole() {
		return this.symbole;
	}

	public boolean estVideJeton() {
		return this.equals(JETON_VIDE);
	}

	// toString
	public String toString() {
		return "" + '[' + this.symbole + ']'; // "" shortcut to cast from char to string
	}
}
