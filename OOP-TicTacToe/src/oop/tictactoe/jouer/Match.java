package oop.tictactoe.jouer;

public class Match {
	
	private boolean victoire ; // etat du jeu
	private int tour ; // nombre de fois qu un joueur a debute son tour (a joue)
	private int tourMax ; //nombre de tour max pour une partie avant d atteindre le match nul

	private int pointMax ;
	
	//constructeur
	public Match(int nombreTourMax ) {
		victoire = false ;
		tour = 0 ;
		tourMax = nombreTourMax ;
		pointMax = 0 ;
	}
	
	public Match (int nombreTourMax, int nombrePointMax) {
		victoire = false ;
		tour = 0 ;
		tourMax = nombreTourMax ;
		
		assert (nombrePointMax >0); // nombre de point max = 0 signifie que la victoire n est pas fonction du nombre de point
		pointMax = nombrePointMax;
	}
	
	//match pour le tictactoe avec un nombre de tour de 9 car 9 cellules dans la grille
	public Match() {
		victoire = false ;
		tour = 0 ;
		tourMax = 9 ;
	}
	
	//getteur
	/**
	 * 
	 * @return combien y a t il de tour au maximum
	 */
	public int getTourMax() {
		return tourMax;
	}
	
	/**
	 * 
	 * @return combien de tour ont ete jouee
	 */
	public int getTour() {
		return this.tour;
	}
	
	/**
	 * 
	 * @return quel est l etat du match
	 */
	public boolean estVictoire() {
		return victoire;
	}
	public void setVictoire(Joueur j1, Joueur j2) {
		assert(j1.getScore() - j2.getScore() !=0);
		this.victoire = true;
	}
	
	/**
	 * 
	 * @return y a t il match nul 
	 */
	public boolean estMatchNul() {
		if (tour >= tourMax)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @return le match est il termine
	 */
	public boolean estTermine() {
		return !(tour <= tourMax) ;
	}
	
	//methode spe
	/**
	 * tourDebut augmente le nombre de tour 
	 * doit ete appele a chaque fois que le debut d un tour de joueur commence
	 */
	public void tourDebut() {
		++ this.tour;
	}
}
