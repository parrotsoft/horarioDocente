/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.Login;
import vista.Principal;

/**
 *
 * @author estudiante
 */
public class LoginController implements ActionListener {
    Login viewLogin = new Login();
    Principal viewPrincipal = new Principal();
    
    public LoginController(Login viewLogin) {
        this.viewLogin = viewLogin;
        this.viewLogin.btnEntrar.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewLogin.btnEntrar) {
            JOptionPane.showMessageDialog(null, "Bienvenido Administrados");
            PrincipalController principalController = new PrincipalController(this.viewPrincipal);
            this.viewPrincipal.setVisible(true);
            this.viewLogin.dispose();
        }
    }
}
