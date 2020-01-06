package oop.tictactoe.grille;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import oop.tictactoe.grille.Jeton;

public class Grille implements In_Grille {

	private Jeton[][] grille;

	// constructeur
	public Grille(int nbrLignes, int nbrColonnes) {
		this.grille = new Jeton[nbrLignes][nbrColonnes];
		this.viderGrille(); //initialisation
	}
	public Grille(int nbrLignes, int nbrColonnes, boolean aleatoire) {
		this.grille = new Jeton[nbrLignes][nbrColonnes];
		this.viderGrille(); //vidange necessaire pour le assert de remplirAleaGrille
		if (aleatoire) {
			this.remplirAleaGrille(); //initialisation
		}
	} 
	
	
	// si il n y a pas de taille préciser on fait une grille de TIC TAC TOE de 3*3
	public Grille() {
		this.grille = new Jeton[3][3];//ce sont des references qui s attendent a recevoir des jetons mais ils sont nulls au depart
		this.viderGrille(); //initialisation
	}

	public Grille(boolean aleatoire) {
		this.grille = new Jeton[3][3];//ce sont des references qui s attendent a recevoir des jetons mais ils sont nulls au depart
		this.viderGrille(); //initialisation
		if (aleatoire) {
			this.remplirAleaGrille(); //initialisation
		}
		}
	// getters
	public int getColonnes() {
		return this.grille[0].length;
	}
	
	public int getLignes() {
		return this.grille.length;
	}
	public int getNbrCellules() {
		return this.grille[0].length * this.grille.length ;
	}
	
	/**
	 *
	 * @param ligne de la cellule indique  le 0 compte
	 * @param colonne de la cellule indique  le 0 compte
	 * @return un JETON (contenant un symbole X ou O ou x ou o et un boolean pour indiquer l ouverture
	 */
	public Jeton getCellule(int ligne, int colonne) {
		assert (ligne < this.grille.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grille[0].length && colonne >= 0); //la cellule doit être dans la grille
		return this.grille[ligne][colonne];
	}

	/**
	 * toString
	 * @return une chaine de caractère contenant l'etat de la grille
	 */
	public String toStringGrille() {
		String sGrille = " " ; // decalage pour les noms de lignes en dizaines
		int ligne = 0;

		// ligne des indices de colonnes
		for (int i = 1; i <= this.grille[0].length; ++i)
			if (i<10) {
				sGrille += " " + " " + " " + i ;
			}
			else
				sGrille += " " + " " + i ;
		sGrille += "\n";
		++ligne;

		// il faut d'abord parcourir les reference de ligne de jeton pour acceder aux
		// jetons
		for (Jeton[] ligneJeton : grille) {
			if (ligne<10) {
				sGrille += " " + ligne;
			}
			else
				sGrille += ligne;
			for (int i = 0; i < ligneJeton.length; i++) {
				sGrille += " " + ligneJeton[i].toString();
			}
			sGrille += "\n";
			++ligne;
		}
		return sGrille;
	}
	
	/**
	 * 
	 */
	public void afficherGrille() {
		System.out.println(this.toStringGrille());
	}

	// methodes specifiques
	/**
	 * estVideCellule
	 * @param ligne de la cellule de la grille le 0 compte
	 * @param colonne de la cellule de la grille  le 0 compte
	 * @return la cellule est elle vide ?
	 */
	public boolean estVideCellule(int ligne, int colonne) {
		assert (ligne < this.grille.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grille[0].length && colonne >= 0); //la cellule doit être dans la grille
		return getCellule(ligne, colonne).estVideJeton();
	}
	
	/**
	 * viderGrille 
	 * permet de mettre tout les jetons de la grille a JETON_VIDE 
	 * ne pourra être appellee que si la grille contient des references nulles
	 */
	private void viderGrille() {
		for (Jeton[] ligneJeton : this.grille) {
			for (int i = 0; i < ligneJeton.length; i++) {
				ligneJeton[i] = Jeton.JETON_VIDE;
			}
		}
	}

