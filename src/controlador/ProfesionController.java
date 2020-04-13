/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ProfesionDaoImpl;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Profesion;
import vista.Profesiones;

/**
 *
 * @author miguel
 */
public class ProfesionController implements ActionListener {

    Profesiones viewProfesion = new Profesiones();
    ProfesionDaoImpl daoProfesion = new ProfesionDaoImpl();
    Profesion profesionEdit = new Profesion();

    public ProfesionController(Profesiones viewProfesion) {
        this.viewProfesion = viewProfesion;
        this.viewProfesion.btnNuevo.addActionListener(this);
        this.viewProfesion.btnCancelar.addActionListener(this);
        this.viewProfesion.btnEliminar.addActionListener(this);
        this.viewProfesion.btnGuardar.addActionListener(this);
        this.setTable();
        this.btnEnable(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewProfesion.btnNuevo) {
            this.btnEnable(false);
        }
        
        if (e.getSource() == this.viewProfesion.btnCancelar) {
            this.btnEnable(true);
        }
        
        if (e.getSource() == this.viewProfesion.btnEliminar) {
            int input = JOptionPane.showConfirmDialog(null, 
                "Confirma la eliminaciòn de del registro?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                if (this.daoProfesion.delete(profesionEdit)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado de forma correcta!");
                    this.setTable();
                    this.btnEnable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: no se puedo eliminar el registro!");
                }
            }
        }
        
        if (e.getSource() == this.viewProfesion.btnGuardar) {
            if (this.viewProfesion.txtDescripcion.getText().length() > 0) {
                if (profesionEdit.getId() != 0) {
                    profesionEdit.setDescripcion(this.viewProfesion.txtDescripcion.getText());
                    if (this.daoProfesion.update(profesionEdit)) {
                        JOptionPane.showMessageDialog(null, "Registro actualizado de forma correcta!");
                        profesionEdit = new Profesion();
                        this.setTable();
                        this.btnEnable(true);
                    }
                } else {
                    Profesion profesion = new Profesion();
                    profesion.setDescripcion(this.viewProfesion.txtDescripcion.getText());
                    if (this.daoProfesion.save(profesion)) {
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
        this.viewProfesion.tblData.setModel(new DefaultTableModel(null, cols));
        List<Profesion> lista = this.daoProfesion.list();
        DefaultTableModel model = (DefaultTableModel) this.viewProfesion.tblData.getModel();
        Object[] fila = new Object[1];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getDescripcion();
            model.addRow(fila);
        }
       
        this.viewProfesion.tblData.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int linea = table.getSelectedRow();
                    profesionEdit = lista.get(linea);
                    btnEnable(false);
                    viewProfesion.txtDescripcion.setText(profesionEdit.getDescripcion());
                    viewProfesion.btnEliminar.setEnabled(true);
                }
            }
        });
    }
    
    private void btnEnable(boolean enabled) {
        this.viewProfesion.txtDescripcion.setText("");
        this.viewProfesion.btnNuevo.setEnabled(enabled);
        this.viewProfesion.btnCancelar.setEnabled(!enabled);
        this.viewProfesion.btnEliminar.setEnabled(false);
        this.viewProfesion.btnGuardar.setEnabled(!enabled);
        this.viewProfesion.txtDescripcion.setEnabled(!enabled);
    }
    
}
