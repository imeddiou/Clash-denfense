/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nico;

import java.util.ArrayList;

/**
 *
 * @author Nico
 */
public class TestCreationDeChemin {
    public static void main(String[] args)throws Exception {
        CreationChemin chemin = new CreationChemin(10,14);
        ArrayList<ArrayList<Integer>> map = chemin.CreationMap();
        
        for(int x=0;x<map.size();x++){
            for(int y=0;y<map.get(0).size();y++){
                if (map.get(x).get(y)==1){
                    System.out.print(map.get(x).get(y));}
                else{System.out.print(".");}
            }
            System.out.println();
        }
    }
}
