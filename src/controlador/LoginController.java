/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioDaoImpl;
import helpers.Encriptacion;
import helpers.Helpers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.Usuario;
import vista.Login;
import vista.Principal;

/**
 *
 * @author estudiante
 */
public class LoginController implements ActionListener {
    Login viewLogin = new Login();
    Principal viewPrincipal = new Principal();
    UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
    
    public LoginController(Login viewLogin) {
        this.viewLogin = viewLogin;
        this.viewLogin.btnEntrar.addActionListener(this);
        this.viewLogin.btnCancelar.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewLogin.btnEntrar) {
            if (Helpers.ValidarUser(this.viewLogin.txtUsuario.getText())) {
                Usuario usuario = new Usuario();
                usuario.setUsuario(this.viewLogin.txtUsuario.getText());
                usuario.setClave(Encriptacion.encriptaClave(this.viewLogin.txtClave.getText()));
                int login = this.usuarioDaoImpl.login(usuario);
                if ( login != 0) {
                    PrincipalController principalController = new PrincipalController(this.viewPrincipal, login);
                    this.viewPrincipal.setVisible(true);
                    this.viewLogin.dispose();  
                } else {
                    JOptionPane.showMessageDialog(null, "Informaciòn incorrecta, por favor verificar...");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Digite un usuario valido...");
            }
            
        }
        
        if (e.getSource() == this.viewLogin.btnCancelar) {
            System.exit(0);
        }
    }
}
