package oop.tictactoe.grille;

public enum Jeton {

	JETON_VIDE(' ', false), 
	JETON_X ('X',true), JETON_O ('O',true), 
	JETON_X_MIN ('x',false), JETON_O_MIN ('o',false);

	private char symbole ;
	private boolean ouvert ;
	
	//Jeton enum
	private Jeton(char jeton, boolean ouvert) {
		this.symbole = jeton;
		this.ouvert = ouvert ;
	}
	public boolean estEgal(Jeton jetonCible){
		return ( this.symbole==jetonCible.symbole && this.ouvert==jetonCible.ouvert);
	}
	
	//jeton charactere
	public char getSymbole() {
		return this.symbole;
	}
	
	public boolean estVideJeton() {
		return this.equals(JETON_VIDE);
	}
	
//	//ouvert boolean
//	public boolean estOuvert() {
//		return this.ouvert ;
//	}
	
//	/**
//	 * Ferme un jeton ouvert
//	 * @return
//	 */
//	public Jeton ouvertToFerme() {
//		assert(this.ouvert == true) ;
//		return Jeton.values()[(this.ordinal() +2) %5];
//	}
	
	//toString
	public String toString() {
		return "" + '[' + this.symbole + ']' ;  // "" shortcut to cast from char to string
	}
	
	//NE MARCHE PAS
//	//viderJeton
//	protected void viderJeton() {
//		 this.symbole = Jeton.JETON_VIDE.getSymbole() ;
//		 this.ouvert = Jeton.JETON_VIDE.estOuvert() ;
//	}
}

