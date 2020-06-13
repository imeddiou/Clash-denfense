/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.PartieRequête;
import Ibrahim.Database;
import Ibrahim.JoueurDAO;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author ibrahim
 */
public class MAP_Attaquant extends javax.swing.JFrame implements ActionListener, KeyListener {

    private Timer timer;
    private static final int delay = 200;
    private Database bdd;
    private ResultSet res;
    private JoueurDAO joueur;
    private ArrayList<ArrayList<Integer>> map;
    private int idJoueur;

    public JoueurDAO getJoueur() {
        return joueur;
    }

    public void setJoueur(JoueurDAO joueur) {
        this.joueur = joueur;
        this.idJoueur = joueur.getId();
    }
    
    public MAP_Attaquant() {
        try {
            this.bdd=new Database();
            bdd.connect();
        }
        catch (SQLException ex) {
            Logger.getLogger(BoutonJouer.class.getName()).log(Level.SEVERE, null, ex);
        }
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        initComponents();
        raffraichir();
        
        timer= new Timer(delay,this);
        timer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 800));
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 800));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setPreferredSize(new java.awt.Dimension(100, 800));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPanel1KeyTyped(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Map.png"))); // NOI18N
        jLabel1.setText("Carte");
        jLabel1.setMaximumSize(new java.awt.Dimension(600, 600));
        jLabel1.setMinimumSize(new java.awt.Dimension(600, 600));
        jLabel1.setPreferredSize(new java.awt.Dimension(600, 600));

        jLabel2.setBackground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("Géant");
        jLabel2.setOpaque(true);
        jLabel2.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel3.setBackground(new java.awt.Color(0, 255, 0));
        jLabel3.setText("Gobelin");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setOpaque(true);
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel4.setBackground(new java.awt.Color(153, 51, 255));
        jLabel4.setText("Chevalier");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setOpaque(true);
        jLabel4.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel5.setFont(new java.awt.Font("Harrington", 1, 18)); // NOI18N
        jLabel5.setText("PDV Rouge :");

        jLabel6.setFont(new java.awt.Font("Harrington", 1, 18)); // NOI18N
        jLabel6.setText("PDV Bleu :");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Harrington", 1, 18)); // NOI18N
        jTextField1.setText("20\n");
        jTextField1.setMaximumSize(new java.awt.Dimension(30, 25));
        jTextField1.setMinimumSize(new java.awt.Dimension(30, 25));
        jTextField1.setPreferredSize(new java.awt.Dimension(30, 25));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Harrington", 1, 18)); // NOI18N
        jTextField2.setText("20");
        jTextField2.setMaximumSize(new java.awt.Dimension(30, 25));
        jTextField2.setMinimumSize(new java.awt.Dimension(30, 25));
        jTextField2.setPreferredSize(new java.awt.Dimension(30, 25));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formKeyPressed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jPanel1KeyPressed

    private void jPanel1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyTyped
        // TODO add your handling code here:        
    }//GEN-LAST:event_jPanel1KeyTyped

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
            java.util.logging.Logger.getLogger(MAP_Attaquant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MAP_Attaquant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MAP_Attaquant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MAP_Attaquant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MAP_Attaquant().setVisible(true);
                
            }
        });
    }
    
    public void raffraichir(){
        jLabel1.removeAll();
        jLabel1.setIcon(null);
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/Image/Map.png")));
        try {
            ArrayList<ArrayList> listeMonstre = new ArrayList<ArrayList>();
            ArrayList<ArrayList> listeTour = new ArrayList<ArrayList>();
            listeMonstre.add(new ArrayList<String>());
            listeMonstre.add(new ArrayList<Double>());
            listeMonstre.add(new ArrayList<Double>());
            listeTour.add(new ArrayList<String>());
            listeTour.add(new ArrayList<Double>());
            listeTour.add(new ArrayList<Double>());
            listeTour.add(new ArrayList<Double>());
            PartieRequête requeteMap = new PartieRequête();
            map=requeteMap.partieRequêteSelectMapAsMatrix(bdd);
                
                        
            res=bdd.executeQuery("SELECT Description,PositionX,PositionY FROM monstre");
            while (res.next()){
                listeMonstre.get(0).add(res.getString(1));
                listeMonstre.get(1).add(res.getDouble(2));
                listeMonstre.get(2).add(res.getDouble(3));
            }
            res=bdd.executeQuery("SELECT Description,PositionX,PositionY,Equipe FROM tour");
            while (res.next()){
                listeTour.get(0).add(res.getString(1));
                listeTour.get(1).add(res.getDouble(2));
                listeTour.get(2).add(res.getDouble(3));
                listeTour.get(3).add(res.getString(4));
            }
            for (int i=0; i<listeMonstre.get(0).size(); i++){
                ImageIcon icon = new ImageIcon(getClass().getResource("/Image/image30x30/"+listeMonstre.get(0).get(i)+".png"));
                JLabel img = new JLabel(icon);
                Double x = (Double) listeMonstre.get(1).get(i)*30;
                Double y = (Double) listeMonstre.get(2).get(i)*30;
                img.setBounds(x.intValue(),y.intValue(),30,30);
                jLabel1.add(img);
            }
            for (int i=0; i<listeTour.get(0).size(); i++){
                ImageIcon icon = new ImageIcon(getClass().getResource("/Image/image30x30/"+listeTour.get(0).get(i)+".png"));
                JLabel img = new JLabel(icon);
                Color rouge = new Color((float)1.0,(float) 0.0, (float)0.0, (float)0.3);
                Color bleue = new Color((float)0.0,(float) 0.0, (float)1.0, (float)0.3);
                img.setOpaque(true);
                if (listeTour.get(3).get(i).equals("Bleue")){
                    img.setBackground(bleue);
                }else{
                    img.setBackground(rouge);
                }
                Double x = (Double) listeTour.get(1).get(i)*30;
                Double y = (Double) listeTour.get(2).get(i)*30;
                img.setBounds(x.intValue(),y.intValue(),30,30);
                jLabel1.add(img);
            }
            res=bdd.executeQuery("SELECT PdV FROM équipe WHERE couleur='Bleue'");
            while (res.next()){
                jTextField1.setText(res.getString(1));
            }
            res=bdd.executeQuery("SELECT PdV FROM équipe WHERE couleur='Rouge'");
            while (res.next()){
                jTextField2.setText(res.getString(1));
            }
            res=bdd.executeQuery("SELECT Rôle,PositionX,PositionY FROM partie");
            while(res.next()){
                if (res.getString(1).contains("Bleu")){
                    ImageIcon icon = new ImageIcon(getClass().getResource("/Image/image30x30/joueurBleue.png"));
                    JLabel img = new JLabel(icon);
                    Double x = res.getDouble(2)*30;
                    Double y = res.getDouble(3)*30;
                    img.setBounds(x.intValue(),y.intValue(),30,30);
                    jLabel1.add(img);
                }else{
                    ImageIcon icon = new ImageIcon(getClass().getResource("/Image/image30x30/joueurRouge.png"));
                    JLabel img = new JLabel(icon);
                    Double x = res.getDouble(2)*30;
                    Double y = res.getDouble(3)*30;
                    img.setBounds(x.intValue(),y.intValue(),30,30);
                    jLabel1.add(img);
                }
            }
                       
            for(int i=0 ; i<map.get(0).size(); i++){
                for(int j=0 ; j<map.get(0).size(); j++){
                    if (map.get(i).get(j)<0){
                        ImageIcon icon = new ImageIcon(getClass().getResource("/Image/image30x30/chemin.png"));
                        JLabel img = new JLabel(icon);
                        img.setBounds(i*30, j*30, 30, 30);
                        jLabel1.add(img);
                    }
                    
                }
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(BoutonJouer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    } 
    
    
    public void actionPerformed(ActionEvent e) {
        raffraichir();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        try {
            this.joueur= new JoueurDAO(20,"pseudo");
            res=bdd.executeQuery("SELECT PositionX,PositionY FROM partie WHERE IdJoueur="+this.joueur.getId());
            Double x = 0.0;
            Double y = 0.0;
            while (res.next()){
                x = res.getDouble(1);
                y = res.getDouble(2);
            }
            char touche = e.getKeyChar();
            if (touche=='q'){  //Gauche
                if (x>=(double)1){
                    x=x-1;
                    bdd.executeQuery("UPDATE partie SET PositionX="+x+" WHERE IdJoueur="+this.joueur.getId());
                }
            }
            if (touche=='d'){  // Droite
                    if (x<=(double)18){
                    x=x+1;
                    bdd.executeQuery("UPDATE partie SET PositionX="+x+" WHERE IdJoueur="+this.joueur.getId());
                }
            }
            if (touche=='z'){  // Haut
                    if (y>=(double)1){
                    y=y-1;
                    bdd.executeQuery("UPDATE partie SET PositionY="+y+" WHERE IdJoueur="+this.joueur.getId());
                    }
            }
            if (touche=='s'){  // Bas
                    if (y<=(double)18){
                    y=y+1;
                    bdd.executeQuery("UPDATE partie SET PositionY="+y+" WHERE IdJoueur="+this.joueur.getId());
                    }
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(BoutonJouer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
