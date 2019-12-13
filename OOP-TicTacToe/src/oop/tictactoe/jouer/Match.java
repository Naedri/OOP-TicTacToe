package oop.tictactoe.jouer;

public class Match {
	
	private boolean victoire = false ;
	private int tour = 0 ;
	
	//getteur
	public int getTour() {
		return this.tour;
	}
	
	public boolean estVictoire() {
		return victoire;
	}
	
	public boolean estMatchNul() {
		return true;
	}
	
	public boolean estMatchVictoire() {
		return true;
	}
}
