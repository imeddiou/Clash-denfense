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
    private String couleur;
    private int IdJoueur;
    private Database baseDeDonnées;
    
    
    
    public void équipeRequêteModificationIdJoueur(int idJoueur){//IM : ajouter baseDedonnées.connect() avant la requete
        try {
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT Rôle FROM partie WHERE IdJoueur = 'idjoueur'");
            if (resultat0.getString("Rôle").contains("bleu")){
                if (resultat0.getString("Rôle").contains("attaquant")){
                    baseDeDonnées.executeQuery("UPDATE équipe SET IdJoueurAttaquant = 'idJoueur' WHERE Couleur = 'Bleue' ");                    
                }
                else{
                    baseDeDonnées.executeQuery("UPDATE équipe SET IdJoueurDéfenseur = 'idJoueur' WHERE Couleur = 'Bleue' ");                    
                }
            }
            else {
                if (resultat0.getString("Rôle").contains("attaquant")){
                    baseDeDonnées.executeQuery("UPDATE équipe SET IdJoueurAttaquant = 'idJoueur' WHERE Couleur = 'Rouge' ");                    
                }
                else{
                    baseDeDonnées.executeQuery("UPDATE équipe SET IdJoueurDéfenseur = 'idJoueur' WHERE Couleur = 'Rouge' ");                    
                }
            }
            ResultSet resultat = baseDeDonnées.executeQuery("SELECT * FROM équipe");
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
}
