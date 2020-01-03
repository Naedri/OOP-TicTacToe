package oop.tictactoe.grille;

public class Forme {

	//il ne sera dessine que des formes symetriques multiaxes
	private int[] distance;
	private int[] orientation;
	private int[][] chemin;
	private int formeNum ;
	private String formeStr ;
	private int[][] grilleModele ;
	private static String[] listFormesDispo = new String[] {"carre","losange","croix"};

	public Forme(int choixForme) {
		
		switch (choixForme) {
		//niveau de complexite croissant
			case 1:
				//carre
				formeNum=1;
				formeStr = "carre";
				distance = new int[] {1,1,1,1} ;
				orientation = new int[] {2,4,6,0};
				//{Direction.EST,Direction.SUD,Direction.OUEST,Direction.NORD}) ;
				grilleModele = new int[][]{{0,0,1,1},{0,1,1,0}}; // {ligne} {colonne}
				break ;
				
			case 2 :
				//losange
				formeNum=2;
				formeStr = "losange";
				distance = new int[]{1,1,1,1};
				orientation = new int[]{1,3,5,7};
				//{Direction.NORD_EST,Direction.SUD_EST,Direction.SUD_OUEST,Direction.NORD_OUEST}) ;
				grilleModele = new int[][]{{0,1,2,1},{1,2,1,0}}; // {ligne} {colonne}
				break;
	
			case 3 :
				//croix
				formeNum = 3;
				formeStr = "croix";
				distance = new int[]{1,1,1,1,1} ;
				orientation = new int[]{1,3,5,7,6};
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
	
	
	/**
	 * renvoie une forme dont les indices sont decale de int decalageIndice
	 * @param decalageIndice
	 * @return
	 */
	public Forme transForme (int decalageIndice) {
		Forme formeTrans = new Forme(getFormeNum());
		
		decalageIndice %= getNbrPoint();
		
		int indiceDecale ;
	    		
		for (int i=0 ;i < getNbrPoint(); ++i) {
			indiceDecale = (decalageIndice+i) % getNbrPoint();
			formeTrans.distance[i]= distance[indiceDecale];
			formeTrans.orientation[i]= orientation[(indiceDecale)] ;
		}
				
		for (int i = 0; i < orientation.length; ++i) {
			formeTrans.chemin[i][0] = formeTrans.distance[i];
			formeTrans.chemin[i][1] = formeTrans.orientation[i];
		}
		
		return formeTrans;
	}

	public String toStringGrilleModele() {
		Grille grille = new Grille(3,3);				
		for (int i = 0; i < grilleModele.length; ++i) {
			grille.placerJeton(Jeton.JETON_X, grilleModele[i][0], grilleModele[i][1]);
		}
		return grilleModele.toString() ;
	}
	
	public static String[] getListFormesDispo() {
		return listFormesDispo;
	}
	
	public static String toStringFormeDispo() {
		String sFormeDispo = "";
		sFormeDispo += "Les Formes dispo sont :";
		for (String forme: listFormesDispo) {
			int indice = 1 ;
			sFormeDispo += "\n<"+indice +"> " + forme ;
			++indice;
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
	
	public static String toStringFormeDispoConsigne() {
		String formeMenu = "";
		formeMenu += toStringFormeDispo();
		
		for (int i = 1 ; i <= listFormesDispo.length ; ++i) {
			Forme formeExemple = new Forme(1);
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
		return grilleModele;
	}
	public int getNbrPoint() {
		return chemin.length;
	}
}
