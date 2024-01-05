package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//Controller
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // /WEB-INF 안에 있는 폴더는 서버 밖에서 호출이 되지 않는다.
    String viewPath = "/WEB-INF/views/new-form.jsp";
    // controller -> view
    RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
    // 다른 서블릿이나 JSP 호출 명령어. 서버 내부에서 호출된다.
    dispatcher.forward(request, response);

  }
}
