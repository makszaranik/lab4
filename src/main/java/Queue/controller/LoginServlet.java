package Queue.controller;

import Queue.dao.UserDAO;
import Queue.dao.UserDataBase;
import Queue.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    UserDAO userDao = UserDataBase.getInstance();
    if (userDao.isUserExist(username, password)) {
      request.getSession().setAttribute("user", new User(username, password));
      response.sendRedirect("/MainPage.jsp");
    } else {
      request.setAttribute("errorMessage", "Invalid username or password");
      request.getRequestDispatcher("/LoginPage.jsp").forward(request, response);
    }
  }



}