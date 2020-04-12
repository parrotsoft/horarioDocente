/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AsignaturaDaoImpl;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Asignatura;
import vista.Asignaturas;

/**
 *
 * @author miguel
 */
public class AsignaturaController implements ActionListener {

    Asignaturas viewAsignatura = new Asignaturas();
    AsignaturaDaoImpl daoAsignatura = new AsignaturaDaoImpl();
    Asignatura asignaturaEdit = new Asignatura();
    
    public AsignaturaController(Asignaturas viewAsignatura) {
        this.viewAsignatura = viewAsignatura;
        this.viewAsignatura.btnNuevo.addActionListener(this);
        this.viewAsignatura.btnCancelar.addActionListener(this);
        this.viewAsignatura.btnEliminar.addActionListener(this);
        this.viewAsignatura.btnGuardar.addActionListener(this);
        this.setTable();
        this.btnEnable(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewAsignatura.btnNuevo) {
            this.btnEnable(false);
        }
        
        if (e.getSource() == this.viewAsignatura.btnCancelar) {
            this.btnEnable(true);
        }
        
        if (e.getSource() == this.viewAsignatura.btnEliminar) {
            int input = JOptionPane.showConfirmDialog(null, 
                "Confirma la eliminaciòn de del registro?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                if (this.daoAsignatura.delete(asignaturaEdit)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado de forma correcta!");
                    this.setTable();
                    this.btnEnable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: no se puedo eliminar el registro!");
                }
            }
        }
        
        if (e.getSource() == this.viewAsignatura.btnGuardar) {
            if (this.viewAsignatura.txtDescripcion.getText().length() > 0 && this.viewAsignatura.txtCreditos.getText().length() > 0) {
                if (asignaturaEdit.getId() != 0) {
                    asignaturaEdit.setDescripcion(this.viewAsignatura.txtDescripcion.getText());
                    asignaturaEdit.setCreditos( Integer.parseInt(this.viewAsignatura.txtCreditos.getText()));
                    if (this.daoAsignatura.update(asignaturaEdit)) {
                        JOptionPane.showMessageDialog(null, "Registro actualizado de forma correcta!");
                        asignaturaEdit = new Asignatura();
                        this.setTable();
                        this.btnEnable(true);
                    }
                } else {
                    Asignatura asignatura = new Asignatura();
                    asignatura.setDescripcion(this.viewAsignatura.txtDescripcion.getText());
                    asignatura.setCreditos( Integer.parseInt(this.viewAsignatura.txtCreditos.getText()));
                    if (this.daoAsignatura.save(asignatura)) {
                        JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta!");
                        this.setTable();
                        this.btnEnable(true);
                    }
                }
                
            }
        }
    }
    
    private void setTable() {
        String[] cols = { "Descripcion", "Creditos" };
        this.viewAsignatura.tblData.setModel(new DefaultTableModel(null, cols));
        List<Asignatura> lista = this.daoAsignatura.list();
        DefaultTableModel model = (DefaultTableModel) this.viewAsignatura.tblData.getModel();
        Object[] fila = new Object[2];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getDescripcion();
            fila[1] = lista.get(i).getCreditos();
            model.addRow(fila);
        }
       
        this.viewAsignatura.tblData.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int linea = table.getSelectedRow();
                    asignaturaEdit = lista.get(linea);
                    btnEnable(false);
                    viewAsignatura.txtDescripcion.setText(asignaturaEdit.getDescripcion());
                    viewAsignatura.txtCreditos.setText(""+asignaturaEdit.getCreditos());
                    viewAsignatura.btnEliminar.setEnabled(true);
                }
            }
        });
    }
    
    private void btnEnable(boolean enabled) {
        this.viewAsignatura.txtDescripcion.setText("");
        this.viewAsignatura.txtCreditos.setText("");
        this.viewAsignatura.btnNuevo.setEnabled(enabled);
        this.viewAsignatura.btnCancelar.setEnabled(!enabled);
        this.viewAsignatura.btnEliminar.setEnabled(false);
        this.viewAsignatura.btnGuardar.setEnabled(!enabled);
        this.viewAsignatura.txtDescripcion.setEnabled(!enabled);
        this.viewAsignatura.txtCreditos.setEnabled(!enabled);
    }
    
}
