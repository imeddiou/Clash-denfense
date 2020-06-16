/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archive_des_classes;

import Archive_des_classes.Database;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author fanny
 */
public class JoueurRequête {

    
    public JoueurRequête() {
    }      
    
    public void joueurInsertion(Database baseDeDonnées, String pseudo){ 
          
        try {
            int dernierIdJoueur = 0; 
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT MAX(IdJoueur) FROM joueur");
            while (resultat0.next()){
                dernierIdJoueur = resultat0.getInt(1)+1;
            }
            baseDeDonnées.executeQuery("INSERT INTO joueur VALUES ('"+dernierIdJoueur+"','"+pseudo+"')");
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
    }
    
    
}
