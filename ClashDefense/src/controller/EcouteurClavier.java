/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Archive_des_classes.JoueurDAO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

/**
 *
 * @author PC d'Adrien
 */
public class EcouteurClavier implements KeyListener{
    Joueur joueur;
    
    public EcouteurClavier(Joueur joueur){
        this.joueur=joueur;
    }
    
    public EcouteurClavier(JoueurDAO j){
        this.joueur=new Joueur(j.getId(),j.getPseudo());
    }
    
//    public void keyPressed(KeyEvent e){
//        if (e.getKeyCode()==37){  //Gauche
//        joueur.deplacementAGauche();
//        System.out.println(Arrays.toString(joueur.getLocalisationJoueur()));
//        }
//        if (e.getKeyCode()==39){  // Droite
//        joueur.deplacementADroite();    
//        System.out.println(Arrays.toString(joueur.getLocalisationJoueur()));
//        }
//        if (e.getKeyCode()==38){  // Haut
//        joueur.deplacementEnHaut();    
//        System.out.println(Arrays.toString(joueur.getLocalisationJoueur()));
//        }
//        if (e.getKeyCode()==40){  // Bas
//        joueur.deplacementEnBas();   
//        System.out.println(Arrays.toString(joueur.getLocalisationJoueur()));
//        }
//        //System . out . print (" une touche a été appuyée - ") ;
//        //System . out . println (" le code de la touche est "+ e . getKeyCode () );
//        }
    
    public void keyTyped ( KeyEvent event ) {
        System . out . print (" un caractère a été frappé - ");
        System . out . println (" ’"+ event . getKeyChar () +" ’");
        System.out.println(Arrays.toString(joueur.getLocalisationJoueur()));
    }
    
    public void keyReleased ( KeyEvent event ) {
        System . out . println (" touche relachée : "+ event . getKeyCode () );
        System.out.println(Arrays.toString(joueur.getLocalisationJoueur()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
