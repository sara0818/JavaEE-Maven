package servlet;

import jdbc.StudentHomeworkJdbc;
import model.Homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/AddHomeworkServlet")
public class AddHomeworkServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Homework homework = new Homework();
    homework.setTitle(req.getParameter("title"));
    homework.setContent(req.getParameter("content"));

    try {
      StudentHomeworkJdbc.addHomework(homework);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    PrintWriter out = resp.getWriter();
    out.println("Submit successfully!");
  }
}
