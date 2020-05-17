/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DocenteDaoImpl;
import dao.ProfesionDaoImpl;
import dao.TipoDocumentoDaoImpl;
import helpers.ExportarExcel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Dia;
import models.Docente;
import models.Profesion;
import models.TipoDocumento;
import vista.Docentes;

/**
 *
 * @author miguel
 */
public class DocenteController implements ActionListener {
    
    Docentes viewDocentes = new Docentes();
    DocenteDaoImpl daoDocente = new DocenteDaoImpl();
    Docente docenteEdit = new Docente();
    
    
    ProfesionDaoImpl daoProfesion = new ProfesionDaoImpl();
    List<Profesion> profesiones;
    
    TipoDocumentoDaoImpl daoTipoDocumento = new TipoDocumentoDaoImpl();
    List<TipoDocumento> tipoDocumentos;
    
    public DocenteController(Docentes viewDocentes) {
        this.viewDocentes = viewDocentes;
        
        this.profesiones = this.daoProfesion.list();
        this.setComboBoxProfesiones();
        
        this.tipoDocumentos = this.daoTipoDocumento.list();
        this.setComboBoxTipoDocumentos();
        
        this.viewDocentes.btnNuevo.addActionListener(this);
        this.viewDocentes.btnCancelar.addActionListener(this);
        this.viewDocentes.btnEliminar.addActionListener(this);
        this.viewDocentes.btnGuardar.addActionListener(this);
        this.viewDocentes.btnImprimir.addActionListener(this);
        
        this.setTable();
        this.btnEnable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewDocentes.btnNuevo) {
            this.btnEnable(false);
        }
        
        if (e.getSource() == this.viewDocentes.btnCancelar) {
            this.btnEnable(true);
        }
        
