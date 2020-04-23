/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adrien;

/**
 *
 * @author PC d'Adrien
 */
public class Tour {   // A adapter suite aux changements dans la BDD
    private int idTour;
    private String designation;
    private double X;
    private double Y;
    private String equipe;
    private double niveau;
    private double PdV;
    private double portee;
    private double frequenceDeTire;
    private double degat;
    
    public Tour( int idTour, String designation, double X, double Y, String equipe, double niveau, double PdV,double portee, double frequenceDeTire, double degat){   
        this.idTour=idTour;
        this.designation=designation;
        this.X=X;
        this.Y=Y;
        this.equipe=equipe; // "Rouge" ou " Bleue"
        this.niveau=niveau;
        this.PdV=PdV;
        this.portee=portee;
        this.frequenceDeTire=frequenceDeTire;
        this.degat=degat;
        
    }
    
    
    
    
    // setter , getteur et afficheur

    public int getIdTour() {
        return idTour;
    }
    
    public void setIdTour(int nouvelId){  //Commentaire : à différencer des setIdTourBDD,setIdTour modifier l id d une tour avant que celle ci n entre dan sla bdd
        this.idTour=nouvelId;
    }

    public String getDesignation(){
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public double getX() {
        return X;
    }

    public void setX(double X) {
        this.X = X;
    }

    public double getY() {
        return Y;
    }

    public void setY(double Y) {
        this.Y = Y;
    }
    
    public String getEquipe(){
        return equipe;   
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }
        
    public Double getNiveau(){
        return niveau;
    }
   
    public void setNiveau(double niveau) {
        this.niveau = niveau;
    }
    
    public Double getVie(){
        return PdV;
    }

    public void setPdV(double PdV) {
        this.PdV = PdV;
    }

    public double getPortee() {
        return portee;
    }
    
    public void setPortee(double portee) {
        this.portee = portee;
    }

    public double getFrequenceDeTire() {
        return frequenceDeTire;
    }
    
    public void setFrequenceDeTir(double frequenceDeTire) {
        this.frequenceDeTire = frequenceDeTire;
    }
    
    public double getDegat(){
        return degat;
    }
    
    public void setDegat(double degat) {
        this.degat = degat;
    }
    
    public void afficherTour(){
        System.out.println("Id= "+this.idTour);
        System.out.println("Description= "+this.designation);
        System.out.println("X= "+this.X+", Y= "+this.Y);
        System.out.println("Equipe= "+this.equipe);
        System.out.println("Niveau= "+this.niveau);
        System.out.println("Vie restante = "+this.PdV);
        System.out.println("Portée = "+this.portee);
        System.out.println("Fréquence de Tir = "+this.frequenceDeTire);
        System.out.println("Dégat = "+this.degat);
    }
    
}
