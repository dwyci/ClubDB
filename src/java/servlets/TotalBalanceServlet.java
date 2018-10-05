
package servlets;

import business.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* @author dallas*/
public class TotalBalanceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "/MemberScreen.jsp";
          String  dTotal ="", cTotal ="";
          double fTotal = 0.0;
          
          ConnectionPool pool = 
                   ConnectionPool.getInstance();
           Connection conn = pool.getConnection();
           Statement t = conn.createStatement();
        
     //SQL Statements to return the total of debits and credits
            dTotal = " SELECT sum(amount) " +
                    " from tblpurchases " +
                    "  where p.memid = '" + m.getMemid() + "' " +
                    " AND transtype = 'D'";
          
            cTotal = " SELECT sum(amount) " +
                    " from tblpurchases " +
                    "  where p.memid = '" + m.getMemid() + "' " +
                    " AND transtype = 'C'";
//To find the final total            
             fTotal = Double.parseDouble(dTotal) - Double.parseDouble(cTotal);
    }
    
     msg = "Total Balance Due = " + fTotal;

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
