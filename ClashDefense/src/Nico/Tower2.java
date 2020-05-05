
package Nico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

public class Tower2 {
    private int vie;
    private double rayon;
    private int degat;
    private double vitesse;
    private double coordonnees[];
    
    public Tower2(int vie,double rayon,int degat, double vitesse, double[] coordonnees){
        this.vie=vie;
        this.rayon=rayon;
        this.degat=degat;
        this.vitesse=vitesse;
        this.coordonnees=coordonnees;
    }
    
    public double DistanceTowerMonstre(double[] coordonneesMonstre){
        double[] coordonnees = this.getCoordonnees();
        double x = coordonnees[0];
        double y = coordonnees[1];
        double X = coordonneesMonstre[0];
        double Y = coordonneesMonstre[1];
        double distance = Math.sqrt(Math.pow(X-x,2)+Math.pow(Y-y,2));
        return distance;
    }  
    public boolean LeMonstreEstDansLeRayon(double[] coordonneesMonstre){
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
      
    public int DefoncerLeMonstre(int carte[][])throws Exception{
        double degat = this.getDegat();
        if(this.testVie()){
            return 0;
        }else{
            int idMonstre = this.idMonstreLePlusAvance();
            MSDC3 monstre = new MSDC3(idMonstre);
            int vieDuMonstre = monstre.getVie();
            vieDuMonstre = vieDuMonstre-(int)degat;
            monstre.setVie(vieDuMonstre);
        }
        return 1;
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

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    public int getDegat() {
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public double[] getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(double[] coordonnees) {
        this.coordonnees = coordonnees;
    }
    
   
}