        if (e.getSource() == this.viewDocentes.btnEliminar) {
            int input = JOptionPane.showConfirmDialog(null, 
                "Confirma la eliminaciòn de del registro?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                if (this.daoDocente.delete(docenteEdit)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado de forma correcta!");
                    this.setTable();
                    this.btnEnable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: no se puedo eliminar el registro!");
                }
            }
        }
        
        if (e.getSource() == this.viewDocentes.btnGuardar) {
            
            if (this.validateField()) {
                TipoDocumento tipoDocumento = this.tipoDocumentos.get(this.viewDocentes.txtTipoDocumento.getSelectedIndex());
                Profesion profesion = this.profesiones.get(this.viewDocentes.txtProfesiones.getSelectedIndex());
                
                if (docenteEdit.getId() != 0) {
                    docenteEdit.setIdentificacion(Integer.parseInt(this.viewDocentes.txtNumeroIdentificacion.getText()));
                    docenteEdit.setUsuarioId(1);
                    docenteEdit.setTipoDocumento(tipoDocumento.getId());
                    docenteEdit.setProfesionId(profesion.getId());
                    docenteEdit.setNombres(this.viewDocentes.txtNombres.getText());
                    docenteEdit.setApellidos(this.viewDocentes.txtApellidos.getText());
                    docenteEdit.setFechaNacimiento(this.viewDocentes.txtFechaNacimiento.getText());
                    docenteEdit.setCorreo(this.viewDocentes.txtCorreo.getText());
                    if (this.daoDocente.update(docenteEdit)) {
                        JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta!");
                        this.setTable();
                        this.btnEnable(true);
                    }
                } else {
                    Docente docente = new Docente();
                    docente.setIdentificacion(Integer.parseInt(this.viewDocentes.txtNumeroIdentificacion.getText()));
                    docente.setUsuarioId(1);
                    docente.setTipoDocumento(tipoDocumento.getId());
                    docente.setProfesionId(profesion.getId());
                    docente.setNombres(this.viewDocentes.txtNombres.getText());
                    docente.setApellidos(this.viewDocentes.txtApellidos.getText());
                    docente.setFechaNacimiento(this.viewDocentes.txtFechaNacimiento.getText());
                    docente.setCorreo(this.viewDocentes.txtCorreo.getText());
                    if (this.daoDocente.save(docente)) {
                        JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta!");
                        this.setTable();
                        this.btnEnable(true);
                    }
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Por favor verificar los todos los campos son requeridos...s");
            }
        }
        
        if (e.getSource() == this.viewDocentes.btnImprimir) {
            try {
		ExportarExcel.exportarUnaTabla(this.viewDocentes.tblData, "docentes");
             } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
             }
        }
    }
    
    private void setComboBoxProfesiones() {
        for (int i = 0; i < this.profesiones.size(); i++) {
            String item = this.profesiones.get(i).getDescripcion();
            this.viewDocentes.txtProfesiones.addItem(item);
        }
    }
    
    private void setComboBoxTipoDocumentos() {
        for (int i = 0; i < this.tipoDocumentos.size(); i++) {
            String item = this.tipoDocumentos.get(i).getDescripcion();
            this.viewDocentes.txtTipoDocumento.addItem(item);
        }
    }
    
    private void setTable() {
        String[] cols = { "Tipo de Cocumento", "Num. Identificacion", "Profesion", "Nombres", "Apellidos", "Fecha Nacimiento", "Correo" };
        this.viewDocentes.tblData.setModel(new DefaultTableModel(null, cols));
        List<Docente> lista = this.daoDocente.list();
        DefaultTableModel model = (DefaultTableModel) this.viewDocentes.tblData.getModel();
        Object[] fila = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getTipoDocumentoText();
            fila[1] = lista.get(i).getIdentificacion();
            fila[2] = lista.get(i).getProfesionText();
            fila[3] = lista.get(i).getNombres();
            fila[4] = lista.get(i).getApellidos();
            fila[5] = lista.get(i).getFechaNacimiento();
            fila[6] = lista.get(i).getCorreo();
            model.addRow(fila);
        }
       
        this.viewDocentes.tblData.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int linea = table.getSelectedRow();
                    docenteEdit = lista.get(linea);
                    btnEnable(false);
                    viewDocentes.txtNombres.setText(docenteEdit.getNombres());
                    viewDocentes.txtApellidos.setText(docenteEdit.getApellidos());
                    viewDocentes.txtCorreo.setText(docenteEdit.getCorreo());
                    viewDocentes.txtFechaNacimiento.setText(docenteEdit.getFechaNacimiento());
                    viewDocentes.txtNumeroIdentificacion.setText(""+docenteEdit.getIdentificacion());
                    viewDocentes.txtTipoDocumento.setSelectedIndex(getPositionTipoDocumento(docenteEdit.getTipoDocumento()));
                    viewDocentes.txtProfesiones.setSelectedIndex(getPositionProfesion(docenteEdit.getProfesionId()));
                    viewDocentes.btnEliminar.setEnabled(true);
                }
            }
        });
    }
    
    private void btnEnable(boolean enabled) {
        this.viewDocentes.txtTipoDocumento.setSelectedIndex(-1);
        this.viewDocentes.txtProfesiones.setSelectedIndex(-1);
        this.viewDocentes.txtNumeroIdentificacion.setText("");
        this.viewDocentes.txtNombres.setText("");
        this.viewDocentes.txtApellidos.setText("");
        this.viewDocentes.txtFechaNacimiento.setText("");
        this.viewDocentes.txtCorreo.setText("");
        
        this.viewDocentes.btnNuevo.setEnabled(enabled);
        this.viewDocentes.btnCancelar.setEnabled(!enabled);
        this.viewDocentes.btnEliminar.setEnabled(false);
        this.viewDocentes.btnGuardar.setEnabled(!enabled);
        
        this.viewDocentes.txtTipoDocumento.setEnabled(!enabled);
        this.viewDocentes.txtProfesiones.setEnabled(!enabled);
        this.viewDocentes.txtNumeroIdentificacion.setEnabled(!enabled);
        this.viewDocentes.txtNombres.setEnabled(!enabled);
        this.viewDocentes.txtApellidos.setEnabled(!enabled);
        this.viewDocentes.txtFechaNacimiento.setEnabled(!enabled);
        this.viewDocentes.txtCorreo.setEnabled(!enabled);
        
    }
    
    private boolean validateField() {
        if ((this.viewDocentes.txtTipoDocumento.getSelectedIndex() != -1) && 
            (this.viewDocentes.txtNumeroIdentificacion.getText().length() > 0) &&
            (this.viewDocentes.txtProfesiones.getSelectedIndex() != -1) &&
            (this.viewDocentes.txtNombres.getText().length() > 0) &&
            (this.viewDocentes.txtApellidos.getText().length() > 0) &&
            (this.viewDocentes.txtFechaNacimiento.getText().length() > 0) &&
            (this.viewDocentes.txtCorreo.getText().length() > 0)) {
             return true;
        } else {
            return false;
        }
    }
    
    
    private int getPositionTipoDocumento(int id) {
        int index = -1;
        for (int i = 0; i < tipoDocumentos.size(); i++) {
            if (tipoDocumentos.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }
    
    private int getPositionProfesion(int id) {
        int index = -1;
        for (int i = 0; i < profesiones.size(); i++) {
            if (profesiones.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }
    
}
