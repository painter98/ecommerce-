package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sellerPageController {
    @FXML
    TextField name,price,sellerid;

    @FXML
    Button Home;

    @FXML
    Label email;

    @FXML
    public void initialize(){ //fxml objects can't be accessed by the constructor and initialize is inbuilt function

        email.setText(ecommerce.emailId);// display emailid
        email.setFont(Font.font(15)); //set font
        email.setTextFill(Color.LIGHTBLUE); //set color
    }

    @FXML
    public void Home(MouseEvent e) throws SQLException, IOException {
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
    public void addProduct(MouseEvent e) throws SQLException {
        int productId=1;
        ResultSet response2 = ecommerce.connection.executingQuery("select max(productId) as productID from product;");
        if(response2.next()){
            productId = response2.getInt("productID")+1;
        }

        String query = String.format("insert into product values (%s, '%s', %s, '%s');",productId,name.getText(),price.getText(),sellerid.getText());
        int response = ecommerce.connection.executingUpdate(query);
        if(response>0){
            System.out.println("new product is added");
        }
    }
}

