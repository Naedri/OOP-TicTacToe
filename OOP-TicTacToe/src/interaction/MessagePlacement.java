package interaction;

import jeton.*;
import partie.Joueur;

public class MessagePlacement {

	/**
	 * Message indiquant que "le joueur X/O a choisi de placer son jeton dans la
	 * cellule ligne ..
	 * 
	 * @param j             joueur dont le symbole sera utilise
	 * @param celluleCoord1 tab de coordonnees de la cellule = * @return string a
	 *                      afficher
	 */
	public static String afficherMessageCoupJoue(Joueur j, int[] celluleCoord1) {
		assert (j != null);
		assert (celluleCoord1 != null); // on s assure que setSaisieCellule a ete appelee
		int saisieLigne1 = celluleCoord1[0] + 1;
		int saisieColonne1 = celluleCoord1[1] + 1;
		return "Le joueur " + j.getJeton().getSymbole() + " a choisi de placer son jeton dans la cellule ligne ["
				+ saisieLigne1 + "] colonne [" + saisieColonne1 + "].\n";
	}

}
