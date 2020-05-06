package Arnaud;


import Ibrahim.JoueurDAO;
import Ibrahim.Database;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arnaud
 */
public class Lobby extends javax.swing.JFrame implements ActionListener {

    private JoueurDAO joueur;
    private Boolean attaquantRougeSelectionne=false;
    private Boolean attaquantBleuSelectionne=false ;
    private Boolean defenseurRougeSelectionne=false ;
    private Boolean defenseurBleuSelectionne=false ;
    private Boolean aSelectionne = false;
    private Database bdd;
    private ResultSet res;
    private Timer timer;
    private static final int delay = 200;
    private int compteur = 2;
    private int affichage = 5;
    /**
     * Get the value of joueur
     *
     * @return the value of joueur
     */
    public JoueurDAO getJoueur() {
        return joueur;
    }

    /**
     * Set the value of joueur
     *
     * @param joueur new value of joueur
     */
    public void setJoueur(JoueurDAO joueur) {
        this.joueur = joueur;
    }

    /**
     * Creates new form Lobby
     */
    public Lobby() {
        try {
            this.bdd=new Database();
            bdd.connect();
        }
        catch (SQLException ex) {
            Logger.getLogger(BoutonJouer.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        raffraichir();
        timer= new Timer(delay,this);
        timer.start();
    }
    
    public void raffraichir(){
        try{        
                res = bdd.executeQuery("SELECT Selectionné FROM partie WHERE Rôle='AttaquantRouge'");
                while (res.next()) {
                    attaquantRougeSelectionne = res.getBoolean(1);
                }
                res = bdd.executeQuery("SELECT Selectionné FROM partie WHERE Rôle='AttaquantBleu'");
                while (res.next()) {
                    attaquantBleuSelectionne = res.getBoolean(1);
                }
                res = bdd.executeQuery("SELECT Selectionné FROM partie WHERE Rôle='DefenseurRouge'");
                while (res.next()) {
                    defenseurRougeSelectionne = res.getBoolean(1);
                }
                res = bdd.executeQuery("SELECT Selectionné FROM partie WHERE Rôle='DefenseurBleu'");
                while (res.next()) {
                    defenseurBleuSelectionne = res.getBoolean(1);
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
            }
        jCheckBox1.setSelected(attaquantRougeSelectionne);
        jCheckBox2.setSelected(attaquantBleuSelectionne);
        jCheckBox3.setSelected(defenseurRougeSelectionne);
        jCheckBox4.setSelected(defenseurBleuSelectionne);
        //depuis le try, sert à coché la case si le rôle est déjà séléctionner par quelqu'un d'autre
        jCheckBox1.setEnabled(!attaquantRougeSelectionne&&!aSelectionne);
        jCheckBox2.setEnabled(!attaquantBleuSelectionne&&!aSelectionne);
        jCheckBox3.setEnabled(!defenseurRougeSelectionne&&!aSelectionne);
        jCheckBox4.setEnabled(!defenseurBleuSelectionne&&!aSelectionne);
        String pseudo1 = "";
        String pseudo2 = "";
        String pseudo3 = "";
        String pseudo4 = "";
        try {
            if (jCheckBox1.isSelected()){
                res = bdd.executeQuery("SELECT pseudo FROM joueur WHERE joueur.IdJoueur=(SELECT IdJoueur FROM partie WHERE Rôle='AttaquantRouge')");
                while (res.next()){
                    pseudo1=res.getString(1);
                }
            }
            else{
                pseudo1="Vide";
            }
            jLabel1.setText(pseudo1);
        
            if (jCheckBox2.isSelected()){
                res = bdd.executeQuery("SELECT pseudo FROM joueur WHERE joueur.IdJoueur=(SELECT IdJoueur FROM partie WHERE Rôle='AttaquantBleu')");
                while (res.next()){
                    pseudo2=res.getString(1);
                }
            }
            else{
                pseudo2="Vide";
            }
            jLabel2.setText(pseudo2);
            if (jCheckBox3.isSelected()){
                res = bdd.executeQuery("SELECT pseudo FROM joueur WHERE joueur.IdJoueur=(SELECT IdJoueur FROM partie WHERE Rôle='DefenseurRouge')");
                while (res.next()){
                    pseudo3=res.getString(1);
                }
            }
            else{
                pseudo3="Vide";
            }
            jLabel3.setText(pseudo3);
            if (jCheckBox4.isSelected()){
                res = bdd.executeQuery("SELECT pseudo FROM joueur WHERE joueur.IdJoueur=(SELECT IdJoueur FROM partie WHERE Rôle='DefenseurBleu')");
                while (res.next()){
                    pseudo4=res.getString(1);
                }
            }
            else{
                pseudo4="Vide";
            }
            jLabel4.setText(pseudo4);
        }
        catch (SQLException ex) {
            Logger.getLogger(jLabel4.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 250));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jCheckBox1.setFont(new java.awt.Font("Harrington", 1, 24)); // NOI18N
        jCheckBox1.setText("Attaquant Rouge");
        jCheckBox1.setOpaque(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1);
        jCheckBox1.setBounds(50, 20, 250, 37);

        jCheckBox2.setFont(new java.awt.Font("Harrington", 1, 24)); // NOI18N
        jCheckBox2.setText("Attaquant Bleu");
        jCheckBox2.setOpaque(false);
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox2);
        jCheckBox2.setBounds(50, 90, 230, 37);

        jCheckBox3.setFont(new java.awt.Font("Harrington", 1, 24)); // NOI18N
        jCheckBox3.setText("Défenseur Rouge");
        jCheckBox3.setOpaque(false);
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox3);
        jCheckBox3.setBounds(330, 20, 250, 37);

        jCheckBox4.setFont(new java.awt.Font("Harrington", 1, 24)); // NOI18N
        jCheckBox4.setText("Défenseur Bleu");
        jCheckBox4.setOpaque(false);
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox4);
        jCheckBox4.setBounds(330, 90, 230, 37);

        jLabel1.setFont(new java.awt.Font("Harrington", 1, 14)); // NOI18N
        String text1 = "";
        try {
            if (jCheckBox1.isSelected()){
                res = bdd.executeQuery("SELECT pseudo FROM joueur WHERE joueur.IdJoueur=(SELECT IdJoueur FROM partie WHERE Rôle='AttaquantRouge')");
                while (res.next()){
                    text1=res.getString(1);
                }
            }
            else{
                text1="Vide";
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(jLabel1.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        jLabel1.setText(text1);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 60, 250, 25);

        jLabel2.setFont(new java.awt.Font("Harrington", 1, 14)); // NOI18N
        String text2 = "";
        try {
            if (jCheckBox2.isSelected()){
                res = bdd.executeQuery("SELECT pseudo FROM joueur WHERE joueur.IdJoueur=(SELECT IdJoueur FROM partie WHERE Rôle='AttaquantBleu')");
                while (res.next()){
                    text2=res.getString(1);
                }
            }
            else{
                text2="Vide";
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(jLabel2.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        jLabel2.setText(text2);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 130, 150, 25);

        jLabel3.setFont(new java.awt.Font("Harrington", 1, 14)); // NOI18N
        String text3 = "";
        try {
            if (jCheckBox3.isSelected()){
                res = bdd.executeQuery("SELECT pseudo FROM joueur WHERE joueur.IdJoueur=(SELECT IdJoueur FROM partie WHERE Rôle='DefenseurRouge')");
                while (res.next()){
                    text3=res.getString(1);
                }
            }
            else{
                text3="Vide";
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(jLabel3.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        jLabel3.setText(text3);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(330, 60, 390, 25);

        jLabel4.setFont(new java.awt.Font("Harrington", 1, 14)); // NOI18N
        String text4 = "";
        try {
            if (jCheckBox4.isSelected()){
                res = bdd.executeQuery("SELECT pseudo FROM joueur WHERE joueur.IdJoueur=(SELECT IdJoueur FROM partie WHERE Rôle='DefenseurBleu')");
                while (res.next()){
                    text4=res.getString(1);
                }
            }
            else{
                text4="Vide";
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(jLabel4.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        jLabel4.setText(text4);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(330, 130, 130, 25);

        jLabel5.setFont(new java.awt.Font("Harrington", 1, 48)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Préparation");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 160, 600, 50);
        getContentPane().add(jLabel6);
        jLabel6.setBounds(0, 0, 0, 0);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arnaud/background_joueur.jpg"))); // NOI18N
        jLabel7.setToolTipText("");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(-10, 0, 670, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        bdd.executeQuery("UPDATE partie SET IdJoueur="+joueur.getId()+",Selectionné=1 WHERE Rôle='AttaquantRouge'");
        attaquantRougeSelectionne=true;
        jLabel1.setText(joueur.getPseudo());
        aSelectionne=true;
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
        bdd.executeQuery("UPDATE partie SET IdJoueur="+joueur.getId()+",Selectionné=1 WHERE Rôle='AttaquantBleu'");
        attaquantBleuSelectionne=true;
        jLabel2.setText(joueur.getPseudo());
        aSelectionne=true;
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
        bdd.executeQuery("UPDATE partie SET IdJoueur="+joueur.getId()+",Selectionné=1 WHERE Rôle='DefenseurRouge'");
        defenseurRougeSelectionne=true;
        jLabel3.setText(joueur.getPseudo());
        aSelectionne=true;
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
        bdd.executeQuery("UPDATE partie SET IdJoueur="+joueur.getId()+",Selectionné=1 WHERE Rôle='DefenseurBleu'");
        defenseurBleuSelectionne=true;
        jLabel4.setText(joueur.getPseudo());
        aSelectionne=true;
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        bdd.disconnect();
    }//GEN-LAST:event_formWindowClosing
    
    @Override
    public void actionPerformed(ActionEvent e) {
        raffraichir(); //pour vérifier à chaque delay si les rôle sont séléctionnés
        if (attaquantBleuSelectionne&&attaquantRougeSelectionne&&defenseurBleuSelectionne&&defenseurRougeSelectionne){
            compteur+=1;
            affichage -= (int)(compteur/5);
            jLabel5.setText("("+affichage+")"); //sert à lancer le décompte avant le début de partie
        }
        if (affichage==0){
            super.dispose();
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Lobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lobby().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
