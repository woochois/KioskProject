package controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import javafx.application.*;
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

public class KioskController {

	@FXML
	private Button paymentButton, clearButton, adminButton;

	@FXML
	private ImageView menuKimchi, menuDoinjang, menuDongtae, menuGochujang, menuTuna, menuBoodae, menuBoritea,
			menuChilgtea, menuGreentea, menuArisoo, menuSoongnyoong, menuDoongullae, menuDasick, menuGGooltarae,
			menuHodduck, menuHangwa, menuGangjung, menuYakgwa, menuKimchiSet, menuBoodaeSet, menuGochujangSet,
			menuDongtaeSet, menuTunaSet, menuDoinjangSet;
    @FXML
    private TableView<Basket> tableView;
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	DB
	static String url = "jdbc:oracle:thin:@localhost:1521:xe", user = "team", password = "1234";
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static PreparedStatement pstmt = null;

	void KioskDB(String DB) { // 디비 연동 컨트롤러
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM PRODUCT WHERE PSEQ = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, DB);
			rs = pstmt.executeQuery();
			
			String tempName = rs.getString("NAME");
			String tempPrice = rs.getString("PRICE");
			if (rs.next()) { 
				rs.getString("NAME");
				rs.getString("PRICE");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
				}
			}
		}
	}
    
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 어드민 버튼 액션
	@FXML
	void onAdminButtonAction(ActionEvent event) throws IOException { 
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
		
// 어드민 아이디 비밀번호 강제설정
		result.ifPresent(usernamePassword -> {
			if(username.getText().equals(user) && (passwordF.getText().equals(password))) {
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

	@FXML
	void onClearButton(ActionEvent event) {
		// 테이블 리셋 버튼
//		orderedView.sort();
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 메뉴 버튼 이벤트
	@FXML
	void onMouseClicked(MouseEvent event) throws SQLException {
		
		Basket currentBasket = new Basket("김치찌개");
		ObservableList<Basket> data = tableView.getItems();
		
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("주문");
		Alert alert1 = new Alert(AlertType.INFORMATION);
		alert1.setTitle("확인");
		alert1.setHeaderText("장바구니에 메뉴가 추가되었습니다!");
		alert1.setContentText("고맙습니다. 맛있게 드세요!");

		// 주문 개수 버튼 -> 나중에 번호로 입력받아서 개수 체크
		ButtonType one = new ButtonType("1개");
		ButtonType two = new ButtonType("2개");
		ButtonType three = new ButtonType("3개");
		ButtonType cancel = new ButtonType("취소", ButtonData.CANCEL_CLOSE);

		if (event.getSource().equals(menuKimchi)) {
			
			alert.setTitle("주문");
			alert.setHeaderText("강원도 고랭지 배추로 담근 김치를 \n베이스로 한 얼큰하고 칼칼하고 매콤짭짤한 김치찌개");
			alert.setContentText("가격은 7,000원 입니다.\n몇 개를 주문하시겠습니까?");

			alert.getButtonTypes().setAll(one, two, three, cancel);
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				data.add(currentBasket);
				alert1.showAndWait();
				KioskDB("1");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("1");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("1");
				
			}
		} else if (event.getSource().equals(menuDoinjang)) {
			alert.setHeaderText("전라도 어딘가에서 담근 된장을 \n베이스로 한 구수하니 깊은 맛의 된장찌개");
			alert.setContentText("가격은 7,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("2");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("2");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("2");
				
			}
		} else if (event.getSource().equals(menuDongtae)) {
			alert.setHeaderText("러시아 앞바다에서 직접 잡은 급냉시킨 명태를 \n 통째로 넣고 끊인 담백하고 깔끔한 동태찌개");
			alert.setContentText("가격은 8,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("3");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("3");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("3");
				
			}
		} else if (event.getSource().equals(menuGochujang)) {
			alert.setHeaderText("순창에서 직접 기른 태양초고추로 담근 고추장을 \n베이스로 한 깊고 칼칼한 고추장찌개");
			alert.setContentText("가격은 5,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("4");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("4");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("4");
				
			}
		} else if (event.getSource().equals(menuTuna)) {

			alert.setHeaderText("주인이 원양어선을 타고 낚은 참다랑어로\n 만든 고소하고 기름진 참치찌개");
			alert.setContentText("가격은 5,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("5");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("5");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("5");
				
			}
		} else if (event.getSource().equals(menuBoodae)) {

			alert.setHeaderText("스팸과 독일 소세지로 만든\n 얼큰한 부대찌개");
			alert.setContentText("가격은 8,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("6");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("6");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("6");
				
			}
		} else if (event.getSource().equals(menuBoritea)) {

			alert.setHeaderText("고향 땅에서 직접 키운 청보리로\n 우려낸 시원하고 구수한 보리차");
			alert.setContentText("가격은 2,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("7");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("7");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("7");
				
			}
		} else if (event.getSource().equals(menuChilgtea)) {
			alert.setHeaderText("칡차");
			alert.setContentText("가격은 7,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("12");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("12");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("12");
				
			}
		} else if (event.getSource().equals(menuGreentea)) {
			alert.setHeaderText("보성 녹차");
			alert.setContentText("가격은 5,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("11");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("11");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("11");
				
			}
		} else if (event.getSource().equals(menuArisoo)) {
			alert.setHeaderText("아리수");
			alert.setContentText("가격은 12,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
			} else if (result.get() == two) {
				alert1.showAndWait();
			} else if (result.get() == three) {
				alert1.showAndWait();
			}
		} else if (event.getSource().equals(menuSoongnyoong)) {
			alert.setHeaderText("숭늉");
			alert.setContentText("가격은 2,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("10");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("10");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("10");
				
			}
		} else if (event.getSource().equals(menuDoongullae)) {
			alert.setHeaderText("둥굴레차");
			alert.setContentText("가격은 2,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("8");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("8");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("8");
				
			}
		} else if (event.getSource().equals(menuDasick)) {
			alert.setHeaderText("다식");
			alert.setContentText("가격은 8,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("13");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("13");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("13");
				
			}
		} else if (event.getSource().equals(menuGGooltarae)) {
			alert.setHeaderText("인사동 그 맛. 꿀타래 (15개입)");
			alert.setContentText("가격은 15,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("18");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("18");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("18");
				
			}
		} else if (event.getSource().equals(menuHodduck)) {
			alert.setHeaderText("호떡");
			alert.setContentText("가격은 2,500원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("15");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("15");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("15");
				
			}
		} else if (event.getSource().equals(menuHangwa)) {
			alert.setHeaderText("한과");
			alert.setContentText("가격은 8,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("17");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("17");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("17");
				
			}
		} else if (event.getSource().equals(menuGangjung)) {
			alert.setHeaderText("강정");
			alert.setContentText("가격은 8,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("16");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("16");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("16");
				
			}
		} else if (event.getSource().equals(menuKimchiSet)) {

			alert.setHeaderText("김찌세트 : 김치찌개 + 숭늉 + 꿀타래");
			alert.setContentText("가격은 17,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("19");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("19");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("19");
				
			}
		} else if (event.getSource().equals(menuBoodaeSet)) {
			alert.setHeaderText("부찌세트 : 부대찌개 + 녹차 + 약과");
			alert.setContentText("가격은 18,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("24");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("24");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("24");
				
			}
		} else if (event.getSource().equals(menuGochujangSet)) {
			alert.setHeaderText("고추찌세트 : 고추장찌개 + 보리차 + 한과");
			alert.setContentText("가격은 15,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("22");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("22");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("22");
				
			}
		} else if (event.getSource().equals(menuDongtaeSet)) {

			alert.setHeaderText("동찌세트 : 동태찌개 + 아리수 + 호떡");
			alert.setContentText("가격은 18,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("21");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("21");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("21");
				
			}
		} else if (event.getSource().equals(menuTunaSet)) {
			alert.setHeaderText("참찌세트 : 침치찌개 + 둥굴레차 + 강정");
			alert.setContentText("가격은 15,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("23");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("23");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("23");
				
			}
		} else if (event.getSource().equals(menuDoinjangSet)) {
			alert.setHeaderText("된찌세트 : 된장찌개 + 칡차 + 다식");
			alert.setContentText("가격은 17,000원 입니다.\n몇 개를 주문하시겠습니까?");
			alert.getButtonTypes().setAll(one, two, three, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == one) {
				alert1.showAndWait();
				KioskDB("20");
				
			} else if (result.get() == two) {
				alert1.showAndWait();
				KioskDB("20");
				
			} else if (result.get() == three) {
				alert1.showAndWait();
				KioskDB("20");
				
			}
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 결제
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
		} else if (result.get() == card) {
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("결제");
			alert2.setHeaderText("카드로 결제가 완료되었습니다!");
			alert2.setContentText("고맙습니다. 맛있게 드세요!");
			alert2.showAndWait();
		}
	}

}
