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
import vista.Dias;
import vista.Disponibilidad;
import vista.Docentes;
import vista.Horarios;
import vista.MiPerfil;
import vista.Profesiones;
import vista.Programas;
import vista.Roles;
import vista.Salones;
import vista.TipoDocumentos;
import vista.Usuarios;
/**
 *
 * @author estudiante
 */
public class PrincipalController implements ActionListener {
    Principal viewPrincipal = new Principal();
    Programas viewProgramas = new Programas();
    Asignaturas viewAsignaturas = new Asignaturas();
    TipoDocumentos viewTipoDocumentos = new TipoDocumentos();
    Salones viewSalones = new Salones();
    Dias viewDias = new Dias();
    Profesiones viewProfesiones = new Profesiones();
    Roles viewRoles = new Roles();
    MiPerfil viewMiPerfil = new MiPerfil();
    Docentes viewDocentes = new Docentes();
    Disponibilidad viewDisponibilidad = new Disponibilidad();
    Usuarios viewUsuarios = new Usuarios();
    Horarios viewHorarios = new Horarios();
    
    
    public PrincipalController(Principal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
        this.viewPrincipal.ItemSalir.addActionListener(this);
        this.viewPrincipal.ItemProgramas.addActionListener(this);
        this.viewPrincipal.ItemAsignaturas.addActionListener(this);
        this.viewPrincipal.ItemTiposDocumentos.addActionListener(this);
        this.viewPrincipal.ItemSalones.addActionListener(this);
        this.viewPrincipal.ItemDias.addActionListener(this);
        this.viewPrincipal.ItemProfesiones.addActionListener(this);
        this.viewPrincipal.ItemRoles.addActionListener(this);
        this.viewPrincipal.ItemMiPerfil.addActionListener(this);
        this.viewPrincipal.ItemDocentes.addActionListener(this);
        this.viewPrincipal.ItemDisponibilidad.addActionListener(this);
        this.viewPrincipal.ItemUsuarios.addActionListener(this);
        this.viewPrincipal.ItemHorarios.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewPrincipal.ItemSalir) {
            System.exit(0);
        }
        if (e.getSource() == this.viewPrincipal.ItemProgramas) {
            Programas viewProgramas = new Programas();
            ProgramaController controllerProgramas = new ProgramaController(viewProgramas);
            this.viewPrincipal.desktopPane.add(viewProgramas);
            viewProgramas.toFront();
            viewProgramas.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemAsignaturas) {
            this.viewPrincipal.desktopPane.add(viewAsignaturas);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemTiposDocumentos) {
            this.viewPrincipal.desktopPane.add(viewTipoDocumentos);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemSalones) {
            this.viewPrincipal.desktopPane.add(viewSalones);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemDias) {
            this.viewPrincipal.desktopPane.add(viewDias);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemProfesiones) {
            this.viewPrincipal.desktopPane.add(viewProfesiones);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemRoles) {
            this.viewPrincipal.desktopPane.add(viewRoles);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemMiPerfil) {
            this.viewPrincipal.desktopPane.add(viewMiPerfil);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemDocentes) {
            this.viewPrincipal.desktopPane.add(viewDocentes);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemDisponibilidad) {
            this.viewPrincipal.desktopPane.add(viewDisponibilidad);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemUsuarios) {
            this.viewPrincipal.desktopPane.add(viewUsuarios);
        }
        
        if (e.getSource() ==this.viewPrincipal.ItemHorarios) {
            this.viewPrincipal.desktopPane.add(viewHorarios);
        }
    }
}
