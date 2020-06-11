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
public class TourRequête {
    Database baseDeDonnées;
    
    
    public TourRequête() {
        try {
            baseDeDonnées.connect();
        } catch (SQLException ex) {
            Logger.getLogger(TourRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tourRequêteInsertion(String description, String couleur){          
        try {  
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT MAX(IdTour) FROM tour WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");        
            int dernierIdTour = 0; 
            double positionX = 0.0;
            double positionY = 0.0;
            int niveau = 0;
            int pdV = 0;
            double portée = 0.0;
            double fréquenceDeTir = 0.0;
            double dégât = 0.0;
            while (resultat0.next()){
                dernierIdTour = resultat0.getInt(1)+1;                
            }
            ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT * FROM cataloguetour WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
            while (resultat1.next()){
                dernierIdTour = dernierIdTour + resultat1.getInt(1);
                positionX = resultat1.getDouble("PositionX");
                positionY = resultat1.getDouble("PositionY");
                niveau = resultat1.getInt("Niveau");
                pdV = resultat1.getInt("PdV");
                portée = resultat1.getDouble("Portée");
                fréquenceDeTir = resultat1.getDouble("FréquenceDeTir");
                dégât = resultat1.getDouble("Dégât");
            }                
            baseDeDonnées.executeQuery("INSERT INTO tour VALUES ('"+dernierIdTour+"','"+description+"','"+positionX+"','"+positionY+"','"+couleur+"','"+niveau+"','"+pdV+"','"+portée+"','"+fréquenceDeTir+"','"+dégât+"')");
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
