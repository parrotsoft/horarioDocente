/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.Principal;
import javax.swing.JOptionPane;
import vista.Asignaturas;
import vista.Programas;
/**
 *
 * @author estudiante
 */
public class PrincipalController implements ActionListener {
    Principal viewPrincipal = new Principal();
    Programas viewProgramas = new Programas();
    Asignaturas viewAsignaturas = new Asignaturas();
    
    public PrincipalController(Principal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
        this.viewPrincipal.ItemSalir.addActionListener(this);
        this.viewPrincipal.ItemProgramas.addActionListener(this);
        this.viewPrincipal.ItemAsignaturas.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewPrincipal.ItemSalir) {
            System.exit(0);
        }
        if (e.getSource() == this.viewPrincipal.ItemProgramas) {
            if (!this.viewPrincipal.desktopPane.isAncestorOf(viewProgramas)) {
                this.viewPrincipal.desktopPane.add(viewProgramas);    
            } else {
                this.viewPrincipal.desktopPane.setSelectedFrame(viewProgramas);
                this.viewProgramas.setVisible(true);
            }
        }
        
        if (e.getSource() == this.viewPrincipal.ItemAsignaturas) {
            this.viewPrincipal.desktopPane.add(viewAsignaturas);
        }
    }
}
