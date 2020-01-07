package oop.tictactoe.grille;

import oop.tictactoe.appli.PartieTicTacToe;

public class Forme {

	//il ne sera dessine que des formes symetriques multiaxes
	private int[] distance;
	private int[] orientation;
	private int[][] chemin;
	private int formeNum ;
	private String formeStr ;
	private int[][] tabGrilleModele ;
	private static String[] listFormesDispo = new String[] {"carre","losange","croix"};
	
	/**
	 * choixForme 1 pour Carre - 2 pour losange - 3 pour croix
	 * @param choixForme 1 pour Carre - 2 pour losange - 3 pour croix
	 */
	public Forme(int choixForme) {
		assert(choixForme != 0 && choixForme <=3 );		
		switch (choixForme) {
		//niveau de complexite croissant
			case 1:
				//carre
				formeNum=1;
				formeStr = "carre";
				distance = new int[] {1,1,1,1} ;
				orientation = new int[] {2,4,6,0};
				//{Direction.EST,Direction.SUD,Direction.OUEST,Direction.NORD}) ;
				tabGrilleModele = new int[][]{{0,0,1,1},{0,1,1,0}}; // {ligne} {colonne}
				break ;
				
			case 2 :
				//losange
				formeNum=2;
				formeStr = "losange";
				distance = new int[]{1,1,1,1};
				orientation = new int[]{1,3,5,7};
				//{Direction.NORD_EST,Direction.SUD_EST,Direction.SUD_OUEST,Direction.NORD_OUEST}) ;
				tabGrilleModele = new int[][]{{0,1,2,1},{1,2,1,0}}; // {ligne} {colonne}
				break;
	
			case 3 :
				//croix
				formeNum = 3;
				formeStr = "croix";
				distance = new int[]{1,1,1,1,1} ;
				orientation = new int[]{1,3,5,7,6};
				//{Direction.NORD_EST,Direction.SUD_EST,Direction.SUD_OUEST,Direction.NORD,Direction.OUEST}),
				tabGrilleModele = new int[][]{{0,1,2,1,1},{1,2,1,0,1}}; // {ligne} {colonne}
				break ;
		}
		//creation de la table chemin avec une profondeur et une direction par ligne
		assert (orientation.length == distance.length);
		chemin = new int[orientation.length][2];
		for (int i = 0; i < orientation.length; ++i) {
			chemin[i][0] = distance[i];
			chemin[i][1] = orientation[i];
		}
	}

	/**
	 * transForme renvoie une forme dont les indices sont decale de int decalageIndice
	 * @param decalageIndice
	 * @return
	 */
	public Forme transForme (int decalageIndice) {
		Forme formeTrans = new Forme(getFormeNum());
		
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
		
		for (int i = 1 ; i <= listFormesDispo.length ; ++i) {
			Forme formeExemple = new Forme(i);
			formeMenu += formeExemple.toStringConsigne();
		}		
		return formeMenu ;
		
	}
	
	public String toStringFormeChoisie() {
		String determinantForme ;
		if (formeNum == 1) {
			determinantForme = "le";
		}
		else {
			determinantForme = "la";
		}
		String sChoisie ="";
		sChoisie += "La forme choisie du numero "+ formeNum +" est " + determinantForme +  " " + formeStr + ".\n" ;
		return sChoisie;
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
	public int getFormeNum() {
		return formeNum;
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
