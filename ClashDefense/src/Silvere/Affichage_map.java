/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Silvere;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Silvère BARDIN
 */
public class Affichage_map {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");

            PreparedStatement requete = connexion.prepareStatement("SELECT Idtour, PositionX, PositionY, Equipe, Description FROM tour;");
            ResultSet resultat = requete.executeQuery();
            ArrayList Id = new ArrayList();
            ArrayList PosX = new ArrayList();
            ArrayList PosY = new ArrayList();
            ArrayList Eq = new ArrayList();
            ArrayList Typetour = new ArrayList();
            int[][] map =new int[20][];
            for (int i=0 ; i<map.length; i=i+1){
                map[i]=new int[20];             // définition d'une map de taille 20x20 (map exemple) à récupérer du code de Nico
            }
            for (int i=0 ; i<map.length; i=i+1){
                for (int j=0 ; j<map[i].length; j=j+1){
                    map[i][j]=0;
                }
            }
            for (int i=0 ; i<map.length; i=i+1){
                for (int j=5 ; j<8; j=j+1){
                    map[i][j]=-1;                   // définition d'un premier chemin
                }
            }
            for (int i=0 ; i<map.length; i=i+1){
                for (int j=12 ; j<15; j=j+1){
                    map[i][j]=-1;                   // définition d'un deuxième chemin
                }
            }
           
            while (resultat.next()) {
                int Idtour = resultat.getInt("Idtour");
                Id.add(Idtour);
                int PositionX = resultat.getInt("PositionX");
                PosX.add(PositionX);
                int PositionY = resultat.getInt("PositionY");
                PosY.add(PositionY);
                String Equipe = resultat.getString("Equipe");
                Eq.add(Equipe);
                String Description = resultat.getString("Description");
                Typetour.add(Description);
            }
            requete.close();
            connexion.close();
        for(int i=0;i<Id.size();i=i+1){
            map[(int)PosX.get(i)][(int)PosY.get(i)]=(int)Id.get(i) ;    // On place les tours à leurs positions
        
        }
        JFrame fenetre = new JFrame();  //création de la map
        JPanel pan = new JPanel (new GridLayout (20,20));
        Border blackline = BorderFactory.createLineBorder(Color.black,1);
        for(int i = 0; i<400;i++){
            int Q = i/20;
            int R  = i%20;
            JPanel Case = new JPanel();
            Case.setBorder(blackline);
            if(map[Q][R]==0){                              // zone de construction des tours
                Color Brown = new Color(51,0,0); 
                Case.setBackground(Brown);
            }else if(map[Q][R]==-1){                        // chemins
                Color Sable = new Color(255,204,102);
                Case.setBackground(Sable);
            }else if(map[Q][R]>0){                          // affichage des tours
                int k = Id.indexOf(map[Q][R]);
                if(Eq.get(k).equals("Bleue")){            //changer la couleur des tours en fonction de leurs équipes
                    Case.setBackground(Color.BLUE);
                }else{
                    Case.setBackground(Color.red);
                }
                if(Typetour.get(k).equals("tourClassique")){// identifier les types de tours
                  ImageIcon icon = new ImageIcon("C:\\Users\\Silvère BARDIN\\Desktop\\images_tours_monstres\\tourclassique.png"); // test affichage de la tour; mettre dans la base de donnée le graphisme des tours et des monstres
                  JLabel img = new JLabel(icon);
                  Case.add(img);  
                }else if(Typetour.get(k).equals("tour3")){
                  ImageIcon icon = new ImageIcon("C:\\Users\\Silvère BARDIN\\Desktop\\images_tours_monstres\\tourincendiaire.png"); // test affichage de la tour; mettre dans la base de donnée le graphisme des tours et des monstres
                  JLabel img = new JLabel(icon);
                  Case.add(img);
                }

                 
                 
            }
            pan.add(Case);
        }
        pan.setBorder(blackline);
        fenetre.add(pan);
        fenetre.setVisible(true);
        fenetre.setSize(850,650);
//        for (int i = 0; i<map.length; i=i+1){
//            for (int j = 0; j<map[j].length; j=j+1)
//            System.out.println(map[i][j]);
//     }
//        for (int i = 0; i<map.length; i=i+1){
//            System.out.println(map[i]);
//        }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

   
    }
}
