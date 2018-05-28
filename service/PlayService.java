package service;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import mvc.LogfileManager;

public class PlayService extends Service<Void> {

	private static final int EXTERNAL_BUFFER_SIZE = 128;
	SourceDataLine line = null;
	AudioInputStream audioInputStream = null;
	File file;
	
	public PlayService(File file) {
		this.file = file;
	}
	
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				try {
					audioInputStream = AudioSystem.getAudioInputStream(file);
				} catch (Exception exception) {
					LogfileManager.logInfo("AudioInputStream: Die Datei kann nicht abgespielt werden!");
				};
				
				AudioFormat audioFormat = audioInputStream.getFormat();		
				
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
				
				try {
					line = (SourceDataLine) AudioSystem.getLine(info);
					line.open();
				} catch (Exception exception) {
					LogfileManager.logInfo("Line: Die Datei kann nicht abgespielt werden!");
				};
				line.start();

				byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
				int nBytesRead = 0;
				while (nBytesRead != -1) {
					try {
						nBytesRead = audioInputStream.read(abData, 0, abData.length);
					} catch (Exception exception) {
						LogfileManager.logInfo("Fehler beim Lesen der Datei!");
					};
					
					if (nBytesRead >= 0)
						line.write(abData, 0, nBytesRead);
					}
				
				return null;		
			}
		};
	}
	
	@Override
	protected void cancelled() {
		line.stop();
		line.close();
		line = null;
	}
}
