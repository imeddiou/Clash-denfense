/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.FlowLayout;
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
import Archive_des_classes.Chemin20x20;
import javax.swing.JOptionPane;

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
            PreparedStatement requete2 = connexion.prepareStatement("SELECT IdMonstre, PositionX, PositionY, Equipe, Description FROM monstre;");
            ResultSet resultat = requete.executeQuery();
            ResultSet resultat2 = requete2.executeQuery();
            ArrayList Id = new ArrayList();
            ArrayList PosX = new ArrayList();
            ArrayList PosY = new ArrayList();
            ArrayList Eq = new ArrayList();
            ArrayList Typetour = new ArrayList();
            ArrayList IdM = new ArrayList();
            ArrayList PosXM = new ArrayList();
            ArrayList PosYM = new ArrayList();
            ArrayList EqM = new ArrayList();
            ArrayList TypeMonstre = new ArrayList();
            int[][] map =new int[20][];
            for (int i=0 ; i<map.length; i=i+1){
                map[i]=new int[20];             // définition d'une map de taille 20x20 (map exemple) à récupérer du code de Nico
            }
            Chemin20x20 chemin = new Chemin20x20();
            ArrayList<ArrayList<Integer>> map2 = chemin.CreationMap();
            for (int i=0 ; i<map.length; i=i+1){
                for (int j=0 ; j<map[i].length; j=j+1){
                    map[i][j]=map2.get(i).get(j);
                }
            }
            map[17][9]=-3; // position des defenseur et attaquant pour test
            map[12][17]=-4;
            map[5][18]=-5;
            map[8][3]=-6;
           // début programme d'affichage de la carte et des éléments
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
            while(resultat2.next()){
                int IdMonstre = resultat2.getInt("IdMonstre");
                IdM.add(IdMonstre);
                Double PositionXM = resultat2.getDouble("PositionX");
                PosXM.add(PositionXM);
                Double PositionYM = resultat2.getDouble("PositionY");
                PosYM.add(PositionYM);
                String EquipeM = resultat2.getString("Equipe");
                EqM.add(EquipeM);
                String DescriptionM =resultat2.getString("Description");
                TypeMonstre.add(DescriptionM);
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
            }else if(map[Q][R]==-1||map[Q][R]==-2){                        // chemins
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
                  ImageIcon icon = new ImageIcon(Affichage_map.class.getResource("tourclassique.png")); 
                  JLabel img = new JLabel(icon);
                  Case.add(img);
                }else if(Typetour.get(k).equals("tourPrécise")){
                  ImageIcon icon = new ImageIcon(Affichage_map.class.getResource("tourprecise.png")); 
                  JLabel img = new JLabel(icon);
                  Case.add(img);
                }else if(Typetour.get(k).equals("tourIncendiaire")){
                  ImageIcon icon = new ImageIcon(Affichage_map.class.getResource("tourincendiare.png")); 
                  JLabel img = new JLabel(icon);
                  Case.add(img);
                
                }
                 
            }else if(map[Q][R]==-3){    //affichage du défenseur rouge
                Case.setBackground(Color.red);
                Case.setLayout(new FlowLayout());
                JLabel Drouge = new JLabel("D");
                Case.add(Drouge);
            }else if(map[Q][R]==-4){     // affichage de l'attaquant rouge
                Case.setBackground(Color.red);
                Case.setLayout(new FlowLayout());
                JLabel Drouge = new JLabel("A");
                Case.add(Drouge);
            }else if(map[Q][R]==-5){     // affichage du défenseur bleu
                Case.setBackground(Color.BLUE);
                Case.setLayout(new FlowLayout());
                JLabel Drouge = new JLabel("D");
                Case.add(Drouge);
            }else if(map[Q][R]==-6){     // affichage de l'attaquant bleu
                Case.setBackground(Color.BLUE);
                Case.setLayout(new FlowLayout());
                JLabel Drouge = new JLabel("A");
                Case.add(Drouge);
            }
            pan.add(Case);
            
        }
       
    
        for(int i=0; i<IdM.size(); i=i+1){  //affichage des monstres
            if(TypeMonstre.get(i).equals("gobelin")){
                ImageIcon icon = new ImageIcon(Affichage_map.class.getResource("gobelin.png"));
                JLabel img = new JLabel(icon);
                int x = (int) Math.floor((double) PosXM.get(i));
                int y = (int) Math.floor((double) PosYM.get(i));
                img.setBounds(x, y, 42, 32);
                fenetre.add(img);
            }else if(TypeMonstre.get(i).equals("giant")){
                ImageIcon icon = new ImageIcon(Affichage_map.class.getResource("giant.png"));
                JLabel img = new JLabel(icon);
                int x = (int) Math.floor((double) PosXM.get(i));
                int y = (int) Math.floor((double) PosYM.get(i));
                img.setBounds(x, y, 42, 32);
                fenetre.add(img);
            }else if(TypeMonstre.get(i).equals("knight")){
                ImageIcon icon = new ImageIcon(Affichage_map.class.getResource("knight.png"));
                JLabel img = new JLabel(icon);
                int x = (int) Math.floor((double) PosXM.get(i));
                int y = (int) Math.floor((double) PosYM.get(i));
                img.setBounds(x, y, 42, 32);
                fenetre.add(img);
            }
            
        }
        pan.setBorder(blackline);
        fenetre.add(pan);
        fenetre.setVisible(true);
        fenetre.setSize(850,650);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

   
    }
}
