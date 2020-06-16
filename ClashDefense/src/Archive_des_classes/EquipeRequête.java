/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archive_des_classes;

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

    
    public EquipeRequête() {
    }    
    
    public void ModificationIdJoueur(Database baseDeDonnées,int idJoueur){
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
    
    public void pertePdV (Database baseDeDonnées,String description,int idMonstre, String couleur){
        try {
            int degat = 0;
            int pdv = 0;
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT Attaque FROM cataloguemonstre WHERE Description = '"+description+"'");
            while (resultat0.next()){
                degat = resultat0.getInt("Attaque");
            }
            ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT PdV FROM équipe WHERE Couleur = '"+couleur+"'");
            while (resultat1.next()){
                pdv = resultat1.getInt("PdV") - degat;
            }
            if (pdv > 0){
                baseDeDonnées.executeQuery("UPDATE équipe SET PdV = '"+pdv+"' WHERE Couleur = '"+couleur+"' ");
                baseDeDonnées.executeQuery("DELETE FROM monstre WHERE IdMonstre = '"+idMonstre+"' ");
            }
            else {
                baseDeDonnées.executeQuery("UPDATE équipe SET PdV = '0' WHERE Couleur = '"+couleur+"' ");
                baseDeDonnées.executeQuery("DELETE FROM monstre WHERE IdMonstre = '"+idMonstre+"' ");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
}
