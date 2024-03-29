/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package constelacionesmultiplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author PabloGuillermo
 */
public class ConstelacionesGUI extends javax.swing.JFrame implements KeyListener, MouseListener {
    
    ConstelacionesLienzo lienzo;
    int cont;
    boolean actNodo;
    boolean actArista;
    int estadoArista;
    int aini;
    public static final int BLANCA=0;
    public static final int OSCURA=1;
    public int turno;
    /**
     * Creates new form KruskalGUI
     */
    
    class PruebaThread implements Runnable{
        boolean parar=false;    
        
        Thread contador;
        
        public void iniciar(){
            contador = new Thread(this);
            contador.start();
            System.out.println("iniciado");
        }
        
        @Override
        public void run() {
            int cont=0;
            
            while(!parar){
                try {
                    contador.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConstelacionesGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Contador en: " +cont);
                cont++;
            }           
        }
        
    }
    
    public ConstelacionesGUI() {
        actNodo=actArista=false;
        cont=1;
        estadoArista=0;
        turno=BLANCA;
        initComponents();
        lienzo = new ConstelacionesLienzo();       
        //JPanel content = new JPanel();
        setSize(500,500);
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(lienzo);
        jPanel1.setBackground(new Color(64, 67, 191));
        //setContentPane(jPanel1);
        setTitle("Constelaciones multiplayer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false);
        pack();
        setLocationRelativeTo(null);   
        lienzo.addMouseListener(this); 
        
        init();
        
    }
    
    public void init(){
        lienzo.nodos.clear();
        lienzo.aristas.clear();
        for (int i = 1; i <= 25; i++) {
            while(!lienzo.insertarNodo((int)(400*Math.random()),(int)(400*Math.random()),i));
        }
        lienzo.repaint();
        turno=BLANCA;
        jLabel1.setText("Turno de las blancas");
        System.out.println(lienzo.nodos.size());        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //JOptionPane.showMessageDialog(null, "Click");
        
        
            int t1,t2;
            t1=t2=0;
            if(estadoArista==0){
                t1=lienzo.perteneceNodo(e.getX(),e.getY());
                if(t1!=0){
                    estadoArista=1;
                    jLabel1.setText("Seleccione el nodo final");
                    aini=t1;
                }
            }    
            else if(estadoArista==1){
                t2=lienzo.perteneceNodo(e.getX(),e.getY());
                if(t2!=0 && t2!=aini){
                                        
                    estadoArista=0;
                    if(lienzo.insertarArista(aini, t2,turno)){
                        System.out.println("Arista insertada");
                        if(turno==BLANCA) turno=OSCURA;
                        else turno = BLANCA;
                        lienzo.repaint();   
                    }
                    else{
                        System.out.println("Error insertando Arista");
                        System.out.println(t1 + "" + t2);
                    }
                                     
                    jLabel1.setText(turno==BLANCA?"Turno de las blancas":"Turno de las oscuras");
                }                
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 620, 30));

        jToggleButton1.setText("Reiniciar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 85, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        init();
        
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConstelacionesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConstelacionesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConstelacionesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConstelacionesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ConstelacionesGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        JOptionPane.showMessageDialog(null, "Click");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    
    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
