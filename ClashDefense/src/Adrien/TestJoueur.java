/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adrien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author PC d'Adrien
 */
public class TestJoueur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");

            //Commentaire titre: Création de la carte
           CarteTest carte1= new CarteTest(new int[]{20,20});
           carte1.creerUneCarteVierge();
           int largeurChemin= 4;
           carte1.creerUnCheminDroitAuMilieu(largeurChemin);

           //Commentaire titre: Affichage de la carte
           //Masqué : carte1.afficherPlan();


           //Commentaire titre: Partie 1 - Création du joueur et mise sur la carte
           System.out.println("Partie 1 - Création du joueur et mise sur la carte");
           Joueur joueur1= new Joueur(5, "JoueurDeTest",connexion, carte1);   // Commentaire : Ne marche que si un joueur a un ID de 5 dans la BDD équipe et partie
           joueur1.setLocalisationJoueur(new int[] {0,0}); // Commentaire : initialisation de la position à l'origine
           System.out.println(Arrays.toString(joueur1.getLocalisationJoueur()));
           joueur1.faireApparaitreJoueurSurLaCarte();
           carte1.afficherPlan();
           
           
           //______________________________________________________________________________________________________________________________

           
           
           //Commentaire titre :Partie 2-Déplacement du joueur
           System.out.println("----------------------------------------------------------------------------------");
           System.out.println("Partie 2-Déplacement du joueur");
           
                    // Commentaire titre : Deplacement de 5 cases à droite 
           joueur1.setLocalisationJoueur(new int[] {0,0}); // Initialiser la position du joueur
           for (int i=0;i<5;i=i+1){
               joueur1.deplacementADroite();
           }
           System.out.println(Arrays.toString(joueur1.getLocalisationJoueur()));

                    // Commentaire titre : Déplacement vers le haut bloqué car en haut de la carte
           joueur1.deplacementEnHaut();
           System.out.println(Arrays.toString(joueur1.getLocalisationJoueur()));        

                    //Commentaire titre : Déplacment de 2 cases vers le bas
            for (int i=0;i<2;i=i+1){
               joueur1.deplacementEnBas();
           }
           System.out.println(Arrays.toString(joueur1.getLocalisationJoueur()));

                    //Commentaire titre : Déplacement vers la droite bloqué au niveau du chemin (à partir de  y = 7)
            for (int i=0;i<10;i=i+1){
               joueur1.deplacementADroite();
           }
           System.out.println(Arrays.toString(joueur1.getLocalisationJoueur()));
//

            //___________________________________________________________________________________
            
            
          //Commentaire titre : Partie 3 - Geteur de camp et Setteur d'elixir et de camp pour des applications suivants
          System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Partie 3 - Geteur de camp et Setteur d'elixir et de camp pour des applications suivants");
        
        System.out.println("Le joueur d'Id "+joueur1.getId()+" est dans le camp "+ joueur1.dansQuelCampEstTIl());  // Commentaire : Guetteur de camp
        joueur1.setElixir(-1); // Commentaire : retire 1 point d'Elixir à l'équipe du joueur
        System.out.println("L'elixir de l'équie du "+joueur1.getPseudo()+" est maintenant de "+joueur1.getElixirEquipe());
        System.out.println(joueur1.getElixirEquipe());
        
        connexion.close();
        } catch (SQLException ex) {
            
        }
        
}
    }
    

