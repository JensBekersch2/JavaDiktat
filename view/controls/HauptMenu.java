package view.controls;
/**
 * HauptMenu
 * Das Menü der Anwendung
 * @author Jens Bekersch (bekersch@th-brandenburg.de)
 * @version 1.00, 04.2018
 */
import controller.MenuController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;

public class HauptMenu extends MenuBar {
	
    Menu menu;
    MenuItem item1, item2, item3;
    SeparatorMenuItem separatorMenuItem;
    MenuController menuController;
    
	public HauptMenu(MenuController menuController) {
		this.menuController = menuController;
		gestalteHauptMenu();
	}
	
	private void gestalteHauptMenu() {       
        menu = new Menu("Datei");       
        item1 = new MenuItem("Neue Aufnahme starten");        
        item1.setOnAction(menuController);
        item1.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
        item2 = new MenuItem("Aufnahmen Übersicht");
        item2.setOnAction(menuController);
        item2.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));        
        separatorMenuItem = new SeparatorMenuItem();
        item3 = new MenuItem("Beenden");
        item3.setOnAction(menuController);
        item3.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));       
        menu.getItems().addAll(item1, item2, separatorMenuItem, item3);       
        this.getMenus().add(menu);		
	}
}
