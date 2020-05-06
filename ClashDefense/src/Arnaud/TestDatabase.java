package Arnaud;


import Ibrahim.Database;
import Utils.OutilsJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arnaud
 */
public class TestDatabase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Database baseDeDonnee = new Database();
        baseDeDonnee.connect(); 
        ResultSet resultat = baseDeDonnee.executeQuery("SELECT * FROM partie");
        OutilsJDBC.afficherResultSet(resultat);
        baseDeDonnee.disconnect();
        
        
        
    }
    
}
