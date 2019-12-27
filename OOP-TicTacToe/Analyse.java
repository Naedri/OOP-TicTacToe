import java.util.Scanner;

public class Analyse {
	public static void main(String[] args) {
		// format : entier[espaces]-[espaces]entier (ligne puis colonne)
		String s = "12  -   			45";
		analyse1(s);
		analyse2(s);
		analyse3(s);
		analyse4(s); // version recommandée
	}

	public static void analyse1(String s) {
		int pos = s.indexOf('-');
		if (pos == -1)
			System.out.println("erreur, il n'y a pas de tiret");
		else {
			try {
				String s1 = s.substring(0, pos).trim(); // sous-chaine entre 0 et pos - 1
				String s2 = s.substring(pos + 1, s.length()).trim(); // entre pos + 1 et s.length() - 1
				int ligne = Integer.parseInt(s1); // la conversion String en entier
				int colonne = Integer.parseInt(s2); // lÃ¨ve une exception si la chaine n'est pas correcte
				System.out.println("ligne = " + ligne + ", colonne = " + colonne);
			} catch (NumberFormatException e) {
				System.out.println("erreur, au moins une des chaines entourant le tiret n'est pas un entier");
			}
		}
	}
	
	public static void analyse2(String s) {
		int pos = s.indexOf('-');
		if (pos != -1) {
			Scanner sc = new Scanner(s.substring(0, pos));
			if (sc.hasNextInt()) {
				int ligne = sc.nextInt();
				sc.close();
				sc = new Scanner(s.substring(pos + 1, s.length()));
				if (sc.hasNextInt()) {
					int colonne = sc.nextInt();
					System.out.println("ligne = " + ligne + ", colonne = " + colonne);
				}
				else
					System.out.println("erreur, il n'y a d'entier aprÃ¨s le tiret");
			}
			else
				System.out.println("erreur, il n'y a d'entier avant le tiret");
			sc.close();
		}
		else
			System.out.println("erreur, il n'y a pas de tiret");
	}
	
	public static void analyse3(String s) {
		Scanner sc = new Scanner(s);
		sc.useDelimiter("\\s*-\\s*");
		if (sc.hasNextInt()) {
			int ligne = sc.nextInt();
			if (sc.hasNextInt()) {
				int colonne = sc.nextInt();
				System.out.println("ligne = " + ligne + ", colonne = " + colonne);
			}
			else
				System.out.println("erreur");
		}
		else
			System.out.println("erreur");				
		sc.close();
	}

	// version recommandée
	public static void analyse4(String s) {
		if (s.matches("\\d+\\s*-\\s*\\d+")) {
			Scanner sc = new Scanner(s);
			sc.useDelimiter("\\s*-\\s*");
			int ligne = sc.nextInt();
			int colonne = sc.nextInt();
			System.out.println("ligne = " + ligne + ", colonne = " + colonne);
			sc.close();
		}
		else
			System.out.println("erreur de format");				
	}
}
