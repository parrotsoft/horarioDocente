/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Usuario;
import vista.Usuarios;

/**
 *
 * @author miguel
 */
public class UsuarioController implements ActionListener {
    
    Usuarios viewUsuarios = new Usuarios();
    UsuarioDaoImpl daoUsuario = new UsuarioDaoImpl();
    Usuario usuarioEdit = new Usuario();
    
    public UsuarioController(Usuarios viewUsuarios) {
        this.viewUsuarios = viewUsuarios;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
