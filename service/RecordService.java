package service;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import mvc.LogfileManager;

import javax.sound.sampled.AudioFileFormat.Type;

public class RecordService extends Service<Void> {
	
	TargetDataLine line;
	AudioInputStream stream;
	File soundFile;
	
	public RecordService(File file) {
		this.soundFile = file;
	}
	
	@Override
	protected Task<Void> createTask() {
		
		return new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				 
				float sampleRate = 16000;
				int sampleSizeInBits = 16;
				int channels = 1;
				boolean signed = true;
				boolean bigEndian = true;
				AudioFormat format =  new AudioFormat(sampleRate, 
				  sampleSizeInBits, channels, signed, bigEndian);


				
				DataLine.Info info = new DataLine.Info(TargetDataLine.class, format); 
					if (!AudioSystem.isLineSupported(info)) {
							LogfileManager.logInfo("Kein Line in gefunden!");
					    } 
					try {
					    line = (TargetDataLine) AudioSystem.getLine(info);
					    line.open(format);
					} catch (LineUnavailableException ex) {
						LogfileManager.logInfo("Line in ist nicht verfügbar!");
					}
					
				line.start();

				File homeDirectory = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Diktat");
				if(!homeDirectory.exists()) {
					homeDirectory.mkdir();
				}
				File file = new File(homeDirectory + File.separator + soundFile);

				AudioInputStream stream = new AudioInputStream(line);
				

				AudioSystem.write(stream, Type.WAVE, file);

				return null;
			}
			
		};


	}

	@Override
	protected void cancelled() {
		line.stop();
		line.close();
	}

}
