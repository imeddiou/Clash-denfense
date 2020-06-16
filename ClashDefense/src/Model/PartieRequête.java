/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Fanny.*;
import Ibrahim.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fanny
 */
public class PartieRequête {
    
    public PartieRequête(){
        
    }
    
    public void ModificationIdJoueur(Database baseDeDonnées,int idJoueur, String rôle){
        baseDeDonnées.executeQuery("UPDATE partie SET IdJoueur = '"+idJoueur+"' WHERE Rôle = '"+rôle+"' ");        
    }
    
    
    public void ModificationPosition (Database baseDeDonnées,String rôle, double positionX, double positionY){
        baseDeDonnées.executeQuery("UPDATE partie SET PositionX = '"+positionX+"', PositionY = '"+positionY+"' WHERE Rôle = '"+rôle+"' ");
    }
    
    public void finDePartie (Database baseDeDonnées){
        baseDeDonnées.executeQuery("DELETE FROM monstre ");
        baseDeDonnées.executeQuery("DELETE FROM tour");
        baseDeDonnées.executeQuery("DELETE FROM joueur");
        baseDeDonnées.executeQuery("UPDATE équipe SET PdV = '1000', Elixir = '50', IdJoueurAttaquant = '0', IdJoueurDéfenseur = '0'");
        baseDeDonnées.executeQuery("UPDATE partie SET PositionX = '0', PositionY = '0', IdJoueur = '0', Sélectionnée = 'false', Map = ''");
        
    }
    
    public void partieRequêteStockageMap(Database baseDeDonnées, ArrayList<ArrayList<Integer>> map){
        String mapString = "";
        for(int i=0; i< 19;i++){
            for(int k=0;k<19;k++){
                 mapString += map.get(i).get(k)+" ";
               //  System.out.println( map.get(i).get(k));
            }
        }
        mapString += map.get(19).get(19);
        System.out.println(mapString);
        baseDeDonnées.executeQuery("UPDATE partie SET Map = '"+ mapString +"'"  );
    }
    public ArrayList<ArrayList<Integer>> partieRequêteSelectMapAsMatrix(Database db){
       ResultSet rs =  db.executeQuery("SELECT Map FROM partie");
      ArrayList<ArrayList<Integer>> mapFinal = new ArrayList<ArrayList<Integer>>();
       String mapString = "";
        try {
            while(rs.next()){
                mapString = rs.getString("Map");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartieRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(mapString);
        String[] mapStringSplitted = new String[400];
        mapStringSplitted = mapString.split(" ");
        System.out.println(mapStringSplitted.toString());
         for (int i=0;i<20;i++){
            ArrayList<Integer> ligneMap = new ArrayList<Integer>();
            for (int j=0;j<20;j++){
                ligneMap.add(0);
            }
            mapFinal.add(ligneMap);
         }
        System.out.println(mapFinal.size());
       // System.out.println(mapStringSplitted.length);
           for(int j=0;j<20;j++){
                for(int k=0; k<20; k++){
                    int nbr = Integer.parseInt(mapStringSplitted[k+j]);
                      mapFinal.get(j).set(k,nbr);
                }
            }
             System.out.println(mapFinal.toString());
        return mapFinal;
    }
    
}
