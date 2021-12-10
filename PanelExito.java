/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

import java.awt.Color;

/**
 *
 * @author luism
 */
public class PanelExito extends javax.swing.JPanel {

    /**
     * Creates new form panelPlantilla
     */
    Interfaz interfaz;
    public PanelExito(Interfaz i) {
        interfaz = i;
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelDerecho = new javax.swing.JPanel();
        PanelIzquierdo = new javax.swing.JPanel();
        PanelCentral = new javax.swing.JPanel();
        tituloPanel = new javax.swing.JLabel();
        Entrar = new javax.swing.JPanel();
        EntrarTxt = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 355));
        setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout PanelDerechoLayout = new javax.swing.GroupLayout(PanelDerecho);
        PanelDerecho.setLayout(PanelDerechoLayout);
        PanelDerechoLayout.setHorizontalGroup(
            PanelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        PanelDerechoLayout.setVerticalGroup(
            PanelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        add(PanelDerecho, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout PanelIzquierdoLayout = new javax.swing.GroupLayout(PanelIzquierdo);
        PanelIzquierdo.setLayout(PanelIzquierdoLayout);
        PanelIzquierdoLayout.setHorizontalGroup(
            PanelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        PanelIzquierdoLayout.setVerticalGroup(
            PanelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        add(PanelIzquierdo, java.awt.BorderLayout.LINE_END);

        tituloPanel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tituloPanel.setForeground(new java.awt.Color(92, 122, 234));
        tituloPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloPanel.setText("Iniciar sesión");

        Entrar.setBackground(new java.awt.Color(92, 122, 234));

        EntrarTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EntrarTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EntrarTxt.setText("Ir a inicio");
        EntrarTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EntrarTxtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EntrarTxtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EntrarTxtMouseExited(evt);
            }
        });

        javax.swing.GroupLayout EntrarLayout = new javax.swing.GroupLayout(Entrar);
        Entrar.setLayout(EntrarLayout);
        EntrarLayout.setHorizontalGroup(
            EntrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EntrarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(EntrarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        EntrarLayout.setVerticalGroup(
            EntrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EntrarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(EntrarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout PanelCentralLayout = new javax.swing.GroupLayout(PanelCentral);
        PanelCentral.setLayout(PanelCentralLayout);
        PanelCentralLayout.setHorizontalGroup(
            PanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tituloPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(PanelCentralLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(Entrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelCentralLayout.setVerticalGroup(
            PanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCentralLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(tituloPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Entrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        add(PanelCentral, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void EntrarTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrarTxtMouseClicked
        interfaz.ponerPanel(interfaz.panelInicio);
    }//GEN-LAST:event_EntrarTxtMouseClicked

    private void EntrarTxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrarTxtMouseEntered
        Entrar.setBackground(new Color(61,39,155));
        EntrarTxt.setForeground(new Color(230, 230, 230));
    }//GEN-LAST:event_EntrarTxtMouseEntered

    private void EntrarTxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrarTxtMouseExited
        Entrar.setBackground(new Color(92,122,234));
        EntrarTxt.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_EntrarTxtMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Entrar;
    private javax.swing.JLabel EntrarTxt;
    private javax.swing.JPanel PanelCentral;
    private javax.swing.JPanel PanelDerecho;
    private javax.swing.JPanel PanelIzquierdo;
    private javax.swing.JLabel tituloPanel;
    // End of variables declaration//GEN-END:variables

    public void Exito(String _texto){
        tituloPanel.setText(_texto);
    }

}
