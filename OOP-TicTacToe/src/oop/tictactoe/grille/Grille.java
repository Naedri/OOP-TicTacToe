package oop.tictactoe.grille;

public class Grille {
	
	private int colonnes;
	private int lignes;
	private Jeton[][] grille ;
	 
//	for(String sousTab[] : tab)
//	{
//	  i = 0;
//	  for(String str : sousTab)
//	  {
//	    System.out.println("La valeur de la nouvelle boucle est  : " + str);
//	    System.out.println("La valeur du tableau à l'indice ["+j+"]["+i+"] est : " + tab[j][i]);
//	    i++;
//	  }
//	  j++;
//	}
	
	//constructeur
	public Grille(int hauteur, int largeur) {
		this.colonnes = largeur;
		this.lignes = hauteur;
		this.grille = new Jeton[lignes][colonnes];
	}

	public Grille() {
		this.colonnes = 3 ;
		this.lignes = 3 ;
		this.grille = new Jeton[3][3];
	}
	
	//getteur
	public int getColonnes() {
		return colonnes;
	}
	public int getLignes() {
		return lignes;
	}
	
	
	public String toString() {
		String sGrille ;
		//il faut d'abord parcourir les reference de ligne de jeton pour accéder aux jetons
		for (Jeton[] reference : grille) {
			for (Jeton cellule : reference) {
				cellule.jetonToCellule();
			}
		}
		return sGrille ;
	}
		
//		for (String cellule : grille)
//		for (unsigned int lig = getHauteur(t); lig >= 1; --lig) {
//			d += sprintf(s + d, "%i ", lig);
//			for (char col = 'a'; col <= 'a'+getLargeur(t)-1; ++col)
//				d += sprintf(s + d, "%c ", get(t, col, lig));
//			d += sprintf(s + d, "\n");
//		}
//		
//		d += sprintf(s + d, "  ");
//		
//		for (char col = 'a'; col <= 'a'+getLargeur(t)-1; ++col)
//			d += sprintf(s + d, "%c ", col);
//		d += sprintf(s + d, "\n");
		
	
}
