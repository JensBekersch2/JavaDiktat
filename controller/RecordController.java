package controller;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.AufnahmeModel;
import service.RecordService;
import view.RecordPane;

public class RecordController implements EventHandler<ActionEvent> {

	ObservableList<AufnahmeModel> tableModel;
	RecordPane bp2;
	RecordService recordService;
	AudioInputStream audioInputStream;
	File file;
	
	public RecordController(ObservableList<AufnahmeModel> tableModel, RecordPane bp2) {
		this.tableModel = tableModel;
		this.bp2 = bp2;
	}

	@Override
	public void handle(ActionEvent e) {
		Button button = (Button) e.getSource();
		switch(button.getText()) {
		case "Aufnahme starten":
			if(bp2.textField.getText().isEmpty()) {
				bp2.message.setText("Bitte geben Sie einen Namen an!");
			} else {
				if(!bp2.message.getText().isEmpty())
					bp2.message.setText("");
				button.setText("Aufnahme stoppen");
				
				String filename = bp2.textField.getText() + ".wav";				
				file = new File(filename);
				
				recordService = new RecordService(file);
				recordService.start();
				
			}
			break;			
		case "Aufnahme stoppen":
			button.setText("Aufnahme starten");
			recordService.cancel();
			
			File homeDirectory = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Diktat");
			File recordFile = new File(homeDirectory + File.separator + file);
			
			try {
				audioInputStream = AudioSystem.getAudioInputStream(recordFile);
			} catch (UnsupportedAudioFileException | IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
        	AudioFormat format = audioInputStream.getFormat();
        	long frames = audioInputStream.getFrameLength();
        	double durationInSeconds = (frames+0.0) / format.getFrameRate(); 

            int seconds = (int) (durationInSeconds % 60);
            int minutes = (int) (durationInSeconds / 60) % 60;
            int hours = (int) (durationInSeconds / (60 * 60)) % 24;
            
            AufnahmeModel aufnahmeModel = new AufnahmeModel(file.getName(), String.format("%02d:%02d:%02d", hours, minutes, seconds));
        	tableModel.add(aufnahmeModel);	
        	
			break;
		}

	}
}
