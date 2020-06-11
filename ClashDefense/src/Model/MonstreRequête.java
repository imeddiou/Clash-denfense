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
public class MonstreRequête {
    private Database baseDeDonnées;

    public MonstreRequête() {
        try {
            baseDeDonnées.connect();
        } catch (SQLException ex) {
            Logger.getLogger(MonstreRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void monstreRequêteInsertion(String description, String couleur){          
        try {            
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT MAX(IdMonstre) FROM monstre WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");        
            int dernierIdMonstre = 0; 
            double positionX = 0.0;
            double positionY = 0.0;
            double vitesse = 0.0;
            int pdV = 0;
            int direction = 0;
            int avancée = 0;
            while (resultat0.next()){
                dernierIdMonstre = resultat0.getInt(1)+1;                
            }
            ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT * FROM cataloguemonstre WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
            while (resultat1.next()){
                dernierIdMonstre = dernierIdMonstre + resultat1.getInt(1);
                positionX = resultat1.getDouble("PositionX");
                positionY = resultat1.getDouble("PositionY");
                vitesse = resultat1.getDouble("Vitesse");
                pdV = resultat1.getInt("PdV");
                direction = resultat1.getInt("Direction");
                avancée = resultat1.getInt("Avancée");
            }                
            baseDeDonnées.executeQuery("INSERT INTO monstre VALUES ('"+dernierIdMonstre+"','"+description+"','"+positionX+"','"+positionY+"','"+couleur+"','"+vitesse+"','"+pdV+"','"+direction+"','"+avancée+"')");
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
    }
    
    
    public void monstreRequêteModificationPosition (int idMonstre, double positionX, double positionY){  
         baseDeDonnées.executeQuery("UPDATE monstre SET PositionX = "+positionX+", PositionY = "+positionY+" where IdMonstre = "+idMonstre);
    }            
}
