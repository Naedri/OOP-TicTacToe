package oop.tictactoe.appli;

public class Menu {
	
	private int choixAccueil ;
	private int choixJouer ;
	
	private AppliTicTacToe appliTicTacToe ;
	private AppliMorpion appliMorpion ;
	private AppliXAlignements appliXAlignement ;
	private AppliForme appliForme ;
	private AppliPermutation appliPermutation ;
	
	public int getChoixAccueil() {
		return choixAccueil;
	}

	public int getChoixJouer() {
		return choixJouer;
	}

	/*
	 * Securise la selection du choix dans le Menu
	 */
	private void setChoixAccueil(int choixAccueil) {
		this.choixAccueil = choixAccueil;
	}

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
				appliTicTacToe.lancerParti() ;
			if (choixJouer == 3)
				appliMorpion.lancerParti() ; 
			if (choixJouer == 4)
				appliXAlignement.lancerParti() ; 
			if (choixJouer == 5)
				appliForme.lancerParti() ; 
			if (choixJouer == 6)
				appliPermutation.lancerParti() ; 
	}

	
}
