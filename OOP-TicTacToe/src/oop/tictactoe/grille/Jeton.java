package oop.tictactoe.grille;

public enum Jeton {

	JETON_X ('X',true), JETON_O ('O',true), JETON_X_MIN ('x',false), JETON_O_MIN ('o',false);

	private char jeton ;
	private boolean ouvert ;
	
	private Jeton(char jeton, boolean ouvert) {
		this.jeton = jeton;
		this.ouvert = ouvert ;
	}
	
	public String toString(){
		String cellule = "" + '[' + this.jeton + ']' ; // "" shortcut to cast from char to string
		return cellule ;
		}
	
	public char getJeton() {
		return this.jeton;
	}
	
	public boolean estOuvert() {
		return this.ouvert ;
	}
	
	private Jeton ouvertInversion() {
		return Jeton.values()[(this.ordinal() +2) %4];
	}
	
	public Jeton ouvertToFerme() {
		assert(this.ouvert == true) ;
		return this.ouvertInversion() ;
	}
	

	
}

