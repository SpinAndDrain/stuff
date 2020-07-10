package de.spinanddrain.narzisszahlen;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by SpinAndDrain on 10th July 2020
 * (c) SpinAndDrain 2020
 */
public class NarzissFinder {
	
	public static void main(String[] args) {
		
		// Einleitung, Initialisierung des Scanners für User Input
		System.out.println("NARZISSFINDER \u00A9 2020 SPINANDDRAIN");
		Scanner reader = new Scanner(System.in);
		
		// Programmschleife
		while(true) {
			
			// Abfragen des Grenzwertes
			System.out.print("Grenzwert: ");
			String input = reader.nextLine();
			System.out.println();
			
			/* Überprüfen, ob die Eingabe als Grenzwert verwendet werden kann.
			 * Falls nicht, erscheint eine Fehlermeldung und man muss den Wert neu eintragen.
			 */
			long g;
			try {
				g = Long.parseLong(input); // Wertzuweisung
			} catch(Exception e) {
				System.err.println("Der Grenzwert '" + input + "' wird nicht unterstützt.");
				continue;
			}
			
			// Initialisierung des Set's für die gefundenen Narzisszahlen
			TreeSet<Long> found = new TreeSet<>();
			System.out.print("Suchen... 0,00%\r");
			long time = System.currentTimeMillis();
			
			/* Schleife, die alle natürlichen Zahlen im Wertebereich {0 <= i <= g}
			 * auf die Narzisseigenschaft überprüft.
			 */
			for(long i = 0; i <= g; i++) {
				int[] p = parse(String.valueOf(i).toCharArray()); 		// Zerlegung der Zahl in die
				long result = 0;						// einzelnen Stellen
				for(int s : p)							// Addieren aller potenzierten Werte
					result += Math.pow(s, p.length);
				if(result == i)							// Überprüfung der Narzisseig.
					found.add(result);					// und ggf. zur Sammlung hinzufügen
				System.out.print("Suchen... " + new DecimalFormat("0.00")
						.format(((double) (i * 100) / g)) + "%\r");
			}
			
			// Auswertung
			System.out.println("Suchen... 100,00%");
			System.out.println("Berechnungsdauer: " + convert(System.currentTimeMillis() - time));
			System.out.println("Gefundene Narzisszahlen im Wertebereich {xeN | 0 <= x <= " + g + "}: "
					+ found);
			
			/* Abfragung eines Neudurchlaufes, falls nicht, wird die Programmschleife
			 * verlassen und somit das Programm beendet.
			 */
			System.out.println("SUCHE BEENDET. NEUE SUCHE STARTEN? [Y|n] ");
			if((input = reader.nextLine()) == null || !input.toLowerCase().equals("y"))
				break;
		}
		
		reader.close();
	}
	
	/**
	 * 
	 * @param chars  Der Character-Wert der einzelnen Zahlen als array
	 * @return  Der Int-Wert der einzelnen Zahlen als array
	 */
	static int[] parse(char[] chars) {
		int[] n = new int[chars.length];
		for(int i = 0; i < chars.length; i++)
			n[i] = Integer.parseInt(String.valueOf(chars[i]));
		return n;
	}
	
	/**
	 * Konvertiert eine Zeit (in MS; millis) in ein lesbar verständliches Format.
	 * 
	 * @param millis
	 * @return der konvertierte String
	 */
	static String convert(long millis) {
		if(millis < 1000) {
			return millis + " ms";
		}
		long sec = millis / 1000;
		if(sec < 60) {
			return sec + " s";
		}
		long min = sec / 60, restSecs = sec % 60;
		if(min < 60) {
			return (min + 1) + " m" + (restSecs == 0 ? "" : " " + restSecs + " s");
		}
		long h = min / 60, restMin = min % 60;
		return h + " h" + (restMin == 0 ? "" : " " + restMin + " m") +
				(restSecs == 0 ? "" : " " + restSecs + " s");
	}
	
}
