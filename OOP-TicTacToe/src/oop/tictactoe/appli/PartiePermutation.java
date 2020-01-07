package oop.tictactoe.appli;

import oop.tictactoe.tours.In_Tour;
import oop.tictactoe.tours.TourPermutation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import oop.tictactoe.grille.Jeton;
import oop.tictactoe.jouer.*;


public class PartiePermutation extends PartieMorpion implements In_Partie{
		
	//la partie durera tant qu il y aura des jetons a ligner pour un des deux joueurs
	
	public PartiePermutation() {
		super();
		remplirAleaGrille();
		int pointMax = getNbrCellules() - getNbrCellules()%2 ;
		pointMax /= 2 ;
		pointMax /= nbrAlign;
		match = new Match(pointMax); //les joueurs joueront tant qu il restera des coups gagnants (alignement de taille nbrAlign)
	}
	
	public PartiePermutation(int grilleNrbLignes, int grilleNbrColonnes) {
		super(grilleNrbLignes, grilleNrbLignes);
		remplirAleaGrille();
		int pointMax = getNbrCellules() - getNbrCellules()%2 ;
		pointMax /= 2 ;
		pointMax /= nbrAlign;
		match = new Match(pointMax); //les joueurs joueront tant qu il restera des coups gagnants (alignement de taille nbrAlign)
	}
	
	public PartiePermutation(int grilleNrbLignes, int grilleNbrColonnes, int choixNbrAlignements) {
		super(grilleNrbLignes, grilleNrbLignes, choixNbrAlignements);
		remplirAleaGrille();
		int pointMax = getNbrCellules() - getNbrCellules()%2 ;
		pointMax /= 2 ;
		pointMax /= nbrAlign;
		match = new Match(pointMax); //les joueurs joueront tant qu il restera des coups gagnants (alignement de taille nbrAlign)
	}
	
	@Override
	public void lancerPartie() {
		afficherGrille();
		//on fait des tours
		while(!(match.estTourMax() || match.getVictoire())) {
			match.tourDebut();
			
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;
			Joueur joueurAutre = (joueurActuel.getJeton().estEgal(joueur1.getJeton())) ? joueur2 : joueur1 ;

			System.out.println(In_Interaction.afficherMessageDebutTour(joueurActuel));
			In_Tour tour = new TourPermutation( this, joueurActuel, joueurAutre, nbrAlign);

			tour.jouerCoup();
			afficherGrille();
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
		for (Jeton[] ligneJeton : this.getGrille()) {
			for (int i = 0; i < ligneJeton.length; i++) {
				ligneJeton[i] = listeJetons.getFirst();
				listeJetons.removeFirst();	
			}
		}
	}
}

