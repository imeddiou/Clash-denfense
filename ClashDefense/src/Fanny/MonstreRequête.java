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

    public MonstreRequête() {
    }
    
    public void monstreRequêteInsertionMonstre(Database baseDeDonnées,String description, String couleur){ //IM : ajouter baseDedonnées.connect() avant la requete         
        try {            
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT MAX(IdMonstre) FROM monstre WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
            ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT * FROM cataloguemonstre WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
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
            while (resultat1.next()){
                positionX = resultat0.getDouble("PositionX");
                positionY = resultat0.getDouble("PositionY");
                vitesse = resultat0.getDouble("Vitesse");
                pdV = resultat0.getInt("PdV");
                direction = resultat0.getInt("Direction");
                avancée = resultat0.getInt("Avancée");
            }                
            baseDeDonnées.executeQuery("INSERT INTO monstre VALUES ('"+dernierIdMonstre+"','"+description+"','"+positionX+"','"+positionY+"','"+couleur+"','"+vitesse+"','"+pdV+"','"+direction+"','"+avancée+"')");
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
    }
    
    
    public void monstreRequêteModificationPosition (Database baseDeDonnées,int idMonstre, double positionX, double positionY){  //IM : ajouter baseDedonnées.connect() avant la requete
        baseDeDonnées.executeQuery("UPDATE monstre SET PositionX = '"+positionX+"' WHERE IdMonstre = '"+idMonstre+"' ");
        baseDeDonnées.executeQuery("UPDATE monstre SET PositionY = '"+positionY+"' WHERE IdMonstre = '"+idMonstre+"' ");
        ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM monstre");
    }            
}
