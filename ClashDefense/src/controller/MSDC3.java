
package controller;

import Nico.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MSDC3 {
    private int idMonstre;
 
    
    public MSDC3(int idMonstre){
        this.idMonstre=idMonstre;
    }
   
    public void AfficherLeMonstre(int carte[][])throws Exception{
        // Ce prgm n'affiche pas réellement le monstre, il change seulement les numéros dans la matrice qui le représentent
        double[] coordonnees=this.getCoordonnees();
        int taille=this.getTaille();
        double x=coordonnees[0];
        double y=coordonnees[1];
        // On récupère les coordonnées du monstre
        for(int i= (int)(x-taille);i<=(int)(x+taille);i++){
            for(int j=(int)(y-taille);j<=(int)(y+taille);j++){
                // Si le monstre est de taille autre que 0 il faut aussi modifier les cases qui l'entourent 
                carte[i][j]=2;
                // 2 sera ici le nombre qui définira la présence d'un monstre
            }
        }
    }
    public void DisparitionDuMonstre(int carte[][])throws Exception{
        // De même que pour afficher le monstre, il faut pouvoir le faire disparaître
        double[] coordonnees=this.getCoordonnees();
        int taille=this.getTaille();
        double x=coordonnees[0];
        double y=coordonnees[1];
        for(int i=(int)(x-taille);i<=(int)(x+taille);i++){
            for(int j=(int)(y-taille);j<=(int)(y+taille);j++){
                if(i==0){carte[i][j]=9;}
                else{carte[i][j]=1;}
                // 1 sera ici le nombre qui définira la présence d'un chemin
                // On fait attention à ne pas transformer un bout du château en chemin
            }
        }
    }
    public boolean TestVie() throws Exception{
        // Il faut vérifier si le monstre a encore assez de vie pour pouvoir continuer à exister
        // Retourne true si le monstre est mort, false sinon
        int pdv = this.getVie();
        if (pdv<=0){
            return true;}
        return false;
    }
    public boolean TestChateau(int carte[][])throws Exception{
        // On returne true si le monstre a atteint le château, false sinon
        double[] coordonnees=this.getCoordonnees();
        int taille=this.getTaille();
        if ((int)coordonnees[0]-taille==0){
            // Si le bord du monstre est en haut de la carte c'est qu'il a atteint le château
            return true;
        }
        return false;
    }
    public void Affichage(int carte[][]){
        // On affiche naïvement la matrice
        for(int x=0;x<carte.length;x++){
            for(int y=0;y<carte[0].length;y++){
                System.out.print(carte[x][y]);
            }
            System.out.println();
        }
    System.out.println();        
    }
    public boolean MurDevant(int carte[][], int vecteur[][])throws Exception{
        // Retourne true s'il y a un mur devant, false sinon
        double[] coordonnees=this.getCoordonnees();
        int taille=this.getTaille();
        int direction=this.getDirection();
        int sens[] = vecteur[direction];
        // Le vecteur est une liste de 4 couples de valeurs, et l'addition d'un couple du vecteur et d'un couple de coordonnées donnera un nouveau couple de coordonnées
        // Ainsi chaque couple du vecteur définit une direction, en haut à droite en bas et à gauche
        int valeurIntermediaire = carte[(int)coordonnees[0]+sens[0]*(1+taille)][(int)coordonnees[1]+sens[1]*(1+taille)];
        //System.out.println((int)coordonnees[0]+sens[0]*(1+taille));
        //System.out.println((int)coordonnees[1]+sens[1]*(1+taille));
        // On définit une valeurIntermediaire simplement pour éviter un calcul répétitif
        if (valeurIntermediaire!=1&&valeurIntermediaire!=9){
            // On doit vérifier si le bord du monstre touche le mur, et non juste le centre, d'où le (1+taille)
            // On fait aussi attention à ne pas prendre le château comme un mur
            return true;}
        return false;
    }
    public boolean FautIlAllerADroite(int carte[][], int vecteur[][])throws Exception{
        // On regarde s'il vaut mieux tourner à droite ou à gauche
        // On retourne true si l'on doit effectivement tourner à droite, false sinon
        double[] coordonnees=this.getCoordonnees();
        int direction=this.getDirection();
        int[] sensDroite = vecteur[(direction+1)%4];
        int[] sensGauche = vecteur[(direction+3)%4];
        // Le vecteur est construit de sorte que le couple suivant un autre coule corresponde au premier couple plus une rotation sur la droite
        // De même pour la gauche, qui correspond à trois rotations sur la droite
        int testDroite = 1;
        int testGauche = 1;
        int compteur = 1;
        // On va désormais regarder simultanément sur la gauche et sur la droite pour savoir où est le mur le plus proche
        while (testDroite==1 && testGauche==1){
            // On quitte le while si on a trouvé un mur
            testDroite=carte[(int)coordonnees[0]+compteur*sensDroite[0]][(int)coordonnees[1]+compteur*sensDroite[1]];
            testGauche=carte[(int)coordonnees[0]+compteur*sensGauche[0]][(int)coordonnees[1]+compteur*sensGauche[1]];
            compteur++;
        }
        // On sort du while, il ne nous reste plus qu'à vérifier qui nous en a fait sortir
        if (testDroite!=1){return false;}
        else{return true;}
    }
    public int Avancer(int carte[][]) throws Exception{
        // C'est le prgm principal qui pemettra de faire avancer le monstre
        // On retourne 1 si le monstre est mort avant d'avoir atteint le château
        // On retourne 2 si le monstre a atteint le château et qu'il a causé des dégâts
        // On retourne 3 si le monstre peut continuer à avancer
        double[] coordonnees=this.getCoordonnees();
        int avancee=this.getAvancee();
        int direction=this.getDirection();
        int vecteur[][] = {{0,1},{1,0},{0,-1},{-1,0}};
        // On vient ici définir le "vecteur" introduit précédemment
        // vecteur[0] est le déplacement vers la droite
        // vecteur[1] est le déplacement vers le bas
        // vecteur[2] est le déplacement vers la gauche
        // vecteur[3] est le déplacement vers le haut
        if(this.TestVie()){
            this.DisparitionDuMonstre(carte);
            // On vérifie que le monstre n'est pas mort, mais s'il l'est on le fait disparaître et on retourne 1
            return 1;
        }
        if(this.TestChateau(carte)){
            this.DisparitionDuMonstre(carte);
            // On vérifie que le monstre n'est pas atteint le château, mais s'il l'a fait on le fait disparaître, et on retourne 2
            return 2;
        }
        if(this.MurDevant(carte,vecteur)==true){
            // On vérifie la présence d'un mur
            if (this.FautIlAllerADroite(carte,vecteur)==true){
                // S'il y en a un on regarde où aller
                direction=(direction+1)%4;
                // Dans ce cas ci on se tourne sur la droite
            }else{
                direction=(direction+3)%4;
                // Et ici sur la gauche
            }
        }
        this.DisparitionDuMonstre(carte);
        // On fait disparaître le monstre avec les anciennes coordonnées
        double nouvellesCoordonnees[]={coordonnees[0]+vecteur[direction][0],coordonnees[1]+vecteur[direction][1]};
        // On applique les nouvelles coordonnées grâce au vecteur et à la direction
        this.setCoordonnees(nouvellesCoordonnees);
        // On insère les nouvelles coordonnées dans la classe monstre
        this.setDirection(direction);
        //On met à jour la direction
        avancee=avancee + 1;
        this.setAvancee(avancee);
        // On incrémente l'avancée 
        this.AfficherLeMonstre(carte);
        // Et enfin on affiche le monstre sur la carte avec ses nouvelle coordonnées, puis on retourne 3
        return 3;
    }
    public int getVie() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT PdV FROM monstre WHERE IdMonstre=?;");
            requete.setInt(1,this.idMonstre);
            ResultSet resultat = requete.executeQuery();
            int vie=0;
            while (resultat.next()) {
                vie = resultat.getInt("PdV");}
            requete.close();
            connexion.close();
        return vie;
    }
    public int getTaille() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT Taille FROM monstre WHERE IdMonstre=?;");
            requete.setInt(1,this.idMonstre);
            ResultSet resultat = requete.executeQuery();
            int taille=0;
            while (resultat.next()) {
                taille = resultat.getInt("Taille");}
            requete.close();
            connexion.close();
        return taille;
    }
    public double getVitesse() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT Vitesse FROM monstre WHERE IdMonstre=?;");
            requete.setInt(1,this.idMonstre);
            ResultSet resultat = requete.executeQuery();
            double vitesse=0;
            while (resultat.next()) {
                vitesse = resultat.getDouble("Vitesse");}
            requete.close();
            connexion.close();
        return vitesse;
    }
    public int getDirection() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT Direction FROM monstre WHERE IdMonstre=?;");
            requete.setInt(1,this.idMonstre);
            ResultSet resultat = requete.executeQuery();
            int direction=0;
            while (resultat.next()) {
                direction = resultat.getInt("Direction");}
            requete.close();
            connexion.close();
        return direction;
    }
    public int getAvancee() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT Avancee FROM monstre WHERE IdMonstre=?;");
            requete.setInt(1,this.idMonstre);
            ResultSet resultat = requete.executeQuery();
            int avancee=0;
            while (resultat.next()) {
                avancee = resultat.getInt("Avancee");}
            requete.close();
            connexion.close();
        return avancee;
    }
    public int getAtk() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT Atk FROM monstre WHERE IdMonstre=?;");
            requete.setInt(1,this.idMonstre);
            ResultSet resultat = requete.executeQuery();
            int atk=0;
            while (resultat.next()) {
                atk = resultat.getInt("Atk");}
            requete.close();
            connexion.close();
        return atk;
    }
    public double[] getCoordonnees() throws Exception{
            Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
            PreparedStatement requete = connexion.prepareStatement("SELECT PositionX,PositionY FROM monstre WHERE IdMonstre=?;");
            requete.setInt(1,this.idMonstre);
            ResultSet resultat = requete.executeQuery();
            double[] XY={0,0};
            while (resultat.next()) {
                double x = resultat.getDouble("PositionX");
                double y = resultat.getDouble("PositionY");
                XY[0] = x;
                XY[1] = y;
            }
            requete.close();
            connexion.close();
            return XY;
    }
    public void setVie(int vie)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE monstre SET PdV=? WHERE IdMonstre=?;");
        requete.setInt(1,vie);
        requete.setInt(2,this.idMonstre);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void setTaille(int taille)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE monstre SET Taille=? WHERE IdMonstre=?;");
        requete.setInt(1,taille);
        requete.setInt(2,this.idMonstre);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void setVitesse(double vitesse)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE monstre SET Vitesse=? WHERE IdMonstre=?;");
        requete.setDouble(1,vitesse);
        requete.setInt(2,this.idMonstre);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void setDirection(int direction)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE monstre SET Direction=? WHERE IdMonstre=?;");
        requete.setInt(1,direction);
        requete.setInt(2,this.idMonstre);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void setAvancee(int avancee)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE monstre SET Avancee=? WHERE IdMonstre=?;");
        requete.setInt(1,avancee);
        requete.setInt(2,this.idMonstre);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void setAtk(int atk)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE monstre SET Atk=? WHERE IdMonstre=?;");
        requete.setInt(1,atk);
        requete.setInt(2,this.idMonstre);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void setCoordonnees(double[] coordonnees)throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete = connexion.prepareStatement("UPDATE monstre SET PositionX=?,PositionY=? WHERE IdMonstre=?;");
        requete.setDouble(1,coordonnees[0]);
        requete.setDouble(2,coordonnees[1]);
        requete.setInt(3,this.idMonstre);
        requete.executeUpdate();
        requete.close();
        connexion.close();
    }
    public void CreationMonstre (int idMonstreCatalogue) throws Exception{
        Connection connexion = DriverManager.getConnection("jdbc:mysql://nemrod.ens2m.fr:3306/20192020_s2_vs1_tp1_clashdefense?serverTimezone=UTC", "clashdefense", "WCvYk10DhJUNKsdX");
        PreparedStatement requete1 = connexion.prepareStatement("INSERT INTO monstre VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        PreparedStatement requete2 = connexion.prepareStatement("SELECT PositionX FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete3 = connexion.prepareStatement("SELECT PositionY FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete4 = connexion.prepareStatement("SELECT Vitesse FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete5 = connexion.prepareStatement("SELECT PdV FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete6 = connexion.prepareStatement("SELECT Direction FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete7 = connexion.prepareStatement("SELECT Avancée FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete8 = connexion.prepareStatement("SELECT Taille FROM monstre WHERE IdMonstre=?;");
        PreparedStatement requete9 = connexion.prepareStatement("SELECT Attaque FROM monstre WHERE IdMonstre=?;");
        requete2.setDouble(1,idMonstreCatalogue);
        requete3.setDouble(1,idMonstreCatalogue);
        requete4.setDouble(1,idMonstreCatalogue);
        requete5.setInt(1,idMonstreCatalogue);
        requete6.setInt(1,idMonstreCatalogue);
        requete7.setInt(1,idMonstreCatalogue);
        requete8.setInt(1,idMonstreCatalogue);
        requete9.setInt(1,idMonstreCatalogue);
        ResultSet resultat2 = requete2.executeQuery();
        ResultSet resultat3 = requete2.executeQuery();
        ResultSet resultat4 = requete2.executeQuery();
        ResultSet resultat5 = requete2.executeQuery();
        ResultSet resultat6 = requete2.executeQuery();
        ResultSet resultat7 = requete2.executeQuery();
        ResultSet resultat8 = requete2.executeQuery();
        ResultSet resultat9 = requete2.executeQuery();
        double PositionX = 0;
        double PositionY = 0;
        double Vitesse = 0;
        int PdV = 0;
        int Direction = 0;
        int Avancee = 0;
        int Taille = 0;
        int Attaque = 0;
        while (resultat2.next()) {
            PositionX = resultat2.getDouble("PositionX");}
        while (resultat3.next()) {
            PositionY = resultat3.getDouble("PositionY");}
        while (resultat4.next()) {
            Vitesse = resultat4.getDouble("Vitesse");}
        while (resultat5.next()) {
            PdV = resultat5.getInt("PdV");}
        while (resultat6.next()) {
            Direction = resultat6.getInt("Direction");}
        while (resultat7.next()) {
            Avancee = resultat7.getInt("Avancée");}
        while (resultat8.next()) {
            Taille = resultat8.getInt("Taille");}
        while (resultat9.next()) {
            Attaque = resultat9.getInt("Attaque");}
        requete1.setInt(1,this.idMonstre);
        requete1.setString(2,"onsenfout");
        requete1.setDouble(3,PositionX);
        requete1.setDouble(4,PositionY);
        requete1.setString(5,"onsenfout");
        requete1.setDouble(6,Vitesse);
        requete1.setInt(7,PdV);
        requete1.setInt(8,Direction);
        requete1.setInt(9,Avancee);
        requete1.setInt(10,Taille);
        requete1.setInt(11,Attaque);
        requete1.executeUpdate();
        requete1.close();
        requete2.close();
        requete3.close();
        requete4.close();
        requete5.close();
        requete6.close();
        requete7.close();
        connexion.close();
        
    }
}
