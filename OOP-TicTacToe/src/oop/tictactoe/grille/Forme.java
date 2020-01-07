package oop.tictactoe.grille;

import oop.tictactoe.appli.PartieTicTacToe;

public enum Forme {
	
	CARRE("carre",  new int[]{1,1,1,1} ,  new int[]{2,4,6,0} ,  new int[][]{{0,1,2,1,1},{1,2,1,0,1}},  new int[][]{{1,2},{1,4},{1,6},{1,0}} ),	
	LOSANGE("losange",  new int[]{1,1,1,1} ,  new int[]{1,3,5,7} ,  new int[][]{{0,1,2,1},{1,2,1,0}},  new int[][]{{1,1},{1,3},{1,5},{1,7}} ),
	CROIX("croix",  new int[]{1,1,1,1,1} ,  new int[]{1,3,5,7,6} ,  new int[][]{{0,1,2,1,1},{1,2,1,0,1}},  new int[][]{{1,1},{1,3},{1,5},{1,7},{1,6}} );

	private String formeStr ;
	private int[] distance;
	private int[] orientation;
	private int[][] tabGrilleModele ;
	private int[][] chemin;
	private static String[] listFormesDispo = new String[] {"carre","losange","croix"};
	
	/**
	 * choixForme 1 pour Carre - 2 pour losange - 3 pour croix
	 * @param choixForme 1 pour Carre - 2 pour losange - 3 pour croix
	 */
	private Forme(String formeStr, int[] distance, int[]orientation, int[][]tabGrilleModele, int[][]chemin ) {
		this.formeStr = formeStr ;
		this.distance = distance ;
		this.orientation = orientation;
		this.tabGrilleModele = tabGrilleModele;
		this.chemin = chemin;

	}

	/**
	 * transForme renvoie une forme dont les indices sont decale de int decalageIndice
	 * @param decalageIndice
	 * @return
	 */
	public Forme transForme (int decalageIndice) {
		Forme formeTrans = this ;
		
		decalageIndice %= getNbrPoint();
		
		int indiceDecale ;
	    		
		for (int i=0 ;i < getNbrPoint(); ++i) {
			indiceDecale = (decalageIndice+i) % getNbrPoint();
			formeTrans.distance[indiceDecale]= distance[i];
			formeTrans.orientation[indiceDecale]= orientation[(i)] ;
		}
				
		for (int i = 0; i < orientation.length; ++i) {
			formeTrans.chemin[i][0] = formeTrans.distance[i];
			formeTrans.chemin[i][1] = formeTrans.orientation[i];
		}
		
		return formeTrans;
	}

	public String toStringGrilleModele() {
		PartieTicTacToe partie = new PartieTicTacToe();			
		for (int i = 0; i < tabGrilleModele[0].length; ++i) {
			partie.placerJeton(Jeton.JETON_X, tabGrilleModele[0][i], tabGrilleModele[1][i]);
		}
		return partie.toStringGrille() ;
	}
	
	public static String[] getListFormesDispo() {
		return listFormesDispo;
	}
	
	public static String toStringFormeDispo() {
		String sFormeDispo = "";
		sFormeDispo += "Les formes disponibles sont :";
		int indice = 1 ;
		for (String forme: listFormesDispo) {
			sFormeDispo += "\n<"+indice +"> " + forme ;
			++indice;
			}
		sFormeDispo += ".\n";
		return sFormeDispo;
	}
	
	public String toStringConsigne() {
		String sConsigne = "";
		sConsigne += "Pour realiser la forme suivante : '" + formeStr +"',\n il faut placer les jetons de la manière suivante :\n";
		sConsigne += toStringGrilleModele();
		return sConsigne ;
	}
	
	public static String toStringFormeDispoConsigne() {
		String formeMenu = "";
		formeMenu += toStringFormeDispo();
		
	    for (Forme formeExemple : Forme.values())
			formeMenu += formeExemple.toStringConsigne();
		return formeMenu ;
	}
	
	public String toStringFormeChoisie() {
		String determinantForme ;
		if (this == CARRE) {
			determinantForme = "le";
		}
		else {
			determinantForme = "la";
		}
		String sChoisie ="";
		sChoisie += "La forme choisie est " + determinantForme +  " " + formeStr + ".\n" ;
		return sChoisie;
	}
	
	public int[][] getTabGrilleModele() {
		return tabGrilleModele;
	}
	
	public int[] getDistance() {
		return distance;
	}
	public int[] getOrientation() {
		return orientation;
	}
	public int[][] getChemin(){
		return chemin;
	}
	public String getFormeStr() {
		return formeStr;
	}
	public int[][] getFormeGrille() {
		return tabGrilleModele;
	}
	public int getNbrPoint() {
		return chemin.length;
	}
}
