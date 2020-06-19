/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import Utils.OutilsJDBC;
import controller.Chemin20x20;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fanny
 */
public class TestPartieRequête {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Database baseDeDonnées = new Database();
        try {
            
            baseDeDonnées.connect();   
            PartieRequête partierequête = new PartieRequête();
            partierequête.finDePartie(baseDeDonnées);
            //ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM monstre");
            //OutilsJDBC.afficherResultSet(resultat);Chemin20x20 chemin = new Chemin20x20();
//            ArrayList<ArrayList<Integer>> map = chemin.CreationMap();
//            partierequête.partieRequêteStockageMap(baseDeDonnées, map);
//            Chemin20x20 chemin = new Chemin20x20();
//            ArrayList<ArrayList<Integer>> map = chemin.CreationMap();
//            partierequête.partieRequêteStockageMap(baseDeDonnées, map);

            ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT * FROM équipe");
            OutilsJDBC.afficherResultSet(resultat1);
            //ResultSet resultat2 = baseDeDonnées.executeQuery("SELECT * FROM équipe");           
           // OutilsJDBC.afficherResultSet(resultat2);
            baseDeDonnées.disconnect(); 
            
            } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

