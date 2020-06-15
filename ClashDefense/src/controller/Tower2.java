
package controller;

import Model.Database;
import Nico.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tower2 {
    private int vie;//vie sera la vie de la tour
    private double rayon;//le rayon est le rayon de la zone couverte par la tour, c'est la portée
    private int degat;//ce sont les dégâts que la tour infligera au/aux monstre(s)
    private double vitesse;//la vitesse est un nombre qui définit le temps en ms que la tour attend entre chaque tir 
    private double timing;//c'est la vitesse qui sera changée pour le monstre si la tour est une tour de type 2, une tour de ralentissement
    private int nombreDeMonstreTouches;//c'est trivialement le nombre de monstre que la tour affecte
    private int niveau;//le niveau est initialement 1 et augmente par l'intermédiaire des joueurs pour augmenter les performances de la tour
    private int type;//le type de la tour et le type de sort qu'elle afflige, ce sera détaillé plus bas
    private double coordonnees[];//les coordonnées de la tour 
    private Database db = new Database();
    
    public Tower2(int vie,double rayon,int degat,double vitesse,double timing,int nombreDeMonstreTouches,int niveau,int type,double[] coordonnees){
        this.vie=vie;
        this.rayon=rayon;
        this.degat=degat;
        this.vitesse=vitesse;
        this.timing=timing;
        this.nombreDeMonstreTouches=nombreDeMonstreTouches;
        this.niveau=niveau;
        this.type=type;
        this.coordonnees=coordonnees;
        try {
            this.db.connect();
        } catch (SQLException ex) {
            Logger.getLogger(Tower2.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    public int getDegat() {
     //   ResultSet rs = db.executeQuery("SELECT Dégât FROM tour where IdTour = ")
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public double[] getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(double[] coordonnees) {
        this.coordonnees = coordonnees;
    }

    public double getTiming() {
        return timing;
    }

    public void setTiming(double timing) {
        this.timing = timing;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNombreDeMonstreTouches() {
        return nombreDeMonstreTouches;
    }

    public void setNombreDeMonstreTouches(int nombreDeMonstreTouches) {
        this.nombreDeMonstreTouches = nombreDeMonstreTouches;
    }
}
