/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import Model.PartieRequête;
import Archive_des_classes.Database;
import java.sql.SQLException;
/**
 *
 * @author Nico
 */
public class MAPinsertionBDDTest {
    public static void main(String[] args) {
        Chemin20x20 chemin = new Chemin20x20();
        ArrayList<ArrayList<Integer>> map = chemin.CreationMap();
        Archive_des_classes.Database baseDeDonnées = new Database();
        PartieRequête partieRequete = new PartieRequête();
        partieRequete.partieRequêteStockageMap(baseDeDonnées,map);
    }
}
