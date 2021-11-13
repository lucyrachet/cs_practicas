/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

import java.awt.Color;

/**
 *
 * @author luism
 */
public class PanelRegistro extends javax.swing.JPanel {

    /**
     * Creates new form panelPlantilla
     */
    Interfaz interfaz;
    public PanelRegistro(Interfaz i) {
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
        jPanel1 = new javax.swing.JPanel();
        PanelCentral = new javax.swing.JPanel();
        tituloPanel = new javax.swing.JLabel();
        nombreLabel = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        passLabel = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        repetirPassLabel = new javax.swing.JLabel();
        repetirPass = new javax.swing.JPasswordField();
        Entrar = new javax.swing.JPanel();
        EntrarTxt = new javax.swing.JLabel();
        iniciarSesion = new javax.swing.JLabel();
        yaTienesCuenta = new javax.swing.JLabel();

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
            .addGap(0, 330, Short.MAX_VALUE)
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
            .addGap(0, 330, Short.MAX_VALUE)
        );

        add(PanelIzquierdo, java.awt.BorderLayout.LINE_END);

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 25));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        tituloPanel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tituloPanel.setForeground(new java.awt.Color(92, 122, 234));
        tituloPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloPanel.setText("Registro");

        nombreLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nombreLabel.setForeground(new java.awt.Color(92, 122, 234));
        nombreLabel.setLabelFor(nombre);
        nombreLabel.setText("Nombre");

        nombre.setToolTipText("");

        passLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passLabel.setForeground(new java.awt.Color(92, 122, 234));
        passLabel.setLabelFor(pass);
        passLabel.setText("Contraseña");

        repetirPassLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        repetirPassLabel.setForeground(new java.awt.Color(92, 122, 234));
        repetirPassLabel.setLabelFor(repetirPass);
        repetirPassLabel.setText("Repetir contraseña");

        Entrar.setBackground(new java.awt.Color(92, 122, 234));

        EntrarTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EntrarTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EntrarTxt.setText("Crear cuenta");
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

        iniciarSesion.setForeground(new java.awt.Color(92, 122, 234));
        iniciarSesion.setText("Iniciar sesión");
        iniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iniciarSesionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iniciarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                iniciarSesionMouseExited(evt);
            }
        });

        yaTienesCuenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        yaTienesCuenta.setText("¿Ya tienes cuenta?");

        javax.swing.GroupLayout PanelCentralLayout = new javax.swing.GroupLayout(PanelCentral);
        PanelCentral.setLayout(PanelCentralLayout);
        PanelCentralLayout.setHorizontalGroup(
            PanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tituloPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(PanelCentralLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(nombreLabel))
            .addGroup(PanelCentralLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelCentralLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(passLabel))
            .addGroup(PanelCentralLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelCentralLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(repetirPassLabel))
            .addGroup(PanelCentralLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(repetirPass, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelCentralLayout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(Entrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelCentralLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(yaTienesCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelCentralLayout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(iniciarSesion))
        );
        PanelCentralLayout.setVerticalGroup(
            PanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCentralLayout.createSequentialGroup()
                .addComponent(tituloPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nombreLabel)
                .addGap(3, 3, 3)
                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(passLabel)
                .addGap(3, 3, 3)
                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(repetirPassLabel)
                .addGap(3, 3, 3)
                .addComponent(repetirPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(Entrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(yaTienesCuenta)
                .addGap(6, 6, 6)
                .addComponent(iniciarSesion))
        );

        add(PanelCentral, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void EntrarTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrarTxtMouseClicked
        interfaz.Login(nombre.getText(), String.valueOf(pass.getPassword()));
    }//GEN-LAST:event_EntrarTxtMouseClicked

    private void EntrarTxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrarTxtMouseEntered
        Entrar.setBackground(new Color(61,39,155));
        EntrarTxt.setForeground(new Color(230, 230, 230));
    }//GEN-LAST:event_EntrarTxtMouseEntered

    private void EntrarTxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrarTxtMouseExited
        Entrar.setBackground(new Color(92,122,234));
        EntrarTxt.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_EntrarTxtMouseExited

    private void iniciarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iniciarSesionMouseClicked
        interfaz.ponerPanel(interfaz.panelIniciarSesion);
    }//GEN-LAST:event_iniciarSesionMouseClicked

    private void iniciarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iniciarSesionMouseEntered
        iniciarSesion.setForeground(new Color(61,39,155));
    }//GEN-LAST:event_iniciarSesionMouseEntered

    private void iniciarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iniciarSesionMouseExited
        iniciarSesion.setForeground(new Color(92,122,234));
    }//GEN-LAST:event_iniciarSesionMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Entrar;
    private javax.swing.JLabel EntrarTxt;
    private javax.swing.JPanel PanelCentral;
    private javax.swing.JPanel PanelDerecho;
    private javax.swing.JPanel PanelIzquierdo;
    private javax.swing.JLabel iniciarSesion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombre;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JPasswordField pass;
    private javax.swing.JLabel passLabel;
    private javax.swing.JPasswordField repetirPass;
    private javax.swing.JLabel repetirPassLabel;
    private javax.swing.JLabel tituloPanel;
    private javax.swing.JLabel yaTienesCuenta;
    // End of variables declaration//GEN-END:variables

    public void Error(){
        
    }
}
