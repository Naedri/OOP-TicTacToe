package oop.tictactoe.tours;

import java.util.EnumSet;

import oop.tictactoe.grille.*;
import oop.tictactoe.jouer.In_Interaction;
import oop.tictactoe.jouer.In_MessagesPlacement;
import oop.tictactoe.jouer.Joueur;

public class TourMorpion extends TourTicTacToe {
	
	private Grille grille;
	private Joueur joueur;
	private int[] saisieCellule ; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
		
	public TourMorpion(Grille grille, Joueur joueurActuel) {
		super(grille, joueurActuel);
		this.grille = grille;
		this.joueur = joueurActuel;
		this.saisieCellule = new int[2]; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
	}
	
	/**
	 * il faut qu avant l appel de cette fonction il ai ete verifie qu il y avait bel et bien un alignement
	 * renvoie la PREMIERE direction trouvée pour laquel un alignement a ete trouve
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @return PREMIERE direction trouvée pour laquel un alignement a ete trouve
	 */
	public Direction directionAlignementXD(int ligne, int colonne, int profondeur) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);
		assert (nbrDirectAvecAlign(ligne, colonne, profondeur) >=1); //il faut qu avant l appel de cette fonction il ai ete verifie qu il y avait bel et bien un alignement
		
		Direction oneDirection = null ;
		
		for (Direction direction : EnumSet.range(Direction.NORD, Direction.SUD_EST))
			if (isAlignement1D1DI(ligne, colonne, profondeur, direction)) {
				oneDirection = direction ;
				return oneDirection ;
			}
		
		return oneDirection ;
	}
	
	/**
	 * ferme des jetons après ils ont ete trouves dans un alignement
	 * ferme d abord dans une direction (nord au sud sens horaire)
	 * puis si le nombre de jeton a fermer n a pas ete atteint
	 * ferme des jetons dans la direction opposée (nord au sud sens anti horaire)
	 * @param ligne du jeton model a fermer
	 * @param colonne du jeton model a fermer
	 * @param profondeur nombre de jetons que l on souhaite fermer (qui sont impliques dans un alignement)
	 */
	public void fermeAlignementXD(int ligne, int colonne, int profondeur) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);
		assert (nbrDirectAvecAlign(ligne, colonne, profondeur) >=1); //il faut qu avant l appel de cette fonction il ai ete verifie qu il y avait bel et bien un alignement

		Jeton jetonModel =  grille.getCellule(ligne, colonne); //dernier jeton que l on va fermer et qui nous servera de modele pour la fermeture des autres jetons
		assert(jetonModel.estOuvert());
		
		Direction direction = directionAlignementXD(ligne, colonne, profondeur); //axe dans lequel la fermeture va se realiser
		
		boolean aligne = true ;//permet de controler que les jetons fermer sont bien alignes
		int indice = 1; //permet de progresser le long de l alignements
		int resteJeton = profondeur ; //compte le nombre de jetons qu il reste a fermer (-1 car on fermera le jeton à grille[ligne][colonne] a la fin
		
		while (aligne && indice < profondeur && resteJeton >= 1) {
			if (grille.existeNextCellule(ligne, colonne, indice, direction)) {
				int[] coordCibleD = grille.coordNextJeton(ligne, colonne, indice, direction);
				Jeton jetonCibleD = grille.getCellule(coordCibleD[0], coordCibleD[1]);
				if (jetonModel.estEgal(jetonCibleD)) {
					grille.ouvertToFermeJeton(coordCibleD[0], coordCibleD[1]);
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
			while (aligne && indice < profondeur && resteJeton >= 1) {
				if (grille.existeNextCellule(ligne, colonne, indice, direction)) {
					int[] coordCibleD = grille.coordNextJeton(ligne, colonne, indice, direction);
					Jeton jetonCibleD = grille.getCellule(coordCibleD[0], coordCibleD[1]);
					if (jetonModel.estEgal(jetonCibleD)) {
						grille.ouvertToFermeJeton(coordCibleD[0], coordCibleD[1]);
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
		grille.ouvertToFermeJeton(ligne, colonne);
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
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		
		for (int i = 0 ; i <= profondeur; ++i) {
			if (grille.existeNextCellule(ligne, colonne, profondeur, oneDirection)) {
				int[] coordCible = grille.coordNextJeton(ligne, colonne, profondeur, oneDirection);
				int ligneCible = coordCible[0];
				int colonneCible = coordCible[1];
				if (!grille.getCellule(ligneCible, colonneCible).estVideJeton()) {
					if(grille.getCellule(ligneCible, colonneCible).estOuvert()) {
						grille.ouvertToFermeJeton(ligneCible, colonneCible) ;
					}
				}
			}
		}
		
	}
	
	/**
	 * permet la saisie et le placement de jeton
	 */
	public void jouerCoup() {
		boolean saisieCorrecte = false;

		while (!saisieCorrecte) {
			saisieCellule = In_Interaction.saisirCellule(grille);
			System.out.println(In_Interaction.afficherMessageCellule(joueur, saisieCellule));
			if (grille.estVideCellule(saisieCellule[0], saisieCellule[1])) {
				
				if (grille.estVideGrille()) {
					saisieCorrecte = true ;
					}
				else {
					if (grille.existeAdjacent(saisieCellule[0], saisieCellule[1])) {
						saisieCorrecte = true ;
					}
					else
						System.out.println("La case selectionnee ne comporte pas de jeton adjacent. Veuillez recommencer.\n");
				}
			}
			else
				System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
			}
		grille.placerJeton(joueur.getJeton(), saisieCellule[0], saisieCellule[1]);
		System.out.println(In_MessagesPlacement.afficherMessageCoupJoue(joueur, saisieCellule));
	}
	
	/**
	 * 
	 */
	public void evaluerCoup() {
		assert(saisieCellule != null);//on oblige le joueur a avoir jouer un coup
		if (nbrDirectAvecAlign(saisieCellule[0], saisieCellule[1], 3) >=1 ) {
			fermeAlignementXD(saisieCellule[0], saisieCellule[1], 3);
			System.out.println(In_Interaction.afficherMessageCoupMarquant(joueur));
			grille.afficherGrille();
			joueur.marquerPoint();
		}
	}
	
}
