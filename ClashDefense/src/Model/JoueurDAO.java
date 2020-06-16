package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Ibrahim.*;
import Ibrahim.Database;
import com.mysql.cj.api.mysqla.result.Resultset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ibrahim
 */

public class JoueurDAO {
    private int id ;
    private String pseudo;
    
    public JoueurDAO(int id, String pseudo){
        this.id = id; this.pseudo = pseudo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public ArrayList<JoueurDAO> getJoeurbdd() throws SQLException{
    ArrayList<JoueurDAO> listJoeursConnectes = new ArrayList();    
    Database db = new Database();
    db.connect();
    ResultSet resultSet = db.executeQuery("select  pseudo from joueur");
    while(resultSet.next()){
        JoueurDAO joeurs = new JoueurDAO(0,"");
        String pseudo = resultSet.getString("pseudo");
        joeurs.setPseudo(pseudo);
        listJoeursConnectes.add(joeurs);
    }
    resultSet.close();
    db.disconnect();
    return listJoeursConnectes;
}

    @Override
    public String toString() {
        return "JoueurDAO{ id="+ id + " pseudo=" + pseudo + '}';
    }
    public void insertJoeurbdd(JoueurDAO j) throws SQLException{
    Database db = new Database();
    db.connect();
    db.executeQuery("insert into joueur (Pseudo) values ('"+j.getPseudo()+"')");
    db.disconnect();
    }
}
