package controller;

import java.util.*;

import javafx.beans.property.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import model.*;

public class AdminController extends KioskDBController implements Initializable{

	@FXML
	private TableView<Ordered> tableViewA;

	@FXML
	private TableColumn<Ordered, String> orderMenu;

	@FXML
	private TableColumn<Ordered, Integer> orderCount, orderPrice, orderTotal;;

	Ordered ordered = new Ordered(null, null, null, null);
	
	static ObservableList<Ordered> myList1 = FXCollections.observableArrayList();

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		for (int i = 1; i < 19; i++) {
			myList1.add(new Ordered(new SimpleStringProperty(NameDB(i)), new SimpleIntegerProperty(CountDB(i)),
					new SimpleIntegerProperty(PriceDB(i)), new SimpleIntegerProperty(TotalDB(i))));
			tableViewA.setItems(myList1);
		}
		orderMenu.setCellValueFactory(cellData -> cellData.getValue().getName());
		orderCount.setCellValueFactory(cellData -> cellData.getValue().getCount().asObject());
		orderPrice.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
		orderTotal.setCellValueFactory(cellData -> cellData.getValue().getTotal().asObject());
	}

}
