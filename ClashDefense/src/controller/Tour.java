/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Adrien.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author PC d'Adrien
 */
public class Tour {   // A adapter suite aux changements dans la BDD
    private int idTour;
    private String designation;
    private double X;
    private double Y;
    private String equipe;
    private double niveau;
    private double PdV;
    private double portee;
    private double frequenceDeTire;
    private double degat;
    
    public Tour( int idTour, String designation, double X, double Y, String equipe, double niveau, double PdV,double portee, double frequenceDeTire, double degat){   
        this.idTour=idTour;
        this.designation=designation;
        this.X=X;
        this.Y=Y;
        this.equipe=equipe; // "Rouge" ou " Bleue"
        this.niveau=niveau;
        this.PdV=PdV;
        this.portee=portee;
        this.frequenceDeTire=frequenceDeTire;
        this.degat=degat;
        
    }
    public double DistanceTowerMonstre(double[] coordonneesMonstre){//Cette classe retourne la distance qui sépare la tour du monstre
        double[] coordonnees = this.getCoordonneesDAO();//On récupère les coordonnées à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        double x = coordonnees[0];
        double y = coordonnees[1];
        double X = coordonneesMonstre[0];
        double Y = coordonneesMonstre[1];
        //On utilisera ces variables pour alléger le calcul suivant
        double distance = Math.sqrt(Math.pow(X-x,2)+Math.pow(Y-y,2));//On calcule la distance géométrique entre le monstre et la tour
        return distance;
    }  
    public boolean LeMonstreEstDansLeRayon(double[] coordonneesMonstre){//Cette classe affirme si un monstre donné est dans le rayon ou non
        double rayon = this.getRayonDAO();//On récupère le rayon à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        if (this.DistanceTowerMonstre(coordonneesMonstre)<=rayon){return true;}//Si la distance est plus petite que le rayon de la tour, alors le monstre est dans le rayon
        return false;
    }
    
    public ArrayList<Integer> lesNmonstresLesPlusAvances(ArrayList<MSDC2> listeMonstre){//On récupère les n monstres les plus avancés parmi tout les monstres sur le terrain
        ArrayList<Integer> listeId = new ArrayList<Integer>();//On va stocker l'Id des monstres
        ArrayList<Integer> listeAvancees = new ArrayList<Integer>();//Et leur avancée
        ArrayList<Integer> listeIdsortie = new ArrayList<Integer>();
        //ArrayList<MSDC2> nPremiersMonstre = new ArrayList<MSDC2>();//les n premiers monstres dans le rayon seront implémentés dans cette liste
        int n = this.getNombreDeMonstreTouchesDAO();//on récupère la valeur indiquant le nombre de monstres touchés dans une variable temporaire
        if (n>listeMonstre.size()){n = listeMonstre.size();}//Si n est plus grand que le nombre de monstre total alors on réduit n au nombre de monstres total
        for (int i=0;i<listeMonstre.size();i++){//Pour tout les monstres sur le terrains
            Monstre monstre = new Monstre(listeMonstre.get(i));//on créé temporairement un monstre
            double[] coordonneesMonstre = monstre.getCoordonneesDAO();//On récupère les coordonnées du monstre
            if (this.LeMonstreEstDansLeRayon(coordonneesMonstre)){//Si le monstre est dans le rayon:
                //int id = monstre.getId()//On récupère l'id du monstre. Mais cela n'est pas encore possible sans la BDD
                listeId.add(i);//On implémente à la liste des id l'id du monstre. Pour l'instant on rajoute i.
                int avancee = monstre.getAvanceeDAO();//On récupère l'avancee du monstre
                listeAvancees.add(avancee);//On implémente à la liste des avancées l'avancée du monstre
            }
        }
        if (listeAvancees.isEmpty()){return listeIdsortie;}//Si aucun monstre n'est dans le rayon alors on retourne d'ores et déjà la liste vide
        for (int i=0;i<n;i++){//Pour tout les monstres dans le rayon:
            int indexMaxAvancee = listeAvancees.indexOf(Collections.max(listeAvancees));//On récupère l'indexation du monstre le plus avancé
            int idMonstreLePlusAvance =listeId.get(indexMaxAvancee);//On récupère l'id du monstre à cette indexation
            listeIdsortie.add(idMonstreLePlusAvance);//On rajoute à la liste des monstres les plus avancés le monstre dont l'id est celui du monstre le plus avancé dans le rayon
            listeAvancees.remove(indexMaxAvancee);//Pour ne pas reprendre le même monstre au prochain passage
            listeId.remove(indexMaxAvancee);//Pour ne pas reprendre le même monstre au prochain passage
        }
        return listeIdsortie;//On retourne la liste des n premiers monstres dans le rayon
    }   
    /*public boolean testVie(){//Cette classe retourne si la tour est encore vivante
        int vie = this.getVie();//On récupère la vie à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        if (vie<=0){return true;}return false;//Si la vie est inférieure à 0 alors la tour est morte
    }*/
    
