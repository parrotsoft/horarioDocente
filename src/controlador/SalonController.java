/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.SalonDaoImpl;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Salon;
import vista.Salones;

/**
 *
 * @author miguel
 */
public class SalonController implements ActionListener {
    
    Salones viewSalon = new Salones();
    SalonDaoImpl daoSalon = new SalonDaoImpl();
    Salon salonEdit = new Salon();
    
    
    public SalonController(Salones viewSalon) {
        this.viewSalon = viewSalon;
        this.viewSalon.btnNuevo.addActionListener(this);
        this.viewSalon.btnCancelar.addActionListener(this);
        this.viewSalon.btnEliminar.addActionListener(this);
        this.viewSalon.btnGuardar.addActionListener(this);
        this.setTable();
        this.btnEnable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewSalon.btnNuevo) {
            this.btnEnable(false);
        }
        
        if (e.getSource() == this.viewSalon.btnCancelar) {
            this.btnEnable(true);
        }
        
        if (e.getSource() == this.viewSalon.btnEliminar) {
            int input = JOptionPane.showConfirmDialog(null, 
                "Confirma la eliminaciòn de del registro?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                if (this.daoSalon.delete(salonEdit)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado de forma correcta!");
                    this.setTable();
                    this.btnEnable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: no se puedo eliminar el registro!");
                }
            }
        }
        
        if (e.getSource() == this.viewSalon.btnGuardar) {
            if (this.viewSalon.txtDescripcion.getText().length() > 0) {
                if (salonEdit.getId() != 0) {
                    salonEdit.setDescripcion(this.viewSalon.txtDescripcion.getText());
                    if (this.daoSalon.update(salonEdit)) {
                        JOptionPane.showMessageDialog(null, "Registro actualizado de forma correcta!");
                        salonEdit = new Salon();
                        this.setTable();
                        this.btnEnable(true);
                    }
                } else {
                    Salon salon = new Salon();
                    salon.setDescripcion(this.viewSalon.txtDescripcion.getText());
                    if (this.daoSalon.save(salon)) {
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
        this.viewSalon.tblData.setModel(new DefaultTableModel(null, cols));
        List<Salon> lista = this.daoSalon.list();
        DefaultTableModel model = (DefaultTableModel) this.viewSalon.tblData.getModel();
        Object[] fila = new Object[1];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getDescripcion();
            model.addRow(fila);
        }
       
        this.viewSalon.tblData.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int linea = table.getSelectedRow();
                    salonEdit = lista.get(linea);
                    btnEnable(false);
                    viewSalon.txtDescripcion.setText(salonEdit.getDescripcion());
                    viewSalon.btnEliminar.setEnabled(true);
                }
            }
        });
    }
    
    private void btnEnable(boolean enabled) {
        this.viewSalon.txtDescripcion.setText("");
        this.viewSalon.btnNuevo.setEnabled(enabled);
        this.viewSalon.btnCancelar.setEnabled(!enabled);
        this.viewSalon.btnEliminar.setEnabled(false);
        this.viewSalon.btnGuardar.setEnabled(!enabled);
        this.viewSalon.txtDescripcion.setEnabled(!enabled);
    }

}
