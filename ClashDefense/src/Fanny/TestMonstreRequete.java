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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ibrahim
 */
public class TestMonstreRequete {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Database baseDeDonnées = new Database();
        try {
            
            baseDeDonnées.connect();    
            MonstreRequête monstrerequête = new MonstreRequête();
            //monstrerequête.monstreInsertion(baseDeDonnées, "Géant", "Bleue");
            //monstrerequête.pertePdV(baseDeDonnées, "tourClassique", 103);
            monstrerequête.perteElixir(baseDeDonnées, "Chevalier", "Bleue");
            //monstrerequête.ModificationPosition(baseDeDonnées, 2, 100, 100);
            ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM équipe");
            OutilsJDBC.afficherResultSet(resultat);
            
            } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

