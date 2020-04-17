/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fanny;

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
    
    private String pseudo;
    private int id;
    private Database baseDeDonnées;
    
    public void joueurRequêteInsertion(String pseudo){
          
        try {
            int dernierIdJoueur = 0; 
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT MAX(IdJoueur) FROM joueur");
            while (resultat0.next()){
                dernierIdJoueur = resultat0.getInt(1)+1;
            }
            baseDeDonnées.executeQuery("INSERT INTO joueur (`IdJoueur`,`Pseudo`) VALUES ('id','pseudo')");
            ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM joueur");
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
    }
    
    public void joueurRequêteModificationPseudo(String pseudo, int id){
        baseDeDonnées.executeQuery("UPDATE joueur SET Pseudo = 'pseudo' WHERE IdJoueur = 'id'");
        ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM joueur");
    }
}
