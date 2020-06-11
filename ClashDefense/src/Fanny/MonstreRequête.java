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
    
    public void monstreRequêteInsertion(Database baseDeDonnées,String description, String couleur){          
        try {             
            int dernierIdMonstre = 0;
            int idMax= 0;
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT MAX(IdMonstre) FROM monstre WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
            System.out.println(resultat0.isClosed());
            while (resultat0.next()){
                idMax = resultat0.getInt(1);
            }
                if (idMax == 0){
                    ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT IdMonstre FROM catalogueMonstre WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
                    while (resultat1.next()){
                        dernierIdMonstre = resultat1.getInt(1)+1;
                    }
                }  
                else{
                    ResultSet resultat2 = baseDeDonnées.executeQuery("SELECT MAX(IdMonstre) FROM monstre WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
                    while (resultat2.next()){
                        dernierIdMonstre = resultat2.getInt(1)+1;
                    }
                }                
                       
            double positionX = 0.0;
            double positionY = 0.0;
            double vitesse = 0.0;
            int pdV = 0;
            int direction = 0;
            int avancée = 0;
            ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT * FROM cataloguemonstre WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
            while (resultat1.next()){
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
    
    
    public void monstreRequêteModificationPosition (Database baseDeDonnées,int idMonstre, double positionX, double positionY){  
           try {  
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT PositionX FROM monstre WHERE idMonstre = '"+idMonstre+"' ");        
            while (resultat0.next()){
                positionX = resultat0.getDouble("PositionX");                
            }
            ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT PositionY FROM monstre WHERE idMonstre = '"+idMonstre+"' ");
            while (resultat1.next()){
                positionY = resultat1.getDouble("PositionY"); 
            }                
            baseDeDonnées.executeQuery("UPDATE monstre SET PositionX = '"+positionX+"', PositionY = '"+positionY+"' WHERE idMonstre = '"+idMonstre+"' ");
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
    }            
}
