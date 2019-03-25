/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(urlPatterns = {"/loginProcess"})
public class loginProcess extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String username = request.getParameter("username");
        String pass = request.getParameter("password");

        Connection cnn = dbconnect.open();
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (cnn != null) {
            try {
                ps = cnn.prepareStatement("select UserName, Password from Account");
                rs = ps.executeQuery();
                if (username == null || username.isEmpty()) {
                    response.sendRedirect("login.jsp");
                }
                if (pass == null ) {
                    response.sendRedirect("login.jsp");
                }
                int found=0;
                while (rs.next()) {
                    if (username.equals(rs.getString("UserName"))) {
                        if (pass.equals(rs.getString("Password"))) {
                            response.sendRedirect("index.html");
                        } else {
                            response.sendRedirect("login.jsp");
                        }
                        found=1;
                        break;
                    }
                    
                }
                if(found==0)
                    response.sendRedirect("login.jsp");

            } catch (SQLException ex) {
                Logger.getLogger(loginProcess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                dbconnect.close(rs, ps, cnn); // dong ket noi   
            }
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(loginProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(loginProcess.class.getName()).log(Level.SEVERE, null, ex);
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