	/**
	 * estPleineGrille
	 * @return toutes les cellules de la grille contiennent elles JETON_VIDE ?
	 */
	public boolean estPleineGrille() {
		boolean estPleine = true;
		for (Jeton[] ligneJeton : this.grille) {
			for (int i = 0; i < ligneJeton.length; i++) {
				if (ligneJeton[i].estVideJeton()) {
					estPleine = false;
				}
			}
		}
		return estPleine ;
	}
	/**
	 * toutes les cellules de la grille sont ils vides ?
	 * @return
	 */
	public boolean estVideGrille() {
		boolean estVide = true;
		for (Jeton[] ligneJeton : this.grille) {
			for (int i = 0; i < ligneJeton.length; i++) {
				if (! ligneJeton[i].estVideJeton()) {
					estVide = false;
				}
			}
		}
		return estVide ;
	}
	
	//remplissageGrille
	/**
	 * remplissage aléatoire avec JEONT caractère ouvert,  
	 * à partir d une liste de JETON finie de taille égale à celle de la grille
	 * tirage aléatoire sans remise de la liste de JETON dans chacune des cellules
	 * si le nombre de jeton est impair le dernier jeton sera determine de maniere aleatoire
	 * Ne pourra etre appellee que si la grille est vide
	 */
	public void remplirAleaGrille() {
		assert(this.estVideGrille());
		//initialisation de la liste des jetons 
		LinkedList<Jeton> listeJetons= new LinkedList<Jeton>(); //Linked list car acces terminaux constant
//		//initialisation de la liste des jetons si le nombre de jeton est impair il y aura un jeton O de plus
//		for (int i = 0; i < getNbrCellules(); ++i) {
//			listeJetons.addLast(Jeton.values()[(i+1) %2 +1]);
//		}
		//initialisation de la liste des jetons
		if (getNbrCellules() % 2 == 0 ) {
			for (int i = 0; i < getNbrCellules(); ++i) {
				listeJetons.addLast(Jeton.values()[(i%2) +1]);
			}
		}
		else {
			Random r = new Random();
			int valeur = r.nextInt(2) + 1; //valeur entre 1 et 2
			listeJetons.addLast(Jeton.values()[valeur]);
			for (int i = 1; i < getNbrCellules(); ++i) { //on ne commence plus a 0 mais a 1
				listeJetons.addLast(Jeton.values()[(i%2) +1]);
			}
		}
		
		//brassage de la liste des jetons
		//https://www.tutorialspoint.com/java/util/collections_shuffle.htm
		Collections.shuffle(listeJetons);
		
	    //insertion de la liste de jetons dans la grille
		//boucle for each pour la Linked list qui travaille difficilement avec des indices
		for (Jeton[] ligneJeton : this.grille) {
			for (int i = 0; i < ligneJeton.length; i++) {
				ligneJeton[i] = listeJetons.getFirst();
				listeJetons.removeFirst();	
			}
		}
	}
	
