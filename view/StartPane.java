package view;

import java.io.File;
import java.net.URI;

import controller.StartController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import model.AufnahmeModel;

public class StartPane extends BorderPane {
	
	ObservableList<AufnahmeModel> tableModel;
	StartController controller;	
	
    public MediaPlayer mp;
    private MediaView mediaView;
    private HBox mediaBar;
    
	public final TableView<AufnahmeModel> table = new TableView<>();
	
	public StartPane(ObservableList<AufnahmeModel> tableModel) {
		this.controller = new StartController(tableModel, this);
		this.tableModel = tableModel;
		gestalteAnsicht();
	}
	
	@SuppressWarnings("unchecked")
	public void gestalteAnsicht() {
		
		table.setEditable(false);
		
		TableColumn<AufnahmeModel, String> dateiName = new TableColumn<AufnahmeModel, String>("Dateiname");
		dateiName.setMinWidth(282);
		dateiName.setCellValueFactory(new PropertyValueFactory<AufnahmeModel, String>("name"));
		
		TableColumn<AufnahmeModel, String> dauer = new TableColumn<AufnahmeModel, String>("Dauer");
		dauer.setMinWidth(100);
		dauer.setCellValueFactory(new PropertyValueFactory<AufnahmeModel, String>("duration"));
		
		table.setItems(tableModel);
		
		table.getColumns().addAll(dateiName, dauer);
		table.getSelectionModel().selectFirst();
		this.setCenter(table);
		
		VBox vbox = new VBox();	
	    vbox.setPadding(new Insets(10));
	    vbox.setAlignment(Pos.CENTER);
	
        Button playButton  = new Button(">");
        playButton.setOnAction(controller);
        
	    vbox.getChildren().add(playButton);
	    
	    this.setBottom(vbox);
		
	}

}
