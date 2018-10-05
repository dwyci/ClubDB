
package servlets;

import business.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* @author dallas */

public class MemberUpdateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String sql ="", msg = "", URL="/MemberScreen.jsp";
        String fdata = "";
        long newpwd = 0;
        
        Member m;
        Member n = new Member();
        
         String dbURL ="jdbc:mysql://localhost:3306/club";
        String dbUser ="root";
        String dbPwd = "sesame";
        
        try {
            m = (Member) request.getSession().getAttribute("m");
            n.setMemid(m.getMemid());
            n.setMemdt(m.getMemdt());
            n.setStatus(m.getStatus());
            n.setPassattempt(m.getPassattempt());
            
            
          
            //obtain form elemnts for all updatebale fields...
            //allow changes to last, first and middlenm
            
            try {
                fdata = request.getParameter("firstname");
                if (!fdata.isEmpty()){
                    n.setFirstnm(fdata);
                } else {
                    msg += "First Name is empty <br>";
                }
             }catch (Exception e){
                 msg += "First name excpetion <br>";
             }
                        
            try {
                fdata = request.getParameter("middlename");
                if (!fdata.isEmpty()){
                    n.setMiddlenm(fdata);
                } else {
                    msg += "Middle Name is empty <br>";
                }
             }catch (Exception e){
                 msg += "Middle name excpetion <br>";
             }
            
             try {
                fdata = request.getParameter("lastname");
                if (!fdata.isEmpty()){
                    n.setLastnm(fdata);
                } else {
                    msg += "Last Name is empty <br>";
                }
             }catch (Exception e){
                 msg += "Last name excpetion <br>";
             }
             
              try {
                fdata = request.getParameter("memid");
                if (!fdata.isEmpty()){
                    n.setMemid(fdata);
                } else {
                    msg += "Member ID is empty <br>";
                }
             }catch (Exception e){
                 msg += "Member ID excpetion <br>";
             }
              
               try {
                fdata = request.getParameter("status");
                if (!fdata.isEmpty()){
                    n.setStatus(fdata);
                } else {
                    msg += "Status is empty <br>";
                }
             }catch (Exception e){
                 msg += "Status excpetion <br>";
             }
               
              try {
                fdata = request.getParameter("memdt");
                if (!fdata.isEmpty()){
                    n.setMemdt(fdata);
                } else {
                    msg += "Member date is empty <br>";
                }
             }catch (Exception e){
                 msg += "Member date excpetion <br>";
             }  
             
//              try {
//                fdata = request.getParameter("psswrd");
//                if (!fdata.isEmpty()){
//                    n.setPassword(fdata);
//                } else {
//                    msg += "Password is empty <br>";
//                }
//             }catch (Exception e){
//                 msg += "Password excpetion <br>";
//             }
              try{
                  newpwd = Long.parseLong(
                            request.getParameter("psswrd"));
                  if (newpwd > 0 ){
                      n.setPassword(newpwd);
                  } else {
                      msg += "Password Illegal<br>";
                  }
                                           
              } catch (NumberFormatException e){
                  msg += "Missing/Bad password<br>";
              }
              
              
//            if (msg.isEmpty()){
//                //update database...
//                n.setLastnm(m.getLastnm());
//                n.setFirstnm(m.getFirstnm());
//                Connection conn = 
//                        DriverManager.getConnection(dbURL, dbUser, dbPwd);
//            sql = "UPDATE tblMembers SET " +
//                    "Lastname = ?, " +
//                    "Firstname = ?, " +
//                    "Middlname = ?, " +
//                    "Status = ?, " +
//                    "MemDt = ?, " +
//                    "Password = ? " +
//                    "WHERE MemID = ? ";
//            
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, n.getLastnm());
//            ps.setString(2, n.getFirstnm());
//            ps.setString(3, n.getMiddlenm());
//            ps.setString(4, n.getStatus());
//            ps.setString(5, n.getMemdt());
//            ps.setLong(6, n.getPassword());
//            ps.setString(7, n.getMemid());
//            int rc = ps.executeUpdate();
//            if(rc ==0){
//                msg += "Update Failed: no recoreds changed. <br>";
//            } else if(rc == 1){
//                msg+= "Member Udated! <br>";
//                m = n;
//                request.getSession().setAttribute("m", m);
//            } else {
//                msg+= "unexpected update of " + rc +" records.<br>";
//            }
//                
//            
//            }
//            
//                  
//            
//        } catch (SQLException e){
//            msg += "Sql excpetion: " + sql + " " + e.getMessage();
//            
//        } catch (Exception e){
//            msg +="General error: " + e.getMessage();
//            
//        }
        if (msg.isEmpty()){
            m = newm;
          msg = MemberDB.updtMember(m);
        }


        request.setAttribute("msg", msg);
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