    public void Type1(ArrayList<MSDC2> listeMonstre){//le type 1 est le dégât de zone, qui inflige des dégâts aux n monstres les plus avancés
        double degat = this.getDegat();//On récupère les dégâts à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        for (int i=0;i<listeMonstre.size();i++){//Pour tout les n monstres les plus avancés qui sont dans la zone:
            Monstre monstre = new Monstre(listeMonstre.get(i));//On créé un monstre temporairement
            int vieDuMonstre = monstre.getPdv();//On récupère sa vie
            vieDuMonstre = vieDuMonstre-(int)degat;//On lui retire de la vie et ce en fonction du niveau de la tour
            //Le coefficient 1,5 est bien évidemment arbitraire
            monstre.setPdv(vieDuMonstre);//On implémente la vie au monstre
            //listeMonstre.set(i,monstre);//Et on le replace dans la liste
        }
    }
    /*public void Type2(ArrayList<MSDC2> listeMonstre){//le type 2 est le ralentissement, qui ralentit les n monstres les plus avancés
        double timing = this.getTiming();//On récupère le timing à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        for (int i=0;i<listeMonstre.size();i++){//Pour tout les n monstres les plus avancés qui sont dans la zone:
            MSDC2 monstre = listeMonstre.get(i);//On créé un monstre temporairement
            double vitesse = monstre.getVitesse();//On récupère la vitesse du monstre
            vitesse = vitesse + (timing+this.getNiveau()*500);//On augmente la vitesse du monstre pour qu'il attende plus longtemps entre chaque pas
            monstre.setVitesse(vitesse);//On implémente la nouvelle vitesse 
            int[] dureeeffet = monstre.getDureeEffet();//On récupère la liste des effets du monstre
            dureeeffet[0] = 4;//On dit arbitrairement que le monstre sera ralentit pendant 4 tours
            monstre.setDureeEffet(dureeeffet);//On implémente ça dans le monstre
            listeMonstre.set(i,monstre);//Et on le replace dans la liste
        }
    }
        public void Type3(ArrayList<MSDC2> listeMonstre){//le type 2 est le steun, qui steun les n monstres les plus avancés
        double timing = this.getTiming();//On récupère le timing à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        for (int i=0;i<listeMonstre.size();i++){//Pour tout les n monstres les plus avancés qui sont dans la zone:
            MSDC2 monstre = listeMonstre.get(i);//On créé un monstre temporairement
            int[] dureeeffet = monstre.getDureeEffet();//On récupère la liste des effets du monstre
            dureeeffet[1] = 1 + (int)(niveau*1.1);//la durée du steun augmente avec le niveau de la tour
            //Le coefficient 1,1 est bien évidemment arbitraire
            monstre.setDureeEffet(dureeeffet);//On implémente ça dans le monstre
            listeMonstre.set(i,monstre);//Et on le replace dans la liste
        }
    }
    public void Type4(ArrayList<MSDC2> listeMonstre){//le type 4 est le dégât continu, qui inflige des dégâts en continu aux n monstres les plus avancés
        double timing = this.getTiming();//On récupère le timing à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        double degat = this.getDegat();//On récupère les dégâts à l'aide du getter, car c'est ainsi qu'il faudra faire avec la BDD
        for (int i=0;i<listeMonstre.size();i++){//Pour tout les n monstres les plus avancés qui sont dans la zone:
            MSDC2 monstre = listeMonstre.get(i);//On créé un monstre temporairement
            int[] dureeeffet = monstre.getDureeEffet();//On récupère la liste des effets du monstre
            dureeeffet[2] = 3;//on dit arbitrairement que le monstre recevra des dégâts pendant 3 tours
            dureeeffet[3] = (int)degat+(int)(this.getNiveau()*1.5);//on indique les dégâts que les monstres se verront infligés pendant ces tours
            //Le coefficient 1,5 est bien évidemment arbitraire
            monstre.setDureeEffet(dureeeffet);//On implémente ça dans le monstre
            listeMonstre.set(i,monstre);//Et on le replace dans la liste
        }
    }*/
    
    public void action(ArrayList<MSDC2> listeMonstreOfficielle){//On met en action la tour
        ArrayList<MSDC2> listeMonstre = this.lesNmonstresLesPlusAvances(listeMonstreOfficielle);//On récupère les n monstres les plus avancées parmi la liste totale des monstres
        //On afflige aux n monstres l'effet qui leur est affecté en fonction du type de la tour
        //PS: pour avoir une tour qui afflige seulement des dégâts au monstre le plus avancé, on choisit le type dégât de zone avec nombreDeMonstreTouches=1
        this.Type1(listeMonstre);
        /*if(this.getType()==1){this.Type1(listeMonstre);}
        if(this.getType()==2){this.Type2(listeMonstre);}
        if(this.getType()==3){this.Type3(listeMonstre);}
        if(this.getType()==4){this.Type4(listeMonstre);}*/
    }
