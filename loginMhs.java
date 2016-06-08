package controller;

import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class loginMhs extends HttpServlet {
DatabaseManager m = new DatabaseManager();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher;       
        Mahasiswa mahasiswa = null;
        Admin admin = null;
        String nim = request.getParameter("nim");
        String pass = request.getParameter("pass");
        HttpSession session = request.getSession();
//        try{
//            Integer.getInteger(nim.substring(0, 3));
//        }
//        catch(Exception ec){
            try{
            mahasiswa = m.login(nim, pass);
            }
            catch(Exception e){
                 String ex = e.getMessage();
                 tampil(response, ex);
            }
//        }
        //Login Admin
//        try{
//            admin = m.loginAdmin(nim, pass);
//            }
//            catch(Exception e){
//                 String ex = e.getMessage();
//                 tampil(response, ex);
//            }
          
        session.setAttribute("mahasiswa", mahasiswa);
        dispatcher = request.getRequestDispatcher("mahasiswa.jsp");
        dispatcher.forward(request, response);   
        
    }
    
    void tampil(HttpServletResponse response, String pesan) throws IOException{
        PrintWriter out = response.getWriter();
        try {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>ERROR</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>"+pesan+"</h1>");
                    out.println("<a href=\"index.jsp\">Login</a>");
                    out.println("</body>");
                    out.println("</html>");
                    }      
        finally {
            out.close();
        }
    }
}
