/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archive_des_classes;
import static java.lang.Math.random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author PC d'Adrien
 */
public class TestAttaquant {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
           CarteTest carte1= new CarteTest(new int[]{20,20});
           Attaquant Haaland= new Attaquant(5, "JoueurDeTest", connexion, carte1);
           
           
           System.out.println("Id max de monstre dans ma partie =" +Haaland.IdMaxMonstre());                // Affiche l'Id max des monstre sur le terrain
           
           System.out.println("Cout en Eixir d'un chevalier "+Haaland.coutMonstreType(1));                  //Affiche le coût du monstre de type 1 (chevalier)
           
           Haaland.extraireMonstreDuCatalogueMonstre(100).AfficherCatactérisiqueMonstre();   // Afficher le monstre d'Id 1 du catalogue
           
           //Haaland.introduireMonstreDansLaBDD(Haaland.extraireMonstreDuCatalogueMonstre(100));    // Ajouter à la BDD de combat le monste d'Id 100 dans le catalogue
           
           
           
           Haaland.setElixir(-20);
           //Haaland.apparitionMonstre(3,"Rouge");
           
           connexion.close();
    }   catch (Exception ex) {
            Logger.getLogger(TestAttaquant.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    

