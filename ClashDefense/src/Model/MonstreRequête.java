/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Archive_des_classes.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fanny
 */
public class MonstreRequête {

    public MonstreRequête() {
    }
    
    public void monstreInsertion(Database baseDeDonnées,String description, String couleur){          
        try {             
            int dernierIdMonstre = 0;
            int idMax= 0;
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT MAX(IdMonstre) FROM monstre WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
            System.out.println(resultat0.isClosed());
            while (resultat0.next()){
                idMax = resultat0.getInt(1);
            }
                if (idMax == 0){
                    ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT IdMonstre FROM cataloguemonstre WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
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
    
    public void pertePdV (Database baseDeDonnées,String description, int idMonstre){
        try {
            int degat = 0;
            int pdv = 0;
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT Dégât FROM cataloguetour WHERE Description = '"+description+"'");
            while (resultat0.next()){
                degat = resultat0.getInt("Dégât");
            }
            ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT PdV FROM monstre WHERE IdMonstre = '"+idMonstre+"'");
            while (resultat1.next()){
                pdv = resultat1.getInt("PdV") - degat;
            }
            if (pdv > 0){
                baseDeDonnées.executeQuery("UPDATE monstre SET PdV = '"+pdv+"' WHERE IdMonstre = '"+idMonstre+"' ");
            }
            else {
                baseDeDonnées.executeQuery("UPDATE monstre SET PdV = '0' WHERE IdMonstre = '"+idMonstre+"' ");
                baseDeDonnées.executeQuery("DELETE FROM monstre WHERE IdMonstre = '"+idMonstre+"' ");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void perteElixir (Database baseDeDonnées,String description, String couleur){
        try {
            int cout = 0;
            double elixir = 0;
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT Coût FROM cataloguemonstre WHERE Description = '"+description+"'");
            while (resultat0.next()){
                cout = resultat0.getInt("Coût");
            }
            ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT Elixir FROM équipe WHERE Couleur = '"+couleur+"'");
            while (resultat1.next()){
                elixir = resultat1.getDouble("Elixir") - cout;
            }
            if (elixir >= 0){
                baseDeDonnées.executeQuery("UPDATE équipe SET Elixir = '"+elixir+"' WHERE Couleur = '"+couleur+"' ");
            }
            else {
                System.out.println ("Vous n'avez pas assez d'élixir pour une telle action : veuillez choisir un autre monstre ou attendre.");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    public void ModificationPosition (Database baseDeDonnées,int idMonstre, double positionX, double positionY){  
        baseDeDonnées.executeQuery("UPDATE monstre SET PositionX = '"+positionX+"', PositionY = '"+positionY+"' WHERE idMonstre = '"+idMonstre+"' ");

    }      
    
    public ArrayList<Integer> listeIdMonstre (Database baseDeDonnées){
        ResultSet resultat =  baseDeDonnées.executeQuery("SELECT IdMonstre FROM monstre");
        ArrayList<Integer> listeIdMonstre = new ArrayList<Integer>();
        int liste = 0;
        try {
            while(resultat.next()){
                liste = resultat.getInt("IdMonstre");  
                listeIdMonstre.add(liste);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartieRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listeIdMonstre;
    }
    
    public ArrayList<Integer> listeIdMonstreRouge (Database baseDeDonnées){
        ResultSet resultat =  baseDeDonnées.executeQuery("SELECT IdMonstre FROM monstre WHERE Equipe = 'Rouge'");
        ArrayList<Integer> listeIdMonstre = new ArrayList<Integer>();
        int liste = 0;
        try {
            while(resultat.next()){
                liste = resultat.getInt("IdMonstre");  
                listeIdMonstre.add(liste);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartieRequête.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return listeIdMonstre;
    }
    
    public ArrayList<Integer> listeIdMonstreBleu (Database baseDeDonnées){
        ResultSet resultat =  baseDeDonnées.executeQuery("SELECT IdMonstre FROM monstre WHERE Equipe = 'Bleue'");
        ArrayList<Integer> listeIdMonstre = new ArrayList<Integer>();
        int liste = 0;
        try {
            while(resultat.next()){
                liste = resultat.getInt("IdMonstre");  
                listeIdMonstre.add(liste);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartieRequête.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return listeIdMonstre;
    }
}
