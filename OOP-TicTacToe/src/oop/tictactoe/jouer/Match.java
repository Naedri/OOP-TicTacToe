package oop.tictactoe.jouer;

public class Match {
	
	private boolean victoire ; // etat du jeu
	private int tour ; // nombre de fois qu un joueur a debute son tour (a joue)
	private int tourMax ; //nombre de tour max pour une partie avant d atteindre le match nul ; si il y en a pas tourMax = 0
	private int pointMax ; //nombre de point max d'un des joueur a partir duquel la partie s arrete ; si il y en a pas tourMax = à
	
	//constructeur
	
	/**
	 * match s arrete 
	 * apres qu un des joueurs ait atteint le nombre de point max defini (nombrePointMax)
	 * doit etre >0
	 * @param nombrePointMax
	 */
	public Match(int nombrePointMax ) {
		assert(nombrePointMax >0);
		victoire = false ;
		tour = 0 ;
		pointMax = nombrePointMax ;
		tourMax = 0 ;
	}
	/**
 	 * si nombrePointMax != 0
	 * match s arrete 
	 * apres qu un des joueurs ait atteint le nombre de point max defini (nombrePointMax)
	 * OU
	 * apres que le nombre de tour des joueurs ait atteint le nombre de tour max defini (nombreTourMax)
	 * si nombrePointMax == 0
	 * match s arrete 
	 * apres que le nombre de tour des joueurs ait atteint le nombre de tour max defini (nombreTourMax)
	 *
	 * @param nombrePointMax
	 * @param nombreTourMax
	 */
	public Match(int nombrePointMax, int nombreTourMax) {
		assert(nombreTourMax>0 && nombrePointMax >= 0);
		victoire = false ;
		tour = 0 ;
		pointMax = nombrePointMax ;
		tourMax = nombreTourMax ;
	}
	
	//getteur
	/**
	 * 
	 * @return a partir de combien de point un joueur a t il gagne
	 */
	public int getPointMax() {
		return pointMax;
	}
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
	public boolean getVictoire() {
		return victoire;
	}
	
	public boolean estTourMax() {
		if (tourMax != 0) {
			return !(tour < tourMax) ;
		}
		else
			return false ;
	}
	
	public boolean estPointMax(Joueur j) {
		if (pointMax != 0) {
			return !(j.getScore() < pointMax) ;
		}
		else
			return false ;
	}
	
	/**
	 * 
	 * @return le match est il termine
	 */
	public boolean estTermine(Joueur joueurActuel) {
		if ( pointMax != 0 && tourMax == 0 ) {
			//match s arrete 
			//apres qu un des joueurs ait atteint le nombre de point max defini (nombrePointMax)
			return !(joueurActuel.getScore() < pointMax) ;
			
		}
		if ( pointMax == 0 && tourMax != 0 ) {
			//match s arrete 
			//apres que le nombre de tour des joueurs ait atteint le nombre de tour max defini (nombreTourMax)
			return !(tour < tourMax) ;

		}
		else {
			// if (pointMax != 0 && tourMax != 0)
			// car pointMax == 0 && tourMax == 0 ne peut exister
			// match s arrete
			// des quand le nombrePointMax a été atteint sinon continue jusqu a ce que le nombreTourMax ait été atteint
			return ( (!(joueurActuel.getScore() < pointMax)) || (!(tour < tourMax)) ) ;
		}
	}
	
	//methode spe
	/**
	 * tourDebut augmente le nombre de tour 
	 * doit ete appele a chaque fois que le debut d un tour de joueur commence
	 */
	public void tourDebut() {
		++ this.tour;
	}
	
	/**
	 * a appelle a chaque fin de tour du joueurActuel
	 * pour evaluer si le match est termine (cad victoire ou match null)
	 * @param joueurActuel dont c est le tour
	 */
	public void evalMatchParTour (Joueur joueurActuel) {
		if (estTermine(joueurActuel)) {
			if (joueurActuel.getScore()>=pointMax)
				victoire = true ;
		}
	}
	
}
