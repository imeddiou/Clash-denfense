/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fanny;

import Ibrahim.Database;
import Utils.OutilsJDBC;
import controller.Chemin20x20;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ibrahim
 */
public class TestPartieRequete {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Database baseDeDonnées = new Database();
        try {
            
            baseDeDonnées.connect();   
            PartieRequête partierequête = new PartieRequête();
            //partierequête.ModificationIdJoueur(baseDeDonnées, 20, "AttaquantBleu");
            ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM partie");
            OutilsJDBC.afficherResultSet(resultat);
//            Chemin20x20 map = new Chemin20x20();
//            ArrayList<ArrayList<Integer>> mapString =  map.CreationMap();
//            System.out.println(mapString.toString());
//            PartieRequête partie = new PartieRequête();
//            partie.partieRequêteStockageMap(baseDeDonnées, mapString);
               partierequête.partieRequêteSelectMapAsMatrix(baseDeDonnées);
            baseDeDonnées.disconnect(); 
            } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
