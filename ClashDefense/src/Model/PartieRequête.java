/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import controller.Chemin20x20;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        baseDeDonnées.executeQuery("UPDATE partie SET IdJoueur = '0', Selectionné = '0', Map = '0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0' "); 
        baseDeDonnées.executeQuery("UPDATE partie SET PositionX = '19', PositionY = '19' WHERE Rôle = 'AttaquantRouge' ");      
        baseDeDonnées.executeQuery("UPDATE partie SET PositionX = '19', PositionY = '0' WHERE Rôle = 'DéfenseurRouge' ");
        baseDeDonnées.executeQuery("UPDATE partie SET PositionX = '0', PositionY = '0' WHERE Rôle = 'AttaquantBleu' ");
        baseDeDonnées.executeQuery("UPDATE partie SET PositionX = '0', PositionY = '19' WHERE Rôle = 'DéfenseurBleu' ");
//        Chemin20x20 chemin = new Chemin20x20();
//        ArrayList<ArrayList<Integer>> map = chemin.CreationMap();
//        partieRequêteStockageMap(baseDeDonnées, map);
//0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -2 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -2 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 -2 0 0 0 0 -2 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -2 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -2 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -1 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 -2 0 0 0 0 0 -2 -1 -2 0 0 0 0 0 0 0 0 -2 -1 -2 0 0 0
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
       ResultSet rs =  db.executeQuery("SELECT DISTINCT Map FROM partie");
       ArrayList<ArrayList<Integer>> mapFinal = new ArrayList<ArrayList<Integer>>();
       String mapString = "";
        try {
            while(rs.next()){
                mapString = rs.getString("Map");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartieRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] mapStringSplitted = mapString.split(" ");
         for (int i=0;i<20;i++){
            ArrayList<Integer> ligneMap = new ArrayList<Integer>();
            for (int j=0;j<20;j++){
                ligneMap.add(Integer.parseInt(mapStringSplitted[i*10+j]));}
            mapFinal.add(ligneMap);
         }
         //System.out.println(mapFinal);
        return mapFinal;
    }
    
}
