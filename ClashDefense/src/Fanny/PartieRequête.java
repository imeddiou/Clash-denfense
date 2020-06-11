/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fanny;

import Ibrahim.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fanny
 */
public class PartieRequête {
    
    public PartieRequête(){
        
    }
    
    public void ModificationIdJoueur(Database baseDeDonnées,int idJoueur, String rôle){
        baseDeDonnées.executeQuery("UPDATE partie SET IdJoueur = '"+idJoueur+"' WHERE Rôle = '"+rôle+"' ");        
    }
    
    
    public void ModificationPosition (Database baseDeDonnées,String rôle, double positionX, double positionY){
        try {
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT Selectionné FROM partie WHERE Rôle = '"+rôle+"'");
            if (resultat0.getBoolean("Selectionné")){
                baseDeDonnées.executeQuery("UPDATE partie SET PositionX = '"+positionX+"' WHERE Rôle = '"+rôle+"' ");
                baseDeDonnées.executeQuery("UPDATE partie SET PositionY = '"+positionY+"' WHERE Rôle = '"+rôle+"' ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void partieRequêteStockageMap(Database baseDeDonnées, ArrayList<ArrayList<Integer>> map){
        String mapString = "";
        mapString = map.toString();
        
        System.out.println(mapString);
        baseDeDonnées.executeQuery("UPDATE partie SET Map = '" + mapString + "'"  );
    }
    
}
