/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DocenteDaoImpl;
import dao.RolDaoImpl;
import dao.UsuarioDaoImpl;
import helpers.ExportarExcel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Docente;
import models.Profesion;
import models.Rol;
import models.TipoDocumento;
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
    
    List<Docente> docentes;
    DocenteDaoImpl daoDocente = new DocenteDaoImpl();
    
    List<Rol> roles;
    RolDaoImpl daoRol = new RolDaoImpl();
    
    
    public UsuarioController(Usuarios viewUsuarios) {
        this.viewUsuarios = viewUsuarios;
        
        this.docentes = daoDocente.list();
        this.roles = daoRol.list();
        
        this.setComboBoxDocentes();
        this.setComboBoxRoles();
        
        this.viewUsuarios.btnNuevo.addActionListener(this);
        this.viewUsuarios.btnCancelar.addActionListener(this);
        this.viewUsuarios.btnEliminar.addActionListener(this);
        this.viewUsuarios.btnGuardar.addActionListener(this);
        this.viewUsuarios.btnImprimir.addActionListener(this);
        
        this.setTable();
        this.btnEnable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewUsuarios.btnNuevo) {
            this.btnEnable(false);
        }
        
        if (e.getSource() == this.viewUsuarios.btnCancelar) {
            this.btnEnable(true);
        }
        
        if (e.getSource() == this.viewUsuarios.btnEliminar) {
            int input = JOptionPane.showConfirmDialog(null, 
                "Confirma la eliminaciòn de del registro?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                if (this.daoUsuario.delete(usuarioEdit)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado de forma correcta!");
                    this.setTable();
                    this.btnEnable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: no se puedo eliminar el registro!");
                }
            }
        }
        
        if (e.getSource() == this.viewUsuarios.btnGuardar) {
            
            if (this.validateField()) {
                Docente docente = this.docentes.get(this.viewUsuarios.txtDocente.getSelectedIndex());
                Rol rol = this.roles.get(this.viewUsuarios.txtRol.getSelectedIndex());
                
                if (usuarioEdit.getId() != 0) {
                    usuarioEdit.setDocenteId(docente.getId());
                    usuarioEdit.setRolId(rol.getId());
                    usuarioEdit.setUsuario(this.viewUsuarios.txtUsuario.getText());
                    usuarioEdit.setClave(this.viewUsuarios.txtClave.getText());
                    
                    if (this.daoUsuario.update(usuarioEdit)) {
                        JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta!");
                        this.setTable();
                        this.btnEnable(true);
                    }
                } else {
                    Usuario usuario = new Usuario();
                    usuario.setDocenteId(docente.getId());
                    usuario.setRolId(rol.getId());
                    usuario.setUsuario(this.viewUsuarios.txtUsuario.getText());
                    usuario.setClave(this.viewUsuarios.txtClave.getText());
                    if (this.daoUsuario.save(usuario)) {
                        JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta!");
                        this.setTable();
                        this.btnEnable(true);
                    }
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Por favor verificar los todos los campos son requeridos...s");
            }
        }
        
        if (e.getSource() == this.viewUsuarios.btnImprimir) {
            try {
		ExportarExcel.exportarUnaTabla(this.viewUsuarios.tblData, "usuarios");
             } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
             }
        }
    }
    
    private void setTable() {
        String[] cols = { "Rol", "Usuario", "Nombre", "Apellido" };
        this.viewUsuarios.tblData.setModel(new DefaultTableModel(null, cols));
        List<Usuario> lista = this.daoUsuario.list();
        DefaultTableModel model = (DefaultTableModel) this.viewUsuarios.tblData.getModel();
        Object[] fila = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getFecha_nacimiento();
            fila[1] = lista.get(i).getUsuario();
            fila[2] = lista.get(i).getNombres();
            fila[3] = lista.get(i).getApellidos();
            model.addRow(fila);
        }
       
        this.viewUsuarios.tblData.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int linea = table.getSelectedRow();
                    usuarioEdit = lista.get(linea);
                    btnEnable(false);
                    
                    viewUsuarios.txtUsuario.setText(usuarioEdit.getUsuario());
                    viewUsuarios.txtClave.setText(usuarioEdit.getClave());
                    viewUsuarios.txtDocente.setSelectedIndex(getPositionDocente(usuarioEdit.getDocenteId()));
                    viewUsuarios.txtRol.setSelectedIndex(getPositionRol(usuarioEdit.getRolId()));
                    viewUsuarios.btnEliminar.setEnabled(true);
                }
            }
        });
    }
    
    private int getPositionDocente(int id) {
        int index = -1;
        for (int i = 0; i < docentes.size(); i++) {
            if (docentes.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }
    
    private int getPositionRol(int id) {
        int index = -1;
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }
    
    private void setComboBoxDocentes() {
        for (int i = 0; i < this.docentes.size(); i++) {
            String item = this.docentes.get(i).getNombres() + " " + this.docentes.get(i).getApellidos();
            this.viewUsuarios.txtDocente.addItem(item);
        }
    }
    
    private void setComboBoxRoles() {
        for (int i = 0; i < this.roles.size(); i++) {
            String item = this.roles.get(i).getDescripcion();
            this.viewUsuarios.txtRol.addItem(item);
        }
    }
    
    private void btnEnable(boolean enabled) {
        this.viewUsuarios.txtDocente.setSelectedIndex(-1);
        this.viewUsuarios.txtRol.setSelectedIndex(-1);
        this.viewUsuarios.txtUsuario.setText("");
        this.viewUsuarios.txtClave.setText("");
     
        this.viewUsuarios.btnNuevo.setEnabled(enabled);
        this.viewUsuarios.btnCancelar.setEnabled(!enabled);
        this.viewUsuarios.btnEliminar.setEnabled(false);
        this.viewUsuarios.btnGuardar.setEnabled(!enabled);
        
        this.viewUsuarios.txtDocente.setEnabled(!enabled);
        this.viewUsuarios.txtRol.setEnabled(!enabled);
        this.viewUsuarios.txtUsuario.setEnabled(!enabled);
        this.viewUsuarios.txtClave.setEnabled(!enabled);
    }
    
    private boolean validateField() {
        if ((this.viewUsuarios.txtDocente.getSelectedIndex() != -1) && 
            (this.viewUsuarios.txtRol.getSelectedIndex() != -1) &&
            (this.viewUsuarios.txtUsuario.getText().length() > 0) &&
            (this.viewUsuarios.txtClave.getText().length() > 0)) {
             return true;
        } else {
            return false;
        }
    }
    
}
