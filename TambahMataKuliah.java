package controller;

import Model.Mahasiswa;
import Model.MataKuliah;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "TambahMataKuliah", urlPatterns = {"/TambahMataKuliah"})
public class TambahMataKuliah extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DatabaseManager db = new DatabaseManager();
        HttpSession session = request.getSession();
        Mahasiswa mahasiswa = (Mahasiswa) session.getAttribute("mahasiswa");
        MataKuliah m[] = db.showMataKuliah(mahasiswa.getNim());
        boolean sudahAda = false;
        String status;
        String kode = request.getParameter("kode");
        for (int i = 0; i < m.length; i++) {
            if (m[i].getKode_matkul().compareToIgnoreCase(kode)==0) {
                sudahAda = true;                
            }
        }
        if (sudahAda) {
            status = "sudah diambil";
        }
        else{
            status = "ditambahkan";
            db.tambahMakul(mahasiswa.getNim(), kode);
            
        }
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet tambah</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet tambah at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
