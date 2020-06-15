
package controller;


import Fanny.PartieRequête;
import Ibrahim.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nico
 */
public class gestionPartie {
        Connection connexion;
     public gestionPartie(Connection connexion){
         this.connexion=connexion;
     }
    
    public void disparitionMonstre(int ID){
        try {
        PreparedStatement requete = connexion.prepareStatement("DELETE FROM monstre WHERE IdMonstre = ?");
            requete.setInt(1, ID);
            System.out.println(requete);
            requete.executeUpdate();

            requete.close();
            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actionMonstre(){
        Database BDD = new Database();
        int n = listeIdMonstre(BDD).size();
        for (int i=0;i<n;i++){//Pour tout les monstres de la BDD:
            Monstre monstre = new Monstre(listeIdMonstre(BDD).get(i));
//            //Si dureeEffet(2)>0
//                //On inflige dureeEffet(3) à la vie du monstre
//                //dureeEffet(2)--;
//            //Si dureeEffet(1)>0
//                //On rajoute 0 au compteur de vitesse dans la BDD d'action
//                //dureeEffet(1)--;
//            //Sinon
//                //Si dureeEffet(0)>0
//                    //On rajoute 0.6 au compteur de vitesse dans la BDD d'action
//                    //dureeEffet(0)--;
//                //Sinon
//                    ////On rajoute 1 au compteur de vitesse dans la BDD d'action
            monstre.setVitesseDAO(monstre.getVitesseDAO()+1);
            double vitesseactuelle = monstre.getVitesseDAO();
            double vitessecatalogue = monstre.getVitesseCatalogueDAO();//On compare avec le compteur dans la BDD catalogue
            if (vitesseactuelle>=vitessecatalogue){//Si le compteur actif >= compteur catalogue
                monstre.setVitesseDAO(0);//On met à 0 le compteur
                monstre.Avancer();}//On avance le monstre
            if (monstre.TestVie()){//Si la vie du monstre est négative:
                disparitionMonstre(ListeIdMonstres().get(i));}//disparitionMonstre(ID);
            if (monstre.TestChateau()){//Si le monstre a atteint le château
                monstre.attaqueChateau();//enleverVieChateau(equipe,ID);
                disparitionMonstre(ListeIdMonstres().get(i));}//disparitionMonstre(ID);
        }
    }

//    public void nouvelleTour(){
//        //On met à 0 le compteur de vitesse
//        //On rajoute la  tour à la liste des tours de la BDD 
//    }
    public void actionTour(){
        int n = ListeIdTours().size();
        for (int i=0;i<n;i++){//Pour toutes les tours de la BDD
            Tour tour = new Tour(ListeIdTours().get(i));// On rajoute 1 au compteur de vitesse dans la BDD d'action
            tour.setVitesseDAO(tour.getVitesseDAO()+1);
            int vitesseactuelle = tour.getVitesseDAO();
            int vitessecatalogue = tour.getVitesseCatalogueDAO();//On compare avec le compteur dans la BDD catalogue
            if (vitesseactuelle>=vitessecatalogue){//Si le compteur actif >= compteur catalogue
                tour.setVitesseDAO(0);//On met à 0 le compteur
                if (tour.getEquipeDAO()=="Rouge"){
                    ArrayList<Integer> listeDeTousLesMonstreRougesOuBleu = ListeIdMonstreBleus();
                }else{
                    ArrayList<Integer> listeDeTousLesMonstreRougesOuBleu = ListeIdMonstreRouges();
                }
                tour.action(listeDeTousLesMonstreRougesOuBleu);//On avance le monstre
            // On compare le compteur dans la BDD catalogue
            //Si ils sont égaux 
                //On met à 0 le compteur
                //Si le type de la tour est 1 (degats de zone) Tour classique tour précise
                    //On enlève la vie aux n monstres de la BDD de l'équipe adverse
                //Si le type de la tour est 2 (ralentissement)
                    //dureeEffet(0) = 4 pour les n monstres les plus avancés
                //Si le type de la tour est 3 (steun)
                    //dureeEffet(1) = 2 pour les n monstres les plus avancés
                //Si le type de la tour est 4 (dégâts continus)
                    //dureeEffet(2) = 3 pour les n monstres les plus avancés
                    //dureeEffet(3) = 10 pour les n monstres les plus avancés
            }
        }
                
    }
    public Monstre creationMonstre(int Id){
        Monstre monstre=new Monstre (connexion,Id,"FauxMonstre",0.0,0.0,"Blanc",0.0,0,0,0); // On créé un faut monstre pour les sorties Catch
        try{
            PreparedStatement requete = connexion.prepareStatement("SELECT Description,PositionX,PositionY,Equipe,Vitesse,PdV,Direction,Avancée FROM monstre WHERE IdMonstre="+Id +";");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                
                // On récupère les données
                String  Description= resultat.getString("Description");                
                Double PositionX=resultat.getDouble("PositionX");
                Double PositionY=resultat.getDouble("PositionY");
                String Equipe=resultat.getString("Equipe");
                Double Vitesse=resultat.getDouble("Vitesse");
                int PdV=resultat.getInt("PdV");
                int Direction=resultat.getInt("Direction");                
                int Avance=resultat.getInt("Avancée");
                

                //On modifie les données du monstre créé à partir de celles récupérer
                monstre.setIdMonstre(Id);
                monstre.setDescription(Description);
                monstre.setPositionX(PositionX);
                monstre.setPositionY(PositionY);
                monstre.setEquipe(Equipe);
                monstre.setVitesse(Vitesse);
                monstre.setPdv(PdV);
                monstre.setDirection(Direction);
                
               
                }
            requete.close();
            return monstre; 
        }catch(SQLException ex){
            System.out.println("erreur dans la recherche du monstre");
            return monstre; 
            // IM-reponse : ex.printStackTrace();
 
        }
    }
    
    public Tour creationTour(int Id){
        Tour tour=new Tour (Id,"FausseTour",0.0,0.0,"Blanc",0.0,0.0,0.0,0.0,0.0); //Commentaire question : comment gérer les catchs dans ce type de méthode
        try{
            PreparedStatement requete = connexion.prepareStatement("SELECT Description,PositionX, PositionY, Equipe, Niveau, PdV, Portée, FréquenceDeTir, Dégât FROM tour WHERE IdTour="+Id +";");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                String  Description= resultat.getString("Description");
                Double X=resultat.getDouble("PositionX");
                Double Y=resultat.getDouble("PositionY");
                String equipe=resultat.getString("Equipe");
                Double Niveau= resultat.getDouble("Niveau");
                Double PdV=resultat.getDouble("PdV");
                Double Portee=resultat.getDouble("Portée");
                Double FrequenceDeTir=resultat.getDouble("FréquenceDeTir");
                Double Degat=resultat.getDouble("Dégât");
                

                
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
    public ArrayList<Integer> listeIdMonstre (Database baseDeDonnées){
        ResultSet resultat =  baseDeDonnées.executeQuery("SELECT IdMonstre FROM monstre");
        ArrayList<Integer> listeIdMonstre = new ArrayList<Integer>();
        int liste = 0;
        try {
            while(resultat.next()){
                liste = resultat.getInt("IdMonstre");  
                listeIdMonstre.add(liste);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartieRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listeIdMonstre;
    }
    
    public ArrayList<Integer> listeIdMonstreRouge (Database baseDeDonnées){
        ResultSet resultat =  baseDeDonnées.executeQuery("SELECT IdMonstre FROM monstre WHERE Equipe = 'Rouge'");
        ArrayList<Integer> listeIdMonstre = new ArrayList<Integer>();
        int liste = 0;
        try {
            while(resultat.next()){
                liste = resultat.getInt("IdMonstre");  
                listeIdMonstre.add(liste);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartieRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listeIdMonstre;
    }
    
    public ArrayList<Integer> listeIdMonstreBleu (Database baseDeDonnées){
        ResultSet resultat =  baseDeDonnées.executeQuery("SELECT IdMonstre FROM monstre WHERE Equipe = 'Bleue'");
        ArrayList<Integer> listeIdMonstre = new ArrayList<Integer>();
        int liste = 0;
        try {
            while(resultat.next()){
                liste = resultat.getInt("IdMonstre");  
                listeIdMonstre.add(liste);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartieRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listeIdMonstre;
    }
    public ArrayList<Integer> listeIdTour (Database baseDeDonnées){
        ResultSet resultat =  baseDeDonnées.executeQuery("SELECT IdTour FROM tour");
        ArrayList<Integer> listeIdTour = new ArrayList<Integer>();
        int liste = 0;
        try {
            while(resultat.next()){
                liste = resultat.getInt("IdTour");  
                listeIdTour.add(liste);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartieRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listeIdTour;
    }
}
