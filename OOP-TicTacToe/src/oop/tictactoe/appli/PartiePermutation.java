package oop.tictactoe.appli;

import oop.tictactoe.tours.TourPermutation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import oop.tictactoe.grille.Grille;
import oop.tictactoe.grille.Jeton;
import oop.tictactoe.jouer.*;


public class PartiePermutation extends PartieMorpion implements In_Partie{
		
	//la partie durera tant qu il y aura des jetons a ligner pour un des deux joueurs
	
	public PartiePermutation() {
		super();
		grille = new Grille(true);
		int pointMax = grille.getNbrCellules() - grille.getNbrCellules()%2 ;
		pointMax /= 2 ;
		pointMax /= nbrAlign;
		match = new Match(pointMax); //les joueurs joueront tant qu il restera des coups gagnants (alignement de taille nbrAlign)
	}
	
	public PartiePermutation(int grilleNrbLignes, int grilleNbrColonnes) {
		super(grilleNrbLignes, grilleNrbLignes);
		grille = new Grille(grilleNrbLignes,grilleNbrColonnes, true);
		int pointMax = grille.getNbrCellules() - grille.getNbrCellules()%2 ;
		pointMax /= 2 ;
		pointMax /= nbrAlign;
		match = new Match(pointMax); //les joueurs joueront tant qu il restera des coups gagnants (alignement de taille nbrAlign)
	}
	
	public PartiePermutation(int grilleNrbLignes, int grilleNbrColonnes, int choixNbrAlignements) {
		super(grilleNrbLignes, grilleNrbLignes, choixNbrAlignements);
		grille = new Grille(grilleNrbLignes,grilleNbrColonnes, true);
		int pointMax = grille.getNbrCellules() - grille.getNbrCellules()%2 ;
		pointMax /= 2 ;
		pointMax /= nbrAlign;
		match = new Match(pointMax); //les joueurs joueront tant qu il restera des coups gagnants (alignement de taille nbrAlign)
	}
	
	@Override
	public void lancerPartie() {
		grille.afficherGrille();
		//on fait des tours
		while(!(match.estTourMax() || match.getVictoire())) {
			match.tourDebut();
			
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;
			Joueur joueurAutre = (joueurActuel.getJeton().estEgal(joueur1.getJeton())) ? joueur2 : joueur1 ;

			System.out.println(In_Interaction.afficherMessageDebutTour(joueurActuel));
			TourPermutation tour = new TourPermutation(grille, joueurActuel, joueurAutre, nbrAlign);

			tour.jouerCoup();
			grille.afficherGrille();
			tour.evaluerCoup();
			
			match.evalVictoireParPointMax (joueurActuel);

			System.out.println(In_Interaction.afficherMessageFinTour(joueurActuel));

		}
		//on compte les points
		System.out.println(In_Interaction.afficherMessageResultat(match, joueur1, joueur2));

	}
	
	// ******* METHODE GRILLE *******
	// ******* METHODE GRILLE REMPLISSAGE ALEATOIRE *******
	/**
	 * remplissage aléatoire avec JEONT caractère ouvert,  
	 * à partir d une liste de JETON finie de taille égale à celle de la grille
	 * tirage aléatoire sans remise de la liste de JETON dans chacune des cellules
	 * si le nombre de jeton est impair le dernier jeton sera determine de maniere aleatoire
	 * Ne pourra etre appellee que si la grille est vide
	 */
	private void remplirAleaGrille() {
		assert(this.estVideGrille());
		//initialisation de la liste des jetons 
		LinkedList<Jeton> listeJetons= new LinkedList<Jeton>(); //Linked list car acces terminaux constant
//		//initialisation de la liste des jetons si le nombre de jeton est impair il y aura un jeton O de plus
//		for (int i = 0; i < getNbrCellules(); ++i) {
//			listeJetons.addLast(Jeton.values()[(i+1) %2 +1]);
//		}
		//initialisation de la liste des jetons
		if (getNbrCellules() % 2 == 0 ) {
			for (int i = 0; i < getNbrCellules(); ++i) {
				listeJetons.addLast(Jeton.values()[(i%2) +1]);
			}
		}
		else {
			Random r = new Random();
			int valeur = r.nextInt(2) + 1; //valeur entre 1 et 2
			listeJetons.addLast(Jeton.values()[valeur]);
			for (int i = 1; i < getNbrCellules(); ++i) { //on ne commence plus a 0 mais a 1
				listeJetons.addLast(Jeton.values()[(i%2) +1]);
			}
		}
		
		//brassage de la liste des jetons
		//https://www.tutorialspoint.com/java/util/collections_shuffle.htm
		Collections.shuffle(listeJetons);
		
	    //insertion de la liste de jetons dans la grille
		//boucle for each pour la Linked list qui travaille difficilement avec des indices
		for (Jeton[] ligneJeton : this.grille) {
			for (int i = 0; i < ligneJeton.length; i++) {
				ligneJeton[i] = listeJetons.getFirst();
				listeJetons.removeFirst();	
			}
		}
	}
	
