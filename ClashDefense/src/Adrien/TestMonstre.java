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
public class TestMonstre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            //Création Monstre
            Monstre noelLeGraet= new Monstre(1, connexion);
            
            // Test getEquipe
            System.out.println(noelLeGraet.getEquipe());
            
            connexion.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        
        
    }
    
}
