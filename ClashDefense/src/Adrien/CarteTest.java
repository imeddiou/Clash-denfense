/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adrien;

import java.util.Arrays;

/**
 *
 * @author PC d'Adrien
 */
public class CarteTest {
    private int[] dimensions;
    private int[][] carte;
    
    public CarteTest(int[] dimensions) {
        this.dimensions=dimensions;
        this.carte=carte;
    }
    
    public int[][] creerUneCarteVierge(){   // Creer une carte de dimension voulue
        this.carte = new int[this.dimensions[0]][this.dimensions[1]];
        return (this.carte);
    }
    
    // Get
    
    public int[] getDimension(){
        return this.dimensions;
    }
    
    public int[][] getCarte(){
        return this.carte;
    }
    
    public int getCase(int[] coordonnees){
        return this.carte[coordonnees[0]][coordonnees[1]];
    }
    
    // Set
    public void setCase(int[] coordonnees, int nouvelleValeure){
        carte[coordonnees[0]][coordonnees[1]]=nouvelleValeure;
    }
    
    public int[][] creerUnCheminDroitAuMilieu(int largeur){ //la largeur doit etre de la meme parite que la dimension de la carte
        this.carte=this.creerUneCarteVierge();
        int milieu=this.dimensions[1]/2;
        int[] intervalChemin=new int[] {milieu-largeur/2,milieu+largeur/2};
        for (int i=intervalChemin[0]-1;i<intervalChemin[1]-1;i=i+1){
            for(int j=0;j<this.dimensions[0];j=j+1){
                carte[j][i]=1;
            }
        }
        return(carte);
    }
    
    public void afficherPlan(){             // Afficher le plan sous la forme de carte 2D et pas sous la forme d'une ligne
        for (int i=0;i<this.dimensions[0];i=i+1){
            System.out.println(Arrays.toString(carte[i]));
        }
}    
}
