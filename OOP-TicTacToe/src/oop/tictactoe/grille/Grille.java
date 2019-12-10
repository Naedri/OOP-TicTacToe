package oop.tictactoe.grille;

public class Grille {
	
	private int hauteur;
	private int largeur;
	
	public enum Jeton{
		
		JX ("[X]",true), JX_MIN ("[x]",false),
		JO ("[O]",true), JO_MIN ("[o]",false);
		
		private String jeton ;
		private boolean ouvert ;
		   
		//Constructeur
		Jeton (char jetonLettre, boolean ouvert){
			assert(ouvert == true);
			//assert(jetonLettre.equals('O') || jetonLettre.equals('X') ); //besoin de faire ça alors qu enum ?
			assert(jetonLettre == 'O' || jetonLettre == 'X'); //besoin de faire ça alors qu enum ?
			this.jeton = "[" + jetonLettre + "]";
			this.ouvert = true ;
			
		}
		
		public String toString(){
			return this.jeton;
			}
		
		public boolean estOuvert() {
			return this.ouvert ;
		}
		
		public void ouvertToFerme() {
			assert(ouvert == true);
			//assert(this.estOuvert == true)
			if (this.toString().equals("[X]"))
				this.jeton = "[x]";
			if (this.toString().equals("[O]"))
				this.jeton = "[o]";
			this.ouvert = false ;
		}
	}
	
	public Grille() {
		
	}
	
	public Grille(int hauteur, int largeur) {
		
	}

}
