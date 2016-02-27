/*
@Author Nil
 */
package deckbldr.dbaccess;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static Connection conn=null;
    private static String dbString = "jdbc:mysql://localhost:3306/mtgdb";
    private static String userName = "MTGDBUSER";
    private static String password = "CUS1166";//changed the directory username and password -Nil
    
  public static void init() throws ClassNotFoundException{  
        Class.forName("com.mysql.jdbc.Driver");
             
    }
    
    /**
        * Creates connection to database
        * @return connection connected
        * @throws SQLException connection is not established
        */
      
    public static Connection getMyConnection() throws SQLException{
        if (conn == null)
          conn=DriverManager.getConnection(dbString,userName, password);  
        return conn;
    }
    
    public static void close()
    {
        if (conn != null)
            try {
                conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
