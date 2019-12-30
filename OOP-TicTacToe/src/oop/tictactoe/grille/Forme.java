package oop.tictactoe.grille;

public class Forme {

	//il ne sera dessine que des formes symetriques multiaxes
	private int[] distance;
	private int[] orientation;
	private int[][] chemin;
	private int formeNum ;
	private String formeStr ;
	private int[][] grilleModele ;
	private static String[] listFormesDispo;

	public Forme(int choixForme) {
		listFormesDispo = new String[] {"carre","losange","croix"};
		
		switch (choixForme) {
		//niveau de complexite croissant
			case 1:
				//carre
				formeNum=1;
				formeStr = "carre";
				distance = new int[] {1,1,1,1} ;
				orientation = new int[] {0,6,4,2};
				//{Direction.EST,Direction.SUD,Direction.OUEST,Direction.NORD}) ;
				grilleModele = new int[][]{{0,0,1,1},{0,1,1,0}}; // {ligne} {colonne}
				break ;
				
			case 2 :
				//losange
				formeNum=2;
				formeStr = "losange";
				distance = new int[]{1,1,1,1};
				orientation = new int[]{1,7,5,3};
				//{Direction.NORD_EST,Direction.SUD_EST,Direction.SUD_OUEST,Direction.NORD_OUEST}) ;
				grilleModele = new int[][]{{0,1,2,1},{1,2,1,0}}; // {ligne} {colonne}
				break;
	
			case 3 :
				//croix
				formeNum = 3;
				formeStr = "croix";
				distance = new int[]{1,1,1,1,1} ;
				orientation = new int[]{0,6,4,2};
				//{Direction.NORD_EST,Direction.SUD_EST,Direction.SUD_OUEST,Direction.NORD,Direction.OUEST}),
				grilleModele = new int[][]{{0,1,2,1,1},{1,2,1,0,1}}; // {ligne} {colonne}
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

	public String toStringGrilleModele() {
		Grille grille = new Grille(3,3);				
		for (int i = 0; i < grilleModele.length; ++i) {
			grille.placerJeton(Jeton.JETON_X, grilleModele[i][0], grilleModele[i][1]);
		}
		return grilleModele.toString() ;
	}
	
	public String toStringFormeDispo() {
		String sFormeDispo = "";
		sFormeDispo += "Les Formes dispo sont :";
		for (String forme: listFormesDispo) {
			sFormeDispo += "\n- " + forme ;
			}
		sFormeDispo += ".\n";
		return sFormeDispo;
	}
	
	public String toStringConsigne() {
		String sConsigne = "";
		sConsigne += "Pour realiser la forme suivante : '" + formeStr +"',\n il faut placer les jetons de la manière suivante :\n";
		sConsigne += toString();
		return sConsigne ;
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
	
//	public void parcourirPointForme(Grille grille, int ligne, int colonne) {
//		;
//	}
//	
//	public boolean parcourirForme(Grille grille, int ligne, int colonne) {
//		return false;
//	}
	
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
		return grilleModele;
	}
	public static String[] getListFormesDispo() {
		return listFormesDispo;
	}
	public int getNbrPoint() {
		return chemin.length;
	}


}
