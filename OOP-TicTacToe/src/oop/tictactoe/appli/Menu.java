package oop.tictactoe.appli;

public class Menu {
	
	private int choixAccueil ;
	private int choixJouer ;
	
	private PartieTicTacToe appliTicTacToe ;
	private PartieMorpion appliMorpion ;
	private PartieXAlignements appliXAlignement ;
	private PartieForme appliForme ;
	private PartiePermutation appliPermutation ;
	
	public int getChoixAccueil() {
		return choixAccueil;
	}

	public int getChoixJouer() {
		return choixJouer;
	}

	/*
	 * Securise la selection du choix dans le Menu
	 */
	/**
	 * Securise la selection du choix dans le Menu
	 * @param choixAccueil
	 */
	private void setChoixAccueil(int choixAccueil) {
		this.choixAccueil = choixAccueil;
	}
	/**
	 * Securise la selection du choix de la partie
	 * @param choixJouer
	 */
	private void setChoixJouer(int choixJouer) {
		this.choixJouer = choixJouer;
	}

	
	/*
	 * Composition du Menu
	 *  1. Afficher Regles
	 *  2. Afficher Auteurs
	 *  3. Jouer  
	 *  	1. Retour  
	 *  	2. MORPION   
	 *  		+ Determiner taille grille    
	 *  			+ largeur     
	 *  			+ longueur
	 *  	3. TIC TAC TOE    
	 *  		+ Determiner taille grille    
	 *  			+ largeur     
	 *  			+ longueur   
	 *  		+ Joueur nom saisie  
	 *  	4. Extension1 : XAlignements
	 *  		+ Determiner taille grille    
	 *  			+ largeur     
	 *  			+ longueur   
	 *  		+ Determiner nombre de jeton à aligner   
	 *  	5. Extension2 :  Forme  
	 *  		+ Determiner taille grille    
	 *  			+ largeur     
	 *  			+ longueur   
	 *  		+ Joueur nom saisie     
	 *  		+ choix forme    
	 *  			+ joueur1.forme choix 
	 *  			+ phrase memoi    
	 *  			+ commune ?     + 
	 *  				+ si oui -> joueur1.forme = joueur2.forme     
	 *  				+ si non -> joueur2.forme choix + phrase memo    
	 *  	6. Extension3 : permutation
	 */
	public void affichageMenu() {
		choixAccueil = 0;
		choixJouer = 0 ;
		
		if (choixAccueil == 3)
			if (choixJouer == 1)
				//affichageMenu ;
			if (choixJouer == 2)
				appliTicTacToe.lancerPartie() ;
			if (choixJouer == 3)
				appliMorpion.lancerPartie() ; 
			if (choixJouer == 4)
				appliXAlignement.lancerPartie() ; 
			if (choixJouer == 5)
				appliForme.lancerPartie() ; 
			if (choixJouer == 6)
				appliPermutation.lancerPartie() ; 
	}

	
}
