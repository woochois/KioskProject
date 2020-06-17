package controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import javafx.application.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.control.ButtonBar.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.*;
import model.*;

public class KioskController extends KioskDBController implements Initializable {

	Basket basket = new Basket(null, null, null);
	
	static ObservableList<Basket> myList = FXCollections.observableArrayList();

	@FXML
	private ImageView menuKimchi, menuDoinjang, menuDongtae, menuGochujang, menuTuna, menuBoodae, menuBoritea,
			menuChilgtea, menuGreentea, menuArisoo, menuSoongnyoong, menuDoongullae, menuDasick, menuGGooltarae,
			menuHodduck, menuHangwa, menuGangjung, menuYakgwa, menuKimchiSet, menuBoodaeSet, menuGochujangSet,
			menuDongtaeSet, menuTunaSet, menuDoinjangSet;

	@FXML
	private TableView<Basket> tableView, tableView1, tableView2, tableView3;

	@FXML
	private TableColumn<Basket, String> tableMenu, tableMenu1, tableMenu2, tableMenu3;

	@FXML
	private TableColumn<Basket, Integer> tableCount, tableCount1, tableCount2, tableCount3, tablePrice, tablePrice1,
			tablePrice2, tablePrice3;

	@FXML
	private Button clearButton, paymentButton;

    @FXML
    private Label totalPrice, totalPrice1, totalPrice2, totalPrice3;
        
