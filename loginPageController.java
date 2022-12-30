package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginPageController {
    @FXML
    TextField email;
    @FXML
    PasswordField password;//gives hidden password

   public void login(MouseEvent e) throws SQLException, IOException {
       String query = String.format("select * from user where emailId = '%s' and pass = '%s'",email.getText(),password.getText());
       //string format is based on the userinput
       ResultSet result = ecommerce.connection.executingQuery(query);

       if(result.next()){
           ecommerce.emailId = result.getString("emailId");
           String userType = result.getString("userType");
           if(userType.equals("seller")){
               AnchorPane SellerPage = FXMLLoader.load(getClass().getResource("sellerPage.fxml"));
               ecommerce.root.getChildren().add(SellerPage);
           }
           else{
               System.out.println("buyer is in");
               productPage productpage = new productPage();

               header Header = new header();
               AnchorPane productPane = new AnchorPane();
               productPane.getChildren().add(productpage.products());
               productPane.setLayoutX(150);
               productPane.setLayoutY(70);
               productPane.setMinSize(200,150);
               ecommerce.root.getChildren().clear(); //clear the background
               ecommerce.root.getChildren().addAll(Header.root,productPane);
           }
       }
       else{
           Dialog<String> dialog = new Dialog<>();//dialog similar to alert box
           dialog.setTitle("Login");
           ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
           dialog.getDialogPane().getButtonTypes().add(type);
           dialog.setContentText("Login Failed, Please check username/password and try again");
           dialog.showAndWait();
       }
   }

    @FXML
    Button Home;

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
}
