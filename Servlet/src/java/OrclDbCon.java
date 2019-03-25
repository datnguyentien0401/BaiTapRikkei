/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class OrclDbCon {
     // mo ket noi
     public static Connection open () {
   
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
               Connection cnn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:demoServlet","hr","datnt0401");
                return cnn;
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(OrclDbCon.class.getName()).log(Level.SEVERE, null, ex);
            }
         return null;
     }
     // dong ket noi
     public static void close (ResultSet rs, PreparedStatement ps, Connection cnn) {
         
         try {
             if(rs!=null && !rs.isClosed()){
                 rs.close();
             }
         } catch (SQLException ex) {
             Logger.getLogger(OrclDbCon.class.getName()).log(Level.SEVERE, null, ex);
         }
         
          try {
             if(ps!=null && !ps.isClosed()){
                 ps.close();
             }
         } catch (SQLException ex) {
             Logger.getLogger(OrclDbCon.class.getName()).log(Level.SEVERE, null, ex);
         }
           try {
             if(cnn!=null && !cnn.isClosed()){
                 cnn.close();
             }
         } catch (SQLException ex) {
             Logger.getLogger(OrclDbCon.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
}
