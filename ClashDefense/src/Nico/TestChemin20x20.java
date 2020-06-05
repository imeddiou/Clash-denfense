
package Nico;

import java.util.ArrayList;

public class TestChemin20x20 {
    public static void main(String[] args){
        Chemin20x20 chemin = new Chemin20x20();//On créé un nouveau chemin
        ArrayList<ArrayList<Integer>> map = chemin.CreationMap();//on récupère la carte
        for(int x=0;x<map.size();x++){//on balaye la map
            for(int y=0;y<map.get(0).size();y++){
                if (map.get(x).get(y)!=0){//Si la coordonnée est celle d'un chemin:
                    System.out.print(-map.get(x).get(y));}//On affiche la constante qui caractérise le chemin
                else{System.out.print(".");}//Sinon on affiche de . pour une question de visibilité
            }
            System.out.println();
        }
    }
}
