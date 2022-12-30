package com.example.ecommerce;

import java.sql.*;

public class databaseConnection {

    Connection con=null;
    String SqlUrl = "jdbc:mysql://localhost:3306/ecommerce?useSSL=false";
    String userName = "root"; //password and username can only used locally
    String password = "Pallavi1998@";
    databaseConnection() throws SQLException { //establishing the connection to sql
        con = DriverManager.getConnection(SqlUrl,userName,password);
        if(con!=null)
            System.out.println("connection established");
    }
    public ResultSet executingQuery(String query) throws SQLException { //select * from temporary --> sql language
//instead sql exception we can use try catch block to stop program from ending

        Statement statement = con.createStatement(); //Statement--> inbuilt function to convert query string to sql command
        ResultSet result = statement.executeQuery(query); //Resultset is to store the set of data from sql it stores the first element of the column
        //executequery --> inbuilt function to execute the query command, not to insert,update,delete

        return result;
    }
    public int executingUpdate(String query){ //to update the data of sql database
        int row=0; //the check to know row has been added or not if row=0 then not added else row=1 row is added
        try{
            Statement statement = con.createStatement();
            row = statement.executeUpdate(query);
            return row;
        }
        catch(Exception e){ //we do not want program to end when input is wrong so catch exception
            e.printStackTrace();
        }
        return row;
    }
}

