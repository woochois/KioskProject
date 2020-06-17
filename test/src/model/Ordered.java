package model;

import javafx.beans.property.*;

public class Ordered {
	private StringProperty Name;
	private IntegerProperty Count;
	private IntegerProperty Price;
	private IntegerProperty Total;

	public Ordered(StringProperty Name, IntegerProperty Count, IntegerProperty Price, IntegerProperty Total) {
		this.Name = Name;
		this.Count = Count;
		this.Price = Price;
		this.Total = Total;
	}

	public StringProperty getName() {
		return Name;
	}
	
	public IntegerProperty getCount() {
		return Count;
	}
	
	public IntegerProperty getPrice() {
		return Price;
	}
	
	public IntegerProperty getTotal() {
		return Total;
	}
}
