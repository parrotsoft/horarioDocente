/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Usuario;
import vista.MiPerfil;

/**
 *
 * @author miguel
 */
public class MiPerfilController implements ActionListener {

    MiPerfil viewMiPerfil = new MiPerfil();
    Usuario usuario = new Usuario();
    
    public MiPerfilController(MiPerfil viewMiPerfil) {
        this.viewMiPerfil = viewMiPerfil;
        this.usuario = PrincipalController.user;
        
        this.viewMiPerfil.txtNombre.setText(this.usuario.getNombres() + " " + this.usuario.getApellidos());
        this.viewMiPerfil.txtCorre.setText(this.usuario.getCorreo());
        this.viewMiPerfil.txtUsuario.setText(this.usuario.getUsuario());
        
        this.viewMiPerfil.txtNombre.setEnabled(false);
        this.viewMiPerfil.txtCorre.setEnabled(false);
        this.viewMiPerfil.txtUsuario.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
}
