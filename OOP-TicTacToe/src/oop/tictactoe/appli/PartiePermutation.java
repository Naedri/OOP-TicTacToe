package oop.tictactoe.appli;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import oop.tictactoe.grille.Jeton;
import oop.tictactoe.jouer.*;


public class PartiePermutation extends CA_Grille_Partie_alignement_fermeture {
		
	private int nbrAlign ;
	private int[] saisieCellule ;
	private int[] saisieCellule2 ;

	//la partie durera tant qu il y aura des jetons a ligner pour un des deux joueurs
	
	public PartiePermutation(int nbrLignes, int nbrColonnes, int nbrAlign) {
		super(nbrLignes, nbrColonnes,  permutMax(nbrLignes, nbrColonnes, nbrAlign), 0);
		remplirAleaGrille();
		this.saisieCellule = new int[2];
		this.saisieCellule2 = new int[2];
		
		this.nbrAlign = nbrAlign;
		int choixNbrAlignMax = (nbrColonnes >= nbrLignes) ? nbrLignes : nbrColonnes;
		assert (nbrAlign <= choixNbrAlignMax); //ce nombre ne doit pas être plus grand que le nombre de colonnes ou de lignes de votre grille
		}
	
	public PartiePermutation(int nbrLignes, int nbrColonnes) {
		super(nbrLignes, nbrColonnes,  permutMax(nbrLignes, nbrColonnes, 3), 0);
		remplirAleaGrille();
		this.saisieCellule = new int[2];
		this.saisieCellule2 = new int[2];
		
		this.nbrAlign = 3;
		int choixNbrAlignMax = (nbrColonnes >= nbrLignes) ? nbrLignes : nbrColonnes;
		assert (nbrAlign <= choixNbrAlignMax); //ce nombre ne doit pas être plus grand que le nombre de colonnes ou de lignes de votre grille
	}
		
	public PartiePermutation() {
		super(5,6,permutMax(5,6,3),0);
		remplirAleaGrille();
		this.saisieCellule = new int[2];
		this.saisieCellule2 = new int[2];
	}
	
	static int permutMax(int ligne, int colonne, int align) {
		assert(ligne>0 && colonne >0 && align >0);
		int pointMax = (ligne*colonne) - ((ligne*colonne)%2) ;
		pointMax /= 2 ;
		pointMax /= align;
		return pointMax;
		
	}
	
	
	// ******* METHODE GRILLE *******
	// ******* METHODE GRILLE REMPLISSAGE ALEATOIRE *******
	/**
	 * remplissage aléatoire avec JEONT caractère ouvert,  
	 * à partir d une liste de JETON finie de taille égale à celle de la grille
	 * tirage aléatoire sans remise de la liste de JETON dans chacune des cellules
	 * si le nombre de jeton est impair le dernier jeton sera determine de maniere aleatoire
	 * Ne pourra etre appellee que si la grille est vide
	 */
	private void remplirAleaGrille() {
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
		for (Jeton[] ligneJeton : this.getGrille()) {
			for (int i = 0; i < ligneJeton.length; i++) {
				ligneJeton[i] = listeJetons.getFirst();
				listeJetons.removeFirst();	
			}
		}
	}

	@Override
	protected void jouerCoup(Joueur joueurActuel) {
		boolean saisieCorrectejouerCoup = false;

		while (!saisieCorrectejouerCoup) {
			System.out.println("Vous allez choisir les deux cases pour permutation.\n");
			saisieCellule = In_Interaction.saisirCellule( getGrille());
			System.out.println(In_Interaction.afficherMessageCellule(joueurActuel, saisieCellule));
			saisieCellule2 = In_Interaction.saisirCellule( getGrille());
			System.out.println(In_Interaction.afficherMessageCellule(joueurActuel, saisieCellule2));
			
			//les jetons doivent etre de cases differentes
			if(  sontDifferentes(saisieCellule[0], saisieCellule[1],saisieCellule2[0], saisieCellule2[1]) ) {
				// les jetons doivent etre adjacents
				if (sontAdjacents(saisieCellule[0], saisieCellule[1],saisieCellule2[0], saisieCellule2[1]) ) {
					saisieCorrectejouerCoup = true ;
				}
				else
					System.out.println("La case selectionnee n est pas adjacente a la premiere case selectionnee. Veuillez recommencer la saisie des deux cellules.\n");
			}
			else
				System.out.println("La case selectionnee est identique a la premiere case selectionnee. Veuillez recommencer la saisie des deux cellules.\n");
			
		}
		permutationJeton(saisieCellule[0], saisieCellule[1],saisieCellule2[0], saisieCellule2[1]);
		System.out.println(In_MessagesPermutation.afficherMessageCoupJoue(joueurActuel, saisieCellule, saisieCellule2));
	}
	
