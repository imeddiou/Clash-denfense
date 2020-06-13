/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author PC d'Adrien
 */
public class Monstre {
    Connection connexion;
    private int idMonstre;
    private String description;
    private double positionX;
    private double positionY;
    private String equipe;
    private double vitesse;
    private int pdv;
    private int direction;
    private int avancee;
    
    
    public Monstre(Connection connexion, int idMonstre ,  String description, double positionX, double positionY,String equipe,double vitesse, int pdv,int direction, int avancee){
        this.connexion=connexion;
        this.idMonstre=idMonstre;
        this.description = description;
        this.positionX=positionX;
        this.positionY=positionY;
        this.equipe=equipe;
        this.vitesse=vitesse;
        this.pdv=pdv;
        this.direction=direction;
        this.avancee=avancee;
    }

    
    //Ajouts Adrien à la classe monstre commencé par Nicolas :
    
   public String getEquipeBDD(){
       String equipe=new String ("Blanc");
       try{
            PreparedStatement requete = connexion.prepareStatement("SELECT Equipe FROM Monstre WHERE IdMonstre="+this.idMonstre+" ;");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                equipe=resultat.getString("Equipe");
                }
            requete.close();
       }catch (SQLException ex){
           ex.printStackTrace();
       }
       return equipe;
   }
   
   public boolean lEquipeEstTElleReconnue(){
       if (this.getEquipeBDD().equals("Blanc")){
           return false;
       }
       return true;
   }
   
   public String getEquipeAdverse(){
       if (this.getEquipeBDD().equals("Bleue")){
        return "Rouge";
        }
       if (this.getEquipeBDD().equals("Rouge")){
            return "Bleue";
       }
       return "Blanc";
   }
   
   public void AfficherCatactérisiqueMonstre() throws Exception{
       System.out.println("Id : "+this.idMonstre);
       System.out.println("Description : "+this.description);
       System.out.println("PositionX : "+this.positionX);
       System.out.println("PositionY : "+this.positionY);
       System.out.println("Equipe : "+this.equipe);
       System.out.println("Vitesse : "+this.vitesse);
       System.out.println("PdV : "+this.pdv);
       System.out.println("Direction : "+this.direction);
       System.out.println("AVancee : "+ this.avancee);
       System.out.println("Vie equipe adverse : "+this.getVieEquipeAdverse());   
   }
   
   public double getVieEquipeAdverse(){
       double PdV=0.0;
        try{
            PreparedStatement requete = connexion.prepareStatement("SELECT PdV FROM équipe WHERE Couleur='"+this.getEquipeAdverse()+"' ;");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                PdV=resultat.getDouble("PdV");
                }
            requete.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return PdV;
   }
   
   public void setVieAdversaire(double differencePdV){
       double vieAdversaireInit=this.getVieEquipeAdverse();
        try {
            PreparedStatement requete = connexion.prepareStatement("UPDATE équipe SET PdV=? WHERE Couleur='"+this.getEquipeAdverse()+"'");
            requete.setDouble(1, vieAdversaireInit+differencePdV);
            System.out.println(requete);
            requete.executeUpdate();
            requete.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
   }

   public void attaqueChateau(double attaque){   // A terme il faut récupérer l'attauqe dans la BDD
       try{
       this.setVieAdversaire(-1.0*attaque);
       } catch (Exception ex) {
            Logger.getLogger(Monstre.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    public int getIdMonstre() {
        return idMonstre;
    }

    public String getDescription() {
        return description;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public int getPdv() {
        return pdv;
    }

    public double getVitesse() {
        return vitesse;
    }

    public int getDirection() {
        return direction;
    }

    public int getAvancee() {
        return avancee;
    }

    public String getEquipe() {
        return equipe;
    }
    
    
    
    
    
    
    
    
    
    
   
   
    

    public void setIdMonstre(int idMonstre) {
        this.idMonstre = idMonstre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public void setPdv(int nouveauxPdv) {
        this.pdv = nouveauxPdv;
    }
    
    public void setDirection(int nouvelleDirection) {
        this.direction = nouvelleDirection;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
   
   
}
