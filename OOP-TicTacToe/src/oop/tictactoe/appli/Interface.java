package oop.tictactoe.appli;

public class Interface {
	
	/*
	 * saisieCellule renvoie l input de l utilisateur sous format brute (table à 1 colonne 2 lignes)
	 * messageCellule message sous forme en reponse de la saisieCellule, avec la forme : L’utilisateur X (resp O) a saisie la cellule «[i],[j]»
	 * messageResultat victoire ou non (continuez la partie
	 * messageTour quel est le tour et qui doit jouer
	 */
	private String saisieCellule ;
	private String messageCellule ;
	private String messageResultat ;
	private String messageTour ;

	public String getSaisieCellule() {
		return this.messageCellule
	}
	
	/*
	 * securise le choix de la cellule par l utilisateur l entrée de l utilisateur
	 */
	public void setSaisieCellule() {
		;
	}
	
	public String getMessageCellule() {
		;
	}
	
	private void setMessageCellule() {
		//this.saisieCellule;
	}
	
	public String getMessageResultat() {
		;
	}
	
	private void setMessageResultat( ) {
		boolean termine = this.matchNul;
		boolean victoire = this.matchVictoire;
	}
	
	public String getMessageTour() {
		;
	}

	private void setMessageTour() {
		int tour = this.getTour; 
	}
	
}
