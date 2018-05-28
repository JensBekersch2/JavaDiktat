package view;

import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import model.AufnahmeModel;
import mvc.Beobachter;

public class HauptView extends BorderPane implements Beobachter {

	StartPane bp1;
	RecordPane bp2;
	ObservableList<AufnahmeModel> tableModel;
	
	public HauptView(ObservableList<AufnahmeModel> tableModel) {
		this.tableModel = tableModel;
		gestalteAnsicht();
	}
	
	private void gestalteAnsicht() {		
		bp1 = new StartPane(tableModel);
		this.setCenter(bp1);
		bp2 = new RecordPane(tableModel);
	}
	
	@Override
	public void geaenderteAuswahl(String auswahl) {
		switch(auswahl) {
			case "StartPane":
				this.setCenter(bp1);
				break;
			case "RecordPane":
				this.setCenter(bp2);
				break;				
		}
	}

}
