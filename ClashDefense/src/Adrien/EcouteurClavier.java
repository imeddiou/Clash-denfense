/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adrien;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JFrame;

/**
 *
 * @author PC d'Adrien
 */
public class EcouteurClavier implements KeyListener{
    Joueur joueur;
    
    public EcouteurClavier(Joueur joueur){
        this.joueur=joueur;
    }
    
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode()==37){  //Gauche
        joueur.deplacementAGauche();
        System.out.println(Arrays.toString(joueur.getLocalisationJoueur()));
        }
        if (e.getKeyCode()==39){  // Droite
        joueur.deplacementADroite();    
        System.out.println(Arrays.toString(joueur.getLocalisationJoueur()));
        }
        if (e.getKeyCode()==38){  // Haut
        joueur.deplacementEnHaut();    
        System.out.println(Arrays.toString(joueur.getLocalisationJoueur()));
        }
        if (e.getKeyCode()==39){  // Bas
        joueur.deplacementEnBas();   
        System.out.println(Arrays.toString(joueur.getLocalisationJoueur()));
        }
        //System . out . print (" une touche a été appuyée - ") ;
        //System . out . println (" le code de la touche est "+ e . getKeyCode () );
        }
    
    public void keyTyped ( KeyEvent event ) {
        System . out . print (" un caractère a été frappé - ");
        System . out . println (" ’"+ event . getKeyChar () +" ’");
    }
    
    public void keyReleased ( KeyEvent event ) {
        System . out . println (" touche relachée : "+ event . getKeyCode () );
    }
    
}
