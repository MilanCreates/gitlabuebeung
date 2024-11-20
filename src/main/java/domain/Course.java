package domain;

import java.sql.Date;

public class Course extends BaseEntity{

    private String name;
    private String description;
    private Date beginDate;
    private int hours;
    private Date endDate;
    private CourseType courseType;
    public int getHours() {
        return hours;
    }

    public Course(Long id, String name, String description, int hours, Date beginDate,Date endDate,  CourseType courseType) throws InvalidValueException {
        super(id);
        this.setName(name);
        this.setBeginDate(beginDate);
        this.setHours(hours);
        this.setEndDate(endDate);
        this.setDescription(description);
        this.setCourseType(courseType);
    }

    public Course(String name, Date endDate, int hours, Date beginDate, String description, CourseType courseType) throws InvalidValueException{
        super(null);
        this.setName(name);
        this.setBeginDate(beginDate);
        this.setHours(hours);
        this.setEndDate(endDate);
        this.setDescription(description);
        this.setCourseType(courseType);
    }

    public void setHours(int hours) {
        if (hours > 0 && hours < 10){
            this.hours = hours;
        }else{
            throw new InvalidValueException("Anzahl der Kursstunden zwischen 1 und 10");
        }

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name!=null && name.length() >1){this.name = name;}
        else {throw new InvalidValueException("Kursname muss mindesens 2 Zeichen lang sein.");}

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && description.length() > 1 ){
            this.description = description;
        }else {
            throw new InvalidValueException("Kursbeschreibung muss mindesens 10 Zeichen lang sein.");
        }


    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) throws InvalidValueException{

        if(courseType != null){

            this.courseType = courseType;
        }else {
            throw new InvalidValueException("Kurs darf nicht null sein.");
        }
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        if (beginDate!=null){
            if (this.endDate != null){
                if (beginDate.before(this.endDate)){

                    this.beginDate = beginDate;
                }else {
                    throw new InvalidValueException("Kursbeginn muss Vor Kursende sein!");
                }
            }else
            {
                throw new InvalidValueException("Startdatum darf nicht null / sein!");
            }
        }

    }

    @Override
    public String toString() {
        return "Course{" +
                "id= " + this.getId() + '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", beginDate=" + beginDate +
                ", hours=" + hours +
                ", endDate=" + endDate +
                ", courseType=" + courseType +
                '}';
    }
}