    @FXML
    private Hyperlink hiddenAdmin;

//=================================================<메뉴 뷰>======================================================//	
	static Integer tot = 0;
	@FXML
	void onMouseClicked(MouseEvent event) throws SQLException {

		Alert alert1 = new Alert(AlertType.INFORMATION);
		alert1.setTitle("확인");
		alert1.setHeaderText("장바구니에 메뉴가 추가되었습니다!");
		alert1.setContentText("고맙습니다. 맛있게 드세요!");

// 주문 개수 버튼 -> 나중에 번호로 입력받아서 개수 체크

		TextInputDialog dialog = new TextInputDialog("1");
		dialog.setTitle("주문");
		
		if (event.getSource().equals(menuKimchi)) {
			dialog.setHeaderText("강원도 고랭지 배추로 담근 김치를 \n베이스로 한 얼큰하고 칼칼하고 매콤짭짤한 김치찌개");
			dialog.setContentText("가격은 7,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("1")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(1) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(1) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(10, MenuNameDB("1"), Integer.parseInt(result.get()), MenuPriceDB(1), MenuPriceDB(1) * Integer.parseInt(result.get()));
			}

		} else if (event.getSource().equals(menuDoinjang)) {
			dialog.setHeaderText("전라도 어딘가에서 담근 된장을 \n베이스로 한 구수하니 깊은 맛의 된장찌개");
			dialog.setContentText("가격은 7,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("2")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(2) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(2) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(10, MenuNameDB("2"), Integer.parseInt(result.get()), MenuPriceDB(2), MenuPriceDB(2) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuDongtae)) {
			dialog.setHeaderText("러시아 앞바다에서 직접 잡은 급냉시킨 명태를 \n 통째로 넣고 끊인 담백하고 깔끔한 동태찌개");
			dialog.setContentText("가격은 8,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("3")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(3) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(3) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(10, MenuNameDB("3"), Integer.parseInt(result.get()), MenuPriceDB(3), MenuPriceDB(3) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuGochujang)) {
			dialog.setHeaderText("순창에서 직접 기른 태양초고추로 담근 고추장을 \n베이스로 한 깊고 칼칼한 고추장찌개");
			dialog.setContentText("가격은 5,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("4")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(4) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(4) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(10, MenuNameDB("4"), Integer.parseInt(result.get()), MenuPriceDB(4), MenuPriceDB(4) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuTuna)) {
			dialog.setHeaderText("주인이 원양어선을 타고 낚은 참다랑어로\n 만든 고소하고 기름진 참치찌개");
			dialog.setContentText("가격은 5,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("5")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(5) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(5) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(10, MenuNameDB("5"), Integer.parseInt(result.get()), MenuPriceDB(5), MenuPriceDB(5) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuBoodae)) {
			dialog.setHeaderText("스팸과 독일 소세지로 만든\n 얼큰한 부대찌개");
			dialog.setContentText("가격은 8,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("6")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(6) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(6) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(10, MenuNameDB("6"), Integer.parseInt(result.get()), MenuPriceDB(6), MenuPriceDB(6) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuBoritea)) {
			dialog.setHeaderText("고향 땅에서 직접 키운 청보리로\n 우려낸 시원하고 구수한 보리차");
			dialog.setContentText("가격은 2,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("7")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(7) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(7) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(20, MenuNameDB("7"), Integer.parseInt(result.get()), MenuPriceDB(7), MenuPriceDB(7) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuDoongullae)) {
			dialog.setHeaderText("둥굴레차");
			dialog.setContentText("가격은 2,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("8")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(8) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(8) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(20, MenuNameDB("8"), Integer.parseInt(result.get()), MenuPriceDB(8), MenuPriceDB(8) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuSoongnyoong)) {
			dialog.setHeaderText("숭늉");
			dialog.setContentText("가격은 2,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("9")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(9) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(9) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(20, MenuNameDB("9"), Integer.parseInt(result.get()), MenuPriceDB(9), MenuPriceDB(9) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuArisoo)) {
			dialog.setHeaderText("아리수");
			dialog.setContentText("가격은 12,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("10")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(10) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(10) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(20, MenuNameDB("10"), Integer.parseInt(result.get()), MenuPriceDB(10), MenuPriceDB(10) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuGreentea)) {
			dialog.setHeaderText("보성 녹차");
			dialog.setContentText("가격은 5,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("11")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(11) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(11) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(20, MenuNameDB("11"), Integer.parseInt(result.get()), MenuPriceDB(11), MenuPriceDB(11) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuChilgtea)) {
			dialog.setHeaderText("칡차");
			dialog.setContentText("가격은 7,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("12")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(12) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(12) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(20, MenuNameDB("12"), Integer.parseInt(result.get()), MenuPriceDB(12), MenuPriceDB(12) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuDasick)) {
			dialog.setHeaderText("다식");
			dialog.setContentText("가격은 8,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("13")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(13) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(13) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(30, MenuNameDB("13"), Integer.parseInt(result.get()), MenuPriceDB(13), MenuPriceDB(13) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuYakgwa)) {
			dialog.setHeaderText("약과");
			dialog.setContentText("가격은 8,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("14")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(14) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(14) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(30, MenuNameDB("14"), Integer.parseInt(result.get()), MenuPriceDB(14), MenuPriceDB(14) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuHodduck)) {
			dialog.setHeaderText("호떡");
			dialog.setContentText("가격은 2,500원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("15")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(15) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(15) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(30, MenuNameDB("15"), Integer.parseInt(result.get()), MenuPriceDB(15), MenuPriceDB(15) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuGangjung)) {
			dialog.setHeaderText("강정");
			dialog.setContentText("가격은 8,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("16")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(16) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(16) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(30, MenuNameDB("16"), Integer.parseInt(result.get()), MenuPriceDB(16), MenuPriceDB(16) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuHangwa)) {
			dialog.setHeaderText("한과");
			dialog.setContentText("가격은 8,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("17")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(17) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(17) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(30, MenuNameDB("17"), Integer.parseInt(result.get()), MenuPriceDB(17), MenuPriceDB(17) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuGGooltarae)) {
			dialog.setHeaderText("인사동 그 맛. 꿀타래 (15개입)");
			dialog.setContentText("가격은 15,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("18")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(18) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(18) * Integer.parseInt(result.get());;
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(30, MenuNameDB("18"), Integer.parseInt(result.get()), MenuPriceDB(18), MenuPriceDB(18) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuKimchiSet)) {
			dialog.setHeaderText("김찌세트 : 김치찌개 + 숭늉 + 꿀타래");
			dialog.setContentText("가격은 17,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("19")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(19) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(19) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(40, MenuNameDB("19"), Integer.parseInt(result.get()), MenuPriceDB(19), MenuPriceDB(19) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuDoinjangSet)) {
			dialog.setHeaderText("된찌세트 : 된장찌개 + 칡차 + 다식");
			dialog.setContentText("가격은 17,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("20")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(20) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(20) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(40, MenuNameDB("20"), Integer.parseInt(result.get()), MenuPriceDB(20), MenuPriceDB(20) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuDongtaeSet)) {
			dialog.setHeaderText("동찌세트 : 동태찌개 + 아리수 + 호떡");
			dialog.setContentText("가격은 18,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("21")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(21) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(21) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(40, MenuNameDB("21"), Integer.parseInt(result.get()), MenuPriceDB(21), MenuPriceDB(21) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuGochujangSet)) {
			dialog.setHeaderText("고추찌세트 : 고추장찌개 + 보리차 + 한과");
			dialog.setContentText("가격은 15,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("22")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(22) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(22) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(40, MenuNameDB("22"), Integer.parseInt(result.get()), MenuPriceDB(22), MenuPriceDB(22) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuTunaSet)) {
			dialog.setHeaderText("참찌세트 : 침치찌개 + 둥굴레차 + 강정");
			dialog.setContentText("가격은 15,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("23")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(23) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(23) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(40, MenuNameDB("23"), Integer.parseInt(result.get()), MenuPriceDB(23), MenuPriceDB(23) * Integer.parseInt(result.get()));
			}
		} else if (event.getSource().equals(menuBoodaeSet)) {
			dialog.setHeaderText("부찌세트 : 부대찌개 + 녹차 + 약과");
			dialog.setContentText("가격은 18,000원 입니다.\n몇 개를 주문하시겠습니까?");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				myList.add(new Basket(new SimpleStringProperty(MenuNameDB("24")), new SimpleIntegerProperty(Integer.parseInt(result.get())),
						new SimpleIntegerProperty(MenuPriceDB(24) * Integer.parseInt(result.get()))));
				tableView.setItems(myList);
				tableView1.setItems(myList);
				tableView2.setItems(myList);
				tableView3.setItems(myList);
				tot += MenuPriceDB(24) * Integer.parseInt(result.get());
				totalPrice.setText("총 가격 : " + tot + "원");
				totalPrice1.setText("총 가격 : " + tot + "원");
				totalPrice2.setText("총 가격 : " + tot + "원");
				totalPrice3.setText("총 가격 : " + tot + "원");
				PaymentDB(40, MenuNameDB("24"), Integer.parseInt(result.get()), MenuPriceDB(24), MenuPriceDB(24) * Integer.parseInt(result.get()));
			}
		}
	}

//=================================================<결제 뷰>======================================================//	

	@FXML
	void onPaymentButton(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("결제");

		alert.setHeaderText("주문내역 확인하셨나요?");
		alert.setContentText("결제를 도와드리겠습니다.");

		ButtonType cash = new ButtonType("현금 결제");
		ButtonType card = new ButtonType("카드 결제");
		ButtonType cancel = new ButtonType("취소", ButtonData.CANCEL_CLOSE);
 
		alert.getButtonTypes().setAll(cash, card, cancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == cash) {
			
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setTitle("결제");
			alert1.setHeaderText("현금으로 결제가 완료되었습니다!");
			alert1.setContentText("고맙습니다. 맛있게 드세요!");
			alert1.showAndWait();
			totalPrice.setText("총 가격");
			totalPrice1.setText("총 가격");
			totalPrice2.setText("총 가격");
			totalPrice3.setText("총 가격");
			PaymentDB(0, "현금결제완료", 0, 0, 0);
			tot = 0;
			myList.clear();
		} else if (result.get() == card) {
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("결제");
			alert2.setHeaderText("카드로 결제가 완료되었습니다!");
			alert2.setContentText("고맙습니다. 맛있게 드세요!");
			alert2.showAndWait();
			totalPrice.setText("총 가격");
			totalPrice1.setText("총 가격");
			totalPrice2.setText("총 가격");
			totalPrice3.setText("총 가격");
			PaymentDB(0, "카드결제완료", 0, 0, 0);
			tot = 0;
			myList.clear();
		}
	}

//=================================================<어드민 뷰>======================================================//

	// 어드민 버튼 액션
	@FXML
	void onAdminClicked(MouseEvent event) throws IOException {

		// 다이알로그 생성
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setHeaderText("관리자님 환영합니다.");

		// 버튼 타입 지정
		ButtonType loginButtonType = new ButtonType("확인", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// 관리자 아이디 입력칸과 비밀번호 입력칸, 그리고 라벨
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(15, 15, 10, 10));

		TextField username = new TextField();
		username.setPromptText("관리자 ID");
		PasswordField passwordF = new PasswordField();
		passwordF.setPromptText("비밀번호");

		grid.add(new Label("관리자 ID :"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("비밀번호 :"), 0, 1);
		grid.add(passwordF, 1, 1);

		// 관리자 아이디 입력칸에 아무것도 입력되지 않으면 로그인 버튼 비활성화
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		username.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// 관리자 아이디 입력칸 초기화
		Platform.runLater(() -> username.requestFocus());

		// 로그인 버튼을 클릭하면 결과를 사용자 이름-암호-페어로 변환
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(username.getText(), passwordF.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

// 어드민 아이디 체크
		result.ifPresent(usernamePassword -> {
			if (username.getText().equals(AdminIdDB()) && (passwordF.getText().equals(AdminPwDB()))) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AdminMain.fxml"));
				try {
					Parent root = (Parent) loader.load();
					Stage da = new Stage();
					Scene scene = new Scene(root);
					da.setTitle("관리자 페이지");
					da.setScene(scene);
					da.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText("아이디와 비밀번호가 다릅니다!");
				alert.setContentText("확인하시고 다시 입력해주세요!");
				alert.showAndWait();
			}
		});
	}

//=================================================<테이블 뷰>======================================================//
	
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		tableMenu.setCellValueFactory(cellData -> cellData.getValue().getMenuName());
		tableCount.setCellValueFactory(cellData -> cellData.getValue().getMenuCount().asObject());
		tablePrice.setCellValueFactory(cellData -> cellData.getValue().getMenuPrice().asObject());
		
		tableMenu1.setCellValueFactory(cellData -> cellData.getValue().getMenuName());
		tableCount1.setCellValueFactory(cellData -> cellData.getValue().getMenuCount().asObject());
		tablePrice1.setCellValueFactory(cellData -> cellData.getValue().getMenuPrice().asObject());
		
		tableMenu2.setCellValueFactory(cellData -> cellData.getValue().getMenuName());
		tableCount2.setCellValueFactory(cellData -> cellData.getValue().getMenuCount().asObject());
		tablePrice2.setCellValueFactory(cellData -> cellData.getValue().getMenuPrice().asObject());
		
		tableMenu3.setCellValueFactory(cellData -> cellData.getValue().getMenuName());
		tableCount3.setCellValueFactory(cellData -> cellData.getValue().getMenuCount().asObject());
		tablePrice3.setCellValueFactory(cellData -> cellData.getValue().getMenuPrice().asObject());

		tableView.setPlaceholder(new Label("주문을 해주세요."));
		tableView1.setPlaceholder(new Label("주문을 해주세요."));
		tableView2.setPlaceholder(new Label("주문을 해주세요."));
		tableView3.setPlaceholder(new Label("주문을 해주세요."));

	}
	
	
//=================================================<테이블뷰 초기화>======================================================//	
	@FXML
	void onClearButton(ActionEvent event) {
		totalPrice.setText("총 가격");
		totalPrice1.setText("총 가격");
		totalPrice2.setText("총 가격");
		totalPrice3.setText("총 가격");
		tot = 0;
		
		PaymentDB(0, "결제취소", 0, 0, 0);
		myList.clear();
	}

}
