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
public class EquipeRequête {
    private Database baseDeDonnées;

    
    public EquipeRequête() {
        try {
            baseDeDonnées.connect();
        } catch (SQLException ex) {
            Logger.getLogger(EquipeRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void équipeRequêteModificationIdJoueur(int idJoueur){
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
}
