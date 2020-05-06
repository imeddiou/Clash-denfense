/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ibrahim;

/**
 *
 * @author ibrahim
 */
public class EquipeDAO {
    private String couleur;
    private int pdV;
    private double Elixir;
    private int idJoueurAttaquant;
    private int idJoueurDéfenseur;

    public EquipeDAO() {
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getPdV() {
        return pdV;
    }

    public void setPdV(int pdV) {
        this.pdV = pdV;
    }

    public double getElixir() {
        return Elixir;
    }

    public void setElixir(double Elixir) {
        this.Elixir = Elixir;
    }

    public int getIdJoueurAttaquant() {
        return idJoueurAttaquant;
    }

    public void setIdJoueurAttaquant(int idJoueurAttaquant) {
        this.idJoueurAttaquant = idJoueurAttaquant;
    }

    public int getIdJoueurDéfenseur() {
        return idJoueurDéfenseur;
    }

    public void setIdJoueurDéfenseur(int idJoueurDéfenseur) {
        this.idJoueurDéfenseur = idJoueurDéfenseur;
    }

    @Override
    public String toString() {
        return "EquipeDAO{" + "couleur=" + couleur + ", pdV=" + pdV + ", Elixir=" + Elixir + ", idJoueurAttaquant=" + idJoueurAttaquant + ", idJoueurD\u00e9fenseur=" + idJoueurDéfenseur + '}';
    }
    
}
