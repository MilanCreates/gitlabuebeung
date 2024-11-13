package at.itkollegimst;

import dataacess.MyDatabaseConnection;
import ui.Cli;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            Connection connection = MyDatabaseConnection.getConnection("jdbc:mysql://127.0.0.1:3306/kurssystem","root","");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Cli cli = new Cli();

        cli.start();


    }
}