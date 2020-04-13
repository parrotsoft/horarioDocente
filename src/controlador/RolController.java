/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.RolDaoImpl;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Dia;
import models.Rol;
import vista.Roles;

/**
 *
 * @author miguel
 */
public class RolController implements ActionListener {

    Roles viewRoles = new Roles();
    RolDaoImpl daoRol = new RolDaoImpl();
    Rol rolEdit = new Rol();
    
    public RolController(Roles viewRoles) {
        this.viewRoles = viewRoles;
        this.viewRoles.btnNuevo.addActionListener(this);
        this.viewRoles.btnCancelar.addActionListener(this);
        this.viewRoles.btnEliminar.addActionListener(this);
        this.viewRoles.btnGuardar.addActionListener(this);
        this.setTable();
        this.btnEnable(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewRoles.btnNuevo) {
            this.btnEnable(false);
        }
        
        if (e.getSource() == this.viewRoles.btnCancelar) {
            this.btnEnable(true);
        }
        
        if (e.getSource() == this.viewRoles.btnEliminar) {
            int input = JOptionPane.showConfirmDialog(null, 
                "Confirma la eliminaciòn de del registro?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                if (this.daoRol.delete(rolEdit)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado de forma correcta!");
                    this.setTable();
                    this.btnEnable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: no se puedo eliminar el registro!");
                }
            }
        }
        
        if (e.getSource() == this.viewRoles.btnGuardar) {
            if (this.viewRoles.txtDescripcion.getText().length() > 0) {
                if (rolEdit.getId() != 0) {
                    rolEdit.setDescripcion(this.viewRoles.txtDescripcion.getText());
                    if (this.daoRol.update(rolEdit)) {
                        JOptionPane.showMessageDialog(null, "Registro actualizado de forma correcta!");
                        rolEdit = new Rol();
                        this.setTable();
                        this.btnEnable(true);
                    }
                } else {
                    Rol rol = new Rol();
                    rol.setDescripcion(this.viewRoles.txtDescripcion.getText());
                    if (this.daoRol.save(rol)) {
                        JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta!");
                        this.setTable();
                        this.btnEnable(true);
                    }
                }
                
            }
        }
    }
 
    private void setTable() {
        String[] cols = { "Descripcion" };
        this.viewRoles.tblData.setModel(new DefaultTableModel(null, cols));
        List<Rol> lista = this.daoRol.list();
        DefaultTableModel model = (DefaultTableModel) this.viewRoles.tblData.getModel();
        Object[] fila = new Object[1];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getDescripcion();
            model.addRow(fila);
        }
       
        this.viewRoles.tblData.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int linea = table.getSelectedRow();
                    rolEdit = lista.get(linea);
                    btnEnable(false);
                    viewRoles.txtDescripcion.setText(rolEdit.getDescripcion());
                    viewRoles.btnEliminar.setEnabled(true);
                }
            }
        });
    }
    
    private void btnEnable(boolean enabled) {
        this.viewRoles.txtDescripcion.setText("");
        this.viewRoles.btnNuevo.setEnabled(enabled);
        this.viewRoles.btnCancelar.setEnabled(!enabled);
        this.viewRoles.btnEliminar.setEnabled(false);
        this.viewRoles.btnGuardar.setEnabled(!enabled);
        this.viewRoles.txtDescripcion.setEnabled(!enabled);
    }

}
