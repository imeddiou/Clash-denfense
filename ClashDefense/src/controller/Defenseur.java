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
public class Defenseur extends Joueur{
    
    public Defenseur(int id, String pseudo, Connection connexion, CarteTest carte) {
        super(id, pseudo, connexion, carte);
    }
    
    public boolean puisJeConstruireUneTourIci(int X, int Y){ // A terme c est la position du joueur qui doit ce trouver là mais pour le bon fonctionnement des tests je met des X et Y provisoire
        boolean reponse=false;
        int[] caseDuJoueur= new int[] {X,Y}; // remplacer X et Y par this.getX et this.getY
        ArrayList<Integer> listeCasesAutorisees= new ArrayList();
        listeCasesAutorisees.add(0);
        if (listeCasesAutorisees.contains(this.carte.getCase(caseDuJoueur))){
            if (X>=0){ // remplacer X et Y par this.getX et this.getY
                if (X<this.getCarte().getDimension()[0]){ // remplacer X et Y par this.getX et this.getY
                    if (Y>=0){ // remplacer X et Y par this.getX et this.getY
                        if (Y<this.getCarte().getDimension()[1]){ // remplacer X et Y par this.getX et this.getY
                            reponse= true;
                        }
                    }
            
                }
                
            }
        }
        return reponse;
    }
    
    
    public void faireApparaitreTourSurLaCarte(int posXTour, int posYTour){  
        int[] caseTour= new int[] {posXTour,posYTour};
        this.carte.setCase(caseTour, 4);
    }
    
        public int IdMaxTour() throws SQLException{
        try {
            int maxId=0;
            PreparedStatement requete = connexion.prepareStatement("SELECT IdTour FROM tour ;");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                int IdTour = resultat.getInt("IdTour");
                int a=IdTour;
                if (a>maxId){
                    maxId=a;
                }
            }
            requete.close();
            return maxId;
        }catch (SQLException ex) {
            return 0;
        }
    }
    
    public Tour extraireTourDuCatalogueTour(double IdTour){
        Tour tour=new Tour (0,"FausseTour",0.0,0.0,"Blanc",0.0,0.0,0.0,0.0,0.0); //Commentaire question : comment gérer les catchs dans ce type de méthode
        try{
            PreparedStatement requete = connexion.prepareStatement("SELECT Description, Niveau, PdV, Portée, FréquenceDeTir, Dégât FROM catalogueTour WHERE IdTour="+IdTour +";");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                String  Description= resultat.getString("Description");
                Double Niveau= resultat.getDouble("Niveau");
                Double PdV=resultat.getDouble("PdV");
                Double Portee=resultat.getDouble("Portée");
                Double FrequenceDeTir=resultat.getDouble("FréquenceDeTir");
                Double Degat=resultat.getDouble("Dégât");
                
                int Id=this.IdMaxTour()+1;
                Double X=this.getX()*1.0;
                Double Y=this.getY()*1.0;
                String equipe=this.dansQuelCampEstTIl();
                
                tour.setIdTour(Id);
                tour.setDesignation(Description);
                tour.setX(X);
                tour.setY(Y);
                tour.setEquipe(equipe);
                tour.setNiveau(Niveau);
                tour.setPdV(PdV);
                tour.setPortee(Portee);
                tour.setFrequenceDeTir(FrequenceDeTir);
                tour.setDegat(Degat);
               
                }
            requete.close();
            return tour; //Commentaire question : comment gérer les catchs dans ce type de méthode
        }catch(SQLException ex){
            System.out.println("Erreur : la tour n'a pas pu être extraite");
            return tour; //Commentaire question : comment gérer les catchs dans ce type de méthode
            // IM-reponse : ex.printStackTrace();
 
        }
    }
    
    public void introduireTourDansLaBDD(Tour tour0){
        try {
            PreparedStatement requete = connexion.prepareStatement("INSERT INTO tour VALUES (?,?,?,?,?,?,?,?,?,?)");
            requete.setInt(1, tour0.getIdTour());
            requete.setString(2, tour0.getDesignation());
            requete.setDouble(3, tour0.getX());
            requete.setDouble(4, tour0.getY());
            requete.setString(5, tour0.getEquipe());
            requete.setDouble(6, tour0.getNiveau());
            requete.setDouble(7, tour0.getVie());
            requete.setDouble(8,tour0.getPortee());
            requete.setDouble(9,tour0.getFrequenceDeTire());
            requete.setDouble(10, tour0.getDegat());
            System.out.println(requete);
            requete.executeUpdate();

            requete.close();
            //connexion.close();

        } catch (SQLException ex) {
            System.out.println("Erreur : la tour n'a pas pu etre insérée dans la base de donnée");
            ex.printStackTrace();
        }
    }
    

    
    
    
    public void construireUneTourIci (int idTypeTour, String couleur){   // On va chercher une tour dans le catalogue, TOUR NON MISE DANS LA BDD POUR L INSTANT, JUSTE SUR LA CARTE, PAS DE DELAI D ATTENTE POUR L INSTANT, A terme l'id serait de mettre ici l'ID de la tour catalogue
        if (this.aiJeAssezDElixirPourFaireCa(this.coutTourType(idTypeTour))){ // Pas de coût en Elixir pour la BDD pour l'instant
            Tour tour=this.extraireTourDuCatalogueTour(idTypeTour);
            //if(this.puisJeConstruireUneTourIci(posXTour,posYTour)){   
                this.setElixir(-this.coutTourType(idTypeTour));  
                // Manque la partie de patience du temps
                this.introduireTourDansLaBDD(tour);
                //this.faireApparaitreTourSurLaCarte(posXTour, posYTour);       
        //}       
    }
}

        public int coutTourType(int idTypeDeTour){  // 1 pour tourClassqiue, 2 pour toruPrécise, 3 pour tourIncendiaire
        int idTour=0;
        int coutTour=0;
            
        try {
            PreparedStatement requete = connexion.prepareStatement("SELECT coût FROM catalogueTour WHERE IdTour="+idTypeDeTour+";");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                coutTour = resultat.getInt("coût");
            }

            requete.close();
        } catch (SQLException ex) {
            System.out.println("Erreur : Le cout de la tour n'a pas été trouvé");
            ex.printStackTrace();
        }
            
    return coutTour;
}
}
    

