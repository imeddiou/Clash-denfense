/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Archive_des_classes.JoueurRequête;
import Archive_des_classes.PartieRequête;
import Archive_des_classes.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author PC d'Adrien
 */
public class Monstre { //essai
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
//            System.out.println(requete);
            requete.executeUpdate();
            requete.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
   }

   public void attaqueChateau(){   // A terme il faut récupérer l'attauqe dans la BDD
       try{
       double attaque = this.getAttaqueDAO();
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
    
    // Partie Nicolas
    
    public boolean TestVie(){//Cette classe permet de vérifier si le monstre est encore en vie
        int pdv = this.getPdVDAO();//On récupère la vie à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        if (pdv<=0){return true;}//Si la vie est inférieure à 0 on déclare que le monstre est mort
        return false;
    }
    
    public boolean TestChateau(){//Cette classe permet de vérifier si le monstre a atteint le château
        int[] coordonnees=this.getCoordonneesDAO();//On récupère les coordonnées à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        if(this.getEquipeBDD()=="Rouge"){
            if (coordonnees[1]==19){//Si l'ordonnée du monstre est celle du chêteau alors on déclare que le monstre a atteint le château
                return true;
            }
        }
        //le ==0 a été choisi arbitrairement ici, et il faudra faire attention à l'équipe à laquelle appartient le monstre. Pour l'autre équipe ce 0 serait ici un 19
        if (this.getEquipeBDD()=="Bleue"){
            if (coordonnees[1]==0){
                return true;
            }
        }
        return false;
    }
    

    
    public boolean MurDevant(){//Cette classe permet de vérifier s'il y a un mur ou un chemin qui n'est pas le bon devant le monstre 
        int[] coordonnees=this.getCoordonneesDAO();//On récupère les coordonnées à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int direction=this.getDirectionDAO();//On récupère la direction à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int vecteur[][] = {{1,0},{0,1},{-1,0},{0,-1}};
        // vecteur[0] est le déplacement vers la droite
        // vecteur[1] est le déplacement vers le bas
        // vecteur[2] est le déplacement vers la gauche
        // vecteur[3] est le déplacement vers le haut
        int sens[] = vecteur[direction];//Le principe du vecteur est détaillé plus loin, il permet de récupérer la direction du monstre
        System.out.println("Sens x= "+sens[0]);
        System.out.println("Sens y= "+sens[1]);
        System.out.println("Test x = "+coordonnees[0]+sens[0]);
        System.out.println("Test y = "+coordonnees[1]+sens[1]);
        if (this.getMAP((int)(coordonnees[0]+sens[0]),(int)(coordonnees[1]+sens[1]))!=this.getMAP((int)(coordonnees[0]),(int)(coordonnees[1]))){return true;}//Si les coordonnées du monstre à l'étape suivante ne sont pas sur le bon chemin, alors on déclare qu'il y a un mur
        return false;
    }
    
    public boolean FautIlAllerADroite(){//Cette classe permet de savoir dans quel sens se tourner si il y'a un mur devant
        int[] coordonnees=this.getCoordonneesDAO();//On récupère les coordonnées à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int direction=this.getDirectionDAO();//On récupère la direction à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int vecteur[][] = {{1,0},{0,1},{-1,0},{0,-1}};
        // vecteur[0] est le déplacement vers la droite
        // vecteur[1] est le déplacement vers le bas
        // vecteur[2] est le déplacement vers la gauche
        // vecteur[3] est le déplacement vers le haut
        int[] sensDroite = vecteur[(direction+1)%4];//Le principe du vecteur est détaillé plus loin, mais ici on récupère la direction à droite du monstre
        int testDroite=this.getMAP((int)(coordonnees[0]+sensDroite[0]),(int)(coordonnees[1]+sensDroite[1]));//On stocke dans une variable temporaire les coordonnées du monstre s'il se dirigeait maintenant sur la droite
        int testChemin=this.getMAP((int)(coordonnees[0]),(int)(coordonnees[1]));
        if (testDroite==testChemin){return true;}//Si les coordonnées du monstre s'il se dirigeait maintenant sur la droite sont sur le bon chemin, alors on déclare qu'il faut tourner à droite
        else{return false;}//sinon qu'il faut tourner à gauche
    }
    
//    public int getMAP(int ncolonne,int nligne){
//        Archive_des_classes.Database baseDeDonnées = new Database();
//        ArrayList<ArrayList<Integer>> Map = new ArrayList<ArrayList<Integer>>();
//        if(nligne>19 || nligne<0){
//            return(-10);
//        }
//        if(ncolonne>19 || ncolonne<0){
//            return(-10);
//        }
//        try{
//            baseDeDonnées.connect();  
//            PartieRequête requete = new PartieRequête();
//            Map = requete.partieRequêteSelectMapAsMatrix(baseDeDonnées);
//            baseDeDonnées.disconnect();
//        }catch (SQLException ex) {
//            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Erreur getMap");
//        }
//        return Map.get(nligne).get(ncolonne);
//    }
    
        public int getMAP(int ncolonne,int nligne){
                        ArrayList<ArrayList<Integer>> mapFinal = new ArrayList<ArrayList<Integer>>();
         try {
             PreparedStatement requete = connexion.prepareStatement("SELECT DISTINCT Map FROM partie ;");
            ResultSet resultat = requete.executeQuery();
            
            String mapString = "";
            
            while (resultat.next()) {
           

                  mapString = resultat.getString("Map");
                  String[] mapStringSplitted = mapString.split(" ");
                  
                   for (int i=0;i<20;i++){
            ArrayList<Integer> ligneMap = new ArrayList<Integer>();
            for (int j=0;j<20;j++){
                ligneMap.add(Integer.parseInt(mapStringSplitted[i*20+j]));}
            mapFinal.add(ligneMap);
         }
            for(int i=0;i<20;i++){
                //System.out.println(mapFinal.get(4).get(i)) ;  
            }       
                

                }
            
            
            
            requete.close();
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
   
        return mapFinal.get(nligne).get(ncolonne);
    }
    
    
    
    public void Avancer(){//Cette classe est la classe qui fait avancer le monstre
        int[] coordonnees=this.getCoordonneesDAO();//On récupère les coordonnées à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int avancee=this.getAvanceeDAO();//On récupère l'avancée à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int direction=this.getDirectionDAO();//On récupère la direction à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int vecteur[][] = {{1,0},{0,1},{-1,0},{0,-1}};
        // vecteur[0] est le déplacement vers la droite
        // vecteur[1] est le déplacement vers le bas
        // vecteur[2] est le déplacement vers la gauche
        // vecteur[3] est le déplacement vers le haut
        if(this.MurDevant()){//On regarde s'il y a un mur devant, et si oui:
            System.out.println("Entre dans mur Devant");
            if (this.FautIlAllerADroite()==true){direction=(direction+1)%4;//Si la voie à droite est libre alors on se tourne d'un cran sur la droite
            System.out.println("Décide d'aller à droite");
            }else{direction=(direction+3)%4;
            System.out.println("décide d'aller à gauche");}//Sinon on se tourne d'un cran sur la gauche 
        }
        int nouvellesCoordonnees[]={coordonnees[0]+vecteur[direction][0],coordonnees[1]+vecteur[direction][1]};//On récupère alors les nouvelles coordonnées du monstre 
        System.out.println("x = "+nouvellesCoordonnees[0]);
        System.out.println("y = "+nouvellesCoordonnees[1]);
        System.out.println("Direction = "+direction);
        this.setCoordonneesDAO(nouvellesCoordonnees[0],nouvellesCoordonnees[1]);////Et on les implémente avec le setter, car c'est ainsi qu'il faudra faire avec la BDD
        this.setDirectionDAO(direction);//De même on met à jour la direction
        avancee=this.getAvanceeDAO()+1;//On incrémente l'avancée puisque le monstre a fait un pas de plus
        this.setAvanceeDAO(avancee);//Et on met à jour l'avancée du monstre
    }
    
    public int[] getCoordonneesDAO(){
        int[] coordonnees={0,0};
        try {
            //Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");

            PreparedStatement requete = this.connexion.prepareStatement("SELECT PositionX, PositionY FROM monstre WHERE IdMonstre="+this.idMonstre+";");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                double positionX = resultat.getDouble("positionX");
                double positionY = resultat.getDouble("positionY");
                coordonnees[0]=(int) positionX;
                coordonnees[1]=(int) positionY;
            }

            requete.close();
            //connexion.close();
       
        } catch (SQLException ex){
        }
        return coordonnees;
    }
    
    public int getDirectionDAO(){
        int direction = 0;
        try {
            PreparedStatement requete = connexion.prepareStatement("SELECT Direction FROM Monstre WHERE IdMonstre="+this.idMonstre+" ;");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                direction=resultat.getInt("Direction");
                }      
            
            requete.close();
//            connexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return direction;
    }
    
    public int getAvanceeDAO(){
        int avancee=0;
        try {
            PreparedStatement requete = connexion.prepareStatement("SELECT Avancee FROM Monstre WHERE IdMonstre="+this.idMonstre+" ;");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                avancee=resultat.getInt("Avancee");
                }
            
            requete.close();
//            connexion.close();
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return avancee;
    }
    
    public void setAvanceeDAO(int mouvement){
        try {
 
            PreparedStatement requete = connexion.prepareStatement("UPDATE monstre SET Avancee=? WHERE IdMonstre="+this.idMonstre);
            requete.setInt(1, this.getAvancee()+mouvement);
            //System.out.println(requete);
            requete.executeUpdate();

            requete.close();
//            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    public int getPdVDAO(){
        int PdV=0;    
        try {

            PreparedStatement requete = connexion.prepareStatement("SELECT PdV FROM Monstre WHERE IdMonstre="+this.idMonstre+" ;");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                PdV=resultat.getInt("PdV");
                }
            
            
            
            requete.close();
//            connexion.close();
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return PdV;
}
public void setCoordonneesDAO(int X, int Y){
        try {

            PreparedStatement requete = connexion.prepareStatement("UPDATE monstre SET PositionX=?,PositionY=? WHERE IdMonstre="+this.idMonstre);
            requete.setInt(1, X);
            requete.setInt(2,Y);
            requete.executeUpdate();

            requete.close();
//            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    
    }
    
    public double getVitesseCatalogueDAO(){
        double vitesse =0;
                try {

            PreparedStatement requete = connexion.prepareStatement("SELECT Vitesse FROM catalogueMonstre WHERE Description='"+this.description+"' ;");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                vitesse =resultat.getDouble("Vitesse");
//                System.out.println(vitesse);
                }
            
            
            
            requete.close();
//            connexion.close();
            

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erreur");
        }
        return vitesse;
    }
    
    public int getAttaqueDAO(){
        int attaque = 0;
                try {

            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");

            PreparedStatement requete = connexion.prepareStatement("SELECT Attaque FROM catalogueMonstre WHERE Description='"+this.description+"' ;");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                attaque =resultat.getInt("Attaque");
                }
            
            
            
            requete.close();
//            connexion.close();
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return attaque;
    }
   public double getVitesseDAO(){
        double vitesse=0;
            try {

            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");

            PreparedStatement requete = connexion.prepareStatement("SELECT Vitesse FROM monstre WHERE IdMonstre="+this.idMonstre+" ;");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                vitesse=resultat.getDouble("Vitesse");
            }
            
            
            
            requete.close();
//            connexion.close();
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return vitesse; 
    }
    
    public void setVitesseDAO(double nouvelleVitesse){
                try {

            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");

            PreparedStatement requete = connexion.prepareStatement("UPDATE monstre SET Vitesse=? WHERE IdMonstre="+this.idMonstre);
            requete.setDouble(1, nouvelleVitesse);
            requete.executeUpdate();

            requete.close();
//            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void setPdVDAO(double nouvellePdV){
                try {

            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");

            PreparedStatement requete = connexion.prepareStatement("UPDATE monstre SET PdV=? WHERE IdMonstre="+this.idMonstre);
            requete.setDouble(1, nouvellePdV);
//            System.out.println(requete);
            requete.executeUpdate();

            requete.close();
//            connexion.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

        public void Affichage(){//Cette classe affiche le monstre
        int[] coordonnees=this.getCoordonneesDAO();//On récupère les coordonnées à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int i = (int)coordonnees[0];//on utilise i pour simplifier la suite
        int j = (int)coordonnees[1];//on utilise j pour simplifier la suite
        for(int x=0;x<20;x++){
            for(int y=0;y<20;y++){//On balaye la map
                if (i==x && j==y){System.out.print("M");}//Si on est sur les coordonnées du monstre alors on affiche le monstre, ici à l'aide d'un M
                else{
                    System.out.print(-this.getMAP(x,y));
                    }
            }
            System.out.println();}
    System.out.println();        
    }
   
        public void setDirectionDAO(int nouvelleDirection){
            int direction=this.getDirectionDAO();
            try{
                       PreparedStatement requete = connexion.prepareStatement("UPDATE monstre SET Direction=? WHERE IdMonstre="+this.idMonstre);
            requete.setDouble(1, nouvelleDirection);

            requete.executeUpdate();

            requete.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    
    }  
        }
