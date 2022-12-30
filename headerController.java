package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.sql.SQLException;

public class headerController {
    @FXML
    Label email;

    @FXML
    TextField searchText;

    @FXML
    public void initialize(){ //fxml objects can't be accessed by the constructor and initialize is inbuilt function
        if(!ecommerce.emailId.equals(""))
            loginbutton.setOpacity(0);
        if(ecommerce.emailId.equals(""))
            logoutbutton.setOpacity(0);
        if(!ecommerce.emailId.equals(""))
            signUp.setOpacity(0);

            email.setText(ecommerce.emailId);// display emailid
            email.setFont(Font.font(15)); //set font
            email.setTextFill(Color.LIGHTBLUE); //set color
    }
    @FXML
    public void search(MouseEvent e) throws SQLException, IOException {
        productPage productpage = new productPage();

        header Header = new header();
        AnchorPane productPane = new AnchorPane();
        productPane.getChildren().add(productpage.productsBySearch(searchText.getText()));
        productPane.setLayoutX(150);
        productPane.setLayoutY(70);
        productPane.setMinSize(200,150);
        ecommerce.root.getChildren().clear(); //clear the background
        ecommerce.root.getChildren().addAll(Header.root,productPane);
    }
    @FXML
    Button logoutbutton;

    @FXML
    public void logout(MouseEvent e) throws SQLException, IOException {
        ecommerce.emailId = "";
        header Header = new header(); //header is required in most of the panes so separate class is created and called anytime needed
        AnchorPane productPane = new AnchorPane();
        productPage productpage = new productPage();

        productPane.getChildren().add(productpage.products());
        productPane.setLayoutX(150);
        productPane.setLayoutY(70);
        productPane.setMinSize(200,150);
        ecommerce.root.getChildren().clear();
        ecommerce.root.getChildren().addAll(Header.root,productPane);
    }
    @FXML
    Button signUp;

    @FXML
    public void signup(MouseEvent e) throws IOException{

        AnchorPane signuppage= FXMLLoader.load(getClass().getResource("signupPage.fxml"));
        ecommerce.root.getChildren().add(signuppage);
    }
    @FXML
    Button loginbutton;

    @FXML
    public void login(MouseEvent e) throws IOException{
        AnchorPane loginpage= FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        ecommerce.root.getChildren().add(loginpage);
    }
}
