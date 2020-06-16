/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archive_des_classes;

/**
 *
 * @author ibrahim
 */
public class MonstreDAO {
    private int idMonstre;
    private String description;
    private double positionX ;
    private double positionY ;
    private String equipe;
    private double vitesse;
    private int pdV;
    private int direction;
    private int avancee;

    public MonstreDAO(int idMonstre, String description, double positionX, double positionY, String equipe, double vitesse, int pdV, int direction, int avancee) {
        this.idMonstre = idMonstre;
        this.description = description;
        this.positionX = positionX;
        this.positionY = positionY;
        this.equipe = equipe;
        this.vitesse = vitesse;
        this.pdV = pdV;
        this.direction = direction;
        this.avancee = avancee;
    }
    
    public MonstreDAO(){
        
    }

    public MonstreDAO(int idMonstre) {
        this.idMonstre = idMonstre;
    }

    public int getIdMonstre() {
        return idMonstre;
    }

    public void setIdMonstre(int idMonstre) {
        this.idMonstre = idMonstre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public int getPdV() {
        return pdV;
    }

    public void setPdV(int pdV) {
        this.pdV = pdV;
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

    @Override
    public String toString() {
        return "MonstreDAO{" + "idMonstre=" + idMonstre + ", description=" + description + ", positionX=" + positionX + ", positionY=" + positionY + ", equipe=" + equipe + ", vitesse=" + vitesse + ", pdV=" + pdV + ", direction=" + direction + ", avancee=" + avancee + '}';
    }
    
}
