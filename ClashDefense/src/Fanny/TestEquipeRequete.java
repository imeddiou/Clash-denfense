/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fanny;

import Fanny.EquipeRequête;
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
public class TestEquipeRequete {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // IM : test des class      
        Database baseDeDonnées = new Database();
        try {
            
            baseDeDonnées.connect();      
            EquipeRequête equiperequête = new EquipeRequête();
            equiperequête.équipeRequêteModificationIdJoueur(baseDeDonnées, 13);
            ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM équipe");
            OutilsJDBC.afficherResultSet(resultat);
            baseDeDonnées.disconnect(); 
            
            } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

