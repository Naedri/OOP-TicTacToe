package oop.tictactoe.appli;

import oop.tictactoe.tours.TourTicTacToe;
import oop.tictactoe.jouer.*;
import oop.tictactoe.grille.*;


public class PartieTicTacToe  implements In_Partie {
	
	protected Joueur joueur1 ;
	protected Joueur joueur2 ;
	protected Match match ;
	protected Jeton[][] grille;
//	protected Grille grille ;
	protected int nbrAlign ; //nombre de jetons a aligner
	
	public PartieTicTacToe() {
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		grille = new Jeton[3][3];
		viderGrille();
		match = new Match(1);//nombre de point max = 1
		nbrAlign = 3 ;
	}
	
	public PartieTicTacToe(int choixGrilleLigne, int choixGrilleColonne) {
		assert(choixGrilleLigne >=0 && choixGrilleColonne >= 0);
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		grille = new Jeton[choixGrilleLigne][choixGrilleColonne];
		viderGrille();
		match = new Match(1); //nombre de point max = 1
		nbrAlign = 3 ;
	}
	
	public PartieTicTacToe(int choixGrilleLigne, int choixGrilleColonne, int choixNbrAlignements) {
		assert(choixGrilleLigne >=0 && choixGrilleColonne >= 0);
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		grille = new Jeton[choixGrilleLigne][choixGrilleColonne];
		viderGrille();
		match = new Match(1); //nombre de point max = 1
		nbrAlign = choixNbrAlignements ;
	}

	public void lancerPartie() {
		afficherGrille();
		//on fait des tours
		while(!(match.estTourMax() || match.getVictoire())) {
			match.tourDebut();
			
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;
			
			System.out.println(In_Interaction.afficherMessageDebutTour(joueurActuel));
			TourTicTacToe tour = new TourTicTacToe(grille, joueurActuel, nbrAlign);

			tour.jouerCoup();
			afficherGrille();
			tour.evaluerCoup();
			
			match.evalVictoireParPointMax (joueurActuel);

//			System.out.println(In_Interaction.afficherMessageFinTour(joueurActuel));
			System.out.println(In_Interaction.afficherMessageResultat(match, joueurActuel));
		}
		//on compte les points
//		System.out.println(In_Interaction.afficherMessageResultat(match,joueur1,joueur2));;

	}
	
	
	// ******* METHODE GRILLE *******
	// ******* METHODE GRILLE GETTEURS *******
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

		// ******* METHODE GRILLE AFFICHAGE *******
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
		
		// ******* METHODE GRILLE EVALUATION *******
		// ******* METHODE GRILLE EVALUATION EST VIDE *******
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
		
		// ******* METHODE GRILLE EVALUATION JETON *******
		
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
		
		// ******* METHODE GRILLE EVALUATION CELLULE *******
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
		
		// ******* METHODE GRILLE PLACEMENT JETON *******
		
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
		
		
		
	
}