//ci-dessous tout les getter et les setter qu'il faudra remplacer avec la mise en place de la BDD
    
    
    
    // setter , getteur et afficheur

    public int getIdTour() {
        return idTour;
    }
    
    public void setIdTour(int nouvelId){  //Commentaire : à différencer des setIdTourBDD,setIdTour modifier l id d une tour avant que celle ci n entre dan sla bdd
        this.idTour=nouvelId;
    }

    public String getDesignation(){
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public double getX() {
        return X;
    }

    public void setX(double X) {
        this.X = X;
    }

    public double getY() {
        return Y;
    }

    public void setY(double Y) {
        this.Y = Y;
    }
    
    public String getEquipe(){
        return equipe;   
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }
        
    public Double getNiveau(){
        return niveau;
    }
   
    public void setNiveau(double niveau) {
        this.niveau = niveau;
    }
    
    public Double getVie(){
        return PdV;
    }

    public void setPdV(double PdV) {
        this.PdV = PdV;
    }

    public double getPortee() {
        return portee;
    }
    
    public void setPortee(double portee) {
        this.portee = portee;
    }

    public double getFrequenceDeTire() {
        return frequenceDeTire;
    }
    
    public void setFrequenceDeTir(double frequenceDeTire) {
        this.frequenceDeTire = frequenceDeTire;
    }
    
    public double getDegat(){
        return degat;
    }
    
    public void setDegat(double degat) {
        this.degat = degat;
    }
    
    public void afficherTour(){
        System.out.println("Id= "+this.idTour);
        System.out.println("Description= "+this.designation);
        System.out.println("X= "+this.X+", Y= "+this.Y);
        System.out.println("Equipe= "+this.equipe);
        System.out.println("Niveau= "+this.niveau);
        System.out.println("Vie restante = "+this.PdV);
        System.out.println("Portée = "+this.portee);
        System.out.println("Fréquence de Tir = "+this.frequenceDeTire);
        System.out.println("Dégat = "+this.degat);
    }
    
}
