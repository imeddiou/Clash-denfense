
package controller;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Attaque {
    public static void main(String[] args){
        Chemin20x20 carte = new Chemin20x20();
        //On créé un nouveau chemin
        ArrayList<ArrayList<Integer>> map = carte.CreationMap();
        //Et on récupère la carte
            int chemin = 1+(int)(Math.random()*((3-1)+1));
            //Le monstre part aléatoirement sur le chemin de gauche, central ou de droite
            double coordonnees[] = {19,chemin+2};
            //On se place au bon endroit pour le départ du monstre
            chemin = map.get(19).get(chemin+2);
            //On récupère la constante qui permettra au monstre de suivre aveuglément son chemin
            double vitesse = 1000;
            //Le monstre avance toutes les 1000 microsecondes
            int vie = 1;
            //On établit arbitrairement une vie > 0
            int direction = 3;
            //On se dirige vers le haut
            int avancee = 0;
            //L'avancée du monstre est initialement 0
            int atk = 1;
            //On fixe les dégâts que le monstre pourra causer à la tour
            int[] dureeEffet = {0,0,0,0};
            //Ce vecteur détermine le durée de chacun des effets qui inflige le monstre
            //Position 0 : Ralentissement   (nombre de tours restant)
            //Position 1 : Steun            (nombre de tours restant)
            //Position 2 : Degat continu    (nombre de tours restant)
            //Position 3 : Degat degat      (valeur des degats du degat continu)
        MSDC2 monstre = new MSDC2(vie,vitesse,atk,dureeEffet,direction,avancee,chemin,coordonnees);
        //On définit donc ce monstre
        Timer timer = new Timer();
        //Et on créer un timer
        timer.scheduleAtFixedRate(new TimerTask(){
            public void run(){
                monstre.Affichage(map);
                //On affiche la map ainsi que la position du monstre par dessus
                if(monstre.TestVie()){timer.cancel();}
                //On vérifie que le monstre soit encore en vie
                if(monstre.TestChateau()){timer.cancel();}
                //On vérifie qu'il n'est pas encore atteint le château
                int[] dureeeffet = monstre.getDureeEffet();
                //On veut maintenant savoir si le monstre est affecté par un quelconque état spécial
                if (dureeeffet[0]!=0){//Si le monstre est ralentit on décrémente le compteur de tours
                    dureeeffet[0]=dureeeffet[0]-1;
                    monstre.setDureeEffet(dureeeffet);
                }else{
                    monstre.setVitesse(vitesse);//Si le monstre n'est pas ralentit alors on implémente la vitesse de base au monstre
                }
                if (dureeeffet[1]!=0){//Si le monstre est steun on passe l'étape d'avancement du monstre et on diminue le compteur de tours
                    dureeeffet[1]=dureeeffet[1]-1;
                    monstre.setDureeEffet(dureeeffet);
                }else{
                    monstre.Avancer(map);
                }
                if (dureeeffet[2]!=0){//Si le monstre recoit des degats continu on décrémente le compteur de tour 
                    dureeeffet[2]=dureeeffet[2]-1;
                    monstre.setDureeEffet(dureeeffet);
                    monstre.setPdv(monstre.getPdv()-dureeeffet[3]);//Sans oublier de défoncer le monstre
                }
                
                //On fait avancer le monstre et on récupère le résultat qui nous indique sa progression
                //if (resultat!=3){
                    //Si le résultat est 3 alors le monstre continue paisiblement son chemin
                    //if(resultat==1){System.out.println("Le monstre est mort");}
                    //Si le résultat est 1 alors le monstre est mort
                    //if(resultat==2){System.out.println("Le monstre a atteint le château et lui a retiré "+monstre.getAtk()+" points de vie");}
                    //Si le résultat est 2 alors le monstre a atteint le château
                    //timer.cancel();
                    //Quelque soit l'un des deux résultats précédents on quitte la boucle d'avancement du monstre
                //}
            }
        },0,(int)vitesse);
        //On commence le timer au temps 0 et on effectue le programme en fonction de la vitesse du monstre
    }
}
