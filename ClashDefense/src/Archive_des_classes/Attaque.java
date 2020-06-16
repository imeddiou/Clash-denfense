
package Archive_des_classes;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Attaque {//C'est la classe qui fait avancer le monstre
    //cocucou adri
    public static void main(String[] args){
        Chemin20x20 carte = new Chemin20x20();//On créé un nouveau chemin
        ArrayList<ArrayList<Integer>> map = carte.CreationMap();//Et on récupère la carte
            int chemin = (int)(Math.random()*(2+1));//Le monstre part aléatoirement sur le chemin de gauche (0), central (1) ou de droite (2)
            //on peut lire en général min+(int)(Math.random()*((max-min)+1))
            double coordonnees[] = {19,chemin+3};//On se place au bon endroit pour le départ du monstre
            chemin = map.get(19).get(chemin+3);//On récupère la constante qui permettra au monstre de suivre aveuglément son chemin
            //C'est ici le cas du monstre qui monte le chemin de gauche de la map
            //Si on veut le monstre qui descende du chemin à droite de la map on remplacera les 2 lignes de codes précédentes par les lignes suivantes:
            //double coordonnees[] = {0,chemin+13};
            //chemin = map.get(0).get(chemin+13);
            //et on fera attention de mettre la direction à 1 comme expliquè plus bas
            double vitesse = 1000;//Le monstre avance toutes les 1000 microsecondes
            int vie = 1;//On établit arbitrairement une vie > 0
            int direction = 3;//On se dirige vers le haut, cela change en fonction de l'équipe du monstre. Si on veut aller vers le bas la direction serait 1, il faudra alors remplacer cette ligne de code par:
            //int direction = 1;
            int avancee = 0;//L'avancée du monstre est initialement 0
            int atk = 1;//On fixe les dégâts que le monstre pourra causer à la tour à 1
            int[] dureeEffet = {0,0,0,0};//Ce vecteur détermine le durée de chacun des effets qui inflige le monstre. On a initialement un vecteur vide puisque le monstre ne subit initialement aucun effet
            //Position 0 : Ralentissement   (nombre de tours restant)
            //Position 1 : Steun            (nombre de tours restant)
            //Position 2 : Degat continu    (nombre de tours restant)
            //Position 3 : Degat degat      (valeur des degats du degat continu)
        MSDC2 monstre = new MSDC2(vie,vitesse,atk,dureeEffet,direction,avancee,chemin,coordonnees);//On définit donc ce monstre
        Timer timer = new Timer();//Et on créer un timer
        timer.scheduleAtFixedRate(new TimerTask(){//On lance la boucle de jeu
            public void run(){
                monstre.Affichage(map);//On affiche la map ainsi que la position du monstre par dessus
                int[] dureeeffet = monstre.getDureeEffet();//On veut maintenant savoir si le monstre est affecté par un quelconque état spécial
                if (dureeeffet[0]!=0){//Si le monstre est ralentit...
                    dureeeffet[0]=dureeeffet[0]-1;//...on décrémente le compteur de tours
                    monstre.setDureeEffet(dureeeffet);
                    //On ne change pas ici la vitesse du monstre car cela est déjà fait dans la classe Tower2
                }else{
                    monstre.setVitesse(vitesse);//Si le monstre n'est pas ralentit alors on implémente la vitesse de base au monstre, celle qui vient de la BDD
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
                if(monstre.TestVie()){timer.cancel();}//On vérifie que le monstre soit encore en vie, sinon on quitte le timer
                if(monstre.TestChateau()){timer.cancel();}//On vérifie qu'il n'est pas encore atteint le château, sinon on quitte le timer
            }
        },0,(int)vitesse);//On commence le timer au temps 0 et on effectue le programme en fonction de la vitesse du monstre
    }
}
