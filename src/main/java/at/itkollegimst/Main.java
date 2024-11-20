package at.itkollegimst;

import dataacess.MyCourserepository;
import dataacess.MyDatabaseConnection;
import dataacess.MySqlRepository;
import ui.Cli;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {



        try {
            Cli cli = new Cli(new MySqlRepository() {
            });
            cli.start();
        } catch (SQLException e) {
            System.out.println("Datenbankfehler"+ e.getMessage() + "SQL STAE: " + e.getSQLState());
        } catch (ClassNotFoundException e) {
            System.out.println("Datenbankfehler" + e.getMessage());
        }






    }
}