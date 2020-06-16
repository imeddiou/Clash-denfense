
package Archive_des_classes;

public class TestMSDC3 {
    public static void main(String[] args)throws Exception {
        int carte[][] = {{9,9,9,9,9,9},
                         {0,0,0,1,1,0},
                         {0,1,1,1,1,0},
                         {0,1,1,1,1,0},
                         {0,1,1,0,0,0},
                         {0,1,1,1,1,0},
                         {0,1,1,1,1,0},
                         {0,0,0,1,1,0}};
        // Le 0 définit le mur
        // Le 1 définit le chemin
        // Le 9 définit le château    
        MSDC3 monstre = new MSDC3(1);
        //monstre.CreationMonstre(0);
        //On choisit le monstre 1
        // On définit toutes les données initiales
        // Le point de départ sera sur le chemin en bas, c'est à dire sur le 1 de la dernière ligne
        double coordonnees[] = {7,3};
        double vitesse = 1;
        int vie = 10;
        int direction = 3;
        int avancee = 0;
        int taille = 0;
        int atk = 1;
        monstre.setCoordonnees(coordonnees);
        monstre.setVitesse(vitesse);
        monstre.setVie(vie);
        monstre.setDirection(direction);
        monstre.setAvancee(avancee);
        monstre.setTaille(taille);
        monstre.setAtk(atk);
        //On affiche le monstre
        monstre.AfficherLeMonstre(carte);
        monstre.Affichage(carte);
        int valeurTest=3;
        while (valeurTest==3){
            valeurTest=monstre.Avancer(carte);
            monstre.Affichage(carte);
            // Tant que le monstre est vivant et qu'il n'a pas atteint le château il continue à avancer
        }
        if(valeurTest==1){System.out.println("Le monstre est mort");}
        if(valeurTest==2){System.out.println("Le monstre a atteint le château et lui a retiré "+monstre.getAtk()+" points de vie");}

    }
}
