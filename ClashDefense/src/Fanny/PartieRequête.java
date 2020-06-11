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
    
    public void partieRequêteModificationIdJoueur(Database baseDeDonnées,int idJoueur){
        try {
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT Rôle FROM partie WHERE IdJoueur = '"+idJoueur+"'");
            String Rôle = "";
            while (resultat0.next()){
                Rôle = resultat0.getString("Rôle");
            }
                if (Rôle.contains("Bleu")){
                    if (Rôle.contains("Attaquant")){
                        baseDeDonnées.executeQuery("UPDATE équipe SET IdJoueurAttaquant = '"+idJoueur+"' WHERE Couleur = 'Bleue' ");                    
                    }
                    else{
                        baseDeDonnées.executeQuery("UPDATE équipe SET IdJoueurDéfenseur = '"+idJoueur+"' WHERE Couleur = 'Bleue' ");                    
                    }
                }
                else {
                    if (Rôle.contains("Attaquant")){
                        baseDeDonnées.executeQuery("UPDATE équipe SET IdJoueurAttaquant = '"+idJoueur+"' WHERE Couleur = 'Rouge' ");                    
                    }
                    else{
                        baseDeDonnées.executeQuery("UPDATE équipe SET IdJoueurDéfenseur = '"+idJoueur+"' WHERE Couleur = 'Rouge' ");                    
                    }
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
    
    
    public void partieRequêteModificationPosition (Database baseDeDonnées,String rôle, double positionX, double positionY){
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
    public void partieRequêteStockageMap(Database baseDeDonnées, ArrayList<ArrayList<Integer>> map){
        String mapString = "";
        mapString = map.toString();
        
        System.out.println(mapString);
        baseDeDonnées.executeQuery("UPDATE partie SET Map = '" + mapString + "'"  );
    }
    
}
