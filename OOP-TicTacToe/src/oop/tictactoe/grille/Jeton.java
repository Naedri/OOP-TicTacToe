package oop.tictactoe.grille;

public enum Jeton {

	JETON_VIDE(' ', false), 
	JETON_X ('X',true), JETON_O ('O',true), 
	JETON_X_MIN ('x',false), JETON_O_MIN ('o',false);

	private char jeton ;
	private boolean ouvert ;
	
	private Jeton(char jeton, boolean ouvert) {
		this.jeton = jeton;
		this.ouvert = ouvert ;
	}
	
	
	public char getJeton() {
		return this.jeton;
	}
	
	public boolean estVide() {
		return this.equals(JETON_VIDE);
	}
	
	public boolean estOuvert() {
		return this.ouvert ;
	}
	
	private Jeton ouvertInversion() {
		return Jeton.values()[(this.ordinal() +2) %5];
	}
	
	public Jeton ouvertToFerme() {
		assert(this.ouvert == true) ;
		return this.ouvertInversion() ;
	}
	
	//toString
	public String toString() {
		return "" + '[' + this.jeton + ']' ;  // "" shortcut to cast from char to string
	}
	
}

