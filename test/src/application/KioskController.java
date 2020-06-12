package application;

import java.sql.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;

public class KioskController {

    @FXML
    private Button paymentButton;

    @FXML
    private Button clearButton;

    @FXML
    private ImageView menuKimchi;

    @FXML
    private ImageView menuDoinjang;

    @FXML
    private ImageView menuDongtae;

    @FXML
    private ImageView menuGochujang;

    @FXML
    private ImageView menuTuna;

    @FXML
    private ImageView menuBoodae;

    @FXML
    private ImageView menuBoritea;

    @FXML
    private ImageView menuChilgtea;

    @FXML
    private ImageView menuGreentea;

    @FXML
    private ImageView menuArisoo;

    @FXML
    private ImageView menuSoongnyoong;

    @FXML
    private ImageView menuDoongullae;

    @FXML
    private ImageView menuDasick;

    @FXML
    private ImageView menuGGooltarae;

    @FXML
    private ImageView menuHodduck;

    @FXML
    private ImageView menuHangwa;

    @FXML
    private ImageView menuGangjung;

    @FXML
    private ImageView menuYakgwa;

    @FXML
    private ImageView menuKimchiSet;

    @FXML
    private ImageView menuBoodaeSet;

    @FXML
    private ImageView menuGochujangSet;

    @FXML
    private ImageView menuDongtaeSet;

    @FXML
    private ImageView menuTunaSet;

    @FXML
    private ImageView menuDoinjangSet;

    @FXML
    private Button adminButton;

    @FXML
    void onAdminButtonAction(ActionEvent event) {
    	
    }

    @FXML
    void onClearButton(ActionEvent event) {

    }

    @FXML
    void onMouseClicked(MouseEvent event) {
    	
    	if(event.getSource().equals(menuKimchi)) {
    		
    	}else if(event.getSource().equals(menuDoinjang)) {
    		
    	}else if(event.getSource().equals(menuDongtae)) {
    		
    	}else if(event.getSource().equals(menuGochujang)) {
    		
    	}else if(event.getSource().equals(menuTuna)) {
    		
    	}else if(event.getSource().equals(menuBoodae)) {
    		
    	}else if(event.getSource().equals(menuBoritea)) {
    		
    	}else if(event.getSource().equals(menuChilgtea)) {
    		
    	}else if(event.getSource().equals(menuGreentea)) {
    		
    	}else if(event.getSource().equals(menuArisoo)) {
    		
    	}else if(event.getSource().equals(menuSoongnyoong)) {
    		
    	}else if(event.getSource().equals(menuDoongullae)) {
    		
    	}else if(event.getSource().equals(menuDasick)) {
    		
    	}else if(event.getSource().equals(menuGGooltarae)) {
    		
    	}else if(event.getSource().equals(menuHodduck)) {
    		
    	}else if(event.getSource().equals(menuHangwa)) {
    		
    	}else if(event.getSource().equals(menuGangjung)) {
    		
    	}else if(event.getSource().equals(menuKimchiSet)) {
    		
    	}else if(event.getSource().equals(menuBoodaeSet)) {
    		
    	}else if(event.getSource().equals(menuGochujangSet)) {
    		
    	}else if(event.getSource().equals(menuDongtaeSet)) {
    		
    	}else if(event.getSource().equals(menuTunaSet)) {
    		
    	}else if(event.getSource().equals(menuDoinjangSet)) {
    		
    	} 	
    }

    @FXML
    void onPaymentButton(ActionEvent event) {

    }
    
    public static void KioskDB() {
    		String usr = "team";
    		String password = "1234";
    		String url = "jdbc:oracle:thin:@localhost:1521:xe";

    		Connection conn = null;

    		try
    		{
    			Class.forName("com.mysql.jdbc.Driver");
    			conn = DriverManager.getConnection(url, usr, password);
    		}catch(
    		ClassNotFoundException e)
    		{
    			System.out.println("드라이버 로딩 실패");
    		}catch(
    		SQLException e)
    		{
    			e.printStackTrace();
    		}

    	}
    	
    }




