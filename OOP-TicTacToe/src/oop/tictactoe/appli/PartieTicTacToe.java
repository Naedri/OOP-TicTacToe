package oop.tictactoe.appli;

import oop.tictactoe.grille.*;

public class PartieTicTacToe {
	
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
