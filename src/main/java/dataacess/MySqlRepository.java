package dataacess;

import domain.Course;
import domain.CourseType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlRepository implements MyCourserepository {


    private Connection con;

    public MySqlRepository() throws SQLException, ClassNotFoundException {

            this.con = MyDatabaseConnection.getConnection("jdbc:mysql://127.0.0.1:3306/kurssystem","root","");

    }

    @Override
    public List<Course> findAllCoursesByName(String name) {
        return List.of();
    }

    @Override
    public List<Course> findAllCoursesByDescription(String description) {
        return List.of();
    }

    @Override
    public List<Course> findAllCoursesByNameorDescription(String searchText) {
        return List.of();
    }

    @Override
    public List<Course> findAllCoursesByStartDate(Date startDate) {
        return List.of();
    }

    @Override
    public List<Course> findAllCoursesByCourseType(CourseType courseType) {
        return List.of();
    }

    @Override
    public List<Course> findAllRunningCourses() {
        return List.of();
    }

    @Override
    public Optional<Course> insert(Course entitiy) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Course> getALl() {
        String sql = "Select * from 'courses'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Course> courseList = new ArrayList<>();
            while (resultSet.next()){
                courseList.add(new Course(

                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("hours"),
                        resultSet.getDate("beginDate"),
                        resultSet.getDate("endDate"),
                        CourseType.valueOf(resultSet.getString("courseType"))
                        )
                );
                return courseList;
            }
        } catch (SQLException e) {
            throw new MyDatabaseException("Database Error");
        }
        return List.of();
    }

    @Override
    public Optional<Course> update(Course entity) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
