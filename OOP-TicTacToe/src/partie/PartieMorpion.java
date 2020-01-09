package partie;

import java.util.EnumSet;

import direction.Direction;
import interaction.MessagePlacement;
import interaction.Messages_Saisie;
import jeton.*;
import utilitaires.Utils_Grille_Evaluation_Adjacent;
import utilitaires.Utils_Grille_Evaluation_Alignement;

public class PartieMorpion extends CA_Grille_Partie_FermetureJeton {

	private int nbrAlign;
	private int[] saisieCellule;

	public PartieMorpion(int nbrLignes, int nbrColonnes, int nbrAlign) {
		super(nbrLignes, nbrColonnes);
		this.saisieCellule = new int[2];
		this.nbrAlign = nbrAlign;
		int choixNbrAlignMax = (nbrColonnes >= nbrLignes) ? nbrLignes : nbrColonnes;
		assert (nbrAlign <= choixNbrAlignMax); // ce nombre ne doit pas être plus grand que le nombre de colonnes ou de
												// lignes de votre grille
	}

	public PartieMorpion(int nbrLignes, int nbrColonnes) {
		super(nbrLignes, nbrColonnes);
		this.saisieCellule = new int[2];
		this.nbrAlign = 3;
		int choixNbrAlignMax = (nbrColonnes >= nbrLignes) ? nbrLignes : nbrColonnes;
		assert (nbrAlign <= choixNbrAlignMax); // ce nombre ne doit pas être plus grand que le nombre de colonnes ou de
												// lignes de votre grille
	}

	public PartieMorpion() {
		super(5, 6);
		this.saisieCellule = new int[2];
		this.nbrAlign = 3;
	}

	// ************ METHODE CA ******************

	@Override
	public void jouerCoup(Joueur joueurActuel) {
		boolean saisieCorrecte = false;

		while (!saisieCorrecte) {
			saisieCellule = Messages_Saisie.saisirCellule(getGrille());
			System.out.println(Messages_Saisie.afficherMessageCellule(joueurActuel, saisieCellule));
			if (estVideCellule(saisieCellule[0], saisieCellule[1])) {

				if (estVideGrille()) {
					saisieCorrecte = true;
				} else {
					if (Utils_Grille_Evaluation_Adjacent.existeAdjacent(saisieCellule[0], saisieCellule[1], this)) {
						saisieCorrecte = true;
					} else
						System.out.println(
								"La case selectionnee ne comporte pas de jeton adjacent. Veuillez recommencer.\n");
				}
			} else
				System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
		}
		placerJeton(joueurActuel.getJeton(), saisieCellule[0], saisieCellule[1]);
		System.out.println(MessagePlacement.afficherMessageCoupJoue(joueurActuel, saisieCellule));
	}

	@Override
	public void evaluerCoup(Joueur joueur1, Joueur joueur2) {
		evaluerCoupAlignOuvert(joueur1, joueur2, this.saisieCellule);
	}

	@Override
	public boolean estFinie() {
		return estPleineGrille();
	}

	// ************ EN AVAL DU COUP GAGNANT ******************

	/**
	 * fermer les jeton selon un axe (continue) de longueur profondeur d orientation
	 * suivant oneDirection mais ne ferme pas le jeton de depart (coord
	 * ligne,colonne) Ne continue de fermer que si les jetons evalue ne sont pas
	 * vide ne sont pas deja ferme sont les memes (axe continue) et renvoie le
	 * nombre de fermeture de jetons realisees
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur si egale a 0 la cellule fermee sera uniquement la
	 *                   cellule[ligne][colonne]
	 * @param direction  orientation de l axe de fermeture des jetons
	 * @return renvoie le nombre de jetons ferme
	 */
	public int fermerAxeJetons1D(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (profondeur >= 1);

		int nbrJetonFermes = 0;
		int coeffProfondeur = 1;
		boolean valide = true;
		while (coeffProfondeur <= profondeur && this.existeNextCellule(ligne, colonne, coeffProfondeur, direction)
				&& valide) {
			int[] coordCible = coordNextJeton(ligne, colonne, coeffProfondeur, direction);
			int ligneCible = coordCible[0];
			int colonneCible = coordCible[1];
			if (getSymboleJetonOouF(ligneCible, colonneCible) == getSymboleJetonOouF(ligne, colonne)) {
				ouvertToFermeJeton(ligneCible, colonneCible);
				++nbrJetonFermes;
			} else {
				valide = false;
			}
			++coeffProfondeur;
		}
		return nbrJetonFermes;
	}

