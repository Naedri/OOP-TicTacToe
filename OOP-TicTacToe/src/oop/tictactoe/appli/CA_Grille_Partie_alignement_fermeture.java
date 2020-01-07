package oop.tictactoe.appli;

import oop.tictactoe.grille.Direction;
import oop.tictactoe.grille.Jeton;

public abstract class CA_Grille_Partie_alignement_fermeture extends CA_Grille_Partie_alignement {
	
	public CA_Grille_Partie_alignement_fermeture(int nbrLignes, int nbrColonnes, 
			int nombrePointMax, int nombreTourMax) {
		super(nbrLignes, nbrColonnes, nombrePointMax, nombreTourMax);
		grilleOuvertureJetons = new boolean[nbrLignes][nbrColonnes];
		iniGrilleFermeture();
}

	private boolean[][] grilleOuvertureJetons ;
	
	
	
	
	// ******* METHODE GRILLE *******
	// ******* METHODE GRILLE Fermeture *******
	//les jetons sont au depart ouvert (true)
	private void iniGrilleFermeture() {
		for (boolean[] ligneJeton : grilleOuvertureJetons) {
			for (int i = 0; i < ligneJeton.length; i++) {
				ligneJeton[i] = true;
			}
		}
	}
	public void ouvertToFermeJeton(int ligne, int colonne) {
		assert (ligne < this.grilleOuvertureJetons.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grilleOuvertureJetons[0].length && colonne >= 0); //la cellule doit être dans la grille
		grilleOuvertureJetons[ligne][colonne] = true ;
	}
	public boolean estOuvert(int ligne, int colonne) {
		assert (ligne < this.grilleOuvertureJetons.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grilleOuvertureJetons[0].length && colonne >= 0); //la cellule doit être dans la grille
		return grilleOuvertureJetons[ligne][colonne];
	}
	
	// ******* METHODE GRILLE AFFICHAGE *******
	/**
	 * toString
	 * @return une chaine de caractère contenant l'etat de la grille
	 */
	@Override
	public String toStringGrille() {
		String sGrille = " " ; // decalage pour les noms de lignes en dizaines
		int ligne = 0;
	
		// ligne des indices de colonnes
		for (int i = 1; i <= getColonnes(); ++i)
			if (i<10) {
				sGrille += " " + " " + " " + i ;
			}
			else
				sGrille += " " + " " + i ;
		sGrille += "\n";
		++ligne;
	
		// il faut d'abord parcourir les reference de ligne de jeton pour acceder aux
		// jetons
		for (Jeton[] ligneJeton : getGrille()) {
			if (ligne<10) {
				sGrille += " " + ligne;
			}
			else
				sGrille += ligne;
			for (int i = 0; i < ligneJeton.length; i++) {
				sGrille += " " + getSymboleJetonOF(ligne-1,i);//-1 car il y a un decalage de ligne
			}
			sGrille += "\n";
			++ligne;
		}
		return sGrille;
	}
	
	/**
	 * renvoie l equivalent d un jeton ferme dans la grille pour le jeton donne
	 */
	public String toStringJetonFerme(Jeton jeton) {
		return "" + '[' + getSymboleJetonFerme(jeton) + ']' ;
	}
	
	/**
	 * renvoie l equivalent du symbole ferme pour le jeton donne
	 */
	public Character getSymboleJetonFerme(Jeton jeton) {
		Character jetonFerme ;
		if (jeton.estEgal(Jeton.JETON_X)){
			jetonFerme = 'x';
		}
		else
			jetonFerme = 'o';
		return jetonFerme ;
	}
	
	/**
	 * renvoie le symbole d un jeton ouvert ou ferme en fonction de la table grilleOuvertureJetons
	 * qui comprend tous les jetons fermes
	 * @param ligne
	 * @param colonne
	 * @return
	 */
	public Character getSymboleJetonOF(int ligne, int colonne) {
		assert (ligne < this.grilleOuvertureJetons.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grilleOuvertureJetons[0].length && colonne >= 0); //la cellule doit être dans la grille
		
		if (estOuvert(ligne, colonne)) {
			return getCellule(ligne, colonne).getSymbole();
		}
		else
			return getSymboleJetonFerme(getCellule(ligne, colonne));
		
	}
	
	//************ EN AMONT DU COUP ******************
	
	
		// ******* METHODE GRILLE *******
		// ******* METHODE GRILLE ADJACENT JETON *******
		