	// ******* METHODE GRILLE PERMUTATION *******
		/**
		 * permute deux jetons de la grille
		 * verifie que les deux jetons electionnes sont dans la grille
		 * verifie que les deux jetons sont adjacents
		 * verifie que les les cellules sont rempli de JETON
		 * Il n est PAS verifie que les deux JETONS a permuter soient ouverts 	 
		 * @param ligneJ1
		 * @param colonneJ1
		 * @param colonneJ1
		 * @param ligneJ2
		 */
		public void permutationJeton(int ligneJ1, int colonneJ1, int ligneJ2, int colonneJ2) {
			assert(ligneJ1 != ligneJ2 || colonneJ1 != colonneJ2); //les jetons doivent etre differents

			assert (ligneJ1 < this.grille.length && ligneJ1 >= 0); //la cellule doit être dans la grille
			assert (colonneJ1 < this.grille[0].length && colonneJ1 >= 0); //la cellule doit être dans la grille
			assert (ligneJ2 < this.grille.length && ligneJ2 >= 0); //la cellule doit être dans la grille
			assert (colonneJ2 < this.grille[0].length && colonneJ2 >= 0); //la cellule doit être dans la grille
					
			assert (!estVideCellule(ligneJ1, colonneJ1)); // la cellule ne doit pas etre vide		
			assert (!estVideCellule(ligneJ2, colonneJ2)); // la cellule ne doit pas etre vide

			assert(sontAdjacents(ligneJ1, colonneJ1, ligneJ2, colonneJ2)); //les jetons doivent etre adjacents

			//si les jetons sont differents
			if (! getCellule(ligneJ1,colonneJ1).estEgal(getCellule(ligneJ2,colonneJ2)) ) {
				Jeton jtemp = getCellule(ligneJ1,colonneJ1);
				grille[ligneJ1][colonneJ1] = getCellule(ligneJ2,colonneJ2);
				grille[ligneJ2][colonneJ2] = jtemp ;
			}
		}
}


//	@Override
//	public void lancerPartie() {
//		grille.afficherGrille();
//		//on fait des tours
//		int resteJeton = grille.getNbrJeton(joueur1.getJeton());
//		while(resteJeton >= nbrAlign) {
//			match.tourDebut();
//			
//			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;
//			//Joueur joueurAutre = ( match.getTour()%2 == 0 ) ? joueur1 : joueur2 ;
//			Joueur joueurAutre = (joueurActuel.getJeton().estEgal(joueur1.getJeton())) ? joueur2 : joueur1 ;
//
//			System.out.println(In_Interaction.afficherMessageDebutTour(joueurActuel));
//			TourPermutation tour = new TourPermutation(grille, joueurActuel, joueurAutre, nbrAlign);
//
//			tour.jouerCoup();
//			grille.afficherGrille();
//			tour.evaluerCoup();
//			
//			match.evalVictoireParPointMax (joueurActuel);
//
//			System.out.println(In_Interaction.afficherMessageFinTour(joueurActuel));
//			
//			//reste t il des jetons a aligner pour les joueurs ?
//			if (grille.getNbrJeton(joueur1.getJeton()) < grille.getNbrJeton(joueur2.getJeton())) {
//				resteJeton =  grille.getNbrJeton(joueur1.getJeton()) ;
//			}
//			else
//				resteJeton =  grille.getNbrJeton(joueur2.getJeton()) ;
//		}
//		//on compte les points
//		System.out.println(In_Interaction.afficherMessageResultat(match, joueur1, joueur2));
//
//	}
//}


