
package controller;

import java.util.ArrayList;

public class Chemin20x20 {
    private int hauteur = 20;//hauteur de la map
    private int largeur = 10;//demi largeur de la map
    private int i = 19;//ordonnee initiale du chemin
    private int j = 4;//absisse initiale du chemin
    private int largeurgauchelimite = 1;//limite gauche pour le chemin absisse incluse
    private int largeurdroitelimite = 8;//limite droite pour le chemin absisse incluse
    //le i et j initiaux de l'autre équipe seront donc 0 et 15
    
    public Chemin20x20(){}
    
    public ArrayList<ArrayList<Integer>> CreationMap(){
        ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
        for (int i=0;i<hauteur;i++){
            ArrayList<Integer> ligneMap = new ArrayList<Integer>();
            for (int j=0;j<largeur;j++){
                ligneMap.add(0);}
            map.add(ligneMap);
        }//On créé une map remplie de zéros
        int direction = 0;//La direction initiale du chemin sera vers le haut
        //direction 0 = haut
        //direction 1 = droite
        //direction 2 = gauche
        int[] HB = {-1,0,0};//Le vecteur haut-bas
        int[] GD = {0,1,-1};//Le vecteur gauche-droite
        int[] XY = {i,j};//Vecteurs de position du curseur de création du chemin central
        map.get(XY[0]).set(XY[1],-1);//On implémente -1 pour le chemin central
        map.get(XY[0]).set(XY[1]-1,-2);//Et -2 pour le chemin de gauche
        map.get(XY[0]).set(XY[1]+1,-2);//Et -2 pour le chemin de droite
        int moyenne = -3;//Réglage initial
        int longueurDesSegments = 4;//Réglages de la longeur des branches
        int min = -longueurDesSegments-1;//On veut être sûr de partir vers le haut lors du premier déplacement du curseur
        int directionPrecedente = 0;//Valeurs initiales car pas de direction précédente initialement
        int ordonneePrecedente = 0;//Valeurs initiales car pas d'ordonnée précédente initialement
        int concentricite = 30;//Réglage de la concentricité du modèle. Plus elle est grande plus le chemin ser serré
        int capteurDeCoin = 1;//cette variable détermine s'il faut faire un coin externe au tournant
        while(XY[0]>5){//On se fixe une limite à partir de laquelle on est sûr de pouvoir revenir vers l'arrivée
            int max = min+longueurDesSegments;//L'écart entre le min et le max est toujours constant, c'est max et le min qui changent
            int chance = this.Random(min,max);//Le ratio "chance" augmente selon la position du min (ou du max)
            //Si le max est < 0 alors le "chance" sera forcément < 0
            //Si le min est > 0 alors le "chance" sera forcément > 0
            if(chance<0){//Si on décide de continuer sur la même direction...
                if(XY[1]+GD[direction]<largeurdroitelimite && XY[1]+GD[direction]>largeurgauchelimite){//Et que l'on ne touchera pas les bords à la prochaine itération..
                    XY[0]=XY[0]+HB[direction];//Selon la direction on va se déplacer vers le haut. On ne partira jamais vers le bas
                    XY[1]=XY[1]+GD[direction];//Selon la direction on va se déplacer vers la droite ou vers la gauche
                    map.get(XY[0]).set(XY[1],-1);//Et on implémente le chemin central dans la map
                    map.get(XY[0]+GD[direction]).set(XY[1]+HB[direction],-2);//ainsi que le chemin adjacent
                    map.get(XY[0]-GD[direction]).set(XY[1]-HB[direction],-2);//ainsi que le chemin adjacent
                    min++;//La proba de tourner au prochain tour augmente alors d'un cran
                    capteurDeCoin = 1;//On informe qu'on pourra faire le coin externe au tournant la prochaine fois que nous tournerons, car le fait d'avancer d'une seule case permet de faire un coin externe ou interne
                }
                else{//Si on touche les bords à la prochaine itération:
                    direction = 0;//On se remet droit
                    min =-longueurDesSegments-1;;//Et on s'assure de monter au moins d'une case pour éviter de revenir sur nos pas
                    if (capteurDeCoin==1){//Si il faut faire un coin:
                        map.get(XY[0]+1).set(XY[1]+GD[direction],-2);
                        map.get(XY[0]+1).set(XY[1],-2);
                        map.get(XY[0]).set(XY[1]+GD[direction],-2);
                    }//On implémente le chemin adjacent dans la coin externe au tournant
                }
            }else{
                //Sinon si on décide de tourner...
                if (direction==0){//Et que la direction était vers le haut, alors on peut tourner à droite où à gauche
                    int variable = (int)((XY[1]-j)/concentricite);//On définit une variable qui évaluera arbitrairement l'écart entre l'axe central j et l'absisse actuelle du curseur, et qui ser plus ou moins négatif ou positif selon si l'on sde trouve à droite ou à gauche de l'axe central
                    direction = this.Random(Math.min(0,variable),Math.max(1,variable));//Le résultat de ce tirage aléatoire veut que plus on est loin de l'axe central j alors plus on a de chance de revenir vers celui-ci
                    if (direction<1){direction = 1;}//On décide de partir vers la droite
                    else{direction = 2;}//On décide de partir sur la gauche
                    if (directionPrecedente!=direction && ordonneePrecedente-XY[0]<4 && directionPrecedente!=0){//C'est un cas particulier, car si en effet on effectue un demi tour trop serré alors le chemin adjacent interne au virage va se repasser par lui même
                        while(ordonneePrecedente-XY[0]<4){//Donc si le virage est trop serré on le "desserre" assez
                            XY[0]=XY[0]-1;//On monte verticalement
                            map.get(XY[0]).set(XY[1],-1);
                            map.get(XY[0]).set(XY[1]-1,-2);
                            map.get(XY[0]).set(XY[1]+1,-2);
                            //Tout en implémentant les chemins adjacents
                        }
                    }
                    if (capteurDeCoin==1){//Si il faut faire un coin:
                        map.get(XY[0]-1).set(XY[1]-GD[direction],-2);
                        map.get(XY[0]-1).set(XY[1],-2);
                        map.get(XY[0]).set(XY[1]-GD[direction],-2);
                    }//On implémente le chemin adjacent dans la coin externe au tournant
                    min = moyenne;//On repart sur une proba calculée de rester dans cette position ou de tourner
                    directionPrecedente = direction;//On retient juste si on se dirige sur la gauche ou sur la droite
                    ordonneePrecedente = XY[0];//Et on retient l'ordonnée à cet endroit là pour savoir où on en est dans les virages
                    capteurDeCoin = 0;//on informe qu'il ne faudra peut être pas faire de coin externe au tournant, si on avance pas au moins d'une case
                }else{//Et si la direction était sur la droite ou sur la gauche
                    if (capteurDeCoin==1){//Si il faut faire un coin:
                        map.get(XY[0]+1).set(XY[1]+GD[direction],-2);
                        map.get(XY[0]+1).set(XY[1],-2);
                        map.get(XY[0]).set(XY[1]+GD[direction],-2);
                    }//On implémente le chemin adjacent dans la coin externe au tournant
                    direction = 0;//On se remet droit
                    min =-longueurDesSegments-1;//Et on s'assure de monter au moins d'une case pour éviter de revenir sur nos pas
                }
            }
        }
        //On insère maintenant les chemins pour que l'arrivée soit en coordonnees [0,j]
        map.get(1).set(Math.min(j,XY[1])-1,-2);
        map.get(2).set(Math.min(j,XY[1])-1,-2);
        map.get(3).set(Math.min(j,XY[1])-1,-2);
        map.get(1).set(Math.max(j,XY[1])+1,-2);
        map.get(2).set(Math.max(j,XY[1])+1,-2);
        map.get(3).set(Math.max(j,XY[1])+1,-2);
        //On créé les coins externes au dernier virage
        for (int k=Math.min(j,XY[1]);k<=Math.max(j,XY[1]);k++){//Pour toutes les colonnes séparant entre la colonne actuelle du monstre et l'arrivée
            map.get(2).set(k,-1);
            map.get(1).set(k,-2);
            map.get(3).set(k,-2);
            //On implément el chemin horizontal aainsi que les chemins adjacents
        }
        map.get(0).set(j,-1);
        map.get(1).set(j,-1);
        map.get(2).set(j,-1);
        //On créé la ligne d'arrivée verticale
        map.get(0).set(j-1,-2);
        map.get(0).set(j+1,-2);
        map.get(1).set(j-1,-2);
        map.get(1).set(j+1,-2);
        //Ainsi que ses chemins adjacents
        map.get(3).set(XY[1],-1);
        map.get(4).set(XY[1],-1);
        //On finit la ligne droite en sortie du while précédent
        map.get(3).set(XY[1]-1,-2);
        map.get(3).set(XY[1]+1,-2);
        map.get(4).set(XY[1]-1,-2);
        map.get(4).set(XY[1]+1,-2);
        //Ainsi que ses chemins adjacents
        this.Symetrie(map);//On symétrise la map
        return map;
    }
    
    public int Random(int min,int max){//cette classe permet de sortir un nombre entier aléatoire compris entre min et max inclus
        int random = min+(int)(Math.random()*((max-min)+1));//On fait attention aux bords pour avoir bien le min et le max inclus
        return random;
    }
    
    public void Symetrie(ArrayList<ArrayList<Integer>> map){//cette classe symétrise la map sur sa largeur
        for (int k=0;k<hauteur;k++){//on balaye la map
            for (int i=0;i<largeur;i++){
                  map.get(k).add(map.get(hauteur-k-1).get(largeur-i-1));//On implémente les coordonnées symétriquement 
            }
        }
    }
}
