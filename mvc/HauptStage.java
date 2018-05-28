package mvc;
/**
 * HauptStage
 * Die HauptStage der Applikation 
 * @author Jens Bekersch (bekersch@th-brandenburg.de)
 * @version 1.00, 04.2018
 */
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import controller.MenuController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.AufnahmeModel;
import model.Model;
import view.HauptView;
import view.controls.HauptMenu;

public class HauptStage extends Application {

	Stage stage;
	Scene scene;
	BorderPane borderPane;
	AufnahmeModel aufnahmeModel;
	ObservableList<AufnahmeModel> tableModel = FXCollections.observableArrayList();
	AudioInputStream audioInputStream;
	/**
	 * Die init-Methode
	 * Ermöglicht die Ausführung von Methoden während des Programmstartes.
	 * Liest die Einstellungen ein
	 */
	public void init() {
        if(!Einstellungen.readProperties()) {
        	Einstellungen.setProperties();
        	Einstellungen.writeProperties();
        	Einstellungen.readProperties();
        }
        LogfileManager.init();
        LogfileManager.logInfo("Programm startup");
        
        File folder = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Diktat");
        for (final File file : folder.listFiles()) {
            if (!file.isDirectory()) {
				try {
					audioInputStream = AudioSystem.getAudioInputStream(file);
				} catch (UnsupportedAudioFileException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	AudioFormat format = audioInputStream.getFormat();
            	long frames = audioInputStream.getFrameLength();
            	double durationInSeconds = (frames+0.0) / format.getFrameRate(); 

                int seconds = (int) (durationInSeconds % 60);
                int minutes = (int) (durationInSeconds / 60) % 60;
                int hours = (int) (durationInSeconds / (60 * 60)) % 24;
                
            	aufnahmeModel = new AufnahmeModel(file.getName(), String.format("%02d:%02d:%02d", hours, minutes, seconds));
            	tableModel.add(aufnahmeModel);
            }
        }

	}
	/**
	 * Die start-Methode
	 * Initialisiert die Stage mit Menü und Standardansicht
	 */
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		// Initialisierung der BorderPane 
		borderPane = new BorderPane();
		
		// Initialisierung des Datenmodells
		Model model = new Model();
		// Initialisierung der Ansicht
		HauptView hauptView = new HauptView(tableModel);
		// Initialisierung des Controllers
		MenuController menuController = new MenuController(model, hauptView);
		
		// Initialisierung und Positionierung des Menüs
        HauptMenu hauptMenu = new HauptMenu(menuController);      
        borderPane.setTop(hauptMenu);				
		
        // Positionierung der View auf der Stage
        borderPane.setCenter(hauptView);
 
        // Zeige das Programmfenster mit allen benötigten Komponenten an
        scene = new Scene(borderPane);    
        stage.setWidth(Integer.parseInt(System.getProperty("anwendungsBreite")));
        stage.setHeight(Integer.parseInt(System.getProperty("anwendungsHoehe")));
        stage.setScene(scene);
        stage.setTitle("Diktat");
        stage.show();	
	}
	/**
	 * Die stop-Methode
	 * Wird beim Beenden des Programmes ausgeführt.
	 * Speichert die Größe des akutellen Fensters in den Einstellungen
	 */
	public void stop() {
		Einstellungen.setProperties(Integer.toString((int) stage.getWidth()), Integer.toString((int) stage.getHeight()));
		Einstellungen.writeProperties();
	}


}
