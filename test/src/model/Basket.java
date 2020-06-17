package model;

import javafx.beans.property.*;

public class Basket {
	private StringProperty menuName;
	private IntegerProperty menuCount;
	private IntegerProperty menuPrice;

	public Basket(StringProperty menuName, IntegerProperty menuCount, IntegerProperty menuPrice) {
		this.menuName = menuName;
		this.menuCount = menuCount;
		this.menuPrice = menuPrice;
	}

	public StringProperty getMenuName() {
		return menuName;
	}
	
	public IntegerProperty getMenuCount() {
		return menuCount;
	}
	
	public IntegerProperty getMenuPrice() {
		return menuPrice;
	}

}