	/**
	 * on ne peut pas ganger d alignements avec des jetons qui sont deja fermes
	 * comme on peut modifier les jetons fermes il faut limiter les jetons evalues au jeton ouverts
	 */
	@Override
	protected void evaluerCoup(Joueur joueur1, Joueur joueur2) {

		assert(saisieCellule != null);//on oblige le joueur a avoir jouer un coup
		assert(saisieCellule2 != null);//on oblige le joueur a avoir jouer un coup

		if ( estOuvert(saisieCellule[0], saisieCellule[1])) {
			Joueur joueurMarquant = null ;
			if (nbrDirectAvecAlign(saisieCellule[0], saisieCellule[1], nbrAlign) >=1 ) {
				if ( getCellule(saisieCellule[0], saisieCellule[1]).estEgal(joueur1.getJeton() ) ) {
					joueurMarquant = joueur1;
				}
				else {
					joueurMarquant = joueur2;
				}
				fermeAlignementXD(saisieCellule[0], saisieCellule[1], nbrAlign);
				System.out.println(In_Interaction.afficherMessageCoupMarquant(joueurMarquant));
				 afficherGrille();
				joueurMarquant.marquerPoint();
			}
		}
		if ( estOuvert(saisieCellule2[0], saisieCellule2[1])) {
			Joueur joueurMarquant = null ;
			if (nbrDirectAvecAlign(saisieCellule2[0], saisieCellule2[1], nbrAlign) >=1 ) {
				if ( getCellule(saisieCellule2[0], saisieCellule2[1]).estEgal(joueur1.getJeton() ) ) {
					joueurMarquant = joueur1;
				}
				else {
					joueurMarquant = joueur2;
				}
				fermeAlignementXD(saisieCellule2[0], saisieCellule2[1], nbrAlign);
				System.out.println(In_Interaction.afficherMessageCoupMarquant(joueurMarquant));
				 afficherGrille();
				joueurMarquant.marquerPoint();
			}
		}

	}
	
	// ******* METHODE GRILLE PERMUTATION *******
	/**
	 * permute deux jetons de la grille
	 * verifie que les deux jetons electionnes sont dans la grille
	 * verifie que les deux jetons sont adjacents
	 * verifie que les les cellules sont rempli de JETON
	 * Il n est PAS verifie que les deux JETONS a permuter soient ouverts 	 
	 * @param ligne1
	 * @param colonne1
	 * @param colonne1
	 * @param ligne2
	 */
	public void permutationJeton(int ligne1, int colonne1, int ligne2, int colonne2) {
		assert( sontDifferentes(ligne1, colonne1, ligne2, colonne2)); //les jetons doivent etre differents
		assert (ligne1 <  getLignes() && ligne1 >= 0); //la cellule doit être dans la grille
		assert (colonne1 <  getColonnes() && colonne1 >= 0); //la cellule doit être dans la grille
		assert (ligne2 <   getLignes() && ligne2 >= 0); //la cellule doit être dans la grille
		assert (colonne2 <   getColonnes() && colonne2 >= 0); //la cellule doit être dans la grille

		assert (!  estVideCellule(ligne1, colonne1)); // la cellule ne doit pas etre vide		
		assert (!  estVideCellule(ligne2, colonne2)); // la cellule ne doit pas etre vide

		assert(sontAdjacents(ligne1, colonne1, ligne2, colonne2)); //les jetons doivent etre adjacents

		//si les jetons sont differents
		if (!   getCellule(ligne1,colonne1).estEgal(  getCellule(ligne2,colonne2)) ) {
			Jeton jtemp =   getCellule(ligne1,colonne1);
			  placerJeton(  getCellule(ligne2,colonne2), ligne1, colonne1);
			  placerJeton(jtemp, ligne2, colonne2);
		}
	}
}

