package application;

import javafx.beans.property.*;

public class KioskMenu {
	private StringProperty menuM;

	public KioskMenu() {
		this(null);
	}
	
	
	
	public KioskMenu(String menuM) {
		this.menuM = new SimpleStringProperty(menuM);
	}



	public StringProperty getMenuM() {
		return menuM;
	}


	public void setMenuM(StringProperty menuM) {
		this.menuM = menuM;
	}
	
}
