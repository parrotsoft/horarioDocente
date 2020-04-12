/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ProgramaDaoImpl;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Programa;
import vista.Programas;

/**
 *
 * @author miguel
 */
public class ProgramaController implements ActionListener {
    
    Programas viewPrograma = new Programas();
    ProgramaDaoImpl daoPrograma = new ProgramaDaoImpl();
    Programa programaEdit = new Programa();
    
    public ProgramaController(Programas viewPrograma) {
        this.viewPrograma = viewPrograma;
        this.viewPrograma.btnNuevo.addActionListener(this);
        this.viewPrograma.btnCancelar.addActionListener(this);
        this.viewPrograma.btnEliminar.addActionListener(this);
        this.viewPrograma.btnGuardar.addActionListener(this);
        this.setTable();
        this.btnEnable(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewPrograma.btnNuevo) {
            this.btnEnable(false);
        }
        
        if (e.getSource() == this.viewPrograma.btnCancelar) {
            this.btnEnable(true);
        }
        
        if (e.getSource() == this.viewPrograma.btnEliminar) {
            int input = JOptionPane.showConfirmDialog(null, 
                "Confirma la eliminaciòn de del registro?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                if (this.daoPrograma.delete(programaEdit)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado de forma correcta!");
                    this.setTable();
                    this.btnEnable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: no se puedo eliminar el registro!");
                }
            }
        }
        
        if (e.getSource() == this.viewPrograma.btnGuardar) {
            if (this.viewPrograma.txtDescripcion.getText().length() > 0) {
                if (programaEdit.getId() != 0) {
                    programaEdit.setDescripcion(this.viewPrograma.txtDescripcion.getText());
                    if (this.daoPrograma.update(programaEdit)) {
                        JOptionPane.showMessageDialog(null, "Registro actualizado de forma correcta!");
                        programaEdit = new Programa();
                        this.setTable();
                        this.btnEnable(true);
                    }
                } else {
                    Programa programa = new Programa();
                    programa.setDescripcion(this.viewPrograma.txtDescripcion.getText());
                    if (this.daoPrograma.save(programa)) {
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
        this.viewPrograma.tblData.setModel(new DefaultTableModel(null, cols));
        List<Programa> lista = this.daoPrograma.list();
        DefaultTableModel model = (DefaultTableModel) this.viewPrograma.tblData.getModel();
        Object[] fila = new Object[1];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getDescripcion();
            model.addRow(fila);
        }
       
        this.viewPrograma.tblData.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int linea = table.getSelectedRow();
                    programaEdit = lista.get(linea);
                    btnEnable(false);
                    viewPrograma.txtDescripcion.setText(programaEdit.getDescripcion());
                    viewPrograma.btnEliminar.setEnabled(true);
                }
            }
        });
    }
    
    private void btnEnable(boolean enabled) {
        this.viewPrograma.txtDescripcion.setText("");
        this.viewPrograma.btnNuevo.setEnabled(enabled);
        this.viewPrograma.btnCancelar.setEnabled(!enabled);
        this.viewPrograma.btnEliminar.setEnabled(false);
        this.viewPrograma.btnGuardar.setEnabled(!enabled);
        this.viewPrograma.txtDescripcion.setEnabled(!enabled);
    }
    
}
