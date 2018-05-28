package view;

import controller.RecordController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.AufnahmeModel;

public class RecordPane extends BorderPane {

	public Label label;
	public Label message;
	public TextField textField;

	RecordController controller;	
	
	public RecordPane(ObservableList<AufnahmeModel> tableModel) {
		this.controller = new RecordController(tableModel, this);
		gestalteAnsicht();
	}
	
	public void gestalteAnsicht() {
		
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    vbox.setAlignment(Pos.BASELINE_CENTER);
	
	    label = new Label("Diktat aufnehmen");
		vbox.getChildren().add(label);
		
	    textField = new TextField();
	    textField.setPromptText("Namen für Aufnahme eingeben");
		vbox.getChildren().add(textField);
		
		Button button = new Button("Aufnahme starten");
		button.setOnAction(controller);
		vbox.getChildren().add(button);

		message = new Label("");
		vbox.getChildren().add(message);
		
		this.setCenter(vbox);
	}
}
