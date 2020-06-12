/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adrien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC d'Adrien
 */
public class TestDefenseur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
           CarteTest carte1= new CarteTest(new int[]{20,20});
           
           //Commentaire titre : création de la carte
           carte1.creerUneCarteVierge();
           int largeurChemin= 4;
           carte1.creerUnCheminDroitAuMilieu(largeurChemin);


           //Commentaire titre : Création du joueur et mise sur la carte
           Defenseur DavidLuiz= new Defenseur(5, "JoueurDeTest", connexion, carte1);   // Besoin d'un joueur avec l'ID 5 dans partie, à modifier l iD si ce n'est pas le cas
           DavidLuiz.faireApparaitreJoueurSurLaCarte();
           
           System.out.println(DavidLuiz.dansQuelCampEstTIl());
           System.out.println("Cout de la tour classique : "+ DavidLuiz.coutTourType(1));


           // Commentaire titre : Partie 1 - Création de la tour et apparition sur la carte
           //Tour Mertesacker= new Tour(this.IdMaxTour,"Tour de niveau 5", 1.0, 1.0, "Rouge", 12.00,1.00,1.00);
           //DavidLuiz.introduireTourDansLaBDD(Mertesacker);
           DavidLuiz.construireUneTourIci(2, "Rouge");
           //DavidLuiz.introduireTourDansLaBDD(DavidLuiz.extraireTourDuCatalogueTour(1));
           //carte1.afficherPlan();
           
           DavidLuiz.setElixir(20);
           
           
           connexion.close();
        } catch (SQLException ex){
    }
    }  
    }
   
