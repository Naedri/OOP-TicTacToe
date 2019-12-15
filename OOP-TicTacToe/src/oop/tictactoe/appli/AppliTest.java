package oop.tictactoe.appli;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AppliTest {

	@Test
	void test() {
		PartieTicTacToe appliTicTacToe = new PartieTicTacToe() ;
		appliTicTacToe.lancerPartie() ;
	}

}
