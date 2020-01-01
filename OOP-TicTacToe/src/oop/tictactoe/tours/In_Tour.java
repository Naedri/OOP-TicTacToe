package oop.tictactoe.tours;

public interface In_Tour {
	
	/**
	 * permet au joueur de joueur(cad saisir) son coup
	 * 
	 * ne peut pas contenir d'argument car ceux ci doivent pouvoir varier
	 */
	public void jouerCoup();
	
	/**
	 * permet d augmenter le score d un joueur si un coup a pu permettre une forme de victoire
	 * le coup qui vient d etre joue permet il au joueur de gagner un point de victoire
	 *
 	 * ne peut pas contenir d'argument car ceux ci doivent pouvoir varier
	 */
	public void evaluerCoup();
	
}
