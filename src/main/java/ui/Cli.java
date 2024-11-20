package ui;

import dataacess.MyCourserepository;
import dataacess.MyDatabaseException;
import domain.Course;
import domain.CourseType;
import domain.InvalidValueException;

import java.sql.Date;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
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
                    addCourse();
                    break;
                case "2":

                    showAllCourses();
                    break;
                case "3":

                    showCourseDetails();
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

    private void addCourse() {
        String name, description;
        int hours;
        Date from, to;
        CourseType courseType;

        try {

            System.out.println("Bitte alles Kursdaten angeben: ");
            System.out.println("Name: ");
            name = scan.nextLine();
            if (name.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein!");
            System.out.println("Beschreibung");
            description = scan.nextLine();
            if (description.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein!");

            System.out.println("Stundenzahl");
            hours = Integer.parseInt(scan.nextLine());
            System.out.println("StartDatum: ");
            from = Date.valueOf(scan.nextLine());

            System.out.println("Enddatum: ");
            to = Date.valueOf(scan.nextLine());


            System.out.println("Kurstyp (ZA/BF/FF/OE)");
            courseType = CourseType.valueOf(scan.nextLine());

            Optional<Course> optionalCourse = repo.insert(
                    new Course(name, to,hours,from,description,courseType)
            );


            if (optionalCourse.isPresent()){


                System.out.println("Kurs anlegen: " + optionalCourse.get());

            }else {
                System.out.println("Kurs konnte nicht angelegt werden.");
            }



        }catch (IllegalArgumentException a){
            System.out.println("Eingabefehler " + a.getMessage());
        }catch (InvalidValueException a){
            System.out.println("Kursdaten nicht korrekt angegeben" +a.getMessage());
        }catch (MyDatabaseException m){
            System.out.println("Fehler beim Einfügen" + m.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void showCourseDetails() {
        System.out.println("Für welchen Kurs möchhtten Sie Kursdetails anzeigen ?");
        Long courseId = Long.parseLong(scan.nextLine());

        try {
            Optional<Course> courseOptional = repo.getById(courseId);
            if (courseOptional.isPresent()){
                System.out.println(courseOptional.get());
            }else {
                System.out.println("Kurs mit ID " + courseOptional.get() + "nicht gefunden.");
            }

        }catch (MyDatabaseException e){
            System.out.println("Datenbankfehler bei Kursdetailanzeige" + e.getMessage());
        }catch (Exception e){
            System.out.println("unbekannter Fehler bei Kursdetailanzeige" + e.getMessage());
        }
    }

    private void showAllCourses() {


        try {
            List<Course> list = repo.getALl();

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
        System.out.println("(1) Kurs eingeben \t (2) Alle Kurse anzeigen \t (3) Alle Kursdetails anzeigen \t");
        System.out.println("");
    }

}