	/**
	 * comparaison de grille
	 * utilisable pour s assurer que des grilles generees aléatoirement sont differentes
	 * @param grille1 de jeton
	 * @param grilleCible de jeton
	 * @return true si grille1 comporte au moins un jeton different de grille2
	 */
	public boolean estEgale(Grille grilleCible) {
		
		//comparaison de taille
		if (this.getNbrCellules() != grilleCible.getNbrCellules()) {
			return false;
		}
		
		//comparaison des cellules
		for (int i=0;i < this.getLignes(); ++i) {
			for (int j = 0; j < this.getColonnes(); ++j) {
				Jeton jetonCible1 = this.getCellule(i, j);
				Jeton jetonCible2 = grilleCible.getCellule(i, j);
				if (! (jetonCible1.estEgal(jetonCible2))) {
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	/**
	 * place un jeton dans la grille
	 * verifie que la cellule ciblé est vide
	 * @param jeton à placer (seuls JETON_X ou JETON_O sont autorisés)
	 * @param ligneCible de la cellule de la grille  le 0 compte
	 * @param colonneCible de la cellule de la grille  le 0 compte
	 */
	public void placerJeton(Jeton jeton, int ligneCible, int colonneCible) {
		assert (ligneCible < this.grille.length && ligneCible >= 0); //la cellule doit être dans la grille
		assert (colonneCible < this.grille[0].length && colonneCible >= 0); //la cellule doit être dans la grille
		assert (estVideCellule(ligneCible, colonneCible)); // la cellule doit etre vide
		assert (!jeton.estVideJeton() && jeton.estOuvert()); // le jeton place ne doit pas etre vide ni ferme
		this.grille[ligneCible][colonneCible] = jeton;
	}
	
	public void placerJetonFerme(Jeton jeton, int ligneCible, int colonneCible) {
		assert (ligneCible < this.grille.length && ligneCible >= 0); //la cellule doit être dans la grille
		assert (colonneCible < this.grille[0].length && colonneCible >= 0); //la cellule doit être dans la grille
		assert (estVideCellule(ligneCible, colonneCible)); // la cellule doit etre vide
		assert (!jeton.estVideJeton()); // le jeton place ne doit pas etre vide
		assert(!jeton.estOuvert()); //le jeton doit etre ferme
		this.grille[ligneCible][colonneCible] = jeton;
	}
	
	public void placerJetonAdjacent(Jeton jeton, int ligneCible, int colonneCible) {
		assert (ligneCible < this.grille.length && ligneCible >= 0); //la cellule doit être dans la grille
		assert (colonneCible < this.grille[0].length && colonneCible >= 0); //la cellule doit être dans la grille
		assert (estVideCellule(ligneCible, colonneCible)); // la cellule doit etre vide
		assert (!jeton.estVideJeton() && jeton.estOuvert()); // le jeton place ne doit pas etre vide ni ferme
	
		assert(existeAdjacent(ligneCible, colonneCible));
		
		placerJeton(jeton,  ligneCible, colonneCible) ;

	}
	
	//permutationJeton
	/**
	 * permute deux jetons de la grille
	 * verifie que les deux jetons electionnes sont dans la grille
	 * verifie que les deux jetons sont adjacents
	 * verifie que les les cellules sont rempli de JETON
	 * Il n est PAS verifie que les deux JETONS a permuter soient ouverts 	 
	 * @param ligneJ1
	 * @param colonneJ1
	 * @param colonneJ1
	 * @param ligneJ2
	 */
	public void permutationJeton(int ligneJ1, int colonneJ1, int ligneJ2, int colonneJ2) {
		assert(ligneJ1 != ligneJ2 || colonneJ1 != colonneJ2); //les jetons doivent etre differents

		assert (ligneJ1 < this.grille.length && ligneJ1 >= 0); //la cellule doit être dans la grille
		assert (colonneJ1 < this.grille[0].length && colonneJ1 >= 0); //la cellule doit être dans la grille
		assert (ligneJ2 < this.grille.length && ligneJ2 >= 0); //la cellule doit être dans la grille
		assert (colonneJ2 < this.grille[0].length && colonneJ2 >= 0); //la cellule doit être dans la grille
				
		assert (!estVideCellule(ligneJ1, colonneJ1)); // la cellule ne doit pas etre vide		
		assert (!estVideCellule(ligneJ2, colonneJ2)); // la cellule ne doit pas etre vide

		assert(sontAdjacents(ligneJ1, colonneJ1, ligneJ2, colonneJ2)); //les jetons doivent etre adjacents

		//si les jetons sont differents
		if (! getCellule(ligneJ1,colonneJ1).estEgal(getCellule(ligneJ2,colonneJ2)) ) {
			Jeton jtemp = getCellule(ligneJ1,colonneJ1);
			grille[ligneJ1][colonneJ1] = getCellule(ligneJ2,colonneJ2);
			grille[ligneJ2][colonneJ2] = jtemp ;
		}
	}

	//nextJeton avec Direction
	
	/**
	 * Pour les elements donnes, existeNextCellule permet de savoir si la cellule image est comprise dans la grille
	 * Pas d indication de la nature du jeton
	 * Pas de limite de profondeur
	 * cellule image cad :
	 * càd le jeton contenu dans la cellule 
	 * projetee depuis la cellule de la grille a ligne,colonne
	 * vers la direction donnee a la profondeur/distance donnee
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	public boolean existeNextCellule(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		
		boolean existe = false;
		
		int ligneCible = profondeur * direction.getDligne() + ligne ;
		int colonneCible =  profondeur * direction.getDcolonne() + colonne ;
		
		if(ligneCible < getLignes() && ligneCible >= 0 
				&& colonneCible < getColonnes() && colonneCible >= 0) {
			existe = true;
		}
		return existe;
	}

	
	/**
	 * coordNextJeton permet de savoir quelles sont les coordonnes (ligne,colonne) du jeton image
	 * càd le jeton contenu dans la cellule 
	 * projetee depuis la cellule de la grille a ligne,colonne
	 * vers la direction donnee a la profondeur/distance donnee
	 * Le jeton peut etre vide
 	 * Pas de limite de profondeur
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	public int[] coordNextJeton(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert(existeNextCellule(ligne, colonne, profondeur, direction));
		
		int[] coord = new int[2];
		int ligneCible = profondeur * direction.getDligne() + ligne ;
		int colonneCible =  profondeur * direction.getDcolonne() + colonne ;
		
		coord[0]=ligneCible;
		coord[1]=colonneCible;
		return coord ;
	}

	/**
	 * getNextJeton permet d obtenir le jeton image 
	 * càd le jeton contenu dans la cellule 
	 * projetee depuis la cellule de la grille a ligne,colonne
	 * vers la direction donnee a la profondeur/distance donnee
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	public Jeton getNextJeton (int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert(existeNextCellule(ligne, colonne, profondeur, direction));
		
		int[] coord = coordNextJeton(ligne, colonne, profondeur, direction);
	
		return getCellule(coord[0], coord[1]);
	}
	
	/**
	 * existe il dans les cellules voisines de la cellule donnee [ligne,colonne]
	 * des jetons non vides
	 * la cellule peut etre vide mais doit etre dans la grille
	 * @param ligne
	 * @param colonne
	 * @return
	 */
	public boolean existeAdjacent(int ligne, int colonne) {
		assert (ligne < this.grille.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grille[0].length && colonne >= 0); //la cellule doit être dans la grille
		
		for (Direction oneDirection : Direction.values())
			if (existeNextCellule(ligne, colonne, 1, oneDirection)) {
				if (! getNextJeton(ligne, colonne, 1, oneDirection).estVideJeton()) {
					return true;
				}
			}
		return false;
		
	}
	

	/**
	 * les cellules donnees sont elles adjacents
	 * doivent etre des ellules differentes
	 * Il n est pas verifie que les cellules comprennent des jetons non vides
	 * @param ligne1
	 * @param colonne1
	 * @param colonne2
	 * @param ligne2
	 * @return
	 */
	public boolean sontAdjacents(int ligne1, int colonne1, int ligne2, int colonne2) {
		assert(sontDifferentes(ligne1, colonne1, ligne2, colonne2)); //les jetons doivent etre differents

		assert (ligne1 < this.grille.length && ligne1 >= 0); //la cellule doit être dans la grille
		assert (colonne1 < this.grille[0].length && colonne1 >= 0); //la cellule doit être dans la grille
		assert (ligne2 < this.grille.length && ligne2 >= 0); //la cellule doit être dans la grille
		assert (colonne2 < this.grille[0].length && colonne2 >= 0); //la cellule doit être dans la grille
		
		boolean adjacent = false ;
		
		if ( (Math.abs(ligne1-ligne2) <= 1) && (Math.abs(colonne1-colonne2) <= 1) ) {
			adjacent = true ;
		}
		
		return adjacent;
	}
	
	/**
	 * Les cellules sont elles de coordonnees differentes
	 * @param ligne1
	 * @param colonne1
	 * @param ligne2
	 * @param colonne2
	 * @return
	 */
	public boolean sontDifferentes(int ligne1, int colonne1, int ligne2, int colonne2) {
		return (ligne1 != ligne2 || colonne1 != colonne2);
	}
	
	//morpion
	public void ouvertToFermeJeton(int ligne, int colonne) {
		grille[ligne][colonne] = grille[ligne][colonne].ouvertToFerme();		
	}
	
	/**
	 * renvoie le nombre de jeton observe dans une grille
	 * @param jetonEvalue evalue
	 * @return le nombre de jeton observe dans une grille
	 */
	public int getNbrJeton(Jeton jetonEvalue) {
		int nbr = 0 ;
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				if (getCellule(i, j).estEgal(jetonEvalue)) {
					++nbr;
				}
			}
		}
		return nbr ;
	}
	
	
}
