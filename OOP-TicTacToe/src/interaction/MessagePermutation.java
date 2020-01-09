package interaction;

import appli.Joueur;

public class MessagePermutation {

	/**
	 * Message indiquant que "le joueur X/O a choisi de permute son jeton dans la
	 * cellule ligne ..
	 * 
	 * @param j             joueur ayant realiser la permutation
	 * @param celluleCoord1 premier cellule
	 * @param celluleCoord2 seconde cellule
	 * @return string a afficher
	 */
	public static String afficherMessageCoupJoue(Joueur j, int[] celluleCoord1, int[] celluleCoord2) {
		assert (j != null);
		assert (celluleCoord1 != null && celluleCoord2 != null); // on s assure que setSaisieCellule a ete appelee
		int saisieLigne1 = celluleCoord1[0] + 1;
		int saisieColonne1 = celluleCoord1[1] + 1;
		int saisieLigne2 = celluleCoord2[0] + 1;
		int saisieColonne2 = celluleCoord2[1] + 1;
		return "Le joueur " + j.getJeton().getSymbole() + " a choisi de permuter la cellule ligne [" + saisieLigne1
				+ "] colonne [" + saisieColonne1 + "], avec la cellule ligne [" + saisieLigne2 + "] colonne ["
				+ saisieColonne2 + "].\n";
	}

}
