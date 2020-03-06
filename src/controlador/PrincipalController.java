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
/**
 *
 * @author estudiante
 */
public class PrincipalController implements ActionListener {
    Principal viewPrincipal = new Principal();
    
    public PrincipalController(Principal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
        this.viewPrincipal.ItemSalir.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewPrincipal.ItemSalir) {
            System.exit(0);
        }
    }
}
