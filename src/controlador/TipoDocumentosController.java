/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.TipoDocumentoDaoImpl;
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
import models.TipoDocumento;
import vista.TipoDocumentos;

/**
 *
 * @author miguel
 */
public class TipoDocumentosController implements ActionListener {
    
    TipoDocumentos viewTipoDocumento = new TipoDocumentos();
    TipoDocumentoDaoImpl daoTipoDocumento = new TipoDocumentoDaoImpl();
    TipoDocumento tipoDocumentoEdit = new TipoDocumento();
    
    public TipoDocumentosController(TipoDocumentos viewTipoDocumento) {
        this.viewTipoDocumento = viewTipoDocumento;
        this.viewTipoDocumento.btnNuevo.addActionListener(this);
        this.viewTipoDocumento.btnCancelar.addActionListener(this);
        this.viewTipoDocumento.btnEliminar.addActionListener(this);
        this.viewTipoDocumento.btnGuardar.addActionListener(this);
        this.setTable();
        this.btnEnable(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewTipoDocumento.btnNuevo) {
            this.btnEnable(false);
        }
        
        if (e.getSource() == this.viewTipoDocumento.btnCancelar) {
            this.btnEnable(true);
        }
        
        if (e.getSource() == this.viewTipoDocumento.btnEliminar) {
            int input = JOptionPane.showConfirmDialog(null, 
                "Confirma la eliminaciòn de del registro?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                if (this.daoTipoDocumento.delete(tipoDocumentoEdit)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado de forma correcta!");
                    this.setTable();
                    this.btnEnable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: no se puedo eliminar el registro!");
                }
            }
        }
        
        if (e.getSource() == this.viewTipoDocumento.btnGuardar) {
            if (this.viewTipoDocumento.txtDescripcion.getText().length() > 0) {
                if (tipoDocumentoEdit.getId() != 0) {
                    tipoDocumentoEdit.setDescripcion(this.viewTipoDocumento.txtDescripcion.getText());
                    if (this.daoTipoDocumento.update(tipoDocumentoEdit)) {
                        JOptionPane.showMessageDialog(null, "Registro actualizado de forma correcta!");
                        tipoDocumentoEdit = new TipoDocumento();
                        this.setTable();
                        this.btnEnable(true);
                    }
                } else {
                    TipoDocumento tipoDocumento = new TipoDocumento();
                    tipoDocumento.setDescripcion(this.viewTipoDocumento.txtDescripcion.getText());
                    if (this.daoTipoDocumento.save(tipoDocumento)) {
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
        this.viewTipoDocumento.tblData.setModel(new DefaultTableModel(null, cols));
        List<TipoDocumento> lista = this.daoTipoDocumento.list();
        DefaultTableModel model = (DefaultTableModel) this.viewTipoDocumento.tblData.getModel();
        Object[] fila = new Object[1];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getDescripcion();
            model.addRow(fila);
        }
       
        this.viewTipoDocumento.tblData.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int linea = table.getSelectedRow();
                    tipoDocumentoEdit = lista.get(linea);
                    btnEnable(false);
                    viewTipoDocumento.txtDescripcion.setText(tipoDocumentoEdit.getDescripcion());
                    viewTipoDocumento.btnEliminar.setEnabled(true);
                }
            }
        });
    }
    
    private void btnEnable(boolean enabled) {
        this.viewTipoDocumento.txtDescripcion.setText("");
        this.viewTipoDocumento.btnNuevo.setEnabled(enabled);
        this.viewTipoDocumento.btnCancelar.setEnabled(!enabled);
        this.viewTipoDocumento.btnEliminar.setEnabled(false);
        this.viewTipoDocumento.btnGuardar.setEnabled(!enabled);
        this.viewTipoDocumento.txtDescripcion.setEnabled(!enabled);
    }    
}
