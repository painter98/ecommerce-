package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.*;
import java.sql.SQLException;

public class signUpController {
    @FXML
    TextField emailId, username, password, usertype;

    @FXML
    Button signUp;
    public void signUp(MouseEvent e) throws SQLException {

        String query = String.format("insert into user values('%s','%s',%s,'%s')", emailId.getText(), username.getText(), password.getText(), usertype.getText());
        int response = ecommerce.connection.executingUpdate(query);
        if(response>0){
            Dialog<String> dialog = new Dialog<>();//dialog similar to alert box
            dialog.setTitle("order");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("You are signed in \n Please login");
            dialog.showAndWait();
        }
        else{
            System.out.println(("sign up is not successful"));
        }
    }
    @FXML
    public void home(MouseEvent e) throws SQLException, IOException {
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
    public void signUpController() throws SQLException {
    }
}
