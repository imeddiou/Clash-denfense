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
//            //Création Monstre
//            //Monstre noelLeGraet= new Monstre(1, connexion);
//            //Monstre jmAulas=new Monstre(2, connexion);
//            //Monstre bLouzi=new Monstre(3,connexion);
//            
//            // Test getEquipe
//            System.out.println("Equipe de LeGraet");
//            System.out.println(noelLeGraet.getEquipe());
//            System.out.println("Equipe d Aulas");
//            System.out.println(jmAulas.getEquipe());
//            System.out.println("Equipe de Louzi");
//            System.out.println(bLouzi.getEquipe());
//            
//            //Test reconnaissance d'équipe
//            System.out.println("LeGraet reconnu ?");
//            System.out.println(noelLeGraet.lEquipeEstTElleReconnue());
//            System.out.println("Aulas reconnu ?");
//            System.out.println(jmAulas.lEquipeEstTElleReconnue());
//            System.out.println("Louzi reconnu ?");
//            System.out.println(bLouzi.lEquipeEstTElleReconnue());
//            
//            
//            //Test equipe adverse
//            System.out.println("Adversaire LeGraet");
//            System.out.println(noelLeGraet.getEquipeAdverse());
//            System.out.println("Adversaire LeGraet");
//            System.out.println(noelLeGraet.getEquipeAdverse());
//            System.out.println("Adversaire LeGraet");
//            System.out.println(noelLeGraet.getEquipeAdverse());
//            
//            //Test affichage caractéristique monstre 
//                //Compliqué avec le système de Exception 
//                
//            //Test getPdVAdverse
//            System.out.println("PdV adversaire LeGraet (les rouge)");
//            System.out.println(noelLeGraet.getVieEquipeAdverse());
//            System.out.println("PdV adversaire Aulas (les bleus)");
//            System.out.println(jmAulas.getVieEquipeAdverse());
//            
//            //Test attaque
//            System.out.println(noelLeGraet.getVieEquipeAdverse());
//            noelLeGraet.attaqueChateau(2.0);
//            System.out.println(noelLeGraet.getVieEquipeAdverse());
   
            
            
            connexion.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        
        
    }
    
}
