package jdbc;

import model.Homework;
import model.Student;
import model.StudentHomework;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentHomeworkJdbc {

  public static void main(String[] args) throws SQLException {
    List<StudentHomework> list = selectAll();

    for (StudentHomework sh : list){
      System.out.println(sh.getHomeworkContent());
    }
  }

  //发布作业
  public static void addHomework(Homework sh) throws SQLException {

    String sqlString = "insert into s_homework(title,content)values(?,?)";

    PreparedStatement pstmt;
    Connection conn=DatabasePool.getHikariDataSource().getConnection();

    Statement statement = conn.createStatement();
    pstmt = conn.prepareStatement(sqlString);
    pstmt.setString(1,sh.getTitle());
    pstmt.setString(2,sh.getContent());
    pstmt.execute();

  }

  //添加学生
  public static void addStudent(Student s) throws SQLException {

    String sqlString = "insert into s_student(name)values(?)";

    PreparedStatement pstmt;

    Connection conn=DatabasePool.getHikariDataSource().getConnection();

    Statement statement = conn.createStatement();

    pstmt = conn.prepareStatement(sqlString);
    pstmt.setString(1,s.getName());
    pstmt.execute();

  }
  //提交作业
  public static void submitHomework(StudentHomework sh) throws SQLException {

    String sqlString = "insert into s_student_homework(student_id,homework_id,homework_title,homework_content)values(?,?,?,?)";

    PreparedStatement pstmt;

    Connection conn=DatabasePool.getHikariDataSource().getConnection();

    Statement statement = conn.createStatement();

    pstmt = conn.prepareStatement(sqlString);
    pstmt.setLong(1,sh.getStudentId());
    pstmt.setLong(2,sh.getHomeworkId());
    pstmt.setString(3,sh.getHomeworkTitle());
    pstmt.setString(4,sh.getHomeworkContent());

    pstmt.execute();


  }

  //重新提交作业
  public static void resubmitHomework(StudentHomework sh) throws SQLException {

    String sqlString = "update s_student_homework set homework_content=?,update_time=? where student_id=? and homework_id=? and homework_title=?";

    PreparedStatement pstmt;

    Connection conn=DatabasePool.getHikariDataSource().getConnection();

    Statement statement = conn.createStatement();


    Timestamp time = new Timestamp(System.currentTimeMillis());
    pstmt = conn.prepareStatement(sqlString);
    pstmt.setString(1,sh.getHomeworkContent());
    pstmt.setTimestamp(2,time);
    pstmt.setLong(3,sh.getStudentId());
    pstmt.setLong(4,sh.getHomeworkId());
    pstmt.setString(5,sh.getHomeworkTitle());
    pstmt.execute();

  }

  //教师修改作业
  public static void modifyHomework(Homework h,String title) throws SQLException {

    String sqlString = "update s_homework set content=?,update_time=? where title=?";

    PreparedStatement pstmt;

    Connection conn=DatabasePool.getHikariDataSource().getConnection();

    Statement statement = conn.createStatement();

    Timestamp time = new Timestamp(System.currentTimeMillis());
    pstmt = conn.prepareStatement(sqlString);
    pstmt.setString(1,h.getContent());
    pstmt.setTimestamp(2,time);
    pstmt.setString(3,title);
    pstmt.execute();

  }

  //修改学生信息
  public static void modifyStudent(Student s,String orginalName) throws SQLException {

    String sqlString = "update s_student set name=?,update_time=? where name=?";

    PreparedStatement pstmt;

    Connection conn=DatabasePool.getHikariDataSource().getConnection();

    Statement statement = conn.createStatement();

    Timestamp time = new Timestamp(System.currentTimeMillis());
    pstmt = conn.prepareStatement(sqlString);
    pstmt.setString(1,s.getName());
    pstmt.setTimestamp(2,time);
    pstmt.setString(3,orginalName);
    pstmt.execute();


  }



  //查询已提交作业
  public static List<StudentHomework> selectAll() throws SQLException {

    String sqlString = "SELECT * FROM s_student_homework";

    List<StudentHomework> list = new ArrayList<>();

//    ConnectionMysql c_mysql = new ConnectionMysql();

    Connection conn=DatabasePool.getHikariDataSource().getConnection();

    Statement statement = conn.createStatement();

    ResultSet resultSet = statement.executeQuery(sqlString);

    while (resultSet.next()){
      StudentHomework sh = new StudentHomework();
      sh.setId(resultSet.getLong("id"));
      sh.setStudentId(resultSet.getLong("student_id"));
      sh.setHomeworkId(resultSet.getLong("homework_id"));
      sh.setHomeworkTitle(resultSet.getString("homework_title"));
      sh.setHomeworkContent(resultSet.getString("homework_content"));
      sh.setCreateTime(resultSet.getTimestamp("create_time"));
      list.add(sh);
    }


    return list;
  }

}
