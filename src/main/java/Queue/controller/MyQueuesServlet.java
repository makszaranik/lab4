package Queue.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Queue.model.QueueManager;
import Queue.model.User;



@WebServlet("/MyQueues")
public class MyQueuesServlet extends HttpServlet {
  private QueueManager queueManager = null;


  public void init(){
    queueManager = QueueManager.getInstance();
  }


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    String username = user.getUsername();

    request.setAttribute("queues", queueManager.getQueuesByUsername(username));

    request.getRequestDispatcher("MyQueues.jsp").forward(request, response);
  }
}