	/**
	 * ferme des jetons après ils ont ete trouves dans un alignement, ferme d abord
	 * dans une direction (nord au sud sens horaire) puis si le nombre de jeton a
	 * fermer n a pas ete atteint ferme des jetons dans la direction opposée (nord
	 * au sud sens anti horaire) il faut que le jeton evalue soit ouvert
	 * 
	 * @param ligne      du jeton model a fermer
	 * @param colonne    du jeton model a fermer
	 * @param profondeur nombre de jetons que l on souhaite fermer (qui sont
	 *                   impliques dans un alignement)
	 */
	public void fermeAlignementXD(int ligne, int colonne, int profondeur) {
		assert (ligne < getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (!estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);

		assert (isDirectAvecAlignOouF(ligne, colonne, profondeur));// on s assure qu il y ait deja un alignement
		assert (estOuvert(ligne, colonne));// il faut que le jeton evalue soit ouvert sinon on va tenter de fermer des
											// jetons qui le sont deja

		Direction direction = getAllDirectAlignOouF(ligne, colonne, profondeur)[0]; // axe dans lequel la fermeture va
																					// se realiser
		assert (direction != null);

		int resteJeton = profondeur - 1; // compte le nombre de jetons qu il reste a fermer il faut que le dernier jeton

		// fermeture d un premier sens de la direction
		resteJeton -= fermerAxeJetons1D(ligne, colonne, profondeur, direction);
		// fermeture du sens oppose de la direction
		if (resteJeton >= 1) {
			resteJeton -= fermerAxeJetons1D(ligne, colonne, resteJeton, direction.inverser());
		}
		assert (resteJeton == 0);

		// fermeture du premier jeton en dernier car il sert de modele a
		// fermerAxeJetons1D
		ouvertToFermeJeton(ligne, colonne);
	}

	// ************ EVALUATION ALIGNEMENT OUVERT OU FERME ******************

	/**
	 * renvoie une chaine de symbole de jetons OUVERT OU FERME obtenus dans une
	 * direction donnee de taille inferieure ou egale a la profondeur (tant que la
	 * projection est dans la grille) a partir d une case de la grille (ligne,
	 * colonne) Attention la case de depart n est pas comprise dans la chaine
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @param grille
	 * @return
	 */
	public String getLigneJetonOouF(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < this.getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < this.getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (profondeur > 0);

		String aligneCible = "";
		int coeffProfondeur = 1;

		while (coeffProfondeur <= profondeur && this.existeNextCellule(ligne, colonne, coeffProfondeur, direction)) {
			int colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
			int ligneCible = coeffProfondeur * direction.getDligne() + ligne;
			aligneCible += getSymboleJetonOouF(ligneCible, colonneCible);
			++coeffProfondeur;
		}
		return aligneCible;
	}

	/**
	 * alignement OUVERT OU FERME pour UNE Direction donnee ET son Inversee
	 * 
	 * @param ligne      de la cellule observée
	 * @param colonne    de la cellule observée
	 * @param profondeur est le nombre de cellule observées au max qui sont alignées
	 *                   dans grille doit etre >=2
	 * @param direction  et direction opposée vers laquelle observer un alignement
	 * @return si un alignement a été trouvé
	 */
	public boolean appartientAlignOouF(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (!estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);

		String aligneEvalue = "";
		for (int i = 1; i <= profondeur; ++i) {
			aligneEvalue += getSymboleJetonOouF(ligne, colonne);
		}

		String aligneCible = "";
		String inverse = getLigneJetonOouF(ligne, colonne, profondeur, direction.inverser());
		inverse = new StringBuilder(inverse).reverse().toString();
		aligneCible += inverse;
		aligneCible += getSymboleJetonOouF(ligne, colonne);
		aligneCible += getLigneJetonOouF(ligne, colonne, profondeur, direction);

		return aligneCible.contains(aligneEvalue);
	}

	/**
	 * alignement OUVERT OU FERME pour TOUTES les Directions disponibles le nombre
	 * de direction pour laquelle un alignement a ete trouvé
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @return le nombre de direction/orientation qui ont été trouvés avec
	 *         alignementCellule dans toutes les directions
	 */
	public int nbrDirectAvecAlignOouF(int ligne, int colonne, int profondeur) {
		assert (ligne < getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (!estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);

		int alignement = 0;

		for (Direction oneDirection : EnumSet.range(Direction.NORD, Direction.SUD_EST)) {
			if (appartientAlignOouF(ligne, colonne, profondeur, oneDirection)) {
				++alignement;
			}
		}
		return alignement;
	}

	/**
	 * existe t il une direction pour laquelle un alignement OUVERT OU FERME de
	 * taille profondeur a ete trouve ?
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param grille
	 * @return
	 */
	public boolean isDirectAvecAlignOouF(int ligne, int colonne, int profondeur) {
		assert (ligne < getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (!estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);

		for (Direction oneDirection : EnumSet.range(Direction.NORD, Direction.SUD_EST)) {
			if (appartientAlignOouF(ligne, colonne, profondeur, oneDirection)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * AVANT appel de cette fonction il devra avoir ete verifie qu il avait des
	 * alignements renvoie les directions (droites et inverses) pour lesquelles un
	 * alignement OUVERT a ete trouve
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @return table des directions pour lesquelles un alignement a ete trouve
	 */
	public Direction[] getAllDirectAlignOouF(int ligne, int colonne, int profondeur) {
		assert (ligne < getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (!estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);
		assert (isDirectAvecAlignOouF(ligne, colonne, profondeur));

		Direction[] tableDirect = new Direction[nbrDirectAvecAlignOouF(ligne, colonne, profondeur)];
		int indice = 0;
		for (Direction direction : EnumSet.range(Direction.NORD, Direction.SUD_EST))
			if (appartientAlignOouF(ligne, colonne, profondeur, direction)) {
				tableDirect[indice] = direction;
				++indice;
			}

		return tableDirect;
	}

	// *************** COORDONNEES A FERMEES ******************

	/**
	 * donne la longueur de l axe (continue) de longueur <= profondeur d orientation
	 * suivant oneDirection mais NE FERME AUCUN JETON Ne continue d evaluer que si
	 * les jetons ne sont pas vide ne sont pas deja ferme sont les memes (axe
	 * continue) sans prendre en compte le jeton de depart(ligne,colonne)
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur si egale a 0 la cellule fermee sera uniquement la
	 *                   cellule[ligne][colonne]
	 * @param direction  orientation de l axe de fermeture des jetons
	 * @return renvoie le nombre de jetons appartenant a un axe pour une direcion
	 *         donne sans prendre en compte le jeton de depart
	 */
	public int getLongueurAxeJetons1D(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (profondeur >= 1);

		int nbrJetonFermes = 0;
		int coeffProfondeur = 1;
		boolean valide = true;
		while (coeffProfondeur <= profondeur && this.existeNextCellule(ligne, colonne, coeffProfondeur, direction)
				&& valide) {
			int[] coordCible = coordNextJeton(ligne, colonne, coeffProfondeur, direction);
			int ligneCible = coordCible[0];
			int colonneCible = coordCible[1];
			if (getSymboleJetonOouF(ligneCible, colonneCible) == getSymboleJetonOouF(ligne, colonne)) {
				++nbrJetonFermes;
			} else {
				valide = false;
			}
			++coeffProfondeur;
		}
		return nbrJetonFermes;
	}

	/**
	 * getLongueurAxeJetons1D donne les coord des jeton ouvert a ferme pour
	 * direction donner selon un axe (continue) de longueur profondeur d orientation
	 * suivant oneDirection mais ne prend pas en compte le jeton de depart (coord
	 * ligne,colonne) Ne continue de fermer que si les jetons evalue ne sont pas
	 * vide ne sont pas deja ferme sont les memes (axe continue)
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction  orientation de l axe de fermeture des jetons
	 * @return renvoie les coordonnees
	 */
	public int[][] getCoordAlignJetons1DOouF(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (profondeur >= 2);
		assert (isDirectAvecAlignOouF(ligne, colonne, profondeur));

		int[][] coordJetonContinu = new int[getLongueurAxeJetons1D(ligne, colonne, profondeur, direction)][2];

		// coord des jetons dans une direction
		int nbrJetonFermes = 0;
		int coeffProfondeur = 1;
		boolean valide = true;
		while (coeffProfondeur <= profondeur && this.existeNextCellule(ligne, colonne, coeffProfondeur, direction)
				&& valide) {
			int[] coordCible = coordNextJeton(ligne, colonne, coeffProfondeur, direction);
			int ligneCible = coordCible[0];
			int colonneCible = coordCible[1];
			if (getSymboleJetonOouF(ligneCible, colonneCible) == getSymboleJetonOouF(ligne, colonne)) {
				coordJetonContinu[nbrJetonFermes][0] = ligneCible;
				coordJetonContinu[nbrJetonFermes][1] = colonneCible;
				++nbrJetonFermes;
			} else {
				valide = false;
			}
			++coeffProfondeur;
		}

		return coordJetonContinu;
	}

	/**
	 * donne les coord des jeton ouvert a fermer pour la premiere direction ou un
	 * alignemet a ete trouver selon un axe (continue) de longueur profondeur d
	 * orientation suivant oneDirection mais ne prend pas en compte le jeton de
	 * depart (coord ligne,colonne) Ne continue de fermer que si les jetons evalue
	 * ne sont pas vide ne sont pas deja ferme sont les memes (axe continue)
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction  orientation de l axe d observation des jetons
	 * @return renvoie les coordonnees
	 */
	public int[][] getCoordAlignJetonsXDOouF(int ligne, int colonne, int profondeur) {
		assert (ligne < getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (profondeur >= 2);
		assert (isDirectAvecAlignOouF(ligne, colonne, profondeur));

		Direction direction = getAllDirectAlignOouF(ligne, colonne, profondeur)[0];

		int[][] coordJetonContinu = new int[profondeur][2];

		// coord des jetons dans une direction
		int[][] coordJetonAferme1D = getCoordAlignJetons1DOouF(ligne, colonne, profondeur, direction);

		for (int i = 0; i < coordJetonAferme1D.length; i++) {
			coordJetonContinu[i][0] = coordJetonAferme1D[i][0];
			coordJetonContinu[i][1] = coordJetonAferme1D[i][1];
		}
		// coord du jeton central
		coordJetonContinu[coordJetonAferme1D.length][0] = ligne;
		coordJetonContinu[coordJetonAferme1D.length][1] = colonne;

		// coord des jetons dans une direction inverse si besoin
		if (coordJetonAferme1D.length < profondeur - 1) {
			int[][] coordJetonAferme1DI = getCoordAlignJetons1DOouF(ligne, colonne, profondeur, direction.inverser());
			for (int i = coordJetonAferme1D.length + 1; i < coordJetonContinu.length; i++) {
				coordJetonContinu[i][0] = coordJetonAferme1DI[i - coordJetonAferme1D.length - 1][0];
				coordJetonContinu[i][1] = coordJetonAferme1DI[i - coordJetonAferme1D.length - 1][1];
			}
		}

		return coordJetonContinu;
	}

	// ************ EVALUATION ALIGNEMENT OUVERT ******************

	public void evaluerCoupAlignOuvert(Joueur joueur1, Joueur joueur2, int[] saisieCellule) {
		assert (joueur1 != null && joueur2 != null && saisieCellule != null && joueur1 != joueur2);
		if (Utils_Grille_Evaluation_Alignement.isAlign(saisieCellule[0], saisieCellule[1], nbrAlign, this)) {
			if (isDirectAvecAlignOouF(saisieCellule[0], saisieCellule[1], nbrAlign)) {

				Jeton jetonEvalue = getCellule(saisieCellule[0], saisieCellule[1]);
				if (jetonEvalue.estEgal(joueur1.getJeton())) {
					joueur1.marquerPoint();
					System.out.println(Messages_Saisie.afficherMessageCoupMarquant(joueur1));
				}
				if (jetonEvalue.estEgal(joueur2.getJeton())) {
					joueur2.marquerPoint();
					System.out.println(Messages_Saisie.afficherMessageCoupMarquant(joueur1));
				}
				fermeAlignementXD(saisieCellule[0], saisieCellule[1], nbrAlign);
				afficherGrille();
			}
		}
	}

}
