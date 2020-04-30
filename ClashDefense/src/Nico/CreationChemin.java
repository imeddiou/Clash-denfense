
package Nico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CreationChemin {
    private int hauteur;
    private int largeur;
    
    public CreationChemin(int hauteur, int largeur){
        this.hauteur=hauteur;
        this.largeur=largeur;
    }
    public ArrayList<ArrayList<Integer>> CreationMap(){
        ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
        for (int i=0;i<this.hauteur;i++){
            ArrayList<Integer> ligneMap = new ArrayList<Integer>();
            //System.out.println(i);
            for (int j=0;j<this.largeur;j++){
                //System.out.println(j);
                ligneMap.add(0);
            }
            map.add(ligneMap);
        }
        int[] HB = {-1,0,0};
        int[] GD = {0,1,-1};
        int i = this.hauteur-2;
        int j = (int)(this.largeur/2)-1;
        int direction = 0;
        int[] XY = {i,j};
        this.Insertion(i,j,map);
        int Avancee = this.Random(2,4);
        int c =0;
        while(c<10){//XY[0]>12){
            c++;
            for(int m=1;m<=Avancee;m++){
                XY[0]=XY[0]+3*HB[direction];
                XY[1]=XY[1]+3*GD[direction];
                      //System.out.println("ok");
                this.Insertion(XY[0],XY[1],map);}
                  //System.out.println("ok");}
            Avancee = this.Random(1,2);
            if (direction==1){
                int variable = (int)((XY[1]-j)/30);
                direction = this.Random(Math.min(0,variable),Math.max(1,variable))+1;
                if (direction<1){
                    direction = 1;
                }else{
                    direction = 2;
                }
            }else{
                direction=1;}  
        }
        return map;
    }
    public int Random(int min,int max){
        int random = min+(int)(Math.random()*((max-min)+1));
        return random;
    }
    public void Insertion(int i,int j,ArrayList<ArrayList<Integer>> map){
        for (int k=i-1;k<=i+1;k++){
            for (int l=j-1;l<=j+1;l++){
                map.get(k).set(l,1);}}
    }
}
