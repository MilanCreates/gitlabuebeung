package dataacess;

import domain.Course;
import domain.CourseType;

import java.sql.Date;
import java.util.List;

public interface MyCourserepository extends BaseRepository<Course, Long>{

    List<Course> findAllCoursesByName(String name);
    List<Course> findAllCoursesByDescription(String description);
    List<Course> findAllCoursesByNameorDescription(String searchText);
    List<Course> findAllCoursesByStartDate(Date startDate);
    List<Course> findAllCoursesByCourseType(CourseType courseType);
    List<Course> findAllRunningCourses();

}
