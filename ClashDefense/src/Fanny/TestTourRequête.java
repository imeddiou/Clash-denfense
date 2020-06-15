/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fanny;

import Ibrahim.Database;
import Utils.OutilsJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fanny
 */
public class TestTourRequête {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Database baseDeDonnées = new Database();
        try {
            
            baseDeDonnées.connect();    
            TourRequête tourrequête = new TourRequête();
            //tourrequête.tourInsertion(baseDeDonnées, "tourClassique", "Blanc");
            //tourrequête.perteElixir(baseDeDonnées, "tourClassique", "Rouge");
            ArrayList<Integer> listeIdTour = tourrequête.listeIdTour(baseDeDonnées);
            System.out.println(listeIdTour);
            //ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM tour");
            //OutilsJDBC.afficherResultSet(resultat);
            baseDeDonnées.disconnect(); 
            
            } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
    

