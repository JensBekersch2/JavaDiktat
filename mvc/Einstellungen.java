package mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * PropertiesManager
 * Klasse setzt, schreibt und liest definierte Systemeinstellungen
 * @author Jens Bekersch <jens@bekersch.com>
 * @version 1.0.0, 03.04.2018
 */
public class Einstellungen {
    /** Initialisiert neues Einstellungs-Objekt */
    public static Properties properties = new Properties();
    /** Pfadangabe zum Einstellungsfile: verstecktes Verzeichnis im Home Verzeichnis des Benutzers */
    public static final String PATH_TO_PROPERTY_FILE = System.getProperty("user.home") + System.getProperty("file.separator") + ".diktat";
    /** Name der Einstellungsdatei */
    public static final String PROPERTY_FILENAME = "einstellungen.ini";
    /**
     * Gibt den Pfad zur Einstellungsdatei zurück
     */    
    public static String getPathToPropertyFile() {
    	return PATH_TO_PROPERTY_FILE;
    }
    /**
     * Setzt die Eigenschaften im Einstellungs-Objekt
     */
    public static void setProperties() {
    	properties.clear();
    	properties.put("anwendungsBreite",EinstellungenDefaultWerte.ANWENDUNGSBREITE);
    	properties.put("anwendungsHoehe",EinstellungenDefaultWerte.ANWENDUNGSHOEHE);
    }   
    
    public static void setProperties(String anwendungsBreite, String anwendungsHoehe) {
    	properties.clear();
    	properties.put("anwendungsBreite",anwendungsBreite);
    	properties.put("anwendungsHoehe",anwendungsHoehe);
    }  
    /**
     * Schreibt die Einstellungen
     * Pr�ft, ob das Verzeichnis bereits vorhanden ist, erstellt dieses, wenn nicht und schreibt die Einstellungen im XML Format in die .ini Datei
     */
    public static void writeProperties() {
        try {
            File path_to_settingsIni = new File(PATH_TO_PROPERTY_FILE);
            if (!path_to_settingsIni.exists()) {
                path_to_settingsIni.mkdir();
            }
            properties.storeToXML(new FileOutputStream(new File(PATH_TO_PROPERTY_FILE + File.separator + PROPERTY_FILENAME)), null);
        } catch (IOException e) {
            System.out.println("Systemeinstellungen konnten nicht geschrieben werden.");
        }    
    }
    /**
     * Liest die Einstellungen aus der .ini Datei
     * @return boolean Lesevorgang erfolgreich: true/false
     */
    public static boolean readProperties() {
        boolean propertyReaderSuccess = true;
        try {          
            Properties p = new Properties(System.getProperties());
            p.loadFromXML(new FileInputStream(PATH_TO_PROPERTY_FILE + File.separator + PROPERTY_FILENAME));
            System.setProperties(p);
        } catch (IOException e) {
            propertyReaderSuccess = false;
        }
        return propertyReaderSuccess;
    }
 
}

