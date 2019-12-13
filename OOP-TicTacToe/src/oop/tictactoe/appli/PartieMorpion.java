package oop.tictactoe.appli;

import oop.tictactoe.grille.*;

public class PartieMorpion {
	

	
	//miroir
	/*
	 */
	public boolean existeCelluleMiroir(int ligneOrigine, int colonneOrigine, int ligneProjete, int colonneProjete ) {
		assert (ligneOrigine <= this.lignes && colonneOrigine <= this.colonnes);
		assert (ligneProjete <= this.lignes && colonneProjete <= this.colonnes);
				
		int ligneMiroir = (ligneOrigine - ligneProjete) + ligneOrigine ;
		int colonneMiroir =  (colonneOrigine - colonneProjete) + colonneOrigine;
		
		return (ligneMiroir <= this.lignes && colonneMiroir <= this.colonnes);
	}
	
	/**
	 * 
	 * @param ligneOrigine
	 * @param colonneOrigine
	 * @param ligneProjete
	 * @param colonneProjete
	 * @return
	 */
	public Jeton getCelluleMiroir(int ligneOrigine, int colonneOrigine, int ligneProjete, int colonneProjete ) {
		assert (ligneOrigine <= this.lignes && colonneOrigine <= this.colonnes);
		assert (ligneProjete <= this.lignes && colonneProjete <= this.colonnes);
				
		int ligneMiroir = (ligneOrigine - ligneProjete) + ligneOrigine ;
		int colonneMiroir =  (colonneOrigine - colonneProjete) + colonneOrigine;
		
		assert (ligneMiroir <= this.lignes && colonneMiroir <= this.colonnes); //on verifie que l on reste dans la grille
		return this.grille[ligneMiroir][colonneMiroir];
	}

}
