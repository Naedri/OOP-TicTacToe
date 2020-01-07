package oop.tictactoe.tours;

import java.util.EnumSet;

import oop.tictactoe.appli.PartieMorpion;
import oop.tictactoe.grille.*;
import oop.tictactoe.jouer.In_Interaction;
import oop.tictactoe.jouer.In_MessagesPlacement;
import oop.tictactoe.jouer.Joueur;

public class TourMorpion extends TourTicTacToe implements In_Tour, In_MessagesPlacement {

	public TourMorpion(PartieMorpion partie, Joueur joueurActuel) {
		super(partie, joueurActuel);
	}
	
	public TourMorpion(PartieMorpion partie, Joueur joueurActuel, int nbrAlign) {
		super(partie, joueurActuel, nbrAlign);
	}

	//************ EN AMONT DU COUP ******************
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
	@Override
	public boolean isAlignement1D1DI(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < partie.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < partie.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (!partie.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);
		assert (partie.estOuvert(ligne,colonne));
		
		// jetonEvalue dont on evalue l implication dans un alignement avec d'autres
		Jeton jetonEvalue = partie.getCellule(ligne, colonne);
		
		/// aligneEvalue ligne de jeton que le joueur souhaiterait avoir à partir de
		/// jetonEvalue
		String aligneEvalue = "";
		for (int i = 1; i <= profondeur; ++i) {
			// aligneEvalue ligne de jeton que le joueur souhaiterait avoir à partir de
			// jetonEvalue
			aligneEvalue += partie.getSymboleJetonOF(ligne, colonne);
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
			if (ligneCible < partie.getLignes() && colonneCible < partie.getColonnes() 
					&& ligneCible >= 0 && colonneCible >= 0) {
				Jeton jetonCible = partie.getCellule(ligneCible, colonneCible);
				aligneCible += partie.getSymboleJetonOF(ligneCible, colonneCible);
			}
			++coeffProfondeur;
		} while (coeffProfondeur < profondeur 
				&& ligneCible < partie.getLignes() && colonneCible < partie.getColonnes()
				&& ligneCible >= 0 && colonneCible >= 0);

		// direction oppposee
		direction = direction.inverser();
		coeffProfondeur = 1; // on ne souhaite pas rajouter le jeton central
		do {
			// jetonCible jeton que l on ajoute à cibleLigne
			colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
			ligneCible = coeffProfondeur * direction.getDligne() + ligne;
			if (ligneCible < partie.getLignes() && colonneCible < partie.getColonnes() 
					&& ligneCible >= 0 && colonneCible >= 0) {
				Jeton jetonCible = partie.getCellule(ligneCible, colonneCible);
				aligneCible = partie.getSymboleJetonOF(ligneCible, colonneCible) + aligneCible;
			}
			++coeffProfondeur;
		} while (coeffProfondeur < profondeur 
				&& ligneCible < partie.getLignes() && colonneCible < partie.getColonnes()
				&& ligneCible >= 0 && colonneCible >= 0);

		// comparaison des chaines
		if (aligneCible.contains(aligneEvalue))
			return true;
		else
			return false;
	}
	
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
		assert (ligne < partie.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < partie.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		
		for (Direction oneDirection : Direction.values())
			if (partie.existeNextCellule(ligne, colonne, 1, oneDirection)) {
				if (! partie.getNextJeton(ligne, colonne, 1, oneDirection).estVideJeton()) {
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
		assert(partie.sontDifferentes(ligne1, colonne1, ligne2, colonne2)); //les jetons doivent etre differents

		assert (ligne1 < partie.getLignes() && ligne1 >= 0); //la cellule doit être dans la grille
		assert (colonne1 < partie.getColonnes() && colonne1 >= 0); //la cellule doit être dans la grille
		assert (ligne2 < partie.getLignes() && ligne2 >= 0); //la cellule doit être dans la grille
		assert (colonne2 < partie.getColonnes() && colonne2 >= 0); //la cellule doit être dans la grille
		
		boolean adjacent = false ;
		
		if ( (Math.abs(ligne1-ligne2) <= 1) && (Math.abs(colonne1-colonne2) <= 1) ) {
			adjacent = true ;
		}
		
		return adjacent;
	}
	
	
	//************ EN AVAL DU COUP ******************
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
		assert (ligne < partie.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < partie.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (!partie.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);
		assert (nbrDirectAvecAlign(ligne, colonne, profondeur) >=1); //il faut qu avant l appel de cette fonction il ai ete verifie qu il y avait bel et bien un alignement

		Jeton jetonModel =  partie.getCellule(ligne, colonne); //dernier jeton que l on va fermer et qui nous servera de modele pour la fermeture des autres jetons
		assert(partie.estOuvert(ligne, colonne));//il faut que le jeton evalue soit ouvert
		
		Direction direction = directionAlignementXD(ligne, colonne, profondeur); //axe dans lequel la fermeture va se realiser
		
		boolean aligne = true ;//permet de controler que les jetons fermer sont bien alignes
		int indice = 1; //permet de progresser le long de l alignements
		int resteJeton = profondeur ; //compte le nombre de jetons qu il reste a fermer
		
		while (aligne && indice < profondeur && resteJeton > 1) {
			if (partie.existeNextCellule(ligne, colonne, indice, direction)) {
				int[] coordCibleD = partie.coordNextJeton(ligne, colonne, indice, direction);
				Jeton jetonCibleD = partie.getCellule(coordCibleD[0], coordCibleD[1]);
				if (jetonModel.estEgal(jetonCibleD) && partie.estOuvert(coordCibleD[0], coordCibleD[1])) {
					partie.ouvertToFermeJeton(coordCibleD[0], coordCibleD[1]);
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
				if (partie.existeNextCellule(ligne, colonne, indice, direction)) {
					int[] coordCibleD = partie.coordNextJeton(ligne, colonne, indice, direction);
					Jeton jetonCibleD = partie.getCellule(coordCibleD[0], coordCibleD[1]);
					if (jetonModel.estEgal(jetonCibleD) && partie.estOuvert(coordCibleD[0], coordCibleD[1])) {
						partie.ouvertToFermeJeton(coordCibleD[0], coordCibleD[1]);
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
		partie.ouvertToFermeJeton(ligne, colonne);
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
		assert (ligne < partie.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < partie.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		
		for (int i = 0 ; i <= profondeur; ++i) {
			if (partie.existeNextCellule(ligne, colonne, profondeur, oneDirection)) {
				int[] coordCible = partie.coordNextJeton(ligne, colonne, profondeur, oneDirection);
				int ligneCible = coordCible[0];
				int colonneCible = coordCible[1];
				if (!partie.getCellule(ligneCible, colonneCible).estVideJeton()) {
					if(partie.estOuvert(ligneCible, colonneCible)) {
						partie.ouvertToFermeJeton(ligneCible, colonneCible) ;
					}
				}
			}
		}
		
	}
	
	@Override
	public void jouerCoup() {
		boolean saisieCorrecte = false;

		while (!saisieCorrecte) {
			saisieCellule = In_Interaction.saisirCellule(partie.getGrille());
			System.out.println(In_Interaction.afficherMessageCellule(joueur, saisieCellule));
			if (partie.estVideCellule(saisieCellule[0], saisieCellule[1])) {
				
				if (partie.estVideGrille()) {
					saisieCorrecte = true ;
					}
				else {
					if (existeAdjacent(saisieCellule[0], saisieCellule[1])) {
						saisieCorrecte = true ;
					}
					else
						System.out.println("La case selectionnee ne comporte pas de jeton adjacent. Veuillez recommencer.\n");
				}
			}
			else
				System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
			}
		partie.placerJeton(joueur.getJeton(), saisieCellule[0], saisieCellule[1]);
		System.out.println(In_MessagesPlacement.afficherMessageCoupJoue(joueur, saisieCellule));
	}
	
	@Override
	public void evaluerCoup() {
		assert(saisieCellule != null);//on oblige le joueur a avoir jouer un coup
		if (nbrDirectAvecAlign(saisieCellule[0], saisieCellule[1], nbrAlign) >=1 ) {
			fermeAlignementXD(saisieCellule[0], saisieCellule[1], nbrAlign);
			System.out.println(In_Interaction.afficherMessageCoupMarquant(joueur));
			partie.afficherGrille();
			joueur.marquerPoint();
		}
	}
	
}
