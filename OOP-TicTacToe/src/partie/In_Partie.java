//****************************************************************
//In_Partie.java
//****************************************************************
package partie;

public interface In_Partie {

	// ******* METHODE APPLI *******
	/**
	 * permet de lancer une partie doit comprendre deux etapes propres a chaque tour
	 * qui eux comprendront 1- joueur un coup 2- evaluer l impact de ce coup sur le
	 * score des joueurs
	 */
	public void lancerPartie();

	public void jouerCoup(Joueur joueurActuel);

	public void evaluerCoup();

}
