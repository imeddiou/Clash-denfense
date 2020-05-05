
package Nico;

import java.util.ArrayList;

public class TestChemin20x20 {
    public static void main(String[] args){
        Chemin20x20 chemin = new Chemin20x20();
        ArrayList<ArrayList<Integer>> map = chemin.CreationMap();
        for(int x=0;x<map.size();x++){
            for(int y=0;y<map.get(0).size();y++){
                if (map.get(x).get(y)!=0){
                    System.out.print(-map.get(x).get(y));}
                else{System.out.print(".");}
            }
            System.out.println();
        }
    }
}
