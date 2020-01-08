package oop.tictactoe.appli;

import Utilitaires.Utils_Grille_Evaluation_Adjacent;
import Utilitaires.Utils_Grille_Evaluation_Alignement;
import oop.tictactoe.grille.Direction;
import oop.tictactoe.grille.Jeton;
import oop.tictactoe.interaction.Messages_Saisie;
import oop.tictactoe.interaction.MessagePlacement;


public class PartieMorpion extends CA_Grille_Partie_FermetureJeton {

	private int nbrAlign ;
	private int[] saisieCellule ;
	
	public PartieMorpion(int nbrLignes, int nbrColonnes, int nbrAlign) {
		super(nbrLignes, nbrColonnes);
		this.saisieCellule = new int[2];
		this.nbrAlign = nbrAlign;
		int choixNbrAlignMax = (nbrColonnes >= nbrLignes) ? nbrLignes : nbrColonnes;
		assert (nbrAlign <= choixNbrAlignMax); //ce nombre ne doit pas être plus grand que le nombre de colonnes ou de lignes de votre grille
	}
	
	public PartieMorpion(int nbrLignes, int nbrColonnes) {
		super(nbrLignes, nbrColonnes);
		this.saisieCellule = new int[2];
		this.nbrAlign = 3;
		int choixNbrAlignMax = (nbrColonnes >= nbrLignes) ? nbrLignes : nbrColonnes;
		assert (nbrAlign <= choixNbrAlignMax); //ce nombre ne doit pas être plus grand que le nombre de colonnes ou de lignes de votre grille
	}
	
	public PartieMorpion() {
		super(5, 6);
		this.saisieCellule = new int[2];
		this.nbrAlign = 3;
	}
	
	//************ METHODE CA ******************

	
	@Override
	public void jouerCoup(Joueur joueurActuel) {
		boolean saisieCorrecte = false;
	
		while (!saisieCorrecte) {
			saisieCellule = Messages_Saisie.saisirCellule( getGrille());
			System.out.println(Messages_Saisie.afficherMessageCellule(joueurActuel, saisieCellule));
			if ( estVideCellule(saisieCellule[0], saisieCellule[1])) {
				
				if ( estVideGrille()) {
					saisieCorrecte = true ;
					}
				else {
					if (Utils_Grille_Evaluation_Adjacent.existeAdjacent(saisieCellule[0], saisieCellule[1], this)) {
						saisieCorrecte = true ;
					}
					else
						System.out.println("La case selectionnee ne comporte pas de jeton adjacent. Veuillez recommencer.\n");
				}
			}
			else
				System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
			}
		 placerJeton(joueurActuel.getJeton(), saisieCellule[0], saisieCellule[1]);
		System.out.println(MessagePlacement.afficherMessageCoupJoue(joueurActuel, saisieCellule));
	}
	
	@Override
	public void evaluerCoup(Joueur joueur1, Joueur joueur2) {
		assert(saisieCellule != null);//on oblige le joueur a avoir jouer un coup
		if (Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(saisieCellule[0], saisieCellule[1], nbrAlign, this) >=1 ) {
			
			Jeton jetonEvalue = getCellule(saisieCellule[0],  saisieCellule[1]);
			if (jetonEvalue.estEgal(joueur1.getJeton())){
				joueur1.marquerPoint();
				System.out.println(Messages_Saisie.afficherMessageCoupMarquant(joueur1));
			}
			if (jetonEvalue.estEgal(joueur2.getJeton())){
				joueur2.marquerPoint();
				System.out.println(Messages_Saisie.afficherMessageCoupMarquant(joueur1));
			}
			
			fermeAlignementXD(saisieCellule[0], saisieCellule[1], nbrAlign);
			afficherGrille();
		}
		
	}
	
	
	//************ EN AVAL DU COUP GAGNANT ******************
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
				assert (Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(ligne, colonne, profondeur, this) >=1); //il faut qu avant l appel de cette fonction il ai ete verifie qu il y avait bel et bien un alignement

				Jeton jetonModel =   getCellule(ligne, colonne); //dernier jeton que l on va fermer et qui nous servera de modele pour la fermeture des autres jetons
				assert( estOuvert(ligne, colonne));//il faut que le jeton evalue soit ouvert
				
				Direction direction = Utils_Grille_Evaluation_Alignement.directionAlignementXD(ligne, colonne, profondeur, this); //axe dans lequel la fermeture va se realiser
				
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

			@Override
			public boolean estFinie() {
				return  estPleineGrille();
			}
	
}
