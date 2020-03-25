package servlet;

import jdbc.StudentHomeworkJdbc;
import model.Homework;
import model.Student;
import model.StudentHomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/ResubmitHomeworkServlet")
public class ResubmitHomeworkServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    StudentHomework sh = new StudentHomework();

    sh.setStudentId(Long.parseLong(req.getParameter("studentID")));
    sh.setHomeworkId(Long.parseLong(req.getParameter("homeworkID")));
    sh.setHomeworkTitle(req.getParameter("title"));
    sh.setHomeworkContent(req.getParameter("content"));

    try {
      StudentHomeworkJdbc.resubmitHomework(sh);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    PrintWriter out = resp.getWriter();
    out.println("Modify successfully!");

  }
}
