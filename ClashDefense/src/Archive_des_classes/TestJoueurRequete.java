/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archive_des_classes;

import Archive_des_classes.Database;
import Utils.OutilsJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ibrahim
 */
public class TestJoueurRequete {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Database baseDeDonnées = new Database();
        try {
            
            baseDeDonnées.connect();      
            JoueurRequête joueurrequête = new JoueurRequête();
            joueurrequête.joueurInsertion(baseDeDonnées, "Fanny");
            ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM joueur");
            OutilsJDBC.afficherResultSet(resultat);
            baseDeDonnées.disconnect();             
            } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    

