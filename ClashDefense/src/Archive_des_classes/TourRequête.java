/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archive_des_classes;

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
public class TourRequête {
    
    public TourRequête() {
    }
    
//    public void tourInsertion(Database baseDeDonnées,String description, String couleur){          
//        try {             
//            int dernierIdTour = 0;
//            int idMax= 0;
//            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT MAX(IdTour) FROM tour WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
//            System.out.println(resultat0.isClosed());
//            while (resultat0.next()){
//                idMax = resultat0.getInt(1);
//            }
//                if (idMax == 0){
//                    ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT IdTour FROM cataloguetour WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
//                    while (resultat1.next()){
//                        dernierIdTour = resultat1.getInt(1)+1;
//                    }
//                }  
//                else{
//                    ResultSet resultat2 = baseDeDonnées.executeQuery("SELECT MAX(IdTour) FROM tour WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
//                    while (resultat2.next()){
//                        dernierIdTour = resultat2.getInt(1)+1;
//                    }
//                }                
//                       
//            double positionX = 0.0;
//            double positionY = 0.0;
//            double vitesse = 0.0;
//            int niveau = 0;
//            int pdV = 0;
//            double portée = 0.0;
//            double fréquenceDeTir = 0.0;
//            double dégât = 0.0;
//            ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT * FROM cataloguetour WHERE Description = '"+description+"' AND Equipe = '"+couleur+"'");
//            while (resultat1.next()){
//                positionX = resultat1.getDouble("PositionX");
//                positionY = resultat1.getDouble("PositionY");
//                niveau = resultat1.getInt("Niveau");
//                pdV = resultat1.getInt("PdV");
//                portée = resultat1.getInt("Portée");
//                fréquenceDeTir = resultat1.getInt("FréquenceDeTir");
//                dégât = resultat1.getInt("Dégât");
//            }                
//            baseDeDonnées.executeQuery("INSERT INTO tour VALUES ('"+dernierIdTour+"','"+description+"','"+positionX+"','"+positionY+"','"+couleur+"','"+niveau+"','"+pdV+"','"+portée+"','"+fréquenceDeTir+"','"+dégât+"')");
//        } catch (SQLException ex) {
//            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
//        }       
//        
//    }
    
    public void tourInsertion(Database baseDeDonnées,String description, String couleur){          
        try {             
            int dernierIdTour = 0;           
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT MAX(IdTour) FROM tour ");
            System.out.println(resultat0.isClosed());
            while (resultat0.next()){
                dernierIdTour = resultat0.getInt(1) +1;
            }   
            double positionX = 0.0;
            double positionY = 0.0;
            double vitesse = 0.0;
            int niveau = 0;
            int pdV = 0;
            double portée = 0.0;
            double fréquenceDeTir = 0.0;
            double dégât = 0.0;
            ResultSet resultat1 = baseDeDonnées.executeQuery("SELECT * FROM cataloguetour WHERE Description = '"+description+"'");
            while (resultat1.next()){
                positionX = resultat1.getDouble("PositionX");
                positionY = resultat1.getDouble("PositionY");
                niveau = resultat1.getInt("Niveau");
                pdV = resultat1.getInt("PdV");
                portée = resultat1.getInt("Portée");
                fréquenceDeTir = resultat1.getInt("FréquenceDeTir");
                dégât = resultat1.getInt("Dégât");
            }                
            baseDeDonnées.executeQuery("INSERT INTO tour VALUES ('"+dernierIdTour+"','"+description+"','"+positionX+"','"+positionY+"','"+couleur+"','"+niveau+"','"+pdV+"','"+portée+"','"+fréquenceDeTir+"','"+dégât+"')");
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
    }
    
    public void perteElixir (Database baseDeDonnées,String description, String couleur){
        try {
            int cout = 0;
            double elixir = 0;
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT Coût FROM cataloguetour WHERE Description = '"+description+"'");
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
                System.out.println ("Vous n'avez pas assez d'élixir pour une telle action : veuillez choisir une autre tour ou attendre.");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public ArrayList<Integer> listeIdTour (Database baseDeDonnées){
        ResultSet resultat =  baseDeDonnées.executeQuery("SELECT IdTour FROM tour");
        ArrayList<Integer> listeIdTour = new ArrayList<Integer>();
        int liste = 0;
        try {
            while(resultat.next()){
                liste = resultat.getInt("IdTour");  
                listeIdTour.add(liste);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartieRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listeIdTour;
    }
}
