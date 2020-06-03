/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Adrien.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC d'Adrien
 */
public class Joueur {   // Adapter pour retirer "connection" des arguments
    private int id;
    private String pseudo;
    Connection connexion;
    CarteTest carte;
    
    
    public Joueur(int id, String pseudo, Connection connexion, CarteTest carte){
        this.id=id;
        this.pseudo=pseudo;
        this.connexion=connexion;
        this.carte=carte;
    }
    
    // Get
    
    public int getId(){
        return this.id;
    }
    
    public String getPseudo(){
        return this.pseudo;
    }
    
    public Connection getConnexion(){
        return this.connexion;
    }
    
    public int[]getLocalisationJoueur() {    // Récupérer les donnnées de la BDD sous la forme {x,y}
        try {
            PreparedStatement requete = this.connexion.prepareStatement("SELECT positionX, positionY FROM partie WHERE IdJoueur="+this.id+";");
            ResultSet resultat = requete.executeQuery();
            int[] a={0,0};
            while (resultat.next()) {
                double positionX = resultat.getDouble("positionX");
                double positionY = resultat.getDouble("positionY");
                a[0]=(int) positionX;
                a[1]=(int) positionY;
            }

            requete.close();
        return a; 
        } catch (SQLException ex){
            int[] b = {0,0};
            return b;
        }
    }
    
    public int getX() {  // Récuper le x du joueur
        return this.getLocalisationJoueur()[0];
    }
    
    public int getY() {   // Récupérer le y du joueur
        return this.getLocalisationJoueur()[1];
    }
    
    public CarteTest getCarte(){    
        return this.carte;
    }
    
    //Set
    
    public void setId(int nouvelId){
        this.id=nouvelId;
    }
    
