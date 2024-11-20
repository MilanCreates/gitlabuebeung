package dataacess;

import domain.Course;
import domain.CourseType;
import util.Assert;

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

        Assert.notNull(entitiy);

        try {
            String sql = "INSERT INTO `courses` ( `name`, `enddate`, `hours`, `begindate`, `description`,  `coursetype`) VALUES (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,entitiy.getName());
            preparedStatement.setString(2,entitiy.getDescription());
            preparedStatement.setInt(3,entitiy.getHours());
            preparedStatement.setDate(4,entitiy.getBeginDate());
            preparedStatement.setDate(5,entitiy.getEndDate());
            preparedStatement.setString(6,entitiy.getCourseType().toString());

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
                return Optional.empty();
            }

            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()){
                return this.getById(generatedKey.getLong(1));
            }

        } catch (MyDatabaseException e) {
            throw new MyDatabaseException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    private int countCoursesInDbWithId(Long id){
        String countsql = "select count(*) from 'courses' where 'id' = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(countsql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int coursecount = resultSet.getInt(1);
            return coursecount;
        } catch (SQLException e) {
            throw new MyDatabaseException(e.getMessage());
        }

    }

    @Override
    public Optional<Course> getById(Long id) {
        Assert.notNull(id);
        if (countCoursesInDbWithId(id) ==0){
            return Optional.empty();
        }else {
            String sql = "SELECT * FROM `courses` WHERE `id` = ?";

            try {


                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();

                Course course = new Course(

                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("hours"),
                        resultSet.getDate("beginDate"),
                        resultSet.getDate("endDate"),
                        CourseType.valueOf(resultSet.getString("courseType"))
                );
                return Optional.of(course);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public List<Course> getALl() {
        String sql = "SELECT * FROM kurssystem.courses";
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
                        resultSet.getDate("begindate"),
                        resultSet.getDate("enddate"),
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

        Assert.notNull(entity);

        String sql = "UPDATE `courses` SET `name` = ?, `description` = ?, `begindate` = ?, `enddate` = ? `courstype` = ?  WHERE `courses`.`id` = ?";

        if (countCoursesInDbWithId(entity.getId()) == 0)
        {
            return Optional.empty();


        }else {
            try {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setString(2,entity.getDescription());
                preparedStatement.setInt(3,entity.getHours());
                preparedStatement.setDate(4,entity.getBeginDate());
                preparedStatement.setDate(5,entity.getEndDate());
                preparedStatement.setString(6,entity.getCourseType().toString());
                preparedStatement.setLong(7,entity.getId());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows==0){
                    return Optional.empty();
                }else {
                    return this.getById(entity.getId());
                }


            }catch (MyDatabaseException e){
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

        Assert.notNull(id);

        String sql = "Delete from `courses` Where `id` = ?";
        try {
            if (countCoursesInDbWithId(id) == 0){
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setLong(1,id);
                preparedStatement.executeUpdate();

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
