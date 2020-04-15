/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.Principal;
import javax.swing.JOptionPane;
import models.Usuario;
import vista.Asignaturas;
import vista.Dias;
import vista.Disponibilidades;
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
    Disponibilidades viewDisponibilidad = new Disponibilidades();
    Usuarios viewUsuarios = new Usuarios();
    Horarios viewHorarios = new Horarios();
    
    UsuarioDaoImpl daoUsuario = new UsuarioDaoImpl();
    Usuario user;
    
    
    public PrincipalController(Principal viewPrincipal, int userId) {
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
        
        this.getUsuario(userId);
        this.viewPrincipal.txtUsuario.setText("Bienvenido " + user.getNombres() + " " +user.getApellidos());
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
            Asignaturas viewAsignaturas = new Asignaturas();
            AsignaturaController controllerAsignatura = new AsignaturaController(viewAsignaturas);
            this.viewPrincipal.desktopPane.add(viewAsignaturas);
            viewAsignaturas.toFront();
            viewAsignaturas.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemTiposDocumentos) {
            TipoDocumentos viewTipoDocumentos = new TipoDocumentos();
            TipoDocumentosController controller = new TipoDocumentosController(viewTipoDocumentos);
            this.viewPrincipal.desktopPane.add(viewTipoDocumentos);
            viewTipoDocumentos.toFront();
            viewTipoDocumentos.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemSalones) {
            Salones viewSalon = new Salones();
            SalonController controllerSalon = new SalonController(viewSalon);
            this.viewPrincipal.desktopPane.add(viewSalon);
            viewSalon.toFront();
            viewSalon.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemDias) {
            Dias viewDias = new Dias();
            DiaController controllerDia = new DiaController(viewDias);
            this.viewPrincipal.desktopPane.add(viewDias);
            viewDias.toFront();
            viewDias.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemProfesiones) {
            Profesiones viewProfesiones = new Profesiones();
            ProfesionController controllerProfesion = new ProfesionController(viewProfesiones);
            this.viewPrincipal.desktopPane.add(viewProfesiones);
            viewProfesiones.toFront();
            viewProfesiones.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemRoles) {
            Roles viewRoles = new Roles();
            RolController controllerRole = new RolController(viewRoles);
            this.viewPrincipal.desktopPane.add(viewRoles);
            viewRoles.toFront();
            viewRoles.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemMiPerfil) {
            this.viewPrincipal.desktopPane.add(viewMiPerfil);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemDocentes) {
            Docentes viewDocentes = new Docentes();
            DocenteController controllerDocente = new DocenteController(viewDocentes);
            this.viewPrincipal.desktopPane.add(viewDocentes);
            viewDocentes.toFront();
            viewDocentes.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemDisponibilidad) {
            Disponibilidades viewDisponibilidad = new Disponibilidades();
            DisponibilidadController controllerDisponibilidad = new DisponibilidadController(viewDisponibilidad);
            this.viewPrincipal.desktopPane.add(viewDisponibilidad);
            viewDisponibilidad.toFront();
            viewDisponibilidad.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemUsuarios) {
            this.viewPrincipal.desktopPane.add(viewUsuarios);
        }
        
        if (e.getSource() ==this.viewPrincipal.ItemHorarios) {
            this.viewPrincipal.desktopPane.add(viewHorarios);
        }
    }
    
    private void getUsuario(int id) {
        user = this.daoUsuario.getUsuario(id);
    }
}
