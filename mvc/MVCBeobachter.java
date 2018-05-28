/**
 * Beobachter
 * Dieser Beobachter ermöglicht die Steuerung der View
 * durch den Controller und das Datenmodell
 * @author Jens Bekersch (bekersch@th-brandenburg.de)
 * @version 1.00, 04.2018
 */
package mvc;

import java.util.ArrayList;
import java.util.List;

public class MVCBeobachter {
	/* Liste, in der alle angemeldeten Beobachter gespeichert sind */
	private List<Beobachter> alleBeobachter = new ArrayList<>();
	/**
	 * <h1>Beobachter anmelden</h1><br>
	 * Durch diese Methode können sich Beobachter anmelden<br>
	 * Syntax: anmelden(Beobachter beobachter)
	 * @param beobachter
	 */
	public void anmelden(Beobachter beobachter) {
		alleBeobachter.add(beobachter);
	}
	
	public void abmelden(Beobachter beobachter) {
		alleBeobachter.remove(beobachter);
	}
	
	public void setzeAuswahl(String auswahl) {
		informiereBeobachter(auswahl);
	}

	public void informiereBeobachter(String auswahl) {
		for(Beobachter beobachter : alleBeobachter) {
			beobachter.geaenderteAuswahl(auswahl);
		}
	}

}
