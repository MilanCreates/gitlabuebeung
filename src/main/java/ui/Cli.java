package ui;

import dataacess.MyCourserepository;
import dataacess.MyDatabaseException;
import domain.Course;

import java.util.List;
import java.util.Scanner;

public class Cli
{
    Scanner scan;

    MyCourserepository repo;

    public Cli(MyCourserepository repo){
        this.repo = repo;
        this.scan = new Scanner(System.in);

    }


    public void start(){
        String input = "-";
        while(!input.equals("x")){
            showMenue();
            input = scan.nextLine();

            switch (input){
                case "1":
                    System.out.println("Kurseingabe");
                    break;
                case "2":

                    showAllCourses();
                    break;
                case "x":
                    System.out.println("Beendet");
                    break;
                default:
                    inputError();
                    break;
            }
        }
        scan.close();
    }

    private void showAllCourses() {
        List<Course> list = null;

        try {
            list = repo.getALl();

            if (list.size() > 0 ){

                for (Course course : list){

                    System.out.println(course.toString());
                }
            }else {
                System.out.println("Kursliste leer.");
            }
        }catch (MyDatabaseException databaseException){
            System.out.println("Datenbankfehler bei Anzeige aller Kurse" + databaseException.getMessage());
        }catch (Exception e){
            System.out.println("Fehler" + e.getMessage());
        }

    }

    private void inputError(){
        System.out.println("Bitte nur die Zahlen der Menüauswahl eingeben!");
    }

    private void showMenue() {
        System.out.println("------------KURSMANAGEMENT------------");
        System.out.println("(1) Kurs eingeben \t (2) Alle Kurse anzeigen \t");
        System.out.println("");
    }

}
