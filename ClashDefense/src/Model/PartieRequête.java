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
        try {
            ResultSet resultat0 = baseDeDonnées.executeQuery("SELECT Selectionné FROM partie WHERE Rôle = '"+rôle+"'");
            if (resultat0.getBoolean("Selectionné")){
                baseDeDonnées.executeQuery("UPDATE partie SET PositionX = '"+positionX+"' WHERE Rôle = '"+rôle+"' ");
                baseDeDonnées.executeQuery("UPDATE partie SET PositionY = '"+positionY+"' WHERE Rôle = '"+rôle+"' ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurRequête.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void partieRequêteStockageMap(Database baseDeDonnées, ArrayList<ArrayList<Integer>> map){
        String mapString = "";
        for(int i=0; i< map.size();i++){
            for(int k=0;k<map.get(0).size();k++){
                 mapString += map.get(i).get(k)+ " ";
            }
        }
        System.out.println(mapString);
        baseDeDonnées.executeQuery("UPDATE partie SET Map = '" + mapString + "'"  );
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
                ligneMap.add(Integer.parseInt(mapStringSplitted[i+j]));}
            mapFinal.add(ligneMap);
         }
        return mapFinal;
    }
    
}
