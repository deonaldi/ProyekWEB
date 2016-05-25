import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException {
// Ambil beberapa request header
        String host = request.getHeader("host");
        String userAgent = request.getHeader("user-agent");
            response.setContentType("text/html; charset=UTF-8");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            if( !username.equals("deon") || !password.equals("12345")){
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("Sorry, you type wrong username/password");
                out.println("</body>");
                out.println("</html>");
                out.close();
            }
            else{
            response.setHeader("content-language", "id");
            long now = System.currentTimeMillis();
            response.setDateHeader("date", now);
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Pemilihan mata kuliah</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>KRS ONLINE SANATA DHARMA</h2>");
            out.println("<p>Selamat datang:<br/>" +
                        " -Host Anda : " + host + "<br/>" +
                        " -User-agent: " + userAgent + "</p>");
                        out.println(
                        "<form action='OrderServlet' method='POST' target='_blank'>" +
                        "<fieldset>" +
                        "<legend>SANATA DHARMA</legend>" +
                        "<table cellpadding='4' cellspacing='2'>" +
                        "<tr>" +
                        "<td>PILIH PRODI:</td>" +
                        "<td><select name='pOccasion'>" +
                        "<option value='TEKNIK INFORMATIKA' selected>TEKNIK INFORMATIKA</option>" +
                        "<option value='TEKNIK MESIN '>TEKNIK MESIN </option>" +
                        "<option value='BIOLOGI'>BIOLOGI</option>" +
                        "<option value='FARMASI'>FARMASI</option>" +
                        "<option value='MATEMATIKA'>MATEMATIKA</option>" +
                        "</select></td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td>:</td>" +
                        "<td>" +
                        "<input type='checkbox' name='pkalkulus' value='kalkulus' " +
                        "checked />kalkulus&nbsp;" +
                                 "<input type='checkbox' name='pkeamanan' value='keamanan basis data ' " +
                        "checked />keamanan basis data&nbsp;" +
                                 "<input type='checkbox' name='pjarkom' value='jaringan komputer' " +
                        "checked />jaringan komputer&nbsp;" +
                                 "<input type='checkbox' name='pSO' value='sistem operasi' " +
                        "checked />sistem operasi&nbsp;" +
                        "<input type='checkbox' name='pSD' value='sistem digital' />" +
                        "sistem digital&nbsp;" +
                        "<input type='checkbox' name='pStrukDat' value='struktur data' />" +
                        "struktudata&nbsp;" +
                        "</td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td>pilih hari:</td>" +
                        "<td><fieldset>" +
                        "<input type='checkbox' name='psenin' value='senin' />" +
                        "senin;" +
                        "<input type='checkbox' name='pselasa' value='selasa' />" +
                        "selasa;" +
                        "<input type='checkbox' name='prabu' value='rabu' />" +
                        "rabu;<br/>" +
                        "<input type='checkbox' name='pkamis' value='kamis' />" +
                        "kamis;" +
                        "<input type='checkbox' name='pjumat' value='jumat' />" +
                        "jumat;" +
                        
                        "</fieldset></td>" +
                        "</tr>" +
                        "</table></fieldset><br/>" +
                        "<fieldset>" +
                        "<legend>dosen pengampu</legend>" +
                        "<table cellpadding='2' cellspacing='2'>" +
                        "<tr>" +
                        "<td>Nama Dosen:</td>" +
                        "<td>" +
                        "<input type='text' name='pRxNama' size='40' maxlength='64'/>" +
                        "</td>" +
                        "</tr>" +
                        "<tr>" +
                        "</tr>" +
                        "</table></fieldset><br/>" +
                        "<fieldset>" +
                        "<legend>Identitas Mahasiswa</legend>" +
                        "<table cellpadding='2' cellspacing='2'>" +
                        "<tr>" +
                        "<td>NAMA MAHASISWA:</td>" +
                        "<td>" +
                        "<input type='text' name='pTxNama' size='40' maxlength='64'/>" +
                        "</td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td>NIM:</td>" +
                        "<td>" +
                        "<input type='text' name='pTxAlmt' size='40' maxlength='64'/>" +
                        "</td>" +
                        "</tr>" +
                        "<tr>" +       
                        "<td>SEMESTER:</td>" +
                        "<td>" +
                        "<input type='text' name='pTxEmail' size='40' maxlength='80'/>" +
                        "</td>" +
                        "<td align='right'>" +
                        "<input type='submit' value='selesai >>' />" +
                        "</td>" +
                        "</tr>" +
                        "</table></fieldset>" +
                        "</form>");
            out.println("</body>");
            out.println("</html>");
            out.close();
            }
}
    protected void doPost(HttpServletRequest request,
                HttpServletResponse response)
                throws ServletException, IOException {
            // Ambil request parameter
                String prodi = request.getParameter("pOccasion");
                String matkul = "";
                matkul += (request.getParameter("pkalkulus")==null) ? "" : "Kalkulus, ";
                matkul += (request.getParameter("pkeamanan")==null) ? "" : "Keamanan Basis Data, ";
                matkul += (request.getParameter("pjarkom")==null) ? "" : "Jaringan Komputer, ";
                matkul += (request.getParameter("pSO")==null) ? "" : "Sistem Operasi, ";
                matkul += (request.getParameter("pSD")==null) ? "" : "Sistem Digital, ";
                matkul += (request.getParameter("pStrukDat")==null) ? "" : "Struktur Data ";
                String hari = "";
                hari += (request.getParameter("psenin")==null) ? "" : "senin, ";
                hari += (request.getParameter("pselasa")==null) ? "" : "selasa, ";
                hari += (request.getParameter("prabu")==null) ? "" : "rabu, ";
                hari += (request.getParameter("pkamis")==null) ? "" : "kamis, ";
                hari += (request.getParameter("pjumat")==null) ? "" : "jumat, ";
                String rxNama = request.getParameter("pRxNama");
                String rxAlmt = request.getParameter("pRxAlmt");
                String rxPesan = request.getParameter("pRxPesan");
                String txNama = request.getParameter("pTxNama");
                String txAlmt = request.getParameter("pTxAlmt");
                String txEmail = request.getParameter("pTxEmail");

                String IPAddr = request.getRemoteAddr();
            // Beberapa response header
                response.setHeader("cache-control", "no-cache");
                response.setContentType("text/html; charset=UTF-8");
                response.setHeader("content-language", "id");
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet OrderServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("Data KRS Anda:" +
                            "<table border='1' cellpadding='4' cellspacing='2'>" +
                            "<tr><td>NAMA MAHASISWA:</td><td>" + txNama + "</td></tr>" +
                            "<tr><td>NIM:</td><td>" + txAlmt + "</td></tr>" +
                            "<tr><td>SEMESTER:</td><td>" + txEmail + "</td></tr>" +
                            
                            "<tr><td>Nama Dosen:</td><td>" + rxNama + "</td></tr>" +
                           
                            
                            "<tr><td>prodi:</td><td>" + prodi + "</td></tr>" +
                            "<tr><td>makul:</td><td>" + matkul + "</td></tr>" +
                            "<tr><td>hari:</td><td>" + hari + "</td></tr>" +
                            "</table>");
                out.println("<p align='right'>" +
                            "<form action='Checkout' target='_blank' method='POST'>" +
                            "<input type='submit' value='Check-out >>'>" +
                            "</form");
                out.println("</body>");
                out.println("</html>");
                out.close();
}
/** Returns a short description of the servlet.
*/
public String getServletInfo() {
return "Order Form";
    }
}