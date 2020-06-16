package Archive_des_classes;

import java.util.ArrayList;

public class MSDC2 {//Monstre Suiveur De Chemin version 2
    private int pdv;//pdv sera la vie du monstre
    private double vitesse;//la vitesse est un nombre qui définit le temps en ms que le monstre attend pour avancer d'une case
    private int atk;//l'atk seront les dégâts que le monstre causera au château s'il l'atteint
    private int[] dureeEffet;//dureeEffet est un capteur passif qui indique au monstre s'il est concerné par un quelconque effet causé par une tour
    private int direction;//direction est la direction que le monstre suit 
    private int avancee;//l'avancée un un compteur qui s'incrémente à chaque fois que le monstre avance pour pouvoir ensuite savoir quels sont les monstres les plus avancés
    private int chemin;//le chemin est une constante choisit aléatoirement au départ qui définit si le monstre devra suivre le chemin de gauche, du centre ou de droite
    private double[] coordonnees;//trivialement les coordonnées du monstre
    
    public MSDC2(int pdv,double vitesse,int atk,int[] dureeEffet,int direction,int avancee,int chemin,double[] coordonnees){
        this.pdv=pdv;
        this.vitesse=vitesse;
        this.atk=atk;
        this.dureeEffet=dureeEffet;
        this.direction=direction;
        this.avancee=avancee;
        this.chemin=chemin;
        this.coordonnees=coordonnees;
    }

    public boolean TestVie(){//Cette classe permet de vérifier si le monstre est encore en vie
        int pdv = this.getPdv();//On récupère la vie à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        if (pdv<=0){return true;}//Si la vie est inférieure à 0 on déclare que le monstre est mort
        return false;
    }
    
    public boolean TestChateau(){//Cette classe permet de vérifier si le monstre a atteint le château
        double[] coordonnees=this.getCoordonnees();//On récupère les coordonnées à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        if ((int)coordonnees[0]==0){return true;}//Si l'ordonnée du monstre est celle du chêteau alors on déclare que le monstre a atteint le château
        //le ==0 a été choisi arbitrairement ici, et il faudra faire attention à l'équipe à laquelle appartient le monstre. Pour l'autre équipe ce 0 serait ici un 19
        return false;
    }
    
    public void Affichage(ArrayList<ArrayList<Integer>> carte){//Cette classe affiche le monstre
        double[] coordonnees=this.getCoordonnees();//On récupère les coordonnées à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int i = (int)coordonnees[0];//on utilise i pour simplifier la suite
        int j = (int)coordonnees[1];//on utilise j pour simplifier la suite
        for(int x=0;x<carte.size();x++){
            for(int y=0;y<carte.get(0).size();y++){//On balaye la map
                if (i==x && j==y){System.out.print("M");}//Si on est sur les coordonnées du monstre alors on affiche le monstre, ici à l'aide d'un M
                else{
                    if(carte.get(x).get(y)==this.getChemin()){System.out.print(-this.getChemin());}//Cette condition sert juste à afficher le chemin que suit le monstre dans sa globalité, et on affiche le chemin par la constante qui le caractérise
                    else{System.out.print(".");}//Pour alléger la vue j'utilise ici des . pour toutes les autres cases
                }
            }
            System.out.println();}
    System.out.println();        
    }
    
    public boolean MurDevant(ArrayList<ArrayList<Integer>> carte, int vecteur[][]){//Cette classe permet de vérifier s'il y a un mur ou un chemin qui n'est pas le bon devant le monstre 
        double[] coordonnees=this.getCoordonnees();//On récupère les coordonnées à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int direction=this.getDirection();//On récupère la direction à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int sens[] = vecteur[direction];//Le principe du vecteur est détaillé plus loin, il permet de récupérer la direction du monstre
        if (carte.get((int)coordonnees[0]+sens[0]).get((int)coordonnees[1]+sens[1])!=this.getChemin()){return true;}//Si les coordonnées du monstre à l'étape suivante ne sont pas sur le bon chemin, alors on déclare qu'il y a un mur
        return false;
    }
    
    public boolean FautIlAllerADroite(ArrayList<ArrayList<Integer>> carte, int vecteur[][]){//Cette classe permet de savoir dans quel sens se tourner si il y'a un mur devant
        double[] coordonnees=this.getCoordonnees();//On récupère les coordonnées à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int direction=this.getDirection();//On récupère la direction à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int[] sensDroite = vecteur[(direction+1)%4];//Le principe du vecteur est détaillé plus loin, mais ici on récupère la direction à droite du monstre
        int testDroite=carte.get((int)coordonnees[0]+sensDroite[0]).get((int)coordonnees[1]+sensDroite[1]);//On stocke dans une variable temporaire les coordonnées du monstre s'il se dirigeait maintenant sur la droite
        if (testDroite==this.getChemin()){return true;}//Si les coordonnées du monstre s'il se dirigeait maintenant sur la droite sont sur le bon chemin, alors on déclare qu'il faut tourner à droite
        else{return false;}//sinon qu'il faut tourner à gauche
    }
    public void Avancer(ArrayList<ArrayList<Integer>> carte){//Cette classe est la classe qui fait avancer le monstre
        double[] coordonnees=this.getCoordonnees();//On récupère les coordonnées à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int avancee=this.getAvancee();//On récupère l'avancée à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int direction=this.getDirection();//On récupère la direction à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        int vecteur[][] = {{0,1},{1,0},{0,-1},{-1,0}};
        // vecteur[0] est le déplacement vers la droite
        // vecteur[1] est le déplacement vers le bas
        // vecteur[2] est le déplacement vers la gauche
        // vecteur[3] est le déplacement vers le haut
        if(this.MurDevant(carte,vecteur)==true){//On regarde s'il y a un mur devant, et si oui:
            if (this.FautIlAllerADroite(carte,vecteur)==true){direction=(direction+1)%4;//Si la voie à droite est libre alors on se tourne d'un cran sur la droite
            }else{direction=(direction+3)%4;}//Sinon on se tourne d'un cran sur la gauche 
        }
        double nouvellesCoordonnees[]={coordonnees[0]+vecteur[direction][0],coordonnees[1]+vecteur[direction][1]};//On récupère alors les nouvelles coordonnées du monstre 
        this.setCoordonnees(nouvellesCoordonnees);////Et on les implémente avec le setter, car c'est ainsi qu'il faudra faire avec la BDD
        this.setDirection(direction);//De même on met à jour la direction
        avancee++;//On incrémente l'avancée puisque le monstre a fait un pas de plus
        this.setAvancee(avancee);//Et on met à jour l'avancée du monstre
    }
//ci-dessous tout les getter et les setter qu'il faudra remplacer avec la mise en place de la BDD
    public int getChemin() {
        return chemin;
    }
    
    public int getPdv() {
        return pdv;
    }

    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
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

    public double[] getCoordonnees() {
        return coordonnees;
    }
    
    public void setChemin(int chemin) {
        this.chemin = chemin;
    }

    public void setCoordonnees(double[] coordonnees) {
        this.coordonnees = coordonnees;
    }

    public int[] getDureeEffet() {
        return dureeEffet;
    }

    public void setDureeEffet(int[] dureeEffet) {
        this.dureeEffet = dureeEffet;
    }
    
    
}
