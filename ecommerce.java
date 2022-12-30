package com.example.ecommerce;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ecommerce extends Application {

    public static databaseConnection connection;
    public static Group root;
    public static String emailId;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        emailId = "";
        connection = new databaseConnection();//connnecting to database class and objrct creation
        root=new Group();
        header Header = new header(); //header is required in most of the panes so separate class is created and called anytime needed
        AnchorPane productPane = new AnchorPane();
        productPage productpage = new productPage();

        productPane.getChildren().add(productpage.products());
        productPane.setLayoutX(150);
        productPane.setLayoutY(70);
        productPane.setMinSize(200,150);
        root.getChildren().addAll(Header.root,productPane);

        Scene scene = new Scene(root, 500, 500); //layout size
        stage.setTitle("Ecommerce"); //title
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e ->{ //close the database file once the program is clsed
            try {
                connection.con.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}