package servlet;

import jdbc.StudentHomeworkJdbc;
import model.Homework;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/ModifyHomeworkServlet")
public class ModifyHomeworkServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Homework h = new Homework();
    h.setContent(req.getParameter("content"));

    String title = req.getParameter("title");

    try {
      StudentHomeworkJdbc.modifyHomework(h,title);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    PrintWriter out = resp.getWriter();
    out.println("Modify successfully!");

  }
}
