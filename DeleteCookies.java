import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteCookies", urlPatterns = {"/DeleteCookies"})
public class DeleteCookies extends HttpServlet {

    public void doGet(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException {
    Cookie cookie;
    cookie = new Cookie("Persistent-Cookie-1","");
    cookie.setMaxAge(-1);
    response.addCookie(cookie);
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "Delete Cookies";
    out.println(ServletUtilities.headWithTitle(title) +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
                "Delete cookies\n" +
                "</BODY></HTML>");
    }
}