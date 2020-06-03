/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Fanny.*;
import Ibrahim.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fanny
 */
public class PartieRequête {
    
    private String rôle;
    private int IdJoueur;
    private Database baseDeDonnées;
    
    
    public void partieRequêteModificationIdJoueur(int idJoueur, String rôle){
        try {
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT selectionné FROM partie WHERE Rôle = 'rôle'");
            if (resultat0.getBoolean("Sélectionné")){
                baseDeDonnées.executeQuery("UPDATE partie SET IdJoueur = 'idJoueur' WHERE Rôle = 'rôle' ");
                ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM partie");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
    
    
    public void partieRequêteModificationPosition (String rôle, double positionX, double positionY){
        try {
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT selectionné FROM partie WHERE Rôle = 'rôle'");
            if (resultat0.getBoolean("Selectionné")){
                baseDeDonnées.executeQuery("UPDATE partie SET PositionX = 'positionX' WHERE Rôle = 'rôle' ");
                baseDeDonnées.executeQuery("UPDATE partie SET PositionY = 'positionY' WHERE Rôle = 'rôle' ");
                ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM partie");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
