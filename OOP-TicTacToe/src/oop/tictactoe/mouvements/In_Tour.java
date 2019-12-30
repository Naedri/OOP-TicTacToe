package oop.tictactoe.mouvements;

public interface In_Tour {
	
	/**
	 * permet au joueur de joueur son coup
	 * ne peut pas contenir d'argument car ceux ci doivent pouvoir varier
	 */
	public void jouerCoup();
	
	/**
	 * permet d augmenter le score d un joueur si un coup a pu permettre une forme de victoire
 	 * ne peut pas contenir d'argument car ceux ci doivent pouvoir varier
	 */
	public void evaluerCoup();
	
}
