/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author estudiante
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        Contenedor = new javax.swing.JInternalFrame();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        ItemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ItemProgramas = new javax.swing.JMenuItem();
        ItemAsignaturas = new javax.swing.JMenuItem();
        ItemTiposDocumentos = new javax.swing.JMenuItem();
        ItemSalones = new javax.swing.JMenuItem();
        ItemDias = new javax.swing.JMenuItem();
        ItemProfesiones = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        ItemRoles = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        ItemMiPerfil = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        ItemDocentes = new javax.swing.JMenuItem();
        ItemDisponibilidad = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktopPane.setLayout(null);

        Contenedor.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        Contenedor.setVisible(false);

        javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor.getContentPane());
        Contenedor.getContentPane().setLayout(ContenedorLayout);
        ContenedorLayout.setHorizontalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ContenedorLayout.setVerticalGroup(
            ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        desktopPane.add(Contenedor);
        Contenedor.setBounds(180, 30, 310, 200);

        jMenu1.setText("Archivo");

        ItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        ItemSalir.setText("Salir");
        jMenu1.add(ItemSalir);

        menuBar.add(jMenu1);

        jMenu2.setText("Parametrización");

        ItemProgramas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        ItemProgramas.setText("Programas");
        jMenu2.add(ItemProgramas);

        ItemAsignaturas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        ItemAsignaturas.setText("Asignaturas");
        jMenu2.add(ItemAsignaturas);

        ItemTiposDocumentos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        ItemTiposDocumentos.setText("Tipos de Documentos");
        jMenu2.add(ItemTiposDocumentos);

        ItemSalones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        ItemSalones.setText("Salones");
        jMenu2.add(ItemSalones);

        ItemDias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        ItemDias.setText("Dias");
        jMenu2.add(ItemDias);

        ItemProfesiones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        ItemProfesiones.setText("Profesiones");
        jMenu2.add(ItemProfesiones);

        menuBar.add(jMenu2);

        jMenu3.setText("Seguridad");

        ItemRoles.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        ItemRoles.setText("Roles");
        jMenu3.add(ItemRoles);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Usuarios");
        jMenu3.add(jMenuItem9);

        ItemMiPerfil.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        ItemMiPerfil.setText("Mi Perfil");
        ItemMiPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemMiPerfilActionPerformed(evt);
            }
        });
        jMenu3.add(ItemMiPerfil);

        menuBar.add(jMenu3);

        jMenu4.setText("Administración");

        ItemDocentes.setText("Docentes");
        jMenu4.add(ItemDocentes);

        ItemDisponibilidad.setText("Disponibilidades");
        jMenu4.add(ItemDisponibilidad);

        menuBar.add(jMenu4);

        jMenu5.setText("Reportes");
        menuBar.add(jMenu5);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ItemMiPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemMiPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ItemMiPerfilActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JInternalFrame Contenedor;
    public javax.swing.JMenuItem ItemAsignaturas;
    public javax.swing.JMenuItem ItemDias;
    public javax.swing.JMenuItem ItemDisponibilidad;
    public javax.swing.JMenuItem ItemDocentes;
    public javax.swing.JMenuItem ItemMiPerfil;
    public javax.swing.JMenuItem ItemProfesiones;
    public javax.swing.JMenuItem ItemProgramas;
    public javax.swing.JMenuItem ItemRoles;
    public javax.swing.JMenuItem ItemSalir;
    public javax.swing.JMenuItem ItemSalones;
    public javax.swing.JMenuItem ItemTiposDocumentos;
    public javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}