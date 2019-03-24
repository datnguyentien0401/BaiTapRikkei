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
public class dbconnect {
     // mo ket noi
     public static Connection open () {
   
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
               Connection cnn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=GiaoHang;","sa","datnt0401");
                return cnn;
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(dbconnect.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(dbconnect.class.getName()).log(Level.SEVERE, null, ex);
         }
         
          try {
             if(ps!=null && !ps.isClosed()){
                 ps.close();
             }
         } catch (SQLException ex) {
             Logger.getLogger(dbconnect.class.getName()).log(Level.SEVERE, null, ex);
         }
           try {
             if(cnn!=null && !cnn.isClosed()){
                 cnn.close();
             }
         } catch (SQLException ex) {
             Logger.getLogger(dbconnect.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
}
