package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import model.Model;
import view.HauptView;

public class MenuController implements EventHandler<ActionEvent> {

	Model model;
	
	public MenuController(Model model, HauptView view) {
		this.model = model;
		this.model.anmelden(view);
	}
	
	@Override
	public void handle(ActionEvent e) {
		MenuItem item = (MenuItem) e.getSource();
		String iText = (String) item.getText();
		switch(iText) {
			case "Neue Aufnahme starten": 
				model.setzeAuswahl("RecordPane");
				break;
			case "Aufnahmen Übersicht":
				model.setzeAuswahl("StartPane");
				break;				
			case "Beenden":
				Platform.exit();
				break;
		}
		
	}


}
