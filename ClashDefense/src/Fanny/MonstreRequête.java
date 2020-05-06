/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fanny;

import Ibrahim.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fanny
 */
public class MonstreRequête {
    private Database baseDeDonnées;
    
    
    public void monstreRequêteInsertionMonstre(String description, String couleur){          
        try {
            int dernierIdMonstre = 0; 
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT MAX(IdMonstre) FROM monstre WHERE Description = 'description', Couleur = 'couleur'");
            while (resultat0.next()){
                dernierIdMonstre = resultat0.getInt(1)+1;
            }
            double positionX = resultat0.getDouble("PositionX");
            double positionY = resultat0.getDouble("PositionY");
            double vitesse = resultat0.getDouble("Vitesse");
            int pdV = resultat0.getInt("PdV");
            int direction = resultat0.getInt("Direction");
            int avancée = resultat0.getInt("Avancée");
            baseDeDonnées.executeQuery("INSERT INTO monstre (`IdMonstre`,`Description`,`PositionX`,`PositionY`,`Equipe`,`Vitesse`,`PdV`,`Direction`,`Avancée`) VALUES ('dernierIdMonstre','description','"+positionX+"','"+positionY+"','couleur','"+vitesse+"','"+pdV+"','"+direction+"','"+avancée+"')");
            ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM monstre");
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
    }
    
    
    public void monstreRequêteModificationPosition (int idMonstre, double positionX, double positionY){  
        baseDeDonnées.executeQuery("UPDATE monstre SET PositionX = 'positionX' WHERE IdMonstre = 'idMonstre' ");
        baseDeDonnées.executeQuery("UPDATE monstre SET PositionY = 'positionY' WHERE IdMonstre = 'idMonstre' ");
        ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM monstre");
    }            
}
