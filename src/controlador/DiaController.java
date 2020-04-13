/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DiaDaoImpl;
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
import vista.Dias;

/**
 *
 * @author miguel
 */
public class DiaController implements ActionListener {
    
    Dias viewDia = new Dias();
    DiaDaoImpl daoDia = new DiaDaoImpl();
    Dia diaEdit = new Dia();
    
    public DiaController(Dias viewDia) {
        this.viewDia = viewDia;
        this.viewDia.btnNuevo.addActionListener(this);
        this.viewDia.btnCancelar.addActionListener(this);
        this.viewDia.btnEliminar.addActionListener(this);
        this.viewDia.btnGuardar.addActionListener(this);
        this.setTable();
        this.btnEnable(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewDia.btnNuevo) {
            this.btnEnable(false);
        }
        
        if (e.getSource() == this.viewDia.btnCancelar) {
            this.btnEnable(true);
        }
        
        if (e.getSource() == this.viewDia.btnEliminar) {
            int input = JOptionPane.showConfirmDialog(null, 
                "Confirma la eliminaciòn de del registro?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                if (this.daoDia.delete(diaEdit)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado de forma correcta!");
                    this.setTable();
                    this.btnEnable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: no se puedo eliminar el registro!");
                }
            }
        }
        
        if (e.getSource() == this.viewDia.btnGuardar) {
            if (this.viewDia.txtDescripcion.getText().length() > 0) {
                if (diaEdit.getId() != 0) {
                    diaEdit.setDescripcion(this.viewDia.txtDescripcion.getText());
                    if (this.daoDia.update(diaEdit)) {
                        JOptionPane.showMessageDialog(null, "Registro actualizado de forma correcta!");
                        diaEdit = new Dia();
                        this.setTable();
                        this.btnEnable(true);
                    }
                } else {
                    Dia dia = new Dia();
                    dia.setDescripcion(this.viewDia.txtDescripcion.getText());
                    if (this.daoDia.save(dia)) {
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
        this.viewDia.tblData.setModel(new DefaultTableModel(null, cols));
        List<Dia> lista = this.daoDia.list();
        DefaultTableModel model = (DefaultTableModel) this.viewDia.tblData.getModel();
        Object[] fila = new Object[1];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getDescripcion();
            model.addRow(fila);
        }
       
        this.viewDia.tblData.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int linea = table.getSelectedRow();
                    diaEdit = lista.get(linea);
                    btnEnable(false);
                    viewDia.txtDescripcion.setText(diaEdit.getDescripcion());
                    viewDia.btnEliminar.setEnabled(true);
                }
            }
        });
    }
    
    private void btnEnable(boolean enabled) {
        this.viewDia.txtDescripcion.setText("");
        this.viewDia.btnNuevo.setEnabled(enabled);
        this.viewDia.btnCancelar.setEnabled(!enabled);
        this.viewDia.btnEliminar.setEnabled(false);
        this.viewDia.btnGuardar.setEnabled(!enabled);
        this.viewDia.txtDescripcion.setEnabled(!enabled);
    }
}
