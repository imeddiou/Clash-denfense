/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClashDefense;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fanny
 */
public class Database {
    private Connection cn;
    private Statement st; 
    
    public Database(){
        // on met un contruteur vide comme on travaille avec une seule base de donnÃ©es
    }
    public void connect() throws SQLException{
        cn = DriverManager.getConnection ("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC" , "clashdefense" , "WCvYk10DhJUNKsdX");
        this.st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
     private ResultSet executeQueryThrowsException(String query) throws SQLException {
        if (query.contains("INSERT") || query.contains("UPDATE") || query.contains("DELETE")) {
            st.executeUpdate(query);
        } else {
            st.execute(query);
            return st.getResultSet();
        }
        return null;
    }
      public void disconnect() {
        try {
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public ResultSet executeQuery(String query) {
        try {
            return executeQueryThrowsException(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        public boolean test_isConnectionWorking() {
        if (cn != null && st != null) {
            try {
                st.execute("SELECT * FROM INFORMATION_SCHEMA.TABLES");
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    
}
