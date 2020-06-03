package controller;

import Nico.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

public class TowerBOUM { //IM : au lieu de creer une connexion à chaque fois, le mieux est de définir une variable Database et d'établir une connexion dans le constructeur
    private int idTour;
    
    //private int vie=1;
    //private double rayon;
    //private int degat=1;
    //private double vitesse;
    //private int taille=0;
    //private double coordonnees[];
    /*private int niveau;
    private int steun;
    private int degatDeZone;*/
    
    public TowerBOUM(int idTour){
        this.idTour=idTour;
    }
    
    public double DistanceTowerMonstre(double[] coordonneesMonstre)throws Exception{
        double[] coordonnees = this.getCoordonnees();
        double x = coordonnees[0];
        double y = coordonnees[1];
        double X = coordonneesMonstre[0];
        double Y = coordonneesMonstre[1];
        double distance = Math.sqrt(Math.pow(X-x,2)+Math.pow(Y-y,2));
        return distance;
    }  
    public boolean LeMonstreEstDansLeRayon(double[] coordonneesMonstre)throws Exception{
        double rayon = this.getRayon();
        if (this.DistanceTowerMonstre(coordonneesMonstre)<=rayon){return true;}
        return false;
    }
    
    public int idMonstreLePlusAvance()throws Exception{    
        int nombreDeMonstre = this.nombreDeMonstre();
        ArrayList<Integer> listeIdMonstre = new ArrayList<Integer>();
        ArrayList<Integer> listeAvancees = new ArrayList<Integer>();
        for (int i=1;i<=nombreDeMonstre;i++){
            int idMonstre=i;
            MSDC3 monstre = new MSDC3(idMonstre);
            double[] coordonneesMonstre = monstre.getCoordonnees();  
            if (this.LeMonstreEstDansLeRayon(coordonneesMonstre)){
                listeIdMonstre.add(idMonstre);
                int avancee = monstre.getAvancee();
                listeAvancees.add(avancee);
            }
        }
        if (listeIdMonstre.isEmpty()){return 0;}
        int indexMaxAvancee = listeAvancees.indexOf(Collections.max(listeAvancees));
        int idMonstreLePlusAvance =listeIdMonstre.get(indexMaxAvancee);
        return idMonstreLePlusAvance;
    }   
    public boolean testVie()throws Exception{
        int vie = this.getVie();
        if (vie<=0){return true;}return false;
    } 
    public void ApparitionTower(int carte[][])throws Exception{
        // Ce prgm n'affiche pas réellement le monstre, il change seulement les numéros dans la matrice qui le représentent
        double[] coordonnees = this.getCoordonnees();
        int taille = this.getTaille();
        double x=coordonnees[0];
        double y=coordonnees[1];
        // On récupère les coordonnées du monstre
        for(int i=(int)(x-taille);i<=(int)(x+taille);i++){
            for(int j=(int)(y-taille);j<=(int)(y+taille);j++){
                // Si le monstre est de taille autre que 0 il faut aussi modifier les cases qui l'entourent 
                carte[i][j]=3;
                // 3 sera ici le nombre qui définira la présence d'un monstre
            }
        }
    }       
    public void DisparitionTower(int carte[][])throws Exception{
        // Ce prgm ne fait pas disparaître réellement le monstre, il change seulement les numéros dans la matrice qui le représentent
        double[] coordonnees = this.getCoordonnees();
        int taille = this.getTaille();
        double x=coordonnees[0];
        double y=coordonnees[1];
        // On récupère les coordonnées du monstre
        for(int i=(int)(x-taille);i<=(int)(x+taille);i++){
            for(int j=(int)(y-taille);j<=(int)(y+taille);j++){
                // Si le monstre est de taille autre que 0 il faut aussi modifier les cases qui l'entourent 
                carte[i][j]=0;
                // 0 sera ici le nombre qui définira le par terre normal
            }
        }
    }     
    public int DefoncerLeMonstre(int carte[][])throws Exception{
        double degat = this.getDegat();
        int steun = this.getSteun();
        int zone = this.getZone();
        if(this.testVie()){
            this.DisparitionTower(carte);
            return 0;
        }
        if (steun==1){
            this.Steun();
        }
        if (zone==1){
            this.Zone();
        }else{
            int idMonstre = this.idMonstreLePlusAvance();
            MSDC3 monstre = new MSDC3(idMonstre);
            int vieDuMonstre = monstre.getVie();
            vieDuMonstre = vieDuMonstre-(int)degat;
            monstre.setVie(vieDuMonstre);
        }
        return 1;
    }
    public void Steun() throws Exception{
        int nombreDeMonstre = this.nombreDeMonstre();
        for (int i=1;i<=nombreDeMonstre;i++){
            int idMonstre=i;
            MSDC3 monstre = new MSDC3(idMonstre);
            double[] coordonneesMonstre = monstre.getCoordonnees();  
            if (this.LeMonstreEstDansLeRayon(coordonneesMonstre)){
                double Vitesse = monstre.getVitesse();
                Vitesse = Vitesse/1.5;
                monstre.setVitesse(Vitesse);
            }
        }
    }
    public void Zone() throws Exception{
        double degat = this.getDegat();
        int nombreDeMonstre = this.nombreDeMonstre();
        for (int i=1;i<=nombreDeMonstre;i++){
            int idMonstre=i;
            MSDC3 monstre = new MSDC3(idMonstre);
            double[] coordonneesMonstre = monstre.getCoordonnees();  
            if (this.LeMonstreEstDansLeRayon(coordonneesMonstre)){
                int vieDuMonstre = monstre.getVie();
                vieDuMonstre = vieDuMonstre-(int)degat;
                monstre.setVie(vieDuMonstre);
            }
        }
    }
    public int nombreDeMonstre()throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("SELECT IdMonstre FROM monstre;");
        ResultSet resultat = requete.executeQuery();
        int nombre=0;
        while (resultat.next()) {nombre++;}
        requete.close();
        connexion.close();
        return nombre;
    }
    
    public double[] getCoordonnees() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT PositionX,PositionY FROM tour WHERE IdTour=?;");
            requete.setInt(1,this.idTour);
            ResultSet resultat = requete.executeQuery();
            double[] XY={0,0};
            while (resultat.next()) {
                double x = resultat.getDouble("PositionX");
                double y = resultat.getDouble("PositionY");
                XY[0] = x;
                XY[1] = y;
            }
            requete.close();
            connexion.close();
            return XY;
    }
    public double getRayon() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT Portée FROM tour WHERE IdTour=?;");
            requete.setInt(1,this.idTour);
            ResultSet resultat = requete.executeQuery();
            double rayon=0;
            while (resultat.next()) {
                rayon = resultat.getDouble("Portée");}
            requete.close();
            connexion.close();
        return rayon;
    }
    public int getVie() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT PdV FROM tour WHERE IdTour=?;");
            requete.setInt(1,this.idTour);
            ResultSet resultat = requete.executeQuery();
            int vie=0;
            while (resultat.next()) {
                vie = resultat.getInt("PdV");}
            requete.close();
            connexion.close();
        return vie;
    }
    public int getTaille() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT Taille FROM tour WHERE IdTour=?;");
            requete.setInt(1,this.idTour);
            ResultSet resultat = requete.executeQuery();
            int taille=0;
            while (resultat.next()) {
                taille = resultat.getInt("Taille");}
            requete.close();
            connexion.close();
        return taille;
    }
    public double getDegat() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT Degat FROM tour WHERE IdTour=?;");
            requete.setInt(1,this.idTour);
            ResultSet resultat = requete.executeQuery();
            double degat=0;
            while (resultat.next()) {
                degat = resultat.getDouble("Degat");}
            requete.close();
            connexion.close();
        return degat;
    }
    public double getVitesse() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT FréquenceDeTir FROM tour WHERE IdTour=?;");
            requete.setInt(1,this.idTour);
            ResultSet resultat = requete.executeQuery();
            double vitesse=0;
            while (resultat.next()) {
                vitesse = resultat.getDouble("FréquenceDeTir");}
            requete.close();
            connexion.close();
        return vitesse;
    }
    public int getSteun() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT Steun FROM tour WHERE IdTour=?;");
            requete.setInt(1,this.idTour);
            ResultSet resultat = requete.executeQuery();
            int steun = 0;
            while (resultat.next()) {
                steun = resultat.getInt("Steun");}
            requete.close();
            connexion.close();
        return steun;
    }
    public int getZone() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT Zone FROM tour WHERE IdTour=?;");
            requete.setInt(1,this.idTour);
            ResultSet resultat = requete.executeQuery();
            int zone = 0;
            while (resultat.next()) {
                zone = resultat.getInt("Zone");}
            requete.close();
            connexion.close();
        return zone;
    }
    public void setCoordonnees(double[] coordonnees)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE tour SET PositionX=?,PositionY=? WHERE IdTour=?;");
        requete.setDouble(1,coordonnees[0]);
        requete.setDouble(2,coordonnees[1]);
        requete.setInt(3,this.idTour);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void setRayon(double rayon)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE tour SET Portée=? WHERE IdTour=?;");
        requete.setDouble(1,rayon);
        requete.setInt(2,this.idTour);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void setVie(int vie)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE tour SET PdV=? WHERE IdTour=?;");
        requete.setInt(1,vie);
        requete.setInt(2,this.idTour);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void setTaille(int taille)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE tour SET Taille=? WHERE IdTour=?;");
        requete.setInt(1,taille);
        requete.setInt(2,this.idTour);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void setDegat(double degat)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE tour SET Degat=? WHERE IdTour=?;");
        requete.setDouble(1,degat);
        requete.setInt(2,this.idTour);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void setVitesse(double vitesse)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE tour SET FréquenceDeTir=? WHERE IdTour=?;");
        requete.setDouble(1,vitesse);
        requete.setInt(2,this.idTour);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void setSteun(int steun)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE tour SET Steun=? WHERE IdTour=?;");
        requete.setDouble(1,steun);
        requete.setInt(2,this.idTour);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void setZone(int zone)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE tour SET Zone=? WHERE IdTour=?;");
        requete.setDouble(1,zone);
        requete.setInt(2,this.idTour);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    /*public void CreationTour (int idTourCatalogue) throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete1 = connexion.prepareStatement("INSERT INTO monstre VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        PreparedStatement requete2 = connexion.prepareStatement("SELECT PositionX FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete3 = connexion.prepareStatement("SELECT PositionY FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete4 = connexion.prepareStatement("SELECT Vitesse FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete5 = connexion.prepareStatement("SELECT PdV FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete6 = connexion.prepareStatement("SELECT Direction FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete7 = connexion.prepareStatement("SELECT Avancée FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete8 = connexion.prepareStatement("SELECT Taille FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete9 = connexion.prepareStatement("SELECT Attaque FROM monstre WHERE IdMonstre=?;");
        requete2.setDouble(1,idTourCatalogue);
        requete3.setDouble(1,idTourCatalogue);
        requete4.setDouble(1,idTourCatalogue);
        requete5.setInt(1,idTourCatalogue);
        requete6.setInt(1,idTourCatalogue);
        requete7.setInt(1,idTourCatalogue);
        requete8.setInt(1,idTourCatalogue);
        requete9.setInt(1,idTourCatalogue);
        ResultSet resultat2 = requete2.executeQuery();
        ResultSet resultat3 = requete2.executeQuery();
        ResultSet resultat4 = requete2.executeQuery();
        ResultSet resultat5 = requete2.executeQuery();
        ResultSet resultat6 = requete2.executeQuery();
        ResultSet resultat7 = requete2.executeQuery();
        ResultSet resultat8 = requete2.executeQuery();
        ResultSet resultat9 = requete2.executeQuery();
        double PositionX = 0;
        double PositionY = 0;
        double Vitesse = 0;
        int PdV = 0;
        int Direction = 0;
        int Avancee = 0;
        int Taille = 0;
        int Attaque = 0;
        while (resultat2.next()) {
            PositionX = resultat2.getDouble("PositionX");}
        while (resultat3.next()) {
            PositionY = resultat3.getDouble("PositionY");}
        while (resultat4.next()) {
            Vitesse = resultat4.getDouble("Vitesse");}
        while (resultat5.next()) {
            PdV = resultat5.getInt("PdV");}
        while (resultat6.next()) {
            Direction = resultat6.getInt("Direction");}
        while (resultat7.next()) {
            Avancee = resultat7.getInt("Avancée");}
        while (resultat8.next()) {
            Taille = resultat8.getInt("Taille");}
        while (resultat9.next()) {
            Attaque = resultat9.getInt("Attaque");}
        requete1.setInt(1,this.idTour);
        requete1.setString(2,"onsenfout");
        requete1.setDouble(3,PositionX);
        requete1.setDouble(4,PositionY);
        requete1.setString(5,"onsenfout");
        requete1.setDouble(6,Vitesse);
        requete1.setInt(7,PdV);
        requete1.setInt(8,Direction);
        requete1.setInt(9,Avancee);
        requete1.setInt(10,Taille);
        requete1.setInt(11,Attaque);
        requete1.executeUpdate();
        requete1.close();
        requete2.close();
        requete3.close();
        requete4.close();
        requete5.close();
        requete6.close();
        requete7.close();
        connexion.close();
    }*/
   
}
