/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import View.BoutonJouer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC d'Adrien
 */
public class Defenseur extends Joueur{
    
    public Defenseur(int id, String pseudo, Connection connexion, ArrayList<ArrayList<Integer>> carte) {
        super(id, pseudo, connexion, carte);
    }
    
    public boolean puisJeConstruireUneTourIci(int X, int Y){ // A terme c est la position du joueur qui doit ce trouver là mais pour le bon fonctionnement des tests je met des X et Y provisoire
        boolean reponse=false;
//        int[] caseDuJoueur= new int[] {X,Y}; // remplacer X et Y par this.getX et this.getY
//        ArrayList<Integer> listeCasesAutorisees= new ArrayList();
//        listeCasesAutorisees.add(0);
        ArrayList<ArrayList<Double>> listePositionTour = new ArrayList<ArrayList<Double>>();
        try {
            PreparedStatement requete = connexion.prepareStatement("SELECT PositionX,PositionY FROM tour ");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()){
                ArrayList<Double> XY =  new ArrayList<Double>();
                XY.add(resultat.getDouble(1));
                XY.add(resultat.getDouble(2));
                listePositionTour.add(XY);
            }
            ArrayList<Double> XYaverifier=new ArrayList<Double>();            
            XYaverifier.add((double)X);
            XYaverifier.add((double)Y);
            if (!listePositionTour.contains(XYaverifier)){
                if(carte.get(Y).get(X)==0){
                    reponse=true;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Defenseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        

//        if (listeCasesAutorisees.contains(carte.get(Y).get(X))){
//            if (X>=0){ // remplacer X et Y par this.getX et this.getY
//                if (X<this.getCarte().get(0).size()){ // remplacer X et Y par this.getX et this.getY
//                    if (Y>=0){ // remplacer X et Y par this.getX et this.getY
//                        if (Y<this.getCarte().get(0).size()){ // remplacer X et Y par this.getX et this.getY
//                        }
//                    }
//            
//                }
//                
//            }
//        }
        return reponse;
    }
    
    
    public void faireApparaitreTourSurLaCarte(int posXTour, int posYTour){  
        //int[] caseTour= new int[] {posXTour,posYTour};
        //this.carte.get(posYTour).set(posXTour, 4);
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
        Tour tour=new Tour (this.connexion,0,"FausseTour",0.0,0.0,"Blanc",0.0,0.0,0.0,0.0,0.0); //Commentaire question : comment gérer les catchs dans ce type de méthode
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
    
    public void introduireTourDansLaBDD(Tour tour0, String couleur){
        try {
            
            PreparedStatement requete = connexion.prepareStatement("SELECT MAX(IdTour) FROM tour");
            ResultSet resultat = requete.executeQuery();
            int idTourMax = 0;
            while (resultat.next()){
                idTourMax = resultat.getInt(1);
            }
            requete = connexion.prepareStatement("INSERT INTO tour VALUES (?,?,?,?,?,?,?,?,?,?)");
            requete.setInt(1, idTourMax+1);
            requete.setString(2, tour0.getDesignation());
            requete.setDouble(3, tour0.getX());
            requete.setDouble(4, tour0.getY());
            requete.setString(5, couleur);
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
    

    
    
    
    public void construireUneTourIci (int idTypeTour, String couleur){   // idTypeTour 1=classique 2 = précise 3= incendiaire
        if (this.aiJeAssezDElixirPourFaireCa(this.coutTourType(idTypeTour))){ 
            Tour tour=this.extraireTourDuCatalogueTour(idTypeTour);
            
            try {
                
                PreparedStatement requete = connexion.prepareStatement("SELECT PositionX,PositionY FROM partie WHERE IdJoueur="+this.id);
                ResultSet resultat = requete.executeQuery();
                Double posXTourDouble = 0.0;
                Double posYTourDouble = 0.0;
                while (resultat.next()) {
                    posXTourDouble= resultat.getDouble(1);
                    posYTourDouble= resultat.getDouble(2);
                }
                int posXTour = posXTourDouble.intValue();
                int posYTour = posYTourDouble.intValue();
                if(this.puisJeConstruireUneTourIci(posXTour,posYTour)){  
                    
                    this.setElixir(-this.coutTourType(idTypeTour));  
                    this.introduireTourDansLaBDD(tour, couleur);
                    this.faireApparaitreTourSurLaCarte(posXTour, posYTour);    
                requete.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erreur : Le cout de la tour n'a pas été trouvé");
                ex.printStackTrace();
            }
               
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
                    coutTour = resultat.getInt(1);
                }

                requete.close();
            } catch (SQLException ex) {
                System.out.println("Erreur : Le cout de la tour n'a pas été trouvé");
                ex.printStackTrace();
            }

            return coutTour;
        }   
}
    

