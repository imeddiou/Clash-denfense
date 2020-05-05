
package Nico;

import java.util.ArrayList;

public class Chemin20x20 {
    private int hauteur = 20;
    //hauteur de la map
    private int largeur = 10;
    //demi largeur de la map
    private int i = 19;
    //ordonnee initiale du chemin
    private int j = 4;
    //absisse initiale du chemin
    private int largeurgauchelimite = 0;
    //limite gauche absisse incluse
    private int largeurdroitelimite = 8;
    //limite droite absisse incluse
    //le i et j initiaux de l'autre équipe seront donc 0 et 15
    
    public Chemin20x20(){}
    
    public ArrayList<ArrayList<Integer>> CreationMap(){
        ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
        for (int i=0;i<hauteur;i++){
            ArrayList<Integer> ligneMap = new ArrayList<Integer>();
            for (int j=0;j<largeur;j++){
                ligneMap.add(0);}
            map.add(ligneMap);
        }
        //On créé une map remplie de zéros
        int direction = 0;
        //La direction initiale du chemin sera vers le haut
        int[] HB = {-1,0,0};
        int[] GD = {0,1,-1};
        //Les deux vecteurs HautBas et GaucheDroite
        int[] XY = {i,j};
        //Vecteurs de position du curseur de création du chemin central
        map.get(XY[0]).set(XY[1],-1);
        //On implémente 1 pour le chemin central
        map.get(XY[0]).set(XY[1]-1,-2);
        map.get(XY[0]).set(XY[1]+1,-2);
        //Et 2 pour ses deux chemins adjacents 
        int moyenne = -3;
        int longueurDesSegments = 4;
        //Réglages de la longeur des branches
        int min = -longueurDesSegments-1;
        //On veut être sûr de partir vers le haut lors du premier déplacement du curseur
        int directionPrecedente = 0;
        int ordonneePrecedente = 0;
        //Valeurs initiales
        int concentricite = 30;
        //Réglage de la concentricité du modèle
        while(XY[0]>5){
            //On se fixe une limite à partir de laquelle on est sûr de pouvoir revenir vers l'arrivée
            int max = min+longueurDesSegments;
            //L'écart entre le min et le max est toujours constant
            //C'est max+min qui change 
            int chance = this.Random(min,max);
            //Le ratio "chance" augmente avec max+min
            if(chance<0){
                //Si on décide de continuer sur la même direction..
                if(XY[1]+GD[direction]<largeurdroitelimite && XY[1]+GD[direction]>largeurgauchelimite){
                    //Et que l'on ne touchera pas les bords à la prochaine itération..
                    XY[0]=XY[0]+HB[direction];
                    XY[1]=XY[1]+GD[direction];
                    //ALors on met à jour les coordonnées du curseur
                    map.get(XY[0]).set(XY[1],-1);
                    map.get(XY[0]+GD[direction]).set(XY[1]+HB[direction],-2);
                    map.get(XY[0]-GD[direction]).set(XY[1]-HB[direction],-2);
                    //Et on implémente le chemin central et les chemins adjacents dans la map
                    min++;
                    //La proba de tourner au prochain tour augmente alors d'un cran
                }
                else{//Si on touche les bords à la prochaine itération
                    direction = 0;
                    //On se remet droit
                    min =-longueurDesSegments-1;;
                    //Et on s'assure de monter au moins d'une case pour éviter de revenir sur nos pas
                }
            }else{
                //Sinon si on décide de tourner
                if (direction==0){
                    //Et que la direction était vers le haut
                    int variable = (int)((XY[1]-j)/concentricite);
                    //On définit une variable qui évaluera arbitrairement l'écart entre l'axe central j et l'absisse actuelle du curseur
                    direction = this.Random(Math.min(0,variable),Math.max(1,variable));
                    //Le résultat de ce tirage aléatoire veut que plus on est loin de l'axe central j alors plus on a de chance de revenir vers celui-ci
                    if (direction<1){direction = 1;}//On décide de partir vers la droite
                    else{direction = 2;}//On décide de partir sur la gauche
                    if (directionPrecedente!=direction && ordonneePrecedente-XY[0]<4 && directionPrecedente!=0){
                        //C'est un cas particulier
                        //Car si en effet on effectue un demi tour trop serré alors le chemin adjacent interne au virage va se repasser par lui même
                        while(ordonneePrecedente-XY[0]<4){
                            //Donc si le virage est trop serré on le "desserre" assez
                            XY[0]=XY[0]-1;
                            map.get(XY[0]).set(XY[1],-1);
                            map.get(XY[0]).set(XY[1]-1,-2);
                            map.get(XY[0]).set(XY[1]+1,-2);
                            //Tout en implémentant les chemins adjacents
                        }
                    }
                    map.get(XY[0]-1).set(XY[1]-GD[direction],-2);
                    //map.get(XY[0]-1).set(XY[1],-2);
                    map.get(XY[0]).set(XY[1]-GD[direction],-2);
                    //On implémente le chemin adjacent dans la coin externe au tournant
                    min = moyenne;
                    //On repart sur une proba calculée de rester dans cette position ou de tourner
                    directionPrecedente = direction;
                    //On retient juste si on se dirige sur la gauche ou sur la droite
                    ordonneePrecedente = XY[0];
                    //Et on retient l'ordonnée à cet endroit là pour savoir où on en est
                }else{
                    //Et si la direction était sur la droite ou sur la gauche
                    map.get(XY[0]+1).set(XY[1]+GD[direction],-2);
                    //map.get(XY[0]+1).set(XY[1],-2);
                    map.get(XY[0]).set(XY[1]+GD[direction],-2);
                    //On implémente le chemin adjacent dans la coin externe au tournant
                    direction = 0;
                    //On se remet droit
                    min =-longueurDesSegments-1;
                    //Et on s'assure de monter au moins d'une case pour éviter de revenir sur nos pas
                }
            }
        }
        map.get(1).set(Math.min(j,XY[1])-1,-2);
        map.get(2).set(Math.min(j,XY[1])-1,-2);
        map.get(3).set(Math.min(j,XY[1])-1,-2);
        map.get(1).set(Math.max(j,XY[1])+1,-2);
        map.get(2).set(Math.max(j,XY[1])+1,-2);
        map.get(3).set(Math.max(j,XY[1])+1,-2);
        for (int k=Math.min(j,XY[1]);k<=Math.max(j,XY[1]);k++){
            map.get(2).set(k,-1);
            map.get(1).set(k,-2);
            map.get(3).set(k,-2);
        }
        map.get(0).set(j,-1);
        map.get(1).set(j,-1);
        map.get(2).set(j,-1);
        map.get(0).set(j-1,-2);
        map.get(0).set(j+1,-2);
        map.get(1).set(j-1,-2);
        map.get(1).set(j+1,-2);
        map.get(3).set(XY[1],-1);
        map.get(4).set(XY[1],-1);
        map.get(3).set(XY[1]-1,-2);
        map.get(3).set(XY[1]+1,-2);
        map.get(4).set(XY[1]-1,-2);
        map.get(4).set(XY[1]+1,-2);
        //On insère les chemins pour que l'arrivée soit en coordonnees [0,j]
        this.Symetrie(map);
        //On symétrise la map
        return map;
    }
    
    public int Random(int min,int max){
        //Class permettant de sortir un nombre entier aléatoire compris entre min et max inclus
        int random = min+(int)(Math.random()*((max-min)+1));
        return random;
    }
    
    public void Symetrie(ArrayList<ArrayList<Integer>> map){
        //Symétrise la map sur sa largeur
        for (int k=0;k<hauteur;k++){
            for (int i=0;i<largeur;i++){
                  map.get(k).add(map.get(hauteur-k-1).get(largeur-i-1));
            }
        }
    }
}
