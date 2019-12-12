package oop.tictactoe.grille;

public enum Jeton {

	JETON_VIDE(' ', false), 
	JETON_X ('X',true), JETON_O ('O',true), 
	JETON_X_MIN ('x',false), JETON_O_MIN ('o',false);

	private char jeton ;
	private boolean ouvert ;
	
	//Jeton enum
	private Jeton(char jeton, boolean ouvert) {
		this.jeton = jeton;
		this.ouvert = ouvert ;
	}
	public boolean estEgal(Jeton jetonCible) {
		return ( this.jeton==jetonCible.jeton && this.ouvert==jetonCible.ouvert);
	}
	
	//jeton charactere
	public char getJeton() {
		return this.jeton;
	}
	
	public boolean estVide() {
		return this.equals(JETON_VIDE);
	}
	
	//ouvert boolean
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
	
	//viderJeton
	protected void viderJeton() {
		 this.jeton = Jeton.JETON_O.getJeton() ;
		 this.ouvert = Jeton.JETON_O.estOuvert() ;
	}
}

