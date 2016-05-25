import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SetCookies", urlPatterns = {"/SetCookies"})
public class SetCookies extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException {
        for(int i=0; i<3; i++) {
        // Default maxAge is -1, indicating cookie
        // applies only to current browsing session.
        Cookie cookie = new Cookie("Session-Cookie " + i,"Cookie-Value-S" + i);
        response.addCookie(cookie);
        cookie = new Cookie("Persistent-Cookie " + i,"Cookie-Value-P" + i);
                cookie.setMaxAge(3600);
                response.addCookie(cookie);
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Setting Cookies";
            out.println (ServletUtilities.headWithTitle(title) +
                        "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                        "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
                        "There are six cookies associated with this page.\n" +
                        "To see them, visit the\n" +
                        "<A HREF=\"/servlet/coreservlets.ShowCookies\">\n" +
                        "<CODE>ShowCookies</CODE> servlet</A>.\n" +
                        "<P>\n" +
                        "Three of the cookies are associated only with the\n" +
                        "current session, while three are persistent.\n" +
                        "Quit the browser, restart, and return to the\n" +
                        "<CODE>ShowCookies</CODE> servlet to verify that\n" +
                        "the three long-lived ones persist across sessions.\n" +
                        "</BODY></HTML>");
    }
}