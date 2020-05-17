/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DiaDaoImpl;
import dao.DisponibilidadDaoImpl;
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
import models.Dia;
import models.Disponibilidad;
import models.Docente;
import models.Profesion;
import models.TipoDocumento;
import vista.Disponibilidades;

/**
 *
 * @author miguel
 */
public class DisponibilidadController implements ActionListener {
    
    Disponibilidades viewDisponibilidad = new Disponibilidades();
    DisponibilidadDaoImpl daoDisponibilidad = new DisponibilidadDaoImpl();
    Disponibilidad disponibilidadEdit = new Disponibilidad();
    
    DiaDaoImpl daoDia = new DiaDaoImpl();
    List<Dia> dias;

    public DisponibilidadController(Disponibilidades viewDisponibilidad) {
        this.viewDisponibilidad = viewDisponibilidad;
        
        this.dias = this.daoDia.list();
        
        this.viewDisponibilidad.btnNuevo.addActionListener(this);
        this.viewDisponibilidad.btnCancelar.addActionListener(this);
        this.viewDisponibilidad.btnEliminar.addActionListener(this);
        this.viewDisponibilidad.btnGuardar.addActionListener(this);
        this.viewDisponibilidad.btnImprimir.addActionListener(this);
        this.setComboBoxDias();
        this.setTable();
        this.btnEnable(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewDisponibilidad.btnNuevo) {
            this.btnEnable(false);
        }
        
        if (e.getSource() == this.viewDisponibilidad.btnCancelar) {
            this.btnEnable(true);
        }
        
        if (e.getSource() == this.viewDisponibilidad.btnEliminar) {
            int input = JOptionPane.showConfirmDialog(null, 
                "Confirma la eliminaciòn de del registro?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                if (this.daoDisponibilidad.delete(disponibilidadEdit)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado de forma correcta!");
                    this.setTable();
                    this.btnEnable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: no se puedo eliminar el registro!");
                }
            }
        }
        
        if (e.getSource() == this.viewDisponibilidad.btnGuardar) {
            
            if (this.validateField()) {
                Dia dia = this.dias.get(this.viewDisponibilidad.txtDia.getSelectedIndex());
                
                if (disponibilidadEdit.getId() != 0) {
                    
                    disponibilidadEdit.setDiaId(dia.getId());
                    disponibilidadEdit.setDocenteId(PrincipalController.user.getDocenteId()); 
                    disponibilidadEdit.setHoraInicial(this.viewDisponibilidad.txtHoraInicial.getText());
                    disponibilidadEdit.setHoraFinal(this.viewDisponibilidad.txtHoraFinal.getText());
                    disponibilidadEdit.setComentario(this.viewDisponibilidad.txtComentario.getText());
                    
                    if (this.daoDisponibilidad.update(disponibilidadEdit)) {
                        JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta!");
                        this.setTable();
                        this.btnEnable(true);
                    }
                } else {
                    Disponibilidad disponibilidad = new Disponibilidad();
                    disponibilidad.setDiaId(dia.getId());
                    disponibilidad.setDocenteId(PrincipalController.user.getDocenteId()); 
                    disponibilidad.setHoraInicial(this.viewDisponibilidad.txtHoraInicial.getText());
                    disponibilidad.setHoraFinal(this.viewDisponibilidad.txtHoraFinal.getText());
                    disponibilidad.setComentario(this.viewDisponibilidad.txtComentario.getText());
                    
                    if (this.daoDisponibilidad.save(disponibilidad)) {
                        JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta!");
                        this.setTable();
                        this.btnEnable(true);
                    }
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Por favor verificar los todos los campos son requeridos...s");
            }
        }
        
        if (e.getSource() == this.viewDisponibilidad.btnImprimir) {
            try {
		ExportarExcel.exportarUnaTabla(this.viewDisponibilidad.tblData, "disponibilidades");
             } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
             }
        }
    }
    
    private void setTable() {
        String[] cols = { "Dia", "Hora Inicial", "Hora Final" };
        this.viewDisponibilidad.tblData.setModel(new DefaultTableModel(null, cols));
        List<Disponibilidad> lista = this.daoDisponibilidad.listForDocente(PrincipalController.user.getDocenteId());
        DefaultTableModel model = (DefaultTableModel) this.viewDisponibilidad.tblData.getModel();
        Object[] fila = new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getDia();
            fila[1] = lista.get(i).getHoraInicial();
            fila[2] = lista.get(i).getHoraFinal();
            model.addRow(fila);
        }
       
        this.viewDisponibilidad.tblData.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int linea = table.getSelectedRow();
                    disponibilidadEdit = lista.get(linea);
                    btnEnable(false);
                    
                    viewDisponibilidad.txtDia.setSelectedIndex(getPositionDias(disponibilidadEdit.getDiaId()));
                    viewDisponibilidad.txtHoraInicial.setText(disponibilidadEdit.getHoraInicial());
                    viewDisponibilidad.txtHoraFinal.setText(disponibilidadEdit.getHoraFinal());
                    viewDisponibilidad.txtComentario.setText(disponibilidadEdit.getComentario());
                    viewDisponibilidad.btnEliminar.setEnabled(true);
                }
            }
        });
    }
    
    private void btnEnable(boolean enabled) {
        this.viewDisponibilidad.txtDia.setSelectedIndex(-1);
        this.viewDisponibilidad.txtHoraInicial.setText("");
        this.viewDisponibilidad.txtHoraFinal.setText("");
        this.viewDisponibilidad.txtComentario.setText("");
        
        this.viewDisponibilidad.btnNuevo.setEnabled(enabled);
        this.viewDisponibilidad.btnCancelar.setEnabled(!enabled);
        this.viewDisponibilidad.btnEliminar.setEnabled(false);
        this.viewDisponibilidad.btnGuardar.setEnabled(!enabled);
        
        this.viewDisponibilidad.txtDia.setEnabled(!enabled);
        this.viewDisponibilidad.txtHoraInicial.setEnabled(!enabled);
        this.viewDisponibilidad.txtHoraFinal.setEnabled(!enabled);
        this.viewDisponibilidad.txtComentario.setEnabled(!enabled);
    }

    private void setComboBoxDias() {
        for (int i = 0; i < this.dias.size(); i++) {
            String item = this.dias.get(i).getDescripcion();
            this.viewDisponibilidad.txtDia.addItem(item);
        }
    }
    
    private int getPositionDias(int id) {
        int index = -1;
        for (int i = 0; i < dias.size(); i++) {
            if (dias.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }
        
    private int getPositionDia(int id) {
        int index = -1;
        for (int i = 0; i < dias.size(); i++) {
            if (dias.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }
    
    private boolean validateField() {
        if ((this.viewDisponibilidad.txtDia.getSelectedIndex() != -1) && 
            (this.viewDisponibilidad.txtHoraInicial.getText().length() > 0) &&
            (this.viewDisponibilidad.txtHoraFinal.getText().length() > 0)) {
             return true;
        } else {
            return false;
        }
    }
        
}
