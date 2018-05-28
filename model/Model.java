package model;

import mvc.MVCBeobachter;

public class Model extends MVCBeobachter {

	private String auswahl;
	
	public Model() {}
	
	public Model(String auswahl) {
		this.auswahl = auswahl;
	}
	
	public String getAuswahl() {
		return this.auswahl;
	}
	
	@Override
	public void setzeAuswahl(String auswahl) {
		this.auswahl = auswahl;
		informiereBeobachter(auswahl);
	}
	
}
