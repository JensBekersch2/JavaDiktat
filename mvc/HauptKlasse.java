/**
 * Diktat
 * Einfache Diktiergerät-Applikation
 * @author Jens Bekersch (bekersch@th-brandenburg.de)
 * @version 1.00, 04.2018
 */
package mvc;

import com.sun.javafx.application.LauncherImpl;

public class HauptKlasse {
	/**
	 * Hauptmethode
	 * @param args
	 */
	public static void main(String[] args) {
		LauncherImpl.launchApplication(HauptStage.class, AppPreloader.class, args);
	}
	
}