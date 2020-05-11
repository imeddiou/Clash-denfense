
package Nico;

import java.util.ArrayList;
import java.util.Collections;

public class Tower2 {
    private int vie;
    private double rayon;
    private int degat;
    private double vitesse;
    private double timingSteun;
    private int nombreDeMonstreTouches;
    private int niveau;
    private int type;
    private double coordonnees[];
    
    public Tower2(int vie,double rayon,int degat,double vitesse,double timingSteun,int nombreDeMonstreTouches,int niveau,int type,double[] coordonnees){
        this.vie=vie;
        this.rayon=rayon;
        this.degat=degat;
        this.vitesse=vitesse;
        this.timingSteun=timingSteun;
        this.nombreDeMonstreTouches=nombreDeMonstreTouches;
        this.niveau=niveau;
        this.type=type;
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
    
    public ArrayList<MSDC2> lesNmonstresLesPlusAvances(int n,ArrayList<MSDC2> listeMonstre){
        ArrayList<Integer> listeId = new ArrayList<Integer>();
        ArrayList<Integer> listeAvancees = new ArrayList<Integer>();
        ArrayList<MSDC2> nPremiersMonstre = new ArrayList<MSDC2>();
        if (n>listeMonstre.size()){n = listeMonstre.size();}
        for (int i=0;i<listeMonstre.size();i++){
            MSDC2 monstre = listeMonstre.get(i);
            double[] coordonneesMonstre = monstre.getCoordonnees();  
            if (this.LeMonstreEstDansLeRayon(coordonneesMonstre)){
                listeId.add(i);
                int avancee = monstre.getAvancee();
                listeAvancees.add(avancee);
            }
        }
        if (listeAvancees.isEmpty()){return nPremiersMonstre;}
        for (int i=0;i<n;i++){
            int indexMaxAvancee = listeAvancees.indexOf(Collections.max(listeAvancees));
            int idMonstreLePlusAvance =listeId.get(indexMaxAvancee);
            nPremiersMonstre.add(listeMonstre.get(idMonstreLePlusAvance));
            listeAvancees.remove(indexMaxAvancee);
            listeId.remove(indexMaxAvancee);
        }
        return nPremiersMonstre;
    }   
    public boolean testVie(){
        int vie = this.getVie();
        if (vie<=0){return true;}return false;
    }
    
    public void Type1(ArrayList<MSDC2> listeMonstre){
        double degat = this.getDegat();
        for (int i=0;i<listeMonstre.size();i++){
            MSDC2 monstre = listeMonstre.get(i);
            int vieDuMonstre = monstre.getPdv();
            vieDuMonstre = vieDuMonstre-((int)degat+(int)(this.getNiveau()*0.6));
            monstre.setPdv(vieDuMonstre);
            listeMonstre.set(i,monstre);
        }
    }
    public void Type2(ArrayList<MSDC2> listeMonstre){
        double timingSteun = this.getTimingSteun();
        for (int i=0;i<listeMonstre.size();i++){
            MSDC2 monstre = listeMonstre.get(i);
            double vitesse = monstre.getVitesse();
            vitesse = 3000;
            monstre.setVitesse(vitesse);
            listeMonstre.set(i,monstre);
        }
    }
    
    public int action(ArrayList<MSDC2> listeMonstreOfficielle){
        if (this.testVie()){return 1;}
        ArrayList<MSDC2> listeMonstre = this.lesNmonstresLesPlusAvances(this.getNombreDeMonstreTouches(),listeMonstreOfficielle);
        if(this.getType()==1){this.Type1(listeMonstre);}
        if(this.getType()==2){this.Type2(listeMonstre);}
        return 2;
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

    public double getTimingSteun() {
        return timingSteun;
    }

    public void setTimingSteun(double timingSteun) {
        this.timingSteun = timingSteun;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNombreDeMonstreTouches() {
        return nombreDeMonstreTouches;
    }

    public void setNombreDeMonstreTouches(int nombreDeMonstreTouches) {
        this.nombreDeMonstreTouches = nombreDeMonstreTouches;
    }
    
    
   
}