		/**
		 * existe il dans les cellules voisines de la cellule donnee [ligne,colonne]
		 * des jetons non vides
		 * la cellule peut etre vide mais doit etre dans la grille
		 * @param ligne
		 * @param colonne
		 * @return
		 */
		public boolean existeAdjacent(int ligne, int colonne) {
			assert (ligne < getLignes() && ligne >= 0); //la cellule doit être dans la grille
			assert (colonne < getColonnes() && colonne >= 0); //la cellule doit être dans la grille
			
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

			assert (ligne1 <  getLignes() && ligne1 >= 0); //la cellule doit être dans la grille
			assert (colonne1 <  getColonnes() && colonne1 >= 0); //la cellule doit être dans la grille
			assert (ligne2 <  getLignes() && ligne2 >= 0); //la cellule doit être dans la grille
			assert (colonne2 <  getColonnes() && colonne2 >= 0); //la cellule doit être dans la grille
			
			boolean adjacent = false ;
			
			if ( (Math.abs(ligne1-ligne2) <= 1) && (Math.abs(colonne1-colonne2) <= 1) ) {
				adjacent = true ;
			}
			
			return adjacent;
		}
		
		
		//************ EN AVAL DU COUP ******************
		
		// ******* METHODE EVALUATION *******

		/**
		 * alignement pour UNE Direction donnee ET son Inversee
		 * sachant que les jetons fermes ne sont pas comptabilises
		 * 
		 * @param ligne      de la cellule observée
		 * @param colonne    de la cellule observée
		 * @param profondeur est le nombre de cellule observées au max qui sont alignées
		 *                   dans grille 
		 *                   doit etre >=2
		 * @param direction  et direction opposée vers laquelle observer un alignement
		 * @return si un alignement a été trouvé
		 */
		public boolean isAlignement1D1DI(int ligne, int colonne, int profondeur, Direction direction) {

			assert (ligne <  getLignes() && ligne >= 0); //la cellule doit être dans la grille
			assert (colonne <  getColonnes() && colonne >= 0); //la cellule doit être dans la grille
			assert (! estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
			assert (profondeur >= 2);
			assert ( estOuvert(ligne,colonne));
			
			// jetonEvalue dont on evalue l implication dans un alignement avec d'autres
		
			/// aligneEvalue ligne de jeton que le joueur souhaiterait avoir à partir de
			/// jetonEvalue
			String aligneEvalue = "";
			for (int i = 1; i <= profondeur; ++i) {
				// aligneEvalue ligne de jeton que le joueur souhaiterait avoir à partir de
				// jetonEvalue
				aligneEvalue +=  getSymboleJetonOF(ligne, colonne);
			}

			// aligneCible ligne de jeton observé dans la direction donnée
			String aligneCible = "";
			int colonneCible = 0;
			int ligneCible = 0;

			// direction donnee
			int coeffProfondeur = 0;
			do {
				// jetonCible jeton que l on ajoute à cibleLigne
				colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
				ligneCible = coeffProfondeur * direction.getDligne() + ligne;
				if (ligneCible <  getLignes() && colonneCible <  getColonnes() 
						&& ligneCible >= 0 && colonneCible >= 0) {
					aligneCible +=  getSymboleJetonOF(ligneCible, colonneCible);
				}
				++coeffProfondeur;
			} while (coeffProfondeur < profondeur 
					&& ligneCible <  getLignes() && colonneCible <  getColonnes()
					&& ligneCible >= 0 && colonneCible >= 0);

			// direction oppposee
			direction = direction.inverser();
			coeffProfondeur = 1; // on ne souhaite pas rajouter le jeton central
			do {
				// jetonCible jeton que l on ajoute à cibleLigne
				colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
				ligneCible = coeffProfondeur * direction.getDligne() + ligne;
				if (ligneCible <  getLignes() && colonneCible <  getColonnes() 
						&& ligneCible >= 0 && colonneCible >= 0) {
					aligneCible +=  getSymboleJetonOF(ligneCible, colonneCible) ;
				}
				++coeffProfondeur;
			} while (coeffProfondeur < profondeur 
					&& ligneCible <  getLignes() && colonneCible <  getColonnes()
					&& ligneCible >= 0 && colonneCible >= 0);

			// comparaison des chaines
			return (aligneCible.contains(aligneEvalue));
		}
		
		/**
		 * ferme des jetons après ils ont ete trouves dans un alignement
		 * ferme d abord dans une direction (nord au sud sens horaire)
		 * puis si le nombre de jeton a fermer n a pas ete atteint
		 * ferme des jetons dans la direction opposée (nord au sud sens anti horaire)
		 * il faut que le jeton evalue soit ouvert
		 * @param ligne du jeton model a fermer
		 * @param colonne du jeton model a fermer
		 * @param profondeur nombre de jetons que l on souhaite fermer (qui sont impliques dans un alignement)
		 */
		public void fermeAlignementXD(int ligne, int colonne, int profondeur) {
			assert (ligne <  getLignes() && ligne >= 0); //la cellule doit être dans la grille
			assert (colonne <  getColonnes() && colonne >= 0); //la cellule doit être dans la grille
			assert (! estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
			assert (profondeur >= 2);
			assert (nbrDirectAvecAlign(ligne, colonne, profondeur) >=1); //il faut qu avant l appel de cette fonction il ai ete verifie qu il y avait bel et bien un alignement

			Jeton jetonModel =   getCellule(ligne, colonne); //dernier jeton que l on va fermer et qui nous servera de modele pour la fermeture des autres jetons
			assert( estOuvert(ligne, colonne));//il faut que le jeton evalue soit ouvert
			
			Direction direction = directionAlignementXD(ligne, colonne, profondeur); //axe dans lequel la fermeture va se realiser
			
			boolean aligne = true ;//permet de controler que les jetons fermer sont bien alignes
			int indice = 1; //permet de progresser le long de l alignements
			int resteJeton = profondeur ; //compte le nombre de jetons qu il reste a fermer
			
			while (aligne && indice < profondeur && resteJeton > 1) {
				if ( existeNextCellule(ligne, colonne, indice, direction)) {
					int[] coordCibleD =  coordNextJeton(ligne, colonne, indice, direction);
					Jeton jetonCibleD =  getCellule(coordCibleD[0], coordCibleD[1]);
					if (jetonModel.estEgal(jetonCibleD) &&  estOuvert(coordCibleD[0], coordCibleD[1])) {
						 ouvertToFermeJeton(coordCibleD[0], coordCibleD[1]);
						--resteJeton;
					}
					else {
						aligne = false;
					}
					++indice;
				}
				else {
					aligne = false ;
				}
			}
			
			// si le nombre de jeton a ferme n est toujours pas atteint
			// ils doivent etre dans l autre direction
			if (resteJeton >= 1) {
				aligne = true ;
				indice = 1;
				direction= direction.inverser();
				while (aligne && indice < profondeur && resteJeton > 1) {
					if ( existeNextCellule(ligne, colonne, indice, direction)) {
						int[] coordCibleD =  coordNextJeton(ligne, colonne, indice, direction);
						Jeton jetonCibleD =  getCellule(coordCibleD[0], coordCibleD[1]);
						if (jetonModel.estEgal(jetonCibleD) &&  estOuvert(coordCibleD[0], coordCibleD[1])) {
							 ouvertToFermeJeton(coordCibleD[0], coordCibleD[1]);
							--resteJeton;
						}
						else {
							aligne = false;
						}
						++indice;
					}
					else {
						aligne = false ;
					}
				}
			}
			//fermeture du jeton model
			assert(resteJeton == 1);
			 ouvertToFermeJeton(ligne, colonne);
		}
		
		/**
		 * fermer les jeton selon un axe 
		 * de longueur profondeur
		 * d orientation suivant oneDirection
		 * @param ligne
		 * @param colonne
		 * @param profondeur si egale a 0 la cellule fermee sera uniquement la cellule[ligne][colonne]
		 * @param oneDirection orientation de l axe de fermeture des jetons
		 */
		public void fermerAxeJetons1D(int ligne, int colonne, int profondeur, Direction oneDirection) {
			assert (ligne <  getLignes() && ligne >= 0); //la cellule doit être dans la grille
			assert (colonne <  getColonnes() && colonne >= 0); //la cellule doit être dans la grille
			
			for (int i = 0 ; i <= profondeur; ++i) {
				if ( existeNextCellule(ligne, colonne, profondeur, oneDirection)) {
					int[] coordCible =  coordNextJeton(ligne, colonne, profondeur, oneDirection);
					int ligneCible = coordCible[0];
					int colonneCible = coordCible[1];
					if (! getCellule(ligneCible, colonneCible).estVideJeton()) {
						if( estOuvert(ligneCible, colonneCible)) {
							 ouvertToFermeJeton(ligneCible, colonneCible) ;
						}
					}
				}
			}
			
		}
}