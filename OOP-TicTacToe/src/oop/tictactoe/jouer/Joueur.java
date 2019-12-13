package oop.tictactoe.jouer;

import java.util.EnumSet;
import oop.tictactoe.grille.Jeton;

public class Joueur {

	private Jeton jeton;
	private int point;
	private static int compteur = 0;
	
	public Joueur(char symbole) {
		this.jeton = Jeton.values()[compteur %2 +1]; //x doit commencer et JETON_X est le [1]
		this.point = 0;
		++compteur;
	}

	public int getPoint() {
		return point;
	}

	private void marquerPoint() {
		++this.point;
	}
	
}
