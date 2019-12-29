package oop.tictactoe.mouvements;

import oop.tictactoe.grille.Jeton;
import oop.tictactoe.grille.Grille;

import java.util.LinkedList;

import oop.tictactoe.grille.Direction;

public class MouvementsForme extends MouvementsMorpion  {

	
	//on pourrait plus simplement utiliser des entiers car les directions sont accessibles par des entiers
//	public class ComposantForme {
//		private Direction directionForme ;
//		private int profondeurForme;
//		public ComposantForme(Direction d, int p) {
//			this.directionForme = d;
//			this.profondeurForme = p ;
//		}
//		
//	}
//	private ComposantForme composant ;
	
	public MouvementsForme(Grille grille) {
		super(grille);
		// TODO Auto-generated constructor stub
	}

	private int listeFormeCarre[][] ;
    private int listeFormeCroix[][] ; 
    private int listeFormeTriangle[][] ; 
	
    /**
     * 
     * @param list de couple de direction et profondeur afin de parcourir la forme
     * @return "Pour parcourir la forme il faut faire \n -Xcellules vers le DIRECTION, \n - Xcellules vers le DIRECTION, \n etc."
     */
	public String getlistFormeCarre() {
		//A COMPLETER
		return "caca";
	}
	
    /**
     * 
     * @param list de couple de direction et profondeur afin de parcourir la forme
     * @return "Pour parcourir la forme il faut faire \n -Xcellules vers le DIRECTION, \n - Xcellules vers le DIRECTION, \n etc."
     */
	public String getlistFormeCroix() {
		//A COMPLETER
		return "caca";
	}
	
    /**
     * 
     * @param list de couple de direction et profondeur afin de parcourir la forme
     * @return "Pour parcourir la forme il faut faire \n -Xcellules vers le DIRECTION, \n - Xcellules vers le DIRECTION, \n etc."
     */
	public String getlistFormetriangle() {
		//A COMPLETER
		return "caca";
	}
	
	/**
	 * CARRE
	 * invoqué à chaque nouveau jeton posé 
	 * (ou a chaque fois que le nombre de jeoton ouvert est egal à celui du nombre de jeton impliqué dans la forme) 
	 * lors d une partie,
	 * @param ligne
	 * @param colonne
	 * @return verifier si un nouveau jeton complete une forme
	 */
	public boolean completeFormeCarré(int ligne, int colonne) {
		//A COMPLETER
		//boucle for avec parcoursForme pour chacun des points de la listeForme
		return true ;
	}
	
	/**
	 * CROIX
	 * invoqué à chaque nouveau jeton posé 
	 * (ou a chaque fois que le nombre de jeoton ouvert est egal à celui du nombre de jeton impliqué dans la forme) 
	 * lors d une partie,
	 * @param ligne
	 * @param colonne
	 * @return verifier si un nouveau jeton complete une forme
	 */
	public boolean completeFormeCroix(int ligne, int colonne) {
		//A COMPLETER
		//boucle for avec parcoursForme pour chacun des points de la listeForme
		return true ;
	}
	
	/**
	 * TRIANGLE
	 * invoqué à chaque nouveau jeton posé 
	 * (ou a chaque fois que le nombre de jeoton ouvert est egal à celui du nombre de jeton impliqué dans la forme) 
	 * lors d une partie,
	 * @param ligne
	 * @param colonne
	 * @return verifier si un nouveau jeton complete une forme
	 */
	public boolean completeFormeTriangle(int ligne, int colonne) {
		//A COMPLETER
		//boucle for avec parcoursForme pour chacun des points de la listeForme
		return true ;
	}
	
	/**
	 * permet d aller de point en point de la Forme
	 * @return true si la liste forme a pu etre parcouru et bouclée
	 */
	private boolean parcoursForme( ) {
		//A COMPLETER
		return true ;
	}

}
