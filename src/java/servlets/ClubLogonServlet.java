package servlets;

import business.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ClubLogonServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "/Logon.jsp";
        String msg = "", userid ="";
        long passattempt;
        Member m;  //requires an emport to use the object member
        
        String dbURL ="jdbc:mysql://localhost:3306/club";
        String dbUser ="root";
        String dbPwd = "sesame";
        
        try {
            userid = request.getParameter("userid").trim();
            passattempt = Long.parseLong(request.getParameter ("password"));
            Connection conn = DriverManager.getConnection(
                                    dbURL, dbUser, dbPwd);
            
            Statement s =  conn.createStatement();
            String sql = 
            "SELECT * FROM tblMembers WHERE MemId = '"+ userid +"'";
            ResultSet r = s.executeQuery(sql);
//            r.last();
//            msg = "Records returned from tblmembers = " 
//                    + r.getRow();
            if (r.next()){
                m = new Member();
                m.setMemid(userid);
                m.setPassword(r.getLong("Password"));
                m.setPassattempt(passattempt);
                if (m.isAuthenticated()){
                    m.setLastnm(r.getString("LastName"));
                   m.setFirstnm(r.getString("FirstName"));
                   m.setMiddlenm(r.getString("MiddleName"));
                   m.setStatus(r.getString("Status"));
                   m.setMemdt(r.getString("MemDt"));
                   URL = "/MemberScreen.jsp";
                   msg = "Member Authenticated! <br>";
                   
                                      
                } else {
                    msg = "Unable to Authenticate. <br>";
                }
                request.getSession().setAttribute("m", m);
                
            }else{
                msg = "Authentication failed (no member returned <br>";
            }

            
        }catch (SQLException e){
            msg = "Sql Error: " + e.getMessage();
        } catch (NumberFormatException e){
            msg = "Illegal password <br>";
        }
        
        request.setAttribute("msg", msg);
        Cookie uid =  new Cookie("userid", userid);
        uid.setMaxAge(60*10);
        uid.setPath("/");
        response.addCookie(uid);
        
        RequestDispatcher disp = 
                    getServletContext().getRequestDispatcher(URL);
            disp.forward(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
