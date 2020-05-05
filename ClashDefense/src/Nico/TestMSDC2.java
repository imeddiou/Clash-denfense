package Nico;

public class TestMSDC2 {
    public static void main(String[] args){
        int carte[][] = {{0,0,0,0,1,0},
                         {0,0,0,0,1,0},
                         {0,0,1,1,1,0},
                         {0,0,1,0,0,0},
                         {0,0,1,0,0,0},
                         {0,0,1,0,0,0},
                         {0,0,1,1,0,0},
                         {0,0,0,1,0,0}};
        double coordonnees[] = {7,3};
        double vitesse = 1;
        int vie = 1;
        int direction = 3;
        int avancee = 0;
        int chemin = 1;
        int atk = 1;
        MSDC2 monstre = new MSDC2(vie,vitesse,atk,direction,avancee,chemin,coordonnees);
        int valeurTest=3;
        while (valeurTest==3){
            //monstre.Affichage(carte);
            //valeurTest=monstre.Avancer(carte);
        }
        if(valeurTest==1){System.out.println("Le monstre est mort");}
        if(valeurTest==2){System.out.println("Le monstre a atteint le château et lui a retiré "+monstre.getAtk()+" points de vie");}

    }
}
