package com.example.ecommerce;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class orders {
    void placeOrder(String productID) throws SQLException {
        ResultSet result = ecommerce.connection.executingQuery("select max(orderId) as orderId from orders");
        int orderId=1;
        if(result.next()){
            orderId = result.getInt("orderId")+1;
        }
        Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());
        String query = String.format("insert into orders values (%s,%s,'%s','%s')",orderId,productID,ecommerce.emailId,ts);

        int response = ecommerce.connection.executingUpdate(query);
        if(response>0){
            Dialog<String> dialog = new Dialog<>();//dialog similar to alert box
            dialog.setTitle("order");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Your order is placed");
            dialog.showAndWait();
        }
        else{
            System.out.println(("The order is not placed"));
        }
    }
}
