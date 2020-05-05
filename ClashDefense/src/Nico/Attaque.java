
package Nico;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Attaque {
    public static void main(String[] args){
        Chemin20x20 carte = new Chemin20x20();
        ArrayList<ArrayList<Integer>> map = carte.CreationMap();
            int chemin = 1+(int)(Math.random()*((3-1)+1));
            double coordonnees[] = {19,chemin+2};
            chemin = map.get(19).get(chemin+2);
            double vitesse = 1000;
            int vie = 1;
            int direction = 3;
            int avancee = 0;
            int atk = 1;
        MSDC2 monstre = new MSDC2(vie,vitesse,atk,direction,avancee,chemin,coordonnees);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            public void run(){
                monstre.Affichage(map);
                int resultat = monstre.Avancer(map);
                if (resultat!=3){
                    if(resultat==1){System.out.println("Le monstre est mort");}
                    if(resultat==2){System.out.println("Le monstre a atteint le château et lui a retiré "+monstre.getAtk()+" points de vie");}
                    timer.cancel();
                }
            }
        },0,(int)vitesse);
    }
}
