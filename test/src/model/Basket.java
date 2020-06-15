package model;

import javafx.beans.property.*;

public class Basket {
	private SimpleStringProperty menuName;
//	public SimpleStringProperty menuPrice;

	public Basket() {
//		this(null, null);
	}

//	public Basket(String menuName, String menuPrice) {
	public Basket(String menuName) {
		this.menuName = new SimpleStringProperty(menuName);
//		this.menuPrice = new SimpleStringProperty(menuPrice);
	}

	protected String getMenuName() {
		return menuName.get();
	}

	protected void setMenuName(String menuName) {
		this.menuName.set(menuName);
	}

	public StringProperty menuNameProperty() {
		return menuName;
	}

//	protected String getMenuPrice() {
//		return menuPrice.get();
//	}
//
//	protected void setMenuPrice(String menuPrice) {
//		this.menuPrice.set(menuPrice);
//	}
//
//	public StringProperty menuPriceProperty() {
//		return menuPrice;
//	}

}
