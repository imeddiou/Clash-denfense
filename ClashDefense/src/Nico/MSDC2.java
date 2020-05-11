package Nico;

import java.util.ArrayList;

public class MSDC2 {
    private int pdv;
    private double vitesse;
    private int atk;
    private int[] dureeEffet;
    private int direction;
    private int avancee;
    private int chemin;
    private double[] coordonnees;
    
    public MSDC2(int pdv,double vitesse,int atk,int[] dureeEffet,int direction,int avancee,int chemin,double[] coordonnees){
        this.pdv=pdv;
        this.vitesse=vitesse;
        this.atk=atk;
        this.dureeEffet=dureeEffet;
        this.direction=direction;
        this.avancee=avancee;
        this.chemin=chemin;
        this.coordonnees=coordonnees;
    }
    
    /*public int ChoixDuChemin(){
        int chemin = 1+(int)(Math.random()*((1-3)+1));
        this.setChemin(chemin);
        return chemin;
    }*/

    public boolean TestVie(){
        int pdv = this.getPdv();
        if (pdv<=0){return true;}
        return false;
    }
    
    public boolean TestChateau(){
        double[] coordonnees=this.getCoordonnees();
        if ((int)coordonnees[0]==0){return true;}
        return false;
    }
    
    public void Affichage(ArrayList<ArrayList<Integer>> carte){
        double[] coordonnees=this.getCoordonnees();
        int i = (int)coordonnees[0];
        int j = (int)coordonnees[1];
        for(int x=0;x<carte.size();x++){
            for(int y=0;y<carte.get(0).size();y++){
                if (i==x && j==y){System.out.print("B");}
                else{
                    if(carte.get(x).get(y)==this.getChemin()){System.out.print(-this.getChemin());}
                    else{System.out.print(".");}
                }
            }
            System.out.println();}
    System.out.println();        
    }
    
    public boolean MurDevant(ArrayList<ArrayList<Integer>> carte, int vecteur[][]){
        double[] coordonnees=this.getCoordonnees();
        int direction=this.getDirection();
        int sens[] = vecteur[direction];
        if (carte.get((int)coordonnees[0]+sens[0]).get((int)coordonnees[1]+sens[1])!=this.getChemin()){return true;}
        return false;
    }
    public boolean FautIlAllerADroite(ArrayList<ArrayList<Integer>> carte, int vecteur[][]){
        double[] coordonnees=this.getCoordonnees();
        int direction=this.getDirection();
        int[] sensDroite = vecteur[(direction+1)%4];
        int testDroite=carte.get((int)coordonnees[0]+sensDroite[0]).get((int)coordonnees[1]+sensDroite[1]);
        if (testDroite==this.getChemin()){return true;}
        else{return false;}
    }
    public void Avancer(ArrayList<ArrayList<Integer>> carte){
        double[] coordonnees=this.getCoordonnees();
        int avancee=this.getAvancee();
        int direction=this.getDirection();
        int vecteur[][] = {{0,1},{1,0},{0,-1},{-1,0}};
        // vecteur[0] est le déplacement vers la droite
        // vecteur[1] est le déplacement vers le bas
        // vecteur[2] est le déplacement vers la gauche
        // vecteur[3] est le déplacement vers le haut
        //if(this.TestVie()){return 1;}
        //if(this.TestChateau(carte)){return 2;}
        if(this.MurDevant(carte,vecteur)==true){
            if (this.FautIlAllerADroite(carte,vecteur)==true){direction=(direction+1)%4;
            }else{direction=(direction+3)%4;}
        }
        double nouvellesCoordonnees[]={coordonnees[0]+vecteur[direction][0],coordonnees[1]+vecteur[direction][1]};
        this.setCoordonnees(nouvellesCoordonnees);
        this.setDirection(direction);
        avancee++;
        this.setAvancee(avancee);
    }

    public int getChemin() {
        return chemin;
    }
    
    public int getPdv() {
        return pdv;
    }

    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getAvancee() {
        return avancee;
    }

    public void setAvancee(int avancee) {
        this.avancee = avancee;
    }

    public double[] getCoordonnees() {
        return coordonnees;
    }
    
    public void setChemin(int chemin) {
        this.chemin = chemin;
    }

    public void setCoordonnees(double[] coordonnees) {
        this.coordonnees = coordonnees;
    }

    public int[] getDureeEffet() {
        return dureeEffet;
    }

    public void setDureeEffet(int[] dureeEffet) {
        this.dureeEffet = dureeEffet;
    }
    
    
}
