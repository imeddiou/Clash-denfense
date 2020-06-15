/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

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
//    public void enleverVieChateau(int equipe, int ID){
//        //récupérer la vie du château
//        //récupérer l 'atk du monstre avec l'ID dans la BDD
//        //Faire la différence
//        disparitionMonstre(ID);
//    }

    //public void nouveauMonstre(){
        //On met à 0 le compteur de vitesse
        //On iniitialise sa postion selon l'équipe
        //On rajoute le monstre à la liste des monstres de la BDD
    //}
    
    public void actionMonstre(){
        int n = ListeIdMonstres().size();
        for (int i=0;i<n;i++){//Pour tout les monstres de la BDD:
            Monstre monstre = new Monstre(ListeIdMonstres().get(i));
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
            int vitesseactuelle = monstre.getVitesseDAO();
            int vitessecatalogue = monstre.getVitesseCatalogueDAO();//On compare avec le compteur dans la BDD catalogue
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

}
