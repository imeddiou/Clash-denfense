/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static java.lang.Math.random;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author PC d'Adrien
 */
public class Attaquant extends Joueur{
    
    public Attaquant(int id, String pseudo, Connection connexion, CarteTest carte) {
        super(id, pseudo, connexion, carte);
    }

//_____________________________________________________________________________________________________________________________________________________
//-----------------------------------------------------------------------------------------------------------------------------------------------------    
//_____________________________________________________________________________________________________________________________________________________
    
    // On cherche l'IdMax de Monstre actuellemnt dans la BDD de combat pour ajouter un monstre à la fin de celle-ci
        public int IdMaxMonstre() throws SQLException{      
        try {
            int maxId=0;
            PreparedStatement requete = connexion.prepareStatement("SELECT IdMonstre FROM monstre ;");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                int IdTour = resultat.getInt("IdMonstre");
                int a=IdTour;
                if (a>maxId){
                    maxId=a;
                }
            }
            requete.close();
            return maxId;
        }catch (SQLException ex) {
            System.out.println("Erreur : l'IdMax n'a pas pu être trouvé");
            return 0;
        }
    }
    //_________________________________________________________________________________________________________________________________
    //---------------------------------------------------------------------------------------------------------------------------------
    //_________________________________________________________________________________________________________________________________
        
        
    // On cherche le coût en Elixir d'un type de monstre dans le catalogue (les types de monstre : 1 = Type Chevalier, 2 = Type Gobelin, 3 = Géant)
        public int coutMonstreType(int idTypeDeMonstre){  // 1 pour chevalier, 2 pour gobelin, 3 pour géant
            int idMonstre=0;
            int coutMonstre=0;
            if (idTypeDeMonstre==1){  // On détermine la correspondance entre l'IdType de Monstre et l'ID Catalogue
                idMonstre=100;
            }
            if (idTypeDeMonstre==2){// On détermine la correspondance entre l'IdType de Monstre et l'ID Catalogue
                idMonstre=300;
            }
            if (idTypeDeMonstre==3){// On détermine la correspondance entre l'IdType de Monstre et l'ID Catalogue
                idMonstre=500;
            }
            // On a maintenant IdMonstre qui correspond à l'Id du type de Monstre
            try {
                PreparedStatement requete = connexion.prepareStatement("SELECT coût FROM catalogueMonstre WHERE IdMonstre="+idMonstre+";");   // On cherche maintenant le coût en Elixir de ce monstre dans le catalogue
                ResultSet resultat = requete.executeQuery();
                while (resultat.next()) {
                    coutMonstre = resultat.getInt("coût");
                }

                requete.close();
                } catch (SQLException ex) {
                    System.out.println("Erreur : le coût du monstre n'a pas pu être trouvé");
                    ex.printStackTrace();
                    }
            
            return coutMonstre;
        }
        
//_______________________________________________________________________________________________________________________________________________
//-----------------------------------------------------------------------------------------------------------------------------------------------
//_______________________________________________________________________________________________________________________________________________

// On cherche ici à extraire les données du catalogue de monstre permettant de créer un monstre (de la classe Monstre)        
    public Monstre extraireMonstreDuCatalogueMonstre(int IdMonstre){
        Monstre monstre=new Monstre (this.connexion,IdMonstre,"FauxMonstre",0.0,0.0,"Blanc",0.0,0,0,0); // On créé un faut monstre pour les sorties Catch
        try{
            PreparedStatement requete = connexion.prepareStatement("SELECT Description,PositionX,PositionY,Equipe,Vitesse,PdV,Direction,Avancée FROM catalogueMonstre WHERE IdMonstre="+IdMonstre +";");   // On cherche l'ensemble des caractéristiques de la classe Monstre
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
                
                //On crééer l'ID le plus grand des monstres au combat pour ne pas avoir de doubblons d'ID
                int Id=this.IdMaxMonstre()+1;

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
            System.out.println("erreur dans la recherche du monstre dans le catalogue");
            return monstre; 
            // IM-reponse : ex.printStackTrace();
 
        }
    }
    
//_____________________________________________________________________________________________________________________________________________________
//-----------------------------------------------------------------------------------------------------------------------------------------------------
//_____________________________________________________________________________________________________________________________________________________

//On choisit un monstre et on l'introduit dans la BDD de monstre au combat    
    public void introduireMonstreDansLaBDD(Monstre monstre0){
        try {
            PreparedStatement requete = connexion.prepareStatement("INSERT INTO monstre VALUES (?,?,?,?,?,?,?,?,?)");
            requete.setInt(1, monstre0.getIdMonstre());
            requete.setString(2, monstre0.getDescription());
            requete.setDouble(3, monstre0.getPositionX());
            requete.setDouble(4, monstre0.getPositionY());
            requete.setString(5, monstre0.getEquipe());
            requete.setDouble(6, monstre0.getVitesse());
            requete.setDouble(7, monstre0.getPdv());
            requete.setDouble(8,monstre0.getDirection());
            requete.setDouble(9,monstre0.getAvancee());
            System.out.println(requete);
            requete.executeUpdate();

            requete.close();
            //connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//____________________________________________________________________________________________________________________________________________________
//----------------------------------------------------------------------------------------------------------------------------------------------------
//____________________________________________________________________________________________________________________________________________________

    
    public void apparitionMonstre (int idTypeMonstre, String couleur){   // On va chercher une tour dans le catalogue, IdType de monstre (1=Chevalier, 2=Gobelin, 3=géant), couleur : Bleu, Rouge
        if (this.aiJeAssezDElixirPourFaireCa(this.coutMonstreType(idTypeMonstre))){ // On vérifie que l'équipe a assez d'Elixir pour fair eapparaître un monstre
            int idMonstre=0;
            if(idTypeMonstre==1){ 
                idMonstre=100;
            }
            if (idTypeMonstre==2){
                idMonstre=300;
            }
            if(idTypeMonstre==3){
                idMonstre=500;
            }
            // Grâce aux 3 if précèdemments on a fait correspondre l'ID de Type à un Id de catalogue
            System.out.println("IdMonstre="+idMonstre);
            if(couleur=="Rouge"){
                idMonstre=idMonstre+100;
            } 
            // Grâce au if précèdent on a modifié l'Id du catalogue si l'attaquant qui place le monstre est rouge
            
            Monstre monstre=this.extraireMonstreDuCatalogueMonstre(idMonstre); // On récupère un monstre dans le catalogue
            monstre.setPositionX(monstre.getPositionX()+(((int)(3*random()))-1)*1.0);       //Le monstre est par défaut au milieu du chemin créé et d'épaisseur 3, on modifie aléatoirement sa position pour déterminer si il est au milieu, à gauche ou a droite sur le chemin
            System.out.println(monstre.getPositionX());
            this.introduireMonstreDansLaBDD(monstre);                           // On introduit le monstre pour lequel on a modifié la position sur le chemin dans la BDD de combat
            this.setElixir(-this.coutMonstreType(idTypeMonstre));               // On enlève à l'équipe le coût en Elixir de la création de ce monstre      
        }       
    }
}
