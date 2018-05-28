package controller;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.AufnahmeModel;
import service.PlayService;
import view.StartPane;

public class StartController implements EventHandler<ActionEvent> {
	
	private static final int EXTERNAL_BUFFER_SIZE = 128;
	ObservableList<AufnahmeModel> tableModel;
	StartPane bp1;
	SourceDataLine line = null;
	PlayService playService;
	
	public StartController(ObservableList<AufnahmeModel> tableModel, StartPane bp1) {
		this.tableModel = tableModel;
		this.bp1 = bp1;
	}

	@Override
	public void handle(ActionEvent e) {
		Button button = (Button) e.getSource();
		switch(button.getText()) {
		case ">":
			button.setText("||");
			AufnahmeModel aufnahmeModel = bp1.table.getSelectionModel().getSelectedItem();
			File homeDirectory = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Diktat");
			File soundFile = new File(homeDirectory + File.separator + aufnahmeModel.getName());

			playService = new PlayService(soundFile);
			playService.start();
			
			playService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent arg0) {
					button.setText(">");				
				}				
			});
			
			break;
		case "||":
			button.setText(">");
			playService.cancel();
			break;
		}
	}
}
