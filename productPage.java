package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class productPage { //adding the fxml features into product page manually --> to avoid complexity
    ListView<HBox> products;

    ListView productsBySearch(String search) throws SQLException {
        products = new ListView<>();
        ObservableList<HBox> productList = FXCollections.observableArrayList(); //this list updates the values as and when updated in its source
        ResultSet result = ecommerce.connection.executingQuery("select * from product;"); //sql query output

        while (result.next()) { //iterate through every product of databse table product
            if (result.getString("ProductName").toLowerCase().contains(search.toLowerCase())) {
                Label name = new Label();
                Label productId = new Label();
                Label price = new Label();
                Button buy = new Button();

                name.setMinWidth(70);//specify physical entities
                productId.setMinWidth(70);
                price.setMinWidth(60);
                buy.setText("Buy");
                HBox productDetails = new HBox(); //need horizantal box to display the products and its details

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) { //mouse click event mirroring to fxml file
                        if (ecommerce.emailId.equals("")) {
                            System.out.println("please login first");
                        } else
                            System.out.println("you are logged in with" + " " + ecommerce.emailId);
                        orders order = new orders();
                        try {
                            order.placeOrder(productId.getText());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                name.setText(result.getString("productName"));
                price.setText(result.getString("price"));
                productId.setText(result.getString("productId"));

                productDetails.getChildren().addAll(productId, name, price, buy); //serialize the details needed
                productList.add(productDetails); //add into observabl elist
            }
        }
        products.setItems(productList);
        return products;
    }
    ListView products() throws SQLException {
        products = new ListView<>();
        ObservableList<HBox> productList = FXCollections.observableArrayList(); //this list updates the values as and when updated in its source
        ResultSet result = ecommerce.connection.executingQuery("select * from product;"); //sql query output

        while (result.next()) { //iterate through every product of databse table product
            Label name = new Label();
            Label productId = new Label();
            Label price = new Label();
            Button buy = new Button();

            name.setMinWidth(70);//specify physical entities
            productId.setMinWidth(70);
            price.setMinWidth(60);
            buy.setText("Buy");
            HBox productDetails = new HBox(); //need horizantal box to display the products and its details

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) { //mouse click event mirroring to fxml file
                    if(ecommerce.emailId.equals("")){
                        System.out.println("please login first");
                    }
                    else
                    System.out.println("you are logged in with"+" "+ecommerce.emailId);
                    orders order=new orders();
                    try {
                        order.placeOrder(productId.getText());
                    } catch (SQLException e) {
                       e.printStackTrace();
                    }
                }
            });
            name.setText(result.getString("productName"));
            price.setText(result.getString("price"));
            productId.setText(result.getString("productId"));

            productDetails.getChildren().addAll(productId, name, price, buy); //serialize the details needed
            productList.add(productDetails); //add into observabl elist
        }
        products.setItems(productList);
        return products;
    }
}
