/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adrien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author PC d'Adrien
 */
public class TestEcouteur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        CarteTest carte1= new CarteTest(new int[]{20,20});
       carte1.creerUneCarteVierge();
       int largeurChemin= 4;
       carte1.creerUnCheminDroitAuMilieu(largeurChemin);
        Joueur joueur1= new Joueur(6, "JoueurDeTest",connexion, carte1);  // Doit y avoir un joueur avec l ID 6 dans la BDD partie
        
        JFrame fenetre = new JFrame();
        fenetre.setSize(500,400);
        fenetre.setVisible(true);
        fenetre.addKeyListener(new EcouteurClavier(joueur1));
        connexion.close();
    }
    }
    

