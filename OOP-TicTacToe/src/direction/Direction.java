package direction;

public enum Direction {
	NORD(-1, 0), NORD_EST(-1, 1), EST(0, 1), SUD_EST(1, 1), SUD(1, 0), SUD_OUEST(1, -1), OUEST(0, -1),
	NORD_OUEST(-1, -1);

	// DEPLACEMENT(ligne,colonne)
	// enum index commence a 0

	// déplacement relatif de la direction
	private final int dligne, dcolonne;

	private Direction(int deplacementLigne, int deplacementColonne) {
		this.dcolonne = deplacementColonne;
		this.dligne = deplacementLigne;
	}

	/**
	 * Retourne le déplacement sur l'axe des x correspondant à la direction
	 * courante.
	 * 
	 * @return le déplacement en x
	 */
	public int getDcolonne() {
		return dcolonne;
	}

	/**
	 * Retourne le déplacement sur l'axe des y correspondant à la direction
	 * courante.
	 * 
	 * @return le déplacement en y
	 */
	public int getDligne() {
		return dligne;
	}

	/**
	 * Retourne une direction inverse à la direction courante.
	 * 
	 * @return la direction opposée
	 */
	public Direction inverser() {
		return Direction.values()[(this.ordinal() + 4) % 8];
	}

}
