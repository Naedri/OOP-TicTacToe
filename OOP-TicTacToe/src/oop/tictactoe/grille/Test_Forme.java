package oop.tictactoe.grille;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_Forme {

	@Test
	void test() {
		
		int[] distance;
		int[] orientation;
		
		Forme forme ;

		//carre
		forme = new Forme(1);		
		distance = new int[] {1,1,1,1};
		orientation = new int[] {2,4,6,0};
		assertArrayEquals(distance, forme.transForme(0).getDistance());
		assertArrayEquals(orientation, forme.transForme(0).getOrientation());

		orientation = new int[] {0,2,4,6};
		assertArrayEquals(distance, forme.transForme(1).getDistance());
		assertArrayEquals(orientation, forme.transForme(1).getOrientation());

		orientation = new int[] {0,2,4,6};
		assertArrayEquals(distance, forme.transForme(5).getDistance());
		assertArrayEquals(orientation, forme.transForme(5).getOrientation());
		
//		for (int i = 0; i < 10; i++) {
//			Forme tmp = forme.transForme(i);
//			System.out.println("\nindice "+i);
//			System.out.println("\ndistance");
//			for (int j = 0; j < tmp.getDistance().length; j++) {
//				System.out.print("{");
//				System.out.print(tmp.getDistance()[j]);
//				System.out.print("}");
//
//			}
//			System.out.println("\norientation");
//			for (int j = 0; j < tmp.getOrientation().length; j++) {
//				System.out.print("{");
//				System.out.print(tmp.getOrientation()[j]);
//				System.out.print("}");
//
//			}
//		}
//		
//		formeStr = "losange";
//		distance = new int[]{1,1,1,1};
//		orientation = new int[]{1,3,5,7};
//		
//		formeStr = "etoile";
//		distance = new int[]{1,1,1,1,1} ;
//		orientation = new int[]{1,3,5,7,6};
		
	}

}
