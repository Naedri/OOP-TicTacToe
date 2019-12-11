package oop.tictactoe.grille;

public class Grille {
	
	private int colonnes;
	private int lignes;
	
	public Grille() {
		this.colonnes = 3 ;
		this.lignes = 3 ;
	}
	
	public Grille(int hauteur, int largeur) {
		this.colonnes = largeur ;
		this.lignes = hauteur ;
	}
	
	

}
