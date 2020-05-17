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
    public static Usuario user;
    
    
    public PrincipalController(Principal viewPrincipal, int userId) {
        this.viewPrincipal = viewPrincipal;
        this.viewPrincipal.ItemSalir.addActionListener(this);
        this.viewPrincipal.ItemProgramas.addActionListener(this);
        this.viewPrincipal.ItemAsignaturas.addActionListener(this);
        this.viewPrincipal.ItemTiposDocumentos.addActionListener(this);
        this.viewPrincipal.ItemSalones.addActionListener(this);
        this.viewPrincipal.ItemDias.addActionListener(this);
        this.viewPrincipal.ItemProfesiones.addActionListener(this);
        this.viewPrincipal.mItemRoles.addActionListener(this);
        this.viewPrincipal.ItemMiPerfil.addActionListener(this);
        this.viewPrincipal.itemDocentes.addActionListener(this);
        this.viewPrincipal.ItemDisponibilidad.addActionListener(this);
        this.viewPrincipal.ItemUsuarios.addActionListener(this);
        this.viewPrincipal.ItemHorarios.addActionListener(this);
        
        this.viewPrincipal.btnProgramas.addActionListener(this);
        this.viewPrincipal.btnAsignatura.addActionListener(this);
        this.viewPrincipal.btnUsuarios.addActionListener(this);
        this.viewPrincipal.btnDocentes.addActionListener(this);
        
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
        
        if (e.getSource() == this.viewPrincipal.mItemRoles) {
            Roles viewRoles = new Roles();
            RolController controllerRole = new RolController(viewRoles);
            this.viewPrincipal.desktopPane.add(viewRoles);
            viewRoles.toFront();
            viewRoles.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.ItemMiPerfil) {
            MiPerfil viewMiPerfil = new MiPerfil();
            MiPerfilController controllerMiPerfil = new MiPerfilController(viewMiPerfil);
            this.viewPrincipal.desktopPane.add(viewMiPerfil);
            viewMiPerfil.toFront();
            viewMiPerfil.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.itemDocentes) {
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
            Usuarios viewUsuarios = new Usuarios();
            UsuarioController controllerUsuario = new UsuarioController(viewUsuarios);
            this.viewPrincipal.desktopPane.add(viewUsuarios);
            viewUsuarios.toFront();
            viewUsuarios.setVisible(true);
        }
        
        if (e.getSource() ==this.viewPrincipal.ItemHorarios) {
            Horarios viewHorarios = new  Horarios();
            HorarioController controllerHorario = new HorarioController(viewHorarios);
            this.viewPrincipal.desktopPane.add(viewHorarios);
            viewHorarios.toFront();
            viewHorarios.setVisible(true);
        }
        
        if (e.getSource() == this.viewPrincipal.btnProgramas) {
            this.viewPrincipal.ItemProgramas.doClick();
        }
        
        if (e.getSource() == this.viewPrincipal.btnAsignatura) {
            this.viewPrincipal.ItemAsignaturas.doClick();
        }
        
        if (e.getSource() == this.viewPrincipal.btnUsuarios) {
            this.viewPrincipal.ItemUsuarios.doClick();
        }
        
        if (e.getSource() == this.viewPrincipal.btnDocentes) {
            this.viewPrincipal.itemDocentes.doClick();
        }
        
    }
    
    private void getUsuario(int id) {
        user = this.daoUsuario.getUsuario(id);
        this.manageMenu();
    }
    
    private void manageMenu() {
        // Si es distinto de Admin
        if (user.getRolId() != 1) {
            this.viewPrincipal.mParametrizacion.setVisible(false);
            this.viewPrincipal.mItemRoles.setVisible(false);
            this.viewPrincipal.ItemUsuarios.setVisible(false);
            this.viewPrincipal.itemDocentes.setVisible(false);
            this.viewPrincipal.ItemHorarios.setVisible(false);
            this.viewPrincipal.btnProgramas.setVisible(false);
            this.viewPrincipal.btnAsignatura.setVisible(false);
            this.viewPrincipal.btnUsuarios.setVisible(false);
            this.viewPrincipal.btnDocentes.setVisible(false);
        }
    }
}
