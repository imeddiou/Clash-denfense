/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Archive_des_classes.Database;
import Model.PartieRequête;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

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
        Monstre nlGraet=new Monstre(connexion,1,"Chevalier", 16.0,0.0,"Bleue",2.0,1452,1,0);
        //System.out.println(Arrays.toString(nlGraet.getCoordonneesDAO()));
       nlGraet.setCoordonneesDAO(16, 0);
        //System.out.println(nlGraet.getCoordonneesDAO()[1]);
        //System.out.println(nlGraet.getMAP(nlGraet.getCoordonneesDAO()[0], nlGraet.getCoordonneesDAO()[1]));
        //nlGraet.Avancer();
        
//        System.out.println(nlGraet.getMAP(15,0));
//                System.out.println(nlGraet.getMAP(0,14));
//        System.out.println("Direction = "+nlGraet.getDirection());
//        System.out.println("Mur devant = "+nlGraet.MurDevant());
//        System.out.println(Arrays.toString(nlGraet.getCoordonneesDAO()));
//        nlGraet.Avancer();
//        System.out.println(Arrays.toString(nlGraet.getCoordonneesDAO()));
        //nlGraet.Affichage();
        
        for (int i=0;i<20;i++){
        System.out.println(i+" = "+nlGraet.getMAP(i,18));
        }
        connexion.close();

    }catch (SQLException ex) {
            ex.printStackTrace();
        }
    } 
    
}