    public void setLocalisationJoueur(int[] nouvelleLocalisation) { // Modifier la localisation du joueur dans la BDD
        try {
        double x =(int)nouvelleLocalisation[0];
        double y =(int)nouvelleLocalisation[1];
            PreparedStatement requete = connexion.prepareStatement("UPDATE partie SET positionX=?, positionY=? WHERE IdJoueur="+this.id);
            requete.setDouble(1, x);
            requete.setDouble(2, y);
            System.out.println(requete);
            requete.executeUpdate();

            requete.close();
            //connexion.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void setPseudo(String nouveauPseudo){
        this.pseudo=nouveauPseudo;
    }
    
    public void faireApparaitreJoueurSurLaCarte(){
        if (this.carte.getCase(new int[] {this.getX(),this.getY()})==0){
            this.carte.setCase(new int[] {this.getX(),this.getY()}, 2);
                }
    }
    
    
    public boolean peutTIlSedeplacerADroite() {         // Test s'il n'y a pas un chemin ou un bords de carte dans la direction voulue
        if (this.carte.getDimension()[1]-2<this.getY()){
            return false;
        }
        ArrayList<Integer> listeCasesAutorisees= new ArrayList();
        listeCasesAutorisees.add(0);  //Ensemble des cases où le déplacement du personnage est autorisé, aujourd'hui seulement la case "0" correspondant à du vide
        int[] caseDeDroite=new int[]{this.getX(),this.getY()+1};
        if (listeCasesAutorisees.contains(this.carte.getCase(caseDeDroite))){
            return true;
        }
        return false;
        }
    
    public void deplacementADroite() {          // Déplacement sur la carte et la BDD
        int[] caseDeDroite=new int[]{this.getX(),this.getY()+1};
        if (this.peutTIlSedeplacerADroite()){
            this.carte.setCase(this.getLocalisationJoueur(), 0);
            this.setLocalisationJoueur(caseDeDroite);
            this.carte.setCase(this.getLocalisationJoueur(), 2);
        }
    }
    
        public boolean peutTIlSedeplacerAGauche() {// Test s'il n'y a pas un chemin ou un bords de carte dans la direction voulue
        if (1>this.getY()){
            return false;
        }
        ArrayList<Integer> listeCasesAutorisees= new ArrayList();
        listeCasesAutorisees.add(0);
        int[] caseDeGauche=new int[]{getX(),this.getY()-1};
        if (listeCasesAutorisees.contains(this.carte.getCase(caseDeGauche))){
            return true;
        }
        return false;
        }
    
    public void deplacementAGauche() {          // Déplacement sur la carte et la BDD
        int[] caseDeGauche=new int[]{this.getX(),this.getY()-1};
        if (this.peutTIlSedeplacerAGauche()){
            this.carte.setCase(this.getLocalisationJoueur(), 0);
            this.setLocalisationJoueur(caseDeGauche);
            this.carte.setCase(this.getLocalisationJoueur(), 2);
        }
    }
    
    public boolean peutTIlSedeplacerEnBas() {// Test s'il n'y a pas un chemin ou un bords de carte dans la direction voulue
        if (this.carte.getDimension()[0]-2<this.getY()){
            return false;
        }
        ArrayList<Integer> listeCasesAutorisees= new ArrayList();
        listeCasesAutorisees.add(0);
        int[] caseDuBas=new int[]{this.getX()+1,this.getY()};
        if (listeCasesAutorisees.contains(this.carte.getCase(caseDuBas))){
            return true;
        }
        return false;
        }
    
    public void deplacementEnBas() {            // Déplacement sur la carte et la BDD
        int[] caseDuBas=new int[]{this.getX()+1,this.getY()};
        if (this.peutTIlSedeplacerEnBas()){
            this.carte.setCase(this.getLocalisationJoueur(), 0);
            this.setLocalisationJoueur(caseDuBas);
            this.carte.setCase(this.getLocalisationJoueur(), 2);
        }
    }
    
    public boolean peutTIlSedeplacerEnHaut() {// Test s'il n'y a pas un chemin ou un bords de carte dans la direction voulue
        if (1>this.getX()){
            return false;
        }
        ArrayList<Integer> listeCasesAutorisees= new ArrayList();
        listeCasesAutorisees.add(0);
        int[] caseDuHaut=new int[]{this.getX()-1,this.getY()};
        if (listeCasesAutorisees.contains(this.carte.getCase(caseDuHaut))){
            return true;
        }
        return false;
        }
    
    public void deplacementEnHaut() {       // Déplacement sur la carte et la BDD
        int[] caseDuHaut=new int[]{this.getX()-1,this.getY()};
        if (this.peutTIlSedeplacerEnHaut()){
            this.carte.setCase(this.getLocalisationJoueur(), 0);
            this.setLocalisationJoueur(caseDuHaut);
            this.carte.setCase(this.getLocalisationJoueur(), 2);
        }
    }
    

    
    public String dansQuelCampEstTIl(){  
        String equipe = "n'est pas inscrit dans la partie";
        try {
            PreparedStatement requete = connexion.prepareStatement("SELECT Couleur FROM équipe WHERE IdJoueur1="+this.id+"||IdJoueur2="+this.id+";");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
            String couleur = resultat.getString("Couleur");
                return couleur;
            }
            requete.close();
            
        } 
        catch (SQLException ex){
        }
       
        return equipe;
    }
    
    
    
    public double getElixirEquipe(){
        double Elixir = 0;
        try {
        
            PreparedStatement requete = connexion.prepareStatement("SELECT Elixir FROM équipe WHERE IdJoueur1="+this.id+"||IdJoueur2="+this.id+";");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
            Elixir = resultat.getDouble("Elixir");
                return Elixir;
            }
            requete.close();
            
        } 
        catch (SQLException ex){
        }
        return Elixir;
    }
    
    public void setElixir(double differenceElixir){ // Methode a completer modifiant la quantité d elixir de l equipe
        try {
        double elixirActuel= this.getElixirEquipe();

            PreparedStatement requete = connexion.prepareStatement("UPDATE équipe SET Elixir=? WHERE IdJoueur1="+this.id+"||IdJoueur2="+this.id);
            requete.setDouble(1, elixirActuel+differenceElixir);
            requete.executeUpdate();

            requete.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        
        
    }
    
    public boolean aiJeAssezDElixirPourFaireCa( double demandeElixir){  // Methode a completer pour savoir si j'ai assez d elixir (pour constuire une tour par exemple)
        if (this.getElixirEquipe()<demandeElixir){
            return false;
        }
        else{
            return true;
        } //
}    
}
